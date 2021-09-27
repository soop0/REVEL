package recommend;

import java.util.List;

/*
 * OutListDTO 클래스	: 개인맞춤추천시 제외할 책번호(index_book)목록을 호출하는 클래스
 * index_member		: 회원번호
 * outVolume		: 선호하지 않는 분량 목록(장편-1, 중편-2, 단편-3, 아무거나-4)
 * outGenre			: 선호하지 않는 장르 목록(로맨스-1, 로판-2, 판타지-3, 현판-4, 무협-5)
 * index_book		: 책번호
 */
public class OutListDTO {
	private int index_member;
	private List outVolume;
	private List outGenre;
	private int index_book;
	
	
	public int getIndex_member() {
		return index_member;
	}
	public void setIndex_member(int index_member) {
		this.index_member = index_member;
	}
	public List getOutVolume() {
		return outVolume;
	}
	public void setOutVolume(List outVolume) {
		this.outVolume = outVolume;
	}
	public List getOutGenre() {
		return outGenre;
	}
	public void setOutGenre(List outGenre) {
		this.outGenre = outGenre;
	}
	public int getIndex_book() {
		return index_book;
	}
	public void setIndex_book(int index_book) {
		this.index_book = index_book;
	}
	
	
	
}
