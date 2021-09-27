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
	<script type="text/javascript">
		function nickCheck()
		{
			var nick = document.nickChange.nick.value;
			if(!nick)
			{
				alert("닉네임을 입력해주세요");
				document.forms[0].nick.focus();
				return false;
			}
		}
	
	</script>
<br />
	<form action="/revel/mypage/nickCheck.rv" name="nickChange" onsubmit="return nickCheck();">
		<h5 class="m-0 font-weight-bold text-primary" align="center">닉네임 변경 페이지</h5><br />
		<p align="center">닉네임  <input type="text" name=nick />
		<button type="submit" class="btn btn-info btn-icon-split btn-sm">
      	  <span class="text">중복확인</span>
        </button>
        </p>
	</form>
	<center>
		<button type= "button" class="btn btn-secondary btn-icon-split btn-sm"  onclick="self.close();" >
	      	<span class="text">닫기</span>
	    </button>
	</center>
</body>
</html>