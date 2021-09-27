package main;

import java.util.List;

import member.MemberReadDTO;

public interface MainDAOInter 
{
	// main�������� main �����̵忡 �ش��ϴ� å����Ʈ�� ��ȸ�Ѵ�. �������� 5��ǰ�� �����Ͽ� å����(DetailDTO)�� �����´�. 
	public List mainBook() throws Exception;
	
	// �˻���� �� ��ȸ
	public int searchCount(SearchDTO dto) throws Exception;
	
	// �˻���� ����Ʈ ��ȸ
	public List searchList(SearchDTO dto) throws Exception;
	
	// ȸ����ȣ�� �������¸� �޾Ƽ� �������¸���Ʈ(index_book)�� ��ȯ�Ѵ�.
	public List getReadList(MemberReadDTO read) throws Exception;
		
	// å��ȣ����Ʈ�� �޾Ƽ� å����(Detail DTO)����Ʈ�� ��ȯ�Ѵ�.
	public List getReadBook(List readList) throws Exception;
	
	// �������� ��ǰ 6�� å����(Detail DTO)����Ʈ�� ��ȯ�Ѵ�.
	public List getRatingBook() throws Exception;
		
	// �ֱٿ� ����� �޸� ��ǰ����Ʈ(index_book)�� ��ȯ�Ѵ�.
	public List getCommentList() throws Exception;
	
}
