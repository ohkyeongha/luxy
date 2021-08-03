<%@page import="java.net.URLEncoder"%>
<%@page import="com.kitri.miniboard.board.BoardVO"%>
<%@page import="com.kitri.miniboard.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	//update하기 위함
	BoardDAO bdao = new BoardDAO();
	
	String bno = request.getParameter("bno");
	String bTitle = request.getParameter("bTitle");
	String bContent = request.getParameter("bContent");
	
	BoardVO bvo = new BoardVO();
	bvo.setBno(Integer.parseInt(bno));
	bvo.setbTitle(bTitle);
	bvo.setbContent(bContent);
	
	int result = bdao.updateBoard(bvo);
	String msg ="";
	
	if(result != 0){	//정상적으로 업데이트
		msg="정상적으로 업데이트 되었습니다.";
		String encodeMsg = URLEncoder.encode(msg, "utf-8");
		response.sendRedirect("board-detail.jsp?bno="+ bno + "&msg=" + encodeMsg);
		
	} else {	//비정상 업데이트
		msg="업데이트 실패했습니다. 다시 시도해 주세요.";
		String encodeMsg = URLEncoder.encode(msg, "utf-8");
		response.sendRedirect("board-update.jsp?bno="+ bno + "&msg=" + encodeMsg);
	}
%>
