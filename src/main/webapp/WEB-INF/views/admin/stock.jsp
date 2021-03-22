<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common/lm-setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.jumbotron {
	 	background-image: url('/labMall_mvc/pj_images/title_04.jpg');
	}
</style>
<script type = "text/javascript">
	function priceInput() {
		document.productRegistform.price.value = document.productRegistform.priceRange.value;
	}
	
	function valueChk() {
		if(document.valueChangeForm.quantity.value == document.valueChangeForm.quantity.min) {
			alert("수량을 변경해주세요!");
			return false;
		}
	}
</script>
</head>
<body>
<!-- 점보트론 -->
<br>
<br>
<br>
<div class="jumbotron jumbotron-fluid">
	<div style="color: white;" class = "container">
		<h1>재고목록</h1>
		<p>재고 현황입니다. 여기서 제품을 등록하고 관리할 수 있습니다.
		<br>카트와 재고 수량을 비교하고 필요한 입고량을 조정해보세요.</p> 
	</div>
</div>


<!-- 화면분할 -->
<div class = "container" align = "center">
	<!-- 재고 목록 -->
	<table class = "table table-hover" style = "text-align: center;">
		<tr class="table-info">
			<th style = "vertical-align: middle;" rowspan = "2">품번</th>
			<th style = "vertical-align: middle;" rowspan = "2">제품명</th>
			<th style = "vertical-align: middle;" rowspan = "2">단가(원)</th>
			<th colspan = "1">수량</th>
			<th style = "vertical-align: middle;" rowspan = "2">등록일</th>
			<th style = "vertical-align: middle;" rowspan = "2">입고선택</th>
		</tr>
		<tr class="table-info">
			<th>재고</th>
		</tr>
		<c:if test = "${cnt > 0}">
			<c:forEach var = "vo" items = "${list}">
				<tr>
					<td style = "vertical-align: middle;">${vo.pdNum}</td>
					<td><a href = "productView.pd?pdNum=${vo.pdNum}&pageNum=${pageNum}" class = "btn">${vo.title}</a></td>
					<td style = "vertical-align: middle;">${vo.p_price}</td>
					<form name = "valueChangeForm" action = "quantityChange.pd" method = "post" onsubmit = "return valueChk();">	<!-- 지저분해 보여도 submit 범위를 제한하려면 이렇게 할 수 밖에 없다. -->
																								<!-- 자바스크립트로 값이 같으면 거절하는 함수를 만들어 여기에 적용한다. 방법을 모르겠다. -->
					<td width = "90">
						<input type = "hidden" name = "pdNum" value = "${vo.pdNum}">
						<input type = "hidden" name = "pageNum" value = "${param.pageNum}">
						<input class = "form-control" type = "number" value = "${vo.p_count}" min = "${vo.p_count}" name = "p_count">
					</td>
					<td style = "vertical-align: middle;">${vo.p_date}</td>
					<td>
						<input class = "btn btn-info" type = "submit" value = "입고">	
						<input class = "btn btn-dark" type = "reset" value = "초기화">
					</td>
					</form>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${cnt == 0}">
			<tr>
				<td colspan = "9">등록된 제품이 없습니다. 제품을 등록해주세요.</td>
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
					<li class="page-item"><a class="page-link" href="stock.pd">◀◀</a></li>
					<li class="page-item"><a class="page-link" href="stock.pd?pageNum=${startPage - pageBlock}" href="#">◀</a></li>
				</c:if>
					
				<!-- 블록 내의 페이지 번호 -->
				<c:forEach var = "i" begin = "${startPage}" end = "${endPage}">
					<c:if test = "${i == currentPage}">
						<li class="page-item"><a class="page-link" href="#"><b>[${i}]</b></a></li>
					</c:if>
					
					<c:if test = "${i != currentPage}">
						<li class="page-item"><a class="page-link" href="stock.pd?pageNum=${i}">[${i}]</a></li>
					</c:if>
				</c:forEach>
				
				<!-- 다음블록[▶] / 마지막[▶▶] -->
				<c:if test = "${pageCount > endPage}">
					<li class="page-item"><a class="page-link" href="stock.pd?pageNum=${startPage + pageBlock}">▶</a></li>
					<li class="page-item"><a class="page-link" href="stock.pd?pageNum=${pageCount}">▶▶</a></li>
				</c:if>
			</c:if>
			
			<!-- 글이 없으면 -->
			<c:if test = "${cnt == 0}">
				<tr>
					<td colspan = "6">게시글이 없습니다.</td>
				</tr>
			</c:if>
		</ul>
	</div>
	<div class = "row">
		<div class = "col-sm-6">
			<table class = "table table-hover">
				<tr>
					<td width = "100%">
						<h5 align = "left">총 ${cnt}건의 제품이 등록되어 있습니다.</h5>
					</td>
				</tr>
			</table>
		</div>
		<div class = "col-sm-6" align = "right">
			<table class = "table table-hover">
			<tr>
				<td width = "100%" align = "right">
					<input class = "btn btn-info" type = "button" data-toggle="modal" data-target="#pd-regist" value = "제품 등록">
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
		

<!-- 제품 등록 시작 -->
<div class= "container">
	<div class="modal fade" id="pd-regist">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">제품 등록</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body" class = "box01">
				<form action = "productRegistAction.pd" enctype="multipart/form-data" method = "post" name = "productRegistform" onsubmit = "return productRegistCheck();">
					
					<div align = "center" style = "text-valign: middle;">
						<table class = "table table-hover">
							<tr>
								<th>제품명</th>
								<td colspan = "2">
									<input class = "form-control input-sm" type = "text" name = "title" placeholder = "제품명을 입력하세요">
								</td>
							</tr>
							
							<tr>
								<th>대표사진</th>
								<td colspan = "2">
									<input class = "form-control input-sm" type = "file" name = "file1">
								</td>
							</tr>
							
							<tr>
								<th>가격</th>
								<td width = "130">
									<input class = "form-control input-sm" type = "number" min= "10000" name = "p_price" value = "10000" step = "1000">
								</td>
								<td>
								</td>
								
							</tr>
							
							<tr>
								<th>수량</th>
								<td colspan = "2">
									<input class = "form-control input-sm" type = "number" min= "1" name = "p_count" value = "1">
								</td>
							</tr>
							
						</table>	
						<div class = "button" align = "center">
							<input type = "submit"  class = "btn btn-info" value = "업로드">&nbsp;
							<input type = "reset" class = "btn btn-info" value = "초기화">
						</div>
						<br>
					</div>
				</form>
			</div>
	
			<!-- Modal footer -->
	    	<div class="modal-footer">
	      		<button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
	      	</div>
	      
	   		</div>
		</div>
	</div>
</div>
<!-- 제품 등록 종료 -->

<div class = "container">
	<div class = "modal fade" id = "order">
		<div class = "modal-dialog">
			<div class = "modal-content">
				<!-- 모달 헤더 -->
				<div class = "modal-header">
					<h4 class = "modal title">주문하기</h4>
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
							<td>주문 금액</td>
							<td align = "right">0개</td>
						</tr>
					</table>
					<hr>
					<table class = "table table-hover">
						<tr class = "table-info">
							<th>결제 예정 금액</th>
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