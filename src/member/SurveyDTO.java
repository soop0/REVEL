package member;

/*
 * SurveyDTO 클래스	: Survey Table의 속성들을 저장 및 호출\
 * index_member		: 회원번호
 * volume			: 선호분량 (1 - 장편 / 2 - 중편 / 3 - 단편 / 4 - 아무거나)
 * romance			: 선호장르 로맨스 선택시 1, 미선택시 0
 * rofan			: 선호장르 로맨스판타지 선택시 1, 미선택시 0
 * fantagy			: 선호장르 판타지 선택시 1, 미선택시 0
 * mofan			: 선호장르 현대판타지 선택시 1, 미선택시 0
 * heroism			: 선호장르 무협 선택시 1, 미선택시 0
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
