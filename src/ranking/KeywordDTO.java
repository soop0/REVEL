package ranking;

/*
 * KeywordDTO 클래스		   : Keyword Table의 속성들을 저장 및 호출
 * index_keyword		   : 키워드번호(캐릭터-100번대, 분위기-200번대, 소재-300번대, 독자반응-400번대)
 * category				   : 키워드 카테고리(캐릭터, 분위기, 소재, 독자반응)
 * keywords				   : 키워드
 * words				   : 키워드에 해당하는 단어들 
 * sum					   : 키워드 합계점수(관리자 통계)
 * rank	            	   : 키워드 순위(관리자 통계)
 * index_keyword_start     : 키워드번호 시작(관리자 통계)
 * index_keyword_end	   : 키워드번호 끝(관리자 통계)
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
