package detail;

import java.sql.Timestamp;

/*
 * Read_index_memberDTO Ŭ���� : read_index_member table�� ��� �Ӽ� ���� �� ȣ��
 * index_member : ȸ����ȣ
 * index_rstatus : �������� �ο� �׸� index ��ȣ
 * index_book : å��ȣ
 * read : ��������(�����Ŵ�1,�а��ִ�2,���о���3,���ɾ���0)
 * reg_date : �������� �ο� ��¥
 * 
 */
public class Read_index_memberDTO {
	private int index_member;
	private int index_rstatus;
	private int index_book;
	private int read;
	private Timestamp reg_date;
	
	public int getIndex_member() 
	{	return index_member;	}
	public void setIndex_member(int index_member) 
	{	this.index_member = index_member;	}
	
	public int getIndex_rstatus() 
	{	return index_rstatus;	}
	public void setIndex_rstatus(int index_rstatus) 
	{	this.index_rstatus = index_rstatus;	}
	
	public int getIndex_book() 
	{	return index_book;	}
	public void setIndex_book(int index_book) 
	{	this.index_book = index_book;	}
	
	public int getRead() 
	{	return read;	}
	public void setRead(int read) 
	{	this.read = read;	}
	
	public Timestamp getReg_date() 
	{	return reg_date;	}
	public void setReg_date(Timestamp reg_date) 
	{	this.reg_date = reg_date;	}
}
