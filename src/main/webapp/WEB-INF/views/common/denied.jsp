<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:if test = "${errMsg != null}">
		<script type="text/javascript">
			alert("${errMsg}");
			window.location = "logout.do";
		</script>
	</c:if>
</body>
</html>