package detail;

/* 
 * ��� ���ƿ� ���
 * review_likeDTO Ŭ���� : review_like table�� ��� �Ӽ� ���� �� ȣ��
 * index_member : ȸ����ȣ
   index_book : å��ȣ
   index_review : ��� ��ȣ
   reaction : ���ƿ� üũ (���ƿ� ������ 1, ��� 0)
   count : �Խù� �������� �ȴ������� üũ
 */
public class Review_LikeDTO {
	private int index_member;
	private int index_book;
	private int index_review;
	private int reaction;
	private int count;
	
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
	public int getIndex_review() {
		return index_review;
	}
	public void setIndex_review(int index_review) {
		this.index_review = index_review;
	}
	public int getReaction() {
		return reaction;
	}
	public void setLike_status(int reaction) {
		this.reaction = reaction;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
