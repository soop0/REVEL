package admin;

/*
 * Ż����� DTO
 * interestLost : ��̰���
 * contentNot : ����������
 * serviceChange : Ÿ�����̿�
 * serviceNot : ���񽺺���
 * etc : ��Ÿ
 * nocomment : Ż����� ����
 */


public class WithdrawalDTO 
{
	private String interestLost = "���Ҽ��� ��̰� ��������";
	private String contentNot = "�а��ִ� �������� ���";
	private String serviceChange = "�ٸ� ���񽺸� �̿��Ϸ���";
	private String serviceNot = "���� �̿��� �����ؼ�";
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
