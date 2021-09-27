package ranking;

import java.util.List;

import detail.DetailDTO;

public interface RankingDAOInter {
	
	// 지정한 기간동안 링크클릭수를 계산하고, 많은 순서에 따라 랭킹리스트를 받아온다.
	public List getRankList(RankingDTO ranking) throws Exception;;
	
	// 랭킹리스트(index_book)리스트를 넣어서 작품정보(DetailDTO)를 받아온다
	public List getBook(List rankList) throws Exception;
	
	//전체 링크클릭수를 계산하고, 많은 순서에 따라 랭킹리스트를 받아온다.
	public List getRankList() throws Exception;
	
	//일간 기간을 설정한다. (어제 하루동안)
	public RankingDTO setDaily() throws Exception;
	
	//주간 기간을 설정한다. (지난주 일요일 ~ 지난주 토요일)
	public RankingDTO setWeekly() throws Exception;
	
	//월간 기간을 설정한다. (전달 1일 ~ 전달 마지막일)
	public RankingDTO setMonthly() throws Exception;

}
