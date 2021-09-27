package member;

import java.util.List;

import detail.RatingMemberDTO;

public interface AnalysisDAOInter {
	
	//ȸ�� ������� ��ü ��(count)
	public int totalCount(int index_member) throws Exception;
	
	//ȸ�� ������ ���� ��������
	public int ratingCount(RatingMemberDTO rating) throws Exception;
	
	//��������� å �������� ���� ��������
	public List getRatingDetail(int index_member) throws Exception;
	
	//ȸ���� ���� Ư�� ��ǰ ��������
	public RatingMemberDTO getRatingList(RatingMemberDTO rating) throws Exception;
		
	//���� �����ϱ�
	public void deleteRating(RatingMemberDTO rating) throws Exception;
	
	//����ǰ�� Ű����� ����ġ ��� ��������
	public List getKeywordList(int index_member) throws Exception;
	
	//���� �򰡽� ��ȣ ���� Ű���� �������� ������Ʈ
	public void updateMemberScore(RatingMemberDTO rating) throws Exception;

	//�������º� ī��Ʈ ����
	public int readCount(MemberReadDTO read) throws Exception;
	
	//���� ���º� ��� ��������
	public List getReadList(MemberReadDTO read) throws Exception;
	
	//���� ���� ����
	public void updateRead(MemberReadDTO read) throws Exception;
	
	//���� ���� ����
	public void deleteRead(MemberReadDTO read) throws Exception;
	
	//ȸ���м� ��ȣŰ���� top20 ��������
	public List getMKeyword(int index_member) throws Exception;
	
	//ȸ���м� ��ȣŰ���� ĳ����-ī�װ� 6�� ��������
	public List getCKeyword_1(int index_member)throws Exception;
	
	//ȸ���м� ��ȣŰ���� ������-ī�װ� 6�� ��������
	public List getCKeyword_2(int index_member)throws Exception;
	
	//ȸ���м� ��ȣŰ���� ����-ī�װ� 6�� ��������
	public List getCKeyword_3(int index_member)throws Exception;
	
	//ȸ���м� ��ȣŰ���� ���ڹ���-ī�װ� 3�� ��������
	public List getCKeyword_4(int index_member)throws Exception;
	
	//ȸ���м� ��ȣ�帣 �帣�� (���о���&����) �� ��������
	public int genreCount(MemberReadDTO read) throws Exception;
		
	//ȸ���м� ��ȣ�帣 ��� �帣�� �� ��������
	public int reviewCount(MemberReadDTO read) throws Exception;

}
