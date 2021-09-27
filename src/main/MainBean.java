package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.DetailDTO;
import detail.RatingMemberDTO;
import member.AnalysisDAOInter;
import member.MemberAnalDTO;
import member.MemberReadDTO;
import ranking.KeywordDAOInter;
import ranking.RankingDAOInter;
import ranking.RankingDTO;

/*
 * MainBean Ŭ����
 * ����ȭ�� ���� �� ����������, �˻�ȭ�� �̵��� ó���Ѵ�.
 */

@Controller
public class MainBean 
{
	@Autowired
	private MainDAOInter mainImpl = null;			// ������
	
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	@Autowired
	private SearchDTO search = null;
	
	@Autowired
	private RatingMemberDTO ratingMember = null;
	
	@Autowired
	private MemberReadDTO read = null;
	
	//RankingDAOImple Ŭ���� ����
	@Autowired
	private RankingDAOInter daoRank = null;
		
	// RankingDTO Ŭ���� ����
	@Autowired
	private RankingDTO ranking = null;
		
	// DetailDTO ����
	@Autowired
	private DetailDTO detail = null;
		
	//KeywordDAOImple Ŭ���� ����
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	/*
	 * ����ȭ���� �����Ѵ�. 
	 * �����̵�� ���� 5��ǰ, ������ ���� ������ 6��ǰ, �ϰ���ŷ 5��ǰ, �ֽŴ�� �� 5��ǰ
	 * �α����� ��� �а��ִ� 3��ǰ, �����Ŵ� 3��ǰ�� ǥ�õȴ�.
	 *  
	 * int index_member		: �α����� ȸ�� ��ȣ
	 * int countReading		: �а��ִ� ��� ��
	 * int countReadWant	: �����Ŵ� ��� ��	
	 * int countRating		: ������ ��ǰ ��� ��
	 * int countRank		: �ϰ� ��ŷ ��ǰ ��� ��
	 * int countComment		: �ֽ� ��� ��ǰ ��� ��
	 * 
	 * List readingList		: �а��ִ� ��ǰ ���� ����Ʈ
	 * List readWantList	: �����Ŵ� ��ǰ ���� ����Ʈ
	 * List commentList		: �ֽ� ��� ��ǰ ���� ����Ʈ
	 * Map <String, List> keywordMap : ��ǰ�� ���� Ű���� �� <��ǰ��ȣ, Ű���帮��Ʈ>
	 * 
	 * *** �����̵� �� ���� 5��ǰ ***
	 * List mainBook = mainImpl.mainBook()	: �����̵� ��Ÿ�� 5��ǰ�� �������� �����Ͽ� ��ǰ����(DetailDTO)����Ʈ�� �޾ƿ´�.
	 * 
	 * session.getAttribute("index_member") != null	: �α��� ���� Ȯ�� 
	 * 		�α��� �� ��� �а��ִ� 3��ǰ, �����Ŵ� 3��ǰ ǥ��
	 * 
	 * *** �а��ִ�/�����Ŵ� 3��ǰ ***
	 * MemberReadDTO read : ȸ����ȣ�� ��������(�а��ִ�:2/ �����Ŵ�:1)�� set�޼��带 ���� �����Ѵ�.
	 * List readList = mainImpl.getReadList(read)	: �� �������¿� ���� ��ǰ 3���� ����� �޾ƿ´�.
	 * readingList = mainImpl.getReadBook(readList) : ��ǰ��Ͽ� ���� ��ǰ����(DetailDTO)����Ʈ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ�� �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
	 * 
	 * *** ������ 6��ǰ ***
	 * List ratingList = mainImpl.getRatingBook()	: ���� �� ��ǰ 6���� ��ǰ����(DetailDTO)����Ʈ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ�� �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
	 * 
	 * *** ���Ϸ�ŷ 5��ǰ ***
	 * ranking = daoRank.setDaily()	: ��ŷ�Ⱓ���� (�ϰ� : �����Ϸ�)
	 * List rankList = daoRank.getRankList(ranking)	: �ϰ���ŷ����Ʈ
	 * List daily = daoRank.getBook(rankList)		: ��ŷ����Ʈ�� �ִ� å��ȣ DetailDTO ����Ʈ
	 * 
	 * *** �ֽ� ��� �� 5��ǰ ***
	 * List commentIndexBook = mainImpl.getCommentList()	: �ֽ� ��ۼ� ��ǰ 5���� ����� �޾ƿ´�.
	 * commentList = mainImpl.getReadBook(commentIndexBook)	: ��ǰ��Ͽ� ���� ��ǰ����(DetailDTO)����Ʈ�� �޾ƿ´�.
	 * List keywords = daoKey.getKeyword(index_book)	: å��ȣ�� ���� Ű���帮��Ʈ�� �����.
	 * keywordMap.put(""+index_book, keywords)			: <å��ȣ, Ű���帮��Ʈ> ������ Map���� �����.
	 */
	
	// revel ����������
	@RequestMapping("main.rv")
	public String main(Model model, HttpSession session) throws Exception 
	{
		int index_member = 0;
		int countReading = 0;
		int countReadWant = 0;
		int countRating = 0;
		int countRank = 0;
		int countComment = 0;
		
		List readingList = null;
		List readWantList = null;
		List commentList = null;
		
		List mainBook = mainImpl.mainBook();

		
		int index_book = 0;
		Map <String, List>keywordMap = new HashMap();
		
		if(session.getAttribute("index_member") != null) {  
			index_member = (Integer)session.getAttribute("index_member"); 
			read.setIndex_member(index_member);
			
			read.setRead(2);
			List readList = mainImpl.getReadList(read);
			if(readList.size() != 0) {
				countReading = readList.size();
			}
			if(countReading > 0) {
				readingList = mainImpl.getReadBook(readList);
				for(int i = 0; i < readingList.size(); i++) {
					index_book = ((DetailDTO) readingList.get(i)).getIndex_book();
					List keywords = daoKey.getKeyword(index_book);
					keywordMap.put(""+index_book, keywords);
				}
			}
			
			read.setRead(1);
			readList = mainImpl.getReadList(read);
			if(readList.size() != 0) {
				countReadWant = readList.size();
			}
			if(countReadWant > 0) {
				readWantList = mainImpl.getReadBook(readList);
				for(int i = 0; i < readWantList.size(); i++) {
					index_book = ((DetailDTO) readWantList.get(i)).getIndex_book();
					List keywords = daoKey.getKeyword(index_book);
					keywordMap.put(""+index_book, keywords);
				}
			}
		}
		
		List ratingList = mainImpl.getRatingBook();
		if(ratingList.size() != 0) {
			countRating = ratingList.size();
			for(int i = 0; i < countRating; i++) {
				index_book = ((DetailDTO) ratingList.get(i)).getIndex_book();
				List keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
		}
		
		ranking = daoRank.setDaily();
		List rankList = daoRank.getRankList(ranking);
		
		countRank = rankList.size();
		if(countRank > 0) {
			List daily = daoRank.getBook(rankList);
			model.addAttribute("daily", daily);
		}
		
		List commentIndexBook = mainImpl.getCommentList();

		if(commentIndexBook.size() != 0) {
			countComment = commentIndexBook.size(); 
		}
		if(countComment > 0) {
			commentList = mainImpl.getReadBook(commentIndexBook);
			for(int i = 0; i < commentList.size(); i++) {
				index_book = ((DetailDTO) commentList.get(i)).getIndex_book();
				List keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
		}
		
		model.addAttribute("mainBook", mainBook);		
		model.addAttribute("countReading", countReading);
		model.addAttribute("countReadWant", countReadWant);
		model.addAttribute("countRating", countRating);
		model.addAttribute("readingList", readingList);
		model.addAttribute("readWantList", readWantList);
		model.addAttribute("ratingList", ratingList);
		model.addAttribute("countRank", countRank);
		model.addAttribute("countComment", countComment);
		model.addAttribute("rankList", rankList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("keywordMap", keywordMap);

	
		return "/main/main";
	}
	
	// �˻���� ������
	@RequestMapping("search.rv")
	public String search(HttpServletRequest request, Model model) throws Exception
	{
		/*
		 *  �˻���� Ȯ��
		 *  count : �˻���� ��
		 *  searchList : �˻���� ����Ʈ
		 */
		
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		search.setStartRow(startRow);
		search.setEndRow(endRow);
		
		int count = 0;
		List searchList = null;
		Map <String, List>keywordMap = new HashMap();
		int index_book = 0;
		List keywords = null;
		
		if(request.getParameter("searchWord") != null && request.getParameter("theme") != null)
		{	
			search.setSearchWord(request.getParameter("searchWord"));
			search.setTheme(request.getParameter("theme"));
			count = mainImpl.searchCount(search);
		}
		else
		{	count = 0;	}
		
		
		
		if(count > 0)
		{	
			searchList = mainImpl.searchList(search);	
			
			for(int i = 0; i < searchList.size(); i++) 
			{
				index_book = ((DetailDTO)searchList.get(i)).getIndex_book();
				keywords = daoKey.getKeyword(index_book);
				keywordMap.put(""+index_book, keywords);
			}
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", search.getStartRow());
		request.setAttribute("endRow", search.getEndRow());
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		model.addAttribute("searchList", searchList);
		model.addAttribute("keywordMap", keywordMap);
		
		
		return "/main/search";
	}
	
	//�������� count, ��ȣ�帣,Ű����,���,���� ��Ʈ�����ֱ�
	@RequestMapping("mypage.rv")
	public String myLogonmypage(HttpSession session, Model model) throws Exception
	{	
		int index_member=0;
		int count = 0;
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
		
		count = analImpl.totalCount(index_member);
		
		//�����ȣ�� ��������(0:���ɾ���,1:�����Ŵ�,2:�а��ִ�,3:���о���) ����
		read.setIndex_member(index_member);
		read.setRead(0);
		int readCount_0 = analImpl.readCount(read);
		read.setRead(1);
		int readCount_1 = analImpl.readCount(read);
		read.setRead(2);
		int readCount_2 = analImpl.readCount(read);
		read.setRead(3);
		int readCount_3 = analImpl.readCount(read);

		//���������� �ʿ��� ��		
		ratingMember.setIndex_member(index_member);
		ratingMember.setRating(1);
		int ratingCount_1 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(2);
		int ratingCount_2 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(3);
		int ratingCount_3 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(4);
		int ratingCount_4 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(5);
		int ratingCount_5 = analImpl.ratingCount(ratingMember);	
		
		/*��ȣ�帣
		 *  int romance,rofan,fantasy,mofan,heroism : �帣�� count ����
		 * int r_romance,r_rofan,r_fantasy,r_mofan,r_heroism : �帣�� ��� count����
		 */
		read.setIndex_member(index_member);
		read.setGenre(1);
		int romance=analImpl.genreCount(read);
		int r_romance=analImpl.reviewCount(read);
		read.setGenre(2);
		int rofan = analImpl.genreCount(read);
		int r_rofan = analImpl.reviewCount(read);
		read.setGenre(3);
		int fantasy = analImpl.genreCount(read);
		int r_fantasy = analImpl.reviewCount(read);
		read.setGenre(4);
		int mofan = analImpl.genreCount(read);
		int r_mofan = analImpl.reviewCount(read);
		read.setGenre(5);
		int heroism = analImpl.genreCount(read);
		int r_heroism = analImpl.reviewCount(read);
		
		
		//��ȣŰ���� top20  list �迭�� keywords(Ű����), cumul_score(��������) �����
		List<MemberAnalDTO> anls_top20 =null;
		anls_top20 = analImpl.getMKeyword(index_member);
		String [] keywords = new String[anls_top20.size()];
		double [] cumul_score = new double[anls_top20.size()];
		for(int i=0; i < anls_top20.size(); i++) 
		{
			keywords[i] = anls_top20.get(i).getKeywords();
			cumul_score[i] = anls_top20.get(i).getCumul_score();
		}
		
		model.addAttribute("readCount_0", readCount_0);
		model.addAttribute("readCount_1", readCount_1);
		model.addAttribute("readCount_2", readCount_2);
		model.addAttribute("readCount_3", readCount_3);
		model.addAttribute("ratingCount_1",ratingCount_1); 
		model.addAttribute("ratingCount_2",ratingCount_2); 
		model.addAttribute("ratingCount_3",ratingCount_3); 
		model.addAttribute("ratingCount_4",ratingCount_4); 
		model.addAttribute("ratingCount_5",ratingCount_5); 
		model.addAttribute("romance", romance);
		model.addAttribute("rofan", rofan);
		model.addAttribute("fantasy", fantasy);
		model.addAttribute("mofan", mofan);
		model.addAttribute("heroism", heroism);
		model.addAttribute("r_romance", r_romance);
		model.addAttribute("r_rofan", r_rofan);
		model.addAttribute("r_fantasy", r_fantasy);
		model.addAttribute("r_mofan", r_mofan);
		model.addAttribute("r_heroism", r_heroism);
		model.addAttribute("keywords", keywords);
		model.addAttribute("cumul_score",cumul_score);	
		model.addAttribute("count", count);
				
		return "/main/mypage";	
	}
	
}
