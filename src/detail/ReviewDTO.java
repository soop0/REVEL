package detail;

import java.sql.Timestamp;

/*
 * reviewDTO Ŭ���� : review table�� ��� �Ӽ� ���� �� ȣ��
   index_review : ��۹�ȣ
   index_member : ȸ����ȣ (�ۼ���)
   index_book : å��ȣ
   title : å����
   content : ��� ����
   reg_time : ��� �ۼ� �ð�
   like_num : ��� ��õ ��
   cleanbot : Ŭ���� ��� ���� ����(�����ڿ� ���� ������ ���) - 1(����), 0(���� �ƴ�)
   nick : �г��� (�ۼ���)
   cnt : �󵵼�(�ִ� ��� �г���, �ִ� ��� ��ǰ �� ���)
   rank : ����(�ִ� ��� �г���, �ִ� ��� ��ǰ �� ���)
   pageSize : ������ ������
   pageNum : ������ ��ȣ
   currentPage : ���� ������
   startRow : ù ���� �۹�ȣ
   endRow : �� �۹�ȣ
   count : ��ü ��� ����
*/
public class ReviewDTO 
{
	private int index_review;
	private int index_member;
	private int index_book;
	private String title;
	private String content;
	private Timestamp reg_time;
	private int like_num;
	private int cleanbot;
	private String nick;
	private int cnt;
	private int rank;
	private int pageSize;
	private String pageNum;
	private int currentPage;
	private int startRow;
	private int endRow;
	private int count;
	
	public int getIndex_review() 
	{	return index_review;	}
	public void setIndex_review(int index_review) 
	{	this.index_review = index_review;	}
	
	public int getIndex_member() 
	{	return index_member;	}
	public void setIndex_member(int index_member) 
	{	this.index_member = index_member;	}
	
	public int getIndex_book() 
	{	return index_book;	}
	public void setIndex_book(int index_book) 
	{	this.index_book = index_book;	}
	
	public String getTitle()
	{	return title;	}
	public void setTitle(String title)
	{	this.title = title;	}
	
	public String getContent() 
	{	return content;	}
	public void setContent(String content) 
	{	this.content = content;	}
	
	public Timestamp getReg_time()
	{	return reg_time;	}
	public void setReg_time(Timestamp reg_time)
	{	this.reg_time = reg_time;	}
	
	public int getLike_num() 
	{	return like_num;	}
	public void setLike_num(int like_num) 
	{	this.like_num = like_num;	}
	
	public int getCleanbot()
	{	return cleanbot;	}
	public void setCleanbot(int cleanbot)
	{	this.cleanbot = cleanbot;	}
	
	public String getNick()
	{	return nick;	}
	public void setNick(String nick) 
	{	this.nick = nick;	}
	
	public int getCnt()
	{	return cnt;	}
	public void setCnt(int cnt)
	{	this.cnt = cnt;	}
	
	public int getRank()
	{	return rank;	}
	public void setRank(int rank)
	{	this.rank = rank;	}
	
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
