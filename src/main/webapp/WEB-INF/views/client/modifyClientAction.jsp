<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test = "${updateCnt == 0}">
		<script type="text/javascript">
			errorAlert(updateError);
		</script>
	</c:if>
	<c:if test = "${updateCnt != 0}">
		<script type = "text/javascript">
			setTimeout(function() {
				alert("회원정보가 수정되었습니다.");
				window.location = "login-news.cl";
			}, 1000);
		</script>
	</c:if>
</body>
</html>