package member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.DetailDAOInter;
import detail.DetailDTO;
import detail.RatingMemberDTO;
import detail.ReadStatusDTO;

@Controller
@RequestMapping("/mypage/")
public class MylistBean {
	
	@Autowired
	private RatingMemberDTO ratingMember = null;
	
	@Autowired
	private MemberReadDTO read = null;
	
	@Autowired
	private MemberAnalDTO anls = null;
	
	@Autowired
	private ReadStatusDTO readStatus = null;
	
	@Autowired
	private DetailDTO detail = null;
	
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	@Autowired
	private DetailDAOInter detailImpl = null;
	
	
	/*회원 선호 장르 분석 차트 평가작품&다읽었다 기준 장르별(1로맨스, 2로판, 3판타지, 4현판, 5무협) 
	* int romance,rofan,fantasy,mofan,heroism : 장르별 count 개수
	* int r_romance,r_rofan,r_fantasy,r_mofan,r_heroism : 장르별 댓글 count개수
	*/
	@RequestMapping("myGenreAnls.rv")
	public String myLogonmyAnalysisGenre(HttpSession session,Model model) throws Exception
	{	
		int index_member=0;
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
		
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
		
		return "/member/analysis/genreChart";
	}
	
	
	/* anlysis 선호키워드 분석
	 * anls_top20 : 회원 선호 키워드 상위20개 결과
	 * anls_100 : 회원 선호 키워드 캐릭터(카테고리) 상위 6개 결과 -> anls_1(키워드), anls_c1(누적점수)
	 * anls_200 : 회원 선호 키워드 분위기(카테고리) 상위 6개 결과 -> anls_2(키워드), anls_c2(누적점수)
	 * anls_300 : 회원 선호 키워드 소재(카테고리) 상위 6개 결과 -> anls_3(키워드), anls_c3(누적점수)
	 * anls_400 : 회원 선호 키워드 독자반응(카테고리) 상위 3개 결과 -> anls_4(키워드), anls_c4(누적점수)
	 * 키워드 결과를 바탕으로 r과 연결해서 wordcloud 사용하기 보류
	 * count 평가작품 개수 가져오기 
	 */	
	@RequestMapping("myKeywordAnls.rv")
	public String myLogonmyAnalysisKeyword(HttpSession session,Model model) throws Exception
	{	
		int count =0;
		int index_member=0;
		List<MemberAnalDTO> anls_top20 =null;
		List <MemberAnalDTO> anls_100 = null;
		List <MemberAnalDTO> anls_200 = null;
		List <MemberAnalDTO> anls_300 = null;
		List <MemberAnalDTO> anls_400 = null;
		
		
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
		
		count = analImpl.totalCount(index_member);

		anls_top20 = analImpl.getMKeyword(index_member);
		anls_100 = analImpl.getCKeyword_1(index_member);
		anls_200 = analImpl.getCKeyword_2(index_member);
		anls_300 = analImpl.getCKeyword_3(index_member);
		anls_400 = analImpl.getCKeyword_4(index_member);
		
		
		// anls_top20 list 배열로 만들기
		String [] keywords = new String[anls_top20.size()];
		double [] cumul_score = new double[anls_top20.size()];
		for(int i=0; i < anls_top20.size(); i++) 
		{
			keywords[i] = anls_top20.get(i).getKeywords();
			cumul_score[i] = anls_top20.get(i).getCumul_score();
			//System.out.println(keywords[i] +" & "+cumul_score[i]);
		}

		//list 배열로 만들어서 키워드와 누적점수를 분리
		String [] anls_1 = new String[6];
		double [] anls_c1 = new double[6];
		String [] anls_2 = new String[6];
		double [] anls_c2 = new double[6];
		String [] anls_3 = new String[6];
		double [] anls_c3 = new double[6];

		for(int i=0; i < 6; i++) 
		{
			anls_1[i] = anls_100.get(i).getKeywords();
			anls_c1[i] = anls_100.get(i).getCumul_score();
			anls_2[i] = anls_200.get(i).getKeywords();
			anls_c2[i] = anls_200.get(i).getCumul_score();
			anls_3[i] = anls_300.get(i).getKeywords();
			anls_c3[i] = anls_300.get(i).getCumul_score();
		}
		
		String [] anls_4 = new String[3];
		double [] anls_c4 = new double[3];
		for(int i=0; i < 3; i++) 
		{
			anls_4[i] = anls_400.get(i).getKeywords();
			anls_c4[i] = anls_400.get(i).getCumul_score();
		}
	
		//r 연결 top20선호키워드 wordcloud 만들기 	
		RConnection rConn = new RConnection();
		rConn.eval("library(wordcloud2)");
		rConn.eval("library(htmltools)");
		rConn.assign("keywords", keywords);
		rConn.assign("cumul_score", cumul_score);
		rConn.eval("anls_top20 <- data.frame(keywords=keywords, cumul_score=cumul_score)");	
		rConn.eval("top20_chart <- wordcloud2(anls_top20, size=0.4, color='random-light')");
		rConn.eval("top20_tag <- renderTags(top20_chart)");
		String top20_chart = rConn.eval("top20_tag$html").asString();
		rConn.close();
		
		model.addAttribute("anls_1", anls_1); 
		model.addAttribute("anls_2", anls_2); 
		model.addAttribute("anls_3", anls_3); 
		model.addAttribute("anls_4", anls_4); 
		model.addAttribute("anls_c1", anls_c1); 
		model.addAttribute("anls_c2", anls_c2); 
		model.addAttribute("anls_c3", anls_c3); 
		model.addAttribute("anls_c4", anls_c4); 
		model.addAttribute("keywords", keywords);
		model.addAttribute("cumul_score",cumul_score);
		model.addAttribute("count", count);
		model.addAttribute("top20_chart",top20_chart); 

		return "/member/analysis/keywordChart";
	}
	
	//anlysis 별점그래프
	@RequestMapping("myRatingAnls.rv")
	public String myLogonmyAnalysisRating(HttpSession session,Model model) throws Exception
	{	
		int index_member=0;		
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
			
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
		int count =analImpl.totalCount(index_member);
			
		model.addAttribute("count",count); 
		model.addAttribute("ratingCount_1",ratingCount_1); 
		model.addAttribute("ratingCount_2",ratingCount_2); 
		model.addAttribute("ratingCount_3",ratingCount_3); 
		model.addAttribute("ratingCount_4",ratingCount_4); 
		model.addAttribute("ratingCount_5",ratingCount_5); 
		return "/member/analysis/ratingChart";
	}
	
	
	//mylist 나의 작품 평가목록
	@RequestMapping("myRating.rv")
	public String myLogonmyListRating(HttpSession session,Model model) throws Exception
	{	
		int index_member=0;
		int count =0;
		int index_book=0;
		List ratingDetail =null;
		 
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}

		count = analImpl.totalCount(index_member);
		if(count>0) 
		//작품 평가목록+제목,작가 가져오기
		{  ratingDetail = analImpl.getRatingDetail(index_member); }
		  
		model.addAttribute("ratingDetail",ratingDetail); 
		model.addAttribute("count",count); 
		return "/member/mylist/myRating";
	}
	
	//내가 평가한 작품 삭제하기
	@RequestMapping("ratingDel.rv")
	public String myLogonmyListRatingDel(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=Integer.parseInt(request.getParameter("memberNum"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));		
		
		//ratingMember에 전달받은 index_member와 index_book 설정하기
		ratingMember.setIndex_member(index_member);
		ratingMember.setIndex_book(index_book);
		//평점 삭제 후 누적점수 업데이트
		analImpl.deleteRating(ratingMember);
		analImpl.updateMemberScore(ratingMember);
		
		// 해당 작품의 전체 평가인원 수와 평점평균 조회
		int ratingTotalNum = detailImpl.ratingTotalNum(index_book);
		float ratingAvg = 0;
		if(ratingTotalNum > 0)
		{	
			ratingAvg = detailImpl.ratingAvg(index_book);	
			detail.setRating_num(ratingTotalNum);
			detail.setRating_revel(ratingAvg);
			detail.setIndex_book(index_book);
			detailImpl.ratingAnl(detail);
		}
		else
		{
			detail.setRating_num(0);
			detail.setRating_revel(0);
			detail.setIndex_book(index_book);
			detailImpl.ratingAnl(detail);
		}

		return "/member/mylist/ratingDel";
	}
	
	
	//독서상태: not 관심없다(0) 
	@RequestMapping("myReadnot.rv")
	public String myLogonmyListRead(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=0;
		int count =0;
		List readList =null;

		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
		 
		//멤버번호와 독서상태 설정
		read.setRead(0);
		read.setIndex_member(index_member);
		count = analImpl.readCount(read);
		 
		if(count>0) 
		{ readList = analImpl.getReadList(read); }
		
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myReadnot";
	}
	
	//독서상태 : myReadwant 읽을거다(1)
	@RequestMapping("myReadwant.rv")
	public String myLogonmyListReadwant(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		 int index_member=0;
		 int count =0;
		 List readList =null;

		 if(session.getAttribute("index_member")!=null) 
		 {  index_member = (Integer)session.getAttribute("index_member"); }
		 else 
		 {	return "/member/login";	}
		 
		 //멤버번호와 독서상태 설정
		 read.setRead(1);
		 read.setIndex_member(index_member);
		 count = analImpl.readCount(read);
		 
		 if(count>0) 
		 { 	readList = analImpl.getReadList(read); }
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myReadwant";
	}
	
	//독서상태 : myReading 읽고있다(2)
	@RequestMapping("myReading.rv")
	public String myLogonmyListReading(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		 int index_member=0;
		 int count =0;
		 List readList =null;

		 if(session.getAttribute("index_member")!=null) 
		 {  index_member = (Integer)session.getAttribute("index_member"); }
		 else 
		 {	return "/member/login";	}
		 
		 //멤버번호와 독서상태 설정
		 read.setRead(2);
		 read.setIndex_member(index_member);
		 count = analImpl.readCount(read);
		 
		 if(count>0) 
		 { 	readList = analImpl.getReadList(read); }
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myReading";
	}
	
	//독서상태 :  myRead 다읽었다(3)
	@RequestMapping("myRead.rv")
	public String myLogonmyListRead(HttpSession session,Model model) throws Exception
	{
		 int index_member=0;
		 int count =0;
		 List readList =null;

		 if(session.getAttribute("index_member")!=null) 
		 {  index_member = (Integer)session.getAttribute("index_member"); }
		 else 
		 {	return "/member/login";	} 
		 
		 //멤버번호와 독서상태 설정
		 read.setRead(3);
		 read.setIndex_member(index_member);
		 count = analImpl.readCount(read);
		 
		 if(count>0) 
		 { 	readList = analImpl.getReadList(read); }
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myRead";
	}
	
	//나의 독서상태 변경하기
	@RequestMapping("readChange.rv")
	public String myLogonmyListReadChange(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=Integer.parseInt(request.getParameter("index_member"));
		int index_rstatus = Integer.parseInt(request.getParameter("index_rstatus"));		
		int readS = Integer.parseInt(request.getParameter("read"));
		int index_book = Integer.parseInt(request.getParameter("index_book"));
		int countRstatus = 0;			// countRstatus : 회원별 독서상태 테이블에 해당 책의 추가할 독서상태가 있는지 확인
		
		//read에 전달받은 index_member와 index_rstatus, read 설정하기
		read.setIndex_member(index_member);
		read.setIndex_rstatus(index_rstatus);
		read.setRead(readS);
		read.setIndex_book(index_book);
		readStatus.setIndex_book(index_book);
		readStatus.setIndex_member(index_member);
		readStatus.setRead(readS);
		
		/* 
		 *	rstatus를 detail 테이블에 독서상태 총합을 업데이트할 컬럼 이름으로 변경
		 *	관심없다(0)-'read_not' / 읽을거다(1)-'read_want' / 읽고있다(2)-'read_ing' / 다읽었다(3)-'read_end'
		 */
		switch(readS)
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
			
			//독서상태변경 
			analImpl.updateRead(read);
			detailImpl.detailRstatusChange(readStatus);
		}
		
		return "/member/mylist/readChange";
	}
	
	
	//나의 독서상태 삭제하기
	@RequestMapping("readDel.rv")
	public String myLogonmyListReadDel(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=Integer.parseInt(request.getParameter("memberNum"));
		int index_rstatus = Integer.parseInt(request.getParameter("readNum"));		
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int countRstatus = 0; 				// 회원별 독서상태 테이블에 해당 책의 추가할 독서상태가 있는지 확인
		
		//read에 전달받은 index_member와 index_rstatus 설정하기
		read.setIndex_member(index_member);
		read.setIndex_rstatus(index_rstatus);
		readStatus.setIndex_book(index_book);
		readStatus.setIndex_member(index_member);
		readStatus.setRead(detailImpl.rstatusSelect(readStatus));
		
		/* 
		 *	rstatus를 detail 테이블에 독서상태 총합을 업데이트할 컬럼 이름으로 변경
		 *	관심없다(0)-'read_not' / 읽을거다(1)-'read_want' / 읽고있다(2)-'read_ing' / 다읽었다(3)-'read_end'
		 */
		switch(readStatus.getRead())
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
			//독서 상태 삭제하기
			analImpl.deleteRead(read);
			detailImpl.detailRstatusDelete(readStatus);
		}

		return "/member/mylist/readDel";
	}
	
}
