package main;

import java.util.List;

import member.MemberReadDTO;

public interface MainDAOInter 
{
	// main페이지의 main 슬라이드에 해당하는 책리스트를 조회한다. 랜덤으로 5작품을 선택하여 책정보(DetailDTO)를 가져온다. 
	public List mainBook() throws Exception;
	
	// 검색결과 수 조회
	public int searchCount(SearchDTO dto) throws Exception;
	
	// 검색결과 리스트 조회
	public List searchList(SearchDTO dto) throws Exception;
	
	// 회원번호와 독서상태를 받아서 독서상태리스트(index_book)를 반환한다.
	public List getReadList(MemberReadDTO read) throws Exception;
		
	// 책번호리스트를 받아서 책정보(Detail DTO)리스트를 번환한다.
	public List getReadBook(List readList) throws Exception;
	
	// 평점높은 작품 6개 책정보(Detail DTO)리스트를 반환한다.
	public List getRatingBook() throws Exception;
		
	// 최근에 댓글이 달린 작품리스트(index_book)를 반환한다.
	public List getCommentList() throws Exception;
	
}
