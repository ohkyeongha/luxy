window.onload = function(){
//	var msg ='${msg}';
	var msg = document.getElementById("msg").value;
	if(msg != ""){
		alert(msg);
	}
	
	var signup = document.getElementById("signupForm");
	signup.onsubmit = function(){
		//회원가입 버튼 클릭 동작
		var pw = document.getElementById("join-pass");
		var pwOk = document.getElementById("join-pass-ok");
		
		if(pw.value == pwOk.value){
// 			document.getElementById("signupForm");
			document.signupForm.action = "signup-process.jsp";
			document.signupForm.submit();
		} else {
			alert("비밀번호가 동일하지 않습니다.");
			return;
		}
	}
}