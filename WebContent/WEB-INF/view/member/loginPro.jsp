<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>
<script type="text/javascript">
	function page_move(url, platform_id, platform, index_member, status){
		var form = document.createElement("form");
	    var input;

	    form.action = url;
	    form.method = "post";

	    input = document.createElement("input");
	    input.setAttribute("type", "hidden");
	    input.setAttribute('name', "platform_id");
	    input.setAttribute("value", platform_id);
	    form.appendChild(input);
	
	    input = document.createElement("input");
	    input.setAttribute("type", "hidden");
	    input.setAttribute('name', "platform");
	    input.setAttribute("value", platform);
	    form.appendChild(input);
	    
	    input = document.createElement("input");
	    input.setAttribute("type", "hidden");
	    input.setAttribute('name', "index_member");
	    input.setAttribute("value", index_member);
	    form.appendChild(input);
	    
	    input = document.createElement("input");
	    input.setAttribute("type", "hidden");
	    input.setAttribute('name', "status");
	    input.setAttribute("value", status);
	    form.appendChild(input);
	    
	    document.body.appendChild(form);
	    form.submit();
	}
</script>

<c:if test="${index_member > 0}">
	<c:if test="${!(empty aop_uri)}">
		<c:redirect url="${aop_uri}" />
	</c:if>
	
	<script type="text/javascript">
		alert("${sessionScope.nick}님 환영합니다.");
		window.location = "/revel/main.rv";
	</script>
</c:if>

<c:if test="${index_member == 0}">
	<script>
		var platform_id = '${platform_id}';
		var platform = '${platform}';
		var index_member = '${index_member}';
		var status = '${status}';
		page_move('/revel/join.rv', platform_id, platform, index_member, status);
	</script>
</c:if>
</body>