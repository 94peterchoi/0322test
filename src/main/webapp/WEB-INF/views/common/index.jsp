<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나만의 노트북 - 공책방</title>
<meta name = "viewport" content = "width=device-width, initial-scale=1">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src = "${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js">
	$(function() {
		$(".nav-link:eq(0)").click(function() {
			var tmpPage = $(this).attr("href");
			
			$("#switchPage jsp:include").fadeOut(1000, function() {
				$("#switch jsp:include").attr("page", tmpPage);
				$(this).fadeIn(1000);
			});
			return false;
		});
		
		$(".nav-link:eq(1)").click(function() {
			var tmpPage = $(this).attr("href");
			
			$("#switchPage jsp:include").fadeOut(1000, function() {
				$("#switch jsp:include").attr("page", tmpPage);
				$(this).fadeIn(1000);
			});
			return false;
		});
		
		$(".nav-link:eq(2)").click(function() {
			var tmpPage = $(this).attr("href");
			
			$("#switchPage jsp:include").fadeOut(1000, function() {
				$("#switch jsp:include").attr("page", tmpPage);
				$(this).fadeIn(1000);
			});
			return false;
		});
	});
</script>
<style>
	iframe {
		border: none;
		margin: 0px;
	}
</style>
</head>
<body>
<header><jsp:include page = "./menu.jsp" /></header>
<div id = "switchPage">
	<jsp:include page = "./main.jsp"/>
</div>
<footer><jsp:include page = "./footer.jsp"/></footer>
</body>
</html>