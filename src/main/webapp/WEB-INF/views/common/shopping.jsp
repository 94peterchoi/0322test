<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name = "viewport" content = "width=device-width, initial-scale=1">
<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
</script>
<title>쇼핑하기</title>
</head>
<body>
<!-- 점보트론 -->
<br>
<br>
<br>
<div class="jumbotron jumbotron-fluid">
	<div style="color: white;" class = "container">
		<h1>기아타이거즈 </h1>
		<p>야구 용품</p> 
	</div>
</div>

<!-- 화면 분할 -->
<div class = "row">
	<div class ="col-sm-3">
		<!-- 제품 리스트 선택 -->
		<div class = "container">
			<ul class = "filter">
				<li class = "big_category">야구 용품
				</li>		
			</ul>
		</div>
	</div>
	
	<!-- 제품 목록 -->
	<div class ="container col-sm-9">
	<h3>제품 목록</h3>
	<hr>
		<!-- 제품 카드 시작 -->
		<form action = "cartAction.do" method = "post" name = "cartform" onsubmit = "return cartCheck();">
			<c:if test = "${cnt > 0}">
				<c:forEach var = "vo" items = "${list}">
				<div style = "display: inline-block; margin: 10px; width: 300px;">
					<div class = "card" align = "center">
						<div class = "card-header"><h5>${vo.title}</h5></div>
						<div class = "card-body">
							<c:choose>
					            <c:when test="${vo.pdImage != '-'}">
					               <a href = ""><img src="/labMall_mvc/pj_images/${vo.pdImage}" width="100%"></a>
					            </c:when>
					            <c:otherwise>
					            	<table>
					            		<tr>
					            			<td align = "center" width = "100%" style = "background-color: #AAAAAA; vertical-align: middle;">
					               				<p>등록된 사진이 없습니다.</p>
					               			</td>
					               		</tr>
					               	</table>
					            </c:otherwise>
		         			</c:choose>
						</div>
						<div class = "card-footer">
							<p>${vo.p_price}원</p><br>
							<br>
							<!-- 아래 버튼 둘은 해결책을 찾을 때까지 제품 정보보기 경로로 연결해둔다. -->
							<a href = "shopProductView.pd?pdNum=${vo.pdNum}&pageNum=1"><input type ="button" class= "btn btn-info" value = "장바구니"></a>&nbsp;
							<a href = "shopProductView.pd?pdNum=${vo.pdNum}&pageNum=1"><input type ="button" class= "btn btn-info" value = "바로구매"></a>
							<br><br>
						</div>
					</div>
				</div>
				</c:forEach>
			</c:if>
		</form>	
		<!-- 제품 카드 종료 -->
		
		<br><br>
		
		<br>
		<br>
		<!-- 더 보기 버튼 -->
		<div align = "center">
			<input type = "button" class = "btn btn-info" value = "더 보기">
		</div>
	</div>
</div>
<br>
<br>
</body>
</html>