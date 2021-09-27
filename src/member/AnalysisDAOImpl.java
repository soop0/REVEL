package member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import detail.RatingMemberDTO;

/*
 *  AnalysisDAOImpl 클래스
 *   AnalysisDAOInter 인터페이스를 implements하여
 *   myBatis의 analSQL을 실행시켜 sql문에 따른 DB에서 원하는 값을 리턴한다.
 * 
 */

@Service
public class AnalysisDAOImpl implements AnalysisDAOInter {

	//mybatis 생성
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	//회원이 평가한 전체 작품 목록의 수 가져오기
	@Override
	public int totalCount(int index_member) throws Exception {		
		return mybatis.selectOne("analysis.totalcount",index_member);
	}
	//회원 평점별 개수 가져오기 
	@Override
	public int ratingCount(RatingMemberDTO rating) throws Exception {
		return mybatis.selectOne("analysis.ratingcount", rating);
	}
	
	//평가한 작품 + 작품디테일 불러오기
	@Override
	public List getRatingDetail(int index_member) throws Exception {
		return mybatis.selectList("analysis.getratingdetail",index_member);
	}

	//회원이 평가한 특정 작품 불러오기
	@Override
	public RatingMemberDTO getRatingList(RatingMemberDTO rating) throws Exception {
		return mybatis.selectOne("analysis.getrating", rating);
	}

	//평가점수 삭제하기
	@Override
	public void deleteRating(RatingMemberDTO rating) throws Exception {
		mybatis.delete("analysis.deleterating",rating);
	}
	
	//평가작품의 키워드와 가중치 목록 가져오기
	@Override
	public List getKeywordList(int index_member) throws Exception {
		return mybatis.selectList("analysis.getkeywordlist",index_member);
	}
	
	//작품평가시 누적점수 업데이트
	@Override
	public void updateMemberScore(RatingMemberDTO rating) throws Exception {
		mybatis.update("analysis.updatescore",rating);
	}
	
	//작품 상태별 개수 가져오기
	@Override
	public int readCount(MemberReadDTO read) throws Exception {
		return mybatis.selectOne("analysis.readcount",read);
	}

	//작품 상태별 목록 가져오기
	@Override
	public List getReadList(MemberReadDTO read) throws Exception {
		return mybatis.selectList("analysis.getreadlist",read);
	}
	
	//독서상태 변경하기
	@Override
	public void updateRead(MemberReadDTO read) throws Exception {
		mybatis.update("analysis.updateread", read);
	}
	
	//독서상태 삭제하기
	@Override
	public void deleteRead(MemberReadDTO read) throws Exception {
		mybatis.delete("analysis.deleteread",read);
	}
	
	//회원분석 선호키워드 top20가져오기
	@Override
	public List getMKeyword(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword",index_member);
	}
	
	//회원분석 선호키워드 캐릭터-카테고리 6개 가져오기
	@Override
	public List getCKeyword_1(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword1",index_member);
	}
	
	//회원분석 선호키워드 분위기-카테고리 6개 가져오기
	@Override
	public List getCKeyword_2(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword2",index_member);
	}
	
	//회원분석 선호키워드 소재-카테고리 6개 가져오기
	@Override
	public List getCKeyword_3(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword3",index_member);
	}
	//회원분석 선호키워드 독자반응-카테고리 3개 가져오기
	@Override
	public List getCKeyword_4(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword4",index_member);
	}


	//회원분석 선호장르 장르별 count가져오기
	@Override
	public int genreCount(MemberReadDTO read) throws Exception {
		return mybatis.selectOne("analysis.genrecount", read);
	}

	//회원분석 선호장르 장르별 댓글 count가져오기
	@Override
	public int reviewCount(MemberReadDTO read) throws Exception {
		return mybatis.selectOne("analysis.reviewcount",read);
	}


	

	

}
