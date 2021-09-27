<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--
	revel 소설 세부 페이지
	http://localhost:8080/revel/detail.rv
--%>


<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Revel | 세부페이지</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/revel/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/plyr.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/style.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/fontello.css" type="text/css">
    <link rel="stylesheet" href="/revel/css/animation.css" type="text/css">
   
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"></script>

</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <!-- <a href="/revel/main.rv"> : revel main 주소  -->
    <!-- <img src="/revel/img/logo.png" alt=""> : revel 로고 파일 경로 -->
    <!-- <nav class="header__menu mobile-menu"> : <li> 태그에 헤더 서비스 작성  -->
    <!-- <ul class="dropdown"> : <li> 태그에 헤더의 세부 서비스/주소 작성 -->
    <!-- <div class="header__right"> : 헤더 오른쪽 검색/마이페이지 버튼 -->
    <header class="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="header__logo">
                        <a href="/revel/main.rv">
                            <img src="/revel/img/revel2.png" alt="">
                        </a>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="header__nav">
                        <nav class="header__menu mobile-menu">
                            <ul>
                                <li><a href="/revel/ranking/daily.rv">랭킹<span class="arrow_carrot-down"></span></a>
                                    <ul class="dropdown">
                                        <li><a href="/revel/ranking/daily.rv">일간</a></li>
                                        <li><a href="/revel/ranking/weekly.rv">주간</a></li>
                                        <li><a href="/revel/ranking/monthly.rv">월간</a></li>
                                        <li><a href="/revel/ranking/total.rv">전체</a></li>
                                    </ul>
                                </li>
                                <c:if test="${sessionScope.status == 11 || sessionScope.status == 12 || sessionScope.status == 100}">
                                	<li><a href="/revel/recommend/alert.rv">추천 <span class="arrow_carrot-down"></span></a>
                                    	<ul class="dropdown">
                                        	<li><a href="/revel/recommend/alert.rv">개인맞춤 추천</a></li>
                                    	</ul>
                                	</li>
                                </c:if>
                                <li><a href="/revel/platform/naverSeries.rv">플랫폼 <span class="arrow_carrot-down"></span></a>
                                	<ul class="dropdown">
                                       	<li><a href="/revel/platform/naverSeries.rv">네이버 시리즈</a></li>
                                       	<li><a href="/revel/platform/kakaoPage.rv">카카오페이지</a></li>
                                   	</ul>
                                </li>
                                <li><a href="/revel/genre.rv">장르 <span class="arrow_carrot-down"></span></a>
                                    <ul class="dropdown">
                                        <li><a href="/revel/genre/total.rv">전체</a></li>
                                        <li><a href="/revel/genre/romance.rv">로맨스</a></li>
                                        <li><a href="/revel/genre/rofan.rv">로맨스 판타지</a></li>
                                        <li><a href="/revel/genre/fantasy.rv">판타지</a></li>
                                        <li><a href="/revel/genre/mofan.rv">현대 판타지</a></li>
                                        <li><a href="/revel/genre/heroism.rv">무협</a></li>
                                    </ul>
                                </li>
                                   <li><a href="/revel/help/notice.rv">고객센터 <span class="arrow_carrot-down"></span></a>
                                    <ul class="dropdown">
                                        <li><a href="/revel/help/notice.rv">공지사항</a></li>
                                        <li><a href="/revel/help/qna.rv">자주하는 질문</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-lg-2">
                    <div class="header__right">
                        <a href="#" class="search-switch"><span class="icon_search"></span></a>
                        	<c:if test="${empty(sessionScope.index_member) || sessionScope.status == 50}">
                        		<a href="/revel/login.rv"><i class="icon_lock"></i></a>
                        	</c:if>
                        	<c:if test="${sessionScope.index_member > 0 && sessionScope.status != 50}">
                        		<c:if test="${sessionScope.status != 100}">
                        			<a href="/revel/mypage.rv"><span class="icon_profile"></span></a>
                        		</c:if>
                        		<c:if test="${sessionScope.status == 100}">
                        			<a href="/revel/manage.rv"><span class="icon_profile"></span></a>
                        		</c:if>
                        		<a href="/revel/logout.rv"><i class="icon_lock-open"></i></a>
                        	</c:if>
                    </div>
                </div>
            </div>
            <div id="mobile-menu-wrap"></div>
        </div>
    </header>
    <!-- Header End -->

	<!-- 카테고리 구성 항목 -->
    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/revel/main.rv"><i class="fa fa-home"></i> Home</a>
                        <a href="/revel/genre/total.rv">Categories</a>
                        <span>
                        	<c:if test="${allDetailBook.genre == '1'}">
                        		Romance
                        	</c:if>
                        	<c:if test="${allDetailBook.genre == '2'}">
                        		Romance Fantasy
                        	</c:if>
                        	<c:if test="${allDetailBook.genre == '3'}">
                        		Fantasy
                        	</c:if>
                        	<c:if test="${allDetailBook.genre == '4'}">
                        		Modern Fantasy
                        	</c:if>
                        	<c:if test="${allDetailBook.genre == '5'}">
                        		Heroism
                        	</c:if>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    
	<!-- 해당 소설 세부내용 -->
    <!-- Anime Section Begin -->
    <section class="anime-details spad">
        <div class="container">
            <div class="anime__details__content">
                <div class="row">
                    <div class="col-lg-3">
                    	<!-- 해당 소설 이미지 -->
                        <div class="anime__details__pic set-bg" data-setbg="${allDetailBook.image}"></div>
                        <!-- 키워드 -->
	                    <div class="product__item__text">
	                    	<ul>
			                    <c:forEach var="keywords" items="${keywords}">
			                    	<li>${keywords.keywords}</li> 
			                    </c:forEach>
		                	</ul>     
		                 </div>    
                    </div>
                    <div class="col-lg-9">
                        <div class="anime__details__text">
                        	<!-- 해당 소설 제목, 작가, 출판사 -->
                            <div class="anime__details__title" data-rate="${allDetailBook.title}">
                                <h3>${allDetailBook.title}</h3>
                                <span>${allDetailBook.writer}, ${allDetailBook.publisher}</span>
                            </div>
                            
                            <!-- 해당 소설 평점 시스템(회원만 서비스 이용 가능) -->
                            <div class="anime__details__rating" data-rate="${allDetailBook.index_book}">						
								 	<div class="rating" data-rate="${rating}">
                                    	<i class="fa fa-star fa-2x" style="color:#fff;cursor:pointer;"></i></a>
                                    	<i class="fa fa-star fa-2x" style="color:#fff;cursor:pointer;"></i></a>
                                    	<i class="fa fa-star fa-2x" style="color:#fff;cursor:pointer;"></i></a>
                                    	<i class="fa fa-star fa-2x" style="color:#fff;cursor:pointer;"></i></a>
                                    	<i class="fa fa-star fa-2x" style="color:#fff;cursor:pointer;"></i></a>	
                					</div>
                            	<span>평점 | <fmt:formatNumber value="${allDetailBook.rating_revel}" type="number" pattern="0.0"/> (총 ${allDetailBook.rating_num}명)</span>
                         	</div>
                            
                            <!-- 해당 소설 줄거리, 작가, 회차, 연령등급, 장르, 완결여부 -->
                            <p>${allDetailBook.plot}</p>
                            <div class="anime__details__widget">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6">
                                        <ul>
                                            <li><span>글/그림 | </span> ${allDetailBook.writer}</li>
                                            <li><span>출판사 | </span> ${allDetailBook.publisher}</li>
                                            <li><span>회차 | </span> 총 ${allDetailBook.times} 화</li>     
                                        </ul>
                                    </div>
                                    <div class="col-lg-6 col-md-6">
                                        <ul>
                                          	<li><span>연령등급 | </span> <c:if test="${allDetailBook.age_grade == '0'}">전체 이용가</c:if>
                                          							  <c:if test="${allDetailBook.age_grade == '12'}">12세 이용가</c:if>
                                          							  <c:if test="${allDetailBook.age_grade == '15'}">15세 이용가</c:if>
                                          							  <c:if test="${allDetailBook.age_grade == '19'}">청소년 관람불가</c:if>
                                          	
                                          	</li>  
                                          	<li><span>장르 | </span> <c:if test="${allDetailBook.genre == '1'}">로맨스</c:if>
                                          							<c:if test="${allDetailBook.genre == '2'}">로맨스 판타지</c:if>
                                          							<c:if test="${allDetailBook.genre == '3'}">판타지</c:if>
                                          							<c:if test="${allDetailBook.genre == '4'}">현대 판타지</c:if>
                                          							<c:if test="${allDetailBook.genre == '5'}">무협</c:if> 
                                          	</li>  
                                          	<li><span>완결여부 | </span> <c:if test="${allDetailBook.comple == 1}">완결</c:if>
                                          							  <c:if test="${allDetailBook.comple == 0}">연재중</c:if> 
                                          	</li>  
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <!-- 
                            	해당 소설 독서상태 시스템(회원만 서비스 이용 가능) 
                            	링크 클릭 기능
                            -->
                            <div class="anime__details__btn">
                          		
                            	
                                <a class="follow-btn" style="cursor:pointer;" id="1" data-rate><i class="icon_cart_alt"></i> 읽을거다</a>
                                <a class="follow-btn" style="cursor:pointer;" id="2"><i class="icon_book_alt"></i> 읽고있다</a>
                                <a class="follow-btn" style="cursor:pointer;" id="3"><i class="icon_like"></i> 다읽었다</a>
                                <a class="follow-btn" style="cursor:pointer;" id="0"><i class="icon_blocked"></i> 관심없다</a> <br /><br /><br />
                                
                                <!-- 링크 클릭 수 증가 기능 추가 필요 -->
                                <c:if test="${!empty(allDetailBook.link_naver)}">
                                	<a href="#" class="watch-btn" id="naver" data-rate="${allDetailBook.link_naver}"><span>네이버시리즈⏵</span></a>&nbsp; 
                                </c:if>
                                <c:if test="${!empty(allDetailBook.link_kakao)}" >
                                	<a href="#" class="watch-btn" id="kakao" data-rate="${allDetailBook.link_kakao}"><span>카카오페이지⏵</span></a>
                                </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- 댓글 리스트 -->
               <div class="row">
                    <div class="col-lg-8 col-md-8">
                        <div class="anime__details__review">
                            <div class="section-title">
                                <h5>Reviews</h5>                    
                            </div>
                            <c:if test="${count == 0}">
                            	<div class="col-lg-8">
                    				<div class="blog__details__content">
                            			<div class="blog__details__item__text">
                        					<p align="center">작성된 댓글이 존재하지 않습니다.</p>
                        				</div>
                        			</div>
                        		</div>			
                            </c:if> 
                            
                            <c:if test="${count > 0}">
                            	<c:forEach var="rvList" items="${rvList}">
                            		<div class="anime__review__item">
                                		<div class="anime__review__item__text">
                                    		<h6>${rvList.nick} - <span><fmt:formatDate value="${rvList.reg_time}" pattern="yyyy-MM-dd HH:mm" /></span>
                                    			<!-- 삭제 버튼 - 관리자, 작성자 본인 시에만 활성화  -->
                                   				<c:if test="${sessionScope.nick eq rvList.nick or sessionScope.status == 100}">	
                                    				<a href="/revel/detail/reviewDelete.rv?reviewNum=${rvList.index_review}&bookNum=${allDetailBook.index_book}" 
                                    			   	   onclick="return reviewDeleteOpen();">
                                    				<i class="icon_trash_alt" style="color:white;float:right"></i></a>
												</c:if> 
                                    		</h6>
                                    		<c:if test="${rvList.cleanbot == 0}">
                                    			<p>${rvList.content}</p>
                                    		</c:if>
                                    		<c:if test="${rvList.cleanbot > 0}">
                                    			<p>클린봇이 부적절한 표현을 감지한 댓글입니다.</p>
                                    		</c:if>
                                    		<input type="hidden" name="index_review" value="${rvList.index_review}">
																			
   											
                                		</div>
                            		</div>
                            	</c:forEach>
                            </c:if>            
                        </div>
                        
                        <!-- 댓글 페이징 -->
                        <c:if test="${count > 0}">
         					<fmt:parseNumber var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}" integerOnly="true"/>
         					<c:set var="pageBlock" value="${5}" />
            				<fmt:parseNumber var="result" value="${currentPage/5}" integerOnly="true" />
           					<c:set var="startPage" value="${result*5+1}" />
            				<c:set var="endPage" value="${startPage + pageBlock -1}" />
            				<c:if test="${endPage > pageCount}">
            					<c:set var="endPage" value="${pageCount}" />
            				</c:if>
                    	
            				<div class="product__pagination" align="center">
            					<c:if test="${startPage > 5}">
                					<a href="/revel/detail.rv?bookNum=${allDetailBook.index_book}&pageNum=${startPage-5}"><i class="fa fa-angle-double-left"></i></a>
                				</c:if>
                        	
                				<c:forEach var="i" begin="${startPage}" end="${endPage}">
                					<a href="/revel/detail.rv?bookNum=${allDetailBook.index_book}&pageNum=${i}">${i}</a>
                				</c:forEach>
                        	
                				<c:if test="${endPage < pageCount}" >
                    				<a href="/revel/detail.rv?bookNum=${allDetailBook.index_book}&pageNum=${startPage+5}"><i class="fa fa-angle-double-right"></i></a>
                				</c:if>
             				</div>
          				</c:if>
                        
                        <!-- 댓글 작성 부분 -->
                        <div class="anime__details__form">
                            <div class="section-title">
                                <h5>Your Comment</h5>
                            </div>
                            <c:if test="${sessionScope.status==11 || sessionScope.status==12 || sessionScope.status==100}">
                            	<form action="/revel/detail/reviewWrite.rv" method="post" name="review">
                                	<input type="hidden" name="index_member" value="${sessionScope.index_member}" />
                                	<input type="hidden" name="index_book" value="${allDetailBook.index_book}" />
                                	<textarea placeholder="댓글을 입력해주세요." name="content" style="color:black"></textarea>
                                	<button type="submit" onclick="return reviewCheck();"><i class="fa fa-location-arrow"></i> 댓글 등록</button>
                            	</form>
                            </c:if>
                            <c:if test="${empty(sessionScope.status) || sessionScope.status==50}">
                            	<form action="/revel/login.rv" method="post" name="review">
                                	<input type="hidden" name="index_member" value="${sessionScope.index_member}" />
                                	<input type="hidden" name="index_book" value="${allDetailBook.index_book}" />
                                	<textarea placeholder="댓글을 입력해주세요." name="content"></textarea>
                                	<button type="submit" onclick="return reviewLogon();"><i class="fa fa-location-arrow"></i> 댓글 등록</button>
                            	</form>
                            </c:if>
                        </div>
                    </div>
                    <!-- 댓글 끝 -->
                    
                    <!-- 같은 작가 작품 추천 -->        
                    <div class="col-lg-4 col-md-4">
                        <div class="anime__details__sidebar">
                        	<c:if test="${sameWriterCount > 1 }" >
                            	<div class="section-title">
                                	<h5>"${allDetailBook.writer}"작가의 다른 작품</h5>
                            	</div>
                            	<c:forEach var="sameWriter" items="${sameWriter}">	
                            		<c:if test="${allDetail.title ne sameWriter.title}"> <!-- 해당 줄 수정 필요 -->
                            			<div class="product__sidebar__view__item set-bg" data-setbg="${sameWriter.image}" onclick="location.href='/revel/detail.rv?bookNum=${sameWriter.index_book}';" style="cursor:pointer;">
                                			<div class="ep"><c:if test="${sameWriter.comple == 1}">완결</c:if><c:if test="${sameWriter.comple == 0}">연재중</c:if> / ${sameWriter.times}화</div>
                                			<div class="view"><i class="icon_star"></i> ${sameWriter.rating_revel}</div>
                                			<div class="section-title">
                                				<h5>${sameWriter.title}</h5>
                            				</div>
                                		</div>
                                	</c:if>
       							</c:forEach>
       						</c:if>
                        </div>
                    </div>
                   <!-- 작품추천 끝 -->               
                </div>
            </div>
        </section>
        <!-- Anime Section End -->

 <!-- Footer Section Begin -->
<!-- 메인화면 맨 아래의 헤더부분 -->
<footer class="footer">
    <div class="page-up">
        <a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="footer__logo">
                    <a href="/revel/main.rv"><img src="/revel/img/revel2.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="footer__nav">
                    <ul>
                         <li class="active"><a href="/revel/ranking/daily.rv">랭킹</a></li>
                        <c:if test="${sessionScope.status == 11 || sessionScope.status == 12 || sessionScope.status == 100}">
                        	<li><a href="/revel/recommend/alert.rv">추천</a></li>
                        </c:if>
                        <li><a href="/revel/platform/naverSeries.rv">플랫폼</a>
                        <li><a href="/revel/genre/total.rv">장르</a></li>
                        <li><a href="/revel/help/notice.rv">고객센터</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3">
                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>

              </div>
          </div>
      </div>
  </footer>
  <!-- Footer Section End -->

  <!-- Search model Begin -->
  <!-- 헤더 상단 검색 아이콘 -->
  <div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch"><i class="icon_close"></i></div>
        <form name="search" class="search-model-form" action="/revel/search.rv" method="post" onsubmit="return valueCheck();">
        	<select name="theme">
        		<option value="none" selected="selected">선택</option>
        		<option value="title">제목</option>
        		<option value="writer">작가</option>		
        	</select>
            <input type="text" id="search-input" name="searchWord" placeholder="Search here....." />
        </form>
    </div>
</div>
<!-- Search model end -->

	<!-- 평점 표시하기 및 평가하기  -->
	<script type="text/javascript">
		$(function(){
			$('.rating svg').click(function(){
				var status = '<c:out value="${sessionScope.status}"/>';
				if(status != ""){
					var targetNum = $(this).index() + 1;
					$('.rating svg').css({color:'#fff'});
					$('.rating svg:nth-child(-n+' + targetNum +')').css({color:'#e89f12'});
								 											 			
					var bookNum = $('.anime__details__rating').attr('data-rate');
					var title = $('.anime__details__title').attr('data-rate');
					var result = confirm(title+" 평점 "+targetNum+"점 평가 하시겠습니까?")
									 		
					if(result){				 			
						//yes
						location.replace('/revel/detail/rating.rv?bookNum='+bookNum+'&rating='+targetNum);	
					}
					else{
						// no
						$('.rating svg').css({color:'#fff'});
					}
				}
				else{
					alert('로그인 후 이용가능합니다.');
					location.replace('/revel/login.rv');
				}
			});
								 
			var targetScore = $('.rating').attr('data-rate');
			$('.rating').find('svg:nth-child(-n+'+targetScore+')').css({color:'#e89f12'});			
		});
	</script>	
	
	<!-- 독서상태  -->
	<script type="text/javascript">
    	$(function(){
        	var count1 = 1;		// 읽을거다 선택상태
            var count2 = 1;		// 읽고있다 선택상태
            var count3 = 1;		// 다읽었다 선택상태
            var count0 = 1;		// 관심없다 선택상태
                            			
            $('.anime__details__btn .follow-btn').click(function(){
            	// 회원상태 확인
                var status = '<c:out value="${sessionScope.status}"/>';
                            				
                // 회원일 때
                if(status == 11 || status == 12 || status == 100){
                	var targetNum = $(this).attr('id');									// 클릭한 독서상태
                    var rstatus = $(this).text();										// 선택한 독서상태 이름
                    var title = $('.anime__details__title').attr('data-rate');			// 소설 제목
                    var bookNum = $('.anime__details__rating').attr('data-rate');		// 소설 번호
                    var targetRstatus = "<c:out value='${readStatus.read}'/>";
                    var checkCount = "<c:out value='${readStatus.checkCount}' />";
                            					
                    if(targetRstatus !="" && checkCount != ""){
                    	if(targetRstatus == 1){
                        	count1 = checkCount;
                    	}
                        else if(targetRstatus == 2){
                        	count2 = checkCount;
                        }
                        else if(targetRstatus == 3){
                        	count3 = checkCount;
                        }
                        else{
                        	count0 = checkCount;
                        }
                	}				
                            					
                	// 읽을거다 선택변경
                	if(targetNum == 1){
                		if(count1 == 1){
                    		var result = confirm(title+"을/를"+rstatus+"에 추가하시겠습니까?");
                        	if(result){
                        		// 읽을거다 선택완료(색 변경)
                            	$('.anime__details__btn .follow-btn:eq(0)').css({'background-color':'#e89f12'});
                            	$('.anime__details__btn .follow-btn:eq(1)').css({'background-color':'#e53637'});
                            	$('.anime__details__btn .follow-btn:eq(2)').css({'background-color':'#e53637'});
                            	$('.anime__details__btn .follow-btn:eq(3)').css({'background-color':'#e53637'});
                            	count1 = 0;
                            	location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=1');
                        	}
                    	}
                    	else{
                    		var result = confirm(title+"을/를"+rstatus+"에 취소하시겠습니까?");
                        	if(result){
                        		// 읽을거다 선택 해제
                            	$('.anime__details__btn .follow-btn:eq(0)').css({'background-color':'#e53637'});	
                            	count1 = 1;
                            	count2 = 1;
                            	count3 = 1;
                            	count0 = 1;
                            	location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=0');
                        	}
                    	}
                	}
                	// 읽고있다 선택변경
                	else if(targetNum == 2){
                		if(count2 == 1){
                    		var result = confirm(title+"을/를"+rstatus+"에 추가하시겠습니까?");
                        		if(result){
                            		// 읽고있다 선택완료(색 변경)
                            		$('.anime__details__btn .follow-btn:eq(0)').css({'background-color':'#e53637'});
                            		$('.anime__details__btn .follow-btn:eq(1)').css({'background-color':'#e89f12'});
                            		$('.anime__details__btn .follow-btn:eq(2)').css({'background-color':'#e53637'});
                            		$('.anime__details__btn .follow-btn:eq(3)').css({'background-color':'#e53637'});
                            		count2 = 0;
                            		location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=1');
                            	}
                    	}
                    	else{
                    		var result = confirm(title+"을/를"+rstatus+"에 취소하시겠습니까?");
                        	if(result){
                        		// 읽고있다 선택 해제
                            	$('.anime__details__btn .follow-btn:eq(1)').css({'background-color':'#e53637'});	
                            	count2 = 1;
                            	count1 = 1;
                            	count3 = 1;
                            	count0 = 1;
                            	location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=0');
                        	}
                    	}
                	}
             		// 다읽었다 선택변경
                	else if(targetNum == 3){
                		if(count3 == 1){
                    		var result = confirm(title+"을/를"+rstatus+"에 추가하시겠습니까?");
                        	if(result){
                        		// 다읽었다 선택완료(색 변경)
                            	$('.anime__details__btn .follow-btn:eq(0)').css({'background-color':'#e53637'});
                            	$('.anime__details__btn .follow-btn:eq(1)').css({'background-color':'#e53637'});
                            	$('.anime__details__btn .follow-btn:eq(2)').css({'background-color':'#e89f12'});
                            	$('.anime__details__btn .follow-btn:eq(3)').css({'background-color':'#e53637'});
                            	count3 = 0;
                            	location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=1');
                        	}
                    }
                    	else{
                    		var result = confirm(title+"을/를"+rstatus+"에 취소하시겠습니까?");
                        	if(result){
                        		// 다읽었다 선택 해제
                            	$('.anime__details__btn .follow-btn:eq(2)').css({'background-color':'#e53637'});	
                            	count3 = 1;
                            	count2 = 1;
                            	count1 = 1;
                            	count0 = 1;
                            	location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=0');
                        	}
                    	}
                	}
             		// 관심없다 선택변경
                	else if(targetNum == 0){
                		if(count0 == 1){
                    		var result = confirm(title+"을/를"+rstatus+"에 추가하시겠습니까?");
                        	if(result){
                        		// 관심없다 선택완료(색 변경)
                            	count0 = 0;
                            	$('.anime__details__btn .follow-btn:eq(0)').css({'background-color':'#e53637'});
                            	$('.anime__details__btn .follow-btn:eq(1)').css({'background-color':'#e53637'});
                            	$('.anime__details__btn .follow-btn:eq(2)').css({'background-color':'#e53637'});
                            	$('.anime__details__btn .follow-btn:eq(3)').css({'background-color':'#e89f12'});
                            								
                            	location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=1');
                        	}
                    	}
                    	else{
                    		var result = confirm(title+"을/를"+rstatus+"에 취소하시겠습니까?");
                        	if(result){
                        		// 관심없다 선택 해제
                            	count0 = 1;
                            	count2 = 1;
                            	count1 = 1;
                            	count3 = 1;
                            	$('.anime__details__btn .follow-btn:eq(3)').css({'background-color':'#e53637'});	
                                							
                            	location.replace('/revel/detail/rstatus.rv?bookNum='+bookNum+'&rstatus='+targetNum+'&checkCount=0');
                        	}
                    	}
              		}
          		}
                // 비회원일 때
                else{
                	alert("로그인 후 이용가능합니다.");
                    location.replace('/revel/login.rv');
                }
            });
                            			
            // 독서상태 표현하기
            var targetRstatus = "<c:out value='${rstatus}'/>";
			if(targetRstatus == 1){
				$('.anime__details__btn .follow-btn:eq(0)').css({'background-color':'#e89f12'});	
			}
			else if(targetRstatus == 2){
				$('.anime__details__btn .follow-btn:eq(1)').css({'background-color':'#e89f12'});
			}
			else if(targetRstatus == 3){
				$('.anime__details__btn .follow-btn:eq(2)').css({'background-color':'#e89f12'});
			}
			else if(targetRstatus == 0){
				$('.anime__details__btn .follow-btn:eq(3)').css({'background-color':'#e89f12'});
			}
    	});
	</script>
	
	<!-- 링크 클릭할 때 링크클릭수 증가하기 -->
	<script type="text/javascript">
		$(function(){
			// 네이버 링크 클릭할 때 링크클릭수 증가하기
        	$('.anime__details__btn #naver').click(function(){
        		var platform = $('.anime__details__btn #naver').text();
            	var bookNum = $('.anime__details__rating').attr('data-rate');
            	var url = $('#naver').attr('data-rate');
                            				
            	if(platform = '네이버시리즈'){
            		platform = 'naver';	
           		}
                            				
            	// 선택한 링크 주소로 이동하는 form 생성
            	var newform = $('<form></form>');
                            				
            	// form 환경설정
            	newform.attr("name","move");
            	newform.attr("method","post");
            	newform.attr("action","/revel/detail/linkClick.rv")
                            				
            	// 파라미터 넘길 값 설정
            	newform.append($('<input/>', {type:'hidden', name:'platform', value:platform}));
            	newform.append($('<input/>', {type:'hidden', name:'bookNum', value:bookNum}));
            	newform.append($('<input/>', {type:'hidden', name:'url', value:url}));
                            				
            	newform.appendTo('body');
                            				
            	// sumbit 설정
            	newform.submit();
       		});
                            			
			// 카카오 링크 클릭할 때 카카오 링크클릭 수 증가하기
        	$('.anime__details__btn #kakao').click(function(){
        		var platform = $('.anime__details__btn #kakao').text();
            	var bookNum = $('.anime__details__rating').attr('data-rate');
            	var url = $('#kakao').attr('data-rate');
                            				
           		if(platform = '카카오페이지'){
           			platform = 'kakao';	
           		}
                            				
            	//선택한 링크 주소로 이동하는 form 생성
            	var newform = $('<form></form>');
                            				
            	//form 환경설정
            	newform.attr("name","move");
            	newform.attr("method","post");
            	newform.attr("action","/revel/detail/linkClick.rv")
                            				
            	//파라미터 넘길 값 설정
            	newform.append($('<input/>', {type:'hidden', name:'platform', value:platform}));
            	newform.append($('<input/>', {type:'hidden', name:'bookNum', value:bookNum}));
            	newform.append($('<input/>', {type:'hidden', name:'url', value:url}));
                            				
            	newform.appendTo('body');
                            				
            	//submit 설정
            	newform.submit();
        	});
		});
    </script>
    
    <!-- 댓글 등록 -->
    <script type="text/javascript">
    	function reviewCheck()
        {
        	var content = document.review.content.value;
            if(content != null && content != "")
            {	
            	var result = confirm("댓글을 등록하시겠습니까?");	
                if(!result)
                {	return false;	}
            }
            else
            {	
            	alert("댓글 내용을 입력해주세요.");	
                return false;	
            }
        }
     </script>
     
     <!-- 비회원일 때 댓글 검사 -->               
     <script type="text/javascript">
     	function reviewLogon()
        {	alert("로그인 후 이용할 수 있습니다.");	}
     </script>

  	<!-- 검색 유효성 검사 -->
  	<script type="text/javascript">
  		function valueCheck()
  		{
  			var theme = document.search.theme.value;
  			var searchWord = document.search.searchWord.value;
  		
  			if(theme == 'none' || theme == null)
  			{
  				alert("검색 테마를 선택해주세요.");
  				return false;
  			}
  		
  			if(searchWord == null || searchWord == "")
  			{
  				alert("검색어를 입력해주세요.");
  				return false;
  			}
  		}
  	</script>
  
  	<!-- 댓글 삭제용 -->
  	<script type="text/javascript">
		function reviewDeleteOpen()
		{
			var msg = confirm("댓글을 삭제하겠습니까?");	
			if(msg == true)
			{ //확인
				window.location="/revel/detail/reviewDelete.rv";
			}	
			else
			{
				return false; //삭제 취소
			}
		}
  	</script>

<!-- Js Plugins -->
<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/player.js"/>"></script>
<script src="<c:url value="/js/jquery.nice-select.min.js"/>"></script>
<script src="<c:url value="/js/mixitup.min.js"/>"></script>
<script src="<c:url value="/js/jquery.slicknav.js"/>"></script>
<script src="<c:url value="/js/owl.carousel.min.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>

</body>


</html>