package detail;

/* 
 * 댓글 좋아요 기능
 * review_likeDTO 클래스 : review_like table의 모든 속성 저장 및 호출
 * index_member : 회원번호
   index_book : 책번호
   index_review : 댓글 번호
   reaction : 좋아요 체크 (좋아요 누르면 1, 취소 0)
   count : 게시물 눌렀는지 안눌렀는지 체크
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
