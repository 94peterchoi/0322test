<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
	<!-- 구매확정 성공 -->
	<c:if test = "${updateCnt == 1}">
		<c:if test = "${reflectCnt == 1}">
			<script type = "text/javascript">
				alert("구매확정 처리했습니다.");
				location.href = "buy.do?pageNum=${pageNum}";
			</script>
		</c:if>
		<c:if test = "${reflectCnt != 1}">
			<script type = "text/javascript">
				alert("구매확정 처리했으나, 재고에 반영되지 않았습니다.");
				location.href = "buy.do?pageNum=${pageNum}";
			</script>
		</c:if>
	</c:if>

	<!-- 구매확정 실패 -->
	<c:if test = "${updateCnt != 1}">
		<c:if test = "${reflectCnt == 1}">
			<script type = "text/javascript">
				alert("구매확정 처리에 실패했지만 재고에 반영됐습니다.");
				location.href = "buy.do?pageNum=${pageNum}";
			</script>
		</c:if>
		<c:if test = "${reflectCnt != 1}">
			<script type = "text/javascript">
				alert("재고가 부족합니다.");
				location.href = "buy.do?pageNum=${pageNum}";
			</script>
		</c:if>
	</c:if>
</body>
</html>