package recommend;

/*
 * MemKeyAnalDTO 클래스 : member_keyword_anal_member_index 테이블 (ex>member_keyword_anal_1) 저장 및 호출
 * 						회원의 키워드 점수에 대한 정보를 저장하고 호출한다.
 * index_keyword : 키워드 번호
 * cumul_score : 키워드의 누적점수
 * */
public class MemKeyAnalDTO {
	private int index_keyword;
	private double cumul_score;
	
	public void setIndex_keyword(int index_keyword) {	this.index_keyword = index_keyword;	}
	public void setCumul_score(double cumul_score) {	this.cumul_score = cumul_score;	}
	
	public int getIndex_keyword() {	return index_keyword;	}
	public double getCumul_score() {	return cumul_score;	}
}
