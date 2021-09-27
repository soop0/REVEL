package ranking;

/*
 * KeywordDTO Ŭ����		   : Keyword Table�� �Ӽ����� ���� �� ȣ��
 * index_keyword		   : Ű�����ȣ(ĳ����-100����, ������-200����, ����-300����, ���ڹ���-400����)
 * category				   : Ű���� ī�װ�(ĳ����, ������, ����, ���ڹ���)
 * keywords				   : Ű����
 * words				   : Ű���忡 �ش��ϴ� �ܾ�� 
 * sum					   : Ű���� �հ�����(������ ���)
 * rank	            	   : Ű���� ����(������ ���)
 * index_keyword_start     : Ű�����ȣ ����(������ ���)
 * index_keyword_end	   : Ű�����ȣ ��(������ ���)
 * 
 */
public class KeywordDTO {
	private int index_keyword;
	private String category;
	private String keywords;
	private String words;
	private int sum;
	private int rank;
	private int index_keyword_start;
	private int index_keyword_end;
	
	
	public int getIndex_keyword() {
		return index_keyword;
	}
	public void setIndex_keyword(int index_keyword) {
		this.index_keyword = index_keyword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getIndex_keyword_start() {
		return index_keyword_start;
	}
	public void setIndex_keyword_start(int index_keyword_start) {
		this.index_keyword_start = index_keyword_start;
	}
	
	public int getIndex_keyword_end() {
		return index_keyword_end;
	}
	public void setIndex_keyword_end(int index_keyword_end) {
		this.index_keyword_end = index_keyword_end;
	}
	
	
	
}
