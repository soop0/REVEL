package member;

import java.util.List;

public interface LogonDAOInter {
	
	// 플랫폼에서 받아온 아이디와 status를 member 테이블의 platform_id와 status를 비교한다.
	// 기존회원이 있으면 1을 리턴받고, 없으면 0을 리턴한다.
	public int isMemberCount(MemberDTO member) throws Exception;
	
	//플랫폼에서 받아온 아이디와 status를 member 테이블의 platform_id와 status를 비교한다.
	//기존회원이면 index_member을 반환한다.
	public int isMember(MemberDTO member) throws Exception;
	
	// 관리자 확인
	public String isAdmin() throws Exception;
	
	//member 테이블에 insert한다.
	public void insertMember(MemberDTO member) throws Exception;
	
	//member 테이블에서 원하는 회원의 정보를 가져온다.
	public MemberDTO getMember(int index_member) throws Exception;
	
	//survey 테이블에 insert한다.
	public void insertSurvey(SurveyDTO survey) throws Exception;
	
	//회원 생년월일을 확인한 뒤, 맞으면 member테이블의 status를 탈퇴상태(50)로 변경하고 1을 반환한다.
	public int deleteMember(MemberDTO member) throws Exception;
	
	// 회원가입 시 설문조사에 참여했는지 확인한다. (참여 : 1, 미참여 : 0)
	public int memberSurveyCount(int index_member) throws Exception;
	
	// 탈퇴회원이 재가입시 설문조사에 참여했으면 새로 조사한 설문조사로 업데이트 시킨다.
	public void updateSurvey(SurveyDTO survey) throws Exception;
	
	// 회원가입이 완료되면 자동으로 DB에 회원별 Read_index_member Table을 생성한다.
	public void read_index_memberCreateTable(int index_member) throws Exception;
	
	// 회원가입이 완료되면 자동으로 DB에 회원별 Read_index_member_Sequence을 생성한다.
	public void read_index_memberSequence(int index_member) throws Exception;
	
	// 회원가입이 완료되면 자동으로 DB에 회원별 Member_keyword_anal_index_member Table을 생성한다.
	public void member_keyword_anal_index_memberCreateTable(int index_member) throws Exception;
	
	// Member_keyword_anal_index_member Table에 키워드 87행 기본값 삽입
	public void member_keyword_anal_index_memberInsert(int index_member) throws Exception;
}
