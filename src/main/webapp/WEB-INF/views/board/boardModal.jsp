<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type = "text/javascript">
	function writeCheck() {
		if(!document.writeform.writer.value) {
			alert("작성자를 입력하세요!!");
			document.writeform.writer.focus();
			return false;
		}
		
		if(!document.writeform.pwd.value) {
			alert("비밀번호를 입력하세요!!");
			document.writeform.pwd.focus();
			return false;
		}
		
		if(!document.writeform.subject.value) {
			alert("제목을 입력하세요!!");
			document.writeform.subject.focus();
			return false;
		}
		
		if(!document.writeform.content.value) {
			alert("내용을 입력하세요!!");
			document.writeform.content.focus();
			return false;
		}
	}
</script>
</head>
<body>

<div class= "container">
	<div class="modal fade" id="write-board">
		<div class="modal-dialog">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">글 쓰기</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body" class = "box01">
				<form action = "writeNewAction.bo" method = "post" name = "writeform" onsubmit = "return writeCheck();">
				
					<div align = "center">
						<table class = "table table-hover">
							<tr>
								<td align = "right">작성자</td>
								<td><input type = "text" name = "writer" width = "100%" size = "38" maxlength = "8"></td>
							</tr>
							<tr>
								<td align = "right">비밀번호</td>
								<td><input type = "password" name = "pwd" width = "100%" size = "38" maxlength = "8"></td>
							</tr>						
							
							<tr>
								<td align = "right">제목</td>
								<td><input type = "text" name = "subject" width = "100%" size = "38" maxlength = "30"></td>
							</tr>
							
							<tr>
								<td align = "right">내용</td>
								<td><textarea cols = "40" rows = "8" name = "content" maxlength = "2000"></textarea></td>
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

</body>
</html>