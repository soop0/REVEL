package admin;

/*
 * 탈퇴사유 DTO
 * interestLost : 흥미감소
 * contentNot : 컨텐츠감소
 * serviceChange : 타서비스이용
 * serviceNot : 서비스불편
 * etc : 기타
 * nocomment : 탈퇴사유 없음
 */


public class WithdrawalDTO 
{
	private String interestLost = "웹소설에 흥미가 떨어져서";
	private String contentNot = "읽고있는 콘텐츠가 없어서";
	private String serviceChange = "다른 서비스를 이용하려고";
	private String serviceNot = "서비스 이용이 불편해서";
	private String etc = null;
	private String nocomment = "null";
	
	public String getInterestLost() 
	{	return interestLost;	}
	public void setInterestLost(String interestLost) 
	{	this.interestLost = interestLost;	}
	
	public String getContentNot()
	{	return contentNot;	}
	public void setContentNot(String contentNot)
	{	this.contentNot = contentNot;	}
	
	public String getServiceChange() 
	{	return serviceChange;	}
	public void setServiceChange(String serviceChange) 
	{	this.serviceChange = serviceChange;	}
	
	public String getServiceNot() 
	{	return serviceNot;	}
	public void setServiceNot(String serviceNot)
	{	this.serviceNot = serviceNot;	}
	
	public String getEtc() 
	{	return etc;	}
	public void setEtc(String etc)
	{	this.etc = etc;	}
	
	public String getNocomment()
	{	return nocomment;	}
	public void setNocomment(String nocomment) 
	{	this.nocomment = nocomment;	}
	
	
}
