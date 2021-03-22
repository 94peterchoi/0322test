<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name = "viewport" content = "width=device-width, initial-scale=1">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type = "text/javascript">
	function back() {
		history.back();
	}
	
	function pwdCheck() {
		if(!document.pwdform.pwd.value) {
			alert("비밀번호를 입력하세요!!");
			document.pwdform.pwd.focus();
			return false;	// 멈추기
		}
	}
</script>
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<%@ include file = "../admin/admin-menu.jsp" %>
<%@ include file = "subContent.jsp" %>
<%@ include file = "../common/modal.jsp" %>
<br>
<br>
<br>
<%@ include file = "../common/footer.jsp" %>
</body>
</html>