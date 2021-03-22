<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 탈퇴 처리 결과</title>
</head>
<body>
	<!-- 회원정보 확인 -->
	<c:if test = "${selectCnt == 1}">
		<!-- 회원탈퇴 실패 -->
		<c:if test = "${deleteCnt == 0}">
			<script type="text/javascript">
				errorAlert(deleteError);
				window.location="main.cl";
			</script>
		</c:if>
		<!-- 회원탈퇴 성공 -->
		<c:if test = "${deleteCnt == 1}">
			<% request.getSession().invalidate(); %>
			<script type="text/javascript">
				setTimeout(function() {
					alert("탈퇴 처리되었습니다.");
					window.location="logout.do";
				}, 1000);
			</script>
		</c:if>
	</c:if>
	<!-- 비밀번호 불일치 -->
	<c:if test = "${selectCnt == -1}">
		<script type="text/javascript">
			errorAlert(passwordError);
			window.location="main.cl";
		</script>
	</c:if>
	<!-- 회원정보 없음 -->
	<c:if test = "${selectCnt == 0}">
		<script type="text/javascript">
			errorAlert(nullIdError);
			window.location="main.cl";
		</script>
	</c:if>
</body>
</html>