package member;

/*
 * SurveyDTO Ŭ����	: Survey Table�� �Ӽ����� ���� �� ȣ��\
 * index_member		: ȸ����ȣ
 * volume			: ��ȣ�з� (1 - ���� / 2 - ���� / 3 - ���� / 4 - �ƹ��ų�)
 * romance			: ��ȣ�帣 �θǽ� ���ý� 1, �̼��ý� 0
 * rofan			: ��ȣ�帣 �θǽ���Ÿ�� ���ý� 1, �̼��ý� 0
 * fantagy			: ��ȣ�帣 ��Ÿ�� ���ý� 1, �̼��ý� 0
 * mofan			: ��ȣ�帣 ������Ÿ�� ���ý� 1, �̼��ý� 0
 * heroism			: ��ȣ�帣 ���� ���ý� 1, �̼��ý� 0
 */

public class SurveyDTO {
	private int index_member;
	private int volume;
	private int romance;
	private int rofan;
	private int fantagy;
	private int mofan;
	private int heroism;
	
	
	public int getIndex_member() {
		return index_member;
	}
	public void setIndex_member(int index_member) {
		this.index_member    = index_member;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getRomance() {
		return romance;
	}
	public void setRomance(int romance) {
		this.romance = romance;
	}
	public int getRofan() {
		return rofan;
	}
	public void setRofan(int rofan) {
		this.rofan = rofan;
	}
	public int getFantagy() {
		return fantagy;
	}
	public void setFantagy(int fantagy) {
		this.fantagy = fantagy;
	}
	public int getMofan() {
		return mofan;
	}
	public void setMofan(int mofan) {
		this.mofan = mofan;
	}
	public int getHeroism() {
		return heroism;
	}
	public void setHeroism(int heroism) {
		this.heroism = heroism;
	}
	
	
}
