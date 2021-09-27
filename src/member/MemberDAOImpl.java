package member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import detail.ReviewDTO;

@Service
public class MemberDAOImpl implements MemberDAOInter{

	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	@Override
	public MemberDTO getMember(MemberDTO member) throws Exception {
		return mybatis.selectOne("getmember", member);
	}

	@Override
	public void nickChange(MemberDTO dto) throws Exception {
		mybatis.selectOne("member.nickchange",dto);
	}

	@Override
	public int nickCheck(String nick) throws Exception 
	{	return mybatis.selectOne("member.nickcheck",nick);	}

	@Override
	public SurveyDTO getSurvey(int index_member) throws Exception {
		return mybatis.selectOne("member.getsurvey",index_member);
	}

	@Override
	public void genreChange(SurveyDTO survey) throws Exception {
		mybatis.selectOne("member.genrechange",survey);
	}

	@Override
	public void volumeChange(SurveyDTO survey) throws Exception {
		mybatis.selectOne("member.volumechange",survey);
	}

	@Override
	public void updateWM(MemberDTO member) throws Exception {
		mybatis.selectOne("member.updatemw",member);
	}
	
	// 나의 댓글 갯수 불러오기
	@Override
	public int myReviewCount(int index_member) throws Exception
	{	return mybatis.selectOne("member.myReviewCount", index_member);	}

	// 나의 댓글 목록 불러오기
	@Override
	public List myReview(int index_member) throws Exception 
	{	return mybatis.selectList("member.myReview", index_member);	}

	// 삭제하기 위해 선택한 내가 쓴 댓글이 있는지 확인하기
	@Override
	public int myReviewDelCount(ReviewDTO review) throws Exception
	{	return mybatis.selectOne("member.myReviewDelCount", review);	}

	// 선택한 내가 쓴 댓글 삭제하기
	@Override
	public void myReviewDel(ReviewDTO review) throws Exception 
	{	mybatis.delete("member.myReviewDel", review);	}

	
	

}
