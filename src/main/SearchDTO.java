package main;

public class SearchDTO 
{
	/*
	 *  <SearchDTO>
	 *    - �˻��׸��� �˻�� ���� �� ȣ���ϴ� Ŭ����
	 *    - theme : �˻� �׸�
	 *    - searchWord : �˻���
	 *    - startRow : ���۽���
	 *    - endRow : ����������
	 */
	
	private String theme;
	private String searchWord;
	private int startRow;
	private int endRow;
	
	// theme ȣ�� �� ����
	public void setTheme(String theme)
	{	this.theme = theme;	}
	public String getTheme()
	{	return this.theme;	}
	
	// searchWord ȣ�� �� ����
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
