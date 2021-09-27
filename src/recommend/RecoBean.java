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
 * ȸ���� ���⿡ ���� ��ǰ�� ��õ�� �ش�. 
 * R�̳� cmdâ���� Rserve�� ��������� �Ѵ�. 
 * */

@Controller
@RequestMapping("/recommend/")
public class RecoBean {
	
	//RecoDAOImpl Ŭ���� ����
	@Autowired
	private RecoDAOInter daoReco = null;
	
	//KeywordDAOImple Ŭ���� ����
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	// DetailDTO ����
	@Autowired
	private DetailDTO detail = null;
	
	// SurveyDTO ����
	@Autowired
	private SurveyDTO survey = null;
	
	// AnalysisDAOInter ����
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	/*
	 * ������ 0.8�̻�(����) /�ѹ��� 15��ǰ / �ִ� 30��ǰ�� ��õ���ش�.
	 * �α��� Ȯ��(���� �ִ��� Ȯ��)��, �ִ� ��쿡 ����м��� �����Ѵ�.
	 * 
	 * List recoList 					: ��������м� ���� ��õ����Ʈ
	 * List reco 						: ��õ����Ʈ�� ���� DetailDTO ����Ʈ 
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * 
	 * ***��������� ��ǰŰ���尡��ġ �ڻ��ο����� �м�***
	 * analImpl.totalCount(index_member) != 0 	: ȸ���� ���� ��ǰ�� �ִ��� Ȯ���Ѵ�. 
	 * daoReco.isTempAnal(index_member) == 0	: temp_anal_index_member �ӽ����̺� �����Ͱ� �ִ��� Ȯ���Ѵ�.
	 * 											  ���� ��ǰ�� �ְ�, �ӽ����̺� ����� �����Ͱ� ������ ����м��� �����Ѵ�.
	 * 
	 * Map category = daoReco.setKeyAnalValue(index_member) : ī�װ��� �ڻ��� ������ �м��� �Ѵ�.
	 * 
	 * category100	: ĳ����, index_keyword 100���� �ڻ��� ������ ��, ��������
	 * category200	: ������, index_keyword 200���� �ڻ��� ������ ��, ��������
	 * category300	: ����, index_keyword 300���� �ڻ��� ������ ��, ��������
	 * category400	: ���ڹ���, index_keyword 400���� �ڻ��� ������ ��, ��������
	 * 
	 * daoReco.insertTempAnal(tempAnalList)		: temp_anal_index_member �ӽ����̺� �м������ �����Ѵ�.
	 * 
	 * ***��õ����Ʈ �����***
	 * List <DetailDTO> getOutList = daoReco.getOutList(index_member)	: ���ܸ��
	 * 		read ���̺��� ���ɾ���(0), ���о���(3), rating ���̺��� �ش�ȸ��(index_member)�� ���� �� �ű� ��ǰ��ȣ(index_book)���� �ҷ��´�.
	 * recoList.remove((Object) list.getIndex_book()) 	: ��õ��Ͽ��� ���ܸ�� ��ǰ��ȣ���� �����Ѵ�.
	 * 
	 * count = recoList.size()	: ��õ����Ʈ�� ����, �ִ� 30������ ǥ���Ѵ�.
	 * 							  count�� endRow�� ���ؼ� count�� ������� endRow = count - 1�� �����Ѵ�.
	 * 		
	 * tempReco = daoReco.getRecoList(recoList)		: ��õ����Ʈ�� �̿��ؼ� ��ǰ�� DetailDTO(��������) ����Ʈ�� �����.
	 *		for(int index = startRow - 1; index <= endRow; index++) {	: ���������� 15�� ��ǰ�� ���̵��� �ϴ� �۾��̴�.
	 *			reco.add(tempReco.get(index));
	 *		}
	 * 
	 * ***Ű���� ǥ��***
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
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
	 * ����м��� �ʿ����� �ƴ��� �Ǵ��ϴ� ������
	 * 
	 * index_member : ȸ����ȣ
	 * memberAnal = 0	: ���ǿ� ����� ȸ������������ DB���� �ҷ��� ȸ������������ ������ �м��� �������� �ʴ´�.
	 * 						�ٷ� /revel/recommend/member.rv �������� �̵��Ѵ�.
	 * memberAnal = 1	: ���ǿ� ����� ȸ������������ ���ų� ���ǿ� ����� ���� DB���� �ҷ��� ȸ������������ �ٸ��� �м��� �����Ѵ�.
	 * 						daoReco.clearValue(index_member): �ӽ����̺��� �����Ͱ��� �����.
	 * 						�����ͺм� �ȳ�â�� ��� ��, /revel/recommend/member.rv �������� �̵��Ѵ�.
	 * memberAnal = 2	: ȸ���� ���� ��ǰ�� ������ �ӽ����̺��� �����Ͱ��� ����� ��ǰ�� ���ش޶�� �ȳ�â�� �����, ���� �������� �̵��Ѵ�.
	 * 
	 * daoReco.isTempAnal(index_member) 	: temp_anal_index_member �ӽ����̺��� �ִ��� Ȯ���Ѵ�. ������ ORA-00942 ������ �߻��Ѵ�.
	 * daoReco.createTempAnal(index_member)	: ORA-00942 ������ �߻��ϸ� temp_anal_index_member ���̺��� �����Ѵ�.
	 * 
	 * daoReco.clearValue(index_member)		: temp_anal_index_member �ӽ����̺� ���� ������ �����͵��� �����.
	 * 
	 * session.getAttribute("memberScore")	: ���ǿ� ����� ȸ����������
	 * newScore								: DB���� �ҷ��� ȸ����������
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
