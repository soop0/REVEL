package help;

import java.util.List;

public interface NoticeDAOInter 
{
	// 공지사항 전체 글 수(count)
	public int noticeCount() throws Exception;
	
	// 공지사항 전체 글 리스트
	public List noticeList(NoticeDTO dto) throws Exception;
	
	// 공지사항 작성
	public void noticeWrite(NoticeDTO dto) throws Exception;
	
	// 공지사항 내용 확인
	public NoticeDTO noticeContent(int index_notice) throws Exception;
	
	// 공지사항 수정
	public void noticeUpdate(NoticeDTO dto) throws Exception;
	
	// 공지사항 삭제
	public void noticeDelete(int index_notice) throws Exception;
}
