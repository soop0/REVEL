package detail;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailDAOImpl implements DetailDAOInter
{
	// å �������� ���� myBatis
	@Autowired
	private SqlSessionTemplate mybatis = null;

	// å ������ �޴� �޼���
	@Override
	public DetailDTO allDetail(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.allDetail",index_book);	}

	// ��� ���� ��ȸ �޼���
	@Override
	public int reviewCount(int index_book) throws Exception 
	{	return mybatis.selectOne("review.reviewCount",index_book);	}
	
	//�ش� �Ҽ��� ��� ������ detail Table�� review_num�� �����ϴ� �޼���
	@Override
	public void review_num(DetailDTO dto) throws Exception 
	{	mybatis.update("review.review_num", dto);	}
	
	// �ش� å�� ��� ��ȸ �޼���
	@Override
	public List<ReviewDTO> reviewList(ReviewDTO dto) throws Exception 
	{	return mybatis.selectList("review.reviewList",dto);	}
	
	// �ش� å�� ��� �߰� �޼���
	@Override
	public void reviewWrite(ReviewDTO dto) throws Exception 
	{	mybatis.insert("review.reviewWrite",dto); 	}

	// �ش� å�� ��� ���� �޼���
	@Override
	public void reviewDelete(int index_review) throws Exception 
	{	mybatis.delete("review.reviewDelete",index_review); 	}

	// ȸ���� �ش� ��ǰ�� ���� �ο��ߴ��� Ȯ���ϴ� �޼���
	// �� ������ 1, ������ 0
	@Override
	public int ratingCount(RatingMemberDTO dto) throws Exception
	{	return mybatis.selectOne("detail.ratingCount", dto);	}

	// ȸ���� �ش� ��ǰ ������ ���� ���̺�(Rating Table)�� �߰��ϴ� �޼���
	@Override
	public void ratingInsert(RatingMemberDTO dto) throws Exception
	{	mybatis.insert("detail.ratingInsert", dto);	}
	
	// ȸ���� �ش� ��ǰ ������ ���� ���̺�(Rating Table)�� ������Ʈ�ϴ� �޼���
	@Override
	public void ratingUpdate(RatingMemberDTO dto) throws Exception 
	{	mybatis.update("detail.ratingUpdate", dto);	}

	// ȸ���� �ش� ��ǰ ������ ��ȸ�ϴ� �޼���
	@Override
	public int ratingSelect(RatingMemberDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.ratingSelect", dto);	}

	// �ش� ��ǰ�� ������ �ο��� ��ü �ο��� ��ȸ�ϴ� �޼���
	@Override
	public int ratingTotalNum(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.ratingTotalNum",index_book);	}

	// �ش� ��ǰ�� ��ü ��� ���� ��ȸ�ϴ� �޼���
	@Override
	public float ratingAvg(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.ratingAvg",index_book);	}

	// �� �ش� ��ǰ �� ���� ��ü �ο����� ��� ������ detail Table�� ������Ʈ�ϴ� �޼���
	@Override
	public void ratingAnl(DetailDTO detail) throws Exception
	{	mybatis.update("detail.ratingAnl", detail);	}
	
	//ȸ���� �ش� ��ǰ �������� ���ÿ��� Ȯ���ϴ� �޼���
	@Override
	public int rstatusCount(ReadStatusDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.rstatusCount", dto);	}
		
	//ȸ���� �ش� ��ǰ �������¸� �������� ���̺�(Read_Status Table)�� �߰��ϴ� �޼���
	@Override
	public void rstatusInsert(ReadStatusDTO dto) throws Exception 
	{	mybatis.insert("detail.rstatusInsert", dto);	}

	//ȸ���� �ش� ��ǰ �������� ��ȸ�ϴ� �޼���
	@Override
	public int rstatusSelect(ReadStatusDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.rstatusSelect", dto);	}

	//ȸ���� �ش� ��ǰ �������¸� �������� ���̺�(Read_Status Table)�� ������Ʈ�ϴ� �޼���
	@Override
	public void rstatusUpdate(ReadStatusDTO dto) throws Exception 
	{	mybatis.update("detail.rstatusUpdate", dto);	}
	
	//ȸ���� �ش� ��ǰ �������¸� �����Ͽ� �������� ���̺�(Read_Status Table)�� ���� �����ϴ� �޼���
	@Override
	public void rstatusDelete(ReadStatusDTO dto) throws Exception 
	{	mybatis.delete("detail.rstatusDelete", dto);	}
	
	//�ش� ��ǰ�� �������¸� ������ ��ü �ο� ��ȸ�ϴ� �޼���
	@Override
	public int readTotalNum(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.readTotalNum",index_book);	}
	
	// �ش� ��ǰ �������� �߰��ϴ� �޼���
	@Override
	public void detailRstatusAdd(ReadStatusDTO dto) throws Exception 
	{	mybatis.update("detail.detailRstatusAdd", dto);	}
	
	// �ش� ��ǰ �������� �����ϴ� �޼���
	@Override
	public void detailRstatusChange(ReadStatusDTO dto) throws Exception 
	{	mybatis.update("detail.detailRstatusChange", dto);	}
	
	//�ش� ��ǰ �������� ����ϴ� �޼���
	@Override
	public void detailRstatusDelete(ReadStatusDTO dto) throws Exception
	{	mybatis.delete("detail.detailRstatusDelete", dto);	}
	
	//����ڰ� ���� �÷����� �ش� �Ҽ��� link_click ���̺� �ִ��� Ȯ��
	@Override
	public int clickCount(LinkClickDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.clickCount", dto);	}
	
	//ȸ���� �ش� ��ǰ�� ���̹��ø��� ��ũ�� Ŭ���ߴ��� Ȯ���ϴ� �޼���
	@Override
	public int naverSelect(LinkClickDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.naverSelect", dto);	}

	//ȸ���� �ش� ��ǰ�� īī�������� ��ũ�� Ŭ���ߴ��� Ȯ���ϴ� �޼���
	@Override
	public int kakaoSelect(LinkClickDTO dto) throws Exception 
	{	return mybatis.selectOne("detail.kakaoSelect", dto);	}

	//ȸ���� �ش� ��ǰ ���̹�/īī�� ��ũ Ŭ�� ���θ� ��ũŬ�� ���̺�(Link_Click Table)�� �߰��ϴ� �޼���
	@Override
	public void clickInsert(LinkClickDTO dto) throws Exception 
	{	mybatis.insert("detail.clickInsert", dto);	}

	// Ŭ���� �÷��� �� ī��Ʈ �� �����ϴ� �޼���
	@Override
	public void platformUpdate(LinkClickDTO dto) throws Exception 
	{	mybatis.update("detail.platformUpdate", dto);	}
	
	//���� �۰� ��ǰ �� Ȯ���ϴ� �޼���
	@Override
	public int sameWriterCount(int index_book) throws Exception 
	{	return mybatis.selectOne("detail.sameWriterCount", index_book);	}

	//���� �۰� ��ǰ ���� Ȯ���ϴ� �޼���
	@Override
	public List sameWriterList(int index_book) throws Exception 
	{	return mybatis.selectList("detail.sameWriterList", index_book);	}
	
}
