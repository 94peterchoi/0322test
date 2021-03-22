<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<style>
	/* 모달 창 세로 위치 조정 -> iframe 상태로 불러오면 모달 윗부분이 메뉴바에 가려지기 때문 */
	@media screen {
		.modal:before {
			display: inline-block;
			vertical-align: middle;
			content: " ";
			height: 10%;
		}
	}

	body {
		font-family: 나눔고딕;
	}
	.jumbotron {
	 	background-image: url('/labMall_mvc/pj_images/title_04.jpg');
	}
</style>
</head>
<header><jsp:include page = "../common/menu.jsp" /></header>
<%@ include file = "../common/board.jsp" %>
<footer><jsp:include page = "../common/footer.jsp"/></footer>
</body>
</html>