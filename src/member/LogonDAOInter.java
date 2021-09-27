package member;

import java.util.List;

public interface LogonDAOInter {
	
	// �÷������� �޾ƿ� ���̵�� status�� member ���̺��� platform_id�� status�� ���Ѵ�.
	// ����ȸ���� ������ 1�� ���Ϲް�, ������ 0�� �����Ѵ�.
	public int isMemberCount(MemberDTO member) throws Exception;
	
	//�÷������� �޾ƿ� ���̵�� status�� member ���̺��� platform_id�� status�� ���Ѵ�.
	//����ȸ���̸� index_member�� ��ȯ�Ѵ�.
	public int isMember(MemberDTO member) throws Exception;
	
	// ������ Ȯ��
	public String isAdmin() throws Exception;
	
	//member ���̺� insert�Ѵ�.
	public void insertMember(MemberDTO member) throws Exception;
	
	//member ���̺��� ���ϴ� ȸ���� ������ �����´�.
	public MemberDTO getMember(int index_member) throws Exception;
	
	//survey ���̺� insert�Ѵ�.
	public void insertSurvey(SurveyDTO survey) throws Exception;
	
	//ȸ�� ��������� Ȯ���� ��, ������ member���̺��� status�� Ż�����(50)�� �����ϰ� 1�� ��ȯ�Ѵ�.
	public int deleteMember(MemberDTO member) throws Exception;
	
	// ȸ������ �� �������翡 �����ߴ��� Ȯ���Ѵ�. (���� : 1, ������ : 0)
	public int memberSurveyCount(int index_member) throws Exception;
	
	// Ż��ȸ���� �簡�Խ� �������翡 ���������� ���� ������ ��������� ������Ʈ ��Ų��.
	public void updateSurvey(SurveyDTO survey) throws Exception;
	
	// ȸ�������� �Ϸ�Ǹ� �ڵ����� DB�� ȸ���� Read_index_member Table�� �����Ѵ�.
	public void read_index_memberCreateTable(int index_member) throws Exception;
	
	// ȸ�������� �Ϸ�Ǹ� �ڵ����� DB�� ȸ���� Read_index_member_Sequence�� �����Ѵ�.
	public void read_index_memberSequence(int index_member) throws Exception;
	
	// ȸ�������� �Ϸ�Ǹ� �ڵ����� DB�� ȸ���� Member_keyword_anal_index_member Table�� �����Ѵ�.
	public void member_keyword_anal_index_memberCreateTable(int index_member) throws Exception;
	
	// Member_keyword_anal_index_member Table�� Ű���� 87�� �⺻�� ����
	public void member_keyword_anal_index_memberInsert(int index_member) throws Exception;
}
