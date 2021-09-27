package member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.DetailDAOInter;
import detail.DetailDTO;
import detail.RatingMemberDTO;
import detail.ReadStatusDTO;

@Controller
@RequestMapping("/mypage/")
public class MylistBean {
	
	@Autowired
	private RatingMemberDTO ratingMember = null;
	
	@Autowired
	private MemberReadDTO read = null;
	
	@Autowired
	private MemberAnalDTO anls = null;
	
	@Autowired
	private ReadStatusDTO readStatus = null;
	
	@Autowired
	private DetailDTO detail = null;
	
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	@Autowired
	private DetailDAOInter detailImpl = null;
	
	
	/*ȸ�� ��ȣ �帣 �м� ��Ʈ ����ǰ&���о��� ���� �帣��(1�θǽ�, 2����, 3��Ÿ��, 4����, 5����) 
	* int romance,rofan,fantasy,mofan,heroism : �帣�� count ����
	* int r_romance,r_rofan,r_fantasy,r_mofan,r_heroism : �帣�� ��� count����
	*/
	@RequestMapping("myGenreAnls.rv")
	public String myLogonmyAnalysisGenre(HttpSession session,Model model) throws Exception
	{	
		int index_member=0;
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
		
		read.setIndex_member(index_member);
		read.setGenre(1);
		int romance=analImpl.genreCount(read);
		int r_romance=analImpl.reviewCount(read);
		
		read.setGenre(2);
		int rofan = analImpl.genreCount(read);
		int r_rofan = analImpl.reviewCount(read);

		read.setGenre(3);
		int fantasy = analImpl.genreCount(read);
		int r_fantasy = analImpl.reviewCount(read);

		read.setGenre(4);
		int mofan = analImpl.genreCount(read);
		int r_mofan = analImpl.reviewCount(read);
		
		read.setGenre(5);
		int heroism = analImpl.genreCount(read);
		int r_heroism = analImpl.reviewCount(read);
		
		model.addAttribute("romance", romance);
		model.addAttribute("rofan", rofan);
		model.addAttribute("fantasy", fantasy);
		model.addAttribute("mofan", mofan);
		model.addAttribute("heroism", heroism);
		
		model.addAttribute("r_romance", r_romance);
		model.addAttribute("r_rofan", r_rofan);
		model.addAttribute("r_fantasy", r_fantasy);
		model.addAttribute("r_mofan", r_mofan);
		model.addAttribute("r_heroism", r_heroism);
		
		return "/member/analysis/genreChart";
	}
	
	
	/* anlysis ��ȣŰ���� �м�
	 * anls_top20 : ȸ�� ��ȣ Ű���� ����20�� ���
	 * anls_100 : ȸ�� ��ȣ Ű���� ĳ����(ī�װ�) ���� 6�� ��� -> anls_1(Ű����), anls_c1(��������)
	 * anls_200 : ȸ�� ��ȣ Ű���� ������(ī�װ�) ���� 6�� ��� -> anls_2(Ű����), anls_c2(��������)
	 * anls_300 : ȸ�� ��ȣ Ű���� ����(ī�װ�) ���� 6�� ��� -> anls_3(Ű����), anls_c3(��������)
	 * anls_400 : ȸ�� ��ȣ Ű���� ���ڹ���(ī�װ�) ���� 3�� ��� -> anls_4(Ű����), anls_c4(��������)
	 * Ű���� ����� �������� r�� �����ؼ� wordcloud ����ϱ� ����
	 * count ����ǰ ���� �������� 
	 */	
	@RequestMapping("myKeywordAnls.rv")
	public String myLogonmyAnalysisKeyword(HttpSession session,Model model) throws Exception
	{	
		int count =0;
		int index_member=0;
		List<MemberAnalDTO> anls_top20 =null;
		List <MemberAnalDTO> anls_100 = null;
		List <MemberAnalDTO> anls_200 = null;
		List <MemberAnalDTO> anls_300 = null;
		List <MemberAnalDTO> anls_400 = null;
		
		
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
		
		count = analImpl.totalCount(index_member);

		anls_top20 = analImpl.getMKeyword(index_member);
		anls_100 = analImpl.getCKeyword_1(index_member);
		anls_200 = analImpl.getCKeyword_2(index_member);
		anls_300 = analImpl.getCKeyword_3(index_member);
		anls_400 = analImpl.getCKeyword_4(index_member);
		
		
		// anls_top20 list �迭�� �����
		String [] keywords = new String[anls_top20.size()];
		double [] cumul_score = new double[anls_top20.size()];
		for(int i=0; i < anls_top20.size(); i++) 
		{
			keywords[i] = anls_top20.get(i).getKeywords();
			cumul_score[i] = anls_top20.get(i).getCumul_score();
			//System.out.println(keywords[i] +" & "+cumul_score[i]);
		}

		//list �迭�� ���� Ű����� ���������� �и�
		String [] anls_1 = new String[6];
		double [] anls_c1 = new double[6];
		String [] anls_2 = new String[6];
		double [] anls_c2 = new double[6];
		String [] anls_3 = new String[6];
		double [] anls_c3 = new double[6];

		for(int i=0; i < 6; i++) 
		{
			anls_1[i] = anls_100.get(i).getKeywords();
			anls_c1[i] = anls_100.get(i).getCumul_score();
			anls_2[i] = anls_200.get(i).getKeywords();
			anls_c2[i] = anls_200.get(i).getCumul_score();
			anls_3[i] = anls_300.get(i).getKeywords();
			anls_c3[i] = anls_300.get(i).getCumul_score();
		}
		
		String [] anls_4 = new String[3];
		double [] anls_c4 = new double[3];
		for(int i=0; i < 3; i++) 
		{
			anls_4[i] = anls_400.get(i).getKeywords();
			anls_c4[i] = anls_400.get(i).getCumul_score();
		}
	
		//r ���� top20��ȣŰ���� wordcloud ����� 	
		RConnection rConn = new RConnection();
		rConn.eval("library(wordcloud2)");
		rConn.eval("library(htmltools)");
		rConn.assign("keywords", keywords);
		rConn.assign("cumul_score", cumul_score);
		rConn.eval("anls_top20 <- data.frame(keywords=keywords, cumul_score=cumul_score)");	
		rConn.eval("top20_chart <- wordcloud2(anls_top20, size=0.4, color='random-light')");
		rConn.eval("top20_tag <- renderTags(top20_chart)");
		String top20_chart = rConn.eval("top20_tag$html").asString();
		rConn.close();
		
		model.addAttribute("anls_1", anls_1); 
		model.addAttribute("anls_2", anls_2); 
		model.addAttribute("anls_3", anls_3); 
		model.addAttribute("anls_4", anls_4); 
		model.addAttribute("anls_c1", anls_c1); 
		model.addAttribute("anls_c2", anls_c2); 
		model.addAttribute("anls_c3", anls_c3); 
		model.addAttribute("anls_c4", anls_c4); 
		model.addAttribute("keywords", keywords);
		model.addAttribute("cumul_score",cumul_score);
		model.addAttribute("count", count);
		model.addAttribute("top20_chart",top20_chart); 

		return "/member/analysis/keywordChart";
	}
	
	//anlysis �����׷���
	@RequestMapping("myRatingAnls.rv")
	public String myLogonmyAnalysisRating(HttpSession session,Model model) throws Exception
	{	
		int index_member=0;		
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
			
		ratingMember.setIndex_member(index_member);
		ratingMember.setRating(1);
		int ratingCount_1 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(2);
		int ratingCount_2 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(3);
		int ratingCount_3 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(4);
		int ratingCount_4 = analImpl.ratingCount(ratingMember);
		ratingMember.setRating(5);
		int ratingCount_5 = analImpl.ratingCount(ratingMember);
		int count =analImpl.totalCount(index_member);
			
		model.addAttribute("count",count); 
		model.addAttribute("ratingCount_1",ratingCount_1); 
		model.addAttribute("ratingCount_2",ratingCount_2); 
		model.addAttribute("ratingCount_3",ratingCount_3); 
		model.addAttribute("ratingCount_4",ratingCount_4); 
		model.addAttribute("ratingCount_5",ratingCount_5); 
		return "/member/analysis/ratingChart";
	}
	
	
	//mylist ���� ��ǰ �򰡸��
	@RequestMapping("myRating.rv")
	public String myLogonmyListRating(HttpSession session,Model model) throws Exception
	{	
		int index_member=0;
		int count =0;
		int index_book=0;
		List ratingDetail =null;
		 
		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}

		count = analImpl.totalCount(index_member);
		if(count>0) 
		//��ǰ �򰡸��+����,�۰� ��������
		{  ratingDetail = analImpl.getRatingDetail(index_member); }
		  
		model.addAttribute("ratingDetail",ratingDetail); 
		model.addAttribute("count",count); 
		return "/member/mylist/myRating";
	}
	
	//���� ���� ��ǰ �����ϱ�
	@RequestMapping("ratingDel.rv")
	public String myLogonmyListRatingDel(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=Integer.parseInt(request.getParameter("memberNum"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));		
		
		//ratingMember�� ���޹��� index_member�� index_book �����ϱ�
		ratingMember.setIndex_member(index_member);
		ratingMember.setIndex_book(index_book);
		//���� ���� �� �������� ������Ʈ
		analImpl.deleteRating(ratingMember);
		analImpl.updateMemberScore(ratingMember);
		
		// �ش� ��ǰ�� ��ü ���ο� ���� ������� ��ȸ
		int ratingTotalNum = detailImpl.ratingTotalNum(index_book);
		float ratingAvg = 0;
		if(ratingTotalNum > 0)
		{	
			ratingAvg = detailImpl.ratingAvg(index_book);	
			detail.setRating_num(ratingTotalNum);
			detail.setRating_revel(ratingAvg);
			detail.setIndex_book(index_book);
			detailImpl.ratingAnl(detail);
		}
		else
		{
			detail.setRating_num(0);
			detail.setRating_revel(0);
			detail.setIndex_book(index_book);
			detailImpl.ratingAnl(detail);
		}

		return "/member/mylist/ratingDel";
	}
	
	
	//��������: not ���ɾ���(0) 
	@RequestMapping("myReadnot.rv")
	public String myLogonmyListRead(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=0;
		int count =0;
		List readList =null;

		if(session.getAttribute("index_member")!=null) 
		{  index_member = (Integer)session.getAttribute("index_member"); }
		else 
		{	return "/member/login";	}
		 
		//�����ȣ�� �������� ����
		read.setRead(0);
		read.setIndex_member(index_member);
		count = analImpl.readCount(read);
		 
		if(count>0) 
		{ readList = analImpl.getReadList(read); }
		
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myReadnot";
	}
	
	//�������� : myReadwant �����Ŵ�(1)
	@RequestMapping("myReadwant.rv")
	public String myLogonmyListReadwant(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		 int index_member=0;
		 int count =0;
		 List readList =null;

		 if(session.getAttribute("index_member")!=null) 
		 {  index_member = (Integer)session.getAttribute("index_member"); }
		 else 
		 {	return "/member/login";	}
		 
		 //�����ȣ�� �������� ����
		 read.setRead(1);
		 read.setIndex_member(index_member);
		 count = analImpl.readCount(read);
		 
		 if(count>0) 
		 { 	readList = analImpl.getReadList(read); }
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myReadwant";
	}
	
	//�������� : myReading �а��ִ�(2)
	@RequestMapping("myReading.rv")
	public String myLogonmyListReading(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		 int index_member=0;
		 int count =0;
		 List readList =null;

		 if(session.getAttribute("index_member")!=null) 
		 {  index_member = (Integer)session.getAttribute("index_member"); }
		 else 
		 {	return "/member/login";	}
		 
		 //�����ȣ�� �������� ����
		 read.setRead(2);
		 read.setIndex_member(index_member);
		 count = analImpl.readCount(read);
		 
		 if(count>0) 
		 { 	readList = analImpl.getReadList(read); }
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myReading";
	}
	
	//�������� :  myRead ���о���(3)
	@RequestMapping("myRead.rv")
	public String myLogonmyListRead(HttpSession session,Model model) throws Exception
	{
		 int index_member=0;
		 int count =0;
		 List readList =null;

		 if(session.getAttribute("index_member")!=null) 
		 {  index_member = (Integer)session.getAttribute("index_member"); }
		 else 
		 {	return "/member/login";	} 
		 
		 //�����ȣ�� �������� ����
		 read.setRead(3);
		 read.setIndex_member(index_member);
		 count = analImpl.readCount(read);
		 
		 if(count>0) 
		 { 	readList = analImpl.getReadList(read); }
		 
		model.addAttribute("count",count); 
		model.addAttribute("readList",readList); 
		model.addAttribute("index_member",index_member);
		return "/member/mylist/myRead";
	}
	
	//���� �������� �����ϱ�
	@RequestMapping("readChange.rv")
	public String myLogonmyListReadChange(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=Integer.parseInt(request.getParameter("index_member"));
		int index_rstatus = Integer.parseInt(request.getParameter("index_rstatus"));		
		int readS = Integer.parseInt(request.getParameter("read"));
		int index_book = Integer.parseInt(request.getParameter("index_book"));
		int countRstatus = 0;			// countRstatus : ȸ���� �������� ���̺� �ش� å�� �߰��� �������°� �ִ��� Ȯ��
		
		//read�� ���޹��� index_member�� index_rstatus, read �����ϱ�
		read.setIndex_member(index_member);
		read.setIndex_rstatus(index_rstatus);
		read.setRead(readS);
		read.setIndex_book(index_book);
		readStatus.setIndex_book(index_book);
		readStatus.setIndex_member(index_member);
		readStatus.setRead(readS);
		
		/* 
		 *	rstatus�� detail ���̺� �������� ������ ������Ʈ�� �÷� �̸����� ����
		 *	���ɾ���(0)-'read_not' / �����Ŵ�(1)-'read_want' / �а��ִ�(2)-'read_ing' / ���о���(3)-'read_end'
		 */
		switch(readS)
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
			
			//�������º��� 
			analImpl.updateRead(read);
			detailImpl.detailRstatusChange(readStatus);
		}
		
		return "/member/mylist/readChange";
	}
	
	
	//���� �������� �����ϱ�
	@RequestMapping("readDel.rv")
	public String myLogonmyListReadDel(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{
		int index_member=Integer.parseInt(request.getParameter("memberNum"));
		int index_rstatus = Integer.parseInt(request.getParameter("readNum"));		
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int countRstatus = 0; 				// ȸ���� �������� ���̺� �ش� å�� �߰��� �������°� �ִ��� Ȯ��
		
		//read�� ���޹��� index_member�� index_rstatus �����ϱ�
		read.setIndex_member(index_member);
		read.setIndex_rstatus(index_rstatus);
		readStatus.setIndex_book(index_book);
		readStatus.setIndex_member(index_member);
		readStatus.setRead(detailImpl.rstatusSelect(readStatus));
		
		/* 
		 *	rstatus�� detail ���̺� �������� ������ ������Ʈ�� �÷� �̸����� ����
		 *	���ɾ���(0)-'read_not' / �����Ŵ�(1)-'read_want' / �а��ִ�(2)-'read_ing' / ���о���(3)-'read_end'
		 */
		switch(readStatus.getRead())
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
			//���� ���� �����ϱ�
			analImpl.deleteRead(read);
			detailImpl.detailRstatusDelete(readStatus);
		}

		return "/member/mylist/readDel";
	}
	
}
