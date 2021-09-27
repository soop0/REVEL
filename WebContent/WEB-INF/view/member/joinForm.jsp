<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 회원가입 페이지
	http://localhost:8080/revel/join.rv 
	비회원/탈퇴회원만 이용할 수 있는 서비스 : aop 처리 페이지
--%>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Revel | Join</title>

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
    
    <!-- login 메인 화면 -->
    <!-- Normal Breadcrumb Begin -->
    <section class="normal-breadcrumb set-bg" data-setbg="/revel/img/normal-breadcrumb.jpg">	<!-- 로그인 이미지 -->
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="normal__breadcrumb__text">
                        <h2>Join</h2>	<!-- 로그인 -->
                        <p>Please participate in the survey.</p>	<!-- 로그인 문구 -->
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Normal Breadcrumb End -->
    
    <script>
		//필수정보 입력 확인
		function enter()
		{
			var nick = document.joinform.nick.value;
			var year = document.joinform.year.value;
			var month = document.joinform.month.value;
			var date = document.joinform.date.value;
			var gender = document.joinform.gender.value;
		
			if(!nick)
			{
				alert("닉네임을 입력해주세요.");
				document.joinform.nick.focus();
				return false;
			}
		
			if(year == "" || month == "" || date == "")
			{
				alert("생년월일을 선택해주세요");
				return false;
			}
		
			if(!gender)
			{
				alert("성별을 선택해주세요");
				return false;
			}
		}
	
		function nickCheck()
		{
			var nick =document.joinform.nick.value;
			if(nick)
			{	open("/revel/mypage/nickCheck.rv?nick="+nick,"check","width=400 height=300");	}
			else
			{	alert("닉네임을 입력해 주세요");	}
		}
	</script>
    
    <!-- Join Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                	<div class="blog__details__content">
                        <div class="blog__details__item__text">
                        	<div class="product__page__title">
                        		<div class="product__page__filter">
                        			<div class="col-lg-12" align="center">
                    				<h4 align="center">기본정보 입력</h4>
										<form name="joinform" action="/revel/joinPro.rv" method="post" onsubmit="return enter();">
											<table width="500" cellpadding="0" cellspacing="0" align="center">
												<tr height="60">
													<td align="center" width="100"><p>닉네임*</p></td>
													<td width="280" align="left">
														<input type="text" name="nick" />
														<input type="button" value="중복확인" onclick="nickCheck();"  /> <%-- 닉네임 중복검사 연결해야함--%>
													</td>
												</tr>
												<tr height="60">
													<td align="center" width="100"><p>생년월일*</p></td>
													<td width="280" align="left">
														<%-- 월에 따라 30일까지/ 31일까지 나오도록 하기 --%>
														<select name="year" class="nice-select">
															<option value="">선택</option>
																<c:forEach var="y" begin="1950" end="2021" step="1">
																	<option>${y}</option>
																</c:forEach>
														</select><p>년</p>
														<select name="month" class="nice-select">
															<option value="">선택</option>
																<c:forEach var="m" begin="1" end="12" step="1">
																	<option>${m}</option>
																</c:forEach>
														</select><p>월</p>
														<select name="date" class="nice-select">
															<option value="">선택</option>
																<c:forEach var="d" begin="1" end="31" step="1">
																	<option>${d}</option>
																</c:forEach>
														</select><p>일</p>
													</td>
												</tr>
												<tr height="60">
													<td align="center" width="100"><p>성별*</p></td>
													<td width="280" align="left"> 
														<input type="radio" name="gender" value="1"/><p>남자</p>	
														<input type="radio" name="gender" value="2" /><p>여자</p>
													</td>
												</tr>
											</table>
											<input type="hidden" name="platform_id" value="${platform_id}" />
											<input type="hidden" name="platform" value="${platform}" />
											<input type="hidden" name="index_member" value="${index_member}" />
											<input type="hidden" name="status" value="${status}" />
									</br></br></br>
                    				<h4 align="center">당신의 취향은?</h4>
											<table width="700" cellpadding="0" cellspacing="0" align="center">
												<tr height="60">
													<td width="250" align="center"><p>어떤 책을 주로 읽으시나요?</p></td>
													<td width="400" align="left">
														<input type="radio" name="volume" value="1" /><p> 장편</p> 
														<input type="radio" name="volume" value="2" /><p> 중편</p> 
														<input type="radio" name="volume" value="3" /><p> 단편</p> 
														<input type="radio" name="volume" value="4" /><p> 아무거나</p>
													</td>
												</tr>
												<tr height="60">
													<td width="250" align="center"><p>선호하는 장르가 있으신가요?</p></td>
													<td width="400" align="left">
														<input type="checkbox" name="romance" value="1" /><p> 로맨스</p> 
														<input type="checkbox" name="rofan" value="1" /><p> 로판</p> 
														<input type="checkbox" name="fantagy" value="1" /><p> 판타지</p>
														<input type="checkbox" name="mofan" value="1" /><p> 현판</p> 
														<input type="checkbox" name="heroism" value="1" /><p> 무협</p>
													</td>
												</tr>
											</table>
											</br></br>
											<button type="submit" class="site-btn">완료</button>
											<button type="button" class="site-btn" onclick="history.go(-1)">이전으로</button>
                 						</form>
                 					</div>
                 				</div>
                        	</div>
                 		</div>
                 	</div>
            	</div>
        	</div>
    	</div>
    </section>
    <!-- Login Section End -->
	
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

