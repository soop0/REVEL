package member;

import java.util.List;

import detail.RatingMemberDTO;

public interface AnalysisDAOInter {
	
	//회원 평점목록 전체 수(count)
	public int totalCount(int index_member) throws Exception;
	
	//회원 평점별 개수 가져오기
	public int ratingCount(RatingMemberDTO rating) throws Exception;
	
	//평점목록의 책 세부정보 같이 가져오기
	public List getRatingDetail(int index_member) throws Exception;
	
	//회원이 평가한 특정 작품 가져오기
	public RatingMemberDTO getRatingList(RatingMemberDTO rating) throws Exception;
		
	//평점 삭제하기
	public void deleteRating(RatingMemberDTO rating) throws Exception;
	
	//평가작품의 키워드와 가중치 목록 가져오기
	public List getKeywordList(int index_member) throws Exception;
	
	//별점 평가시 선호 취향 키워드 누적점수 업데이트
	public void updateMemberScore(RatingMemberDTO rating) throws Exception;

	//독서상태별 카운트 개수
	public int readCount(MemberReadDTO read) throws Exception;
	
	//독서 상태별 목록 가져오기
	public List getReadList(MemberReadDTO read) throws Exception;
	
	//독서 상태 변경
	public void updateRead(MemberReadDTO read) throws Exception;
	
	//독서 상태 삭제
	public void deleteRead(MemberReadDTO read) throws Exception;
	
	//회원분석 선호키워드 top20 가져오기
	public List getMKeyword(int index_member) throws Exception;
	
	//회원분석 선호키워드 캐릭터-카테고리 6개 가져오기
	public List getCKeyword_1(int index_member)throws Exception;
	
	//회원분석 선호키워드 분위기-카테고리 6개 가져오기
	public List getCKeyword_2(int index_member)throws Exception;
	
	//회원분석 선호키워드 소재-카테고리 6개 가져오기
	public List getCKeyword_3(int index_member)throws Exception;
	
	//회원분석 선호키워드 독자반응-카테고리 3개 가져오기
	public List getCKeyword_4(int index_member)throws Exception;
	
	//회원분석 선호장르 장르별 (다읽었다&평점) 수 가져오기
	public int genreCount(MemberReadDTO read) throws Exception;
		
	//회원분석 선호장르 댓글 장르별 수 가져오기
	public int reviewCount(MemberReadDTO read) throws Exception;

}
