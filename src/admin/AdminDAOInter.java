package admin;

import java.util.List;

import detail.ReviewDTO;
import ranking.KeywordDTO;

public interface AdminDAOInter 
{
	// ��ü ȸ���� �˻�
	public int memberCount() throws Exception;
	
	// ���̹� ȸ���� �˻�
	public int naverCount() throws Exception;
	
	// īī�� ȸ���� �˻�
	public int kakaoCount() throws Exception;
	
	// Ż�� ȸ���� �˻�
	public int withdrawalCount() throws Exception;
	
	// ���� ȸ���� �˻�
	public int manCount() throws Exception;
	
	// ���� ȸ���� �˻�
	public int womanCount() throws Exception;
	
	// 10�� ȸ���� �˻�
	public int count10() throws Exception;
	
	// 20�� ȸ���� �˻�
	public int count20() throws Exception;
	
	// 30�� ȸ���� �˻�
	public int count30() throws Exception;
	
	// 40�� ȸ���� �˻�
	public int count40() throws Exception;
	
	// 50�� �̻� ȸ���� �˻�
	public int count50() throws Exception;
	
	// ��̰��� Ż������� �˻�
	public int interestLost(String interestLost) throws Exception;
	
	// ���������� Ż������� �˻�
	public int contentNot(String contentNot) throws Exception;
	
	// Ÿ�����̿� Ż������� �˻�
	public int serviceChange(String serviceChange) throws Exception;
	
	// ���񽺺��� Ż������� �˻�
	public int serviceNot(String serviceNot) throws Exception;
	
	// ��Ÿ Ż������� �˻�
	public int etc(WithdrawalDTO dto) throws Exception;
	
	// ��ü ȸ�� ����Ʈ �˻�
	public List memberList() throws Exception;
	
	// ���̹� ȸ�� ����Ʈ �˻�
	public List naverList() throws Exception;
	
	// īī�� ȸ�� ����Ʈ �˻�
	public List kakaoList() throws Exception;
	
	// Ż�� ȸ�� ����Ʈ �˻�
	public List withdrawalList() throws Exception;
	
	// �Ҽ� ��ü ����Ʈ ���� �˻�
	public int bookListCount() throws Exception;
	
	// �Ҽ� ��ü ����Ʈ �˻�
	public List bookList() throws Exception;
	
	// �����Ŵ� ���� ��ǰ �� Ȯ��
	public int read_wantCount() throws Exception;
	
	// �а��ִ� ���� ��ǰ �� Ȯ��
	public int read_ingCount() throws Exception;
	
	// ���о��� ���� ��ǰ �� Ȯ��
	public int read_endCount() throws Exception;
	
	// ���ɾ��� ���� ��ǰ �� Ȯ��
	public int read_notCount() throws Exception;
	
	// �������º� ��ŷ ���� ���� Ȯ��
	public int readRankingCount(String read) throws Exception;
	
	// �������º� ��ŷ ���� ����Ʈ �˻�
	public List readRankList(String read) throws Exception;
	
	// ��ü ����� �˻�
	public int reviewCount() throws Exception;
	
	// ��ü ��� ����Ʈ �˻�
	public List reviewList() throws Exception;
	
	// �����ϱ� ���� ������ ����� �ִ��� �˻�
	public int reviewDelCount(ReviewDTO review) throws Exception;
	
	// ������ ��� �����ϱ�(��� ������ ���ְ�, Ŭ���� �˸� �޽����� ����)
	public void reviewDel(ReviewDTO review) throws Exception;
	
	// �ִ� ��� �г��� ���� ���� �˻�
	public int reviewNickCount() throws Exception;
	
	// �ִ� ��� �Ҽ� ���� ���� �˻�
	public int reviewBookCount() throws Exception;
	
	// �ִ� ��� �г��� ���� �˻�
	public List reviewNickRank() throws Exception;
	
	// �ִ� ��� �Ҽ� ���� �˻�
	public List reviewBookRank() throws Exception;
	
	// ��ü �������� ����Ʈ �˻�
	public List noticeList() throws Exception;
	
	// Ŭ���� ���� ��� �� �˻�
	public int cleanbotCount() throws Exception;
	
	// Ŭ���� ���� ��� ����Ʈ �˻�
	public List cleanbotList() throws Exception;
	
	// �帣�� ���� ���� �˻�
	public int genreRatingCount(AdminDTO dto) throws Exception;
	
	// �帣�� ���� ���� �˻�
	public int genreAgeCount(AdminDTO dto) throws Exception;
	
	// �帣�� ���� �÷��� ��ũ�� �˻�
	public AdminDTO genreAgePlatform(AdminDTO dto) throws Exception;
	
	// Ű���� ī�װ��� ���� �˻�
	public List keywordRank(KeywordDTO dto) throws Exception;
	
	// ���� �α� ��ǰ ���� �˻�
	public List genderTopRank(int gender) throws Exception;
	
	// link_click ���̺� ����� ����Ʈ ���� Ȯ��
	public int link_clickCount() throws Exception;
	
}
