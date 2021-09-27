package member;

import java.sql.Timestamp;

/*
 * 	Read_index_member Table(회원 독서상태 테이블)에 이용되는 DTO클래스
 * 	 - index_rstatus : 시퀀스로 증가하는 변수
 *	 - index_book : 웹소설번호
 *   - read : 독서상태 (0: 관심없음, 1:읽을거다, 2:읽고있다, 3:다읽었다)
 *   - reg_date : 상태변경한 날짜
 *   - index_member : 회원번호(회원테이블 호출시 사용)
 *   - index_title : detailDTO 웹소설제목(나의평가목록에서 사용)
 *   - writer : detailDTO 웹소설 작가 (나의평가목록에서 사용)
 *   - genre : detail테이블의 genre 회원 선호 장르분석에서 사용함 (1로맨스, 2로판,3판타지,4현판,5무협)
 */

public class MemberReadDTO {
	private int index_rstatus;
	private int index_book;
	private int read;
	private Timestamp reg_date;
	private int index_member;
	private String title;
	private String writer;
	private int genre;
	
	public int getIndex_rstatus() {
		return index_rstatus;
	}
	public void setIndex_rstatus(int index_rstatus) {
		this.index_rstatus = index_rstatus;
	}
	public int getIndex_book() {
		return index_book;
	}
	public void setIndex_book(int index_book) {
		this.index_book = index_book;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getIndex_member() {
		return index_member;
	}
	public void setIndex_member(int index_member) {
		this.index_member = index_member;
	}
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
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	
}
