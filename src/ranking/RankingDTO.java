package ranking;

/*
 * RankingDTO Ŭ����	: Ŭ���� ��� ������ �Ǵ� �Ⱓ(today, yesterday)���� 
 * 					  ��ũŬ������ ����ϴ� �� �ʿ��� �Ӽ��� ȣ�� �� �����ϴ� Ŭ����
 * index_book		: å��ȣ
 * link_sum			: �����Ⱓ���� Ŭ���� �հ�
 * endDay			: ������ ��¥
 * startDay			: ���� ��¥
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
