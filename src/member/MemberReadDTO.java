package member;

import java.sql.Timestamp;

/*
 * 	Read_index_member Table(ȸ�� �������� ���̺�)�� �̿�Ǵ� DTOŬ����
 * 	 - index_rstatus : �������� �����ϴ� ����
 *	 - index_book : ���Ҽ���ȣ
 *   - read : �������� (0: ���ɾ���, 1:�����Ŵ�, 2:�а��ִ�, 3:���о���)
 *   - reg_date : ���º����� ��¥
 *   - index_member : ȸ����ȣ(ȸ�����̺� ȣ��� ���)
 *   - index_title : detailDTO ���Ҽ�����(�����򰡸�Ͽ��� ���)
 *   - writer : detailDTO ���Ҽ� �۰� (�����򰡸�Ͽ��� ���)
 *   - genre : detail���̺��� genre ȸ�� ��ȣ �帣�м����� ����� (1�θǽ�, 2����,3��Ÿ��,4����,5����)
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
