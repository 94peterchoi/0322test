<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>구매 내역</title>
<style>
	.jumbotron {
	 	background-image: url('/labMall_mvc/pj_images/title_04.jpg');
	}
</style>
</head>
<body>
	<%@ include file = "admin-menu.jsp" %>
	<!-- 점보트론 -->
	<br>
	<br>
	<br>
	<div class="jumbotron jumbotron-fluid">
		<div style="color: white;" class = "container">
			<h1>구매목록</h1>
			<p>전체 구매 목록입니다.
			<br>구매 확정 시, 환불이 불가하며 결산에 반영됩니다.</p>
		</div>
	</div>
	
	
	<!-- 화면분할 -->
	<div class = "container" align = "center">
		<!-- 구매 목록 -->
		<table class = "table table-hover" style = "text-align: center;">
			<tr class="table-info">
				<th>품번</th>
				<th>품명</th>
				<th></th>
				<th>단가(원)</th>
				<th>수량</th>
				<th>총액</th>
				<th>요청</th>
				<th></th>
				<th></th>
				<th>승인일</th>
				<th>상태</th>
			</tr>
			
			<!-- 구매내역이 있는 경우 -->
			<c:if test = "${cnt > 0}">
				<c:forEach var = "vo" items = "${list}">
					<tr>
						<td>${vo.pdNum}</td>
						<td>${vo.title}</td>
						<td></td>
						<td>${vo.p_price}</td>
						<td>${vo.p_count}</td>
						<td>${vo.p_price * vo.p_count}</td>
						<td>${vo.clientId}</td>
						<td></td>
						<td></td>
						<td>${vo.auDate}</td>
						
						<!-- 구매 미확정 -->
						<c:if test = "${vo.state == 0}">
							<form action = "buyConfirm.do" method = "post">
								<input type = "hidden" name = "pdNum" value = "${vo.pdNum}">
								<input type = "hidden" name = "buyNum" value = "${vo.buyNum}">
								<input type = "hidden" name = "p_count" value = "${vo.p_count}">
								<input type = "hidden" name = "pageNum" value = "${pageNum}">
								<%-- <input type = "hidden" name = "auHostId" value = "${sessionScope.clientId}"> --%>
								<td><input class = "btn btn-dark" type = "submit" value = "승인대기"></td>
							</form>
						</c:if>
						
						<!-- 구매 확정 -->
						<c:if test = "${vo.state == 1}">
							<td><input class = "btn btn-info" type = "button" value = "결제확정"></td>
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
	
	<!-- 품목별 환불 모달 -->
	<!-- <div class = "container">
		<div class = "modal fade" id = "refund-sub">
			<div class = "modal-dialog">
				<div class = "modal-content">
					모달 헤더
					<div class = "modal-header">
						<h4 class = "modal title">환불하기</h4>
						<button type = "button" class ="close" data-dismiss = "modal">&times;</button>
					</div>
					모달 바디
					<div class = "modal-body">
						<table class = "table table-hover">
							<tr>
								<th colspan = "2" class = "table-info">
									내역
								</th>
							</tr>
							<tr>
								<td colspan = "2">
									<img src = "../pj_images/model_01.jpg" width = "100%">
								</td>
							</tr>
							<tr>
								<td>품명</td>
								<td align = "right">갤럭시북S</td>
							</tr>
							<tr>
								<td>수량</td>
								<td align = "right">1개</td>
							</tr>
							<tr>
								<td>환불 사유</td>
								<td align = "right">
									<select name = "rf-reason">
										<option value = "---선택하세요---">---선택하세요---</option>
										<option value = "단순 변심">단순 변심</option>
										<option value = "제품 파손">제품 파손</option>
										<option value = "배송 지연">배송 지연</option>
										<option value = "기타">기타</option>
									</select>
								</td>
							</tr>
						</table>
						<hr>
						<table class = "table table-hover">
							<tr class = "table-info">
								<th>환불 예정 금액</th>
								<th align = "right" style = "text-align:right;">1,300,000원</th>
							</tr>
						</table>
						<br>
						환불 요청하시겠습니까?
						<br><br>
					</div>
					모달 푸터
					<div class = "modal-footer">
						<div align = "center">
							<button type = "button" class = "btn btn-info" data-dismiss = "modal">예</button>
							<button type = "button" class = "btn btn-info" data-dismiss = "modal">아니오</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	
	<!-- 전체 환불 모달 -->
	<!-- <div class = "container">
		<div class = "modal fade" id = "refund">
			<div class = "modal-dialog">
				<div class = "modal-content">
					모달 헤더
					<div class = "modal-header">
						<h4 class = "modal title">환불하기</h4>
						<button type = "button" class ="close" data-dismiss = "modal">&times;</button>
					</div>
					모달 바디
					<div class = "modal-body">
						<table class = "table table-hover">
							<tr>
								<th colspan = "2" class = "table-info">
									요청 내역
								</th>
							</tr>
							<tr>
								<td>선택한 품목 수</td>
								<td align = "right">2개</td>
							</tr>
							<tr>
								<td>선택한 총 수량</td>
								<td align = "right">3개</td>
							</tr>
						</table>
						<hr>
						<table class = "table table-hover">
							<tr class = "table-info">
								<th>환불 예상 금액</th>
								<th align = "right" style = "text-align:right;">2,340,000원</th>
							</tr>
						</table>
						<br>
						환불 요청하시겠습니까?
						<br><br>
					</div>
					모달 푸터
					<div class = "modal-footer">
						<div align = "center">
							<button type = "button" class = "btn btn-info" data-dismiss = "modal">예</button>
							<button type = "button" class = "btn btn-info" data-dismiss = "modal">아니오</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>