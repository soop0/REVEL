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
	// admin ���� myBatis
	@Autowired
	private SqlSessionTemplate mybatis = null;

	// ��ü ȸ���� �˻�
	@Override
	public int memberCount() throws Exception 
	{	return mybatis.selectOne("admin.memberCount");	}
	
	// ���̹� ȸ���� �˻�
	@Override
	public int naverCount() throws Exception 
	{	return mybatis.selectOne("admin.naverCount");	}

	// īī�� ȸ���� �˻�
	@Override
	public int kakaoCount() throws Exception
	{	return mybatis.selectOne("admin.kakaoCount");	}
	
	// Ż�� ȸ���� �˻�
	@Override
	public int withdrawalCount() throws Exception 
	{	return mybatis.selectOne("admin.withdrawalCount");	}
	
	// ���� ȸ���� �˻�
	@Override
	public int manCount() throws Exception 
	{	return mybatis.selectOne("admin.manCount");	}

	// ���� ȸ���� �˻�
	@Override
	public int womanCount() throws Exception 
	{	return mybatis.selectOne("admin.womanCount");	}

	// 10�� ȸ���� �˻�
	@Override
	public int count10() throws Exception 
	{	return mybatis.selectOne("admin.count10");	}

	// 20�� ȸ���� �˻�
	@Override
	public int count20() throws Exception
	{	return mybatis.selectOne("admin.count20");	}

	// 30�� ȸ���� �˻�
	@Override
	public int count30() throws Exception 
	{	return mybatis.selectOne("admin.count30");	}

	// 40�� ȸ���� �˻�
	@Override
	public int count40() throws Exception
	{	return mybatis.selectOne("admin.count40");	}

	// 50�� �̻� ȸ���� �˻�
	@Override
	public int count50() throws Exception 
	{	return mybatis.selectOne("admin.count50");	}
	
	// ��̰��� Ż������� �˻�
	@Override
	public int interestLost(String interestLost) throws Exception 
	{	return mybatis.selectOne("admin.interestLost",interestLost);	}

	// ������ ���� Ż������� �˻�
	@Override
	public int contentNot(String contentNot) throws Exception 
	{	return mybatis.selectOne("admin.contentNot", contentNot);	}

	// Ÿ�����̿� Ż������� �˻�
	@Override
	public int serviceChange(String serviceChange) throws Exception
	{	return mybatis.selectOne("admin.serviceChange", serviceChange);	}

	// ���񽺺��� Ż������� �˻�
	@Override
	public int serviceNot(String serviceNot) throws Exception 
	{	return mybatis.selectOne("admin.serviceNot", serviceNot);	}

	// ��Ÿ Ż������� �˻�
	@Override
	public int etc(WithdrawalDTO dto) throws Exception 
	{	return mybatis.selectOne("admin.etc", dto);	}
	
	// ��ü ȸ�� ����Ʈ �˻�
	@Override
	public List memberList() throws Exception 
	{	return mybatis.selectList("admin.memberList");	}
	
	// ���̹� ȸ�� ����Ʈ �˻�
	@Override
	public List naverList() throws Exception
	{	return mybatis.selectList("admin.naverList");	}
	
	// īī�� ȸ�� ����Ʈ �˻�
	@Override
	public List kakaoList() throws Exception 
	{	return mybatis.selectList("admin.kakaoList");	}

	// Ż�� ȸ�� ����Ʈ �˻�
	@Override
	public List withdrawalList() throws Exception 
	{	return mybatis.selectList("admin.withdrawalList");	}
	
	// �Ҽ� ��ü ����Ʈ �� �˻�
	@Override
	public int bookListCount() throws Exception
	{	return mybatis.selectOne("admin.bookListCount");	}
	
	// �Ҽ� ��ü ����Ʈ �˻�
	@Override
	public List bookList() throws Exception 
	{	return mybatis.selectList("admin.bookList");	}
	
	// �����Ŵ� ���� ��ǰ �� Ȯ��
	@Override
	public int read_wantCount() throws Exception
	{	return mybatis.selectOne("admin.read_wantCount");	}

	// �а��ִ� ���� ��ǰ �� Ȯ��
	@Override
	public int read_ingCount() throws Exception 
	{	return mybatis.selectOne("admin.read_ingCount");	}

	// ���о��� ���� ��ǰ �� Ȯ��
	@Override
	public int read_endCount() throws Exception
	{	return mybatis.selectOne("admin.read_endCount");	}

	// ���ɾ��� ���� ��ǰ �� Ȯ��
	@Override
	public int read_notCount() throws Exception 
	{	return mybatis.selectOne("admin.read_notCount");	}
	
	// �������º� ��ŷ ���� ���� Ȯ��
	@Override
	public int readRankingCount(String read) throws Exception 
	{	return mybatis.selectOne("admin.readRankingCount", read);	}
	
	// �������º� ��ŷ ���� ����Ʈ
	@Override
	public List readRankList(String read) throws Exception 
	{	return mybatis.selectList("admin.readRank", read);	}
	
	// ��ü ����� �˻�
	@Override
	public int reviewCount() throws Exception
	{	return mybatis.selectOne("admin.reviewCount");	}

	// ��ü ��� ����Ʈ �˻�
	@Override
	public List reviewList() throws Exception 
	{	return mybatis.selectList("admin.reviewList");	}
	
	// �����ϱ� ���� ������ ����� �ִ��� �˻�
	@Override
	public int reviewDelCount(ReviewDTO review) throws Exception 
	{	return mybatis.selectOne("admin.reviewDelCount", review);	}

	// ������ ��� �����ϱ�(��� ������ ���ְ�, Ŭ���� �˸� �޽����� ����)
	@Override
	public void reviewDel(ReviewDTO review) throws Exception
	{	mybatis.update("admin.reviewDel", review);	}
	
	// �ִ� ��� �г��� ���� ���� �˻�
	@Override
	public int reviewNickCount() throws Exception
	{	return mybatis.selectOne("admin.reviewNickCount");	}

	// �ִ� ��� �Ҽ� ���� ���� �˻�
	@Override
	public int reviewBookCount() throws Exception
	{	return mybatis.selectOne("admin.reviewBookCount");	}
	
	// �ִ� ��� �г��� ���� �˻�
	@Override
	public List reviewNickRank() throws Exception 
	{	return mybatis.selectList("admin.reviewNickRank");	}

	// �ִ� ��� �Ҽ� ���� �˻�
	@Override
	public List reviewBookRank() throws Exception 
	{	return mybatis.selectList("admin.reviewBookRank");	}

	// ��ü �������� ����Ʈ �˻�
	@Override
	public List noticeList() throws Exception 
	{	return mybatis.selectList("admin.noticeList");	}

	// Ŭ���� ���� ��� �� �˻�
	@Override
	public int cleanbotCount() throws Exception 
	{	return mybatis.selectOne("admin.cleanbotCount");	}
	
	// Ŭ���� ���� ��� ����Ʈ �˻�
	@Override
	public List cleanbotList() throws Exception 
	{	return mybatis.selectList("admin.cleanbotList");	}

	// �帣�� ���� ���� �˻�
	@Override
	public int genreRatingCount(AdminDTO dto) throws Exception
	{	return mybatis.selectOne("admin.genreRatingCount", dto);	}

	// �帣�� ���� ���� �˻�
	@Override
	public int genreAgeCount(AdminDTO dto) throws Exception 
	{	return mybatis.selectOne("admin.genreAgeCount", dto);	}

	// �帣�� ���� �÷��� ��ũ�� �˻�
	@Override
	public AdminDTO genreAgePlatform(AdminDTO dto) throws Exception
	{	return mybatis.selectOne("admin.genreAgePlatform", dto);	}

	// Ű���� ī�װ��� ���� �˻�
	@Override
	public List keywordRank(KeywordDTO dto) throws Exception 
	{	return mybatis.selectList("admin.keywordRank", dto);	}

	// ���� �α� ��ǰ ���� �˻�
	@Override
	public List genderTopRank(int gender) throws Exception 
	{	return mybatis.selectList("admin.genderTopRank", gender);	}

	// link_click ���̺� ����� ����Ʈ ���� Ȯ��
	@Override
	public int link_clickCount() throws Exception 
	{	return mybatis.selectOne("admin.link_clickCount");	}
	
}
