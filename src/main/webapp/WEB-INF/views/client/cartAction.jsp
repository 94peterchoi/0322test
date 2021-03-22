<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<body>
	<c:if test = "${insertCnt == 1}">
		<script type="text/javascript">
			alert("장바구니에 등록되었습니다.");
			window.location.href = "shopping.do";
		</script>
	</c:if>
	
	<c:if test = "${insertCnt != 1}">
		<script type="text/javascript">
			alert("장바구니에 등록에 실패하였습니다.");
			window.location.href = "shopping.do";
		</script>
	</c:if>
</body>
</html>