<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.jumbotron {
	 	background-image: url('/labMall_mvc/pj_images/title_04.jpg');
	}
</style>
<title>환불 내용</title>
</head>
<body>
<!-- 점보트론 -->
<br>
<br>
<br>
<div class="jumbotron jumbotron-fluid">
	<div style="color: white;" class = "container">
		<h1>환불 요청 목록</h1>
		<p>환불 요청된 물품을 확인하세요</p> 
	</div>
</div>


<!-- 화면분할 -->
<div class = "container" align = "center">
	<!-- 장바구니 목록 -->
	<table class = "table table-hover" style = "text-align: center;">
		<tr class="table-info">
			<th>요청번호</th>
			<th>품명</th>
			<th>단가(원)</th>
			<th>수량</th>
			<th>신청인</th>
			<th>신청일</th>
			<th>상태</th>
		</tr>
		<c:if test = "${cnt > 0}">
			<c:forEach var = "vo" items = "${list}">
				<tr>
					<td>${vo.refundNum}</td>
					<td>${vo.title}</td>
					<td>${vo.p_price}</td>
					<td>${vo.p_count}</td>
					<td>${vo.clientId}</td>
					<td>${vo.refundReqDate }</td>
					<c:if test = "${vo.state == 0}">
						<form action = "confirmRefund.do">
							<input type = "hidden" name = "refundNum" value ="${vo.refundNum}">
							<input type = "hidden" name = "pageNum" value ="${pageNum}">
							<td>
								<input type = "submit" class = "btn btn-dark" value = "대기">
							</td>
							<td>-</td>
						</form>
					</c:if>
					<c:if test = "${vo.state != 0}">
						<td>
							<input type = "button" class = "btn btn-info" value = "승인">
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	
	<!-- 내역이 없는 경우 -->
		<c:if test="${cnt == 0}">
			<tr>
				<td colspan = "10">환불 요청된 제품이 없습니다.</td>
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
		
		

<div class = "container">
	<div class = "modal fade" id = "order">
		<div class = "modal-dialog">
			<div class = "modal-content">
				<!-- 모달 헤더 -->
				<div class = "modal-header">
					<h4 class = "modal title">환불하기</h4>
					<button type = "button" class ="close" data-dismiss = "modal">&times;</button>
				</div>
				<!-- 모달 바디 -->
				<div class = "modal-body">
					<table class = "table table-hover">
						<tr>
							<th colspan = "2" class = "table-info">
								내역
							</th>
						</tr>
						<tr>
							<td>전체 상품</td>
							<td align = "right">0개</td>
						</tr>
						<tr>
							<td>환불 금액</td>
							<td align = "right">0개</td>
						</tr>
					</table>
					<hr>
					<table class = "table table-hover">
						<tr class = "table-info">
							<th>환불 예정 금액</th>
							<th align = "right">0원</th>
						</tr>
					</table>
					<br>
					결제하시겠습니까?
					<br><br>
				</div>
				<!-- 모달 푸터 -->
				<div class = "modal-footer">
					<div align = "center">
						<button type = "button" class = "btn btn-info" data-dismiss = "modal">예</button>
						<button type = "button" class = "btn btn-info" data-dismiss = "modal">아니오</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>