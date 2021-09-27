package help;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 	<noticeBean 클래스>
 *   	- myBatis를 이용하여 DB에서 noticeSQL 조건에 따라 불러온 공지사항 관련 값을 view에 보여주기 위해 처리하는 클래스
 *      - 공지사항 조회, 추가, 수정, 삭제 기능
 *      - 추가, 수정, 삭제는 관리자일 때만 가능
 */

@Controller
@RequestMapping("/help/")
public class NoticeBean 
{
	// NoticeDTO 생성
	@Autowired
	private NoticeDTO notice = null;
	
	// noticeDAOImpl 클래스 생성(다형성)
	@Autowired
	private NoticeDAOInter noticeImpl = null;
	
	// 공지사항 전체 리스트 조회
	@RequestMapping("notice.rv")
	public String notice(HttpServletRequest request, Model model) throws Exception
	{
		int pageSize = 15;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)
		{	pageNum = "1";	}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List noticeList = null;

		notice.setCurrentPage(currentPage);
		notice.setEndRow(endRow);
		notice.setPageNum(pageNum);
		notice.setPageSize(pageSize);
		notice.setStartRow(startRow);
		
		count = noticeImpl.noticeCount();
		if(count >0)
		{	noticeList = noticeImpl.noticeList(notice);	}
		number = count - (currentPage-1)*pageSize;
		
		notice.setCount(count);
		model.addAttribute("page", notice);
		model.addAttribute("number", number);
		model.addAttribute("noticeList", noticeList);
		
		return "/help/notice";
	}
	
	// 공지사항 추가 기능(관리자)
	@RequestMapping("notice/write.rv")
	public String noticeWrite(Model model) throws Exception
	{	return "/help/noticeWriteForm";	}
	
	// 추가할 공지사항을 DB에 저장 후 view에 추가된 상태로 조회해서 보여준다.(관리자)
	@RequestMapping(value="/noticeWritePro.rv", method = RequestMethod.POST)
	public String noticeWritePro(HttpServletRequest request) throws Exception
	{
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setWriter(request.getParameter("writer"));
		noticeImpl.noticeWrite(dto);
		return "redirect:/help/notice.rv";
	}
	
	// 공지사항 내용을 조회하여 view에 출력
	@RequestMapping("notice/content.rv")
	public String noticeContent(HttpServletRequest request, Model model) throws Exception
	{
		/*
		 *  index_notice : 공지번호
		 *  notice : 해당 공지사항 
		 */
		
		int index_notice = Integer.parseInt(request.getParameter("noticeNum"));
		notice = noticeImpl.noticeContent(index_notice);
		notice.setPageNum(request.getParameter("pageNum"));
				
		model.addAttribute("notice", notice);
		
		return "/help/content";
	}
	
	// 공지사항 제목 및 내용 수정(관리자)
	@RequestMapping("notice/update.rv")
	public String noticeUpdate(HttpServletRequest request, Model model) throws Exception
	{	
		int index_notice = Integer.parseInt(request.getParameter("noticeNum"));
		notice = noticeImpl.noticeContent(index_notice);
		if(request.getParameter("pageNum").equals(null))
		{	notice.setPageNum("");	}
		else
		{	notice.setPageNum(request.getParameter("pageNum"));	}
		model.addAttribute("notice", notice);
		return "/help/noticeUpdateForm";	
	}
	
	// 공지사항 제목 및 내용 수정한 후 DB에 저장하여 view에 수정된 상태로 보여준다.(관리자)
	@RequestMapping(value="/noticeUpdatePro.rv", method = RequestMethod.POST)
	public String noticeUpdatePro(HttpServletRequest request) throws Exception
	{
		NoticeDTO dto = new NoticeDTO();
		dto.setIndex_notice(Integer.parseInt(request.getParameter("noticeNum")));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setWriter(request.getParameter("writer"));
		noticeImpl.noticeUpdate(dto);
		
		if(request.getParameter("pageNum").equals(""))
		{	return "redirect:/manage/board/notice.rv";	}
		return "redirect:/help/notice/content.rv?noticeNum="
				+request.getParameter("noticeNum")
				+"&pageNum="
				+request.getParameter("pageNum");
	}
	
	// 공지사항 삭제(관리자) : 해당 공지사항 제목 및 내용 삭제
	@RequestMapping("notice/delete.rv")
	public String noticeDelete(HttpServletRequest request, Model model) throws Exception
	{
		int index_notice = Integer.parseInt(request.getParameter("noticeNum"));
		String pageNum = request.getParameter("pageNum");
		int check = 0;
		
		if(index_notice > 0)
		{
			noticeImpl.noticeDelete(index_notice);
			check = 1;
		}
		
		model.addAttribute("check", check);
		model.addAttribute("pageNum", pageNum);
		
		return "/help/noticeDelete";
	}
	
	// 자주하는 질문 페이지
	@RequestMapping("qna.rv")
	public String faq(HttpServletRequest request, Model model) throws Exception
	{	return "/help/faq";	}
}
