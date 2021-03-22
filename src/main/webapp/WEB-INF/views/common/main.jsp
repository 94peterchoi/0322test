<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인</title>
</head>
<body>
<%@ include file = "menu.jsp" %>
	<c:if test = "${errMsg != null}">
		<script type="text/javascript">
			alert("${errMsg}");
			window.location = "main.do";
		</script>
	</c:if>
<%@ include file = "news.jsp" %>
<%@ include file = "footer.jsp" %>
</body>
</html>