package member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class LogonDAOImple implements LogonDAOInter
{	
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	// 플랫폼에서 받아온 아이디를 member 테이블의 platform_id와 비교한다.
	// 기존회원이 있는 수만큼 count값을 리턴받고, 없으면 0을 반환한다.
	@Override
	public int isMemberCount(MemberDTO member) throws Exception 
	{	return mybatis.selectOne("logon.isMemberCount", member);	}
	
	//플랫폼에서 받아온 아이디를 member 테이블의 platform_id와 비교한다.
	//기존회원과 일치하면 index_member, 없으면 0을 반환한다.
	@Override
	public int isMember(MemberDTO member) throws Exception 
	{	return mybatis.selectOne("logon.isMember", member);	}
	
	// 관리자인지 비교한다.
	@Override
	public String isAdmin() throws Exception 
	{	return mybatis.selectOne("logon.isAdmin");	}
	
	//member 테이블에 insert한다.
	@Override
	public void insertMember(MemberDTO member) throws Exception 
	{	mybatis.insert("logon.insertMember", member);	}
	
	//member 테이블에서 원하는 회원의 정보를 가져온다.
	@Override
	public MemberDTO getMember(int index_member) throws Exception 
	{	return mybatis.selectOne("logon.getMember", index_member);	}
	
	//survey 테이블에 insert한다.
	@Override
	public void insertSurvey(SurveyDTO survey) throws Exception 
	{	mybatis.insert("logon.insertSurvey", survey);	}
	
	//회원 생년월일을 확인한 뒤, 맞으면 member테이블의 status를 탈퇴상태(50)로 변경하고 1을 반환한다.
	@Override
	public int deleteMember(MemberDTO member) throws Exception
	{
		int check = mybatis.selectOne("logon.checkBirthday", member);
		if(check == 1)
		{	mybatis.update("logon.updateMStatus", member);	}
		return check;
	}

	// 회원가입 시 설문조사에 참여했는지 확인한다. (참여 : 1, 미참여 : 0)
	@Override
	public int memberSurveyCount(int index_member) throws Exception
	{	return mybatis.selectOne("logon.memberSurveyCount", index_member);	}

	// 탈퇴회원이 재가입시 설문조사에 참여했으면 새로 조사한 설문조사로 업데이트 시킨다.
	@Override
	public void updateSurvey(SurveyDTO survey) throws Exception 
	{	mybatis.update("logon.updateSurvey", survey);	}

	// 회원가입이 완료되면 자동으로 DB에 회원별 Read_index_member Table을 생성한다.
	@Override
	public void read_index_memberCreateTable(int index_member) throws Exception
	{	mybatis.insert("logon.read_index_memberCreateTable", index_member);	}
	
	// 회원가입이 완료되면 자동으로 DB에 회원별 Read_index_member Sequence을 생성한다.
	@Override
	public void read_index_memberSequence(int index_member) throws Exception
	{	mybatis.insert("logon.read_index_memberSequence", index_member);	}

	// 회원가입이 완료되면 자동으로 DB에 회원별 Member_keyword_anal_index_member Table을 생성한다.
	@Override
	public void member_keyword_anal_index_memberCreateTable(int index_member) throws Exception 
	{	mybatis.insert("logon.member_keyword_anal_index_memberCreateTable", index_member);	}
	
	//생성된 Member_keyword_anal_index_member Table에 기본 키워드 87행 삽입
	@Override
	public void member_keyword_anal_index_memberInsert(int index_member) throws Exception 
	{	mybatis.insert("logon.member_keyword_anal_index_memberInsert", index_member);	}
	
	
}
