package genre;

import java.util.List;
import detail.DetailDTO;

public interface GenreDAOInter {
	
	// ī�װ� : �帣�� ��ü ����Ʈ
	public List kindList(DetailDTO dto) throws Exception;
	
	// ī�װ� : �帣�� ��ü ����Ʈ ��(count)
	public int kindCount(int genre) throws Exception;
	
	// ī�װ� : ��ü ����Ʈ ��(�帣 ���� x - count)
	public int totalCount() throws Exception;
	
	// ī�װ� : ��ü ����Ʈ(�帣 ���� x)
	public List totalList(DetailDTO dto) throws Exception;
}
