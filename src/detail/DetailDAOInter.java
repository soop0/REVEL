package detail;

import java.util.List;

public interface DetailDAOInter {
	
	//Detail ���̺��� ��ü ���� �˻�
	public DetailDTO allDetail(int index_book) throws Exception;
	
	//��� ����(count)
	public int reviewCount(int index_book) throws Exception;
	
	//�ش� �Ҽ��� ��� ������ detail Table�� review_num�� �����ϴ� �޼���
	public void review_num(DetailDTO dto) throws Exception;
	
	//��� ��ȸ
	public List<ReviewDTO> reviewList(ReviewDTO dto) throws Exception;
		
	//��� �ۼ�
	public void reviewWrite(ReviewDTO dto) throws Exception;
		
	//��� ����
	public void reviewDelete(int index_review) throws Exception;
	
	// ȸ���� �ش� ��ǰ�� ���� �ο��ߴ��� Ȯ���ϴ� �޼���
	// �� ������ 1, ������ 0
	public int ratingCount(RatingMemberDTO dto) throws Exception;
	
	// ȸ���� �ش� ��ǰ ������ ���� ���̺�(Rating Table)�� �߰��ϴ� �޼���
	public void ratingInsert(RatingMemberDTO dto) throws Exception;
	
	// ȸ���� �ش� ��ǰ ������ ���� ���̺�(Rating Table)�� ������Ʈ�ϴ� �޼���
	public void ratingUpdate(RatingMemberDTO dto) throws Exception;
	
	// ȸ���� �ش� ��ǰ ������ ��ȸ�ϴ� �޼���
	public int ratingSelect(RatingMemberDTO dto) throws Exception;
	
	// �ش� ��ǰ�� ��ü ���� �ο����� ��ȸ�ϴ� �޼���
	public int ratingTotalNum(int index_book) throws Exception;
	
	// �ش� ��ǰ�� ��ü ��� ������ ��ȸ�ϴ� �޼���
	public float ratingAvg(int index_book) throws Exception;
	
	// �� �ش� ��ǰ �� ���� ��ü �ο����� ��� ������ detail Table�� ������Ʈ�ϴ� �޼���
	public void ratingAnl(DetailDTO detail) throws Exception;
	
	//ȸ���� �ش� ��ǰ �������� ���ÿ��� Ȯ���ϴ� �޼���
	public int rstatusCount(ReadStatusDTO dto) throws Exception;
		
	//ȸ���� �ش� ��ǰ �������¸� �������� ���̺�(Read_Status Table)�� �߰��ϴ� �޼���
	public void rstatusInsert(ReadStatusDTO dto) throws Exception;
	
	//ȸ���� �ش� ��ǰ �������� ��ȸ�ϴ� �޼���
	public int rstatusSelect(ReadStatusDTO dto) throws Exception;
	
	//ȸ���� �ش� ��ǰ �������¸� �������� ���̺�(Read_Status Table)�� ������Ʈ�ϴ� �޼���
	public void rstatusUpdate(ReadStatusDTO dto) throws Exception;
	
	//ȸ���� �ش� ��ǰ �������¸� �����Ͽ� �������� ���̺�(Read_Status Table)�� ���� �����ϴ� �޼���
	public void rstatusDelete(ReadStatusDTO dto) throws Exception;
	
	//�ش� ��ǰ�� �������¸� ������ ��ü �ο� ��ȸ�ϴ� �޼���
	public int readTotalNum(int index_book) throws Exception;
	
	//�ش� ��ǰ �������� �߰��ϴ� �޼���
	public void detailRstatusAdd(ReadStatusDTO dto) throws Exception;
	
	//�ش� ��ǰ �������� �����ϴ� �޼���
	public void detailRstatusChange(ReadStatusDTO dto) throws Exception;
	
	//�ش� ��ǰ �������� ����ϴ� �޼���
	public void detailRstatusDelete(ReadStatusDTO dto) throws Exception;
	
	//����ڰ� ���� �÷����� �ش� �Ҽ��� link_click ���̺� �ִ��� Ȯ��
	public int clickCount(LinkClickDTO dto) throws Exception;
		
	//ȸ���� �ش� ��ǰ�� ���̹��ø��� ��ũ�� Ŭ���ߴ��� Ȯ���ϴ� �޼���
	public int naverSelect(LinkClickDTO dto) throws Exception;
		
	//ȸ���� �ش� ��ǰ�� īī�������� ��ũ�� Ŭ���ߴ��� Ȯ���ϴ� �޼���
	public int kakaoSelect(LinkClickDTO dto) throws Exception;
		
	//ȸ���� �ش� ��ǰ ���̹�/īī�� ��ũ Ŭ�� ���θ� ��ũŬ�� ���̺�(Link_Click Table)�� �߰��ϴ� �޼���
	public void clickInsert(LinkClickDTO dto) throws Exception;
		
	//ȸ���� �ش� ��ǰ �÷��� ��ũ �� ������Ʈ�ϴ� �޼���
	public void platformUpdate(LinkClickDTO dto) throws Exception;
	
	//���� �۰� ��ǰ �� Ȯ���ϴ� �޼���
	public int sameWriterCount(int index_book) throws Exception;
		
	//���� �۰� ��ǰ ���� Ȯ���ϴ� �޼���
	public List sameWriterList(int index_book) throws Exception;
	
}
