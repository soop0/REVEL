<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.index_member == null && check == 1}">
	<script>
		alert("회원탈퇴에 성공하셨습니다");
		window.location = "/revel/main.rv";
	</script>
</c:if>
<c:if test="${check == 0}">
	<script>
		alert("회원탈퇴에 실패하셨습니다. 생년월일을 다시 확인해주세요.");
		history.go(-1);
	</script>
</c:if>
