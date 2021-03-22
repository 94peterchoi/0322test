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
		<script type = "text/javascript" src = "${path}script.js">
			errorAlert(updateError);
		</script>
	</c:if>
		
	<c:if test = "${updateCnt != 0}">
		<script type = "text/javascript">
			alert("글이 수정되었습니다.");
			window.location = 'boardList.bo?pageNum=${pageNum}';
		</script>
	</c:if>
</body>
</html>