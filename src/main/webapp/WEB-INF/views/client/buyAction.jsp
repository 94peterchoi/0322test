<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<body>
	<c:if test = "${insertCnt == 1}">
		<script type="text/javascript">
			alert("구매 요청하였습니다.\n요청내역은 구매 목록에서 확인 가능합니다");
			window.location.href = "shopping.do";
		</script>
	</c:if>
	
	<c:if test = "${insertCnt != 1}">
		<script type="text/javascript">
			alert("구매 요청에 실패하였습니다.");
			window.location.href = "shopping.do";
		</script>
	</c:if>
</body>
</html>