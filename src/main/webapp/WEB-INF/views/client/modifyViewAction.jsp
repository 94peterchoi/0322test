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
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type = "text/javascript">
	function modifyLoad() {
		document.getElementById("mod_btn").click();
	}
	
	function back() {
		history.back();
	}
</script>
</head>
<body onload = "modifyLoad();" style = "background-image: url(pj_images/title_04.jpg);">
<%@ include file = "login-menu.jsp" %>
<c:if test = "${selectCnt == 1}">
	<input type="button" id = "mod_btn" class="btn dropdown-item" data-toggle="modal" data-target="#modify">
	
	<div class= "container">
		<div class="modal fade" id="modify">
			<div class="modal-dialog">
				<div class="modal-content">
		    
		    	<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">내 정보</h4>
					<button type="button" class="close" data-dismiss="modal" onclick = "back();">&times;</button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body">
					<p> 내 정보를 변경하시려면<br>
						아래 내용 변경 후 수정하기 버튼을 눌러주세요.</p>
					<div align = "center">
						<form action = "modifyClientAction.do" method = "post" name = "modifyform" onsubmit = "return modifyformCheck();">
							<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
							<div align = "center">
								<br>
								<table>
									<tr>
										<td align = "right"><label for = "mod_id"><b>아이디&nbsp;</b></label></td>
										<td>"${vo.id}"</td>
									</tr>
									
									<tr>
										<td align = "right"><label for = "mod_joinDate"><b>가입일&nbsp;</b></label></td>
										<td><fmt:formatDate value = "${vo.reg_date}" pattern = "yyyy-MM-dd HH:mm" /></td>
									</tr>
									
									<tr>
										<td align = "right"><label for = "mod_pwd"><b>비밀번호&nbsp;</b></label></td>
										<td>
											<input type = "password" name = "mod_pwd" value = "${vo.pwd}">					
										</td>
									</tr>
									<tr>
										<td align = "right"><label for = "mod_rePwd"><b>비밀번호 확인&nbsp;</b></label></td>
										<td>
											<input type = "password" name = "mod_rePwd" id = "mod_rePwd">					
										</td>
									</tr>
									<tr>
										<td align = "right"><label for = "mod_name"><b>이름&nbsp;</b></label></td>
										<td>
											<input type = "text" name = "mod_name" id = "mod_name" value = "${vo.name}">					
										</td>
									</tr>
									<tr>
										<td align = "right"><label for = "mod_hp1"><b>연락처&nbsp;</b></label></td>
										<td>
											<c:set var = "arrHp" value = "${fn:split(vo.phone, '-')}" />
											<input type = "text" id = "mod_hp1" name = "mod_hp1" onkeyup = "udtNextHp1();" size = "3" maxlength = "3" value = "${arrHp[0]}">
											-
											<input type = "text" id = "mod_hp2" name = "mod_hp2" onkeyup = "udtNextHp2();" size = "4" maxlength = "4" value = "${arrHp[1]}">
											-
											<input type = "text" id = "mod_hp3" name = "mod_hp3" onkeyup = "udtNextHp3();" size = "4" maxlength = "4" value = "${arrHp[2]}">					
										</td>
									</tr>
									<tr>
										<td align = "right"><label for = "mod_email1"><b>이메일&nbsp;</b></label></td>
										<td>
											<c:set var = "arrEmail" value = "${fn:split(vo.email, '@')}" />
											<input type = "text" name = "mod_email1" id = "mod_email1" size = "5" value = "${arrEmail[0]}">
											@
											<input type = "text" name = "mod_email2" id = "mod_email2" size = "8" value = "${arrEmail[1]}">	
											<select name = "mod_email3" onchange = "udtSelectEmailChk();">
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
												<input type = "submit" class = "btn btn-info" value = "수정하기">
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
		      		<button type="button" class="btn btn-dark" data-dismiss="modal" onclick = "back();">Close</button>
		      	</div>
		      
		   		</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test = "${selectCnt != 1}">
	<script type="text/javascript">
		errorAlert(passwordError);
	</script>
</c:if>
</body>
</html>