<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 메인 페이지
	http://localhost:8080/revel/main.rv  
--%>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Revel</title>

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
                                <li><a href="/revel/genre/total.rv">장르 <span class="arrow_carrot-down"></span></a>
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

    <!-- Hero Section Begin : main 페이지 슬라이드 화면-->
    <!-- <div class="hero__slider owl-carousel"> : 슬라이드 갯수 설정 가능 -->
    <!-- data-setbg="/revel/img/romance_naver/romance_1.jpg" : 해당 이미지 경로 설정 -->
    <!-- <a href="/revel/detail/book1.rv"><span>Watch Now</span> : 바로가기 경로 설정 -->
    <section class="hero">
        <div class="container">
            <div class="hero__slider owl-carousel">
            	<c:forEach var="mainBook" items="${mainBook}" begin="0" end="4">
                	<div class="hero__items set-bg" data-setbg="${mainBook.image}" onclick="location.href='/revel/detail.rv?bookNum=${mainBook.index_book}';" style="cursor:pointer; width:1172px; height:750px">
                    	<div class="row">
                        	<div class="col-lg-6">
                            	<div class="hero__text">
                                	<div class="label">
                                		<c:if test="${mainBook.genre == 1}">로맨스</c:if>
                                		<c:if test="${mainBook.genre == 2}">로맨스 판타지</c:if>
                                		<c:if test="${mainBook.genre == 3}">판타지</c:if>
                                		<c:if test="${mainBook.genre == 4}">현대 판타지</c:if>
                                		<c:if test="${mainBook.genre == 5}">무협</c:if>
                                	</div>
                                		<h2>${mainBook.title}</h2>
                                		<p>${mainBook.writer}/${mainBook.publisher}</p>
                                		<p>${mainBook.plot}</p>
                                		<a href="/revel/detail.rv?bookNum=${mainBook.index_book}"><span>보러가기</span> <i class="fa fa-angle-right"></i></a>
                            	</div>
                        	</div>
                    	</div>
                	</div>
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

<!-- Product Section Begin : 랭킹 항목 -->
    <!-- <div class="section-title"> : 랭킹 항목 제목 작성(최신순, 인기순, 추천순) -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                
	                <!-- 로그인한 회원에게만 나타난다. 읽고있다, 읽을거다 목록 3개 (비회원, 탈퇴자, 관리자 x)-->
	                <c:if test="${sessionScope.status == 11 || sessionScope.status == 12}">
	                    <div class="trending__product">
	                        <div class="row">
	                            <div class="col-lg-8 col-md-8 col-sm-8">
	                            <!-- <div class="section-title"> : section-title 작성(인기작품, 추천작품, 최신작품 등)  -->
	                                <!-- 읽고있다 -->
	                                <div class="section-title">
	                                    <h4>${sessionScope.nick}님이 읽고 계신 작품</h4>
	                                </div>
	                            </div>
	                            <div class="col-lg-4 col-md-4 col-sm-4">
	                                <div class="btn__all">	<!-- 더보기 버튼 -->
	                                    <a href="/revel/mypage/myReading.rv" class="primary-btn">View All <span class="arrow_right"></span></a>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <c:if test="${countReading == 0}">
	                        	<div class="col-lg-8">
                    			<div class="blog__details__content">
                            		<div class="blog__details__item__text">
                        				<p align="center">${sessionScope.nick}님이 읽고 계신 작품이 없습니다.</p>
                        			</div>
                        		</div>
                        	</div>	
	                        </c:if>
	                        
	                        <c:if test="${countReading != 0}">		                        
		                        <div class="row">
			                        <c:forEach var="readingList" items="${readingList}">	
			                            <div class="col-lg-4 col-md-6 col-sm-6">
			                                <div class="product__item">
			                                	<!-- 해당 웹소설 이미지 주소 -->
			                                    <div class="product__item__pic set-bg" data-setbg="${readingList.image}" onclick="location.href='/revel/detail.rv?bookNum=${readingList.index_book}';" style="cursor:pointer;">
			                                     	<div class="ep"><c:if test="${readingList.comple == 1}">완결</c:if> <c:if test="${readingList.comple == 0}">연재중</c:if> / ${readingList.times}화</div>
			                                      	<div class="comment"><i class="icon_chat_alt"></i> ${readingList.review_num}</div>
			                                       	<div class="view"><i class="icon_star"></i> ${readingList.rating_revel}</div>
			                                    </div>
			                                    <div class="product__item__text">
			                                        <ul>
			                                            <li style="background-color: RebeccaPurple">
	                                            			<c:if test="${readingList.genre == 1}">
	                                            				<c:set var="gen" value="romance"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">로맨스</a>
	                                            			</c:if>
	                                            			<c:if test="${readingList.genre == 2}">
	                                            				<c:set var="gen" value="rofan"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">로맨스판타지</a>
	                                            			</c:if>
	                                            			<c:if test="${readingList.genre == 3}">
	                                            				<c:set var="gen" value="fantasy"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">판타지</a>                                            			
	                                            			</c:if>
	                                            			<c:if test="${readingList.genre == 4}">
	                                            				<c:set var="gen" value="mofan"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">현대판타지</a>
	                                            			</c:if>
	                                            			<c:if test="${readingList.genre == 5}">
	                                            				<c:set var="gen" value="heroism"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">무협</a>
	                                            			</c:if>
			                                            </li>
			                                            <!-- 키워드 나타내기-->
			                                            <c:forEach var="keywordMap" items="${keywordMap}">
			                                            	<c:set var="keyVal" value="${keywordMap.key}" />
			                                            	<c:if test="${keyVal == readingList.index_book}">
			                                            		<c:forEach var="keyword" items="${keywordMap.value}" begin="0" end="2" step="1">
			                                            			<li>${keyword.keywords}</li>
			                                            		</c:forEach>
			                                            	</c:if>
			                                            </c:forEach>
			                                        </ul>
			                                        <h5><a href="/revel/detail.rv?bookNum=${readingList.index_book}">${readingList.title}</a></h5>
			                                    </div>                                
			                                </div>
			                            </div>
			                        </c:forEach>    
		                        </div>
	                        </c:if>
	                    </div>
	                    
	                    
	                    <div class="trending__product">
	                        <div class="row">
	                            <div class="col-lg-8 col-md-8 col-sm-8">
	                                <!-- 읽을거다 -->
	                                <div class="section-title">
	                                    <h4>${sessionScope.nick}님이 읽을 예정인 작품</h4>
	                                </div>
	                            </div>
	                            <div class="col-lg-4 col-md-4 col-sm-4">
	                                <div class="btn__all">	<!-- 더보기 버튼 -->
	                                    <a href="/revel/mypage/myReadwant.rv" class="primary-btn">View All <span class="arrow_right"></span></a>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <c:if test="${countReadWant == 0}">
	                        	<div class="col-lg-8">
                    			<div class="blog__details__content">
                            		<div class="blog__details__item__text">
                        				<p align="center">${sessionScope.nick}님이 읽을 예정인 작품이 없습니다.</p>
                        			</div>
                        		</div>
                        	</div>	
	                        </c:if>
	                        
	                        <c:if test="${countReadWant != 0}">		                        
		                        <div class="row">
			                        <c:forEach var="readWantList" items="${readWantList}">	
			                            <div class="col-lg-4 col-md-6 col-sm-6">
			                                <div class="product__item">
			                                	<!-- 해당 웹소설 이미지 주소 -->
			                                    <div class="product__item__pic set-bg" data-setbg="${readWantList.image}" onclick="location.href='/revel/detail.rv?bookNum=${readWantList.index_book}';" style="cursor:pointer;">
			                                     	<div class="ep"><c:if test="${readWantList.comple == 1}">완결</c:if> <c:if test="${readWantList.comple == 0}">연재중</c:if> / ${readWantList.times}화</div>
			                                      	<div class="comment"><i class="icon_chat_alt"></i> ${readWantList.review_num}</div>
			                                       	<div class="view"><i class="icon_star"></i> ${readWantList.rating_revel}</div>
			                                    </div>
			                                    <div class="product__item__text">
			                                        <ul>
			                                            <li style="background-color: RebeccaPurple">
	                                            			<c:if test="${readWantList.genre == 1}">
	                                            				<c:set var="gen" value="romance"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">로맨스</a>
	                                            			</c:if>
	                                            			<c:if test="${readWantList.genre == 2}">
	                                            				<c:set var="gen" value="rofan"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">로맨스판타지</a>
	                                            			</c:if>
	                                            			<c:if test="${readWantList.genre == 3}">
	                                            				<c:set var="gen" value="fantasy"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">판타지</a>                                            				
	                                            			</c:if>
	                                            			<c:if test="${readWantList.genre == 4}">
	                                            				<c:set var="gen" value="mofan"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">현대판타지</a>
	                                            			</c:if>
	                                            			<c:if test="${readWantList.genre == 5}">
	                                            				<c:set var="gen" value="heroism"/>
	                                            				<a href='/revel/genre/${gen}.rv' style="color: white">무협</a>
	                                            			</c:if>
			                                            </li>
			                                            <!-- 키워드 나타내기 -->
			                                            <c:forEach var="keywordMap" items="${keywordMap}">
			                                            	<c:set var="keyVal" value="${keywordMap.key}" />
			                                            	<c:if test="${keyVal == readWantList.index_book}">
			                                            		<c:forEach var="keyword" items="${keywordMap.value}" begin="0" end="2" step="1">
			                                            			<li>${keyword.keywords}</li>
			                                            		</c:forEach>
			                                            	</c:if>
			                                            </c:forEach>
			                                        </ul>
			                                        <h5><a href="/revel/detail.rv?bookNum=${readWantList.index_book}">${readWantList.title}</a></h5>
			                                    </div>                                
			                                </div>
			                            </div>
			                        </c:forEach>    
		                        </div>
	                        </c:if>
	                    </div>
	                </c:if>
                    
                    <!-- 평점순 작품목록 -->
                    <div class="popular__product">
                        <div class="row">
                            <div class="col-lg-8 col-md-8 col-sm-8">
                                <div class="section-title">
                                    <h4>평점 높은 작품</h4>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-4">
                                <div class="btn__all">
                                    <a href="/revel/genre/total.rv" class="primary-btn">View All <span class="arrow_right"></span></a>
                                </div>
                            </div>
                        </div>
                        <div class="row">

		                        <c:forEach var="ratingList" items="${ratingList}">	
		                            <div class="col-lg-4 col-md-6 col-sm-6">
		                                <div class="product__item">
		                                	<!-- 해당 웹소설 이미지 주소 -->
		                                    <div class="product__item__pic set-bg" data-setbg="${ratingList.image}" onclick="location.href='/revel/detail.rv?bookNum=${ratingList.index_book}';" style="cursor:pointer;">
		                                     	<div class="ep"><c:if test="${ratingList.comple == 1}">완결</c:if> <c:if test="${ratingList.comple == 0}">연재중</c:if> / ${ratingList.times}화</div>
		                                      	<div class="comment"><i class="icon_chat_alt"></i> ${ratingList.review_num}</div>
		                                       	<div class="view"><i class="icon_star"></i> ${ratingList.rating_revel}</div>
		                                    </div>
		                                    <div class="product__item__text">
		                                        <ul>
		                                            <li style="background-color: RebeccaPurple">
                                            			<c:if test="${ratingList.genre == 1}">
                                            				<c:set var="gen" value="romance"/>
                                            				<a href='/revel/genre/${gen}.rv' style="color: white">로맨스</a>
                                            			</c:if>
                                            			<c:if test="${ratingList.genre == 2}">
                                            				<c:set var="gen" value="rofan"/>
                                            				<a href='/revel/genre/${gen}.rv' style="color: white">로맨스판타지</a>
                                            			</c:if>
                                            			<c:if test="${ratingList.genre == 3}">
                                            				<c:set var="gen" value="fantasy"/>
                                            				<a href='/revel/genre/${gen}.rv' style="color: white">판타지</a>                                            				
                                            			</c:if>
                                            			<c:if test="${ratingList.genre == 4}">
                                            				<c:set var="gen" value="mofan"/>
                                            				<a href='/revel/genre/${gen}.rv' style="color: white">현대판타지</a>
                                            			</c:if>
                                            			<c:if test="${ratingList.genre == 5}">
                                            				<c:set var="gen" value="heroism"/>
                                            				<a href='/revel/genre/${gen}.rv' style="color: white">무협</a>
                                            			</c:if>
		                                            </li>
		                                            <!-- 키워드 나타내기 -->
		                                            <c:forEach var="keywordMap" items="${keywordMap}">
		                                            	<c:set var="keyVal" value="${keywordMap.key}" />
		                                            	<c:if test="${keyVal == ratingList.index_book}">
		                                            		<c:forEach var="keyword" items="${keywordMap.value}" begin="0" end="2" step="1">
		                                            			<li>${keyword.keywords}</li>
		                                            		</c:forEach>
		                                            	</c:if>
		                                            </c:forEach>
		                                        </ul>
		                                        <h5><a href="/revel/detail.rv?bookNum=${ratingList.index_book}">${ratingList.title}</a></h5>
		                                    </div>                                
		                                </div>
		                            </div>
		                        </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-8">
                    <div class="product__sidebar">
                        <div class="product__sidebar__view">
                        
                        <!-- Daily Ranking 일간 랭킹 -->
                            <div class="section-title">	<!-- sub 타이틀 테마(일간, 주간, 월간 랭킹) -->
                                <h5>Daily Ranking</h5>
                            </div>
                            <c:if test="${countRank == 0}">
                        		<div class="col-lg-8">
                    			<div class="blog__details__content">
                            		<div class="blog__details__item__text">
                        				<p align="center">해당 목록은 존재하지 않습니다.</p>
                        			</div>
                        		</div>
                        	</div>	
                        	</c:if>
                        	
                        	<c:if test="${countRank > 0}">
	                            <c:forEach var="daily" items="${daily}" begin="0" end="4">
		                            <div class="filter__gallery">
		                            	<!-- mix day year : day와 year에서만 보인다. -->
		                                <div class="product__sidebar__view__item set-bg mix day years"
		                                data-setbg="${daily.image}" onclick="location.href='/revel/detail.rv?bookNum=${daily.index_book}';" style="cursor:pointer;">	<!--  해당 이미지 경로 -->
		                               		<div class="ep"><c:if test="${daily.comple == 1}">완결</c:if> <c:if test="${daily.comple == 0}">연재중</c:if> / ${daily.times}화</div>	<!-- 현재연재량 / 총 연재량 -->
		                                	<div class="view"><i class="icon_star"></i> ${daily.rating_revel}</div>	<!-- 평점 -->
		                                	<h5><a href="/revel/detail.rv?bookNum=${daily.index_book}">${daily.title}</a></h5>	<!-- 제목 및 주소 -->
		                            	</div>
		                            </div>
	                            </c:forEach>
                            </c:if>
        				</div>
    				</div>
    				
				    <!-- 최신 댓글 작품 -->				
				    <div class="product__sidebar__comment">
				        <div class="section-title">
				            <h5>New Comment</h5>
				        </div>
				        <c:if test="${countComment == 0}">
				        	<div class="col-lg-8">
                    			<div class="blog__details__content">
                            		<div class="blog__details__item__text">
                        				<p align="center">해당 목록은 존재하지 않습니다.</p>
                        			</div>
                        		</div>
                        	</div>	
				        </c:if>
				        
				        <c:if test="${countComment > 0}">
					        <c:forEach var="commentList" items="${commentList}">
						        <div class="product__sidebar__comment__item">
						            <div class="product__sidebar__comment__item__pic" style="width:100px">
						                <img src="${commentList.image}" onclick="location.href='/revel/detail.rv?bookNum=${commentList.index_book}';" style="cursor:pointer;">
						            </div>
						            <div class="product__sidebar__comment__item__text">
		                                <ul>
		                                    <li style="background-color: RebeccaPurple">
                                            	<c:if test="${commentList.genre == 1}">
                                            		<c:set var="gen" value="romance"/>
                                            		<a href='/revel/genre/${gen}.rv' style="color: white">로맨스</a>
                                            	</c:if>
                                            	<c:if test="${commentList.genre == 2}">
                                            		<c:set var="gen" value="rofan"/>
                                            		<a href='/revel/genre/${gen}.rv' style="color: white">로맨스판타지</a>
                                            		</c:if>
                                            	<c:if test="${commentList.genre == 3}">
                                            		<c:set var="gen" value="fantasy"/>
                                            		<a href='/revel/genre/${gen}.rv' style="color: white">판타지</a>                                            				
                                            	</c:if>
                                            	<c:if test="${commentList.genre == 4}">
                                            		<c:set var="gen" value="mofan"/>
                                            		<a href='/revel/genre/${gen}.rv' style="color: white">현대판타지</a>
                                            	</c:if>
                                            	<c:if test="${commentList.genre == 5}">
                                            		<c:set var="gen" value="heroism"/>
                                            		<a href='/revel/genre/${gen}.rv' style="color: white">무협</a>
                                            	</c:if>
		                                    </li>
		                                    <!-- 키워드 나타내기 -->
		                                    <c:forEach var="keywordMap" items="${keywordMap}">
		                                        <c:set var="keyVal" value="${keywordMap.key}" />
		                                        <c:if test="${keyVal == commentList.index_book}">
		                                            <c:forEach var="keyword" items="${keywordMap.value}" begin="0" end="2" step="1">
		                                            	<li>${keyword.keywords}</li>
		                                            </c:forEach>
		                                    	</c:if>
		                                    </c:forEach>
		                                </ul>
					                <h5><a href="/revel/detail.rv?bookNum=${commentList.index_book}">${commentList.title}</a></h5>	<!-- 제목 및 주소 -->
					                <span><i class="icon_chat_alt"></i> ${commentList.review_num}</span>	<!-- 전체댓글수 -->
					            	</div>
					    		</div>
					    	</c:forEach>    
				        </c:if>
				    </div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Product Section End -->

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