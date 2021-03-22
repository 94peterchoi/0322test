<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class = "container" align = "center">
		<h3 class = "col-sm-6" align = "left">글 보기</h3>
		<hr class = "col-sm-6">
		<table class = "table table-hover col-sm-6">
			<tr>
				<th style = "background-color: #3c3c3c; color: white; width:100px;">글번호</th>
				<td>${number + 1}</td>
				<th style = "background-color: #3c3c3c; color: white; width:100px;">조회수</th>
				<td>${dto.readCnt}</td>
			</tr>	
			<tr>
				<th style = "background-color: #3c3c3c; color: white; width:100px;">작성자</th>
				<td>${dto.writer}</td>
				<th style = "background-color: #3c3c3c; color: white; width:100px;">작성일</th>
				<td><fmt:formatDate value = "${dto.reg_date}" type = "date" dateStyle = "default" /></td>
			</tr>
			<tr>
				<th style = "background-color: #3c3c3c; color: white; width:100px;">제목</th>
				<td colspan = "3">${dto.subject}</td>
			</tr>
			<tr>
				<th height = "300" style = "background-color: #3c3c3c; color: white; width:100px;">내용</th>
				<td colspan = "3">${dto.content}</td>
			</tr>
		</table>
		<hr class = "col-sm-6">
		<br>
		<input type = "button" class = "btn btn-info" data-toggle="modal" data-target="#write-board" value = "수정하기">
		<input type = "button" class = "btn btn-info" data-toggle="modal" data-target="#delete-board" value = "삭제하기">
		<input type = "button" class = "btn btn-info" data-toggle="modal" data-target="#ref-board" value = "답글쓰기"
			onclick="window.location='write.bo?num=${dto.num}&pageNum=${pageNum}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'">
		<a href = "boardList.bo"><input type = "button" class = "btn btn-info" value = "목록으로"></a>
	</div>
	
	<!-- The Modal : 게시글 수정 인증 -->
	<div class= "container">
		<div class="modal fade" id="write-board">
			<div class="modal-dialog">
				<div class="modal-content">
		    
		    	<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">글 수정하기</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body" class = "box01">
					<p>수정하려면 게시글 비밀번호를 입력하세요.</p>
					<form action = "modifyViewAction.bo" method = "post" name = "pwdform" onsubmit = "return pwdCheck();">
						<input type = "hidden" name = "num" value = "${param.num}">	
						<input type = "hidden" name = "pageNum" value = "${param.pageNum}">
						<div align = "center">
							<br>
							<table>
								<tr>
									<td align = "right"><label for = "pwd"><b>비밀번호&nbsp;</b></label></td>
									<td>
										<input type = "password" name = "pwd" id = "pwd">					
									</td>
								</tr>
								<tr>
									<td colspan = "2" align = "center">
										<br><br>
										<input type = "submit"  class = "btn btn-info" value = "인증하기">&nbsp;
										<input type = "reset"  class = "btn btn-info" value = "재입력">
									</td>
							</table>
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
	<!-- 모달 종료 : 게시글 수정 인증 -->
	
		<!-- The Modal : 게시글 삭제 인증 -->
	<div class= "container">
		<div class="modal fade" id="delete-board">
			<div class="modal-dialog">
				<div class="modal-content">
		    
		    	<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">글 삭제하기</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body" class = "box01">
					<p>삭제하려면 게시글 비밀번호를 입력하세요.</p>
					<form action = "deleteAction.bo" method = "post" name = "delPwdform" onsubmit = "return delPwdCheck();">
						<input type = "hidden" name = "num" value = "${param.num}">	
						<input type = "hidden" name = "pageNum" value = "${param.pageNum}">
						<div align = "center">
							<br>
							<table>
								<tr>
									<td align = "right"><label for = "pwd"><b>비밀번호&nbsp;</b></label></td>
									<td>
										<input type = "password" name = "pwd" id = "pwd">					
									</td>
								</tr>
								<tr>
									<td colspan = "2" align = "center">
										<br><br>
										<input type = "submit"  class = "btn btn-info" value = "삭제하기">&nbsp;
										<input type = "reset"  class = "btn btn-info" value = "재입력">
									</td>
							</table>
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
	<!-- 모달 종료 : 게시글 삭제 인증 -->
</body>
</html>