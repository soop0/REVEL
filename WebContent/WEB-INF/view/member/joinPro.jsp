<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${nickCount != 0}" > 
	<script>
		alert("사용할 수 없는 닉네임 입니다.");
		window.history.go(-1);
	</script>
</c:if>

<c:if test="${nickCount==0}" > 
	<c:if test="${index_member > 0}" > 
		<script>
			alert("로그인이 완료되었습니다.");
			window.location = "/revel/main.rv";
		</script>
	</c:if>
</c:if>
