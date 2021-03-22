<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:if test = "${selectCnt == 0}">
	<script type = "text/javascript">
		alert("비밀번호가 일치하지 않습니다. \n확인후 다시 시도하세요");
		history.back();
	</script>
</c:if>

<c:if test = "${selectCnt == 1}">
	<c:if test = "${delectCnt == 0}">
		<script type = "text/javascript">
			alert("게시글 삭제에 실패했습니다.");
			history.back();
		</script>
	</c:if>
	
	<c:if test = "${deleteCnt == 1}">
		<script type = "text/javascript">
			alert("게시글이 삭제됐습니다.");
			location.href = "boardList.bo";
		</script>
	</c:if>
</c:if>
</body>
</html>