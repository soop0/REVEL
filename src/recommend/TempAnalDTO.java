package recommend;
/*
 * TempAnalDTO 클래스 : temp_anala_index_member Table의 속성들을 저장 및 호출
 * index_member 	: Table 이름에 넣을 회원번호
 * index_book 		: 책번호
 * category 		: 키워드 카테고리, 100: 캐릭터, 200: 분위기, 300: 소재, 400: 독자반응
 * value 			: 카테고리별 코사인연관도 분석 값 
 */
public class TempAnalDTO {
	private int index_member;
	private int index_book;
	private int category;
	private double value;
	
	public int getIndex_member() {
		return index_member;
	}
	public void setIndex_member(int index_member) {
		this.index_member = index_member;
	}
	public int getIndex_book() {
		return index_book;
	}
	public void setIndex_book(int index_book) {
		this.index_book = index_book;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
}
