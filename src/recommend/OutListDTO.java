package recommend;

import java.util.List;

/*
 * OutListDTO Ŭ����	: ���θ�����õ�� ������ å��ȣ(index_book)����� ȣ���ϴ� Ŭ����
 * index_member		: ȸ����ȣ
 * outVolume		: ��ȣ���� �ʴ� �з� ���(����-1, ����-2, ����-3, �ƹ��ų�-4)
 * outGenre			: ��ȣ���� �ʴ� �帣 ���(�θǽ�-1, ����-2, ��Ÿ��-3, ����-4, ����-5)
 * index_book		: å��ȣ
 */
public class OutListDTO {
	private int index_member;
	private List outVolume;
	private List outGenre;
	private int index_book;
	
	
	public int getIndex_member() {
		return index_member;
	}
	public void setIndex_member(int index_member) {
		this.index_member = index_member;
	}
	public List getOutVolume() {
		return outVolume;
	}
	public void setOutVolume(List outVolume) {
		this.outVolume = outVolume;
	}
	public List getOutGenre() {
		return outGenre;
	}
	public void setOutGenre(List outGenre) {
		this.outGenre = outGenre;
	}
	public int getIndex_book() {
		return index_book;
	}
	public void setIndex_book(int index_book) {
		this.index_book = index_book;
	}
	
	
	
}
