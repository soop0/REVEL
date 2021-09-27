package member;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
* Handles requests for the application home page.
*/

@Controller
public class LogonBean 
{	
	@Autowired
	private LogonDAOInter daol = null;

	@Autowired
	private MemberDAOInter dao = null;

	@Autowired
	private MemberDTO member = null;
	
	/* NaverLoginBO */
	@Autowired
	private NaverLoginBO naverLoginBO = null;
	
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO)
	{	this.naverLoginBO = naverLoginBO;	}
	
	@RequestMapping("login.rv")
	public String loginForm(Model model, HttpSession session) 
	{	
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		// System.out.println("���̹�:" + naverAuthUrl);
		
		//loginNaver.jsp�� url �Ķ���͸� �ѱ��.
		model.addAttribute("url", naverAuthUrl);
		
		return "/member/login";		
	}
	
	//���̵� ��ȸ ��, ������ index_member ���� -> main�������� �̵�
	//             ������ index_member�� 0���� -> joinForm�������� �̵�
	@RequestMapping("loginPro.rv")
	public String loginPro(String platform_id, String platform, String uri, HttpServletRequest request, HttpSession session, Model model) throws Exception 
	{	
		int isMemberCount = 0;		// ȸ������ Ȯ��
		int index_member = 0;		// ȸ��������ȣ
		int status = 0;				// ȸ������
	
		// aop ����� ������������ ���ư� uri
		if(uri != "")
		{	
			String[] aop_uri = uri.split("/revel");	
			model.addAttribute("aop_uri", aop_uri[1]);
		}
		else
		{	
			String aop_uri = null;	
			model.addAttribute("aop_uri", aop_uri);
		}
		
		// ������ ���°�
		if(platform_id.equals(daol.isAdmin())) 
		{	status = 100;	}
		else
		{
			// �÷��� �α��� �� ȸ�� ���°�
			if(platform.equals("kakao"))
			{	status = 12;	}
			else if(platform.equals("naver")) 
			{	status = 11;	}
		}
		member.setPlatform_id(platform_id);
		member.setStatus(status);
		
		// DB�� ���� ȸ���� �ִ��� ���Ѵ�. 0:��ȸ��,Ż��ȸ�� 1:ȸ�� 
		isMemberCount = daol.isMemberCount(member);
		
		// ���� ȸ�� �α��� ó��
		if(isMemberCount != 0)
		{	
			index_member = daol.isMember(member);
			member = daol.getMember(index_member);
			session.setAttribute("index_member", member.getIndex_member());
			session.setAttribute("nick", member.getNick());
			session.setAttribute("status", member.getStatus());
		}
		
		model.addAttribute("index_member", index_member);
		model.addAttribute("platform_id", platform_id);
		model.addAttribute("platform", platform);
		model.addAttribute("status", status);

		return "/member/loginPro";
	}
	 
	/* 
	 * ���̹� �α��� ������ callbackȣ�� �޼ҵ�
	 * ���̹��� �α��� �� �ѹ� �α��� �����ϸ� ���������� ��������� ������
	 * �� ���Ŀ��� ���̹� �α��� ��ư�� ������ �ڵ� �α����� �ȴ�.
	 */
	
	@RequestMapping(value = "/loginNaver.rv", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws Exception
	{
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		//1. �α��� ����� ������ �о�´�.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String������ json������
		/** apiResult json ����
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		
		//2. String������ apiResult�� json���·� �ٲ�
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
	
		//3. ������ �Ľ�
		//Top���� �ܰ� _response �Ľ�
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
	
		//response�� nickname�� �Ľ�
		String platform_id = (String)response_obj.get("id");
		
		//DB ó��
		String platform = "naver";	// platform : naver
		int isMemberCount = 0;		// ȸ������ Ȯ��
		int index_member = 0;		// ȸ��������ȣ
		int status = 0;				// ȸ������
		
		
		// ������ ���°�
		if(platform_id.equals(daol.isAdmin())) 
		{	status = 100;	}
		else
		{
			// �÷��� �α��� �� ȸ�� ���°�
			if(platform.equals("kakao"))
			{	status = 12;	}
			else if(platform.equals("naver")) 
			{	status = 11;	}
		}
		member.setPlatform_id(platform_id);
		member.setStatus(status);
		
		// DB�� ���� ȸ���� �ִ��� ���Ѵ�. 0:��ȸ��,Ż��ȸ�� 1:ȸ�� 
		isMemberCount = daol.isMemberCount(member);
				
		// ���� ȸ�� �α��� ó��
		if(isMemberCount != 0)
		{	
			index_member = daol.isMember(member);
			member = daol.getMember(index_member);
			session.setAttribute("index_member", member.getIndex_member());
			session.setAttribute("nick", member.getNick());
			session.setAttribute("status", member.getStatus());
		}
		
		model.addAttribute("index_member", index_member);
		model.addAttribute("platform_id", platform_id);
		model.addAttribute("platform", platform);
		model.addAttribute("status", status);

		return "/member/loginNaver";
	}
	
	@RequestMapping("join.rv")
	public String join(HttpServletRequest request, Model model) throws Exception
	{
		model.addAttribute("platform_id", request.getParameter("platform_id"));
		model.addAttribute("platform", request.getParameter("platform"));
		model.addAttribute("index_member", request.getParameter("index_member"));
		model.addAttribute("status", request.getParameter("status"));
		return "/member/joinForm";
	}
	
	@RequestMapping("joinPro.rv")
	public String joinPro(String platform, MemberDTO member, SurveyDTO survey, Model model, HttpSession session) throws Exception
	{	
		//�г��� �ߺ� Ȯ�� count 1�̸� �г����ߺ�
		int nickCount = dao.nickCheck(member.getNick());
		
		if(nickCount == 0)
		{
			//������� 20210709 �������� �����
			String month = (member.getMonth() < 10) ? "0" + member.getMonth() : "" + member.getMonth();
			String date = (member.getDate() < 10) ? "0" + member.getDate() : "" + member.getDate();
			String birthday = "" + member.getYear() + month + date;
			member.setBirthday(birthday);
		
			//���̰��
			Date today = new Date();
			SimpleDateFormat md = new SimpleDateFormat("MMdd");
			SimpleDateFormat yd = new SimpleDateFormat("yyyy");
			int todayM = Integer.parseInt(md.format(today));
			int todayY = Integer.parseInt(yd.format(today));
		
			int birth = Integer.parseInt(month+date);
			int age = todayY - member.getYear() - 1;
			if(birth < todayM) 
			{	age = todayY - member.getYear(); }
		
			member.setAge(age);
		
			// ��ȸ�� ȸ������, Ż��ȸ��(index_member == 0�� ��) DB�� ȸ������ ���� �߰�
			daol.insertMember(member);	
			
			// ���� ������ ȸ���� index_member�� �����Ͽ� �������� index_member�� �����Ѵ�.	
			int index_member = 0;
			if(daol.isMemberCount(member) != 0)
			{
				index_member = daol.isMember(member);
				survey.setIndex_member(index_member);
			}
		
			// surveyTable�� �������簡 �̹� ��ϵǾ� �ִ��� Ȯ���Ѵ�.
			int surveyCount = daol.memberSurveyCount(survey.getIndex_member());
		
			// �������� ���
			if(surveyCount == 0) 
			{
				//Read_index_member ���̺����
				//Member_Keyword_anal_index_member ����
				daol.insertSurvey(survey);
			}
		
			// ȸ�������� �Ϸ�Ǹ� �ڵ����� DB�� ȸ���� ��������, ����м� ���̺�, �������� �������� �����Ѵ�.
			daol.read_index_memberCreateTable(survey.getIndex_member());
			daol.read_index_memberSequence(survey.getIndex_member());
			daol.member_keyword_anal_index_memberCreateTable(survey.getIndex_member());
			daol.member_keyword_anal_index_memberInsert(survey.getIndex_member());
		
			session.setAttribute("index_member", survey.getIndex_member());
			session.setAttribute("nick", member.getNick());
			session.setAttribute("status", member.getStatus());
		}
		
		model.addAttribute("index_member", survey.getIndex_member());
		model.addAttribute("nickCount",nickCount);		

		return "/member/joinPro";
	}
	
	/*
	 *  �α׾ƿ� �� revel�� ���� ������ ��� �������.
	 *  ��, ���̹��� �α��� �� �Ϻ��� �α׾ƿ��� �Ϸ��� 
	 *  ���̹� Ȩ�������� �����ؼ� �α׾ƿ� ��ư�� �������Ѵ�!
	 *  īī�� �α����� ��� ����... 
	 */
	@RequestMapping("logout.rv")
	public String myLogonlogout(HttpSession session) 
	{
		session.invalidate();
		return "/member/logout";
	}
}
