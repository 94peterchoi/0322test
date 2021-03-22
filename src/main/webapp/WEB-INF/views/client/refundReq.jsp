<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<body>
	<!-- 환불실패 -->
	<c:if test = "${insertCnt == 0}">
		<script type = "text/javascript">
			alert("환불요청 등록이 실패했습니다.");
			window.location.href = "buy.do";
		</script>
	</c:if>

	<!-- 환불성공 -->
	<c:if test = "${insertCnt != 0}">
		<script type = "text/javascript">
			alert("환불요청이 등록되었습니다.");
			window.location.href = "buy.do";
		</script>
	</c:if>
</body>
</html>