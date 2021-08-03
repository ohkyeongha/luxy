<%@page import="com.kitri.miniboard.member.MemberVO"%>
<%@page import="com.kitri.miniboard.member.MemberDAO"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("join-userId");
	String pw = request.getParameter("join-userPw");
	String email = request.getParameter("join-email");
	
	MemberDAO mdao = new MemberDAO();
	String resultUserId = mdao.selectUserId(id);
	
	String msg ="";
	//id 조회 성공 - 가입 가능
	if (resultUserId == null){
		//회원가입 - DB insert
		MemberVO mvo = new MemberVO();
		mvo.setUserId(id);
		mvo.setUserPw(pw);
		mvo.setEmail(email);
		
		int result = mdao.insertSignup(mvo);
		
		//결과가 1 - 성공
		if(result == 1){ 
			msg = "회원가입에 성공하였습니다";
		} 
		//결과가 0 - 실패
		else { 
			msg = "회원가입이 실패했습니다. 다시 시도 해주세요";
		}
		
		
	}
	//id 조회 실패 - 중복 ID로 가입 불가능
	else {
		//존재하는 ID입니다.
		msg = "존재하는 ID입니다.";
	}
	
	String encodeMsg = URLEncoder.encode(msg, "utf-8");
	response.sendRedirect("login.jsp?msg=" + encodeMsg);

%>