package recommend;

/*
 * MemKeyAnalDTO Ŭ���� : member_keyword_anal_member_index ���̺� (ex>member_keyword_anal_1) ���� �� ȣ��
 * 						ȸ���� Ű���� ������ ���� ������ �����ϰ� ȣ���Ѵ�.
 * index_keyword : Ű���� ��ȣ
 * cumul_score : Ű������ ��������
 * */
public class MemKeyAnalDTO {
	private int index_keyword;
	private double cumul_score;
	
	public void setIndex_keyword(int index_keyword) {	this.index_keyword = index_keyword;	}
	public void setCumul_score(double cumul_score) {	this.cumul_score = cumul_score;	}
	
	public int getIndex_keyword() {	return index_keyword;	}
	public double getCumul_score() {	return cumul_score;	}
}
