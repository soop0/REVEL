package ranking;

/*
 * RankingDTO 클래스	: 클릭수 계산 기준이 되는 기간(today, yesterday)동안 
 * 					  링크클릭수를 계산하는 데 필요한 속성을 호출 및 저장하는 클래스
 * index_book		: 책번호
 * link_sum			: 설정기간동안 클릭수 합계
 * endDay			: 마지막 날짜
 * startDay			: 시작 날짜
 * 
 */
public class RankingDTO {
	private int index_book;
	private int link_sum;
	private String endDay;
	private String startDay;
	
	public int getIndex_book() {
		return index_book;
	}
	public void setIndex_book(int index_book) {
		this.index_book = index_book;
	}
	public int getLink_sum() {
		return link_sum;
	}
	public void setLink_sum(int link_sum) {
		this.link_sum = link_sum;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	
	
}
