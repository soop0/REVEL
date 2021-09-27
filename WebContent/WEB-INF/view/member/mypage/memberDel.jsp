<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 회원탈퇴 페이지
	http://localhost:8080/revel/mypage/memberDel.rv 
	회원일 때만 이용할 수 있는 서비스 : aop 처리 페이지
--%>

<!-- session 검사 : 회원만 이용가능 -->
<script type="text/javascript">
	var status = '<c:out value="${sessionScope.status}"/>';
	if(status == "" || status == null || status == 50)
	{
		alert("회원만 이용가능한 서비스입니다.");
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
    
    <title>Revel | Mypage | Withdrawal</title>

    <!-- Custom fonts for this template-->
    <link href="/revel/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/revel/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <!-- 왼쪽 카테고리 항목 작성 -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <!-- main 로고 등록 -->
            <a class="sidebar-brand d-flex align-items-center justify-content-right" href="/revel/main.rv">
       			<img src="/revel/img/revel2.png">
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <!-- 마이페이지로 돌아가는 부분 -->
            <li class="nav-item active">
                <a class="nav-link" href="/revel/mypage.rv">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>My Revel</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <!-- 회원관련 서비스 제목 -->
            <div class="sidebar-heading">
                Membership
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <!-- 회원관련 상세 서비스 항목(회원수정, 회원 탈퇴) -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>My Profile</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">My Profile:</h6>
                        <a class="collapse-item" href="/revel/mypage/memberUpdate.rv">Edit Profile</a>
                        <a class="collapse-item" href="/revel/mypage/memberDel.rv">Withdrawal</a>
                    </div>
                </div>
            </li>
      
            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <!-- 회원 웹소설 통계 관련 서비스 제목 -->
            <div class="sidebar-heading">
                My Revel
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <!-- 나의 댓글 리스트(수정, 삭제) -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/revel/mypage/review.rv">
                    <i class="fas fa-comments fa-2x"></i>
                    <span>My Review</span>
                </a>
              
            </li>

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                	aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Analysis</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" 
                	data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Analysis:</h6>
                        <a class="collapse-item" href="/revel/mypage/myGenreAnls.rv">선호장르</a>
                        <a class="collapse-item" href="/revel/mypage/myKeywordAnls.rv">선호키워드</a>
                        <a class="collapse-item" href="/revel/mypage/myRatingAnls.rv">별점 그래프</a>
                    </div>
                </div>
                
            </li>

            <!-- Nav Item - Tables -->
            <!-- 나의 리스트(평점 항목, 읽을거다, 읽고있다, 다읽었다 항목) -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                   aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-table"></i>
                    <span>My List</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">My List:</h6>
                        <a class="collapse-item" href="/revel/mypage/myRating.rv">나의 평점</a>
                        <a class="collapse-item" href="/revel/mypage/myReadwant.rv">읽을거다</a>
                        <a class="collapse-item" href="/revel/mypage/myReading.rv">읽고있다</a>
                        <a class="collapse-item" href="/revel/mypage/myRead.rv">다읽었다</a>
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
                        <c:if test="${sessionScope.status == 11 || sessionScope.status == 12}">
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
                <!-- 탈퇴 내용-->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <!-- 회원탈퇴 제목 -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Withdrawal</h1>
                    </div>
                
                	<!-- 회원탈퇴 코드 -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">회원탈퇴</h6>
                                </div>
                                <div class="card-body">
                                	<p align="center">생년월일을 입력해주세요.</p>
                                   <form action="memberDelPro.rv" name="delform" method="post" align="center" onsubmit="return check();">
										<input type="number" name="birthday" placeholder="ex : 20210701 "/>
			 		   	 	     		<input type="hidden" name="status" value="50" />
			 					 	   	<input type="hidden" name="index_member" value="${sessionScope.index_member}" />
			 							</br></br></br>	
			 						<p align="center">탈퇴사유를 선택해주세요.</p>
			 							<table border="1" width="500" cellpadding="0" cellspacing="0" align="center" style="border-radius:16px;">
			 								<tr>
			 									<td width="100" align="center">탈퇴사유</td>			
												<td width="400"align="center">
													<input type="radio" name="withdrawal" value="1"  onClick="this.form.withdrawal_etc.style='display:none'" />웹소설에 흥미가 떨어져서</br>		
													<input type="radio" name="withdrawal" value="2"  onClick="this.form.withdrawal_etc.style='display:none'" />읽고있는 콘텐츠가 없어서</br>
													<input type="radio" name="withdrawal" value="3"  onClick="this.form.withdrawal_etc.style='display:none'" />다른 서비스를 이용하려고</br>
													<input type="radio" name="withdrawal" value="4"  onClick="this.form.withdrawal_etc.style='display:none'"  />서비스 이용이 불편해서</br>
													<input type="radio" name="withdrawal" value="5"  onClick="this.form.withdrawal_etc.style='display:inline'" />기타 다른 이유
													<textarea rows="5" cols="50" name="withdrawal_etc" style="display:none;" ></textarea>
												</td>
											</tr>
										</table>
										</br>
										<button type="submit" align="center" class="btn btn-danger btn-icon-split">
												<span class="icon text-white-50">
                                            		<i class="fas fa-trash"></i>
                                        		</span>
                                        		<span class="text">탈퇴하기</span>
                                        	</button>
									</form>
                                </div>
                            </div>
   
	<script>
		function spread()
		{	document.withdrawal_etc.style.display="inline";	}
		
		//라디오버튼체크확인
		function check()
		{			
			var withdrawal = document.getElementsByName('withdrawal');
			var chk_radio = null;

			for(var i=0;i< withdrawal.length;i++)
			{ 
				if(withdrawal[i].checked == true) { chk_radio =  withdrawal[i].value;}
			}
			if(chk_radio == null)
			{
	            alert("탈퇴사유를 선택해 주세요"); 
				return false;
			}
		}
	</script>
	
    
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
    <script src="/revel/resources/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/revel/resources/js/demo/chart-area-demo.js"></script>
    <script src="/revel/resources/js/demo/chart-pie-demo.js"></script>

</body>

</html>

    