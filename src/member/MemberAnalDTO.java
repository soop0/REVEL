package member;

/* MemberAnalDTO 클래스 :member_keyword_anal_index_member의 속성들을 저장 및 호출
 * 회원 선호 키워드를 알아보기 위해 필요한 변수들
 * index_keyword : 키워드 87개
 * cumul_score : 별점을 매긴 작품이 가진 키워드 비중(weight)*평점(rating)을 계산한 누적 키워드 점수
 * weight : keyword_anal테이블의 키워드 비중치 (cumul_score를 계산하기위해 필요 )
 * rating : rating 테이블의 rating(평점) (cumul_score를 계산하기위해 필요 )
 * keyword : keyword테이블의 keyword 키워드이름 회원분석 차트에서 사용함
 */

public class MemberAnalDTO {
	private int index_keyword;
	private float cumul_score;
	private float weight;
	private float rating;
	private String keywords;
	
	public int getIndex_keyword() {
		return index_keyword;
	}
	public void setIndex_keyword(int index_keyword) {
		this.index_keyword = index_keyword;
	}
	public float getCumul_score() {
		return cumul_score;
	}
	public void setCumul_score(float cumul_score) {
		this.cumul_score = cumul_score;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}
