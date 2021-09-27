package member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import detail.RatingMemberDTO;

/*
 *  AnalysisDAOImpl Ŭ����
 *   AnalysisDAOInter �������̽��� implements�Ͽ�
 *   myBatis�� analSQL�� ������� sql���� ���� DB���� ���ϴ� ���� �����Ѵ�.
 * 
 */

@Service
public class AnalysisDAOImpl implements AnalysisDAOInter {

	//mybatis ����
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	//ȸ���� ���� ��ü ��ǰ ����� �� ��������
	@Override
	public int totalCount(int index_member) throws Exception {		
		return mybatis.selectOne("analysis.totalcount",index_member);
	}
	//ȸ�� ������ ���� �������� 
	@Override
	public int ratingCount(RatingMemberDTO rating) throws Exception {
		return mybatis.selectOne("analysis.ratingcount", rating);
	}
	
	//���� ��ǰ + ��ǰ������ �ҷ�����
	@Override
	public List getRatingDetail(int index_member) throws Exception {
		return mybatis.selectList("analysis.getratingdetail",index_member);
	}

	//ȸ���� ���� Ư�� ��ǰ �ҷ�����
	@Override
	public RatingMemberDTO getRatingList(RatingMemberDTO rating) throws Exception {
		return mybatis.selectOne("analysis.getrating", rating);
	}

	//������ �����ϱ�
	@Override
	public void deleteRating(RatingMemberDTO rating) throws Exception {
		mybatis.delete("analysis.deleterating",rating);
	}
	
	//����ǰ�� Ű����� ����ġ ��� ��������
	@Override
	public List getKeywordList(int index_member) throws Exception {
		return mybatis.selectList("analysis.getkeywordlist",index_member);
	}
	
	//��ǰ�򰡽� �������� ������Ʈ
	@Override
	public void updateMemberScore(RatingMemberDTO rating) throws Exception {
		mybatis.update("analysis.updatescore",rating);
	}
	
	//��ǰ ���º� ���� ��������
	@Override
	public int readCount(MemberReadDTO read) throws Exception {
		return mybatis.selectOne("analysis.readcount",read);
	}

	//��ǰ ���º� ��� ��������
	@Override
	public List getReadList(MemberReadDTO read) throws Exception {
		return mybatis.selectList("analysis.getreadlist",read);
	}
	
	//�������� �����ϱ�
	@Override
	public void updateRead(MemberReadDTO read) throws Exception {
		mybatis.update("analysis.updateread", read);
	}
	
	//�������� �����ϱ�
	@Override
	public void deleteRead(MemberReadDTO read) throws Exception {
		mybatis.delete("analysis.deleteread",read);
	}
	
	//ȸ���м� ��ȣŰ���� top20��������
	@Override
	public List getMKeyword(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword",index_member);
	}
	
	//ȸ���м� ��ȣŰ���� ĳ����-ī�װ� 6�� ��������
	@Override
	public List getCKeyword_1(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword1",index_member);
	}
	
	//ȸ���м� ��ȣŰ���� ������-ī�װ� 6�� ��������
	@Override
	public List getCKeyword_2(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword2",index_member);
	}
	
	//ȸ���м� ��ȣŰ���� ����-ī�װ� 6�� ��������
	@Override
	public List getCKeyword_3(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword3",index_member);
	}
	//ȸ���м� ��ȣŰ���� ���ڹ���-ī�װ� 3�� ��������
	@Override
	public List getCKeyword_4(int index_member) throws Exception {
		return mybatis.selectList("analysis.getmkeyword4",index_member);
	}


	//ȸ���м� ��ȣ�帣 �帣�� count��������
	@Override
	public int genreCount(MemberReadDTO read) throws Exception {
		return mybatis.selectOne("analysis.genrecount", read);
	}

	//ȸ���м� ��ȣ�帣 �帣�� ��� count��������
	@Override
	public int reviewCount(MemberReadDTO read) throws Exception {
		return mybatis.selectOne("analysis.reviewcount",read);
	}


	

	

}
