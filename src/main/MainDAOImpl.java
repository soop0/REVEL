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
	
	// Controller���� ��ĵ�� Ŭ���������� Autowired�� ����� �� �ִ�.
	@Autowired
	private SqlSessionTemplate mybatis = null;
	
	// AnalysisDAOInter Ŭ����
	@Autowired
	private AnalysisDAOInter analImpl = null;
	
	// main�������� main �����̵忡 �ش��ϴ� å����Ʈ�� ��ȸ�Ѵ�. �������� 5��ǰ�� �����Ͽ� å����(DetailDTO)�� �����´�. 
	@Override
	public List mainBook() throws Exception		
	{	return mybatis.selectList("main.mainBook"); 	}

	// �˻���� �� ��ȸ
	@Override
	public int searchCount(SearchDTO dto) throws Exception
	{	return mybatis.selectOne("main.searchCount", dto);	}

	// �˻���� ����Ʈ ��ȸ
	@Override
	public List searchList(SearchDTO dto) throws Exception 
	{	return mybatis.selectList("main.searchList", dto);	}
	
	// ȸ����ȣ�� �������¸� �޾Ƽ� �������¸���Ʈ(index_book)�� ��ȯ�Ѵ�.
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
		
	// å��ȣ����Ʈ�� �޾Ƽ� å����(Detail DTO)����Ʈ�� ��ȯ�Ѵ�.
	@Override
	public List getReadBook(List readList) throws Exception{
		return mybatis.selectList("getReadBook", readList);
	}
	
	// �������� ��ǰ 6�� å����(Detail DTO)����Ʈ�� ��ȯ�Ѵ�.
	@Override
	public List getRatingBook() throws Exception{
		return mybatis.selectList("getRatingBook");
	}
		
	// �ֱٿ� ����� �޸� ��ǰ����Ʈ(index_book)�� ��ȯ�Ѵ�.
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
