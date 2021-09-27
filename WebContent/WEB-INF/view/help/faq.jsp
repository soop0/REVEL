<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%-- 
	revel 자주하는 질문 페이지
	http://localhost:8080/revel/help/qna.rv 
--%>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Revel | 자주하는 질문</title>

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
    
    <!-- Custom fonts for this template-->
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

  
    
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
                        <span onclick="location.href='/revel/help/qna.rv'" style="cursor:pointer;">FAQ</span>
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
                                        <h4>자주하는 질문</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                     	<div class="blog__details__content">
							<div class="blog__details__text">
                        		<!-- FAQ 내용 작성 -->
                            	<div class="card shadow mb-4">
                                	<div class="card-header py-3">
                                    	<h6 class="m-0 font-weight-bold text-primary">로그인 및 회원가입은 카카오와 네이버만 가능한가요?</h6>
                                	</div>
                                	<div class="card-body">
                                    	현재 Revel에서는 카카오와 네이버를 통해서만 로그인 및 회원가입이 가능합니다.</br>
                                    	단, Revel에서 로그아웃을 할 때 로그인 했던 플랫폼의 계정이 남아있으니 </br>
                                    	꼭! 로그인 했던 해당 플랫폼에서 계정 로그아웃 해주시기 바랍니다.
                                	</div>
                            	</div>
                            
                            	<div class="card shadow mb-4">
                                	<div class="card-header py-3">
                                    	<h6 class="m-0 font-weight-bold text-primary">작품 업데이트는 언제 되는 건가요?</h6>
                                	</div>
                                	<div class="card-body">
                                    	현재는 업데이트 계획이 없습니다.</br>
                                    	정식 서비스 출시할 때, 정기적인 업데이트 진행을 생각하고 있습니다. 
                                	</div>
                            	</div>
                            
                            	<div class="card shadow mb-4">
                                	<div class="card-header py-3">
                                    	<h6 class="m-0 font-weight-bold text-primary">개인 맞춤 추천은 어떻게 추천되나요?</h6>
                                	</div>
                                	<div class="card-body">
                                    	회원님이 평가한 평점, 선호하는 장르와 웹소설 분량을 토대로 Revel에서 자체 제작한</br>
                                    	추천 알고리즘을 통해 회원별 개인 맞춤 작품 추천 서비스를 제공하고 있습니다.
                                	</div>
                            	</div>
                            
                            	<div class="card shadow mb-4">
                                	<div class="card-header py-3">
                                    	<h6 class="m-0 font-weight-bold text-primary">추천 작품이 제 취향이 아닌 것 같아요.</h6>
                                	</div>
                                	<div class="card-body">
                                    	Revel 추천 시스템은 회원이 평가한 작품평점, 선호하는 장르, 웹소설의 분량을 기준으로 추천하기</br>
                                    	때문에 더 많은 작품의 평점을 매길수록 회원의 취향에 맞는 작품을 추천해드릴 수 있습니다.</br>
                                    	또한, 추천된 작품수가 적을 때 선호장르와 분량 변경을 통해 추천된 작품 수가 달라질 수 있습니다.
                                	</div>
                            	</div>
                            
                            	<div class="card shadow mb-4">
                                	<div class="card-header py-3">
                                    	<h6 class="m-0 font-weight-bold text-primary">제가 쓴 댓글이 삭제되었어요.</h6>
                                	</div>
                                	<div class="card-body">
                                    	부적절한 용어를 사용한 댓글은 관리자가 즉시 발견하여 삭제합니다.</br>
                                    	클린한 댓글문화를 함께 만들어 나갔으면 좋겠습니다.
                                	</div>
                            	</div>
                            
                            	<div class="card shadow mb-4">
                                	<div class="card-header py-3">
                                    	<h6 class="m-0 font-weight-bold text-primary">탈퇴 후 다시 가입했더니 제 정보가 없어졌어요.</h6>
                                	</div>
                                	<div class="card-body">
                                    	탈퇴를 하게 되면 Revel에 저장된 기존 회원 정보에 접근을 할 수 없게 됩니다.</br>
                                    	탈퇴를 선택할 때, 신중하게 결정해주세요.
                                	</div>
                            	</div>          
							</div>
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

   <!-- Bootstrap core JavaScript-->
    <script src="/revel/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/revel/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/revel/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/revel/resources/js/sb-admin-2.min.js"></script>


</body>

</html>