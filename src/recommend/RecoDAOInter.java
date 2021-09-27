package recommend;

import java.util.List;
import java.util.Map;

import detail.DetailDTO;
import member.SurveyDTO;

public interface RecoDAOInter {
	
	//로그인한 회원번호(index_member)를 넣어서 회원취향분석테이블(member_keyword_anal_회원번호)을 조회한다. 
	public List <MemKeyAnalDTO> getMemKeyAnal(int index_member) throws Exception;
	
	//작품별키워드분석테이블(keyword_anal)을 조회한다.
	public List <KeyAnalDTO> getKeyAnal() throws Exception;
	
	//추천된 작품번호(index_book)리스트를 넣어서 작품정보를 받아온다.
	public List <DetailDTO> getRecoBook(List recoList) throws Exception;
	
	//분석할 결과를 저장할 임시테이블(temp_anal_index_member)에 데이터가 있는지 확인한다.
	public int isTempAnal(int index_member) throws Exception;
	
	//분석할 결과를 저장할 임시테이블(temp_anal_index_member)을 생성한다.
	public void createTempAnal(int index_member) throws Exception;
	
	//분석한 결과를 임시테이블(temp_anal_index_member)에 insert한다.
	public void insertTempAnal(List tempAnalList) throws Exception;
	
	//임시테이블에서 연관도 오름차순으로 추천리스트를 받아온다.
	public List getRecoList(int number) throws Exception;
	
	//회원키워드 누적점수(취향)과 작품의 키워드 가중치를 비교한다.
	public Map setKeyAnalValue(int index_member) throws Exception;
	
	/* 
	 * 추천리스트에서 회원번호에 따라 제외할 작품목록(read Table의 관심없다(0), 다읽었다(3), 
	 * rating Table의 회원(index_member)이 평점 준 작품들)
	 */
	public List getOutList(int index_member) throws Exception;
	
	//survey 테이블에서 회원(index_member)의 정보를 불러온다.
	public SurveyDTO getSurvey(int index_member) throws Exception;
	
	//TempAnal 테이블의 데이터들을 지운다.
	public void clearValue(int index_member) throws Exception;
}
