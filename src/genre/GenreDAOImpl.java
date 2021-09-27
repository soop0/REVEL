package genre;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import detail.DetailDTO;

/*
 *  GenreDAOImpl Ŭ����
 *    - GenreDAOInter �������̽��� implements�Ͽ� myBatis�� genreSQL�� �������
 *      sql���� ���� DB���� ���ϴ� ���� �����Ѵ�.
 * 
 */

@Service
public class GenreDAOImpl implements GenreDAOInter {
	
	// myBatis ����
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	// �帣�� ��ü����Ʈ ���� DB���� ������ ���� �޴´�.
	@Override
	public int kindCount(int genre) throws Exception 
	{	return mybatis.selectOne("genre.kindCount", genre);	}

	// �帣�� ��ü����Ʈ�� DB���� ������ ���� �޴´�.
	@Override
	public List kindList(DetailDTO dto) throws Exception 
	{	return mybatis.selectList("genre.kindList", dto);	}

	// �帣 ���� ���� ��ü����Ʈ ���� DB���� ������ ���� �޴´�.
	@Override
	public int totalCount() throws Exception 
	{	return mybatis.selectOne("genre.totalCount");	}

	// �帣 ���� ���� ��ü����Ʈ�� DB���� ������ ���� �޴´�.
	@Override
	public List totalList(DetailDTO dto) throws Exception 
	{	return mybatis.selectList("genre.totalList", dto);	}
}
