package ranking;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class KeywordDAOImpl implements KeywordDAOInter{
	
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	//KeywordDAOImple Ŭ���� ����
	@Autowired
	private KeywordDAOInter daoKey = null;
	
	//Keyword_Anal ���̺��� Ű���� ����ġ(weight)���� ���� 8�� list�� ��ȯ�Ѵ�.
	@Override
	public List getIndexKeyword(int index_book) throws Exception{
		return mybatis.selectList("getIndexKeyword", index_book);
	}
	
	/*
	 * Keyword ���̺��� Ű�����ȣ(index_keword)�� list�� �޾Ƽ� Ű����(keywords)�� list�� ��ȯ�Ѵ�.
	 * 
	 * List <KeywordDTO> keywordList = daoKey.getIndexKeyword(index_book) 
	 * 		�ش� å��ȣ���� Ű���� ����ġ�� ���� ������� KeywordDTO �������� �޾ƿ´�.
	 * List list : Ű���� ����Ʈ�� ���� ��ȯ�Ѵ�.
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
