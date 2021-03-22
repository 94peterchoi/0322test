<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test = "${insertCnt == 1}">
		consol.log("성공");
		<script type="text/javascript">
			alert("제품정보가 등록되었습니다.");
			location.href = "stock.pd?pageNum=1";
		</script>		
	</c:if>

	<c:if test = "${insertCnt != 1}">
		<script type="text/javascript">
			alert("제품정보 등록에 실패했습니다.");
			history.back();
		</script>		
	</c:if>
</body>
</html>