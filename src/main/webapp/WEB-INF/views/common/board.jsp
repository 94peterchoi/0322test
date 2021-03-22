<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "lm-setting.jsp" %>
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
<title>게시판</title>
</head>
<body>
<body>
<br>
<br>
<br>
<div class="jumbotron jumbotron-fluid">
	<div style="color: white;" class = "container">
		<h1>게시판</h1>
		<p>이 게시판은 비회원도 작성할 수 있습니다.
		<br>게시판 비밀번호를 꼭 기억하세요.</p> 
	</div>
</div>

<div class = "container">
	<h3>Q&A.</h3>
	<br>
	<p>총 ${cnt}건의 게시글이 존재합니다.</p>
	<table class = "table table-hover" style = "text-align: center;">		
		<tr class = "table-info">
			<th width = "10%">번호</th>
			<th width = "40%">제목</th>
			<th width = "20%">작성자</th>
			<th width = "20%">등록일</th>
			<th width = "10%">조회수</th>
		</tr>
		
		<!-- 게시글이 있으면 -->
		<c:if test = "${cnt > 0}">
			<c:forEach var = "dto" items = "${dtos}">	<!-- var = "작은바구니" items = "큰바구니" -->
				<tr>
					<td>${number}<c:set var = "number" value = "${number - 1}" /></td>
					<td style = "text-align: left;">
						<!-- 답글인 경우 : 들여쓰기 > 1 -->
		                <c:if test="${dto.ref_level > 1}">
		                <c:set var="wid" value="${(dto.ref_level - 1) * 10}" />
		                	<img src="pj_images/level.gif" border="0" width="${wid}" height="15">
		                </c:if>
		                
		                <!-- 답글인 경우 : 들여쓰기 > 0 -->
						<c:if test="${dto.ref_level > 0}">
    		                <img src="pj_images/re.gif" border="0" width="20" height="15">
	        	        </c:if>
	        	        
	        	        <!-- hot 이미지 -->
						<c:if test = "${dto.readCnt > 10}">
							<img src = "pj_images/hot.gif" border = "0" width = "20" height = "15">
						</c:if>
						
						<!-- 상세페이지 -->
						<a href = "content.bo?num=${dto.num}&pageNum=${pageNum}&number=${number}">${dto.subject}</a>
					</td>
					<td>${dto.writer}</td>
					<td><fmt:formatDate value = "${dto.reg_date}" pattern = "yyyy-MM-dd HH:mm" /></td>
					<td>${dto.readCnt}</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<!-- 게시글이 없으면 -->
		<c:if test = "${cnt == 0}">
			<tr>
				<td colspan = "6">게시글이 없습니다. 글을 작성해주세요.!!</td>
			</tr>
		</c:if>
	</table>
	
			
	<!-- 페이지 이동 -->
	<div class = "text-center">
		<ul class="pagination pagination-sm justify-content-center"> <!-- 가운데 정렬 -->
			<!-- 글이 있으면 -->
			<c:if test = "${cnt > 0}">
				<!-- 처음[◀◀] / 이전블록[◀] -->
				<c:if test = "${startPage > pageBlock}">
					<li class="page-item"><a class="page-link" href="boardList.bo">◀◀</a></li>
					<li class="page-item"><a class="page-link" href="boardList.bo?pageNum=${startPage - pageBlock}" href="#">◀</a></li>
				</c:if>
					
				<!-- 블록 내의 페이지 번호 -->
				<c:forEach var = "i" begin = "${startPage}" end = "${endPage}">
					<c:if test = "${i == currentPage}">
						<li class="page-item"><a class="page-link" href="#"><b>[${i}]</b></a></li>
					</c:if>
					
					<c:if test = "${i != currentPage}">
						<li class="page-item"><a class="page-link" href="boardList.bo?pageNum=${i}">[${i}]</a></li>
					</c:if>
				</c:forEach>
				
				<!-- 다음블록[▶] / 마지막[▶▶] -->
				<c:if test = "${pageCount > endPage}">
					<li class="page-item"><a class="page-link" href="boardList.bo?pageNum=${startPage + pageBlock}">▶</a></li>
					<li class="page-item"><a class="page-link" href="boardList.bo?pageNum=${pageCount}">▶▶</a></li>
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
	
	<div class = "container">
		<div class = "row">
			<div class = "col-sm-6" align = "right">
				<input type = "button" class = "btn btn-info" data-toggle="modal" data-target="#write-board" value = "글 쓰기" onclick = "loginChk();">
			</div>
		</div>
	</div>
	
</div>

<%@ include file = "../board/boardModal.jsp" %>
</body>
</html>