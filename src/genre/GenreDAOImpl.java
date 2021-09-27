package genre;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import detail.DetailDTO;

/*
 *  GenreDAOImpl 클래스
 *    - GenreDAOInter 인터페이스를 implements하여 myBatis의 genreSQL을 실행시켜
 *      sql문에 따른 DB에서 원하는 값을 리턴한다.
 * 
 */

@Service
public class GenreDAOImpl implements GenreDAOInter {
	
	// myBatis 생성
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	// 장르별 전체리스트 수를 DB에서 가져와 리턴 받는다.
	@Override
	public int kindCount(int genre) throws Exception 
	{	return mybatis.selectOne("genre.kindCount", genre);	}

	// 장르별 전체리스트를 DB에서 가져와 리턴 받는다.
	@Override
	public List kindList(DetailDTO dto) throws Exception 
	{	return mybatis.selectList("genre.kindList", dto);	}

	// 장르 구분 없이 전체리스트 수를 DB에서 가져와 리턴 받는다.
	@Override
	public int totalCount() throws Exception 
	{	return mybatis.selectOne("genre.totalCount");	}

	// 장르 구분 없이 전체리스트를 DB에서 가져와 리턴 받는다.
	@Override
	public List totalList(DetailDTO dto) throws Exception 
	{	return mybatis.selectList("genre.totalList", dto);	}
}
