/**
 * validation check
 */
// 위치 : 공책방
// 에러메시지
var insertError = "회원가입에 실패했습니다. \n확인후 다시 시도하세요";
var passwordError = "비밀번호가 일치하지 않습니다. \n확인후 다시 시도하세요";
var deleteError = "회원탈퇴에 실패했습니다. \n확인후 다시 시도하세요";
var updateError = "회원정보 수정에 실패했습니다. \n확인후 다시 시도하세요";
var nullIdError = "존재하지 않는 ID입니다. \n확인후 다시 시도하세요";
var recommand = "회원가입을 축하합니다. 로그인하세요!!";

function errorAlert(errorMsg) {
	alert(errorMsg);
	history.back(); // 이전 페이지로 이동
}

// -- 메인 페이지
function mainFocus() {
	document.mainform.id.focus();
}

function mainCheck() {
	if(!document.mainform.id.value) {
		alert("아이디를 입력하세요!!")
		document.mainform.id.focus();
		return false;
	}
	if(!document.mainform.pwd.value) {
		alert("비밀번호를 입력하세요!!")
		document.mainform.pwd.focus();
		return false;
	}
}

// --- 회원가입 페이지
// 회원가입 폼
function signInFocus() {
	document.signInform.id.focus();
}

function signInCheck() {
	if(!document.signInform.id.value) {
		alert("아이디를 입력하세요!!");
		document.signInform.id.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.pwd.value) {
		alert("비밀번호를 입력하세요!!");
		document.signInform.pwd.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.repwd.value) {
		alert("비밀번호 확인을 체크하세요!!");
		document.signInform.repwd.focus();
		return false;	// 멈추기
	}
	else if(document.signInform.pwd.value != document.signInform.repwd.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.signInform.repwd.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.name.value) {
		alert("이름을 입력하세요!!");
		document.signInform.name.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.jumin1.value) {
		alert("주민번호를 입력하세요!!");
		document.signInform.jumin1.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.jumin2.value) {
		alert("주민번호 뒷자리를 입력하세요!!");
		document.signInform.jumin2.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.email1.value) {
		alert("이메일을 입력하세요!!");
		document.signInform.email1.focus();
		return false;	// 멈추기
	}
	// email2 값 null && 직접입력 => "이메일 형식에 일치하지 않습니다.!!" 출력, email2로 이동
	else if(document.signInform.email2.value == null || document.signInform.email2.value == 0) {
		alert("이메일 형식에 일치하지 않습니다.!!");
		document.signInform.email2.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.zipcode.value) {
		alert("우편번호를 입력하세요.!!");
		document.signInform.zipcode.focus();
		return false;	// 멈추기
	}
	else if(!document.signInform.address.value) {
		alert("주소를 입력하세요.!!");
		document.signInform.address.focus();
		return false;	// 멈추기
	}
	// 2. 중복확인 버튼을 클릭하지 않는 경우
	// 중복확인 버튼 클릭여부 체크(0:클릭안함, 1:클릭함 ) => signIn.jsp = hiddenId
	// 먼저, <input type = "hidden" name = "hiddenId" value = "0">
	else if(document.signInform.hiddenId.value == "0") {
		alert("중복확인을 해주세요!!");
		document.signInform.dupChk.focus();
		return false;
	}
}

//-----------------------------------------------------------------  

// 자동 입력항목(input) 포커스 이동
function nextJumin1() {
	if(document.signInform.jumin1.value.length >= 6) {
		document.signInform.jumin2.focus();
	}
}
function nextJumin2() {
	if(document.signInform.jumin2.value.length >= 7) {
		document.signInform.hp1.focus();
	}
}
function nextHp1() {
	if(document.signInform.hp1.value.length >= 3) {
		document.signInform.hp2.focus();
	}
}
function nextHp2() {
	if(document.signInform.hp2.value.length >= 4) {
		document.signInform.hp3.focus();
	}
}
function nextHp3() {
	if(document.signInform.hp3.value.length >= 4) {
		document.signInform.email1.focus();
	}
}
function nextAddress() {
	if(document.signInform.zipcode.value.length >= 5) {
		document.signInform.address.focus();
	}
}


// 이메일 선택
function selectEmailChk() {
	// 직접 입력 - email2 초기화 
	if(document.signInform.email3.value == 0) {	// 자바스크립트에선 equals를 쓸 일이 없다.
		document.signInform.email2.value = "";
		document.signInform.email2.focus();
	}
	// 선택값 입력
	else {
		//직접 입력이 아닌경우 select box의 값(email3)을 email2의 값으로 설정
		document.signInform.email2.value = document.signInform.email3.value;
	}
}

//-- 회원가입 페이지(중복확인)
// 1. 중복확인 버튼 클릭 시 서브창 open
function confirmId() {
	// id값 미입력시
	if(!document.signInform.id.value) {
		alert("아이디를 입력하세요!!");
		document.signInform.id.focus();
		return false;
	}
	
	var url = "confirmId.do?id=" + document.signInform.id.value;
	window.open(url, "confirm", "menubar=no, width=400, height=300");
	
}

// 3. 중복확인창 포커스
function confirmIdFocus() {
	document.confirmform.id.focus();
}

// 중복확인창에서 id 입력 여부
function confirmIdCheck() {
	// id값 미입력시
	if(!document.confirmform.id.value) {
		alert("아이디를 입력하세요!!");
		document.confirmform.id.focus();
		return false;	// 멈추기
	}
}
// 부모창으로 값 전달
/* 
 * opener : window 객체의 open() 메서드로 열린 새 창(=중복확인창)에서 
 * 			열어준 부모창(=회원가입창)에 접근할 때 사용
 * self.close(); 메시지 없이 현재창을 닫을 때 사용
 * hiddenId : 중복확인 버튼 클릭 여부(0:클릭안함, 1:클릭함 )
 */
function setId(id) {
	opener.document.signInform.id.value = id;
	opener.document.signInform.hiddenId.value = 1;
	self.close();
}

// ------- 인증(회원탈퇴, 수정) -----------------

// 회원탈퇴 - 인증처리 페이지
function passwdFocus() {
	document.passwdform.pwd.focus();
}
// 일반 비밀번호 인증
function passwdCheck() {
	if(!document.passwdform.pwd.value) {
		alert("비밀번호를 입력하세요!!");
		document.passwdform.pwd.focus();
		return false;	// 멈추기
	}
}
// 회원정보 수정 인증
function modPasswdCheck() {
	if(!document.mod_passwdform.pwd.value) {
		alert("비밀번호를 입력하세요!!");
		document.passwdform.pwd.focus();
		return false;	// 멈추기
	}
}

function loginCheck() {
	if(!document.loginform.input_id.value) {
		alert("아이디를 입력하세요!!");
		document.loginform.input_id.focus();
		return false;	// 멈추기
	}
	
	if(!document.loginform.input_pwd.value) {
		alert("비밀번호를 입력하세요!!");
		document.loginform.input_pwd.focus();
		return false;	// 멈추기
	}
}

function modifyFormFocus() {
	document.modifyform.mod_pwd.focus();
}

//유효성 체크
function modifyformCheck() {
	if(!document.modifyform.mod_pwd.value) {
		alert("비밀번호를 입력하세요!!");
		document.modifyform.mod_pwd.focus();
		return false;	// 멈추기
	}
	else if(!document.modifyform.mod_rePwd.value) {
		alert("비밀번호 확인을 체크하세요!!");
		document.modifyform.mod_rePwd.focus();
		return false;	// 멈추기
	}
	else if(document.modifyform.mod_pwd.value != document.modifyform.mod_rePwd.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.modifyform.mod_rePwd.focus();
		return false;	// 멈추기
	}
	else if(!document.modifyform.mod_name.value) {
		alert("이름을 입력하세요!!");
		document.modifyform.mod_name.focus();
		return false;	// 멈추기
	}
	else if(!document.modifyform.mod_email1.value) {
		alert("이메일을 입력하세요!!");
		document.modifyform.mod_email1.focus();
		return false;	// 멈추기
	}
	
	else if(!document.modifyform.mod_zipcode.value) {
		alert("우편번호를 입력하세요!!");
		document.modifyform.mod_zipcode.focus();
		return false;	// 멈추기
	}
	
	// email2 값 null && 직접입력 => "이메일 형식에 일치하지 않습니다.!!" 출력, email2로 이동
	else if(document.modifyform.mod_email2.value == null || document.modifyform.mod_email2.value == 0) {
		alert("이메일 형식에 일치하지 않습니다.!!");
		document.modifyform.mod_email2.focus();
		return false;	// 멈추기
	}
	else if(document.modifyform.mod_address.value == null || document.modifyform.mod_email2.value == 0) {
		alert("주소를 입력하세요.!!");
		document.modifyform.mod_address.focus();
		return false;	// 멈추기
	}
	
}

//자동 입력항목(input) 포커스 이동
function udtNextHp1() {
	if(document.modifyform.mod_hp1.value.length >= 3) {
		document.modifyform.mod_hp2.value = "";
		document.modifyform.mod_hp2.focus();
	}
}
function udtNextHp2() {
	if(document.modifyform.mod_hp2.value.length >= 4) {
		document.modifyform.mod_hp3.value = "";
		document.modifyform.mod_hp3.focus();
	}
}
function udtNextHp3() {
	if(document.modifyform.mod_hp3.value.length >= 4) {
		document.modifyform.mod_email1.focus();
	}
}

//이메일 선택
function udtSelectEmailChk() {
	// 직접 입력 - email2 초기화 
	if(document.modifyform.mod_email3.value == 0) {
		document.modifyform.mod_email2.value = "";
		document.modifyform.mod_email2.focus();
	}
	// 선택값 입력
	else {
		//직접 입력이 아닌경우 select box의 값(email3)을 email2의 값으로 설정
		document.modifyform.mod_email2.value = document.modifyform.mod_email3.value;
	}
}

//메뉴 한글로 변하기
function mover1() {
	document.getElementById("news").innerText = "뉴스";
}
function mover2() {
	document.getElementById("shopping").innerText = "쇼핑";
}
function mover3() {
	document.getElementById("board").innerText = "게시판";
}
function mover4() {
	document.getElementById("login").innerText = "접속하기";
}
function mover5() {
	document.getElementById("join").innerText = "가입하기";
}
// 메뉴 영어로 돌아오기
function mout1() {
	document.getElementById("news").innerText = "News";
}
function mout2() {
	document.getElementById("shopping").innerText = "Shopping";
}
function mout3() {
		document.getElementById("board").innerText = "Board";
}
function mout4() {
	document.getElementById("login").innerText = "Log-in";
}
function mout5() {
	document.getElementById("join").innerText = "Join";
}