<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name = "viewport" content = "width=device-width, initial-scale=1">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>로그인 이후 네비게이션 바</title>
</head>
<body>
<!-- 네비게이션 바(메뉴), 모달(로그인, 약관, 회원가입, 우편번호 찾기), 푸터 구현 -->

	<nav class="navbar navbar-expand-md bg-danger navbar-dark fixed-top">
	<!-- Brand -->
	<a class="navbar-brand" href="login.do">기아타이거즈</a>
	
	<!-- Toggler/collapsibe Button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	  <span class="navbar-toggler-icon"></span>
	</button>
	
	<!-- Navbar links -->
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
	    <ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="shopping.do">야구용품</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="login-board.bo">게시판</a>
			</li>
	    </ul>
  	</div>
	
	<div class="navbar-nav ml-auto">
	    <ul class="nav navbar-nav">
			<li class = "dropdown">	<!-- 아래쪽으로 리스트가 추가되어 나옴 -->
				<a href = "#" class = "nav-link dropdown-toggle" data-toggle = "dropdown" id = "navbardrop">${sessionScope.clientId} 메뉴<span class = "caret"></span></a>
				<div class = "dropdown-menu">
					<input type="button" class="btn dropdown-item" onclick = "window.location='cart.do'" value = "장바구니">
					<input type="button" class="btn dropdown-item" onclick = "window.location='buy.do'" value = "구매목록">
					<input type="button" class="btn dropdown-item" onclick = "window.location='refund.do'" value = "환불목록">
					<input type="button" class="btn dropdown-item" data-toggle="modal" data-target="#modify-section" value = "내정보">
					<input type="button" class="btn dropdown-item" data-toggle="modal" data-target="#withdrawl-section" value = "회원탈퇴">
					<input type="button" class="btn dropdown-item" onclick = "window.location='logout.do'" value = "로그아웃">
				</div>
			</li>
	    </ul>
  	</div>
</nav>

<%@ include file="../common/modal.jsp" %>
</body>
</html>