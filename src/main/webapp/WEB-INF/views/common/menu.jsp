<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name = "viewport" content = "width=device-width, initial-scale=1">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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

<body>

<!-- 네비게이션 바(메뉴), 모달(로그인, 약관, 회원가입, 우편번호 찾기), 푸터 구현 -->

	<nav class="navbar navbar-expand-md bg-danger navbar-dark fixed-top" >
	<!-- Brand -->
	<a class="navbar-brand" href="./main.do">기아타이거즈</a>
	
	<!-- Toggler/collapsibe Button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	  <span class="navbar-toggler-icon"></span>
	</button>
	
	<!-- Navbar links -->
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
	    <ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" id = "shopping" href="shopping.do">야구용품</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id = "board" href="boardList.bo">게시판</a>
			</li>
	    </ul>
  	</div>
	
	<div class="navbar-nav ml-auto">
	    <ul class="nav navbar-nav">
			<li class = "dropdown">	<!-- 아래쪽으로 리스트가 추가되어 나옴 -->
				<a href = "#" class = "nav-link dropdown-toggle" data-toggle = "dropdown" id = "navbardrop">접속하기<span class = "caret"></span></a>
				<!-- caret : 아래 화살표 클릭시 아이콘이 나오도록 함 -->
				<div class = "dropdown-menu">
					<input type="button" class="btn dropdown-item" data-toggle="modal" data-target="#login-section" value = "로그인">
					<input type="button" class="btn dropdown-item" data-toggle="modal" data-target="#regist" value = "회원가입">
				</div>
			</li>
	    </ul>
  	</div>
</nav>

<c:if test = "${selectCnt == -1}">
	<script type = "text/javascript">
		alert("* 비밀번호를 확인해주세요!! *");	
	</script>
</c:if>
<c:if test = "${selectCnt == 0}">
	<script type = "text/javascript">
		alert("* 존재하지 않는 ID입니다!! *");
	</script>
</c:if>
<c:if test = "${insertCnt == 1}">
	<script type = "text/javascript">
		alert("* 회원가입에 성공했습니다. 로그인하세요!! *");
	</script>
</c:if>

<%@ include file="modal.jsp" %>
</body>
</html>