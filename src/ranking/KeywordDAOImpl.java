package ranking;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class KeywordDAOImpl implements KeywordDAOInter{
	
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	//KeywordDAOImple 클래스 생성
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	//Keyword_Anal 테이블에서 키워드 가중치(weight)점수 상위 8개 list를 반환한다.
	@Override
	public List getIndexKeyword(int index_book) throws Exception{
		return mybatis.selectList("getIndexKeyword", index_book);
	}
	
	/*
	 * Keyword 테이블에서 키워드번호(index_keword)를 list로 받아서 키워드(keywords)를 list로 반환한다.
	 * 
	 * List <KeywordDTO> keywordList = daoKey.getIndexKeyword(index_book) 
	 * 		해당 책번호에서 키워드 가중치가 높은 순서대로 KeywordDTO 형식으로 받아온다.
	 * List list : 키워드 리스트를 만들어서 반환한다.
	 */
	@Override
	public List getKeyword(int index_book) throws Exception{
		List <KeywordDTO> keywordList = daoKey.getIndexKeyword(index_book);
		List list = new ArrayList();
		for(KeywordDTO item : keywordList) {
			list.add(item.getIndex_keyword());
		}
		return mybatis.selectList("getKeyword", list);
	}
}
