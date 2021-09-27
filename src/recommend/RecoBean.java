package recommend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.DetailDTO;
import ranking.KeywordDAOInter;
import ranking.KeywordDTO;
import member.AnalysisDAOInter;
import member.SurveyDTO;

/*
 * 회원의 취향에 맞춘 작품을 추천해 준다. 
 * R이나 cmd창에서 Rserve를 실행해줘야 한다. 
 * */

@Controller
@RequestMapping("/recommend/")
public class RecoBean {
	
	//RecoDAOImpl 클래스 생성
	@Autowired
	private RecoDAOInter daoReco = null;
	
	//KeywordDAOImple 클래스 생성
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	// DetailDTO 생성
	@Autowired
	private DetailDTO detail = null;
	
	// SurveyDTO 생성
	@Autowired
	private SurveyDTO survey = null;
	
	// AnalysisDAOInter 생성
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	/*
	 * 연관도 0.8이상(미정) /한번에 15작품 / 최대 30작품을 추천해준다.
	 * 로그인 확인(세션 있는지 확인)후, 있는 경우에 취향분석을 진행한다.
	 * 
	 * List recoList 					: 개인취향분석 최종 추천리스트
	 * List reco 						: 추천리스트에 따른 DetailDTO 리스트 
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * 
	 * ***개인취향과 작품키워드가중치 코사인연관도 분석***
	 * analImpl.totalCount(index_member) != 0 	: 회원이 평가한 작품이 있는지 확인한다. 
	 * daoReco.isTempAnal(index_member) == 0	: temp_anal_index_member 임시테이블에 데이터가 있는지 확인한다.
	 * 											  평가한 작품이 있고, 임시테이블에 저장된 데이터가 없으면 취향분석을 진행한다.
	 * 
	 * Map category = daoReco.setKeyAnalValue(index_member) : 카테고리별 코사인 연관도 분석을 한다.
	 * 
	 * category100	: 캐릭터, index_keyword 100번대 코사인 연관도 값, 오름차순
	 * category200	: 분위기, index_keyword 200번대 코사인 연관도 값, 오름차순
	 * category300	: 소재, index_keyword 300번대 코사인 연관도 값, 오름차순
	 * category400	: 독자반응, index_keyword 400번대 코사인 연관도 값, 오름차순
	 * 
	 * daoReco.insertTempAnal(tempAnalList)		: temp_anal_index_member 임시테이블에 분석결과를 저장한다.
	 * 
	 * ***추천리스트 만들기***
	 * List <DetailDTO> getOutList = daoReco.getOutList(index_member)	: 제외목록
	 * 		read 테이블에서 관심없다(0), 다읽었다(3), rating 테이블에서 해당회원(index_member)이 평점 을 매긴 작품번호(index_book)들을 불러온다.
	 * recoList.remove((Object) list.getIndex_book()) 	: 추천목록에서 제외목록 작품번호들을 제거한다.
	 * 
	 * count = recoList.size()	: 추천리스트의 개수, 최대 30개까지 표시한다.
	 * 							  count와 endRow를 비교해서 count가 작은경우 endRow = count - 1로 설정한다.
	 * 		
	 * tempReco = daoReco.getRecoList(recoList)		: 추천리스트를 이용해서 작품의 DetailDTO(세부정보) 리스트를 만든다.
	 *		for(int index = startRow - 1; index <= endRow; index++) {	: 한페이지에 15개 작품이 보이도록 하는 작업이다.
	 *			reco.add(tempReco.get(index));
	 *		}
	 * 
	 * ***키워드 표시***
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 * 
	 * 
	 * */
	@RequestMapping("member.rv")
	public String myLogonmemberReco(HttpSession session, Model model, String pageNum) {
		
		int index_member = 0;
		int pageSize = 15;
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		int count = 0;
		
		List recoList = new ArrayList();
		List reco = new ArrayList();
		Map <String, List> keywordMap = new HashMap();
		
		if(session.getAttribute("index_member") != null) {
			index_member = (Integer) session.getAttribute("index_member");
			
			try {
				if(analImpl.totalCount(index_member) != 0) {
					if(daoReco.isTempAnal(index_member) == 0) {
						Map category = daoReco.setKeyAnalValue(index_member);

						double [] category100 = (double[]) category.get("category100");
						double [] category200 = (double[]) category.get("category200");
						double [] category300 = (double[]) category.get("category300");
						double [] category400 = (double[]) category.get("category400");
	
						List tempAnalList = new ArrayList();
						for(int index = 0; index < category100.length; index++) {
							TempAnalDTO tempAnal = new TempAnalDTO();
							tempAnal.setIndex_member(index_member);
							tempAnal.setIndex_book(index+1);
							if((""+category100[index]).equals("NaN")) {category100[index] = 0;}
							tempAnal.setValue(category100[index]);
							tempAnal.setCategory(100);
							
							tempAnalList.add(tempAnal);
						}
						
						for(int index = 0; index < category200.length; index++) {
							TempAnalDTO tempAnal = new TempAnalDTO();
							tempAnal.setIndex_member(index_member);
							tempAnal.setIndex_book(index+1);
							if((""+category200[index]).equals("NaN")) {category200[index] = 0;}
							tempAnal.setValue(category200[index]);
							tempAnal.setCategory(200);
							
							tempAnalList.add(tempAnal);
						}
	
						for(int index = 0; index < category300.length; index++) {
							TempAnalDTO tempAnal = new TempAnalDTO();
							tempAnal.setIndex_member(index_member);
							tempAnal.setIndex_book(index+1);
							if((""+category300[index]).equals("NaN")) {category300[index] = 0;}
							tempAnal.setValue(category300[index]);
							tempAnal.setCategory(300);
							
							tempAnalList.add(tempAnal);
						}
	
						for(int index = 0; index < category400.length; index++) {
							TempAnalDTO tempAnal = new TempAnalDTO();
							tempAnal.setIndex_member(index_member);
							tempAnal.setIndex_book(index+1);
							if((""+category400[index]).equals("NaN")) {category400[index] = 0;}
							tempAnal.setValue(category400[index]);
							tempAnal.setCategory(400);
							
							tempAnalList.add(tempAnal);
						}
						daoReco.insertTempAnal(tempAnalList);
					}
				}
				
				recoList = daoReco.getRecoList(index_member);
				
				List <OutListDTO> getOutList = daoReco.getOutList(index_member);
				
				for(OutListDTO list : getOutList) {
					recoList.remove((Object) list.getIndex_book());
				}
				
				if(recoList.size() >= 30) {
					count = 30;
				}else {
					count = recoList.size();
				}
				
				if(endRow > count) {
					endRow = count - 1;
					detail.setEndRow(endRow);
				}
				
				List <DetailDTO> tempReco = null;
				if(count > 0) {
					tempReco = daoReco.getRecoBook(recoList);
					for(int index = startRow - 1; index < endRow; index++) {
						reco.add(tempReco.get(index));
					}
				}
				
				if(reco.size() == 0){	count = 0;	}
				
				int index_book = 0;
				for(int i = 0; i < reco.size(); i++) {
					index_book = ((DetailDTO) reco.get(i)).getIndex_book();
					List keywords = daoKey.getKeyword(index_book);
					keywordMap.put(""+index_book, keywords);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", detail.getStartRow());
			model.addAttribute("endRow", detail.getEndRow());
			model.addAttribute("count", count);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("reco", reco);
			model.addAttribute("keywordMap", keywordMap);
		}
		return "/recommend/memberReco";
	}
	
	/*
	 * 취향분석이 필요한지 아닌지 판단하는 페이지
	 * 
	 * index_member : 회원번호
	 * memberAnal = 0	: 세션에 저장된 회원취향점수와 DB에서 불러온 회원취향점수가 같으면 분석을 진행하지 않는다.
	 * 						바로 /revel/recommend/member.rv 페이지로 이동한다.
	 * memberAnal = 1	: 세션에 저장된 회원취향점수가 없거나 세션에 저장된 값이 DB에서 불러온 회원취향점수와 다르면 분석을 진행한다.
	 * 						daoReco.clearValue(index_member): 임시테이블의 데이터값을 지운다.
	 * 						데이터분석 안내창을 띄운 후, /revel/recommend/member.rv 페이지로 이동한다.
	 * memberAnal = 2	: 회원이 평가한 작품이 없으면 임시테이블의 데이터값을 지우고 작품을 평가해달라는 안내창을 띄운후, 이전 페이지로 이동한다.
	 * 
	 * daoReco.isTempAnal(index_member) 	: temp_anal_index_member 임시테이블이 있는지 확인한다. 없으면 ORA-00942 에러가 발생한다.
	 * daoReco.createTempAnal(index_member)	: ORA-00942 에러가 발생하면 temp_anal_index_member 테이블을 생성한다.
	 * 
	 * daoReco.clearValue(index_member)		: temp_anal_index_member 임시테이블에 값이 있으면 데이터들을 지운다.
	 * 
	 * session.getAttribute("memberScore")	: 세션에 저장된 회원취향점수
	 * newScore								: DB에서 불러온 회원취향점수
	 */
	@RequestMapping("alert.rv")
	public String myLogonalert(HttpSession session, Model model) {
		int index_member = 0;
		int memberAnal = 0;

		if(session.getAttribute("index_member") != null) {
			index_member = (Integer) session.getAttribute("index_member");
			
			try {
				try {
					daoReco.isTempAnal(index_member);
				}catch(Exception s) {
					if(s.getMessage().contains("ORA-00942")) {
						daoReco.createTempAnal(index_member);
					}
				}
				
				List <MemKeyAnalDTO> newMemberKeyword = daoReco.getMemKeyAnal(index_member);
				List newScore = new ArrayList();
				for(MemKeyAnalDTO score : newMemberKeyword) {
					newScore.add(score.getCumul_score());
				}
				
				if(analImpl.totalCount(index_member) != 0) {
					if(session.getAttribute("memberScore") == null) {
						session.setAttribute("memberScore", newScore);
						daoReco.clearValue(index_member);
						memberAnal = 1;
					}else if(!newScore.containsAll((List) session.getAttribute("memberScore"))) {
						daoReco.clearValue(index_member);
						session.setAttribute("memberScore", newScore);
						memberAnal = 1;
					}
				}else {
					daoReco.clearValue(index_member);
					memberAnal = 2;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		model.addAttribute("memberAnal", memberAnal);
		return "/recommend/alert";
	}
}
