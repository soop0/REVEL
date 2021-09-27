package ranking;

import java.util.List;

import detail.DetailDTO;

public interface RankingDAOInter {
	
	// ������ �Ⱓ���� ��ũŬ������ ����ϰ�, ���� ������ ���� ��ŷ����Ʈ�� �޾ƿ´�.
	public List getRankList(RankingDTO ranking) throws Exception;;
	
	// ��ŷ����Ʈ(index_book)����Ʈ�� �־ ��ǰ����(DetailDTO)�� �޾ƿ´�
	public List getBook(List rankList) throws Exception;
	
	//��ü ��ũŬ������ ����ϰ�, ���� ������ ���� ��ŷ����Ʈ�� �޾ƿ´�.
	public List getRankList() throws Exception;
	
	//�ϰ� �Ⱓ�� �����Ѵ�. (���� �Ϸ絿��)
	public RankingDTO setDaily() throws Exception;
	
	//�ְ� �Ⱓ�� �����Ѵ�. (������ �Ͽ��� ~ ������ �����)
	public RankingDTO setWeekly() throws Exception;
	
	//���� �Ⱓ�� �����Ѵ�. (���� 1�� ~ ���� ��������)
	public RankingDTO setMonthly() throws Exception;

}
