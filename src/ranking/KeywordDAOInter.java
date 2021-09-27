package ranking;

import java.util.List;

public interface KeywordDAOInter {
	
	//Keyword_Anal 테이블에서 키워드 가중치(weight)점수 상위 8개 list를 반환한다.
	public List getIndexKeyword(int index_book) throws Exception;
	
	//Keyword 테이블에서 키워드번호(index_keword)를 list로 받아서 키워드(keywords)를 list로 반환한다.
	public List getKeyword(int index_book) throws Exception;
}
