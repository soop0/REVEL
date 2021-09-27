package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import detail.DetailDAOInter;
import detail.DetailDTO;
import detail.ReviewDTO;
import help.NoticeDAOInter;
import ranking.KeywordDTO;

@Controller
public class AdminBean
{
	@Autowired
	private AdminDAOInter adminImpl = null;
	
	@Autowired
	private DetailDTO detail = null;
	
	@Autowired
	private DetailDAOInter detailImpl = null;
	
	@Autowired
	private NoticeDAOInter noticeImpl = null;
	
	@Autowired
	private ReviewDTO review = null;
	
	@Autowired
	private WithdrawalDTO withdrawal = null;
	
	@Autowired
	private AdminDTO admin = null;
	
	@Autowired
	private KeywordDTO keyword = null;
	
	// ����������
	@RequestMapping("manage.rv")
	public String myLogonmanage(Model model) throws Exception
	{
		/*
		 * memberCount : ȸ����
		 * bookCount : ��ϵ� �Ҽ���
		 * reviewCount : ��ۼ�
		 * noticeCount : �������׼�
		 * genre : �帣��ȣ
		 * age : ����
		 * ageCount : ���ɰ���
		 * total : �÷��� ��ü Ŭ����
		 * naverCount : ���̹�ȸ����
		 * kakaoCount : īī��ȸ����
		 * categoryNumber : Ű���� ī�װ� ��ȣ
		 * keyword100 : ĳ���� Ű���� Top5
		 * manCount : ����ȸ����
		 * womanCount : ����ȸ����
		 */
		int memberCount = adminImpl.memberCount();
		int bookCount = adminImpl.bookListCount();
		int reviewCount = adminImpl.reviewCount();
		int noticeCount = noticeImpl.noticeCount();
		int[] genre = {1,2,3,4,5};
		int[] age = {10,20,30,40,50};
		int ageCount = 0;
		int total = 0;
		int[] categoryNumber = {100,200};
		List keyword100 = null;
		
		// �帣�� ���� ���
		for(int i=0; i<age.length; i++)
		{
			for(int j=0; j<genre.length; j++)
			{
				if(age[i] != 50)
				{
					admin.setAgeStart(age[i]);
					admin.setAgeEnd(age[i+1]);
					admin.setGenre(genre[j]);
					ageCount = adminImpl.genreAgeCount(admin);
					if(ageCount > 0)
					{
						total = adminImpl.genreAgePlatform(admin).getNaver() + adminImpl.genreAgePlatform(admin).getKakao();
						switch(age[i])
						{
							case 10 : admin.getAge10()[j] = total;	
										break;
							case 20 : admin.getAge20()[j] = total;
										break;
							case 30 : admin.getAge30()[j] = total;
										break;
							case 40 : admin.getAge40()[j] = total;
										break;
						}
					}
					else
					{
						total = 0;
						switch(age[i])
						{
							case 10 : admin.getAge10()[j] = total;	
										break;
							case 20 : admin.getAge20()[j] = total;
										break;
							case 30 : admin.getAge30()[j] = total;
										break;
							case 40 : admin.getAge40()[j] = total;
										break;
						}
					}
				}
				else
				{
					admin.setAgeStart(age[i]);
					admin.setGenre(genre[j]);
					ageCount = adminImpl.genreAgeCount(admin);
					if(ageCount > 0)
					{
						total = adminImpl.genreAgePlatform(admin).getNaver() + adminImpl.genreAgePlatform(admin).getKakao();
						admin.getAge50()[j] = total;	
					}
					else
					{
						total = 0;
						admin.getAge50()[j] = total;
					}
				}
			}
		}
		
		// ĳ���� Ű���� Top5
		keyword.setIndex_keyword_start(categoryNumber[0]);
		keyword.setIndex_keyword_end(categoryNumber[1]);
		if(keyword.getIndex_keyword_start() == 100)
		{	keyword100 = adminImpl.keywordRank(keyword);	}
		
		
		
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("bookCount", bookCount);
		model.addAttribute("reviewCount", reviewCount);
		model.addAttribute("noticeCount", noticeCount);
		model.addAttribute("age10", admin.getAge10());
		model.addAttribute("age20", admin.getAge20());
		model.addAttribute("age30", admin.getAge30());
		model.addAttribute("age40", admin.getAge40());
		model.addAttribute("age50", admin.getAge50());
		model.addAttribute("naverCount", adminImpl.naverCount());
		model.addAttribute("kakaoCount", adminImpl.kakaoCount());
		model.addAttribute("character", keyword100);
		model.addAttribute("manCount", adminImpl.manCount());
		model.addAttribute("womanCount", adminImpl.womanCount());
		
		return "/admin/management";
	}
	
	// ȸ�� ���� ������
	@RequestMapping("manage/membership.rv")
	public String myLogonmembership(HttpServletRequest request, Model model) throws Exception
	{
		// count : ����ȸ�� ��
		// naverCount : ���̹�ȸ�� ��
		// kakaoCount : īī��ȸ�� ��
		// naverRatio : ���̹� ȸ�� ����
		// kakaoRatio : īī�� ȸ�� ����
		// withdrawalCount : Ż��ȸ�� ��
		
		int count = 0;
		int naverCount = 0;
		int kakaoCount = 0;
		int withdrawalCount = 0;
		int naverRatio = 0;
		int kakaoRatio = 0;
		String platform = request.getParameter("platform");
		List memberList = null;
		
		// ������ ȸ�� �� �� ���� �ҷ�����
		count = adminImpl.memberCount();
		naverCount = adminImpl.naverCount();
		kakaoCount = adminImpl.kakaoCount();
		withdrawalCount = adminImpl.withdrawalCount();
		naverRatio = Math.round((float)naverCount*100/count);
		kakaoRatio = Math.round(kakaoCount*100/count);
		memberList = adminImpl.memberList();
		
		// ���̹�/īī��/Ż�� ȸ�� ����Ʈ �ҷ�����
		if(platform != null)
		{
			if(platform.equals("naver"))
			{
				if(naverCount > 0)
				{	memberList = adminImpl.naverList();	}
			}
			else if(platform.equals("kakao"))
			{
				if(kakaoCount > 0)
				{	memberList = adminImpl.kakaoList();	}
			}
			else if(platform.equals("withdrawal"))
			{
				if(withdrawalCount > 0)
				{	memberList = adminImpl.withdrawalList();	}
			}
		}
		
		model.addAttribute("count", count);
		model.addAttribute("naverCount", naverCount);
		model.addAttribute("kakaoCount", kakaoCount);
		model.addAttribute("withdrawalCount", withdrawalCount);
		model.addAttribute("naverRatio", naverRatio);
		model.addAttribute("kakaoRatio", kakaoRatio);
		model.addAttribute("platform", platform);
		model.addAttribute("memberList", memberList);
		
		return "/admin/membershipList";
	}
	
	// �Ҽ� ����Ʈ ���� ������
	@RequestMapping("manage/novel.rv")
	public String myLogonnovel(Model model) throws Exception
	{
		/*
		 *  count : �Ҽ���
		 *  bookList : �Ҽ� ����Ʈ
		 *  reviewCount : ��� ��
		 *  read_wantCount : �а�ʹ� ���� ��ǰ ��
		 *  read_ingCount : �а��ִ� ���� ��ǰ ��
		 *  read_endCount : ���о��� ���� ��ǰ ��
		 *  read_notCount : ���ɾ��� ���� ��ǰ ��
		 */
		
		int count = 0;
		List bookList = null;
		int reviewCount = 0;
		int read_wantCount = 0;
		int read_ingCount = 0;
		int read_endCount = 0;
		int read_notCount = 0;
		
		count = adminImpl.bookListCount();
		read_wantCount = adminImpl.read_wantCount();
		read_ingCount = adminImpl.read_ingCount();
		read_endCount = adminImpl.read_endCount();
		read_notCount = adminImpl.read_notCount();
		
		if(count > 0)
		{	
			bookList = adminImpl.bookList();	
			for(int i=0; i<bookList.size(); i++)
			{
				if(bookList.get(i) instanceof DetailDTO)
				{	
					detail = (DetailDTO)bookList.get(i);
					reviewCount = detailImpl.reviewCount(detail.getIndex_book());
					detail.setReview_num(reviewCount);
					detailImpl.review_num(detail);
				}
			}
			bookList = adminImpl.bookList();
		}
		
		model.addAttribute("count", count);
		model.addAttribute("bookList", bookList);
		model.addAttribute("read_wantCount", read_wantCount);
		model.addAttribute("read_ingCount", read_ingCount);
		model.addAttribute("read_endCount", read_endCount);
		model.addAttribute("read_notCount", read_notCount);
		
		return "/admin/novelList";
	}
	
	// �����Ŵ�, �а� �ִ�, ���о���, ���ɾ��� ��ǰ ���� ��� ������
	@RequestMapping("manage/novel/ranking.rv")
	public String myLogonrankingNovel(HttpServletRequest request, Model model) throws Exception
	{
			// read : �Ķ���� �Ѿ���� ��������
			// readRankingCount : ���������� ������ �ű� �� �ִ��� Ȯ�� 
			// read_wantCount : �а�ʹ� ���� ��ǰ ��
			// read_ingCount : �а��ִ� ���� ��ǰ ��
			// read_endCount : ���о��� ���� ��ǰ ��
			// read_notCount : ���ɾ��� ���� ��ǰ ��
		
			String read = request.getParameter("read");
			int readRankingCount = 0;
			int read_wantCount = 0;
			int read_ingCount = 0;
			int read_endCount = 0;
			int read_notCount = 0;
			List readRankList = null;
			
			readRankingCount = adminImpl.readRankingCount(read);
			read_wantCount = adminImpl.read_wantCount();
			read_ingCount = adminImpl.read_ingCount();
			read_endCount = adminImpl.read_endCount();
			read_notCount = adminImpl.read_notCount();
	
			if(read != null)
			{
				if(readRankingCount > 0)
				{	readRankList = adminImpl.readRankList(read);	}
			}
			
			model.addAttribute("read", read);
			model.addAttribute("readRankingCount", readRankingCount);
			model.addAttribute("readRankList", readRankList);
			model.addAttribute("read_wantCount", read_wantCount);
			model.addAttribute("read_ingCount", read_ingCount);
			model.addAttribute("read_endCount", read_endCount);
			model.addAttribute("read_notCount", read_notCount);
			
			return "/admin/novelRanking";
	}
	
	// ��� ����Ʈ ���� ������
	@RequestMapping("manage/board/review.rv")
	public String myLogonreview(HttpServletRequest request, Model model) throws Exception
	{
		// count : ��ü ��� ��
		// cleanbotCount : Ŭ���� ���� ��� ��
		// theme : �Ķ���� �Ѿ���� �׸���
		// reviewList : ��ü ��� ����Ʈ
		// reviewNickRank : �ִ� ��� ȸ�� ����Ʈ
		// reviewBookRank : �ִ� ��� �Ҽ� ����Ʈ
	
		int count = 0;
		int cleanbotCount = 0;
		String theme = request.getParameter("theme");
		List reviewList = null;
		List reviewNickRank = null;
		List reviewBookRank = null;
		
		count = adminImpl.reviewCount();
		cleanbotCount = adminImpl.cleanbotCount();
		if(count > 0)
		{	
			reviewList = adminImpl.reviewList();	
			reviewNickRank = adminImpl.reviewNickRank();
			reviewBookRank = adminImpl.reviewBookRank();
		}
		
		// theme�� clean�� �� Ŭ���� ���� ��� ����Ʈ ��� �ҷ�����
		if(theme != null)
		{
			if(theme.equals("clean"))
			{
				if(cleanbotCount > 0)
				{	reviewList = adminImpl.cleanbotList();	}
			}
		}
		
		model.addAttribute("count", count);
		model.addAttribute("theme", theme);
		model.addAttribute("cleanbotCount", cleanbotCount);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("reviewNickRank", reviewNickRank);
		model.addAttribute("reviewBookRank", reviewBookRank);
		
		return "/admin/reviewList";
	}
	
	// �ִ� ��� ȸ��, �ִ� ��� ��ǰ ���� ��� ������
	@RequestMapping("manage/board/review/ranking.rv")
	public String myLogonrankingReview(HttpServletRequest request, Model model) throws Exception
	{
		// theme : �Ķ���� �Ѿ���� �׸���
		// count : ��ü ��� ��
		// cleanbotCount : Ŭ���� ���� ��� ��
		// reviewNickCount : �ִ� ��� ȸ�� ��
		// reviewBookCount : �ִ� ��� �Ҽ� ��
		// reviewNickRank : �ִ� ��� ȸ�� ����Ʈ
		// reviewBookRank : �ִ� ��� �Ҽ� ����Ʈ
		
		String theme = request.getParameter("theme");
		int count = 0;
		int cleanbotCount = 0;
		int reviewNickCount = 0;
		int reviewBookCount = 0;
		List reviewNickRank = null;
		List reviewBookRank = null;
		
		count = adminImpl.reviewCount();
		cleanbotCount = adminImpl.cleanbotCount();
		reviewNickCount = adminImpl.reviewNickCount();
		reviewBookCount = adminImpl.reviewBookCount();
		
		if(theme != null)
		{
			if(reviewNickCount > 0 && reviewBookCount > 0)
			{	
				reviewNickRank = adminImpl.reviewNickRank();
				reviewBookRank = adminImpl.reviewBookRank();	
			}
		}
		
		model.addAttribute("theme", theme);
		model.addAttribute("count", count);
		model.addAttribute("cleanbotCount", cleanbotCount);
		model.addAttribute("reviewNickRank", reviewNickRank);
		model.addAttribute("reviewBookRank", reviewBookRank);
		
		return "/admin/reviewRanking";
	}
	
	// ������ �������� ��� ����(Ŭ����)
	@RequestMapping("manage/board/reviewDel.rv")
	public String myLogonreviewDel(HttpServletRequest request, Model model) throws Exception
	{
		int index_review = Integer.parseInt(request.getParameter("reviewNum"));
		int index_book = Integer.parseInt(request.getParameter("bookNum"));
		int status = 0;
		int count = 0;
		
		// ���� Ȯ���Ͽ� ������ ȸ�� ���� ���� ������ 0
		HttpSession sessionCurrent = request.getSession();
		if(sessionCurrent.getAttribute("status") != null)
		{
			if(sessionCurrent.getAttribute("status") instanceof Integer)
			{	status = (int)sessionCurrent.getAttribute("status");	}
		}
		
		// ȸ�� ���°� �������̸� ������ ��� Ŭ������ ���� �����Ѵ�.
		if(status == 100)
		{
			review.setIndex_book(index_book);
			review.setIndex_review(index_review);
			count = adminImpl.reviewDelCount(review);
			if(count > 0)
			{
				review.setCleanbot(1); 				// ��� ���� 1 , �ƴϸ� 0
				adminImpl.reviewDel(review);
			}
		}
		
		return "redirect:/manage/board/review.rv";
	}
	
	// �������� ����Ʈ ���� ������
	@RequestMapping("manage/board/notice.rv")
	public String myLogonnotice(Model model) throws Exception
	{
		int count = noticeImpl.noticeCount();
		List noticeList = null;
		
		if(count > 0)
		{	noticeList = adminImpl.noticeList();	}
		
		model.addAttribute("count", count);
		model.addAttribute("noticeList", noticeList);
		
		return "/admin/noticeList";
	}
	
	// ȸ�� ��� �м� ������
	@RequestMapping("manage/analysis/membership.rv")
	public String myLogonmembershipAnalysis(Model model) throws Exception
	{
		/*
		 * memberCount : ��üȸ����
		 * manCount : ����ȸ����
		 * womanCount : ����ȸ����
		 * naverCount : ���̹�ȸ����
		 * kakaoCount : īī��ȸ����
		 * ratio10 : 10��ȸ������
		 * ratio20 : 20��ȸ������
		 * ratio30 : 30��ȸ������
		 * ratio40 : 40��ȸ������
		 * ratio50 : 50��ȸ������
		 * interestLost : ��̰���
		 * contentNot : ����������
		 * serviceChange : Ÿ�����̿�
		 * serviceNot : ���񽺺���
		 * etc : ��Ÿ
		 */
		
		int ratio10 = 0;
		int ratio20 = 0;
		int ratio30 = 0;
		int ratio40 = 0;
		int ratio50 = 0;
				
		ratio10 = Math.round((float)adminImpl.count10()*100/adminImpl.memberCount());
		ratio20 = Math.round((float)adminImpl.count20()*100/adminImpl.memberCount());
		ratio30 = Math.round((float)adminImpl.count30()*100/adminImpl.memberCount());
		ratio40 = Math.round((float)adminImpl.count40()*100/adminImpl.memberCount());
		ratio50 = Math.round((float)adminImpl.count50()*100/adminImpl.memberCount());
		
		model.addAttribute("manCount", adminImpl.manCount());
		model.addAttribute("womanCount", adminImpl.womanCount());
		model.addAttribute("naverCount", adminImpl.naverCount());
		model.addAttribute("kakaoCount", adminImpl.kakaoCount());
		model.addAttribute("ratio10", ratio10);
		model.addAttribute("ratio20", ratio20);
		model.addAttribute("ratio30", ratio30);
		model.addAttribute("ratio40", ratio40);
		model.addAttribute("ratio50", ratio50);
		model.addAttribute("interestLost", adminImpl.interestLost(withdrawal.getInterestLost()));
		model.addAttribute("contentNot", adminImpl.contentNot(withdrawal.getContentNot()));
		model.addAttribute("serviceChange", adminImpl.serviceChange(withdrawal.getServiceChange()));
		model.addAttribute("serviceNot", adminImpl.serviceNot(withdrawal.getServiceNot()));
		model.addAttribute("etc", adminImpl.etc(withdrawal));

		
		return "/admin/membershipAnalysis";
	}
	
	// �帣�� ����/���� �׷���
	@RequestMapping("manage/analysis/novel/genre.rv")
	public String myLogonnovelAnalysisGenre(Model model) throws Exception
	{
		/*
		 * 	genre : �帣��ȣ
		 *  rating : ����
		 *  age : ����
		 *  ratingCount : ��������
		 *  ageCount : ���ɰ���
		 *  total : �÷��� ��ü Ŭ����
		 */
		
		int[] genre = {1,2,3,4,5};
		int[] rating = {1,2,3,4,5};
		int[] age = {10,20,30,40,50};
		int ratingCount = 0;
		int ageCount = 0;
		int total = 0;
		
		// �帣�� ���� ���
		for(int i=0; i<genre.length; i++)
		{
			for(int j=0; j<rating.length; j++)
			{
				admin.setGenre(genre[i]);
				admin.setRating(rating[j]);
				ratingCount = adminImpl.genreRatingCount(admin);
				switch(genre[i])
				{
					case 1 : admin.getRomance()[j] = ratingCount;	
								break;
					case 2 : admin.getRofan()[j] = ratingCount;
								break;
					case 3 : admin.getFantasy()[j] = ratingCount;
								break;
					case 4 : admin.getMofan()[j] = ratingCount;
								break;
					case 5 : admin.getHeroism()[j] = ratingCount;
								break;
				}
			}
		}
		
		// �帣�� ���� ���
		for(int i=0; i<age.length; i++)
		{
			for(int j=0; j<genre.length; j++)
			{
				if(age[i] != 50)
				{
					admin.setAgeStart(age[i]);
					admin.setAgeEnd(age[i+1]);
					admin.setGenre(genre[j]);
					ageCount = adminImpl.genreAgeCount(admin);
					if(ageCount > 0)
					{
						total = adminImpl.genreAgePlatform(admin).getNaver() + adminImpl.genreAgePlatform(admin).getKakao();
						switch(age[i])
						{
							case 10 : admin.getAge10()[j] = total;	
										break;
							case 20 : admin.getAge20()[j] = total;
										break;
							case 30 : admin.getAge30()[j] = total;
										break;
							case 40 : admin.getAge40()[j] = total;
										break;
						}
					}
					else
					{
						total = 0;
						switch(age[i])
						{
							case 10 : admin.getAge10()[j] = total;	
										break;
							case 20 : admin.getAge20()[j] = total;
										break;
							case 30 : admin.getAge30()[j] = total;
										break;
							case 40 : admin.getAge40()[j] = total;
										break;
						}
					}
				}
				else
				{
					admin.setAgeStart(age[i]);
					admin.setGenre(genre[j]);
					ageCount = adminImpl.genreAgeCount(admin);
					if(ageCount > 0)
					{
						total = adminImpl.genreAgePlatform(admin).getNaver() + adminImpl.genreAgePlatform(admin).getKakao();
						admin.getAge50()[j] = total;	
					}
					else
					{
						total = 0;
						admin.getAge50()[j] = total;
					}
				}
			}
		}
		
		
		model.addAttribute("romance", admin.getRomance());
		model.addAttribute("rofan",admin.getRofan());
		model.addAttribute("fantasy", admin.getFantasy());
		model.addAttribute("mofan", admin.getMofan());
		model.addAttribute("heroism", admin.getHeroism());
		model.addAttribute("age10", admin.getAge10());
		model.addAttribute("age20", admin.getAge20());
		model.addAttribute("age30", admin.getAge30());
		model.addAttribute("age40", admin.getAge40());
		model.addAttribute("age50", admin.getAge50());
		
		return "/admin/novelAnalysisGenre";
	}
	
	// ī�װ��� Ű���� top5 ��Ʈ
	@RequestMapping("manage/analysis/novel/keyword.rv")
	public String myLogonnovelAnalysisKeyword(Model model) throws Exception
	{
		/*
		 * categoryNumber : Ű���� ī�װ� ��ȣ
		 * keyword100 : ĳ���� Ű���� Top5
		 * keyword200 : ������ Ű���� Top5
		 * keyword300 : ���� Ű���� Top5
		 * keyword400 : ���ڹ��� Ű���� Top5
		 */
		
		int[] categoryNumber = {100,200,300,400,500};
		List keyword100 = null;
		List keyword200 = null;
		List keyword300 = null;
		List keyword400 = null;
		
		for(int i=0; i<categoryNumber.length-1; i++)
		{
			if(categoryNumber[i] != 500)
			{
				keyword.setIndex_keyword_start(categoryNumber[i]);
				keyword.setIndex_keyword_end(categoryNumber[i+1]);
				
				switch(keyword.getIndex_keyword_start())
				{
					case 100 : keyword100 = adminImpl.keywordRank(keyword);
									break;
					case 200 : keyword200 = adminImpl.keywordRank(keyword);
									break;
					case 300 : keyword300 = adminImpl.keywordRank(keyword);
									break;
					case 400 : keyword400 = adminImpl.keywordRank(keyword);
									break;
				}
			}
		}
		
		model.addAttribute("character", keyword100);
		model.addAttribute("atmosphere", keyword200);
		model.addAttribute("material", keyword300);
		model.addAttribute("reaction", keyword400);
		
		return "/admin/novelAnalysisKeyword";
	}
	
	// �α⵵�� top5 ��Ʈ
	@RequestMapping("manage/analysis/novel/best.rv")
	public String myLogonnovelAnalysisBest(Model model) throws Exception
	{
		/*
		 *  manBest : ����ȸ�� �α�Ҽ� ����Ʈ
		 *  womanBest : ����ȸ�� �α�Ҽ� ����Ʈ
		 *  count : link_click ���̺� ����� ����Ʈ ����
		 */
		
		List manBest = null;
		List womanBest = null;
		int count = 0;
		
		count = adminImpl.link_clickCount();
		if(count > 0)
		{
			manBest = adminImpl.genderTopRank(1);
			womanBest = adminImpl.genderTopRank(2);
		}
		
		model.addAttribute("count", count);
		model.addAttribute("manBest", manBest);
		model.addAttribute("womanBest", womanBest);
		
		return "/admin/novelAnalysisBest";
	}
}
