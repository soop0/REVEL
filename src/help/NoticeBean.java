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
 * 	<noticeBean Ŭ����>
 *   	- myBatis�� �̿��Ͽ� DB���� noticeSQL ���ǿ� ���� �ҷ��� �������� ���� ���� view�� �����ֱ� ���� ó���ϴ� Ŭ����
 *      - �������� ��ȸ, �߰�, ����, ���� ���
 *      - �߰�, ����, ������ �������� ���� ����
 */

@Controller
@RequestMapping("/help/")
public class NoticeBean 
{
	// NoticeDTO ����
	@Autowired
	private NoticeDTO notice = null;
	
	// noticeDAOImpl Ŭ���� ����(������)
	@Autowired
	private NoticeDAOInter noticeImpl = null;
	
	// �������� ��ü ����Ʈ ��ȸ
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
	
	// �������� �߰� ���(������)
	@RequestMapping("notice/write.rv")
	public String noticeWrite(Model model) throws Exception
	{	return "/help/noticeWriteForm";	}
	
	// �߰��� ���������� DB�� ���� �� view�� �߰��� ���·� ��ȸ�ؼ� �����ش�.(������)
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
	
	// �������� ������ ��ȸ�Ͽ� view�� ���
	@RequestMapping("notice/content.rv")
	public String noticeContent(HttpServletRequest request, Model model) throws Exception
	{
		/*
		 *  index_notice : ������ȣ
		 *  notice : �ش� �������� 
		 */
		
		int index_notice = Integer.parseInt(request.getParameter("noticeNum"));
		notice = noticeImpl.noticeContent(index_notice);
		notice.setPageNum(request.getParameter("pageNum"));
				
		model.addAttribute("notice", notice);
		
		return "/help/content";
	}
	
	// �������� ���� �� ���� ����(������)
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
	
	// �������� ���� �� ���� ������ �� DB�� �����Ͽ� view�� ������ ���·� �����ش�.(������)
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
	
	// �������� ����(������) : �ش� �������� ���� �� ���� ����
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
	
	// �����ϴ� ���� ������
	@RequestMapping("qna.rv")
	public String faq(HttpServletRequest request, Model model) throws Exception
	{	return "/help/faq";	}
}
