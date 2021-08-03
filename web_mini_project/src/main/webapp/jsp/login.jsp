<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   String userId = "";
   String idChecked = "";
   
//    String msg = request.getParameter("msg");
//    if(msg == null){
// 	   msg = "";
//    }
   
   Cookie[] cookies = request.getCookies();
   if (cookies != null) {
      for (Cookie cookie : cookies) {
         if (cookie.getName().equals("idCheck")) {
            userId = cookie.getValue();
            idChecked = "checked";
         }
      }
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/login-style.css">
<script type="text/javascript" src = "../js/login-script.js"></script>
</head>
<body>
	<div class="login-wrap">
      <div class="login-html">
         <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
         <label for="tab-1" class="tab">Sign In</label> <input id="tab-2"
            type="radio" name="tab" class="sign-up"><label for="tab-2"
            class="tab">Sign Up</label>
         <div class="login-form">
         	<input type="hidden" id="msg" value="${msg }">
            <form action="login-process.jsp" method="post">
	            <div class="sign-in-htm">
	               <div class="group"> <!-- id 입력창 -->
	                  <label for="user" class="label">Username</label> 
	                  <input id="user" type="text" class="input" name="userId" value="<%=userId  %>" required>
	               </div>
	               
	               <div class="group"><!-- pw 입력창 -->
	                  <label for="pass" class="label">Password</label>
	                  <input id="pass" type="password" class="input" data-type="password" name="userPw" required>
	               </div>
	               
	               <div class="group"><!-- id 기억 -->
	                  <input id="check" type="checkbox" class="check" name="idcheck" checked value="Y"> 
	                  <label for="check"><span class="icon"></span> Keep me Signed in</label>
	               </div>
	               
	               <div class="group">
	                  <input type="submit" class="button" value="Sign In">
	               </div>
	               
	               <div class="hr"></div>
	               <div class="foot-lnk">
	                  <a href="#forgot">Forgot Password?</a>
	               </div>
	            </div>
            </form>
            <form action="" method="post" name = "signupForm" id="signupForm">
		        <div class="sign-up-htm">
		               <div class="group">	<!-- id입력 -->
		                  <label for="join-user" class="label">Username</label> 
		                  <input id="join-user" type="text" class="input" name = "join-userId" required>
		               </div>
		               <div class="group"> <!-- pw입력 -->
		                  <label for="join-pass" class="label">Password</label>
		                  <input id="join-pass" type="password" class="input" data-type="password" name = "join-userPw" required>
		               </div>
		               <div class="group"> <!-- pw확인 입력 -->
		                  <label for="join-pass-ok" class="label">Repeat Password</label> 
		                  <input id="join-pass-ok" type="password" class="input" data-type="password" required>
		               </div>
		               <div class="group"> <!-- email입력 -->
		                  <label for="join-email" class="label">Email Address</label> 
		                  <input id="join-email" type="text" class="input" name = "join-email" required>
		               </div>
		               <div class="group">
		                  <input type="submit" id="signup-btn" class="button" value="Sign Up">
		               </div>
		            <div class="hr"></div>
		            <div class="foot-lnk">
		               <label for="tab-1">Already Member?</a>
		            </div>
		        </div>
            </form>
         </div>
      </div>
   </div>


</body>
</html>

