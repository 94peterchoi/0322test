<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<body>
	<c:if test = "${deleteCnt == 1}">
		<script type="text/javascript">
			alert("선택하신 제품을 장바구니에서 제외했습니다.");
			window.location.href = "cart.do?pageNum=${pageNum}";
		</script>
	</c:if>
	
	<c:if test = "${deleteCnt != 1}">
		<script type="text/javascript">
			alert("장바구니 빼기에 실패하였습니다.");
			window.location.href = "cart.do?pageNum=${pageNum}";
		</script>
	</c:if>
</body>
</html>