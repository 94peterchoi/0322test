<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공책방 - 회원가입 승인</title>
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src = "/labMall_mvc/common/script.js"></script>
</head>
<body>
	<!-- 가입실패 -->
	<c:if test = "${insertCnt == 0}">
		<script type = "text/javascript">
			errorAlert(insertError);
			history.back();
		</script>
	</c:if>

	<!-- 가입 성공 -->
	<c:if test = "${insertCnt != 0}">
		<script type = "text/javascript">
		alert("가입되었습니다.\n로그인 전에 이메일 인증하세요");
		</script>
		<c:redirect url = "mainSuccess.do?insertCnt=${insertCnt}" />
	</c:if>
</body>
</html>