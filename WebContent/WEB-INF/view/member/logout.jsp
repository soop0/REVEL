<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 로그아웃 페이지
	http://localhost:8080/revel/logout.rv 
	로그인 상태일 때만 이용할 수 있는 서비스 : aop 처리 페이지
--%>


<c:if test="${sessionScope.member_index == null}">
	<script>
		alert("로그아웃되었습니다.");
		window.location = "/revel/main.rv";
	</script>
</c:if>