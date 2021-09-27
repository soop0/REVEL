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
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		// System.out.println("네이버:" + naverAuthUrl);
		
		//loginNaver.jsp로 url 파라미터를 넘긴다.
		model.addAttribute("url", naverAuthUrl);
		
		return "/member/login";		
	}
	
	//아이디 조회 후, 있으면 index_member 보냄 -> main페이지로 이동
	//             없으면 index_member로 0보냄 -> joinForm페이지로 이동
	@RequestMapping("loginPro.rv")
	public String loginPro(String platform_id, String platform, String uri, HttpServletRequest request, HttpSession session, Model model) throws Exception 
	{	
		int isMemberCount = 0;		// 회원유무 확인
		int index_member = 0;		// 회원고유번호
		int status = 0;				// 회원상태
	
		// aop 실행시 원래페이지로 돌아갈 uri
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
		
		// 관리자 상태값
		if(platform_id.equals(daol.isAdmin())) 
		{	status = 100;	}
		else
		{
			// 플랫폼 로그인 별 회원 상태값
			if(platform.equals("kakao"))
			{	status = 12;	}
			else if(platform.equals("naver")) 
			{	status = 11;	}
		}
		member.setPlatform_id(platform_id);
		member.setStatus(status);
		
		// DB에 기존 회원이 있는지 비교한다. 0:비회원,탈퇴회원 1:회원 
		isMemberCount = daol.isMemberCount(member);
		
		// 기존 회원 로그인 처리
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
	 * 네이버 로그인 성공시 callback호출 메소드
	 * 네이버로 로그인 시 한번 로그인 생성하면 인증세션이 만들어지기 때문에
	 * 그 이후에는 네이버 로그인 버튼만 눌러도 자동 로그인이 된다.
	 */
	
	@RequestMapping(value = "/loginNaver.rv", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws Exception
	{
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
	
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
	
		//response의 nickname값 파싱
		String platform_id = (String)response_obj.get("id");
		
		//DB 처리
		String platform = "naver";	// platform : naver
		int isMemberCount = 0;		// 회원유무 확인
		int index_member = 0;		// 회원고유번호
		int status = 0;				// 회원상태
		
		
		// 관리자 상태값
		if(platform_id.equals(daol.isAdmin())) 
		{	status = 100;	}
		else
		{
			// 플랫폼 로그인 별 회원 상태값
			if(platform.equals("kakao"))
			{	status = 12;	}
			else if(platform.equals("naver")) 
			{	status = 11;	}
		}
		member.setPlatform_id(platform_id);
		member.setStatus(status);
		
		// DB에 기존 회원이 있는지 비교한다. 0:비회원,탈퇴회원 1:회원 
		isMemberCount = daol.isMemberCount(member);
				
		// 기존 회원 로그인 처리
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
		//닉네임 중복 확인 count 1이면 닉네임중복
		int nickCount = dao.nickCheck(member.getNick());
		
		if(nickCount == 0)
		{
			//생년월일 20210709 형식으로 만들기
			String month = (member.getMonth() < 10) ? "0" + member.getMonth() : "" + member.getMonth();
			String date = (member.getDate() < 10) ? "0" + member.getDate() : "" + member.getDate();
			String birthday = "" + member.getYear() + month + date;
			member.setBirthday(birthday);
		
			//나이계산
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
		
			// 비회원 회원가입, 탈퇴회원(index_member == 0일 때) DB에 회원가입 정보 추가
			daol.insertMember(member);	
			
			// 새로 가입한 회원의 index_member를 추출하여 설문조사 index_member에 저장한다.	
			int index_member = 0;
			if(daol.isMemberCount(member) != 0)
			{
				index_member = daol.isMember(member);
				survey.setIndex_member(index_member);
			}
		
			// surveyTable에 설문조사가 이미 등록되어 있는지 확인한다.
			int surveyCount = daol.memberSurveyCount(survey.getIndex_member());
		
			// 설문조사 등록
			if(surveyCount == 0) 
			{
				//Read_index_member 테이블생성
				//Member_Keyword_anal_index_member 생성
				daol.insertSurvey(survey);
			}
		
			// 회원가입이 완료되면 자동으로 DB에 회원별 독서상태, 취향분석 테이블, 독서상태 시퀀스를 생성한다.
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
	 *  로그아웃 시 revel에 사용된 세션은 모두 사라진다.
	 *  단, 네이버로 로그인 시 완벽한 로그아웃을 하려면 
	 *  네이버 홈페이지로 접속해서 로그아웃 버튼을 눌러야한다!
	 *  카카오 로그인은 상관 없음... 
	 */
	@RequestMapping("logout.rv")
	public String myLogonlogout(HttpSession session) 
	{
		session.invalidate();
		return "/member/logout";
	}
}
