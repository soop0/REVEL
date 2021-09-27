package member;

import java.util.List;

import detail.ReviewDTO;
import member.MemberDTO;

public interface MemberDAOInter {
	
	//회원 정보 가져오기
	public MemberDTO getMember(MemberDTO member) throws Exception;
	
	//회원정보 수정
	//public void memberUpdate(MemberDTO member)throws Exception;

	//회원 닉네임 변경
	public void nickChange(MemberDTO member)throws Exception;
	
	//닉네임 중복체크
	public int nickCheck(String nick) throws Exception;
	
	//설문조사 테이블 조회하기
	public SurveyDTO getSurvey(int idex_member) throws Exception;
	
	//설문조사 선호장르 변경하기
	public void genreChange(SurveyDTO survey) throws Exception;

	//설문조사 volume(1장편 2중편 3단편 4아무거나 int) 변경하기
	public void volumeChange(SurveyDTO survey) throws Exception;
	
	//탈퇴 사유 저장하기 
	public void updateWM(MemberDTO member) throws Exception;
	
	// 내가 쓴 댓글 갯수 불러오기
	public int myReviewCount(int index_member) throws Exception;
	
	// 내가 쓴 댓글 목록 불러오기
	public List myReview(int index_member) throws Exception;
	
	// 삭제하기 위해 선택한 내가 쓴 댓글이 있는지 확인하기
	public int myReviewDelCount(ReviewDTO review) throws Exception;
	
	// 선택한 내가 쓴 댓글 삭제하기
	public void myReviewDel(ReviewDTO review) throws Exception;
	
	//내가 평가한 목록 불러오기
	
	//독서상태 불러오기
	
	//독서상태 변경하기

}
