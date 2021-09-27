package help;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeDAOImpl implements NoticeDAOInter
{
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	// �������� ��ü �� �� 
	@Override
	public int noticeCount() throws Exception 
	{	return mybatis.selectOne("notice.count");	}

	// �������� ��ü �� ����Ʈ�� ����
	@Override
	public List noticeList(NoticeDTO dto) throws Exception 
	{	return mybatis.selectList("notice.noticeList", dto);	}

	// �������� �߰�
	@Override
	public void noticeWrite(NoticeDTO dto) throws Exception
	{	mybatis.insert("notice.noticeWrite", dto);	}

	// �ش� �������� ���� Ȯ��
	@Override
	public NoticeDTO noticeContent(int index_notice) throws Exception
	{	return mybatis.selectOne("notice.noticeContent", index_notice);	}

	// �������� ����
	@Override
	public void noticeUpdate(NoticeDTO dto) throws Exception 
	{	mybatis.update("notice.noticeUpdate", dto);	}

	// �������� ����
	@Override
	public void noticeDelete(int index_notice) throws Exception 
	{	mybatis.delete("notice.noticeDelete", index_notice);	}

	
	

}
