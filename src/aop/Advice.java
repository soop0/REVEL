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
	 * Advice Ŭ����
	 *  - ȸ������, �����ڼ��� �� ���� ���� ó���� �� �� aop ���
	 *        => aop ���� �� Advice Ŭ������ ������ �޼��� ����
	 *  - aop�� ������ �޼��� �ۼ�
	 *  
	 */
	
	/* 
	 * HttpServletRequest : ����� request => jsp���� ����ϴ� ��ü
	 * ServerRequest : �������� ���������� ���Ǵ� request
	 * LibraryRequest : �ܺ� ���̺귯�� �����͸� ����ϴ� request
	 * joinpoint : �߻����� �޼��忡 ���� ���� 
	 * args : �߻����� �޼����� �Ű���������
	 * proceed : ���ư����ϴ� ��η� �̵�(�帧 �̵�) => proceed �������� ���� �ڵ�� before, �Ʒ��� �ڵ�� after
	 */
	
	// aop ó�� : around
	// ȸ�� ���� �̿� �� �α��� �� �̿� �����ϰ� Ÿ�� ����
	// ���� ����, ��õ ����, ����������
	
	public Object myLogon(ProceedingJoinPoint jp) throws Throwable
	{	
		/*
		 * RequestAttributes ra = RequestContextHolder.currentRequestAttributes();		// ���� ���� ���� request ��ü
		 * ServletRequestAttributes sra = (ServletRequestAttributes)ra;				// HttpServletRequest�� �������� ���� ��ȯ
		 * HttpServletRequest request2 = sra.getRequest();								// jsp�������� ����ϴ� request
		*/
		
		// ���� ������� request ��ü ��������
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("status") == null)
		{
			NaverLoginBO naverLoginBO = new NaverLoginBO();
			
			/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			
			//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
			//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
			System.out.println("���̹�:" + naverAuthUrl);
			
			//loginNaver.jsp�� url �Ķ���͸� �ѱ��.
			request.setAttribute("url", naverAuthUrl);
			System.out.println(request.getRequestURI());
			request.setAttribute("uri", request.getRequestURI());
			return "/member/login";
		}
		else
		{	return jp.proceed();	}
	}
	
}
