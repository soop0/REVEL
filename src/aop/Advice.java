package aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import member.NaverLoginBO;

public class Advice 
{
	/*
	 * Advice 클래스
	 *  - 회원서비스, 관리자서비스 등 서비스 조건 처리를 할 때 aop 사용
	 *        => aop 실행 시 Advice 클래스에 지정한 메서드 실행
	 *  - aop를 실행할 메서드 작성
	 *  
	 */
	
	/* 
	 * HttpServletRequest : 사용자 request => jsp에서 사용하던 객체
	 * ServerRequest : 서버에서 내부적으로 사용되는 request
	 * LibraryRequest : 외부 라이브러리 데이터를 사용하는 request
	 * joinpoint : 발생지점 메서드에 대한 정보 
	 * args : 발생지점 메서드의 매개변수정보
	 * proceed : 돌아가야하는 경로로 이동(흐름 이동) => proceed 기준으로 위쪽 코드는 before, 아래쪽 코드는 after
	 */
	
	// aop 처리 : around
	// 회원 서비스 이용 시 로그인 후 이용 가능하게 타겟 설정
	// 평점 서비스, 추천 서비스, 마이페이지
	
	public Object myLogon(ProceedingJoinPoint jp) throws Throwable
	{	
		/*
		 * RequestAttributes ra = RequestContextHolder.currentRequestAttributes();		// 현재 실행 중인 request 객체
		 * ServletRequestAttributes sra = (ServletRequestAttributes)ra;				// HttpServletRequest를 가져오기 위해 변환
		 * HttpServletRequest request2 = sra.getRequest();								// jsp에서부터 사용하던 request
		*/
		
		// 현재 사용중인 request 객체 가져오기
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("status") == null)
		{
			NaverLoginBO naverLoginBO = new NaverLoginBO();
			
			/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			
			//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
			//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
			System.out.println("네이버:" + naverAuthUrl);
			
			//loginNaver.jsp로 url 파라미터를 넘긴다.
			request.setAttribute("url", naverAuthUrl);
			System.out.println(request.getRequestURI());
			request.setAttribute("uri", request.getRequestURI());
			return "/member/login";
		}
		else
		{	return jp.proceed();	}
	}
	
}
