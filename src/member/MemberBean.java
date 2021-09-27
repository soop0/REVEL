package member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.ReviewDTO;
import member.MemberDTO;

@Controller
@RequestMapping("/mypage/")

public class MemberBean 
{	
	@Autowired
	private MemberDAOInter dao = null;
	@Autowired
	private MemberDTO member = null;
	@Autowired
	private SurveyDTO survey = null;
	@Autowired
	private LogonDAOInter daol = null;
	@Autowired
	private ReviewDTO review = null;
	
	// 회원정보 수정페이지에 DB에 저장된 회원의 정보를 불러온다.
	@RequestMapping("memberUpdate.rv")
	public String myLogonupdateForm(HttpSession session, Model model) throws Exception
	{		
		 int index_member=0;
		 if(session.getAttribute("index_member")!=null) 
		 {
			index_member = (Integer)session.getAttribute("index_member");
			member.setStatus((Integer)session.getAttribute("status"));	//여기까지 저장됨
			member.setIndex_member(index_member);
			
			member = dao.getMember(member);
			survey = dao.getSurvey(index_member);
			

			model.addAttribute("member", member);
			model.addAttribute("survey",survey);			
		}
		 else 
		 {	 return "/member/login";	}
		 
		return "/member/mypage/updateForm";
	}
	
	// 수정된 회원정보를 DB에 업데이트 한다.
	@RequestMapping("memberUpdatePro.rv")
	public String updatePro(MemberDTO member, SurveyDTO survey, String volume,Model model, HttpSession session) 
	{	return "/member/mypage/updatePro";	}
	
	@RequestMapping("volumeChange.rv")
	public String volumeChange(String volume,HttpSession session,Model model) {		
		int index_member=0;
		 if(session.getAttribute("index_member")!=null) {
			index_member = (Integer)session.getAttribute("index_member");	
		}
		 
		 try{
			 survey = dao.getSurvey(index_member);	
			 survey.setVolume(Integer.parseInt(volume));
			 dao.volumeChange(survey);
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/mypage/volumeChange";
	}
	
	
	//  버튼을 누르면 ->  index_member의 survey 테이블에 선호장르값이 변경됨 0(회색), 1(파란색) 
	//genre 이름 전달 , 전달받은 장르값이 숫자0일경우 1로변경, 1일경우 0으로 변경 , 색을 마지막에 변경해줌??
	@RequestMapping("genreChange.rv")
	public String genreChange(String genre,HttpSession session,Model model) {	
		

		int index_member=0;
		 if(session.getAttribute("index_member")!=null) {
			index_member = (Integer)session.getAttribute("index_member");	
		}
		 
		try{
			survey = dao.getSurvey(index_member);
			if(genre.equals("romance")){
				int i=(survey.getRomance()==1)?0:1;
				survey.setRomance(i);	
			}
			if(genre.equals("rofan")){
				int i=(survey.getRofan()==1)?0:1;
				survey.setRofan(i);	
			}
			if(genre.equals("fantagy")){
				int i=(survey.getFantagy()==1)?0:1;
				survey.setFantagy(i);	
			}
			if(genre.equals("mofan")){
				int i=(survey.getMofan()==1)?0:1;
				survey.setMofan(i);	
			}
			if(genre.equals("heroism")){
				int i=(survey.getHeroism()==1)?0:1;
				survey.setHeroism(i);	
			}		
			dao.genreChange(survey);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/member/mypage/genreChange";
	}
	
	
	@RequestMapping("nickChange.rv")
	public String myLogonnickChange() 
	{	return "/member/mypage/nickChange";	}
	
	
	@RequestMapping("nickCheck.rv")
	public String nickCheck(String nick, Model model) 
	{			
		//count 1 이면 중복, 0 사용가능
		int count=1;
		try {
			count = dao.nickCheck(nick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("count",count);			
		model.addAttribute("nick",nick);			
		return "/member/mypage/nickCheck";
	}
	
	@RequestMapping("nickChangePro.rv")
	public String nickChangePro(String nick,HttpSession session,Model model)
	{
		 int index_member=0;
		 if(session.getAttribute("index_member")!=null) {
			index_member = (Integer)session.getAttribute("index_member");	
			member.setIndex_member(index_member);
			member.setStatus((Integer)session.getAttribute("status"));
		}
		try {
			member = dao.getMember(member);
			member.setNick(nick);
			dao.nickChange(member);	
			session.setAttribute("nick", nick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("member",member);			
		return "/member/mypage/nickChangePro";
	}	
	
	@RequestMapping("memberDel.rv")
	public String myLogonmemberDel(HttpSession session) 
	{	
		if(session.getAttribute("index_member")==null)
	 	{	return "/member/login";	}
		
		return "/member/mypage/memberDel";	
	}
	
	@RequestMapping("memberDelPro.rv")
	public String memberDelPro(MemberDTO member, HttpSession session, Model model) throws Exception
	{
		//탈퇴사유 -> 선택된 값  텍스트로 변환시켜주기	
		switch(Integer.parseInt(member.getWithdrawal()))
		{
			case 1:
				member.setWithdrawal("웹소설에 흥미가 떨어져서");
				break;
			case 2:
				member.setWithdrawal("읽고있는 콘텐츠가 없어서");
				break;
			case 3:
				member.setWithdrawal("다른 서비스를 이용하려고");
				break;
			case 4:
				member.setWithdrawal("서비스 이용이 불편해서");
				break;
			case 5:
				member.setWithdrawal(member.getWithdrawal_etc());
				break;
		}
		
		dao.updateWM(member);
		
		// check = 회원탈퇴 성공시 1, 실패시 0 
		int check = 0;
		check = daol.deleteMember(member);
		
		if(check != 0) 
		{	session.invalidate();	}
		model.addAttribute("check", check);
		return "/member/mypage/memberDelPro";
	}
	
	// 나의 댓글 리스트
	// 내가 작성한 댓글 리스트를 조회하고, 각각의 댓글을 삭제할 수 있다.
	@RequestMapping("review.rv")
	public String myLogonmyReview(HttpServletRequest request, Model model) throws Exception
	{
		int count = 0;
		List myReviewList = null;
		
		// 세션 있는지 확인하여 회원이면 회원번호 리턴한다.
		HttpSession sessionCurrent = request.getSession();
		int index_member = 0;
		
		if(sessionCurrent.getAttribute("index_member") != null)
		{
			if(sessionCurrent.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)sessionCurrent.getAttribute("index_member");	}
		}
		else 
		{	 return "/member/login";	}
		
		// 회원일 때 내가 쓴 댓글 조회한다.
		if(index_member > 0)
		{
			// 내가 쓴 댓글이 있는지 확인하는 카운트
			count = dao.myReviewCount(index_member);
			if(count > 0)
			{	myReviewList = dao.myReview(index_member);	}
		}
		
		model.addAttribute("count", count);
		model.addAttribute("myReviewList", myReviewList);
		
		return "/member/mypage/myReview";
	}
	
	// 내가 작성한 목록 리스트(마이페이지)에서 해당 댓글 삭제하기
	@RequestMapping("reviewDel.rv")
	public String myLogonreviewDel(HttpServletRequest request) throws Exception
	{
		int index_review = Integer.parseInt(request.getParameter("reviewNum"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int index_member = 0;
		int count = 0;
		
		// 세션 확인하여 있으면 회원번호 리턴 없으면 0
		HttpSession sessionCurrent = request.getSession();
		if(sessionCurrent.getAttribute("index_member") != null)
		{
			if(sessionCurrent.getAttribute("index_member") instanceof Integer)
			{	
				index_member = (int)sessionCurrent.getAttribute("index_member");
				review.setIndex_member(index_member);
				review.setIndex_book(index_book);
				review.setIndex_review(index_review);
			}
		}
		
		// 회원일 때 선택한 내가 쓴 댓글이 있는지 확인한 후 삭제한다.
		if(index_member > 0)
		{
			count = dao.myReviewDelCount(review);
			if(count > 0)
			{	dao.myReviewDel(review);	}
		}
		
		return "redirect:/mypage/review.rv";
	}
	
	
}
