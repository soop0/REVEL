package detail;

import java.util.List;

public interface DetailDAOInter {
	
	//Detail 테이블의 전체 내용 검색
	public DetailDTO allDetail(int index_book) throws Exception;
	
	//댓글 갯수(count)
	public int reviewCount(int index_book) throws Exception;
	
	//해당 소설의 댓글 갯수를 detail Table의 review_num에 저장하는 메서드
	public void review_num(DetailDTO dto) throws Exception;
	
	//댓글 조회
	public List<ReviewDTO> reviewList(ReviewDTO dto) throws Exception;
		
	//댓글 작성
	public void reviewWrite(ReviewDTO dto) throws Exception;
		
	//댓글 삭제
	public void reviewDelete(int index_review) throws Exception;
	
	// 회원이 해당 작품을 평점 부여했는지 확인하는 메서드
	// 평가 했으면 1, 없으면 0
	public int ratingCount(RatingMemberDTO dto) throws Exception;
	
	// 회원의 해당 작품 평점을 평점 테이블(Rating Table)에 추가하는 메서드
	public void ratingInsert(RatingMemberDTO dto) throws Exception;
	
	// 회원의 해당 작품 평점을 평점 테이블(Rating Table)에 업데이트하는 메서드
	public void ratingUpdate(RatingMemberDTO dto) throws Exception;
	
	// 회원의 해당 작품 평점을 조회하는 메서드
	public int ratingSelect(RatingMemberDTO dto) throws Exception;
	
	// 해당 작품의 전체 평점 인원수를 조회하는 메서드
	public int ratingTotalNum(int index_book) throws Exception;
	
	// 해당 작품의 전체 평균 평점을 조회하는 메서드
	public float ratingAvg(int index_book) throws Exception;
	
	// 각 해당 작품 간 계산된 전체 인원수와 평균 평점을 detail Table에 업데이트하는 메서드
	public void ratingAnl(DetailDTO detail) throws Exception;
	
	//회원별 해당 작품 독서상태 선택여부 확인하는 메서드
	public int rstatusCount(ReadStatusDTO dto) throws Exception;
		
	//회원별 해당 작품 독서상태를 독서상태 테이블(Read_Status Table)에 추가하는 메서드
	public void rstatusInsert(ReadStatusDTO dto) throws Exception;
	
	//회원별 해당 작품 독서상태 조회하는 메서드
	public int rstatusSelect(ReadStatusDTO dto) throws Exception;
	
	//회원별 해당 작품 독서상태를 독서상태 테이블(Read_Status Table)에 업데이트하는 메서드
	public void rstatusUpdate(ReadStatusDTO dto) throws Exception;
	
	//회원별 해당 작품 독서상태를 해제하여 독서상태 테이블(Read_Status Table)의 행을 삭제하는 메서드
	public void rstatusDelete(ReadStatusDTO dto) throws Exception;
	
	//해당 작품의 독서상태를 선택한 전체 인원 조회하는 메서드
	public int readTotalNum(int index_book) throws Exception;
	
	//해당 작품 독서상태 추가하는 메서드
	public void detailRstatusAdd(ReadStatusDTO dto) throws Exception;
	
	//해당 작품 독서상태 변경하는 메서드
	public void detailRstatusChange(ReadStatusDTO dto) throws Exception;
	
	//해당 작품 독서상태 취소하는 메서드
	public void detailRstatusDelete(ReadStatusDTO dto) throws Exception;
	
	//사용자가 누른 플랫폼의 해당 소설이 link_click 테이블에 있는지 확인
	public int clickCount(LinkClickDTO dto) throws Exception;
		
	//회원별 해당 작품의 네이버시리즈 링크를 클릭했는지 확인하는 메서드
	public int naverSelect(LinkClickDTO dto) throws Exception;
		
	//회원별 해당 작품의 카카오페이지 링크를 클릭했는지 확인하는 메서드
	public int kakaoSelect(LinkClickDTO dto) throws Exception;
		
	//회원별 해당 작품 네이버/카카오 링크 클릭 여부를 링크클릭 테이블(Link_Click Table)에 추가하는 메서드
	public void clickInsert(LinkClickDTO dto) throws Exception;
		
	//회원별 해당 작품 플랫폼 링크 수 업데이트하는 메서드
	public void platformUpdate(LinkClickDTO dto) throws Exception;
	
	//동일 작가 작품 수 확인하는 메서드
	public int sameWriterCount(int index_book) throws Exception;
		
	//동일 작가 작품 정보 확인하는 메서드
	public List sameWriterList(int index_book) throws Exception;
	
}
