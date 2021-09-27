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
	
	// 공지사항 전체 글 수 
	@Override
	public int noticeCount() throws Exception 
	{	return mybatis.selectOne("notice.count");	}

	// 공지사항 전체 글 리스트로 리턴
	@Override
	public List noticeList(NoticeDTO dto) throws Exception 
	{	return mybatis.selectList("notice.noticeList", dto);	}

	// 공지사항 추가
	@Override
	public void noticeWrite(NoticeDTO dto) throws Exception
	{	mybatis.insert("notice.noticeWrite", dto);	}

	// 해당 공지사항 내용 확인
	@Override
	public NoticeDTO noticeContent(int index_notice) throws Exception
	{	return mybatis.selectOne("notice.noticeContent", index_notice);	}

	// 공지사항 수정
	@Override
	public void noticeUpdate(NoticeDTO dto) throws Exception 
	{	mybatis.update("notice.noticeUpdate", dto);	}

	// 공지사항 삭제
	@Override
	public void noticeDelete(int index_notice) throws Exception 
	{	mybatis.delete("notice.noticeDelete", index_notice);	}

	
	

}
