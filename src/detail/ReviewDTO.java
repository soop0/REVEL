package detail;

import java.sql.Timestamp;

/*
 * reviewDTO 클래스 : review table의 모든 속성 저장 및 호출
   index_review : 댓글번호
   index_member : 회원번호 (작성자)
   index_book : 책번호
   title : 책제목
   content : 댓글 내용
   reg_time : 댓글 작성 시간
   like_num : 댓글 추천 수
   cleanbot : 클린봇 댓글 삭제 여부(관리자에 의해 삭제된 댓글) - 1(삭제), 0(삭제 아님)
   nick : 닉네임 (작성자)
   cnt : 빈도수(최다 댓글 닉네임, 최다 댓글 작품 때 사용)
   rank : 순위(최다 댓글 닉네임, 최다 댓글 작품 때 사용)
   pageSize : 페이지 사이즈
   pageNum : 페이지 번호
   currentPage : 현재 페이지
   startRow : 첫 시작 글번호
   endRow : 끝 글번호
   count : 전체 댓글 갯수
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
