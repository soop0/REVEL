package member;

/* MemberAnalDTO Ŭ���� :member_keyword_anal_index_member�� �Ӽ����� ���� �� ȣ��
 * ȸ�� ��ȣ Ű���带 �˾ƺ��� ���� �ʿ��� ������
 * index_keyword : Ű���� 87��
 * cumul_score : ������ �ű� ��ǰ�� ���� Ű���� ����(weight)*����(rating)�� ����� ���� Ű���� ����
 * weight : keyword_anal���̺��� Ű���� ����ġ (cumul_score�� ����ϱ����� �ʿ� )
 * rating : rating ���̺��� rating(����) (cumul_score�� ����ϱ����� �ʿ� )
 * keyword : keyword���̺��� keyword Ű�����̸� ȸ���м� ��Ʈ���� �����
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
