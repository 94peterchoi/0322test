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
<title>구매 내역</title>
<style>
	.jumbotron {
	 	background-image: url('/labMall_mvc/pj_images/title_04.jpg');
	 	font-weight: bold;
	 	
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
		<h1>구매목록</h1>
		<p>고객님께서 구매한 목록으로, 여기서 환불을 신청할 수 있습니다.
		<br>구매확정은 관리자 또는 고객이 직접 결정할 수 있으며,<br>처리중 버튼을 눌러 확정할 수 있습니다.
		<br>확정 시 환불이 불가합니다.</p>
	</div>
</div>


<!-- 화면분할 -->
<div class = "container" align = "center">
	<!-- 구매 목록 -->
	<table class = "table table-hover" style = "text-align: center;">
		<tr class="table-info">
			<th>품번</th>
			<th>품명</th>
			<th>단가(원)</th>
			<th>수량</th>
			<th>총액</th>
			<th>결제 상태</th>
			<th colspan = "2">승인일</th>
			<th>환불 요청</th>
		</tr>
		
		<!-- 구매내역이 있는 경우 -->
		<c:if test = "${cnt > 0}">
			<c:forEach var = "vo" items = "${list}">
				<tr>
					<td>${vo.pdNum}</td>
					<td>${vo.title}</td>					
					<td>${vo.p_price}</td>
					<td>${vo.p_count}</td>
					<td>${vo.p_price * vo.p_count}</td>
					
					<!-- 구매 미확정 -->
					<c:if test = "${vo.state == 0}">
						<form action = "buyConfirm.do" method = "post">
							<input type = "hidden" name = "buyNum" value = "${vo.buyNum}">
							<input type = "hidden" name = "pageNum" value = "${pageNum}">
							<%-- <input type = "hidden" name = "auHostId" value = "${sessionScope.clientId}"> --%>
							<td><input class = "btn btn-info" type = "submit" value = "처리중"></td>
						</form>
						<form action = "refundReq.do" method = "post">
							<input type = "hidden" name = "pdNum" value = "${vo.pdNum}">
							<input type = "hidden" name = "p_count" value = "${vo.p_count}">
							<input type = "hidden" name = "clientId" value = "${sessionScope.clientId}">
							<input type = "hidden" name = "buyNum" value = "${vo.buyNum}">
							<th></th>
							<td width = "100"><input name = "refundcount" class = "form-control" type = "number" min = "0" max = "${vo.p_count}" step = "1" value = "0"></td>
							<td width = "100"><input class = "btn btn-danger" type = "submit" value = "환불요청"></td>
						</form>
					</c:if>
					
					<!-- 구매 확정 -->
					<c:if test = "${vo.state == 1}">
						<td><input class = "btn btn-success" type = "button" value = "구매확정"></td>
						<td colspan = "2">${vo.auDate}</td>
						<td colspan = "3"><input class = "btn btn-dark" type = "button" value = "환불불가"></td>
					</c:if>
					
				</tr>
			</c:forEach>
		</c:if>
		
		<!-- 구매내역이 없는 경우 -->
		<c:if test="${cnt == 0}">
			<tr>
				<td colspan = "10">등록된 제품이 없습니다. 제품을 등록해주세요.</td>
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
					<li class="page-item"><a class="page-link" href="buy.do">◀◀</a></li>
					<li class="page-item"><a class="page-link" href="buy.do?pageNum=${startPage - pageBlock}" href="#">◀</a></li>
				</c:if>
					
				<!-- 블록 내의 페이지 번호 -->
				<c:forEach var = "i" begin = "${startPage}" end = "${endPage}">
					<c:if test = "${i == currentPage}">
						<li class="page-item"><a class="page-link" href="#"><b>[${i}]</b></a></li>
					</c:if>
					
					<c:if test = "${i != currentPage}">
						<li class="page-item"><a class="page-link" href="buy.do?pageNum=${i}">[${i}]</a></li>
					</c:if>
				</c:forEach>
				
				<!-- 다음블록[▶] / 마지막[▶▶] -->
				<c:if test = "${pageCount > endPage}">
					<li class="page-item"><a class="page-link" href="buy.do?pageNum=${startPage + pageBlock}">▶</a></li>
					<li class="page-item"><a class="page-link" href="buy.do?pageNum=${pageCount}">▶▶</a></li>
				</c:if>
			</c:if>
			
			<!-- 글이 없으면 -->
			<c:if test = "${cnt == 0}">
				<tr>
					<td>-</td>
				</tr>
			</c:if>
			
		</ul>
	</div>
</div>
<%@ include file = "../common/footer.jsp" %>
</body>
</html>