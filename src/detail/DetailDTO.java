package detail;

/*
 * detailDTO 클래스 : detail Table의 속성들을 저장 및 호출
	index_book : 책번호
	title : 책제목
	writer : 작가
	publisher : 출판사
	times : 연재량
	comple : 완결여부
	genre : 장르
	rating_revel : 평점
	age_grade : 등급
	img : 이미지 파일
	link_naver : 네이버시리즈 이미지 링크
	link_kakao : 카카오페이지 이미지 링크
	plot : 줄거리
	rating_num : 평점 인원수
	review_num : 댓글 인원수
	read_want : 읽고싶다
	read_ing : 읽는중이다
	read_end : 다읽었다
	read_not : 관심없다
	book : 단행본 여부
	start_row : 시작작품(genreBean에서 사용)
	end_row : 마지막작품(genreBean에서 사용)
	rank : 순위(독서상태별 순위 나타낼 때 사용 - 관리자)
	gender : 성별(남자(1), 여자(2) => 성별 인기도서 순위 나타낼 때 사용(관리자))
	total : 전체 링크 클릭 수(네이버+카카오) => 인기도서 순위 나타낼 때 사용

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
	   
	   // get() : 변수 호출 , set() : 변수 저장
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
