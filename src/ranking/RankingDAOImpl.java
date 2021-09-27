package ranking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import detail.DetailDTO;

public class RankingDAOImpl implements RankingDAOInter{
	
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	@Autowired
	private RankingDTO ranking = null;
	
	//지정한 기간동안 링크클릭수를 계산하고, 많은 순서에 따라 랭킹리스트를 받아온다.
	@Override
	public List getRankList(RankingDTO ranking) {
		List <RankingDTO> totalList = mybatis.selectList("getRankList", ranking);
		List rankList = new ArrayList();
		int index = 0;
		for(RankingDTO total : totalList) {
			rankList.add(total.getIndex_book());
			if(index > 20) {break;}
			index++;
		}	
		return rankList;
	}
	
	//랭킹리스트(index_book)리스트를 넣어서 작품정보(DetailDTO)를 받아온다
	@Override
	public List <DetailDTO> getBook(List rankList){
		return mybatis.selectList("getBook", rankList);
	}
	
	//전체 링크클릭수를 계산하고, 많은 순서에 따라 랭킹리스트를 받아온다.
	@Override
	public List getRankList() throws Exception{
		List <RankingDTO> totalList = mybatis.selectList("getTotalRankList");
		List rankList = new ArrayList();
		int index = 0;
		for(RankingDTO total : totalList) {
			rankList.add(total.getIndex_book());
			if(index > 20) {break;}
			index++;
		}			
		
		return rankList;
	}
	
	//일간 기간을 설정한다. (어제 하루동안)
	@Override
	public RankingDTO setDaily() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		Date setDate =sdf.parse(today);
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(setDate);
		cal.add(Calendar.DATE, -1);
		String endDay = today;
		String startDay = sdf.format(cal.getTime());
		
		ranking.setEndDay(endDay);
		ranking.setStartDay(startDay);
		
		return ranking;
	}
	
	//주간 기간을 설정한다.(지난주 일요일 ~ 지난주 토요일)
	@Override
	public RankingDTO setWeekly() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		Date setDate =sdf.parse(today);
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(setDate);
		cal.add(Calendar.DAY_OF_WEEK, -7);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String startDay = sdf.format(cal.getTime());
		
		cal.add(Calendar.DAY_OF_WEEK, 7);
		cal.setFirstDayOfWeek(Calendar.SATURDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String endDay = sdf.format(cal.getTime());

		ranking.setEndDay(endDay);
		ranking.setStartDay(startDay);
		
		return ranking;
	}
	
	//월간 기간을 설정한다. (전달 1일 ~ 전달 마지막일)	
	@Override
	public RankingDTO setMonthly() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		Date setDate =sdf.parse(today);
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(setDate);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, 1);
		String startDay = sdf.format(cal.getTime());
		
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String endDay = sdf.format(cal.getTime());
		
		ranking.setEndDay(endDay);
		ranking.setStartDay(startDay);
		
		return ranking;
	}
	
	
}
