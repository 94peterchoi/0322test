<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<!-- 비밀번호 인증 실패 -->
<c:if test = "${selectCnt == 0}">
	<script type = "text/javascript">
		errorAlert(pwdError);
	</script>
</c:if>

<!-- 비밀번호 인증 성공 -->
<c:if test = "${selectCnt != 0}">
	<div class = "container" align = "center">
	<h3 align = "left" class = "col-sm-6">글 수정하기</h3>
	<hr class = "col-sm-6">
		<form action = "modifyAction.bo" method = "post">
			<input type = "hidden" name = "num" value = "${num}">
			<input type = "hidden" name = "pageNum" value = "${pageNum}">
			<table class = "table table-hover col-sm-6">
				<tr>
					<th style = "background-color: #3c3c3c; color: white; width:100px">작성자</th>
					<td><input type = "text" name = "writer" value = "${dto.writer}" style = "width:100%"></td>
				</tr>
				<tr>
					<th style = "background-color: #3c3c3c; color: white; width:100px">제목</th>
					<td><input type = "text" name = "subject" value = "${dto.subject}" style = "width:100%"></td>
				</tr>
				<tr>
					<th style = "background-color: #3c3c3c; color: white; width:100px; height:300px">내용</th>
					<td>
						<textarea rows = "10" cols = "50" name = "content" style = "width:100%; word-break:break-all" name = "content">${dto.content}</textarea>
					</td>
				</tr>
			</table>
			<hr class = "col-sm-6">
			<br>
			<p align = "left" class = "col-sm-6">수정하려면 확인 버튼을 누르세요.</p>
			<br>
				<input class = "btn btn-info" type = "submit" value = "확인">
				<input class = "btn btn-info" type = "reset" value = "취소"
					onclick = "window.history.back();">
		</form>	
	</div>
</c:if>
</body>
</html>