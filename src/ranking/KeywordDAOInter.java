package ranking;

import java.util.List;

public interface KeywordDAOInter {
	
	//Keyword_Anal ���̺��� Ű���� ����ġ(weight)���� ���� 8�� list�� ��ȯ�Ѵ�.
	public List getIndexKeyword(int index_book) throws Exception;
	
	//Keyword ���̺��� Ű�����ȣ(index_keword)�� list�� �޾Ƽ� Ű����(keywords)�� list�� ��ȯ�Ѵ�.
	public List getKeyword(int index_book) throws Exception;
}
