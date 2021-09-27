<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 나의 선호장르 분석 페이지
	http://localhost:8080/revel/mypage/myRating.rv 
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<!-- 워드클라우드 사용  -->
<script src="/revel/js/htmlwidgets/htmlwidgets.js"></script>
<script src="/revel/js/htmlwidgets/wordcloud2.js"></script>
<link href="/revel/js/htmlwidgets/lib/wordcloud2-0.0.1/wordcloud.css" rel="stylesheet" />
<script src="/revel/js/htmlwidgets/lib/wordcloud2-0.0.1/wordcloud2-all.js"></script>
<script src="/revel/js/htmlwidgets/lib/wordcloud2-0.0.1/hover.js"></script>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Revel | Mypage | My Review</title>

    <!-- Custom fonts for this template-->
    <link href="/revel/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
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

            <!-- Nav Item - Charts -->
            <!-- 나의 통계 분석 서비스(선호장르, 선호키워드, 별점그래프) -->
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
			
			<!-- 나의 댓글 리스트 부분 -->
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
                
                
                

                <!--  -->
                <!-- Begin Page Content -->
                
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <!-- 해당 페이지 이름 -->
                    <h1 class="h3 mb-2 text-gray-800">My Keyword Analysis</h1>

                    <!-- DataTales Example -->
                    <c:if test="${count==0}">
                   		<div class="card">
	                    	<div class="card-body">
	                   	  		<p align="center">분석할 평가 작품이 없습니다</p>
	                   	 	</div>
                   	  	</div>
                    </c:if>
                    
	               	 
					 <!-- 나의 선호 키워드 분석 워드클라우드 차트 -->  
					 <!-- 평가항목이 0이 아닐 때만 보이게 -->
					 <c:if test="${count!=0}">                  
	                 	<div class="card">
							<div class="card-header">
	                            <h6 class="m-0 font-weight-bold text-primary">나의 선호 키워드 top20 워드클라우드</h6>
							</div>
						
		                   <div class="card-body">
								<!--차트가 그려질 부분-->
			                    <div class="chart"  align="center">
									${top20_chart }							
								</div>
						   </div>
					  </div> 
					  <!--/c:if-->
	                    
                    <!-- 나의 선호 키워드 분석 차트 -->                    
                   	<div class="card">
						<div class="card-header">
                            <h6 class="m-0 font-weight-bold text-primary">나의 선호 키워드 top10 차트</h6>
						</div>
					
	                   <div class="card-body">
							<!--차트가 그려질 부분-->
		                    <div class="chart"  align="center">
								<canvas id="myKeyword_top"></canvas>							
							</div>
					   </div>
				  </div> 
				   <br />
				    
					<!-- 나의 선호키워드_캐릭터 분석 차트 -->
				   <div class="card">
						<div class="card-header">
                            <h6 class="m-0 font-weight-bold text-primary">나의 선호 키워드-캐릭터 차트</h6>
						</div>
                  	 <div class="card-body">
						<!--차트가 그려질 부분-->
	                    <div class="chart"  align="center">
							<canvas id="myKeyword_1"></canvas>
						</div>
				     </div>
				   </div>
					 <br />
				   
					<!-- 나의 선호키워드_분위기 분석 차트 -->
				   <div class="card">
						<div class="card-header">
                            <h6 class="m-0 font-weight-bold text-primary">나의 선호 키워드-분위기 차트</h6>
						</div>
                  	 <div class="card-body">
						<!--차트가 그려질 부분-->
	                    <div class="chart"  align="center">
							<canvas id="myKeyword_2"></canvas>
						</div>
				     </div>
				   </div>
				   <br />
				   <!-- 나의 선호키워드_소재 분석 차트 -->
				   <div class="card">
						<div class="card-header">
                            <h6 class="m-0 font-weight-bold text-primary">나의 선호 키워드-소재 차트</h6>
						</div>
                  	 <div class="card-body">
						<!--차트가 그려질 부분-->
	                    <div class="chart"  align="center">
							<canvas id="myKeyword_3"></canvas>
						</div>
				     </div>
				   </div>
				 	<br />
				 
				   <!-- 나의 선호키워드_독자반응 분석 차트 -->
				   <div class="card">
						<div class="card-header">
                            <h6 class="m-0 font-weight-bold text-primary">나의 선호 키워드-독자반응 차트</h6>
						</div>
                  	 <div class="card-body">
						<!--차트가 그려질 부분-->
	                    <div class="chart"  align="center">
							<canvas id="myKeyword_4"></canvas>
						</div>
				     </div>
				   </div>
				</c:if>
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
    

	<script type="text/javascript">
		var context = document.getElementById('myKeyword_top').getContext('2d');
		var myKeyword_top = new Chart(context, {  
		    type: 'horizontalBar',
		    data: {
		     // labels: '${keywords}',
		     
		     labels:[
		    	 '${keywords[0]}','${keywords[1]}','${keywords[2]}','${keywords[3]}','${keywords[4]}',
		    	 '${keywords[5]}','${keywords[6]}','${keywords[7]}','${keywords[8]}','${keywords[9]}'],
		      datasets: [
		        {
		          label: '선호키워드',
		         // data: '${cumul_score}',
		         data :[
		        	
		        	 '${cumul_score[0]}','${cumul_score[1]}','${cumul_score[2]}','${cumul_score[3]}','${cumul_score[4]}',
		        	 '${cumul_score[5]}','${cumul_score[6]}','${cumul_score[7]}','${cumul_score[8]}','${cumul_score[9]}'],
		         backgroundColor: [
		        	 'rgba(12, 73, 120, 0.64)',
		        	 'rgba(12, 73, 120, 0.64)',
		        	 'rgba(12, 73, 120, 0.64)',
					 'rgba(12, 73, 120, 0.64)',
		        	 'rgba(12, 73, 120, 0.64)',
		         ]
		        }
		      ]
		    },
		    options: {
		      title: {
		        display: true,
		        text: '회원 선호 키워드 top 10'
		      }
		    }
		});
		
		//선호키워드_캐릭터
		var context = document.getElementById('myKeyword_1').getContext('2d');
		var myKeyword_1 = new Chart(context, {  
		    type: 'horizontalBar',
		    data: {
		     labels:['${anls_1[0]}','${anls_1[1]}','${anls_1[2]}','${anls_1[3]}','${anls_1[4]}','${anls_1[5]}'],
		      datasets: [
		        {
		          label: '선호 키워드-캐릭터',
		         data :['${anls_c1[0]}','${anls_c1[1]}','${anls_c1[2]}','${anls_c1[3]}','${anls_c1[4]}','${anls_c1[5]}'],
		         backgroundColor: [
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
	                 'rgba(255, 99, 132, 0.2)',
		         ]
		        }
		      ]
		    },
		    options: {
		      title: {
		        display: true,
		        text: '회원 선호 키워드 - 캐릭터'
		      }
		    }
		});
		//선호키워드_분위기
		var context = document.getElementById('myKeyword_2').getContext('2d');
		var myKeyword_2 = new Chart(context, {  
		    type: 'horizontalBar',
		    data: {
		     labels:['${anls_2[0]}','${anls_2[1]}','${anls_2[2]}','${anls_2[3]}','${anls_2[4]}','${anls_2[5]}'],
		      datasets: [
		        {
		          label: '선호 키워드-분위기',
		         data :['${anls_c2[0]}','${anls_c2[1]}','${anls_c2[2]}','${anls_c2[3]}','${anls_c2[4]}','${anls_c2[5]}'],
		         backgroundColor: [
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
	                 'rgba(54, 162, 235, 0.2)',
		         ]
		        }
		      ]
		    },
		    options: {
		      title: {
		        display: true,
		        text: '회원 선호 키워드 - 분위기'
		      }
		    }
		});
		
		//선호키워드_소재
		var context = document.getElementById('myKeyword_3').getContext('2d');
		var myKeyword_3 = new Chart(context, {  
		    type: 'horizontalBar',
		    data: {
		     labels:['${anls_3[0]}','${anls_3[1]}','${anls_3[2]}','${anls_3[3]}','${anls_3[4]}','${anls_3[5]}'],
		      datasets: [
		        {
		          label: '선호 키워드-소재',
		         data :['${anls_c3[0]}','${anls_c3[1]}','${anls_c3[2]}','${anls_c3[3]}','${anls_c3[4]}','${anls_c3[5]}'],
		         backgroundColor: [
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
	                 'rgba(255, 206, 86, 0.2)',
		         ]
		        }
		      ]
		    },
		    options: {
		      title: {
		        display: true,
		        text: '회원 선호 키워드 - 소재'
		      }
		    }
		});
		
		//선호키워드_독자반응
		var context = document.getElementById('myKeyword_4').getContext('2d');
		var myKeyword_4 = new Chart(context, {  
		    type: 'horizontalBar',
		    data: {
		     labels:['${anls_4[0]}','${anls_4[1]}','${anls_4[2]}'],
		      datasets: [
		        {
		          label: '선호 키워드-독자반응',
		         data :['${anls_c4[0]}','${anls_c4[1]}','${anls_c4[2]}'],
		         backgroundColor: [
                     'rgba(75, 192, 192, 0.2)',
                     'rgba(75, 192, 192, 0.2)',
                     'rgba(75, 192, 192, 0.2)',
		         ]
		        }
		      ]
		    },
		    options: {
		      title: {
		        display: true,
		        text: '회원 선호 키워드 - 독자반응'
		      }
		    }
		});
		
	</script>


</body>

</html>
