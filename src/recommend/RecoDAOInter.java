package recommend;

import java.util.List;
import java.util.Map;

import detail.DetailDTO;
import member.SurveyDTO;

public interface RecoDAOInter {
	
	//�α����� ȸ����ȣ(index_member)�� �־ ȸ������м����̺�(member_keyword_anal_ȸ����ȣ)�� ��ȸ�Ѵ�. 
	public List <MemKeyAnalDTO> getMemKeyAnal(int index_member) throws Exception;
	
	//��ǰ��Ű����м����̺�(keyword_anal)�� ��ȸ�Ѵ�.
	public List <KeyAnalDTO> getKeyAnal() throws Exception;
	
	//��õ�� ��ǰ��ȣ(index_book)����Ʈ�� �־ ��ǰ������ �޾ƿ´�.
	public List <DetailDTO> getRecoBook(List recoList) throws Exception;
	
	//�м��� ����� ������ �ӽ����̺�(temp_anal_index_member)�� �����Ͱ� �ִ��� Ȯ���Ѵ�.
	public int isTempAnal(int index_member) throws Exception;
	
	//�м��� ����� ������ �ӽ����̺�(temp_anal_index_member)�� �����Ѵ�.
	public void createTempAnal(int index_member) throws Exception;
	
	//�м��� ����� �ӽ����̺�(temp_anal_index_member)�� insert�Ѵ�.
	public void insertTempAnal(List tempAnalList) throws Exception;
	
	//�ӽ����̺��� ������ ������������ ��õ����Ʈ�� �޾ƿ´�.
	public List getRecoList(int number) throws Exception;
	
	//ȸ��Ű���� ��������(����)�� ��ǰ�� Ű���� ����ġ�� ���Ѵ�.
	public Map setKeyAnalValue(int index_member) throws Exception;
	
	/* 
	 * ��õ����Ʈ���� ȸ����ȣ�� ���� ������ ��ǰ���(read Table�� ���ɾ���(0), ���о���(3), 
	 * rating Table�� ȸ��(index_member)�� ���� �� ��ǰ��)
	 */
	public List getOutList(int index_member) throws Exception;
	
	//survey ���̺��� ȸ��(index_member)�� ������ �ҷ��´�.
	public SurveyDTO getSurvey(int index_member) throws Exception;
	
	//TempAnal ���̺��� �����͵��� �����.
	public void clearValue(int index_member) throws Exception;
}
