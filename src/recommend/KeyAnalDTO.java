package recommend;

/*
 * KeyAnalDTO Ŭ���� : Keyword_anala Table�� �Ӽ����� ���� �� ȣ��
 * index_book 		: å��ȣ
 * index_keyword 	: Ű�����ȣ
 * weight			: ����ġ  
 */

public class KeyAnalDTO {
	int index_book;
	int index_keyword;
	double weight;
	
	// get() : ���� ȣ�� , set() : ���� ����
	public void setIndex_book(int index_book) {	this.index_book = index_book;	}
	public void setIndex_keyword(int index_keyword) {	this.index_keyword = index_keyword;	}
	public void setWeight(double weight) {	this.weight = weight;	}
	
	public int getIndex_book() {	return index_book;	}
	public int getIndex_keyword() {	return index_keyword;	}
	public double getWeight() {	return weight;	}
}
