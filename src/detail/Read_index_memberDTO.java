package detail;

import java.sql.Timestamp;

/*
 * Read_index_memberDTO 클래스 : read_index_member table의 모든 속성 저장 및 호출
 * index_member : 회원번호
 * index_rstatus : 독서상태 부여 항목 index 번호
 * index_book : 책번호
 * read : 독서상태(읽을거다1,읽고있다2,다읽었다3,관심없다0)
 * reg_date : 독서상태 부여 날짜
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
