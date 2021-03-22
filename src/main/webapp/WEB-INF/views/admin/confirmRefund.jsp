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
	<c:if test = "${updateCnt != 1}">
		<script type = "text/javascript">
			alert("환불 승인에 실패했습니다.\n잠시 후 다시 시도해주세요.");
			location.href = 'refund.do?pageNum=${pageNum}';
		</script>
		<!-- 이후에 response.sendRedirect 문은 무시된다. -->
	</c:if>
	
	<c:if test = "${updateCnt == 1}">
		<script type = "text/javascript">
			alert("환불이 승인되었습니다.");
			location.href = 'refund.do?pageNum=${pageNum}';
		</script>
	</c:if>
</body>
</html>