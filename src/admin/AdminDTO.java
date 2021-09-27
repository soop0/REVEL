package admin;

/*
 * AdminDTO : ������ ���� ��� DTO
 * genre : �帣 ��ȣ
 * rating : ����
 * ageStart : ���ɽ���
 * ageEnd : ���ɳ�
 * naver : ���̹� ��ũ ��
 * kakao : īī�� ��ũ ��
 * romance : �θǽ� �� ���� ��ǰ ��
 * rofan : �θǽ���Ÿ�� �� ���� ��ǰ ��
 * fantasy : ��Ÿ�� �� ���� ��ǰ ��
 * mofan : ������Ÿ�� �� ���� ��ǰ ��
 * heroism : ���� �� ���� ��ǰ ��
 * age10 : 10�� ����
 * age20 : 20�� ����
 * age30 : 30�� ����
 * age40 : 40�� ����
 * age50 : 50�� �̻� ����
 * 
 */
public class AdminDTO 
{
	private int genre;
	private int rating;
	private int ageStart;
	private int ageEnd;
	private int naver;
	private int kakao;
	private int[] romance = new int[5];
	private int[] rofan = new int[5];
	private int[] fantasy = new int[5];
	private int[] mofan = new int[5];
	private int[] heroism = new int[5];
	private int[] age10 = new int[5];
	private int[] age20 = new int[5];
	private int[] age30 = new int[5];
	private int[] age40 = new int[5];
	private int[] age50 = new int[5];
	
	public int getGenre() 
	{	return genre;	}
	public void setGenre(int genre) 
	{	this.genre = genre;	}
	
	public int getRating() 
	{	return rating;	}
	public void setRating(int rating) 
	{	this.rating = rating;	}
	
	public int getAgeStart() 
	{	return ageStart;	}
	public void setAgeStart(int ageStart) 
	{	this.ageStart = ageStart;	}
	
	public int getAgeEnd() 
	{	return ageEnd;	}
	public void setAgeEnd(int ageEnd) 
	{	this.ageEnd = ageEnd;	}
	
	public int getNaver() 
	{	return naver;	}
	public void setNaver(int naver) 
	{	this.naver = naver;	}
	
	public int getKakao() 
	{	return kakao;	}
	public void setKakao(int kakao) 
	{	this.kakao = kakao;	}
	
	public int[] getRomance()
	{	return romance;	}
	public void setRomance(int[] romance)
	{	this.romance = romance;	}
	
	public int[] getRofan()
	{	return rofan;	}
	public void setRofan(int[] rofan)
	{	this.rofan = rofan;	}
	
	public int[] getFantasy() 
	{	return fantasy;	}
	public void setFantasy(int[] fantasy) 
	{	this.fantasy = fantasy;	}
	
	public int[] getMofan() 
	{	return mofan;	}
	public void setMofan(int[] mofan) 
	{	this.mofan = mofan;	}
	
	public int[] getHeroism()
	{	return heroism;	}
	public void setHeroism(int[] heroism)
	{	this.heroism = heroism;	}
	
	public int[] getAge10() 
	{	return age10;	}
	public void setAge10(int[] age10)
	{	this.age10 = age10;	}
	
	public int[] getAge20() 
	{	return age20;	}
	public void setAge20(int[] age20) 
	{	this.age20 = age20;	}
	
	public int[] getAge30() 
	{	return age30;	}
	public void setAge30(int[] age30) 
	{	this.age30 = age30;	}
	
	public int[] getAge40() 
	{	return age40;	}
	public void setAge40(int[] age40)
	{	this.age40 = age40;	}
	
	public int[] getAge50() 
	{	return age50;	}
	public void setAge50(int[] age50) 
	{	this.age50 = age50;	}

}
