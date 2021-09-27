package detail;

import java.sql.Timestamp;

/*
 * 	Rating Table(작품평점 테이블)에 이용되는 DTO클래스
 * 	 - 회원별 해당 작품의 평점을 Rating Table에서 호출하거나 저장하는 변수들을 모은 클래스
 * 	 - index_book : 웹소설번호
 *   - index_member : 회원번호
 *   - rating : 회원이 해당 소설에 부여한 평점
 *   - reg_time : 평점 부여한 날짜
 *   - index_title : detailDTO 웹소설제목(나의평가목록에서 사용)
 *   - writer : detailDTO 웹소설 작가 (나의평가목록에서 사용)
 */

public class RatingMemberDTO 
{
	private int index_book;
	private int index_member;
	private int rating;
	private Timestamp reg_time;
	private String title;
	private String writer;
	
	// RatingMemberDTO에 해당된 변수들을 각각 저장 및 호출하는 메서드
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
