package admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import detail.ReviewDTO;
import ranking.KeywordDTO;

@Service
public class AdminDAOImpl implements AdminDAOInter
{
	// admin 전용 myBatis
	@Autowired
	private SqlSessionTemplate mybatis = null;

	// 전체 회원수 검색
	@Override
	public int memberCount() throws Exception 
	{	return mybatis.selectOne("admin.memberCount");	}
	
	// 네이버 회원수 검색
	@Override
	public int naverCount() throws Exception 
	{	return mybatis.selectOne("admin.naverCount");	}

	// 카카오 회원수 검색
	@Override
	public int kakaoCount() throws Exception
	{	return mybatis.selectOne("admin.kakaoCount");	}
	
	// 탈퇴 회원수 검색
	@Override
	public int withdrawalCount() throws Exception 
	{	return mybatis.selectOne("admin.withdrawalCount");	}
	
	// 남성 회원수 검색
	@Override
	public int manCount() throws Exception 
	{	return mybatis.selectOne("admin.manCount");	}

	// 여성 회원수 검색
	@Override
	public int womanCount() throws Exception 
	{	return mybatis.selectOne("admin.womanCount");	}

	// 10대 회원수 검색
	@Override
	public int count10() throws Exception 
	{	return mybatis.selectOne("admin.count10");	}

	// 20대 회원수 검색
	@Override
	public int count20() throws Exception
	{	return mybatis.selectOne("admin.count20");	}

	// 30대 회원수 검색
	@Override
	public int count30() throws Exception 
	{	return mybatis.selectOne("admin.count30");	}

	// 40대 회원수 검색
	@Override
	public int count40() throws Exception
	{	return mybatis.selectOne("admin.count40");	}

	// 50대 이상 회원수 검색
	@Override
	public int count50() throws Exception 
	{	return mybatis.selectOne("admin.count50");	}
	
	// 흥미감소 탈퇴사유수 검색
	@Override
	public int interestLost(String interestLost) throws Exception 
	{	return mybatis.selectOne("admin.interestLost",interestLost);	}

	// 컨텐츠 부족 탈퇴사유수 검색
	@Override
	public int contentNot(String contentNot) throws Exception 
	{	return mybatis.selectOne("admin.contentNot", contentNot);	}

	// 타서비스이용 탈퇴사유수 검색
	@Override
	public int serviceChange(String serviceChange) throws Exception
	{	return mybatis.selectOne("admin.serviceChange", serviceChange);	}

	// 서비스불편 탈퇴사유수 검색
	@Override
	public int serviceNot(String serviceNot) throws Exception 
	{	return mybatis.selectOne("admin.serviceNot", serviceNot);	}

	// 기타 탈퇴사유수 검색
	@Override
	public int etc(WithdrawalDTO dto) throws Exception 
	{	return mybatis.selectOne("admin.etc", dto);	}
	
	// 전체 회원 리스트 검색
	@Override
	public List memberList() throws Exception 
	{	return mybatis.selectList("admin.memberList");	}
	
	// 네이버 회원 리스트 검색
	@Override
	public List naverList() throws Exception
	{	return mybatis.selectList("admin.naverList");	}
	
	// 카카오 회원 리스트 검색
	@Override
	public List kakaoList() throws Exception 
	{	return mybatis.selectList("admin.kakaoList");	}

	// 탈퇴 회원 리스트 검색
	@Override
	public List withdrawalList() throws Exception 
	{	return mybatis.selectList("admin.withdrawalList");	}
	
	// 소설 전체 리스트 수 검색
	@Override
	public int bookListCount() throws Exception
	{	return mybatis.selectOne("admin.bookListCount");	}
	
	// 소설 전체 리스트 검색
	@Override
	public List bookList() throws Exception 
	{	return mybatis.selectList("admin.bookList");	}
	
	// 읽을거다 누른 작품 수 확인
	@Override
	public int read_wantCount() throws Exception
	{	return mybatis.selectOne("admin.read_wantCount");	}

	// 읽고있다 누른 작품 수 확인
	@Override
	public int read_ingCount() throws Exception 
	{	return mybatis.selectOne("admin.read_ingCount");	}

	// 다읽었다 누른 작품 수 확인
	@Override
	public int read_endCount() throws Exception
	{	return mybatis.selectOne("admin.read_endCount");	}

	// 관심없다 누른 작품 수 확인
	@Override
	public int read_notCount() throws Exception 
	{	return mybatis.selectOne("admin.read_notCount");	}
	
	// 독서상태별 랭킹 순위 갯수 확인
	@Override
	public int readRankingCount(String read) throws Exception 
	{	return mybatis.selectOne("admin.readRankingCount", read);	}
	
	// 독서상태별 랭킹 순위 리스트
	@Override
	public List readRankList(String read) throws Exception 
	{	return mybatis.selectList("admin.readRank", read);	}
	
	// 전체 리뷰수 검색
	@Override
	public int reviewCount() throws Exception
	{	return mybatis.selectOne("admin.reviewCount");	}

	// 전체 댓글 리스트 검색
	@Override
	public List reviewList() throws Exception 
	{	return mybatis.selectList("admin.reviewList");	}
	
	// 삭제하기 위해 선택한 댓글이 있는지 검색
	@Override
	public int reviewDelCount(ReviewDTO review) throws Exception 
	{	return mybatis.selectOne("admin.reviewDelCount", review);	}

	// 선택한 댓글 삭제하기(댓글 내용은 없애고, 클린봇 알림 메시지로 변경)
	@Override
	public void reviewDel(ReviewDTO review) throws Exception
	{	mybatis.update("admin.reviewDel", review);	}
	
	// 최다 댓글 닉네임 순위 갯수 검색
	@Override
	public int reviewNickCount() throws Exception
	{	return mybatis.selectOne("admin.reviewNickCount");	}

	// 최다 댓글 소설 순위 갯수 검색
	@Override
	public int reviewBookCount() throws Exception
	{	return mybatis.selectOne("admin.reviewBookCount");	}
	
	// 최다 댓글 닉네임 순위 검색
	@Override
	public List reviewNickRank() throws Exception 
	{	return mybatis.selectList("admin.reviewNickRank");	}

	// 최다 댓글 소설 순위 검색
	@Override
	public List reviewBookRank() throws Exception 
	{	return mybatis.selectList("admin.reviewBookRank");	}

	// 전체 공지사항 리스트 검색
	@Override
	public List noticeList() throws Exception 
	{	return mybatis.selectList("admin.noticeList");	}

	// 클린봇 삭제 댓글 수 검색
	@Override
	public int cleanbotCount() throws Exception 
	{	return mybatis.selectOne("admin.cleanbotCount");	}
	
	// 클린봇 삭제 댓글 리스트 검색
	@Override
	public List cleanbotList() throws Exception 
	{	return mybatis.selectList("admin.cleanbotList");	}

	// 장르별 별점 갯수 검색
	@Override
	public int genreRatingCount(AdminDTO dto) throws Exception
	{	return mybatis.selectOne("admin.genreRatingCount", dto);	}

	// 장르별 연령 갯수 검색
	@Override
	public int genreAgeCount(AdminDTO dto) throws Exception 
	{	return mybatis.selectOne("admin.genreAgeCount", dto);	}

	// 장르별 연령 플랫폼 링크수 검색
	@Override
	public AdminDTO genreAgePlatform(AdminDTO dto) throws Exception
	{	return mybatis.selectOne("admin.genreAgePlatform", dto);	}

	// 키워드 카테고리별 순위 검색
	@Override
	public List keywordRank(KeywordDTO dto) throws Exception 
	{	return mybatis.selectList("admin.keywordRank", dto);	}

	// 성별 인기 작품 순위 검색
	@Override
	public List genderTopRank(int gender) throws Exception 
	{	return mybatis.selectList("admin.genderTopRank", gender);	}

	// link_click 테이블에 저장된 리스트 갯수 확인
	@Override
	public int link_clickCount() throws Exception 
	{	return mybatis.selectOne("admin.link_clickCount");	}
	
}
