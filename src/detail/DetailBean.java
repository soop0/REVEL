package detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.AnalysisDAOInter;
import ranking.KeywordDAOInter;

/*
detail.DetailDTO => detail
detail.ReviewDTO => review
detail.Review_LikeDTO => review_like
detail.Read_index_memberDTO => read_member
detail.ReadStatusDTO => readStatus
detail.LinkClickDTO => linkClick
*/

@Controller
public class DetailBean 
{	
	@Autowired
	private DetailDAOInter detailImpl = null;
	
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	@Autowired
	private ReviewDTO review = null;
	
	@Autowired
	private RatingMemberDTO ratingMember = null;
	
	@Autowired
	private ReadStatusDTO readStatus = null;

	@Autowired
	private LinkClickDTO linkClick = null;
	
	//KeywordDAOImple Ŭ���� ����
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	@RequestMapping("detail.rv")
	public String detail(HttpServletRequest request, Model model, HttpSession session) throws Exception 
	{
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		DetailDTO allDetailBook = detailImpl.allDetail(index_book); 
		
		// ��� �Խ��� �ۼ�
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) 
		{	pageNum = "1";	}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		review.setStartRow(startRow);
		review.setEndRow(endRow);
		review.setIndex_book(index_book);
		
		// ��� ��ȸ
		List<ReviewDTO> rvList = null;
		
		count = detailImpl.reviewCount(review.getIndex_book());
		if(count > 0) 
		{
			rvList = detailImpl.reviewList(review); //������ index_book �޾�����, �Ķ���Ͱ� reviewDTO�� ����Ǿ� review�� 
			allDetailBook.setReview_num(count);
			detailImpl.review_num(allDetailBook);
		}
		number = count - (currentPage-1)*pageSize;
		
		// ���� ��ȸ
		int rating = 0;				// rating = ����
		int ratingCount = 0;		// rating = �������� Ȯ��
		
		//�������� ��ȸ
		int read = -1; 			//	read = ��������(�а�ʹ�-1, �а��ִ�-2, ���о���-3, ���ɾ���-0)
		int readCount = 0;   	//	readCount = �������� ���� ���� Ȯ��
				
		//��ũ Ŭ�� ��ȸ
		int naver = 0;		//	naver = ���̹� ��ũ Ŭ�� ��
		int kakao = 0;		//	kakao = īī�� ��ũ Ŭ�� ��
		int naverCount = 0; 
		int kakaoCount = 0;
		
		// ������ �ִ��� Ȯ���Ͽ� ȸ���̸� �ش� ��ǰ�� ������ �ִ��� Ȯ���Ͽ� ������ ������ �����Ѵ�.
		HttpSession sessionCurrent = request.getSession();				// ���� �ִ� ���� ȣ��
		int index_member = 0;
		
		// session�� index_member�� ���� �ִ��� Ȯ��
		// ȸ���̸� ȸ����ȣ ���� => index_member�� ����, ������ null����
		if(sessionCurrent.getAttribute("index_member") != null)
		{	
			if(sessionCurrent.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)sessionCurrent.getAttribute("index_member");	}
		}
		
		if(index_member > 0)
		{
			ratingMember.setIndex_book(index_book);
			ratingMember.setIndex_member(index_member);
			readStatus.setIndex_book(index_book);
			readStatus.setIndex_member(index_member);
			linkClick.setIndex_book(index_book);
			linkClick.setIndex_member(index_member);
			
			ratingCount = detailImpl.ratingCount(ratingMember);
			if(ratingCount > 0)
			{	rating = detailImpl.ratingSelect(ratingMember);	}	
			
			readCount = detailImpl.rstatusCount(readStatus);
			if(readCount > 0)
			{	
				read = detailImpl.rstatusSelect(readStatus); 
				readStatus.setCheckCount(0);
			}
			else
			{	readStatus.setCheckCount(1);		}
		}
		else
		{	readStatus.setCheckCount(1); 	}
		
		//��ũ Ŭ�� - ��ȸ�� = 0
		if(index_member == 0)
		{
			linkClick.setIndex_book(index_book);
			linkClick.setIndex_member(index_member);
		}
		
		// �ش� ��ǰ�� ��ü ���ο� ���� ������� ��ȸ
		int ratingTotalNum = detailImpl.ratingTotalNum(index_book);
		float ratingAvg = 0;
		
		if(ratingTotalNum > 0)
		{	
			ratingAvg = detailImpl.ratingAvg(index_book);	
			allDetailBook.setRating_num(ratingTotalNum);
			allDetailBook.setRating_revel(ratingAvg);
			detailImpl.ratingAnl(allDetailBook);
		}
		
		//Ű���� �ޱ�
		Map <String, List>keywordMap = new HashMap();
		List keywords = daoKey.getKeyword(index_book);
		
		//��� ����Ʈ ��ȸ
		if(count > 0) 
		{
			rvList = detailImpl.reviewList(review); //������ index_book �޾�����, �Ķ���Ͱ� reviewDTO�� ����Ǿ� review�� 
			allDetailBook.setReview_num(count);
			detailImpl.review_num(allDetailBook);
		}
		number = count - (currentPage-1)*pageSize;
				
		//���� �۰� ��ǰ ��ȸ
		int sameWriterCount = 0;
		List sameWriter = null;
		sameWriterCount = detailImpl.sameWriterCount(index_book);
		if(sameWriterCount > 0)
		{	
			sameWriter = detailImpl.sameWriterList(index_book);	
			allDetailBook.setIndex_book(index_book);
			detailImpl.sameWriterCount(index_book);
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", review.getStartRow());
		request.setAttribute("endRow",review.getEndRow());
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rating", rating);
		request.setAttribute("rstatus", read);
		request.setAttribute("sameWriterCount", sameWriterCount);
		model.addAttribute("allDetailBook", allDetailBook); 
		model.addAttribute("rvList",rvList);
		model.addAttribute("keywords", keywords);
		model.addAttribute("sameWriter",sameWriter);
		model.addAttribute("readStatus",readStatus);

		return "/detail/detail";
	}
	
	// ����ۼ�
	@RequestMapping(value = "/detail/reviewWrite.rv", method = RequestMethod.POST)
	public String reviewWrite(HttpServletRequest request) throws Exception
	{
		review.setIndex_book(Integer.parseInt(request.getParameter("index_book")));
		review.setIndex_member(Integer.parseInt(request.getParameter("index_member")));
		review.setContent(request.getParameter("content"));
		detailImpl.reviewWrite(review);
		return "redirect:/detail.rv?bookNum="+review.getIndex_book();
	}
	
	// ��� ����
	@RequestMapping("/detail/reviewDelete.rv")
	public String reviewDelete(HttpServletRequest request) throws Exception
	{	
		int index_review = Integer.parseInt(request.getParameter("reviewNum"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		
		detailImpl.reviewDelete(index_review);
		return "redirect:/detail.rv?bookNum="+index_book;
	}
	
	/*
	 *  ȸ�� ���� �� �ش� å�� ������ �ο��ϴ� �޼���
	 *  ��ȸ���̰ų� Ż��ȸ���� ���� ���񽺸� �̿����� ���Ѵ�.
	 */
	@RequestMapping("/detail/rating.rv")
	public String rating(HttpServletRequest request, HttpSession session, Model model) throws Exception
	{
		// memberNum�� �������� �ҷ�����
		int rating = Integer.parseInt(request.getParameter("rating"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int status = 0;
		int index_member = 0;
		int countRating = 0;
		
		// ȸ���������� �ƴ����� Ȯ���Ѵ�.
		if(session != null)
		{	
			if(session.getAttribute("status") instanceof Integer)
			{	status = (int)session.getAttribute("status");	}
			
			if(session.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)session.getAttribute("index_member");	}	
		}
		
		
		// ȸ�������� ��쿡�� �ش� å�� ������ �ο��Ѵ�.
		if(status == 11 || status == 12)
		{
			// ȸ����ȣ, ���Ҽ���ȣ, ������ RatingMemberDTO�� �����Ѵ�.
			ratingMember.setIndex_book(index_book);
			ratingMember.setIndex_member(index_member);
			ratingMember.setRating(rating);
			
			// ���� ���̺��� ȸ���� �ش� å�� ������ �ο��ߴ��� Ȯ���Ѵ�.
			// ������ 0, ������ 1
			countRating = detailImpl.ratingCount(ratingMember);
			
			// count ��� 1�̸� �ش� ���Ҽ��� ������ �ο������� �����Ƿ� ���� �ο��� ������ ������Ʈ �����ְ�,
			// count ��� 0�̸� �ش� ���Ҽ��� ������ �ο��� ���� �����Ƿ� �������̺� �־��ش�(insert).
			if(countRating > 0)
			{	
				detailImpl.ratingUpdate(ratingMember);	
				//�������� ������Ʈ
				analImpl.updateMemberScore(ratingMember);
			}
			else
			{	
				detailImpl.ratingInsert(ratingMember);	
				//�������� ������Ʈ
				analImpl.updateMemberScore(ratingMember);
			}
		}
		
		return "redirect:/detail.rv?bookNum="+index_book;
	}
	
	// ȸ���� �ش� å�� �������� �����ϴ� �޼���
	@RequestMapping("/detail/rstatus.rv")
	public String rstatus(HttpServletRequest request, HttpSession session, Model model) throws Exception
	{
		/*
		 *  rstatus : ���� ����� ��������
		 *  rstatusBefore : ������ ��ϵǾ� �ִ� ��������
		 *  index_book : ����� ���������� å��ȣ
		 *  checkCount : �������� ����/���� ���� (����-1 , ����-0)
		 *  status : ȸ������
		 *  index_member : ȸ����ȣ
		 *  countRstatus : ȸ���� �������� ���̺� �ش� å�� �߰��� �������°� �ִ��� Ȯ��
		 */
		int rstatus = Integer.parseInt(request.getParameter("rstatus"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int checkCount = Integer.parseInt(request.getParameter("checkCount"));
		int status = 0;
		int index_member = 0;
		int countRstatus = 0;
			
		// ���� ������ �ִ��� �Ǵ�
		HttpSession sessionCurrent = request.getSession();
			
		// ȸ�� ���� ���� Ȯ��
		if(sessionCurrent.getAttribute("status") != null)
		{
			if(session.getAttribute("status") instanceof Integer)
			{	status = (int)session.getAttribute("status");	}
		}
		
		//ȸ�� ��ȣ ���� Ȯ��
		if(sessionCurrent.getAttribute("index_member") != null)
		{
			if(session.getAttribute("index_member") instanceof Integer) 
			{	index_member = (int)session.getAttribute("index_member");	}
		}
			
		//ȸ���� �������� ���� ���� - å��ȣ, ȸ����ȣ, �������� ReadStatusDTO ����
		if(status == 11 || status == 12) 
		{
			readStatus.setIndex_book(index_book);
			readStatus.setIndex_member(index_member);
			readStatus.setRead(rstatus);
			
			/* 
			 *	rstatus�� detail ���̺� �������� ������ ������Ʈ�� �÷� �̸����� ����
			 *	���ɾ���(0)-'read_not' / �����Ŵ�(1)-'read_want' / �а��ִ�(2)-'read_ing' / ���о���(3)-'read_end'
			 */
			switch(rstatus)
			{
				case 0 : readStatus.setReadAfter("read_not");
						 break;
				case 1 : readStatus.setReadAfter("read_want");
						 break;
				case 2 : readStatus.setReadAfter("read_ing");
						 break;
				case 3 : readStatus.setReadAfter("read_end");	
						 break;
			}
			
			countRstatus = detailImpl.rstatusCount(readStatus);
			// �������� ������Ʈ(ȸ���� �������� ���̺�, detail ���̺�)
			if(countRstatus > 0) 
			{	
				if(checkCount == 1)
				{
					int rstatusBefore = detailImpl.rstatusSelect(readStatus);
				
					switch(rstatusBefore)
					{
						case 0 : readStatus.setReadBefore("read_not");
							 	 break;
						case 1 : readStatus.setReadBefore("read_want");
							 	 break;
						case 2 : readStatus.setReadBefore("read_ing");
							  	 break;
						case 3 : readStatus.setReadBefore("read_end");	
							 	 break;
					}
					
					detailImpl.rstatusUpdate(readStatus);	
					detailImpl.detailRstatusChange(readStatus);
				}
				else
				{
					detailImpl.rstatusDelete(readStatus);
					detailImpl.detailRstatusDelete(readStatus);
				}
			
			}
			else 
			{	
				detailImpl.rstatusInsert(readStatus);	
				detailImpl.detailRstatusAdd(readStatus);
			}
		}
		
		return "redirect:/detail.rv?bookNum="+index_book;		
	}
	
	// ��ũ Ŭ�� �� ����
	@RequestMapping("/detail/linkClick.rv")
	public String linkClick(HttpServletRequest request, HttpSession session, Model model) throws Exception
	{	
		/*
		 *  platform : ��ũ �÷���
		 *  index_book : å��ȣ
		 *  status : ȸ�� ����
		 *  index_member : ȸ����ȣ
		 *  platformClick : �÷��� Ŭ����
		 *  count : lick_click ���̺� ������ ���� Ȯ��
		 */
		
		String platform = request.getParameter("platform");
		String url = request.getParameter("url");
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int status = 0;						
		int index_member = 0;
		int platformClick = 0;
		int count = 0;
		int result = 0;
		
		// ȸ�� ���� ���� �ڵ�(��ȸ��-0 , ȸ��-11 or 12, Ż��-50, ������-100)
		HttpSession sessionCurrent = request.getSession();
		if(sessionCurrent.getAttribute("status") != null)
		{
			if(session.getAttribute("status") instanceof Integer)
			{	
				status = (int)session.getAttribute("status");	// ȸ��, Ż��, ������	
			}
		}
		
		// ȸ�� ��ȣ �˻� �ڵ�(��ȸ��-0, ȸ��,Ż��,������-ȸ��������ȣ)
		if(sessionCurrent.getAttribute("index_member") != null)
		{
			if(session.getAttribute("index_member") instanceof Integer)
			{	index_member = (int)session.getAttribute("index_member");	}
		}
		
		// �����ڰ� �ƴϸ� ��ũ�� �ø���.
		if(status != 100)
		{
			// ȸ������
			if(status == 11 || status == 12)
			{
				// link_click ���̺� �ش� �Ҽ��� �� �÷��� ī��Ʈ �� ����
				linkClick.setIndex_book(index_book);
				linkClick.setIndex_member(index_member);
				linkClick.setPlatform(platform);
				
				// ����ڰ� ���� �÷����� �ش� �Ҽ��� link_click ���̺� �ִ��� Ȯ��
				count = detailImpl.clickCount(linkClick);
				
				// count�� 1�̻��̸� ���� �����Ϳ� ����ڰ� ���� �÷��� Ŭ���� ����
				// count�� 0�̸� �ش� ������ link_click ���̺� �߰�
				if(count > 0)
				{
					detailImpl.platformUpdate(linkClick);
					result = 1;
				}
				else
				{
					if(linkClick.getPlatform().equals("naver"))
					{	linkClick.setPlatformCount(1);	}
					else	
					{	linkClick.setPlatformCount(1);	}
					detailImpl.clickInsert(linkClick);
					result = 1;
				}
			}
			// ��ȸ��, Ż��ȸ��
			else
			{
				// link_click ���̺� �ش� �Ҽ��� �� �÷��� ī��Ʈ �� ����
				linkClick.setIndex_book(index_book);
				linkClick.setIndex_member(0);
				linkClick.setPlatform(platform);
				
				// ����ڰ� ���� �÷����� �ش� �Ҽ��� link_click ���̺� �ִ��� Ȯ��
				count = detailImpl.clickCount(linkClick);
				
				// count�� 1�̻��̸� ���� �����Ϳ� ����ڰ� ���� �÷��� Ŭ���� ����
				// count�� 0�̸� �ش� ������ link_click ���̺� �߰�
				if(count > 0)
				{
					detailImpl.platformUpdate(linkClick);
					result = 1;
				}
				else
				{
					if(linkClick.getPlatform().equals("naver"))
					{	linkClick.setPlatformCount(1);	}
					else
					{	linkClick.setPlatformCount(1); 	}
					detailImpl.clickInsert(linkClick);
					result = 1;
				}			
			}
		}
		else
		{	result = 0;	}
	
		model.addAttribute("result", result);
		model.addAttribute("url", url);
		
		return "/detail/linkClick";
	}
	
	
}	