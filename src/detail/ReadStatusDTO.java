package detail;

import java.sql.Timestamp;

/*
 * Read_Status Table(독서상태 테이블)에 이용되는 DTO클래스
 * index_book : 책번호
 * index_member : 회원번호
 * index_rstatus : 독서상태번호
 * read : 독서상태
 * readBefore : 독서상태 변경 전 컬럼 이름(detail 테이블)
 * readAfter : 독서상태 변경 후 컬럼 이름(detail 테이블)
 * reg_date : 독서상태 부여한 시간
 * checkCount : 독서상태 선택/해제 구분 (선택-1 , 해제-0)
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
