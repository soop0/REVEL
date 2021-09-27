<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
	revel 공지사항 삭제 페이지
	http://localhost:8080/revel/help/notice.delete.rv 
	관리자만 이용할 수 있는 서비스(로그인 필수) : aop 처리 페이지
--%>

<c:if test="${check == 1}">
	<script language="JavaScript">
		alert("삭제가 완료되었습니다.");
	</script>
	<c:if test="${!(empty pageNum)}">
		<meta http-equiv="Refresh" content="0;url=/revel/help/notice.rv?pageNum=${pageNum}">
	</c:if>
	<c:if test="${empty pageNum}">
		<meta http-equiv="Refresh" content="0;url=/revel/manage/board/notice.rv">
	</c:if>
</c:if>

<c:if test="${check != 1}">
	<script language="JavaScript">
		alert("삭제가 되지 않았습니다. 다시 시도해주세요.");
		history.go(-1);
	</script>
</c:if>