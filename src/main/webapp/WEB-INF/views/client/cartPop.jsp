<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name = "viewport" content = "width=device-width, initial-scale=1">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js">

</script>
<body>
	<form action = "#" method = "post">
		<p>장바구니에 담으시겠습니까?</p>
		<table>
			<tr>
				<td>
					<input type = "number" min = "1" step = "1" value = "1">
				</td>
			</tr>
			<tr>
				<td>
					<input type = "submit" class = "btn btn-info" value = "담기">
					
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>