package main;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.AnalysisDAOInter;
import member.MemberReadDTO;

@Service
public class MainDAOImpl implements MainDAOInter
{
	
	// Controller에서 스캔된 클래스에서만 Autowired를 사용할 수 있다.
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	// AnalysisDAOInter 클래스
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	// main페이지의 main 슬라이드에 해당하는 책리스트를 조회한다. 랜덤으로 5작품을 선택하여 책정보(DetailDTO)를 가져온다. 
	@Override
	public List mainBook() throws Exception		
	{	return mybatis.selectList("main.mainBook"); 	}

	// 검색결과 수 조회
	@Override
	public int searchCount(SearchDTO dto) throws Exception
	{	return mybatis.selectOne("main.searchCount", dto);	}

	// 검색결과 리스트 조회
	@Override
	public List searchList(SearchDTO dto) throws Exception 
	{	return mybatis.selectList("main.searchList", dto);	}
	
	// 회원번호와 독서상태를 받아서 독서상태리스트(index_book)를 반환한다.
	@Override
	public List getReadList(MemberReadDTO read) throws Exception{
		List <MemberReadDTO> readTotalList = analImpl.getReadList(read);
		List readList = new ArrayList();
		int index = 0;
		for(MemberReadDTO r : readTotalList) {
			readList.add(r.getIndex_book());
			if(index > 1) {break;}
			index++;
		}
			
		return readList;
	}
		
	// 책번호리스트를 받아서 책정보(Detail DTO)리스트를 반환한다.
	@Override
	public List getReadBook(List readList) throws Exception{
		return mybatis.selectList("getReadBook", readList);
	}
	
	// 평점높은 작품 6개 책정보(Detail DTO)리스트를 반환한다.
	@Override
	public List getRatingBook() throws Exception{
		return mybatis.selectList("getRatingBook");
	}
		
	// 최근에 댓글이 달린 작품리스트(index_book)를 반환한다.
	@Override
	public List getCommentList() throws Exception{
		List bookList = mybatis.selectList("getCommentList");
		List commentList = new ArrayList();
		int index = 0;
		for(Object c : bookList) {
			commentList.add((int) c);
			if(index > 3) {break;}
			index++;
		}
			
		return commentList;
	}
}
