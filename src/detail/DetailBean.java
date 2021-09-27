package detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.AnalysisDAOInter;
import ranking.KeywordDAOInter;

/*
detail.DetailDTO => detail
detail.ReviewDTO => review
detail.Review_LikeDTO => review_like
detail.Read_index_memberDTO => read_member
detail.ReadStatusDTO => readStatus
detail.LinkClickDTO => linkClick
*/

@Controller
public class DetailBean 
{	
	@Autowired
	private DetailDAOInter detailImpl = null;
	
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	@Autowired
	private ReviewDTO review = null;
	
	@Autowired
	private RatingMemberDTO ratingMember = null;
	
	@Autowired
	private ReadStatusDTO readStatus = null;

	@Autowired
	private LinkClickDTO linkClick = null;
	
	//KeywordDAOImple 클래스 생성
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	@RequestMapping("detail.rv")
	public String detail(HttpServletRequest request, Model model, HttpSession session) throws Exception 
	{
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		DetailDTO allDetailBook = detailImpl.allDetail(index_book); 
		
		// 댓글 게시판 작성
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) 
		{	pageNum = "1";	}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		review.setStartRow(startRow);
		review.setEndRow(endRow);
		review.setIndex_book(index_book);
		
		// 댓글 조회
		List<ReviewDTO> rvList = null;
		
		count = detailImpl.reviewCount(review.getIndex_book());
		if(count > 0) 
		{
			rvList = detailImpl.reviewList(review); //원래는 index_book 받았지만, 파라미터가 reviewDTO로 변경되어 review로 
			allDetailBook.setReview_num(count);
			detailImpl.review_num(allDetailBook);
		}
		number = count - (currentPage-1)*pageSize;
		
		// 별점 조회
		int rating = 0;				// rating = 별점
		int ratingCount = 0;		// rating = 별점여부 확인
		
		//독서상태 조회
		int read = -1; 			//	read = 독서상태(읽고싶다-1, 읽고있다-2, 다읽었다-3, 관심없다-0)
		int readCount = 0;   	//	readCount = 독서상태 선택 여부 확인
				
		//링크 클릭 조회
		int naver = 0;		//	naver = 네이버 링크 클릭 수
		int kakao = 0;		//	kakao = 카카오 링크 클릭 수
		int naverCount = 0; 
		int kakaoCount = 0;
		
		// 세션이 있는지 확인하여 회원이면 해당 작품의 별점이 있는지 확인하여 있으면 별점을 추출한다.
		HttpSession sessionCurrent = request.getSession();				// 현재 있는 세션 호출
		int index_member = 0;
		
		// session에 index_member의 값이 있는지 확인
		// 회원이면 회원번호 리턴 => index_member에 대입, 없으면 null리턴
		if(sessionCurrent.getAttribute("index_member") != null)
		{	
			if(sessionCurrent.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)sessionCurrent.getAttribute("index_member");	}
		}
		
		if(index_member > 0)
		{
			ratingMember.setIndex_book(index_book);
			ratingMember.setIndex_member(index_member);
			readStatus.setIndex_book(index_book);
			readStatus.setIndex_member(index_member);
			linkClick.setIndex_book(index_book);
			linkClick.setIndex_member(index_member);
			
			ratingCount = detailImpl.ratingCount(ratingMember);
			if(ratingCount > 0)
			{	rating = detailImpl.ratingSelect(ratingMember);	}	
			
			readCount = detailImpl.rstatusCount(readStatus);
			if(readCount > 0)
			{	
				read = detailImpl.rstatusSelect(readStatus); 
				readStatus.setCheckCount(0);
			}
			else
			{	readStatus.setCheckCount(1);		}
		}
		else
		{	readStatus.setCheckCount(1); 	}
		
		//링크 클릭 - 비회원 = 0
		if(index_member == 0)
		{
			linkClick.setIndex_book(index_book);
			linkClick.setIndex_member(index_member);
		}
		
		// 해당 작품의 전체 평가인원 수와 평점평균 조회
		int ratingTotalNum = detailImpl.ratingTotalNum(index_book);
		float ratingAvg = 0;
		
		if(ratingTotalNum > 0)
		{	
			ratingAvg = detailImpl.ratingAvg(index_book);	
			allDetailBook.setRating_num(ratingTotalNum);
			allDetailBook.setRating_revel(ratingAvg);
			detailImpl.ratingAnl(allDetailBook);
		}
		
		//키워드 달기
		Map <String, List>keywordMap = new HashMap();
		List keywords = daoKey.getKeyword(index_book);
		
		//댓글 리스트 조회
		if(count > 0) 
		{
			rvList = detailImpl.reviewList(review); //원래는 index_book 받았지만, 파라미터가 reviewDTO로 변경되어 review로 
			allDetailBook.setReview_num(count);
			detailImpl.review_num(allDetailBook);
		}
		number = count - (currentPage-1)*pageSize;
				
		//동일 작가 작품 조회
		int sameWriterCount = 0;
		List sameWriter = null;
		sameWriterCount = detailImpl.sameWriterCount(index_book);
		if(sameWriterCount > 0)
		{	
			sameWriter = detailImpl.sameWriterList(index_book);	
			allDetailBook.setIndex_book(index_book);
			detailImpl.sameWriterCount(index_book);
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", review.getStartRow());
		request.setAttribute("endRow",review.getEndRow());
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rating", rating);
		request.setAttribute("rstatus", read);
		request.setAttribute("sameWriterCount", sameWriterCount);
		model.addAttribute("allDetailBook", allDetailBook); 
		model.addAttribute("rvList",rvList);
		model.addAttribute("keywords", keywords);
		model.addAttribute("sameWriter",sameWriter);
		model.addAttribute("readStatus",readStatus);

		return "/detail/detail";
	}
	
	// 댓글작성
	@RequestMapping(value = "/detail/reviewWrite.rv", method = RequestMethod.POST)
	public String reviewWrite(HttpServletRequest request) throws Exception
	{
		review.setIndex_book(Integer.parseInt(request.getParameter("index_book")));
		review.setIndex_member(Integer.parseInt(request.getParameter("index_member")));
		review.setContent(request.getParameter("content"));
		detailImpl.reviewWrite(review);
		return "redirect:/detail.rv?bookNum="+review.getIndex_book();
	}
	
	// 댓글 삭제
	@RequestMapping("/detail/reviewDelete.rv")
	public String reviewDelete(HttpServletRequest request) throws Exception
	{	
		int index_review = Integer.parseInt(request.getParameter("reviewNum"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		
		detailImpl.reviewDelete(index_review);
		return "redirect:/detail.rv?bookNum="+index_book;
	}
	
	/*
	 *  회원 서비스 중 해당 책의 평점을 부여하는 메서드
	 *  비회원이거나 탈퇴회원이 평점 서비스를 이용하지 못한다.
	 */
	@RequestMapping("/detail/rating.rv")
	public String rating(HttpServletRequest request, HttpSession session, Model model) throws Exception
	{
		// memberNum은 세션으로 불러오기
		int rating = Integer.parseInt(request.getParameter("rating"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int status = 0;
		int index_member = 0;
		int countRating = 0;
		
		// 회원상태인지 아닌지를 확인한다.
		if(session != null)
		{	
			if(session.getAttribute("status") instanceof Integer)
			{	status = (int)session.getAttribute("status");	}
			
			if(session.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)session.getAttribute("index_member");	}	
		}
		
		
		// 회원상태일 경우에만 해당 책의 평점을 부여한다.
		if(status == 11 || status == 12)
		{
			// 회원번호, 웹소설번호, 평점을 RatingMemberDTO에 저장한다.
			ratingMember.setIndex_book(index_book);
			ratingMember.setIndex_member(index_member);
			ratingMember.setRating(rating);
			
			// 평점 테이블에서 회원이 해당 책에 평점을 부여했는지 확인한다.
			// 없으면 0, 있으면 1
			countRating = detailImpl.ratingCount(ratingMember);
			
			// count 결과 1이면 해당 웹소설의 평점을 부여한적이 있으므로 새로 부여한 평점을 업데이트 시켜주고,
			// count 결과 0이면 해당 웹소설의 평점을 부여한 적이 없으므로 평점테이블에 넣어준다(insert).
			if(countRating > 0)
			{	
				detailImpl.ratingUpdate(ratingMember);	
				//누적점수 업데이트
				analImpl.updateMemberScore(ratingMember);
			}
			else
			{	
				detailImpl.ratingInsert(ratingMember);	
				//누적점수 업데이트
				analImpl.updateMemberScore(ratingMember);
			}
		}
		
		return "redirect:/detail.rv?bookNum="+index_book;
	}
	
	// 회원별 해당 책의 독서상태 선택하는 메서드
	@RequestMapping("/detail/rstatus.rv")
	public String rstatus(HttpServletRequest request, HttpSession session, Model model) throws Exception
	{
		/*
		 *  rstatus : 새로 등록할 독서상태
		 *  rstatusBefore : 이전에 등록되어 있는 독서상태
		 *  index_book : 등록할 독서상태의 책번호
		 *  checkCount : 독서상태 선택/해제 구분 (선택-1 , 해제-0)
		 *  status : 회원상태
		 *  index_member : 회원번호
		 *  countRstatus : 회원별 독서상태 테이블에 해당 책의 추가할 독서상태가 있는지 확인
		 */
		int rstatus = Integer.parseInt(request.getParameter("rstatus"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int checkCount = Integer.parseInt(request.getParameter("checkCount"));
		int status = 0;
		int index_member = 0;
		int countRstatus = 0;
			
		// 현재 세션이 있는지 판단
		HttpSession sessionCurrent = request.getSession();
			
		// 회원 상태 여부 확인
		if(sessionCurrent.getAttribute("status") != null)
		{
			if(session.getAttribute("status") instanceof Integer)
			{	status = (int)session.getAttribute("status");	}
		}
		
		//회원 번호 여부 확인
		if(sessionCurrent.getAttribute("index_member") != null)
		{
			if(session.getAttribute("index_member") instanceof Integer) 
			{	index_member = (int)session.getAttribute("index_member");	}
		}
			
		//회원만 독서상태 선택 가능 - 책번호, 회원번호, 독서상태 ReadStatusDTO 저장
		if(status == 11 || status == 12) 
		{
			readStatus.setIndex_book(index_book);
			readStatus.setIndex_member(index_member);
			readStatus.setRead(rstatus);
			
			/* 
			 *	rstatus를 detail 테이블에 독서상태 총합을 업데이트할 컬럼 이름으로 변경
			 *	관심없다(0)-'read_not' / 읽을거다(1)-'read_want' / 읽고있다(2)-'read_ing' / 다읽었다(3)-'read_end'
			 */
			switch(rstatus)
			{
				case 0 : readStatus.setReadAfter("read_not");
						 break;
				case 1 : readStatus.setReadAfter("read_want");
						 break;
				case 2 : readStatus.setReadAfter("read_ing");
						 break;
				case 3 : readStatus.setReadAfter("read_end");	
						 break;
			}
			
			countRstatus = detailImpl.rstatusCount(readStatus);
			// 독서상태 업데이트(회원별 독서상태 테이블, detail 테이블)
			if(countRstatus > 0) 
			{	
				if(checkCount == 1)
				{
					int rstatusBefore = detailImpl.rstatusSelect(readStatus);
				
					switch(rstatusBefore)
					{
						case 0 : readStatus.setReadBefore("read_not");
							 	 break;
						case 1 : readStatus.setReadBefore("read_want");
							 	 break;
						case 2 : readStatus.setReadBefore("read_ing");
							  	 break;
						case 3 : readStatus.setReadBefore("read_end");	
							 	 break;
					}
					
					detailImpl.rstatusUpdate(readStatus);	
					detailImpl.detailRstatusChange(readStatus);
				}
				else
				{
					detailImpl.rstatusDelete(readStatus);
					detailImpl.detailRstatusDelete(readStatus);
				}
			
			}
			else 
			{	
				detailImpl.rstatusInsert(readStatus);	
				detailImpl.detailRstatusAdd(readStatus);
			}
		}
		
		return "redirect:/detail.rv?bookNum="+index_book;		
	}
	
	// 링크 클릭 수 증가
	@RequestMapping("/detail/linkClick.rv")
	public String linkClick(HttpServletRequest request, HttpSession session, Model model) throws Exception
	{	
		/*
		 *  platform : 링크 플랫폼
		 *  index_book : 책번호
		 *  status : 회원 상태
		 *  index_member : 회원번호
		 *  platformClick : 플랫폼 클릭수
		 *  count : lick_click 테이블 데이터 유무 확인
		 */
		
		String platform = request.getParameter("platform");
		String url = request.getParameter("url");
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int status = 0;						
		int index_member = 0;
		int platformClick = 0;
		int count = 0;
		int result = 0;
		
		// 회원 상태 결정 코드(비회원-0 , 회원-11 or 12, 탈퇴-50, 관리자-100)
		HttpSession sessionCurrent = request.getSession();
		if(sessionCurrent.getAttribute("status") != null)
		{
			if(session.getAttribute("status") instanceof Integer)
			{	
				status = (int)session.getAttribute("status");	// 회원, 탈퇴, 관리자	
			}
		}
		
		// 회원 번호 검색 코드(비회원-0, 회원,탈퇴,관리자-회원고유번호)
		if(sessionCurrent.getAttribute("index_member") != null)
		{
			if(session.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)session.getAttribute("index_member");	}
		}
		
		// 관리자가 아니면 링크수 올린다.
		if(status != 100)
		{
			// 회원상태
			if(status == 11 || status == 12)
			{
				// link_click 테이블에 해당 소설의 각 플랫폼 카운트 수 증가
				linkClick.setIndex_book(index_book);
				linkClick.setIndex_member(index_member);
				linkClick.setPlatform(platform);
				
				// 사용자가 누른 플랫폼의 해당 소설이 link_click 테이블에 있는지 확인
				count = detailImpl.clickCount(linkClick);
				
				// count가 1이상이면 기존 데이터에 사용자가 누른 플랫폼 클릭수 증가
				// count가 0이면 해당 값들을 link_click 테이블에 추가
				if(count > 0)
				{
					detailImpl.platformUpdate(linkClick);
					result = 1;
				}
				else
				{
					if(linkClick.getPlatform().equals("naver"))
					{	linkClick.setPlatformCount(1);	}
					else	
					{	linkClick.setPlatformCount(1);	}
					detailImpl.clickInsert(linkClick);
					result = 1;
				}
			}
			// 비회원, 탈퇴회원
			else
			{
				// link_click 테이블에 해당 소설의 각 플랫폼 카운트 수 증가
				linkClick.setIndex_book(index_book);
				linkClick.setIndex_member(0);
				linkClick.setPlatform(platform);
				
				// 사용자가 누른 플랫폼의 해당 소설이 link_click 테이블에 있는지 확인
				count = detailImpl.clickCount(linkClick);
				
				// count가 1이상이면 기존 데이터에 사용자가 누른 플랫폼 클릭수 증가
				// count가 0이면 해당 값들을 link_click 테이블에 추가
				if(count > 0)
				{
					detailImpl.platformUpdate(linkClick);
					result = 1;
				}
				else
				{
					if(linkClick.getPlatform().equals("naver"))
					{	linkClick.setPlatformCount(1);	}
					else
					{	linkClick.setPlatformCount(1); 	}
					detailImpl.clickInsert(linkClick);
					result = 1;
				}			
			}
		}
		else
		{	result = 0;	}
	
		model.addAttribute("result", result);
		model.addAttribute("url", url);
		
		return "/detail/linkClick";
	}
	
	
}	