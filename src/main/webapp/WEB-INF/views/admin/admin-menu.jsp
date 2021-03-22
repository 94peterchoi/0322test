<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메뉴 - 네비게이션 바</title>
<meta name = "viewport" content = "width=device-width, initial-scale=1">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src = "/labMall_mvc/common/script.js"></script>
</head>
<body>
<!-- 네비게이션 바(메뉴), 모달(로그인, 약관, 회원가입, 우편번호 찾기), 푸터 구현 -->

	<nav class="navbar navbar-expand-md bg-danger navbar-dark fixed-top">
	<!-- Brand -->
	<a class="navbar-brand" href="closing.do" target = "switch">기아타이거즈</a>
	
	<!-- Toggler/collapsibe Button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	  <span class="navbar-toggler-icon"></span>
	</button>
	
	<!-- Navbar links -->
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
	    <ul class="navbar-nav">
			<!-- <li class="nav-item">
				<a class="nav-link" href="closing.do">결산</a>
			</li> -->
			<li class="nav-item">
				<a class="nav-link" href="stock.pd">물품등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="buy.do">구매</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="refund.do">환불</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="admin-board.bo">게시판</a>
			</li>
	    </ul>
  	</div>
	
	<div class="navbar-nav ml-auto">
	    <ul class="nav navbar-nav">
			<li>
				<a class="nav-link" href="logout.do">로그아웃</a>
			</li>
	    </ul>
  	</div>
</nav>
<!-- 관리자 메뉴 종료 -->

</body>
</html>