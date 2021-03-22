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
<title>환불 내역</title>
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
	<div style="color: white;" class="jumbotron jumbotron-fluid">
		<div class = "container">
			<h1>환불목록</h1>
			<p>고객님께서 환불요청한 목록입니다.</p> 
		</div>
	</div>
	
	
	<!-- 화면분할 -->
	<div class = "container" align = "center">
		<!-- 구매 목록 -->
		<table class = "table table-hover" style = "text-align: center;">
			<tr class="table-info">
				<th>순번</th>
				<th>품명</th>
				<th>단가(원)</th>
				<th>수량</th>
				<th>총액</th>
				<th>환불신청일</th>
				<th>상태</th>
				<th>환불 취소</th>
			</tr>
			
			<!-- 환불내역이 있는 경우 -->
			<c:if test = "${cnt > 0}">
				<c:forEach var = "vo" items = "${list}">
					<tr>
						<td>${vo.refundNum}</td>
						<td>${vo.title}</td>
						<td>${vo.p_price}</td>
						<td>${vo.p_count}</td>
						<td>${vo.p_price * vo.p_count}</td>
						<td>${vo.refundReqDate}</td>
						<td>
							<c:if test = "${vo.state == 0}">
								요청 처리 중
							</c:if>
							<c:if test = "${vo.state != 0}">
								처리 완료
							</c:if>
						</td>
						<td width = "100">
							<c:if test = "${vo.state == 0}">
								<form action = "cancelRefund.do" method = "post">
									<input type = "hidden" name = "pdNum" value = "${vo.pdNum}">
									<input type = "hidden" name = "refundNum" value = "${vo.refundNum}">
									<input type = "hidden" name = "p_count" value = "${vo.p_count}">
									<input type = "hidden" name = "buyNum" value = "${vo.buyNum}">
									<input class = "btn btn-dark" type = "submit" value = "취소">
								</form>
							</c:if>
							<c:if test = "${vo.state != 0}">
								<input class = "btn btn-info" type = "button" value = "취소불가">
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<!-- 구매내역이 없는 경우 -->
			<c:if test="${cnt == 0}">
				<tr>
					<td colspan = "10">환불된 제품이 없습니다.</td>
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
						<li class="page-item"><a class="page-link" href="refund.do">◀◀</a></li>
						<li class="page-item"><a class="page-link" href="refund.do?pageNum=${startPage - pageBlock}" href="#">◀</a></li>
					</c:if>
						
					<!-- 블록 내의 페이지 번호 -->
					<c:forEach var = "i" begin = "${startPage}" end = "${endPage}">
						<c:if test = "${i == currentPage}">
							<li class="page-item"><a class="page-link" href="#"><b>[${i}]</b></a></li>
						</c:if>
						
						<c:if test = "${i != currentPage}">
							<li class="page-item"><a class="page-link" href="refund.do?pageNum=${i}">[${i}]</a></li>
						</c:if>
					</c:forEach>
					
					<!-- 다음블록[▶] / 마지막[▶▶] -->
					<c:if test = "${pageCount > endPage}">
						<li class="page-item"><a class="page-link" href="refund.do?pageNum=${startPage + pageBlock}">▶</a></li>
						<li class="page-item"><a class="page-link" href="refund.do?pageNum=${pageCount}">▶▶</a></li>
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