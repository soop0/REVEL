package main;

public class SearchDTO 
{
	/*
	 *  <SearchDTO>
	 *    - 검색테마와 검색어를 저장 및 호출하는 클래스
	 *    - theme : 검색 테마
	 *    - searchWord : 검색어
	 *    - startRow : 시작시점
	 *    - endRow : 마지막시점
	 */
	
	private String theme;
	private String searchWord;
	private int startRow;
	private int endRow;
	
	// theme 호출 및 저장
	public void setTheme(String theme)
	{	this.theme = theme;	}
	public String getTheme()
	{	return this.theme;	}
	
	// searchWord 호출 및 저장
	public void setSearchWord(String searchWord)
	{	this.searchWord = searchWord;	}
	public String getSearchWord()
	{	return searchWord;	}
	
	public int getStartRow()
	{	return startRow;	}
	public void setStartRow(int startRow) 
	{	this.startRow = startRow;	}
	
	public int getEndRow()
	{	return endRow;	}
	public void setEndRow(int endRow)
	{	this.endRow = endRow;	}
	
}
