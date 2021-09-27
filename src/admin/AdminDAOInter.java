package admin;

import java.util.List;

import detail.ReviewDTO;
import ranking.KeywordDTO;

public interface AdminDAOInter 
{
	// 전체 회원수 검색
	public int memberCount() throws Exception;
	
	// 네이버 회원수 검색
	public int naverCount() throws Exception;
	
	// 카카오 회원수 검색
	public int kakaoCount() throws Exception;
	
	// 탈퇴 회원수 검색
	public int withdrawalCount() throws Exception;
	
	// 남성 회원수 검색
	public int manCount() throws Exception;
	
	// 여성 회원수 검색
	public int womanCount() throws Exception;
	
	// 10대 회원수 검색
	public int count10() throws Exception;
	
	// 20대 회원수 검색
	public int count20() throws Exception;
	
	// 30대 회원수 검색
	public int count30() throws Exception;
	
	// 40대 회원수 검색
	public int count40() throws Exception;
	
	// 50대 이상 회원수 검색
	public int count50() throws Exception;
	
	// 흥미감소 탈퇴사유수 검색
	public int interestLost(String interestLost) throws Exception;
	
	// 컨텐츠부족 탈퇴사유수 검색
	public int contentNot(String contentNot) throws Exception;
	
	// 타서비스이용 탈퇴사유수 검색
	public int serviceChange(String serviceChange) throws Exception;
	
	// 서비스불편 탈퇴사유수 검색
	public int serviceNot(String serviceNot) throws Exception;
	
	// 기타 탈퇴사유수 검색
	public int etc(WithdrawalDTO dto) throws Exception;
	
	// 전체 회원 리스트 검색
	public List memberList() throws Exception;
	
	// 네이버 회원 리스트 검색
	public List naverList() throws Exception;
	
	// 카카오 회원 리스트 검색
	public List kakaoList() throws Exception;
	
	// 탈퇴 회원 리스트 검색
	public List withdrawalList() throws Exception;
	
	// 소설 전체 리스트 갯수 검색
	public int bookListCount() throws Exception;
	
	// 소설 전체 리스트 검색
	public List bookList() throws Exception;
	
	// 읽을거다 누른 작품 수 확인
	public int read_wantCount() throws Exception;
	
	// 읽고있다 누른 작품 수 확인
	public int read_ingCount() throws Exception;
	
	// 다읽었다 누른 작품 수 확인
	public int read_endCount() throws Exception;
	
	// 관심없다 누른 작품 수 확인
	public int read_notCount() throws Exception;
	
	// 독서상태별 랭킹 순위 갯수 확인
	public int readRankingCount(String read) throws Exception;
	
	// 독서상태별 랭킹 순위 리스트 검색
	public List readRankList(String read) throws Exception;
	
	// 전체 리뷰수 검색
	public int reviewCount() throws Exception;
	
	// 전체 댓글 리스트 검색
	public List reviewList() throws Exception;
	
	// 삭제하기 위해 선택한 댓글이 있는지 검색
	public int reviewDelCount(ReviewDTO review) throws Exception;
	
	// 선택한 댓글 삭제하기(댓글 내용은 없애고, 클린봇 알림 메시지로 변경)
	public void reviewDel(ReviewDTO review) throws Exception;
	
	// 최다 댓글 닉네임 순위 갯수 검색
	public int reviewNickCount() throws Exception;
	
	// 최다 댓글 소설 순위 갯수 검색
	public int reviewBookCount() throws Exception;
	
	// 최다 댓글 닉네임 순위 검색
	public List reviewNickRank() throws Exception;
	
	// 최다 댓글 소설 순위 검색
	public List reviewBookRank() throws Exception;
	
	// 전체 공지사항 리스트 검색
	public List noticeList() throws Exception;
	
	// 클린봇 삭제 댓글 수 검색
	public int cleanbotCount() throws Exception;
	
	// 클린봇 삭제 댓글 리스트 검색
	public List cleanbotList() throws Exception;
	
	// 장르별 별점 갯수 검색
	public int genreRatingCount(AdminDTO dto) throws Exception;
	
	// 장르별 연령 갯수 검색
	public int genreAgeCount(AdminDTO dto) throws Exception;
	
	// 장르별 연령 플랫폼 링크수 검색
	public AdminDTO genreAgePlatform(AdminDTO dto) throws Exception;
	
	// 키워드 카테고리별 순위 검색
	public List keywordRank(KeywordDTO dto) throws Exception;
	
	// 성별 인기 작품 순위 검색
	public List genderTopRank(int gender) throws Exception;
	
	// link_click 테이블에 저장된 리스트 갯수 확인
	public int link_clickCount() throws Exception;
	
}
