package genre;

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

/*
 *  <genreBean 클래스>
 *   	- myBatis를 이용하여 DB에서 genreSQL 조건에 따라 불러온 리스트 및 값을 view에 보여주기 위해 처리하는 클래스
 *  	- 장르별 DB 코드
 *          - 로맨스(romance)  : 1
 *          - 로판(rofan)     : 2
 *          - 판타지(fantasy)  : 3
 *          - 현판(mofan)     : 4
 *          - 무협(heroism)   : 5
 */

@Controller
@RequestMapping("/genre/")
public class genreBean {
	
	// genreDAOImpl 클래스 생성(다형성)
	@Autowired
	private GenreDAOInter genreImpl = null;
	
	// DetailDTO 생성
	@Autowired
	private DetailDTO detail = null;
	
	//KeywordDAOImple 클래스 생성
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	// 전체 장르 url 매핑시 모든 장르에 해당하는 전체 리스트를 view에 보여주는 메서드
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	
	@RequestMapping("total.rv")
	public String total(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List total = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = genreImpl.totalCount();
		if(count > 0)
		{	
			total = genreImpl.totalList(detail);	
			for(int i = 0; i < total.size(); i++) 
			{
				index_book = ((DetailDTO)total.get(i)).getIndex_book();
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
		model.addAttribute("total", total);
		model.addAttribute("keywordMap", keywordMap);

		
		return"/genre/total";	
	}	
	
	// 전체 장르 url 매핑시 로맨스에 해당하는 전체 리스트를 view에 보여주는 메서드
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	@RequestMapping("romance.rv")
	public String romance(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setGenre(1);
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List romance = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = genreImpl.kindCount(detail.getGenre());
		if(count > 0)
		{	
			romance = genreImpl.kindList(detail);	
		
			for(int i = 0; i < romance.size(); i++) 
			{
				index_book = ((DetailDTO)romance.get(i)).getIndex_book();
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
		model.addAttribute("romance", romance);
		model.addAttribute("keywordMap", keywordMap);

		
		return"/genre/romance";	
	}	
	
	// 전체 장르 url 매핑시 로판에 해당하는 전체 리스트를 view에 보여주는 메서드
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	@RequestMapping("rofan.rv")
	public String rofan(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setGenre(2);
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List rofan = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = genreImpl.kindCount(detail.getGenre());
		if(count > 0)
		{	
			rofan = genreImpl.kindList(detail);	
			for(int i = 0; i < rofan.size(); i++) 
			{
				index_book = ((DetailDTO)rofan.get(i)).getIndex_book();
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
		model.addAttribute("rofan", rofan);
		model.addAttribute("keywordMap", keywordMap);

		return"/genre/rofan";			
	}
	
	// 전체 장르 url 매핑시 판타지에 해당하는 전체 리스트를 view에 보여주는 메서드
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	@RequestMapping("fantasy.rv")
	public String fantasy(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setGenre(3);
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List fantasy = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = genreImpl.kindCount(detail.getGenre());
		if(count > 0)
		{	
			fantasy = genreImpl.kindList(detail);
			for(int i = 0; i < fantasy.size(); i++) 
			{
				index_book = ((DetailDTO)fantasy.get(i)).getIndex_book();
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
		model.addAttribute("fantasy", fantasy);
		model.addAttribute("keywordMap", keywordMap);

		return"/genre/fantasy";	
	}	
	
	// 전체 장르 url 매핑시 현판에 해당하는 전체 리스트를 view에 보여주는 메서드
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	@RequestMapping("mofan.rv")
	public String mofan(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setGenre(4);
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List mofan = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = genreImpl.kindCount(detail.getGenre());
		if(count > 0)
		{	
			mofan = genreImpl.kindList(detail);	
			for(int i = 0; i < mofan.size(); i++)
			{
				index_book = ((DetailDTO)mofan.get(i)).getIndex_book();
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
		model.addAttribute("mofan", mofan);
		model.addAttribute("keywordMap", keywordMap);

		return"/genre/mofan";			
	}	
	
	// 전체 장르 url 매핑시 무협에 해당하는 전체 리스트를 view에 보여주는 메서드
	/*
	 * ***키워드 표시***
	 * Map <String, List> keywordMap	: 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * int index_book									: 책번호, recoList에서 책번호를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	@RequestMapping("heroism.rv")
	public String heroism(HttpServletRequest request, Model model) throws Exception 
	{	
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		detail.setGenre(5);
		detail.setStartRow(startRow);
		detail.setEndRow(endRow);
		
		int count = 0;
		List heroism = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		count = genreImpl.kindCount(detail.getGenre());
		if(count > 0)
		{	
			heroism = genreImpl.kindList(detail);	
			for(int i = 0; i < heroism.size(); i++) 
			{
				index_book = ((DetailDTO)heroism.get(i)).getIndex_book();
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
		model.addAttribute("heroism", heroism);
		model.addAttribute("keywordMap", keywordMap);
		
		return"/genre/heroism";	
	}
}