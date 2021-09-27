<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Revel | Mypage | Edit Profile</title>

    <!-- Custom fonts for this template-->
    <link href="/revel/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/revel/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body>
	<center>
	<br />
		<h5 class="m-0 font-weight-bold text-primary" align="center">닉네임 중복 확인</h5><br />
		<c:if test ="${count ==0 }">
			<p>입력한 닉네임:[ ${nick } ] 사용 가능한 닉네임 입니다.</p>
			<c:if test="${sessionScope.index_member != null}">
				<button type="button" class="btn btn-info btn-icon-split btn-sm" onclick="window.location='/revel/mypage/nickChangePro.rv?nick=${nick}';">
					<span class="icon text-white-50">
                      <i class="fas fa-exchange-alt"></i>
                     </span>
                     <span class="text">사용하기</span>
                </button>
			
			</c:if>
		</c:if>
		
		<c:if test ="${count ==1 }">
			<p align="center">입력한 닉네임: [ ${nick } ]사용할 수 없는 닉네임입니다.</p>
			<c:if test="${sessionScope.index_member != null}">
				<button type="button" class="btn btn-info btn-icon-split btn-sm" onclick="window.location='/revel/mypage/nickChange.rv';" >
					<span class="icon text-white-50">
                      <i class="fas fa-exchange-alt"></i>
                     </span>
                     <span class="text">다시검색</span>
                </button>
			</c:if>
		</c:if>	
		
		<button type= "button" class="btn btn-secondary btn-icon-split btn-sm"  onclick="self.close();" >
	      	<span class="text">닫기</span>
	    </button>
	   </center>
</body>
</html>
