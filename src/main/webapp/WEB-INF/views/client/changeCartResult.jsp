<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<body>
	<c:if test = "${updateCnt == 1}">
		<script type="text/javascript">
			alert("장바구니 수량을 변경했습니다.");
			window.location.href = "cart.do";
		</script>
	</c:if>
	
	<c:if test = "${updateCnt != 1}">
		<script type="text/javascript">
			alert("장바구니 수량 변경에 실패했습니다.");
			window.location.href = "cart.do";
		</script>
	</c:if>
</body>
</html>