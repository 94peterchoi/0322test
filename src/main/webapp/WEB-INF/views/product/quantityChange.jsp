<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test = "${updateCnt == 1}">
		<script type = "text/javascript">
			alert("입고가 완료됐습니다.");
			location.href = 'stock.pd?pageNum=${pageNum}';
		</script>
	</c:if>
	<c:if test = "${updateCnt != 1}">
		<script type = "text/javascript">
			alert("입고 처리가 실패했습니다.");
			location.href = 'stock.pd?pageNum=${pageNum}';
		</script>
	</c:if>
</body>
</html>