package member;

import java.util.List;

import detail.ReviewDTO;
import member.MemberDTO;

public interface MemberDAOInter {
	
	//ȸ�� ���� ��������
	public MemberDTO getMember(MemberDTO member) throws Exception;
	
	//ȸ������ ����
	//public void memberUpdate(MemberDTO member)throws Exception;

	//ȸ�� �г��� ����
	public void nickChange(MemberDTO member)throws Exception;
	
	//�г��� �ߺ�üũ
	public int nickCheck(String nick) throws Exception;
	
	//�������� ���̺� ��ȸ�ϱ�
	public SurveyDTO getSurvey(int idex_member) throws Exception;
	
	//�������� ��ȣ�帣 �����ϱ�
	public void genreChange(SurveyDTO survey) throws Exception;

	//�������� volume(1���� 2���� 3���� 4�ƹ��ų� int) �����ϱ�
	public void volumeChange(SurveyDTO survey) throws Exception;
	
	//Ż�� ���� �����ϱ� 
	public void updateWM(MemberDTO member) throws Exception;
	
	// ���� �� ��� ���� �ҷ�����
	public int myReviewCount(int index_member) throws Exception;
	
	// ���� �� ��� ��� �ҷ�����
	public List myReview(int index_member) throws Exception;
	
	// �����ϱ� ���� ������ ���� �� ����� �ִ��� Ȯ���ϱ�
	public int myReviewDelCount(ReviewDTO review) throws Exception;
	
	// ������ ���� �� ��� �����ϱ�
	public void myReviewDel(ReviewDTO review) throws Exception;
	
	//���� ���� ��� �ҷ�����
	
	//�������� �ҷ�����
	
	//�������� �����ϱ�

}
