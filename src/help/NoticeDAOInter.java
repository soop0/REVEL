package help;

import java.util.List;

public interface NoticeDAOInter 
{
	// �������� ��ü �� ��(count)
	public int noticeCount() throws Exception;
	
	// �������� ��ü �� ����Ʈ
	public List noticeList(NoticeDTO dto) throws Exception;
	
	// �������� �ۼ�
	public void noticeWrite(NoticeDTO dto) throws Exception;
	
	// �������� ���� Ȯ��
	public NoticeDTO noticeContent(int index_notice) throws Exception;
	
	// �������� ����
	public void noticeUpdate(NoticeDTO dto) throws Exception;
	
	// �������� ����
	public void noticeDelete(int index_notice) throws Exception;
}
