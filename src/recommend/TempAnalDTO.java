package recommend;
/*
 * TempAnalDTO Ŭ���� : temp_anala_index_member Table�� �Ӽ����� ���� �� ȣ��
 * index_member 	: Table �̸��� ���� ȸ����ȣ
 * index_book 		: å��ȣ
 * category 		: Ű���� ī�װ�, 100: ĳ����, 200: ������, 300: ����, 400: ���ڹ���
 * value 			: ī�װ��� �ڻ��ο����� �м� �� 
 */
public class TempAnalDTO {
	private int index_member;
	private int index_book;
	private int category;
	private double value;
	
	public int getIndex_member() {
		return index_member;
	}
	public void setIndex_member(int index_member) {
		this.index_member = index_member;
	}
	public int getIndex_book() {
		return index_book;
	}
	public void setIndex_book(int index_book) {
		this.index_book = index_book;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
}
