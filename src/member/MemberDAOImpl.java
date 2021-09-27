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
	
	// ���� ��� ���� �ҷ�����
	@Override
	public int myReviewCount(int index_member) throws Exception
	{	return mybatis.selectOne("member.myReviewCount", index_member);	}

	// ���� ��� ��� �ҷ�����
	@Override
	public List myReview(int index_member) throws Exception 
	{	return mybatis.selectList("member.myReview", index_member);	}

	// �����ϱ� ���� ������ ���� �� ����� �ִ��� Ȯ���ϱ�
	@Override
	public int myReviewDelCount(ReviewDTO review) throws Exception
	{	return mybatis.selectOne("member.myReviewDelCount", review);	}

	// ������ ���� �� ��� �����ϱ�
	@Override
	public void myReviewDel(ReviewDTO review) throws Exception 
	{	mybatis.delete("member.myReviewDel", review);	}

	
	

}
