<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 공지사항 목록 페이지
	http://localhost:8080/revel/help/notice.rv 
--%>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Revel | Notice</title>

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
    
    <!-- 카테고리 구성 항목 -->
    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/revel/main.rv"><i class="fa fa-home"></i> Home</a>
                        <a href="/revel/help/notice.rv">help</a>
                        <span onclick="location.href='/revel/help/notice.rv'" style="cursor:pointer;">Notice</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

	<!-- Product Section Begin -->
	<section class="product-page spad">
    	<div class="container">
        	<div class="row">
                <div class="col-lg-8">
                    <div class="product__page__content">
                        <div class="product__page__title">
                            <div class="row">
                                <div class="col-lg-8 col-md-8 col-sm-6">
                                	<!-- 현재 페이지 제목 -->
                                    <div class="section-title">
                                        <h4>공지사항</h4>
                                    </div>
                                </div>
                                <!-- 공지사항 추가 기능(관리자만 가능) -->
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <div class="product__page__filter">
                                      <c:if test="${sessionScope.status == 100}">	
                                      	<a href="/revel/help/notice/write.rv"><p>글쓰기</p></a>
                                      </c:if>  
                                    </div>
                                </div>
                            </div>
                        </div>
		
		<!-- count가 0개이면 저장된 공지사항이 없다. -->
		<div class="blog__details__content">
			<div class="blog__details__text" align="center">
				<c:if test="${page.count == 0}">
					<p>게시판에 저장된 글이 없습니다.</p>
				</c:if>
			</div>
		</div>
		
		<!-- count가 1 이상이면 저장된 공지사항 전체 목록을 호출한다. -->
		<c:if test="${page.count >0}">
			<div class="blog__details__item__text">
				<table border="1" width="750" cellpadding="0" cellspacing="0" align="center">
					<tr height="30">
						<td align="center" width="70"  ><p>번 호</p></td>
						<td align="center" width="350" ><p>제   목</p></td>
						<td align="center" width="130" ><p>작성자</p></td>
						<td align="center" width="250" ><p>작성일</p></td>
					</tr>					
			
					<c:forEach var="notice" items="${noticeList}">
					<tr height="30">
						<td align="center" width="70" >
							<p>${number}</p>
							<c:set var="number" value="${number-1}" />
						</td>
						<td width="300" align="center">
							<a href="/revel/help/notice/content.rv?noticeNum=${notice.index_notice}&pageNum=${page.currentPage}">
								<p>${notice.title}</p>
							</a>
						<td align="center" width="130"><p>${notice.writer}</p></td>
						<td align="center" width="200">
							<p><fmt:formatDate value="${notice.reg_time}" pattern="yyyy-MM-dd HH:mm" /></p>
						</td>	
					</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		
		<!-- 페이지 넘기기(이전, 다음페이지) -->
		 <c:if test="${page.count > 0}">
         	<fmt:parseNumber var="pageCount" value="${page.count / page.pageSize + (page.count % page.pageSize == 0 ? 0 : 1)}" integerOnly="ture"/>
         	<c:set var="pageBlock" value="${5}" />
            <fmt:parseNumber var="result" value="${page.currentPage/5}" integerOnly="true" />
            <c:set var="startPage" value="${result*5+1}" />
            <c:set var="endPage" value="${startPage + pageBlock -1}" />
            <c:if test="${endPage > pageCount}">
            	<c:set var="endPage" value="${pageCount}" />
            </c:if>
                    	
            <div class="product__pagination" align="center">
            	<c:if test="${startPage > 5}">
                	<a href="/revel/help/notice.rv?pageNum=${startPage-5}"><i class="fa fa-angle-double-left"></i></a>
                </c:if>
                        	
                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                	<a href="/revel/help/notice.rv?pageNum=${i}">${i}</a>
                </c:forEach>
                        	
                <c:if test="${endPage < pageCount}" >
                    <a href="/revel/help/notice.rv?pageNum=${startPage+5}"><i class="fa fa-angle-double-right"></i></a>
                </c:if>
             </div>
          </c:if>
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
	