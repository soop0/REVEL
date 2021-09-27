package detail;

import java.sql.Timestamp;

/*
 * Read_Status Table(�������� ���̺�)�� �̿�Ǵ� DTOŬ����
 * index_book : å��ȣ
 * index_member : ȸ����ȣ
 * index_rstatus : �������¹�ȣ
 * read : ��������
 * readBefore : �������� ���� �� �÷� �̸�(detail ���̺�)
 * readAfter : �������� ���� �� �÷� �̸�(detail ���̺�)
 * reg_date : �������� �ο��� �ð�
 * checkCount : �������� ����/���� ���� (����-1 , ����-0)
 */
public class ReadStatusDTO {
	private int index_book;
	private int index_member;
	private int index_rstatus;
	private int read;
	private String readBefore;
	private String readAfter;
	private Timestamp reg_date;
	private int checkCount;
	
	public int getIndex_book() 
	{	return index_book;	}
	public void setIndex_book(int index_book) 
	{	this.index_book = index_book;	}
	
	public int getIndex_member()
	{	return index_member;	}
	public void setIndex_member(int index_member)
	{	this.index_member = index_member;	}
	
	public int getIndex_rstatus() 
	{	return index_rstatus;	}
	public void setIndex_rstatus(int index_rstatus) 
	{	this.index_rstatus = index_rstatus;	}
	
	public int getRead() 
	{	return read;	}
	public void setRead(int read) 
	{	this.read = read;	}
	
	public String getReadBefore()
	{	return readBefore;	}
	public void setReadBefore(String readBefore)
	{	this.readBefore = readBefore;	}
	
	public String getReadAfter()
	{	return readAfter;	}
	public void setReadAfter(String readAfter)
	{	this.readAfter = readAfter;	}
	
	public Timestamp getReg_date() 
	{	return reg_date;	}
	public void setReg_date(Timestamp reg_date) 
	{	this.reg_date = reg_date;	}
	
	public int getCheckCount() 
	{	return checkCount;	}
	public void setCheckCount(int checkCount) 
	{	this.checkCount = checkCount;	}

}
