package detail;

/*
 * detailDTO Ŭ���� : detail Table�� �Ӽ����� ���� �� ȣ��
	index_book : å��ȣ
	title : å����
	writer : �۰�
	publisher : ���ǻ�
	times : ���緮
	comple : �ϰῩ��
	genre : �帣
	rating_revel : ����
	age_grade : ���
	img : �̹��� ����
	link_naver : ���̹��ø��� �̹��� ��ũ
	link_kakao : īī�������� �̹��� ��ũ
	plot : �ٰŸ�
	rating_num : ���� �ο���
	review_num : ��� �ο���
	read_want : �а�ʹ�
	read_ing : �д����̴�
	read_end : ���о���
	read_not : ���ɾ���
	book : ���ົ ����
	start_row : ������ǰ(genreBean���� ���)
	end_row : ��������ǰ(genreBean���� ���)
	rank : ����(�������º� ���� ��Ÿ�� �� ��� - ������)
	gender : ����(����(1), ����(2) => ���� �α⵵�� ���� ��Ÿ�� �� ���(������))
	total : ��ü ��ũ Ŭ�� ��(���̹�+īī��) => �α⵵�� ���� ��Ÿ�� �� ���

*/

public class DetailDTO 
{
	   private int index_book;
	   private String title;
	   private String writer;
	   private String publisher;
	   private int times;
	   private int comple;
	   private int genre;
	   private float rating_revel;
	   private int age_grade;
	   private String image;
	   private String link_naver;
	   private String link_kakao;
	   private String plot;
	   private int rating_num;
	   private int review_num;
	   private int read_want;
	   private int read_ing;
	   private int read_end;
	   private int read_not;
	   private int book;
	   private int startRow;
	   private int endRow;
	   private int rank;
	   private int gender;
	   private int total;
	   
	   // get() : ���� ȣ�� , set() : ���� ����
	   public int getIndex_book()
	   {   return index_book;	}
	   public void setIndex_book(int index_book) 
	   {   this.index_book = index_book;	}
	   
	   public String getTitle() 
	   {   return title;	}
	   public void setTitle(String title) 
	   {   this.title = title;	}
	   
	   public String getWriter() 
	   {   return writer;	}
	   public void setWriter(String writer) 
	   {   this.writer = writer;	}
	   
	   public String getPublisher() 
	   {   return publisher;	}
	   public void setPublisher(String publisher) 
	   {   this.publisher = publisher;	}
	   
	   public int getTimes() 
	   {   return times;	}
	   public void setTimes(int times)
	   {   this.times = times;	}
	   
	   public int getComple() 
	   {   return comple;	}
	   public void setComple(int comple) 
	   {   this.comple = comple;	}
	   
	   public int getGenre()
	   {   return genre;	}
	   public void setGenre(int genre)
	   {   this.genre = genre;	}
	   
	   public float getRating_revel() 
	   {   return rating_revel;	}
	   public void setRating_revel(float rating_revel) 
	   {   this.rating_revel = rating_revel;	}
	   
	   public int getAge_grade()
	   {   return age_grade;	}
	   public void setAge_grade(int age_grade) 
	   {   this.age_grade = age_grade;	}
	   
	   public String getImage()
	   {   return image;	}
	   public void setImage(String image) 
	   {   this.image = image;	}
	   
	   public String getLink_naver() 
	   {   return link_naver;	}
	   public void setLink_naver(String link_naver)
	   {   this.link_naver = link_naver;	}
	   
	   public String getLink_kakao() 
	   {   return link_kakao;	}
	   public void setLink_kakao(String link_kakao)
	   {	this.link_kakao = link_kakao;	}
	   
	   public String getPlot() 
	   {   return plot;	}
	   public void setPlot(String plot)
	   {   this.plot = plot;	}
	   
	   public int getRating_num() 
	   {   return rating_num;	}
	   public void setRating_num(int rating_num) 
	   {   this.rating_num = rating_num;	}
	   
	   public int getReview_num() 
	   {   return review_num;	}
	   public void setReview_num(int review_num) 
	   {   this.review_num = review_num;	}
	   
	   public int getRead_want() 
	   {   return read_want;	}
	   public void setRead_want(int read_want) 
	   {   this.read_want = read_want;	}
	   
	   public int getRead_ing() 
	   {   return read_ing;	}
	   public void setRead_ing(int read_ing) 
	   {   this.read_ing = read_ing;	}
	   
	   public int getRead_end() 
	   {   return read_end;	}
	   public void setRead_end(int read_end)
	   {	this.read_end = read_end;	}
	   
	   public int getRead_not() 
	   {   return read_not;	}
	   public void setRead_not(int read_not) 
	   {   this.read_not = read_not;	}
	   
	   public int getBook()
	   {   return book;	}
	   public void setBook(int book) 
	   {   this.book = book;	}
	   
	   public int getStartRow()
	   {   return startRow;	}
	   public void setStartRow(int startRow) 
	   {   this.startRow = startRow;	}
	   
	   public int getEndRow()
	   {   return endRow;	}
	   public void setEndRow(int endRow) 
	   {   this.endRow = endRow;	}
	   
	   public int getRank()
	   {	return rank;	}
	   public void setRank(int rank)
	   {	this.rank = rank;	}
	   
	   public int getGender() 
	   {	return gender;	}
	   public void setGender(int gender)
	   {	this.gender = gender;	}
	   
	   public int getTotal() 
	   {	return total;	}
	   public void setTotal(int total) 
	   {	this.total = total;	}
	   
	   
}
