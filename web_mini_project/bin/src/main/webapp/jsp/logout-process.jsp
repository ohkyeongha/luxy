<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String userId = (String)session.getAttribute("userId");
	if(userId != null){
		//로그인 상태 - 세션 종료
		session.removeAttribute("userId");
		response.sendRedirect("login.jsp");
	} else {
		response.sendRedirect("login.jsp");
	}
%>
