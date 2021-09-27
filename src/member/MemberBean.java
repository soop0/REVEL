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
	
	// ȸ������ ������������ DB�� ����� ȸ���� ������ �ҷ��´�.
	@RequestMapping("memberUpdate.rv")
	public String myLogonupdateForm(HttpSession session, Model model) throws Exception
	{		
		 int index_member=0;
		 if(session.getAttribute("index_member")!=null) 
		 {
			index_member = (Integer)session.getAttribute("index_member");
			member.setStatus((Integer)session.getAttribute("status"));	//������� �����
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
	
	// ������ ȸ�������� DB�� ������Ʈ �Ѵ�.
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
	
	
	//  ��ư�� ������ ->  index_member�� survey ���̺� ��ȣ�帣���� ����� 0(ȸ��), 1(�Ķ���) 
	//genre �̸� ���� , ���޹��� �帣���� ����0�ϰ�� 1�κ���, 1�ϰ�� 0���� ���� , ���� �������� ��������??
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
		//count 1 �̸� �ߺ�, 0 ��밡��
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
		//Ż����� -> ���õ� ��  �ؽ�Ʈ�� ��ȯ�����ֱ�	
		switch(Integer.parseInt(member.getWithdrawal()))
		{
			case 1:
				member.setWithdrawal("���Ҽ��� ��̰� ��������");
				break;
			case 2:
				member.setWithdrawal("�а��ִ� �������� ���");
				break;
			case 3:
				member.setWithdrawal("�ٸ� ���񽺸� �̿��Ϸ���");
				break;
			case 4:
				member.setWithdrawal("���� �̿��� �����ؼ�");
				break;
			case 5:
				member.setWithdrawal(member.getWithdrawal_etc());
				break;
		}
		
		dao.updateWM(member);
		
		// check = ȸ��Ż�� ������ 1, ���н� 0 
		int check = 0;
		check = daol.deleteMember(member);
		
		if(check != 0) 
		{	session.invalidate();	}
		model.addAttribute("check", check);
		return "/member/mypage/memberDelPro";
	}
	
	// ���� ��� ����Ʈ
	// ���� �ۼ��� ��� ����Ʈ�� ��ȸ�ϰ�, ������ ����� ������ �� �ִ�.
	@RequestMapping("review.rv")
	public String myLogonmyReview(HttpServletRequest request, Model model) throws Exception
	{
		int count = 0;
		List myReviewList = null;
		
		// ���� �ִ��� Ȯ���Ͽ� ȸ���̸� ȸ����ȣ �����Ѵ�.
		HttpSession sessionCurrent = request.getSession();
		int index_member = 0;
		
		if(sessionCurrent.getAttribute("index_member") != null)
		{
			if(sessionCurrent.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)sessionCurrent.getAttribute("index_member");	}
		}
		else 
		{	 return "/member/login";	}
		
		// ȸ���� �� ���� �� ��� ��ȸ�Ѵ�.
		if(index_member > 0)
		{
			// ���� �� ����� �ִ��� Ȯ���ϴ� ī��Ʈ
			count = dao.myReviewCount(index_member);
			if(count > 0)
			{	myReviewList = dao.myReview(index_member);	}
		}
		
		model.addAttribute("count", count);
		model.addAttribute("myReviewList", myReviewList);
		
		return "/member/mypage/myReview";
	}
	
	// ���� �ۼ��� ��� ����Ʈ(����������)���� �ش� ��� �����ϱ�
	@RequestMapping("reviewDel.rv")
	public String myLogonreviewDel(HttpServletRequest request) throws Exception
	{
		int index_review = Integer.parseInt(request.getParameter("reviewNum"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int index_member = 0;
		int count = 0;
		
		// ���� Ȯ���Ͽ� ������ ȸ����ȣ ���� ������ 0
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
		
		// ȸ���� �� ������ ���� �� ����� �ִ��� Ȯ���� �� �����Ѵ�.
		if(index_member > 0)
		{
			count = dao.myReviewDelCount(review);
			if(count > 0)
			{	dao.myReviewDel(review);	}
		}
		
		return "redirect:/mypage/review.rv";
	}
	
	
}
