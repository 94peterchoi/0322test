<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type = "text/javascript">
	function priceSync() {
		document.pdViewForm.price.value = document.pdViewForm.priceRange.value;
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
	<form action = "productModifyAction.pd" enctype="multipart/form-data" name = "pdViewForm" method = "post">
		<input type ="hidden" name = "pdNum" value = "${param.pdNum}">
		<input type ="hidden" name = "pageNum" value = "${param.pageNum}">
		<table class = "table table-hover">
			<tr>
				<th align = "right" width = "100">품명</th>
				<td colspan = "2"><input class = "form-control" type = "text" name = "title" value = "${vo.title}"></td>
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
         			<br>
         			<br>
         			<p>사진을 교체하려면 파일을 선택하세요.</p>
         			<br>
         			<input class = "form-control input-sm" type = "file" name = "file1">
				</td>
			</tr>
			
			<tr>
				<th align = "right">가격</th>
				<td>
					<input class = "form-control" type = "number" name = "p_price" min = "10000" max = "8000000" step = "10000" value = "${vo.p_price}">
				</td>
				
			</tr>
		
			<tr>
				<th align = "right">수량</th>
				<td colspan = "2"><input class = "form-control" type = "number" name = "p_count" min = "1" value = "${vo.p_count}"></td>
			</tr>
			
		</table>
		<hr>
		<div class = "button" align = "center">
			<input type = "submit"  class = "btn btn-info" value = "수정">&nbsp;
			<input type = "reset" class = "btn btn-info" value = "초기화">
			<a href = "productDeleteAction.pd?pdNum=${vo.pdNum}&pageNum=${pageNum}"><input type = "button" class = "btn btn-info" value = "삭제"></a>
			<a href = "stock.pd?pageNum=${pageNum}"><input type = "button" class = "btn btn-info" value = "돌아가기"></a>
		</div>
		<br>
			
	</form>
</div>	
</body>
</html>