package detail;

/* LinkClickDTO Ŭ���� : LinkClick Table�� �Ӽ����� ���� �� ȣ��
   index_book : å��ȣ
   index_member : ȸ����ȣ
   reg_time : Ŭ�� ��¥
   naver : ���̹��ø��� ��ũ Ŭ��
   kakao : īī�������� ��ũ Ŭ��
   platform : ����ڰ� ���� ��ũ �÷���
   platformCount : ����ڰ� ���� ��ũ �÷��� ���� Ȯ��
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
