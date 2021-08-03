<%@page import="com.kitri.miniboard.board.BoardVO"%>
<%@page import="com.kitri.miniboard.board.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//게시판 모든 값 조회
	BoardDAO bdao = new BoardDAO();
	List<BoardVO> blist = bdao.selectBoardList();
	
	request.setAttribute("blist", blist);
	
	RequestDispatcher rd = request.getRequestDispatcher("board.jsp");
	rd.forward(request, response);
%>