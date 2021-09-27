package member;

import java.sql.Timestamp;

/*
 * MemberDTO Ŭ����	: Member Table�� �Ӽ����� ���� �� ȣ��
 * index_member 	: ȸ����ȣ
 * flatform_id		: īī��/���̹� API�� �̿��Ͽ� �α����� �� �޴� ������ȣ
 * birthday			: �������(ex>19940625)
 * year				: ����
 * month			: ���� ��
 * date				: ���� ��¥
 * age				: ����(������)
 * gender			: ����
 * status			: ȸ�� ����(11-���̹��̿� ����, 12-īī���̿� ���� / 50 - Ż�� / 100 - ������)
 * reg_date			: ���Խð�
 * b_day			: �������(ex>1994��06��25��) 
 * withdrawal		: Ż�����
 * withdrawal_etc	: ��Ÿ��ư���� �Է¹��� Ż�����
 */

public class MemberDTO {


	private int index_member;
	private String platform_id;
	private String nick;
	private String birthday;
	private int year;
	private int month;
	private int date;
	private int age;
	private int gender;
	private int status;
	private Timestamp reg_date;
	private String b_day;
	private String withdrawal;
	private String withdrawal_etc;

	
	public void setIndex_member(int index_member)
	{	this.index_member = index_member;	}
	
	public void setPlatform_id(String platform_id) 
	{	this.platform_id = platform_id;	}
	
	public void setNick(String nick) 
	{	this.nick = nick;	}
	
	public void setBirthday(String birthday) 
	{	this.birthday = birthday;	}
	
	public void setYear(int year) 
	{	this.year = year;	}
	
	public void setMonth(int month)
	{	this.month = month;	}
	
	public void setDate(int date) 
	{	this.date = date;	}
	
	public void setAge(int age) 
	{	this.age = age;	}
	
	public void setGender(int gender)
	{	this.gender = gender;	}
	
	public void setStatus(int status) 
	{	this.status = status;	}
	
	public void setReg_date(Timestamp reg_date)
	{	this.reg_date = reg_date;	}
	
	public void setWithdrawal(String withdrawal)
	{	this.withdrawal = withdrawal;	}
	
	public int getIndex_member() {return index_member;}
	public String getPlatform_id() {return platform_id;}
	public String getNick() {return nick;}
	public String getBirthday() {return birthday;}
	public int getYear() {return year;}
	public int getMonth() {return month;}
	public int getDate() {return date;}
	public int getAge() {return age;}
	public int getGender() {return gender;}
	public int getStatus() {return status;}
	public Timestamp getReg_date() {return reg_date;}
	public String getWithdrawal() {	return withdrawal;}
	

	public String getWithdrawal_etc() 
	{	return withdrawal_etc;	}
	
	public void setWithdrawal_etc(String withdrawal_etc)
	{	this.withdrawal_etc = withdrawal_etc;	}

	public String getB_day() 
	{
		String yy = birthday.substring(0,4);
		String mm = birthday.substring(4,6);
		String dd = birthday.substring(6);	
		b_day=yy+"�� "+mm+"�� "+dd+"�� ";
		return b_day;
	}


	
}

		

	
	



