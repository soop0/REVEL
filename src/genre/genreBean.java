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
 *  <genreBean Ŭ����>
 *   	- myBatis�� �̿��Ͽ� DB���� genreSQL ���ǿ� ���� �ҷ��� ����Ʈ �� ���� view�� �����ֱ� ���� ó���ϴ� Ŭ����
 *  	- �帣�� DB �ڵ�
 *          - �θǽ�(romance)  : 1
 *          - ����(rofan)     : 2
 *          - ��Ÿ��(fantasy)  : 3
 *          - ����(mofan)     : 4
 *          - ����(heroism)   : 5
 */

@Controller
@RequestMapping("/genre/")
public class genreBean {
	
	// genreDAOImpl Ŭ���� ����(������)
	@Autowired
	private GenreDAOInter genreImpl = null;
	
	// DetailDTO ����
	@Autowired
	private DetailDTO detail = null;
	
	//KeywordDAOImple Ŭ���� ����
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	// ��ü �帣 url ���ν� ��� �帣�� �ش��ϴ� ��ü ����Ʈ�� view�� �����ִ� �޼���
	/*
	 * ***Ű���� ǥ��***
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
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
	
	// ��ü �帣 url ���ν� �θǽ��� �ش��ϴ� ��ü ����Ʈ�� view�� �����ִ� �޼���
	/*
	 * ***Ű���� ǥ��***
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
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
	
	// ��ü �帣 url ���ν� ���ǿ� �ش��ϴ� ��ü ����Ʈ�� view�� �����ִ� �޼���
	/*
	 * ***Ű���� ǥ��***
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
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
	
	// ��ü �帣 url ���ν� ��Ÿ���� �ش��ϴ� ��ü ����Ʈ�� view�� �����ִ� �޼���
	/*
	 * ***Ű���� ǥ��***
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
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
	
	// ��ü �帣 url ���ν� ���ǿ� �ش��ϴ� ��ü ����Ʈ�� view�� �����ִ� �޼���
	/*
	 * ***Ű���� ǥ��***
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
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
	
	// ��ü �帣 url ���ν� ������ �ش��ϴ� ��ü ����Ʈ�� view�� �����ִ� �޼���
	/*
	 * ***Ű���� ǥ��***
	 * Map <String, List> keywordMap	: ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * int index_book									: å��ȣ, recoList���� å��ȣ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
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