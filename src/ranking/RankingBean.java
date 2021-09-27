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
	
	// DetailDTO 생성
	@Autowired
	private DetailDTO detail = null;
	
	//RankingDAOImple 클래스 생성
	@Autowired
	private RankingDAOInter daoRank = null;
	
	//KeywordDAOImple 클래스 생성
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	@Autowired
	private RankingDTO ranking = null;
	
	/*
	 * 클릭수에 따라 일일 인기작품 TOP20을 표시한다. (어제 하루동안)
	 * 
	 * List rankList 					: 지정한 기간동안의 랭킹리스트
	 * List daily 						: 일간랭킹리스트에 따른 DetailDTO 리스트 
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * 
	 * ***일간랭킹리스트 만들기***
	 * ranking = daoRank.setDaily()		: 기간을 설정한다.
	 * 
	 * rankList = daoRank.getRankList(ranking)	: 지정한 기간동안의 랭킹리스트를 받아온다.
	 * count = rankList.size()	: 일간랭킹리스트의 개수
	 * 							  count와 endRow를 비교해서 count가 작은경우 endRow = count로 설정한다.
	 * 
	 * tempDaily = daoRank.getBook(rankList)	: 랭킹리스트를 이용해서 작품의 DetailDTO(세부정보) 리스트를 만든다.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20개 작품이 보이도록 하는 작업이다.
	 *			daily.add(tempDaily.get(index - 1))
	 *		}
	 *
	 * ***키워드 표시***
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.			
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
	 * 클릭수에 따라 주간 인기작품 TOP20을 표시한다. (지난주 일요일 ~ 지난주 토요일)
	 * 
	 * List rankList 					: 지정한 기간동안의 랭킹리스트
	 * List weekly 						: 주간랭킹리스트에 따른 DetailDTO 리스트 
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * 
	 * ***주간랭킹리스트 만들기***
	 * ranking = daoRank.setWeekly()		: 기간을 설정한다.
	 * 
	 * rankList = daoRank.getRankList(ranking)	: 지정한 기간동안의 랭킹리스트를 받아온다.
	 * count = rankList.size()	: 주간랭킹리스트의 개수
	 * 							  count와 endRow를 비교해서 count가 작은경우 endRow = count로 설정한다.
	 * 
	 * tempWeekly = daoRank.getBook(rankList)	: 랭킹리스트를 이용해서 작품의 DetailDTO(세부정보) 리스트를 만든다.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20개 작품이 보이도록 하는 작업이다.
	 *			weekly.add(tempWeekly.get(index - 1))
	 *		}
	 *
	 * ***키워드 표시***
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.			
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
	 * 클릭수에 따라 월간 인기작품 TOP20을 표시한다. (전달 1일 ~ 전달 마지막일)
	 * 
	 * List rankList 					: 지정한 기간동안의 랭킹리스트
	 * List monthly 					: 월간랭킹리스트에 따른 DetailDTO 리스트 
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * 
	 * ***월간랭킹리스트 만들기***
	 * ranking = daoRank.setMonthly()		: 기간을 설정한다.
	 * 
	 * rankList = daoRank.getRankList(ranking)	: 지정한 기간동안의 랭킹리스트를 받아온다.
	 * count = rankList.size()	: 월간랭킹리스트의 개수
	 * 							  count와 endRow를 비교해서 count가 작은경우 endRow = count로 설정한다.
	 * 
	 * tempMonthly = daoRank.getBook(rankList)	: 랭킹리스트를 이용해서 작품의 DetailDTO(세부정보) 리스트를 만든다.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20개 작품이 보이도록 하는 작업이다.
	 *			monthly.add(tempMonthly.get(index - 1))
	 *		}
	 *
	 * ***키워드 표시***
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.			
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
	 * 클릭수에 인기작품 TOP20을 표시한다. (전체기간)
	 * 
	 * List rankList 					: 전체기간동안의 랭킹리스트
	 * List total	 					: 전체랭킹리스트에 따른 DetailDTO 리스트 
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * 
	 * ***전체랭킹리스트 만들기***
	 * rankList = daoRank.getRankList()		: 전체기간동안 랭킹리스트를 받아온다.
	 * count = rankList.size()	: 전체랭킹리스트의 개수
	 * 							  count와 endRow를 비교해서 count가 작은경우 endRow = count로 설정한다.
	 * 
	 * tempTotal = daoRank.getBook(rankList)	: 랭킹리스트를 이용해서 작품의 DetailDTO(세부정보) 리스트를 만든다.
	 *		for(int index = startRow; index <= endRow; index++) {	 : 20개 작품이 보이도록 하는 작업이다.
	 *			total.add(tempTotal.get(index - 1))
	 *		}
	 *
	 * ***키워드 표시***
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.			
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
