package detail;

import java.sql.Timestamp;

/*
 * 	Rating Table(��ǰ���� ���̺�)�� �̿�Ǵ� DTOŬ����
 * 	 - ȸ���� �ش� ��ǰ�� ������ Rating Table���� ȣ���ϰų� �����ϴ� �������� ���� Ŭ����
 * 	 - index_book : ���Ҽ���ȣ
 *   - index_member : ȸ����ȣ
 *   - rating : ȸ���� �ش� �Ҽ��� �ο��� ����
 *   - reg_time : ���� �ο��� ��¥
 *   - index_title : detailDTO ���Ҽ�����(�����򰡸�Ͽ��� ���)
 *   - writer : detailDTO ���Ҽ� �۰� (�����򰡸�Ͽ��� ���)
 */

public class RatingMemberDTO 
{
	private int index_book;
	private int index_member;
	private int rating;
	private Timestamp reg_time;
	private String title;
	private String writer;
	
	// RatingMemberDTO�� �ش�� �������� ���� ���� �� ȣ���ϴ� �޼���
	public int getIndex_book() 
	{	return index_book;	}
	public void setIndex_book(int index_book)
	{	this.index_book = index_book;	}
	
	public int getIndex_member() 
	{	return index_member;	}
	public void setIndex_member(int index_member)
	{	this.index_member = index_member;	}
	
	public int getRating() 
	{	return rating;	}
	public void setRating(int rating)
	{	this.rating = rating;	}
	
	public Timestamp getReg_time() 
	{	return reg_time;	}
	public void setReg_time(Timestamp reg_time) 
	{	this.reg_time = reg_time;	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	
	
}
