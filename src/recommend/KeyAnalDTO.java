package recommend;

/*
 * KeyAnalDTO 클래스 : Keyword_anala Table의 속성들을 저장 및 호출
 * index_book 		: 책번호
 * index_keyword 	: 키워드번호
 * weight			: 가중치  
 */

public class KeyAnalDTO {
	int index_book;
	int index_keyword;
	double weight;
	
	// get() : 변수 호출 , set() : 변수 저장
	public void setIndex_book(int index_book) {	this.index_book = index_book;	}
	public void setIndex_keyword(int index_keyword) {	this.index_keyword = index_keyword;	}
	public void setWeight(double weight) {	this.weight = weight;	}
	
	public int getIndex_book() {	return index_book;	}
	public int getIndex_keyword() {	return index_keyword;	}
	public double getWeight() {	return weight;	}
}
