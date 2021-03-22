<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<!-- The Modal : 로그인 -->
<div class= "container">
	<div class="modal fade" id="login-section">
		<div class="modal-dialog">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">로그인</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body" class = "box01">
				<form action = "loginPro.do" method = "post" name = "loginform" onsubmit = "return loginCheck();">
					<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
					<div align = "center">
						<br>
						<br>
						<table>
							<tr>
								<td align = "right"><label for = "input_id"><b>아이디&nbsp;</b></label></td>
								<td>
									<input type = "text" name = "id" id = "id">					
								</td>
							</tr>
							<tr>
								<td align = "right"><label for = "input_pwd"><b>비밀번호&nbsp;</b></label></td>
								<td>
									<input type = "password" name = "pwd" id = "pwd">					
								</td>
							</tr>
							<tr>
								<td colspan = "2" align = "center">
									<input id = "check_remember" type = "checkbox"><label for = "check_remember">&nbsp;ID 기억하기</label>
								</td>
								
							</tr>
							
							<tr>
								<td colspan = "2">
								<br>
									<div class = "button" align = "center">
										<input type = "submit"  class = "btn btn-info" value = "로그인" onsubmit = "mainCheck();">&nbsp;
									<a href = "#" data-toggle="modal" data-target="#regist"><input type = "button" class = "btn btn-info" value = "회원가입"></a>
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
<!-- 모달 종료 : 로그인 -->

<!-- The Modal : 회원가입 -->
<div class= "container">
	<div class="modal fade" id="regist">
		<div class="modal-dialog">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">회원 가입</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
				<div align = "center">
					<form action = "signInAction.do" method = "post" name = "signInform" onsubmit = "return signInCheck();">
						<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
						<input type = "hidden" name = "hiddenId" value = "0">
						<div align = "center">
							<br>
							<table>
								<tr>
									<td align = "right"><label for = "id"><b>아이디&nbsp;</b></label></td>
									<td>
										<input type = "text" name = "id" id = "id" size = "10">
										<input type = "button" class = "btn btn-dark" value = "중복확인"  onclick = "confirmId();">					
									</td>
								</tr>
								<tr>
									<td align = "right"><label for = "pwd"><b>비밀번호&nbsp;</b></label></td>
									<td>
										<input type = "password" name = "pwd">					
									</td>
								</tr>
								<tr>
									<td align = "right"><label for = "rePwd"><b>비밀번호 확인&nbsp;</b></label></td>
									<td>
										<input type = "password" name = "rePwd" id = "rePwd">					
									</td>
								</tr>
								<tr>
									<td align = "right"><label for = "name"><b>이름&nbsp;</b></label></td>
									<td>
										<input type = "text" name = "name" id = "name">					
									</td>
								</tr>
								
								<tr>
									<td align = "right"><label for = "hp1"><b>연락처&nbsp;</b></label></td>
									<td>
										<input type = "text" id = "hp1" name = "hp1" onkeyup = "nextHp1();" size = "3" maxlength = "3">
										-
										<input type = "text" id = "hp2" name = "hp2" onkeyup = "nextHp2();" size = "4" maxlength = "4">
										-
										<input type = "text" id = "hp3" name = "hp3" onkeyup = "nextHp3();" size = "4" maxlength = "4">					
									</td>
								</tr>
								<tr>
									<td align = "right"><label for = "email1"><b>이메일&nbsp;</b></label></td>
									<td>
										<input type = "text" name = "email1" id = "email1" size = "5">
										@
										<input type = "text" name = "email2" id = "email2" size = "8">	
										<select name = "email3" onchange = "selectEmailChk();">
											<option value = "0">직접 입력</option>
											<option value = "naver.com">네이버</option>
											<option value = "google.com">구글</option>
											<option value = "daum.net">다음</option>
											<option value = "kakao.com">카카오</option>
											<option value = "nate.com">네이트</option>
										</select>
									</td>
								</tr>
								
								<tr>
									<td colspan = "2">
									<br>
										<div class = "button" align = "center">
											<input type = "submit" class = "btn btn-info" value = "회원 가입">
											&nbsp;&nbsp;<input class = "btn btn-info" type = "reset" value = "다시 입력">	
										</div>
										<br>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</div>
	
			<!-- Modal footer -->
	    	<div class="modal-footer">
	      		<button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
	      	</div>
	      
	   		</div>
		</div>
	</div>
</div>
<!-- 모달 종료 : 회원가입 -->

<!-- The Modal : 회원탈퇴 -->
<div class= "container">
	<div class="modal fade" id="withdrawl-section">
		<div class="modal-dialog">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">회원 탈퇴</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body" class = "box01">
				<p>탈퇴하시려면 비밀번호를 입력하세요.</p>
				<form action = "deleteClientAction.do" method = "post" name = "passwdform" onsubmit = "return passwdCheck();">
					<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
					<div align = "center">
						<br>
						<table>
							<tr>
								<td align = "right"><label for = "pwd"><b>비밀번호&nbsp;</b></label></td>
								<td>
									<input type = "password" name = "pwd" id = "del_pwd">					
								</td>
							</tr>
							<tr>
								<td colspan = "2" align = "center">
									<br><br>
									<input type = "submit"  class = "btn btn-info" value = "회원 탈퇴">&nbsp;
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
<!-- 모달 종료 : 회원 탈퇴 -->

<!-- The Modal : 회원 정보 수정 인증 -->
<div class= "container">
	<div class="modal fade" id="modify-section">
		<div class="modal-dialog">
			<div class="modal-content">
	    
	    	<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">내 정보 보기</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body" class = "box01">
				<p>확인하려면 비밀번호를 입력하세요.</p>
				<form action = "modifyViewAction.do" method = "post" name = "mod_passwdform" onsubmit = "return modPasswdCheck();">
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
<!-- 모달 종료 : 회원정보 수정 인증 -->

<!-- 모달 : 회원정보 수정 처리 = > modifyViewAction.do로 이양 -->

</body>
</html>