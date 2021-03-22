<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>중복확인</title>
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src = "/labMall_mvc/common/script.js"></script>
</head>
<body>
	<form action = "confirmId.do" method = "post" name = "confirmform" onsubmit = "confirmIdCheck();">
	<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
	<c:if test = "${selectCnt == 1}">
		<table>
			<tr>
				<th colspan = "2">
					<span>${id}</span>는 사용할 수 없습니다.
				</th>
			</tr>
			
			<tr>
				<th>아이디 : </th>
				<td>
					<input class = "input" type = "text" name = "id" maxlength ="20"
						style = "width=150px" autofocus required>
				</td>
			</tr>
			
			<tr>
				<th colspan = "2">
					<input class = "inputButton" type = "submit" value = "확인">
					<input class = "inputButton" type = "reset" value = "취소" onclick = "self.close();">
				</th>
			</tr>
		</table>
	</c:if>
	<c:if test = "${selectCnt != 1}">
		<table class = "table">
			<tr>
				<td align = "center">
					<span>${id}</span>은(는) 사용할 수 있습니다.
				</td>
			</tr>
			
			<tr>
				<th>
					<div align = "center">
						<input class = "btn btn-info" type = "button" value = "확인" onclick = "setId('${id}');">
					</div>
				</th>
			</tr>
		</table>
	</c:if>
	</form>
</body>
</html>