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
<script type="text/javascript"></script>
<title>장바구니</title>
<style>
	.jumbotron {
	 	background-image: url('/labMall_mvc/pj_images/title_04.jpg');
	}
</style>
</head>
<body>
<%@ include file = "login-menu.jsp" %>

<!-- 점보트론 -->
<br>
<br>
<br>
<div class="jumbotron jumbotron-fluid">
	<div style="color: white;" class = "container">
		<h1>장바구니</h1>
		<p>즐겨 찾는 제품을 여기에 담아 간편구매 할 수 있습니다.
		<br>장바구니 수량을 변경하면 구매예상금액도 확인할 수 있어요.</p> 
	</div>
</div>


<!-- 장바구니 목록 -->
<div class = "container" align = "center">
	<table class = "table table-hover" style = "text-align: center;">
		<tr class="table-info">
			<th>품명</th>
			<th>단가(원)</th>
			<th>수량</th>
			<th>총 가격</th>
			<th colspan = "2">수량변경</th>
			<th colspan = "3">구매요청</th>
			<th>제거</th>
		</tr>
		<c:if test = "${cnt > 0}">
			<c:forEach var = "vo" items = "${list}">
				<tr>
					<td>${vo.title}</td>
					<td>${vo.p_price}</td>
					<td>${vo.p_count}</td>
					<td>${vo.totPrice}</td>
					<form action = "changeCart.do" method = "post">
						<input type = "hidden" name = "guestId" value = "${sessionScope.clientId}">
						<input type = "hidden" name = "cartNum" value = "${vo.cartNum}">
						<td width = "100"><input type = "number" name = "p_count" class = "form-control" value = "${vo.p_count}" min = "1" step = "1"></td>
						<td>
							<input type = "submit" class = "btn btn-info" value = "변경">
							<input type = "reset" class = "btn btn-info" value = "초기화">
						</td>
					</form>							
					<form action = "buyAction.do" method = "post">
						<td></td>
						<input type = "hidden" name = "guestId" value = "${sessionScope.clientId}">
						<input type = "hidden" name = "pdNum" value = "${vo.pdNum}">
						<td width = "80"><input type = "number" name = "buycount" class = "form-control" value = "${vo.p_count}" min = "1" step = "1"></td> 
						<td>
							<input type = "submit" class = "btn btn-info" value = "구매">
							<input type = "reset" class = "btn btn-info" value = "초기화">
						</td>
					</form> 
					<form action = "deleteCart.do" method = "post">
						<td>
							<input type = "hidden" name = "cartNum" value = "${vo.cartNum}">
							<input type = "hidden" name = "pageNum" value = "${pageNum}">
							<input class = "btn btn-dark" type = "submit" value = "빼기">
						</td>
					</form>
				</tr>
				
			</c:forEach>
		</c:if>
		
		<c:if test = "${cnt == 0}">
			<tr>
				<td colspan = "8">장바구니에 담긴 제품이 없습니다.</td>
			</tr>
		</c:if>
	</table>
	
	<!-- 페이지 이동 -->
	<div class = "col-sm-4 text-center">
		<ul class="pagination pagination-sm justify-content-center"> <!-- 가운데 정렬 -->
			<!-- 글이 있으면 -->
			<c:if test = "${cnt > 0}">
				<!-- 처음[◀◀] / 이전블록[◀] -->
				<c:if test = "${startPage > pageBlock}">
					<li class="page-item"><a class="page-link" href="cart.do">◀◀</a></li>
					<li class="page-item"><a class="page-link" href="cart.do?pageNum=${startPage - pageBlock}" href="#">◀</a></li>
				</c:if>
					
				<!-- 블록 내의 페이지 번호 -->
				<c:forEach var = "i" begin = "${startPage}" end = "${endPage}">
					<c:if test = "${i == currentPage}">
						<li class="page-item"><a class="page-link" href="#"><b>[${i}]</b></a></li>
					</c:if>
					
					<c:if test = "${i != currentPage}">
						<li class="page-item"><a class="page-link" href="cart.do?pageNum=${i}">[${i}]</a></li>
					</c:if>
				</c:forEach>
				
				<!-- 다음블록[▶] / 마지막[▶▶] -->
				<c:if test = "${pageCount > endPage}">
					<li class="page-item"><a class="page-link" href="cart.do?pageNum=${startPage + pageBlock}">▶</a></li>
					<li class="page-item"><a class="page-link" href="cart.do?pageNum=${pageCount}">▶▶</a></li>
				</c:if>
			</c:if>
			
			<!-- 글이 없으면 -->
			<c:if test = "${cnt == 0}">
				<tr>
					<td colspan = "6"> - </td>
				</tr>
			</c:if>
		</ul>
	</div>
</div>
<%@ include file = "../common/footer.jsp" %>
</body>
</html>