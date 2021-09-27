package detail;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailDAOImpl implements DetailDAOInter
{
	// 책 세부정보 전용 myBatis
	@Autowired
	private SqlSessionTemplate mybatis = null;

	// 책 정보를 받는 메서드
	@Override
	public DetailDTO allDetail(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.allDetail",index_book);	}

	// 댓글 갯수 조회 메서드
	@Override
	public int reviewCount(int index_book) throws Exception 
	{	return mybatis.selectOne("review.reviewCount",index_book);	}
	
	//해당 소설의 댓글 갯수를 detail Table의 review_num에 저장하는 메서드
	@Override
	public void review_num(DetailDTO dto) throws Exception 
	{	mybatis.update("review.review_num", dto);	}
	
	// 해당 책의 댓글 조회 메서드
	@Override
	public List<ReviewDTO> reviewList(ReviewDTO dto) throws Exception 
	{	return mybatis.selectList("review.reviewList",dto);	}
	
	// 해당 책의 댓글 추가 메서드
	@Override
	public void reviewWrite(ReviewDTO dto) throws Exception 
	{	mybatis.insert("review.reviewWrite",dto); 	}

	// 해당 책의 댓글 삭제 메서드
	@Override
	public void reviewDelete(int index_review) throws Exception 
	{	mybatis.delete("review.reviewDelete",index_review); 	}

	// 회원이 해당 작품을 평점 부여했는지 확인하는 메서드
	// 평가 했으면 1, 없으면 0
	@Override
	public int ratingCount(RatingMemberDTO dto) throws Exception
	{	return mybatis.selectOne("detail.ratingCount", dto);	}

	// 회원의 해당 작품 평점을 평점 테이블(Rating Table)에 추가하는 메서드
	@Override
	public void ratingInsert(RatingMemberDTO dto) throws Exception
	{	mybatis.insert("detail.ratingInsert", dto);	}
	
	// 회원의 해당 작품 평점을 평점 테이블(Rating Table)에 업데이트하는 메서드
	@Override
	public void ratingUpdate(RatingMemberDTO dto) throws Exception 
	{	mybatis.update("detail.ratingUpdate", dto);	}

	// 회원의 해당 작품 평점을 조회하는 메서드
	@Override
	public int ratingSelect(RatingMemberDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.ratingSelect", dto);	}

	// 해당 작품의 평점을 부여한 전체 인원을 조회하는 메서드
	@Override
	public int ratingTotalNum(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.ratingTotalNum",index_book);	}

	// 해당 작품의 전체 평균 평점 조회하는 메서드
	@Override
	public float ratingAvg(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.ratingAvg",index_book);	}

	// 각 해당 작품 간 계산된 전체 인원수와 평균 평점을 detail Table에 업데이트하는 메서드
	@Override
	public void ratingAnl(DetailDTO detail) throws Exception
	{	mybatis.update("detail.ratingAnl", detail);	}
	
	//회원별 해당 작품 독서상태 선택여부 확인하는 메서드
	@Override
	public int rstatusCount(ReadStatusDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.rstatusCount", dto);	}
		
	//회원별 해당 작품 독서상태를 독서상태 테이블(Read_Status Table)에 추가하는 메서드
	@Override
	public void rstatusInsert(ReadStatusDTO dto) throws Exception 
	{	mybatis.insert("detail.rstatusInsert", dto);	}

	//회원별 해당 작품 독서상태 조회하는 메서드
	@Override
	public int rstatusSelect(ReadStatusDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.rstatusSelect", dto);	}

	//회원별 해당 작품 독서상태를 독서상태 테이블(Read_Status Table)에 업데이트하는 메서드
	@Override
	public void rstatusUpdate(ReadStatusDTO dto) throws Exception 
	{	mybatis.update("detail.rstatusUpdate", dto);	}
	
	//회원별 해당 작품 독서상태를 해제하여 독서상태 테이블(Read_Status Table)의 행을 삭제하는 메서드
	@Override
	public void rstatusDelete(ReadStatusDTO dto) throws Exception 
	{	mybatis.delete("detail.rstatusDelete", dto);	}
	
	//해당 작품의 독서상태를 선택한 전체 인원 조회하는 메서드
	@Override
	public int readTotalNum(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.readTotalNum",index_book);	}
	
	// 해당 작품 독서상태 추가하는 메서드
	@Override
	public void detailRstatusAdd(ReadStatusDTO dto) throws Exception 
	{	mybatis.update("detail.detailRstatusAdd", dto);	}
	
	// 해당 작품 독서상태 변경하는 메서드
	@Override
	public void detailRstatusChange(ReadStatusDTO dto) throws Exception 
	{	mybatis.update("detail.detailRstatusChange", dto);	}
	
	//해당 작품 독서상태 취소하는 메서드
	@Override
	public void detailRstatusDelete(ReadStatusDTO dto) throws Exception
	{	mybatis.delete("detail.detailRstatusDelete", dto);	}
	
	//사용자가 누른 플랫폼의 해당 소설이 link_click 테이블에 있는지 확인
	@Override
	public int clickCount(LinkClickDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.clickCount", dto);	}
	
	//회원별 해당 작품의 네이버시리즈 링크를 클릭했는지 확인하는 메서드
	@Override
	public int naverSelect(LinkClickDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.naverSelect", dto);	}

	//회원별 해당 작품의 카카오페이지 링크를 클릭했는지 확인하는 메서드
	@Override
	public int kakaoSelect(LinkClickDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.kakaoSelect", dto);	}

	//회원별 해당 작품 네이버/카카오 링크 클릭 여부를 링크클릭 테이블(Link_Click Table)에 추가하는 메서드
	@Override
	public void clickInsert(LinkClickDTO dto) throws Exception 
	{	mybatis.insert("detail.clickInsert", dto);	}

	// 클릭한 플랫폼 별 카운트 수 증가하는 메서드
	@Override
	public void platformUpdate(LinkClickDTO dto) throws Exception 
	{	mybatis.update("detail.platformUpdate", dto);	}
	
	//동일 작가 작품 수 확인하는 메서드
	@Override
	public int sameWriterCount(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.sameWriterCount", index_book);	}

	//동일 작가 작품 정보 확인하는 메서드
	@Override
	public List sameWriterList(int index_book) throws Exception 
	{	return mybatis.selectList("detail.sameWriterList", index_book);	}
	
}
