package member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class LogonDAOImple implements LogonDAOInter
{	
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	// �÷������� �޾ƿ� ���̵� member ���̺��� platform_id�� ���Ѵ�.
	// ����ȸ���� �ִ� ����ŭ count���� ���Ϲް�, ������ 0�� ��ȯ�Ѵ�.
	@Override
	public int isMemberCount(MemberDTO member) throws Exception 
	{	return mybatis.selectOne("logon.isMemberCount", member);	}
	
	//�÷������� �޾ƿ� ���̵� member ���̺��� platform_id�� ���Ѵ�.
	//����ȸ���� ��ġ�ϸ� index_member, ������ 0�� ��ȯ�Ѵ�.
	@Override
	public int isMember(MemberDTO member) throws Exception 
	{	return mybatis.selectOne("logon.isMember", member);	}
	
	// ���������� ���Ѵ�.
	@Override
	public String isAdmin() throws Exception 
	{	return mybatis.selectOne("logon.isAdmin");	}
	
	//member ���̺� insert�Ѵ�.
	@Override
	public void insertMember(MemberDTO member) throws Exception 
	{	mybatis.insert("logon.insertMember", member);	}
	
	//member ���̺��� ���ϴ� ȸ���� ������ �����´�.
	@Override
	public MemberDTO getMember(int index_member) throws Exception 
	{	return mybatis.selectOne("logon.getMember", index_member);	}
	
	//survey ���̺� insert�Ѵ�.
	@Override
	public void insertSurvey(SurveyDTO survey) throws Exception 
	{	mybatis.insert("logon.insertSurvey", survey);	}
	
	//ȸ�� ��������� Ȯ���� ��, ������ member���̺��� status�� Ż�����(50)�� �����ϰ� 1�� ��ȯ�Ѵ�.
	@Override
	public int deleteMember(MemberDTO member) throws Exception
	{
		int check = mybatis.selectOne("logon.checkBirthday", member);
		if(check == 1)
		{	mybatis.update("logon.updateMStatus", member);	}
		return check;
	}

	// ȸ������ �� �������翡 �����ߴ��� Ȯ���Ѵ�. (���� : 1, ������ : 0)
	@Override
	public int memberSurveyCount(int index_member) throws Exception
	{	return mybatis.selectOne("logon.memberSurveyCount", index_member);	}

	// Ż��ȸ���� �簡�Խ� �������翡 ���������� ���� ������ ��������� ������Ʈ ��Ų��.
	@Override
	public void updateSurvey(SurveyDTO survey) throws Exception 
	{	mybatis.update("logon.updateSurvey", survey);	}

	// ȸ�������� �Ϸ�Ǹ� �ڵ����� DB�� ȸ���� Read_index_member Table�� �����Ѵ�.
	@Override
	public void read_index_memberCreateTable(int index_member) throws Exception
	{	mybatis.insert("logon.read_index_memberCreateTable", index_member);	}
	
	// ȸ�������� �Ϸ�Ǹ� �ڵ����� DB�� ȸ���� Read_index_member Sequence�� �����Ѵ�.
	@Override
	public void read_index_memberSequence(int index_member) throws Exception
	{	mybatis.insert("logon.read_index_memberSequence", index_member);	}

	// ȸ�������� �Ϸ�Ǹ� �ڵ����� DB�� ȸ���� Member_keyword_anal_index_member Table�� �����Ѵ�.
	@Override
	public void member_keyword_anal_index_memberCreateTable(int index_member) throws Exception 
	{	mybatis.insert("logon.member_keyword_anal_index_memberCreateTable", index_member);	}
	
	//������ Member_keyword_anal_index_member Table�� �⺻ Ű���� 87�� ����
	@Override
	public void member_keyword_anal_index_memberInsert(int index_member) throws Exception 
	{	mybatis.insert("logon.member_keyword_anal_index_memberInsert", index_member);	}
	
	
}
