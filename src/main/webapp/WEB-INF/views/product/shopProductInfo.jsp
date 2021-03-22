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
<script type = "text/javascript">
	function priceSync() {
		document.pdViewForm.price.value = document.pdViewForm.priceRange.value;
	}
	
	function loginStateCheck() {
		if(${sessionScope.clientId == null}) {
			alert("로그인해주세요!!");
			window.location.href = "shopProductView.pd?pdNum=${param.pdNum}&pageNum=1";
		}
		else {
			return false;
		}
	}
</script>
</head>
<body>
<br>
<br>
<br>
<br>
<div class = "container" align = "center">
	<h3 align = "left">제품 정보</h3>
	<hr>
	<form action = "directBuy.pd" enctype="multipart/form-data" name = "pdViewForm" method = "post">
		<input type ="hidden" name = "pdNum" value = "${param.pdNum}">
		<input type ="hidden" name = "pageNum" value = "${param.pageNum}">
		<table class = "table table-hover">
			<tr>
				<th align = "right" width = "100">품명</th>
				<td colspan = "2">${vo.title}</td>
			</tr>
			
			<tr>
				<th align = "right">대표사진</th>
				<td colspan = "2">
					<c:choose>
			            <c:when test="${vo.pdImage != '-'}">
			               <img src="/labMall_mvc/pj_images/${vo.pdImage}" width="400">
			            </c:when>
			            <c:otherwise>
			            	<table>
			            		<tr>
			            			<td align = "center" width = "400" height = "235" style = "background-color: #AAAAAA; vertical-align: middle;">
			               				<p>등록된 사진이 없습니다.<br>사진을 등록해주세요.</p>
			               			</td>
			               		</tr>
			               	</table>
			            </c:otherwise>
         			</c:choose>
				</td>
			</tr>
			
			<tr>
				<th align = "right">가격</th>
				<td colspan = "2">${vo.p_price}</td>
			</tr>
		
			<tr>
				<th align = "right">수량</th>
				<td colspan = "2">${vo.p_count}</td>
			</tr>
			
		</table>
		<hr>
		<div class = "button" align = "center">
			<input class = "btn btn-info" type = "button" data-toggle="modal" data-target="#buy-section" value = "바로구매" onclick = "loginStateCheck();">
			<input class = "btn btn-info" type = "button" data-toggle="modal" data-target="#cart-section" value = "장바구니" onclick = "loginStateCheck();">
			<a href = "shopping.do"><input type = "button" class = "btn btn-info" value = "돌아가기"></a>
		</div>
		<br>
			
	</form>
</div>	

<!-- The Modal : 장바구니 진입 -->
<div class= "container">
	<div class="modal fade" id="cart-section">
		<div class="modal-dialog">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">장바구니 담기</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body" class = "box01">
				<form action = "cartAction.do" method = "post" name = "cartform" onsubmit = "return cartCheck();">
					<input type = "hidden" name = "guestId" value = "${sessionScope.clientId}">
					<input type = "hidden" name = "pdNum" value = "${param.pdNum}">
					<div align = "center">
						<p align = "left">${vo.title}을(를) 장바구니에 담습니다.<br>수량을 지정하세요</p>
						<br>
						<table>
							<tr>
								<td align = "right" style = "vertical-align: middle;"><label for = "cartcount"><b>수량&nbsp;</b></label></td>
								<td>
									<input class = "form-control" type = "number" min = "1" step = "1" value = "1" name = "cartcount">
								</td>
							</tr>
							
							<tr>
								<td colspan = "2">
								<br>
								<br>
									<div class = "button" align = "center">
										<input type = "submit"  class = "btn btn-info" value = "담기">&nbsp;
										<input type = "reset" class = "btn btn-info" value = "초기화">
										<br>
										<br>
									</div>
								</td>
							</tr>
						</table>
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
<!-- 모달 종료 : 장바구니 진입 -->

<!-- The Modal : 바로구매 진입 -->
<div class= "container">
	<div class="modal fade" id="buy-section">
		<div class="modal-dialog">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">바로 구매</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body" class = "box01">
				<form action = "buyAction.do" method = "post" name = "buyform">
					<input type = "hidden" name = "guestId" value = "${sessionScope.clientId}">
					<input type = "hidden" name = "pdNum" value = "${param.pdNum}">
					<div align = "center">
						<p align = "left">${vo.title}을(를) 구매합니다.<br>수량을 지정하세요</p>
						<br>
						<table>
							<tr>
								<td align = "right" style = "vertical-align: middle;"><label for = "cartcount"><b>수량&nbsp;</b></label></td>
								<td>
									<input class = "form-control" type = "number" min = "1" step = "1" value = "1" name = "buycount">
								</td>
							</tr>
							
							<tr>
								<td colspan = "2">
								<br>
								<br>
									<div class = "button" align = "center">
										<input type = "submit"  class = "btn btn-info" value = "구매">&nbsp;
										<input type = "reset" class = "btn btn-info" value = "초기화">
										<br>
										<br>
									</div>
								</td>
							</tr>
						</table>
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
<!-- 모달 종료 : 바로구매 진입 -->

</body>
</html>