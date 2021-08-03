<%@page import="com.kitri.miniboard.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDAO bdao = new BoardDAO();
	String bno = request.getParameter("bno");
	
	int result = bdao.deleteBoard(Integer.parseInt(bno));
	
	if(result != 0){
		//삭제 성공
		response.sendRedirect("board.jsp");
	} else {
		//삭제 실패
		response.sendRedirect("board-detail.jsp?bno="+bno);
	}
%>