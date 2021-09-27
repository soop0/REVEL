package help;

import java.sql.Timestamp;

/*
 * �������� DTO
 * 
 * index_notice : �������� ��ȣ
 * title : ����
 * content : ����
 * writer : �ۼ���
 * reg_time : �ۼ�����
 * pageSize : �������� ������
 * pageNum : ������ ��ȣ
 * currentPage : ���� ������
 * startRow : ���� �۹�ȣ
 * endRow : �� �۹�ȣ
 * count : ��ü �� ��
 */


// DTO : ���� ���� �� ȣ���ϴ� ����
public class NoticeDTO 
{
	private int index_notice;
	private String title;
	private String content;
	private String writer;
	private Timestamp reg_time;
	private int pageSize;
	private String pageNum;
	private int currentPage;
	private int startRow;
	private int endRow;
	private int count;
	
	
	public int getIndex_notice() 
	{	return index_notice;	}
	public void setIndex_notice(int index_notice)
	{	this.index_notice = index_notice;	}
	
	public String getTitle() 
	{	return title;	}
	public void setTitle(String title) 
	{	this.title = title;	}
	
	public String getContent()
	{	return content;	}
	public void setContent(String content) 
	{	this.content = content;	}
	
	public String getWriter()
	{	return writer;	}
	public void setWriter(String writer) 
	{	this.writer = writer;	}
	
	public Timestamp getReg_time() 
	{	return reg_time;	}
	public void setReg_time(Timestamp reg_time) 
	{	this.reg_time = reg_time;	}
	
	public int getPageSize() 
	{	return pageSize;	}
	public void setPageSize(int pageSize)
	{	this.pageSize = pageSize;	}
	
	public String getPageNum()
	{	return pageNum;	}
	public void setPageNum(String pageNum)
	{	this.pageNum = pageNum;	}
	
	public int getCurrentPage()
	{	return currentPage;	}
	public void setCurrentPage(int currentPage) 
	{	this.currentPage = currentPage;	}
	
	public int getStartRow() 
	{	return startRow;	}
	public void setStartRow(int startRow)
	{	this.startRow = startRow;	}
	
	public int getEndRow()
	{	return endRow;	}
	public void setEndRow(int endRow) 
	{	this.endRow = endRow;	}
	
	public int getCount()
	{	return count;	}
	public void setCount(int count)
	{	this.count = count;	}
	
	
}
