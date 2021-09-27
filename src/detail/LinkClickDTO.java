package detail;

/* LinkClickDTO 클래스 : LinkClick Table의 속성들을 저장 및 호출
   index_book : 책번호
   index_member : 회원번호
   reg_time : 클릭 날짜
   naver : 네이버시리즈 링크 클릭
   kakao : 카카오페이지 링크 클릭
   platform : 사용자가 누른 링크 플랫폼
   platformCount : 사용자가 누른 링크 플랫폼 정보 확인
 * */
import java.sql.Timestamp;

public class LinkClickDTO {
	private int index_book;
	private int index_member;
	private Timestamp reg_time;
	private int naver;
	private int kakao;
	private String platform;
	private int platformCount;
	
	public int getIndex_book()
	{	return index_book;	}
	public void setIndex_book(int index_book)
	{	this.index_book = index_book;	}
	
	public int getIndex_member()
	{	return index_member;	}
	public void setIndex_member(int index_member)
	{	this.index_member = index_member;	}
	
	public Timestamp getReg_time()
	{	return reg_time;	}
	public void setReg_time(Timestamp reg_time)
	{	this.reg_time = reg_time;	}
	
	public int getNaver()
	{	return naver;	}
	public void setNaver(int naver)
	{	this.naver = naver;	}
	
	public int getKakao()
	{	return kakao;	}
	public void setKakao(int kakao)
	{	this.kakao = kakao;	}
	
	public String getPlatform()
	{	return platform;	}
	public void setPlatform(String platform)
	{	this.platform = platform;	}
	
	public int getPlatformCount()
	{	return platformCount;	}
	public void setPlatformCount(int platformCount)
	{	this.platformCount = platformCount;	}
	
}
