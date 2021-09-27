package recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;

import detail.DetailDTO;
import ranking.KeywordDTO;
import member.SurveyDTO;

public class RecoDAOImpl implements RecoDAOInter{
	
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	@Autowired
	private RecoDAOInter daoReco = null;
	
	// SurveyDTO 생성
	@Autowired
	private SurveyDTO survey = null;
	
	// OutListDTO 생성
	@Autowired
	private OutListDTO outList = null;
	
	//로그인한 회원번호(index_member)를 넣어서 회원취향분석테이블(member_keyword_anal_회원번호)을 조회한다. 
	@Override
	public List <MemKeyAnalDTO> getMemKeyAnal(int index_member) throws Exception {
		return mybatis.selectList("getMemKeyAnal",index_member);
	}

	//작품별키워드분석테이블(keyword_anal)을 조회한다.
	@Override
	public List <KeyAnalDTO> getKeyAnal() throws Exception {
		return mybatis.selectList("getKeyAnal");
	}

	//추천된 작품번호(index_book)리스트를 넣어서 작품정보를 받아온다.
	@Override
	public List<DetailDTO> getRecoBook(List recoList) throws Exception {
		return mybatis.selectList("getRecoBook", recoList);
	}
	
	//분석할 결과를 저장할 임시테이블(temp_anal_index_member)에 데이터가 있는지 확인한다.
	@Override
	public int isTempAnal(int index_member) throws Exception{
		return mybatis.selectOne("isTempAnal", index_member);
	}
	
	//분석할 결과를 저장할 임시테이블(temp_anal_index_member)을 생성한다.
	@Override
	public void createTempAnal (int index_member)  throws Exception {
		mybatis.insert("createTempAnal", index_member);
	}
	
	//분석한 결과를 임시테이블(temp_anal_index_member)에 insert한다.
	@Override
	public void insertTempAnal(List tempAnalList) throws Exception {
		mybatis.insert("insertTempAnal", tempAnalList);
	}
	
	/* 
	 * 임시테이블에서 연관도 오름차순으로 추천리스트를 받아온다.
	 * List <TempAnalDTO> recoTotalList = mybatis.selectList("getRecoBook", index_member)
	 * 		: 코사인연관도 분석결과가 높은 순서로 추천순위 전체리스트를 받아온다.
	 */
	@Override
	public List getRecoList(int index_member) throws Exception{
		List <TempAnalDTO> recoTotalList = mybatis.selectList("getRecoList", index_member);
		
		int index = 0;
		List recoList = new ArrayList();
		for(TempAnalDTO total : recoTotalList) {
			recoList.add(total.getIndex_book());
			if(index > 30) {break;}
			index++;
			//System.out.println("index=="+index);
		}
		return recoList;
	}
	
	/*
	 * 회원키워드 누적점수(취향)과 작품의 키워드 가중치를 비교한다.
	 * 
	 * List <MemKeyAnalDTO> member = daoReco.getMemKeyAnal(index_member) 
	 * 		로그인한 회원의 Member_Key_Anal 테이블에서 취향점수를 MemKeyAnalDTO member로 저장한다.
	 * 
	 * int [] member_index_keyword 	: R에 Vector로 전달하기 위해 index_keyword를 배열로 저장 
	 * double [] member_cumul_score	: R에 Vector로 전달하기 위해 cumul_score를 배열로 저장  (회원취향누적점수)
	 * 
	 * List <KeyAnalDTO> KeyAnal = daoReco.getKeyAnal()
	 * 		Keyword_anal 테이블에서 각 작품의 키워드 점수를 KeyAnalDTO keyAnal로 저장한다.
	 * 
	 * int [] index_keyword	: R에 Vector로 전달하기 위해 index_keyword를 배열로 저장
	 * int [] index_book	: R에 Vector로 전달하기 위해 index_book를 배열로 저장
	 * double [] weight		: R에 Vector로 전달하기 위해 weight를 배열로 저장	(키워드 가중치 값)
	 * 
	 * rConn.eval("library(lsa)") : 코사인연관도를 분석하기 위한 라이브러리 호출
	 * 
	 * rCpnn.eval("cosine(회원취향점수, 작품키워드가중치)") : 작품과 category별 코사인 연관도 분석 실행
	 * 
	 * category100	: 캐릭터, index_keyword 100번대 코사인 연관도 값
	 * category200	: 분위기, index_keyword 200번대 코사인 연관도 값
	 * category300	: 소재, index_keyword 300번대 코사인 연관도 값
	 * category400	: 독자반응, index_keyword 400번대 코사인 연관도 값
	 * 
	 * Map <String, double []> category : <카테고리명, 코사인연관도 값> 
	 */
	
	@Override
	public Map setKeyAnalValue(int index_member) throws Exception{
		
		List <MemKeyAnalDTO> member = daoReco.getMemKeyAnal(index_member); 
		int [] member_index_keyword = new int[member.size()];
		double [] member_cumul_score = new double[member.size()];
		for (int i = 0; i < member.size(); i++) {
			member_index_keyword[i] = member.get(i).getIndex_keyword();
			member_cumul_score[i] = member.get(i).getCumul_score();
		}
		
		List <KeyAnalDTO> keyAnal = daoReco.getKeyAnal();
		int [] index_keyword = new int[keyAnal.size()];
		int [] index_book = new int[keyAnal.size()];
		double [] weight = new double[keyAnal.size()];
		
		for(int i = 0; i < keyAnal.size(); i++) {
			index_keyword[i] = keyAnal.get(i).getIndex_keyword();
			index_book[i] = keyAnal.get(i).getIndex_book();
			weight[i] = keyAnal.get(i).getWeight();
		}
		
		RConnection rConn = new RConnection();

		rConn.eval("library(lsa)");
		
		rConn.assign("member_index_keyword", member_index_keyword);
		rConn.assign("member_cumul_score", member_cumul_score);
		rConn.eval("member <- data.frame(index_keyword=member_index_keyword, cumul_score=member_cumul_score)");
		
		rConn.assign("index_keyword", index_keyword);
		rConn.assign("index_book", index_book);
		rConn.assign("weight", weight);
		rConn.eval("keyAnalTable <- data.frame(index_book=index_book, index_keyword=index_keyword, weight=weight)");
		
		//rConn.eval("memberScore <- member$cumul_score/sum(member$cumul_score)");
		rConn.eval("memberScore <- member$cumul_score");
		
		rConn.eval("category100 <- NULL");
		rConn.eval("category200 <- NULL");
		rConn.eval("category300 <- NULL");
		rConn.eval("category400 <- NULL");
		
		rConn.eval("for(index_book in c(1:390)){ "
				+ "  keyAnal <- keyAnalTable[keyAnalTable$index_book == index_book, ]; "
				+ "  category100 <- c(category100, cosine(memberScore[keyAnal$index_keyword%/%100 == 1], "
				+ "                    keyAnal$weight[keyAnal$index_keyword%/%100 == 1])); "
				+ "  category200 <- c(category200, cosine(memberScore[keyAnal$index_keyword%/%100 == 2], "
				+ "                    keyAnal$weight[keyAnal$index_keyword%/%100 == 2])); "
				+ "  category300 <- c(category300, cosine(memberScore[keyAnal$index_keyword%/%100 == 3], "
				+ "                    keyAnal$weight[keyAnal$index_keyword%/%100 == 3])); "
				+ "  category400 <- c(category400, cosine(memberScore[keyAnal$index_keyword%/%100 == 4], "
				+ "                   keyAnal$weight[keyAnal$index_keyword%/%100 == 4])); "
				+ "}");
		
		double [] category100 = rConn.eval("category100").asDoubles();
		double [] category200 = rConn.eval("category200").asDoubles();
		double [] category300 = rConn.eval("category300").asDoubles();
		double [] category400 = rConn.eval("category400").asDoubles();
		
		Map <String, double []> category = new HashMap();
		category.put("category100", category100);
		category.put("category200", category200);
		category.put("category300", category300);
		category.put("category400", category400);
		
		rConn.close();
		return category;
	}
	
	/*
	 * 취향분석 추천목록에서 제외할 리스트를 만든다.
	 * survey = daoReco.getSurvey(index_member)	: 설문조사에서 선호장르와 선호분량 정보를 가져온다.
	 * 선호분량 (장편:1(300편 초과), 중편:2(100 ~ 300), 단편:3(100 미만), 아무거나:4)
	 * 선호장르 (로맨스:1, 로판:2, 판타지:3, 현판:4, 무협:5) 
	 * 
	 * List outVolume							: 선택한 선호분량에 따라 제외할 분량 목록을 만든다. (ex> 장편(1)을 선택하면 2,3 만 남음)
	 * if(volume == 4 || outVolume.size() == 3)	: 아무거나(4)를 선택했거나, 설문조사에서 선호분량을 선택하지 않은 경우 리스트에 0만 남도록 한다.	
	 * 
	 * List outGenre							: 선택한 선호장르에 따라 제외할 장르 목록을 만든다. (ex> 로맨스(1), 판타지(3)을 선택하면 2,4,5 만 남음)
	 * survey.getRomance() == 0					: 설문조사시 장르를 선택하지 않으면 0, 선택하면 1이기 때문에 0인 경우에만 장르의 번호를 리스트에 추가한다.
	 * if(outGenre.size() == 5 || outGenre.size() == 0) : 모든 장르를 선택하거나, 설문조사에서 선호장르를 선택하지 않은 경우 리스트에 0만 남도록 한다.
	 * 
	 * outList	: 제외할 선호분량과 선호장르 번호를 outListDTO에 담는다.
	 */
	@Override
	public List getOutList(int index_member) throws Exception{
		
		survey = daoReco.getSurvey(index_member);
		
		int volume = survey.getVolume();
		
		List outVolume = new ArrayList();
		outVolume.add(1);
		outVolume.add(2);
		outVolume.add(3);
		
		outVolume.remove((Object) volume);
		
		if(volume == 4 || outVolume.size() == 3) {
			outVolume.clear();
			outVolume.add(0);
		}
		
		List outGenre = new ArrayList();
		if(survey.getRomance() == 0) {	outGenre.add(1);	}
		if(survey.getRofan() == 0) {	outGenre.add(2);	}
		if(survey.getFantagy() == 0) {	outGenre.add(3);	}
		if(survey.getMofan() == 0) {	outGenre.add(4);	}
		if(survey.getHeroism() == 0) {	outGenre.add(5);	}
		if(outGenre.size() == 5 || outGenre.size() == 0) {		
			outGenre.clear();
			outGenre.add(0);
		}

		outList.setIndex_member(index_member);
		outList.setOutVolume(outVolume);
		outList.setOutGenre(outGenre);
		
		return mybatis.selectList("getOutList",outList);
	}
	
	//survey 테이블에서 회원(index_member)의 정보를 불러온다.
	@Override
	public SurveyDTO getSurvey(int index_member) throws Exception{
		return mybatis.selectOne("getSurvey",index_member);
	}
	
	//TempAnal 테이블의 데이터들을 지운다.
	@Override
	public void clearValue(int index_member) throws Exception{
		mybatis.insert("clearValue", index_member);
	}
	
	
}
