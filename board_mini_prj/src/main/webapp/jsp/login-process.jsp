<%@page import="com.kitri.miniboard.member.MemberDAO"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

   String userId = request.getParameter("userId");
   String userPw = request.getParameter("userPw");
   String idcheck = request.getParameter("idcheck");
   
   if(idcheck==null){
      idcheck="N";
   }
   
   MemberDAO mdao = new MemberDAO();
   String resultUserId = mdao.selectLogin(userId, userPw);
   
   
   Cookie cookie = new Cookie("idcheck",userId);
   
   if(resultUserId != null){
	   session.setAttribute("userId", userId);
      
      //쿠키 확인
      if(idcheck.equals("Y")){
         cookie.setMaxAge(60*60*24);//1일
         cookie.setPath("/");
      
      }else{//N이 들어왔을때
         cookie.setMaxAge(0);
         cookie.setPath("/");
      }
      response.addCookie(cookie);
      response.sendRedirect("board.jsp");
      
   }else{
      //로그인실패
      //로그인 실패 문구 필요
      String msg = "아이디 또는 비밀번호를 확인해주세요.";
      String encodeMsg = URLEncoder.encode(msg,"utf-8");
      response.sendRedirect("login.jsp?msg=" + encodeMsg);
   }
   
   
   
   
   
%>