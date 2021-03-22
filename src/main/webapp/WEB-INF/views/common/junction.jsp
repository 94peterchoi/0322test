<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test = "${gradeCnt == 0}">	<!-- 사용자 확인됐으나 ROLE_USER 등급이 아닌 경우(관리자) -->
		<script type="text/javascript">
			alert("${msg}");
			window.location = "main.do";
		</script>
	</c:if>
	
	<c:if test = "${gradeCnt == 1}">	<!-- 접속자가 회원 등급인 경우 -->
		<script type="text/javascript">
			alert("${msg}");
			window.location = "main.cl";
		</script>
	</c:if>

	<c:if test = "${gradeCnt == 2}">	<!-- 접속자가 관리자 등급인 경우 -->
		<script type="text/javascript">
			alert("${msg}");
			window.location = "main.ad";
		</script>
	</c:if>

</body>
</html>