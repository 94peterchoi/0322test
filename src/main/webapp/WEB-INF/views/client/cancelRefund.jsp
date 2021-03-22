<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<body>
	<c:if test = "${deleteCnt == 1}">
		<c:if test = "${updateCnt == 1}">
			<script type="text/javascript">
				alert("환불을 취소했습니다.");
				window.location.href = "refund.do";
			</script>
		</c:if>
		<c:if test = "${updateCnt == 0}">
			<script type="text/javascript">
				alert("환불을 취소했으나 구매수량에 반영하지 못했습니다.");
				window.location.href = "refund.do";
			</script>
		</c:if>
	</c:if>
	
	<c:if test = "${deleteCnt != 1}">
		<c:if test = "${updateCnt == 1}">
			<script type="text/javascript">
				alert("환불취소에 실패했음에도 구매수량에 환불수량이 더해졌습니다.\n관리자에게 문의하십시오.");
				window.location.href = "refund.do";
			</script>
		</c:if>
		<c:if test = "${updateCnt == 0}">
			<script type="text/javascript">
				alert("환불 취소에 실패했습니다.\n문제가 지속되면 관리자에게 문의하십시오.");
				window.location.href = "refund.do";
			</script>
		</c:if>
	</c:if>
</body>
</html>