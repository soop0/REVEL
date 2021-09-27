package ranking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.DetailDTO;


@Controller
@RequestMapping("/ranking/")
public class RankingBean {
	
	// DetailDTO ����
	@Autowired
	private DetailDTO detail = null;
	
	//RankingDAOImple Ŭ���� ����
	@Autowired
	private RankingDAOInter daoRank = null;
	
	//KeywordDAOImple Ŭ���� ����
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	@Autowired
	private RankingDTO ranking = null;
	
	/*
	 * Ŭ������ ���� ���� �α���ǰ TOP20�� ǥ���Ѵ�. (���� �Ϸ絿��)
	 * 
	 * List rankList 					: ������ �Ⱓ������ ��ŷ����Ʈ
	 * List daily 						: �ϰ���ŷ����Ʈ�� ���� DetailDTO ����Ʈ 
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * 
	 * ***�ϰ���ŷ����Ʈ �����***
	 * ranking = daoRank.setDaily()		: �Ⱓ�� �����Ѵ�.
	 * 
	 * rankList = daoRank.getRankList(ranking)	: ������ �Ⱓ������ ��ŷ����Ʈ�� �޾ƿ´�.
	 * count = rankList.size()	: �ϰ���ŷ����Ʈ�� ����
	 * 							  count�� endRow�� ���ؼ� count�� ������� endRow = count�� �����Ѵ�.
	 * 
	 * tempDaily = daoRank.getBook(rankList)	: ��ŷ����Ʈ�� �̿��ؼ� ��ǰ�� DetailDTO(��������) ����Ʈ�� �����.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20�� ��ǰ�� ���̵��� �ϴ� �۾��̴�.
	 *			daily.add(tempDaily.get(index - 1))
	 *		}
	 *
	 * ***Ű���� ǥ��***
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.			
	 */
	@RequestMapping("daily.rv")
	public String daily(HttpSession session, Model model, String pageNum) {
		int pageSize = 20;
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		int count = 0;
		
		List rankList = new ArrayList();
		List daily = new ArrayList();
		Map <String, List>keywordMap = new HashMap();
		
		try {
			ranking = daoRank.setDaily();
			
			rankList = daoRank.getRankList(ranking);
			
			count = rankList.size();
			if(endRow > count) {
				endRow = count;
				detail.setEndRow(endRow);
			}
			
			List <DetailDTO> tempDaily = null;
			if(count > 0) {
				tempDaily = daoRank.getBook(rankList);
				for(int index = startRow; index <= endRow; index++) {
					daily.add(tempDaily.get(index - 1));
				}
			}
			
			int index_book = 0;
			for(int i = 0; i < daily.size(); i++) {
				index_book = ((DetailDTO) daily.get(i)).getIndex_book();
				List keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", detail.getStartRow());
		model.addAttribute("endRow", detail.getEndRow());
		model.addAttribute("count", count);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("daily", daily);
		model.addAttribute("keywordMap", keywordMap);
		
		return "/ranking/daily";
	}
	
	/*
	 * Ŭ������ ���� �ְ� �α���ǰ TOP20�� ǥ���Ѵ�. (������ �Ͽ��� ~ ������ �����)
	 * 
	 * List rankList 					: ������ �Ⱓ������ ��ŷ����Ʈ
	 * List weekly 						: �ְ���ŷ����Ʈ�� ���� DetailDTO ����Ʈ 
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * 
	 * ***�ְ���ŷ����Ʈ �����***
	 * ranking = daoRank.setWeekly()		: �Ⱓ�� �����Ѵ�.
	 * 
	 * rankList = daoRank.getRankList(ranking)	: ������ �Ⱓ������ ��ŷ����Ʈ�� �޾ƿ´�.
	 * count = rankList.size()	: �ְ���ŷ����Ʈ�� ����
	 * 							  count�� endRow�� ���ؼ� count�� ������� endRow = count�� �����Ѵ�.
	 * 
	 * tempWeekly = daoRank.getBook(rankList)	: ��ŷ����Ʈ�� �̿��ؼ� ��ǰ�� DetailDTO(��������) ����Ʈ�� �����.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20�� ��ǰ�� ���̵��� �ϴ� �۾��̴�.
	 *			weekly.add(tempWeekly.get(index - 1))
	 *		}
	 *
	 * ***Ű���� ǥ��***
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.			
	 */	
	@RequestMapping("weekly.rv")
	public String week(HttpSession session, Model model, String pageNum) {
		int pageSize = 20;
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		int count = 0;
		
		List rankList = new ArrayList();
		List weekly = new ArrayList();
		Map <String, List>keywordMap = new HashMap();
		
		try {
			ranking = daoRank.setWeekly();
			
			rankList = daoRank.getRankList(ranking);
			
			count = rankList.size();
			if(endRow > count) {
				endRow = count;
				detail.setEndRow(endRow);
			}
			
			List <DetailDTO> tempWeekly = null;
			if(count > 0) {
				tempWeekly = daoRank.getBook(rankList);
				for(int index = startRow; index <= endRow; index++) {
					weekly.add(tempWeekly.get(index - 1));
				}
			}
			
			int index_book = 0;
			for(int i = 0; i < weekly.size(); i++) {
				index_book = ((DetailDTO) weekly.get(i)).getIndex_book();
				List keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", detail.getStartRow());
		model.addAttribute("endRow", detail.getEndRow());
		model.addAttribute("count", count);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("weekly", weekly);
		model.addAttribute("keywordMap", keywordMap);
		
		return "/ranking/weekly";
	}
	
	/*
	 * Ŭ������ ���� ���� �α���ǰ TOP20�� ǥ���Ѵ�. (���� 1�� ~ ���� ��������)
	 * 
	 * List rankList 					: ������ �Ⱓ������ ��ŷ����Ʈ
	 * List monthly 					: ������ŷ����Ʈ�� ���� DetailDTO ����Ʈ 
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * 
	 * ***������ŷ����Ʈ �����***
	 * ranking = daoRank.setMonthly()		: �Ⱓ�� �����Ѵ�.
	 * 
	 * rankList = daoRank.getRankList(ranking)	: ������ �Ⱓ������ ��ŷ����Ʈ�� �޾ƿ´�.
	 * count = rankList.size()	: ������ŷ����Ʈ�� ����
	 * 							  count�� endRow�� ���ؼ� count�� ������� endRow = count�� �����Ѵ�.
	 * 
	 * tempMonthly = daoRank.getBook(rankList)	: ��ŷ����Ʈ�� �̿��ؼ� ��ǰ�� DetailDTO(��������) ����Ʈ�� �����.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20�� ��ǰ�� ���̵��� �ϴ� �۾��̴�.
	 *			monthly.add(tempMonthly.get(index - 1))
	 *		}
	 *
	 * ***Ű���� ǥ��***
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.			
	 */		
	@RequestMapping("monthly.rv")
	public String monthly(HttpSession session, Model model, String pageNum) {
		int pageSize = 20;
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		int count = 0;
		
		List rankList = new ArrayList();
		List monthly = new ArrayList();
		Map <String, List>keywordMap = new HashMap();
		
		try {
			ranking = daoRank.setMonthly();
			
			rankList = daoRank.getRankList(ranking);
			
			count = rankList.size();
			if(endRow > count) {
				endRow = count;
				detail.setEndRow(endRow);
			}
			
			List <DetailDTO> tempMonthly = null;
			if(count > 0) {
				tempMonthly = daoRank.getBook(rankList);
				for(int index = startRow; index <= endRow; index++) {
					monthly.add(tempMonthly.get(index - 1));
				}
			}
			
			int index_book = 0;
			for(int i = 0; i < monthly.size(); i++) {
				index_book = ((DetailDTO) monthly.get(i)).getIndex_book();
				List keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", detail.getStartRow());
		model.addAttribute("endRow", detail.getEndRow());
		model.addAttribute("count", count);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("monthly", monthly);
		model.addAttribute("keywordMap", keywordMap);
		
		return "/ranking/monthly";
	}

	/*
	 * Ŭ������ �α���ǰ TOP20�� ǥ���Ѵ�. (��ü�Ⱓ)
	 * 
	 * List rankList 					: ��ü�Ⱓ������ ��ŷ����Ʈ
	 * List total	 					: ��ü��ŷ����Ʈ�� ���� DetailDTO ����Ʈ 
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * 
	 * ***��ü��ŷ����Ʈ �����***
	 * rankList = daoRank.getRankList()		: ��ü�Ⱓ���� ��ŷ����Ʈ�� �޾ƿ´�.
	 * count = rankList.size()	: ��ü��ŷ����Ʈ�� ����
	 * 							  count�� endRow�� ���ؼ� count�� ������� endRow = count�� �����Ѵ�.
	 * 
	 * tempTotal = daoRank.getBook(rankList)	: ��ŷ����Ʈ�� �̿��ؼ� ��ǰ�� DetailDTO(��������) ����Ʈ�� �����.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20�� ��ǰ�� ���̵��� �ϴ� �۾��̴�.
	 *			total.add(tempTotal.get(index - 1))
	 *		}
	 *
	 * ***Ű���� ǥ��***
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.			
	 */		
	@RequestMapping("total.rv")
	public String total(HttpSession session, Model model, String pageNum) {
		int pageSize = 20;
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		int count = 0;
		
		List rankList = new ArrayList();
		List total = new ArrayList();
		Map <String, List>keywordMap = new HashMap();
		
		try {
			rankList = daoRank.getRankList();
			
			count = rankList.size();
			if(endRow > count) {
				endRow = count;
				detail.setEndRow(endRow);
			}
			
			List <DetailDTO> tempTotal = null;
			if(count > 0) {
				tempTotal = daoRank.getBook(rankList);
				for(int index = startRow; index <= endRow; index++) {
					total.add(tempTotal.get(index - 1));
				}
			}
			
			int index_book = 0;
			for(int i = 0; i < total.size(); i++) {
				index_book = ((DetailDTO) total.get(i)).getIndex_book();
				List keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", detail.getStartRow());
		model.addAttribute("endRow", detail.getEndRow());
		model.addAttribute("count", count);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total", total);
		model.addAttribute("keywordMap", keywordMap);
		
		return "ranking/total";
	}
	
	
}
