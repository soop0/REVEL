<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 소설관리 목록 페이지(신규 책 등록, 수정, 삭제)
	http://localhost:8080/revel/manage/novel.rv 
	관리자일 때만 이용할 수 있는 서비스 : aop 처리 페이지
--%>

<!-- session 검사 : 관리자만 이용가능 -->
<script type="text/javascript">
	var status = '<c:out value="${sessionScope.status}"/>';
	if(status != 100)
	{
		alert("해당 페이지를 이용할 권한이 없습니다.");
		window.location="/revel/main.rv";
	}
</script>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Revel | Management | Novel</title>

    <!-- Custom fonts for this template -->
    <link href="/revel/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/revel/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/revel/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <!-- 왼쪽 카테고리 항목 작성 -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <!-- revel 로고 등록 -->
            <a class="sidebar-brand d-flex align-items-center justify-content-right" href="/revel/main.rv">
                <img src="/revel/img/revel2.png">
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <!-- 관리자 관리 페이지로 돌아가는 부분 -->
            <li class="nav-item">
                <a class="nav-link" href="/revel/manage.rv">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Management</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <!-- revel List 관리 제목 -->
            <div class="sidebar-heading">
                Revel List
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <!-- 관리자 서비스 항목(소설, 회원, 게시판) -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/revel/manage/membership.rv">
                    <i class="fas fa-fw fa-users"></i>
                    <span>Membership</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="/revel/manage/novel.rv">
                    <i class="fas fa-fw fa-book"></i>
                    <span>Novel</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseBoard"
                    aria-expanded="true" aria-controls="collapseBoard">
                    <i class="fas fa-fw fa-clipboard-list"></i>
                    <span>Board</span>
                </a>
                <div id="collapseBoard" class="collapse" aria-labelledby="headingBoard" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Board:</h6>
                        <a class="collapse-item" href="/revel/manage/board/review.rv">Review</a>
                        <a class="collapse-item" href="/revel/manage/board/notice.rv">Notice</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <!-- 관리자 통계 관련 서비스 제목 -->
            <div class="sidebar-heading">
                Analysis
            </div>

            <!-- Nav Item - Charts -->
            <!-- 관리자 회원 통계 분석 서비스(전체 이용회원, 연령별(성별), 이용시간) -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/revel/manage/analysis/membership.rv">
                    <i class="fas fa-fw fa-chart-line"></i>
                    <span>Membership</span>
                </a>
            </li>

            <!-- Nav Item - Charts -->
            <!-- 관리자 소설 통계 분석 서비스(장르별 별점, 연령대, 인기도서, 키워드) -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseNovelCharts"
                	aria-expanded="true" aria-controls="collapseNovelCharts">
                    <i class="fas fa-fw fa-chart-bar"></i>
                    <span>Novel</span>
                </a>
                <div id="collapseNovelCharts" class="collapse" aria-labelledby="headingNovelCharts" 
                	data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Novel:</h6>
                        <a class="collapse-item" href="/revel/manage/analysis/novel/genre.rv">장르</a>
                        <a class="collapse-item" href="/revel/manage/analysis/novel/best.rv">인기도서</a>
                        <a class="collapse-item" href="/revel/manage/analysis/novel/keyword.rv">키워드</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <!-- 위쪽 Topbar 카테고리 항목 -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                	<i class="fa fa-bars"></i>
                </button>
                    
					<!-- Nav Item - User Information -->
                    <!-- anime 카테고리 항목 -->
                    <ul class="navbar-nav ml-auto" >
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">랭킹</span>
                            </a>
                            <!-- Dropdown - User Information -->
                            <!-- anime 카테고리 항목별 세부항목 -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="/revel/ranking/daily.rv">일간</a>
                                <a class="dropdown-item" href="/revel/ranking/weekly.rv">주간</a>
                             	<a class="dropdown-item" href="/revel/ranking/monthly.rv">월간</a>
                             	<a class="dropdown-item" href="/revel/ranking/total.rv">전체</a>
                            </div>
                        </li>
                        <c:if test="${sessionScope.status == 11 || sessionScope.status == 12 || sessionScope.status == 100}">
                        	<li class="nav-item dropdown no-arrow">
                            	<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                	data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                	<span class="mr-2 d-none d-lg-inline text-gray-600 small">추천</span>
                            	</a>
                            	<!-- Dropdown - User Information -->
                            	<!-- anime 카테고리 항목별 세부항목 -->
                            	<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                	aria-labelledby="userDropdown">
                                	<a class="dropdown-item" href="/revel/recommend/alert.rv">개인맞춤 추천</a>
                            	</div>
                        	</li>
                        </c:if>
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">플랫폼</span>
                            </a>
                            <!-- Dropdown - User Information -->
                            <!-- anime 카테고리 항목별 세부항목 -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="/revel/platform/naverSeries.rv">네이버 시리즈</a>
                             	<a class="dropdown-item" href="/revel/platform/kakaoPage.rv">카카오페이지</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">장르</span>
                            </a>
                            <!-- Dropdown - User Information -->
                            <!-- anime 카테고리 항목별 세부항목 -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="/revel/genre/total.rv">전체</a>
                                <a class="dropdown-item" href="/revel/genre/romance.rv">로맨스</a>
                             	<a class="dropdown-item" href="/revel/genre/rofan.rv">로맨스 판타지</a>
                             	<a class="dropdown-item" href="/revel/genre/fantasy.rv">판타지</a>
                             	<a class="dropdown-item" href="/revel/genre/mofan.rv">현대 판타지</a>
                             	<a class="dropdown-item" href="/revel/genre/heroism.rv">무협</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">고객센터</span>
                            </a>
                            <!-- Dropdown - User Information -->
                            <!-- anime 카테고리 항목별 세부항목 -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="/revel/help/notice.rv">공지사항</a>
                                <a class="dropdown-item" href="/revel/help/qna.rv">자주하는 질문</a>
                            </div>
                        </li>
                      
                    	<div class="topbar-divider d-none d-sm-block"></div>
                    
                    	<!-- 마이 페이지 / 로그인 아웃 코드 --> 
                 		<li class="nav-item dropdown no-arrow mx-1">
                 			<c:if test="${sessionScope.index_member > 0 && sessionScope.status != 50}">
                 				<c:if test="${sessionScope.status != 100}">
                    				<a class="nav-link dropdown-toggle" href="/revel/mypage.rv">
                    					<i class="fas fa-user text-gray-300"></i>
                        			</a>
                    			</c:if>
                    			<c:if test="${sessionScope.status == 100}">
                    				<a class="nav-link dropdown-toggle" href="/revel/manage.rv">
                    					<i class="fas fa-user text-gray-300"></i>
                        			</a>
                    			</c:if>
                        	</c:if>
                    	</li>
                    	<li class="nav-item dropdown no-arrow mx-1">
                    		<c:if test="${empty(sessionScope.index_member) || sessionScope.status == 50}">
                    			<a class="nav-link dropdown-toggle" href="/revel/login.rv">
                        			<i class="fas fa-lock text-gray-300"></i>
                        		</a>
                        	</c:if>
                        	<c:if test="${sessionScope.index_member > 0 && sessionScope.status != 50}">
                    			<a class="nav-link dropdown-toggle" href="/revel/logout.rv">
                        			<i class="fas fa-lock-open text-gray-300"></i>
                        		</a> 
                        	</c:if>
                    	</li>
					</ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <!-- 관리자페이지 내용 -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <!-- 관리자 페이지 제목 -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">소설 관리</h1>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row">

                        <!-- 읽을거다 Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body" onclick="location.href='/revel/manage/novel/ranking.rv?read=read_want'" style="cursor:pointer;">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                읽을거다</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${read_wantCount}권</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-book-medical fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 읽고 있다 Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body" onclick="location.href='/revel/manage/novel/ranking.rv?read=read_ing'" style="cursor:pointer;">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                읽고있다</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${read_ingCount}권</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-book-reader fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 다읽었다 Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body" onclick="location.href='/revel/manage/novel/ranking.rv?read=read_end'" style="cursor:pointer;">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            	다읽었다</div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${read_endCount}권</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-book fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 관심없다 Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body" onclick="location.href='/revel/manage/novel/ranking.rv?read=read_not'" style="cursor:pointer;">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                관심없다</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${read_notCount}권</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-book-dead fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Content Row -->

                    <!-- DataTales Example -->
                    <!-- 전체 소설 리스트  -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">소설 리스트</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<!-- count가 0이면 등록된 소설이 없다. -->
                            	<c:if test="${count == 0}">
                            		<p align="center">등록된 소설 항목이 없습니다.</p>
                            	</c:if>
                            	<!-- count가 1이상이면 등록된 소설리스트를 보여준다. -->
                            	<c:if test="${count > 0}">
                                	<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    	<thead align="center">
                                        	<tr>
                                            	<th>No.</th>
                                            	<th>제목</th>
                                            	<th>작가</th>
                                            	<th>출판사</th>
                                            	<th>장르</th>
                                            	<th>연령등급</th>
                                            	<th>연재/완결</th>
                                            	<th>평점(평가인원)</th>
                                            	<th>댓글수</th>
                                            </tr>
                                    	</thead>
                                  
                                       	<tbody align="center">
                                    		<c:forEach var="book" items="${bookList}">
                                        		<tr>
                                            		<td>${book.index_book}</td>
                                            		<td>${book.title}</td>
                                            		<td>${book.writer}</td>
                                            		<td>${book.publisher}</td>
                                            		<td>
                                            			<c:if test="${book.genre == 1}">로맨스</c:if>
                                            			<c:if test="${book.genre == 2}">로맨스 판타지</c:if>
                                            			<c:if test="${book.genre == 3}">판타지</c:if>
                                            			<c:if test="${book.genre == 4}">현대 판타지</c:if>                             			
                                            			<c:if test="${book.genre == 5}">무협</c:if>
                                            		</td>
                                            		<td>
                                            			<c:if test="${book.age_grade == 0}">전체</br>이용가</c:if>
                                            			<c:if test="${book.age_grade == 12}">12세</br>이용가</c:if>
                                            			<c:if test="${book.age_grade == 15}">15세</br>이용가</c:if>
                                            			<c:if test="${book.age_grade == 19}">청소년</br>관람불가</c:if>
                                            		</td>
                                            		<td>
                                            			총 ${book.times}화
                                            			<c:if test="${book.comple == 1}"></br>완결</c:if>
                                            			<c:if test="${book.comple == 0}"></br>연재중</c:if>
                                            		</td>
                                            		<td>${book.rating_revel}점(${book.rating_num}명)</td>
                                            		<td>${book.review_num}</td>	
                                        		</tr>
                                        	</c:forEach>
                                    	</tbody>
                                	</table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Revel 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/revel/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/revel/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/revel/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/revel/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/revel/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/revel/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/revel/resources/js/demo/datatables-demo.js"></script>

</body>

</html>