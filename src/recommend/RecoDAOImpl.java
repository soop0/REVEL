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
	
	// SurveyDTO ����
	@Autowired
	private SurveyDTO survey = null;
	
	// OutListDTO ����
	@Autowired
	private OutListDTO outList = null;
	
	//�α����� ȸ����ȣ(index_member)�� �־ ȸ������м����̺�(member_keyword_anal_ȸ����ȣ)�� ��ȸ�Ѵ�. 
	@Override
	public List <MemKeyAnalDTO> getMemKeyAnal(int index_member) throws Exception {
		return mybatis.selectList("getMemKeyAnal",index_member);
	}

	//��ǰ��Ű����м����̺�(keyword_anal)�� ��ȸ�Ѵ�.
	@Override
	public List <KeyAnalDTO> getKeyAnal() throws Exception {
		return mybatis.selectList("getKeyAnal");
	}

	//��õ�� ��ǰ��ȣ(index_book)����Ʈ�� �־ ��ǰ������ �޾ƿ´�.
	@Override
	public List<DetailDTO> getRecoBook(List recoList) throws Exception {
		return mybatis.selectList("getRecoBook", recoList);
	}
	
	//�м��� ����� ������ �ӽ����̺�(temp_anal_index_member)�� �����Ͱ� �ִ��� Ȯ���Ѵ�.
	@Override
	public int isTempAnal(int index_member) throws Exception{
		return mybatis.selectOne("isTempAnal", index_member);
	}
	
	//�м��� ����� ������ �ӽ����̺�(temp_anal_index_member)�� �����Ѵ�.
	@Override
	public void createTempAnal (int index_member)  throws Exception {
		mybatis.insert("createTempAnal", index_member);
	}
	
	//�м��� ����� �ӽ����̺�(temp_anal_index_member)�� insert�Ѵ�.
	@Override
	public void insertTempAnal(List tempAnalList) throws Exception {
		mybatis.insert("insertTempAnal", tempAnalList);
	}
	
	/* 
	 * �ӽ����̺��� ������ ������������ ��õ����Ʈ�� �޾ƿ´�.
	 * List <TempAnalDTO> recoTotalList = mybatis.selectList("getRecoBook", index_member)
	 * 		: �ڻ��ο����� �м������ ���� ������ ��õ���� ��ü����Ʈ�� �޾ƿ´�.
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
	 * ȸ��Ű���� ��������(����)�� ��ǰ�� Ű���� ����ġ�� ���Ѵ�.
	 * 
	 * List <MemKeyAnalDTO> member = daoReco.getMemKeyAnal(index_member) 
	 * 		�α����� ȸ���� Member_Key_Anal ���̺��� ���������� MemKeyAnalDTO member�� �����Ѵ�.
	 * 
	 * int [] member_index_keyword 	: R�� Vector�� �����ϱ� ���� index_keyword�� �迭�� ���� 
	 * double [] member_cumul_score	: R�� Vector�� �����ϱ� ���� cumul_score�� �迭�� ����  (ȸ�����⴩������)
	 * 
	 * List <KeyAnalDTO> KeyAnal = daoReco.getKeyAnal()
	 * 		Keyword_anal ���̺��� �� ��ǰ�� Ű���� ������ KeyAnalDTO keyAnal�� �����Ѵ�.
	 * 
	 * int [] index_keyword	: R�� Vector�� �����ϱ� ���� index_keyword�� �迭�� ����
	 * int [] index_book	: R�� Vector�� �����ϱ� ���� index_book�� �迭�� ����
	 * double [] weight		: R�� Vector�� �����ϱ� ���� weight�� �迭�� ����	(Ű���� ����ġ ��)
	 * 
	 * rConn.eval("library(lsa)") : �ڻ��ο������� �м��ϱ� ���� ���̺귯�� ȣ��
	 * 
	 * rCpnn.eval("cosine(ȸ����������, ��ǰŰ���尡��ġ)") : ��ǰ�� category�� �ڻ��� ������ �м� ����
	 * 
	 * category100	: ĳ����, index_keyword 100���� �ڻ��� ������ ��
	 * category200	: ������, index_keyword 200���� �ڻ��� ������ ��
	 * category300	: ����, index_keyword 300���� �ڻ��� ������ ��
	 * category400	: ���ڹ���, index_keyword 400���� �ڻ��� ������ ��
	 * 
	 * Map <String, double []> category : <ī�װ���, �ڻ��ο����� ��> 
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
	 * ����м� ��õ��Ͽ��� ������ ����Ʈ�� �����.
	 * survey = daoReco.getSurvey(index_member)	: �������翡�� ��ȣ�帣�� ��ȣ�з� ������ �����´�.
	 * ��ȣ�з� (����:1(300�� �ʰ�), ����:2(100 ~ 300), ����:3(100 �̸�), �ƹ��ų�:4)
	 * ��ȣ�帣 (�θǽ�:1, ����:2, ��Ÿ��:3, ����:4, ����:5) 
	 * 
	 * List outVolume							: ������ ��ȣ�з��� ���� ������ �з� ����� �����. (ex> ����(1)�� �����ϸ� 2,3 �� ����)
	 * if(volume == 4 || outVolume.size() == 3)	: �ƹ��ų�(4)�� �����߰ų�, �������翡�� ��ȣ�з��� �������� ���� ��� ����Ʈ�� 0�� ������ �Ѵ�.	
	 * 
	 * List outGenre							: ������ ��ȣ�帣�� ���� ������ �帣 ����� �����. (ex> �θǽ�(1), ��Ÿ��(3)�� �����ϸ� 2,4,5 �� ����)
	 * survey.getRomance() == 0					: ��������� �帣�� �������� ������ 0, �����ϸ� 1�̱� ������ 0�� ��쿡�� �帣�� ��ȣ�� ����Ʈ�� �߰��Ѵ�.
	 * if(outGenre.size() == 5 || outGenre.size() == 0) : ��� �帣�� �����ϰų�, �������翡�� ��ȣ�帣�� �������� ���� ��� ����Ʈ�� 0�� ������ �Ѵ�.
	 * 
	 * outList	: ������ ��ȣ�з��� ��ȣ�帣 ��ȣ�� outListDTO�� ��´�.
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
	
	//survey ���̺��� ȸ��(index_member)�� ������ �ҷ��´�.
	@Override
	public SurveyDTO getSurvey(int index_member) throws Exception{
		return mybatis.selectOne("getSurvey",index_member);
	}
	
	//TempAnal ���̺��� �����͵��� �����.
	@Override
	public void clearValue(int index_member) throws Exception{
		mybatis.insert("clearValue", index_member);
	}
	
	
}
