package recommend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.DetailDTO;
import ranking.KeywordDAOInter;

@Controller
@RequestMapping("/platform/")
public class platformBean {

	@Autowired
	private PlatformDAOInter platformImpl = null;
	
	@Autowired
	private DetailDTO detail = null;
	
	//KeywordDAOImple 클래스 생성
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	@RequestMapping("naverSeries.rv")
	public String naverSeries(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		//detail.setLink_naver();
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List naverSeries = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = platformImpl.naverCount(detail.getLink_naver());
		if(count > 0)
		{	
			naverSeries = platformImpl.naverList(detail);
			for(int i = 0; i < naverSeries.size(); i++) 
			{
				index_book = ((DetailDTO)naverSeries.get(i)).getIndex_book();
				keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
			
		}

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", detail.getStartRow());
		request.setAttribute("endRow", detail.getEndRow());
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		model.addAttribute("naverSeries", naverSeries);
		model.addAttribute("keywordMap", keywordMap);

		return"/recommend/naverSeries";	
	}	
	
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	@RequestMapping("kakaoPage.rv")
	public String kakaoPage(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		//detail.setLink_kakao();
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List kakaoPage = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = platformImpl.kakaoCount(detail.getLink_kakao());
		if(count > 0)
		{	
			kakaoPage = platformImpl.kakaoList(detail);
			
			for(int i = 0; i < kakaoPage.size(); i++)
			{
				index_book = ((DetailDTO)kakaoPage.get(i)).getIndex_book();
				keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
			
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", detail.getStartRow());
		request.setAttribute("endRow", detail.getEndRow());
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		model.addAttribute("kakaoPage", kakaoPage);
		model.addAttribute("keywordMap", keywordMap);
		
		return"/recommend/kakaoPage";	
	}	
}
