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
 * MainBean 클래스
 * 메인화면 구성 및 마이페이지, 검색화면 이동을 처리한다.
 */

@Controller
public class MainBean 
{
	@Autowired
	private MainDAOInter mainImpl = null;			// 다형성
	
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	@Autowired
	private SearchDTO search = null;
	
	@Autowired
	private RatingMemberDTO ratingMember = null;
	
	@Autowired
	private MemberReadDTO read = null;
	
	//RankingDAOImple 클래스 생성
	@Autowired
	private RankingDAOInter daoRank = null;
		
	// RankingDTO 클래스 생성
	@Autowired
	private RankingDTO ranking = null;
		
	// DetailDTO 생성
	@Autowired
	private DetailDTO detail = null;
		
	//KeywordDAOImple 클래스 생성
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	/*
	 * 메인화면을 구성한다. 
	 * 슬라이드쇼 랜덤 5작품, 평점이 높은 순서로 6작품, 일간랭킹 5작품, 최신댓글 순 5작품
	 * 로그인한 경우 읽고있다 3작품, 읽을거다 3작품이 표시된다.
	 *  
	 * int index_member		: 로그인한 회원 번호
	 * int countReading		: 읽고있다 목록 수
	 * int countReadWant	: 읽을거다 목록 수	
	 * int countRating		: 평점순 작품 목록 수
	 * int countRank		: 일간 랭킹 작품 목록 수
	 * int countComment		: 최신 댓글 작품 목록 수
	 * 
	 * List readingList		: 읽고있다 작품 정보 리스트
	 * List readWantList	: 읽을거다 작품 정보 리스트
	 * List commentList		: 최신 댓글 작품 정보 리스트
	 * Map <String, List> keywordMap : 작품에 따른 키워드 맵 <작품번호, 키워드리스트>
	 * 
	 * *** 슬라이드 쇼 랜덤 5작품 ***
	 * List mainBook = mainImpl.mainBook()	: 슬라이드쇼에 나타낼 5작품을 랜덤으로 선택하여 작품정보(DetailDTO)리스트를 받아온다.
	 * 
	 * session.getAttribute("index_member") != null	: 로그인 여부 확인 
	 * 		로그인 한 경우 읽고있다 3작품, 읽을거다 3작품 표시
	 * 
	 * *** 읽고있다/읽을거다 3작품 ***
	 * MemberReadDTO read : 회원번호와 독서상태(읽고있다:2/ 읽을거다:1)을 set메서드를 통해 저장한다.
	 * List readList = mainImpl.getReadList(read)	: 각 독서상태에 따른 작품 3개의 목록을 받아온다.
	 * readingList = mainImpl.getReadBook(readList) : 작품목록에 따라 정품정보(DetailDTO)리스트를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트를 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 * 
	 * *** 평점순 6작품 ***
	 * List ratingList = mainImpl.getRatingBook()	: 평점 순 작품 6개의 작품정보(DetailDTO)리스트를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트를 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 * 
	 * *** 일일랭킹 5작품 ***
	 * ranking = daoRank.setDaily()	: 랭킹기간설정 (일간 : 전날하루)
	 * List rankList = daoRank.getRankList(ranking)	: 일간랭킹리스트
	 * List daily = daoRank.getBook(rankList)		: 랭킹리스트에 있는 책번호 DetailDTO 리스트
	 * 
	 * *** 최신 댓글 순 5작품 ***
	 * List commentIndexBook = mainImpl.getCommentList()	: 최신 댓글순 작품 5개의 목록을 받아온다.
	 * commentList = mainImpl.getReadBook(commentIndexBook)	: 작품목록에 따라 정품정보(DetailDTO)리스트를 받아온다.
	 * List keywords = daoKey.getKeyword(index_book)	: 책번호에 따라 키워드리스트를 만든다.
	 * keywordMap.put(""+index_book, keywords)			: <책번호, 키워드리스트> 형식의 Map으로 만든다.
	 */
	
	// revel 메인페이지
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
	
	// 검색결과 페이지
	@RequestMapping("search.rv")
	public String search(HttpServletRequest request, Model model) throws Exception
	{
		/*
		 *  검색결과 확인
		 *  count : 검색결과 수
		 *  searchList : 검색결과 리스트
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
	
	//독서상태 count, 선호장르,키워드,댓글,평점 차트보여주기
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
		
		//멤버번호와 독서상태(0:관심없다,1:읽을거다,2:읽고있다,3:다읽었다) 설정
		read.setIndex_member(index_member);
		read.setRead(0);
		int readCount_0 = analImpl.readCount(read);
		read.setRead(1);
		int readCount_1 = analImpl.readCount(read);
		read.setRead(2);
		int readCount_2 = analImpl.readCount(read);
		read.setRead(3);
		int readCount_3 = analImpl.readCount(read);

		//별점분포도 필요한 값		
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
		
		/*선호장르
		 *  int romance,rofan,fantasy,mofan,heroism : 장르별 count 개수
		 * int r_romance,r_rofan,r_fantasy,r_mofan,r_heroism : 장르별 댓글 count개수
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
		
		
		//선호키워드 top20  list 배열로 keywords(키워드), cumul_score(누적점수) 만들기
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
