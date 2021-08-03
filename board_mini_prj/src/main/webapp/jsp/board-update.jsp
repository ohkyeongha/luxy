<%@page import="com.kitri.miniboard.board.BoardDAO"%>
<%@page import="com.kitri.miniboard.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 	BoardDAO bdao = new BoardDAO();
 	int bno = Integer.parseInt(request.getParameter("bno"));
 	BoardVO bvo = bdao.selectBoard(bno);
 	
 	String msg = request.getParameter("msg");
 	if(msg == null){
 		msg = "";
 	}
 	String userId =(String)session.getAttribute("userId");
 	if(userId == null){
 		userId = "";
 	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read Board</title>
<script type="text/javascript" src = "../js/board-update-script.js"></script>
<link rel="stylesheet" href="../css/board-detail-style.css">
</head>
<body>
	<input type="hidden" id = "msg" value ="<%=msg%>">
	<header>
		<c:choose>
			<c:when test="${!empty sessionScope.userId }">
				<div id="hBtn"><a href="logout-process.jsp">로그아웃</a></div><label><span>${userId }</span> 님 반갑습니다.</label>
			</c:when>
			<c:otherwise>
				<div id="hBtn"><a href="login.jsp">로그인</a></div><label>로그인 해주세요</label>
			</c:otherwise>
		</c:choose>
			
		<h1>자세히보기</h1>
		
		<c:choose>
			<c:when test="${!empty sessionScope.userId }">
				<div id="hBtn"><a href = "board.jsp"> 글 목록 </a></div>
			</c:when>
		</c:choose>
	</header>
	<form action="board-update-process.jsp" method="post" name ="boardWriteForm">
		<div id="writearea">
			<div id = "write-box">
				<div id = "no">
					<span class = "label">글 번호</span>
					<span class = "print-box"><%=bvo.getBno() %></span>
					<input type="hidden" name = "bno" value="<%=bvo.getBno() %>">
				</div>
				<div id = "title">
					<span class = "label">제목</span>
					<input type = "text" id= "bTitle" name = "bTitle" class = "print-box" value="<%=bvo.getbTitle() %>">
				</div>
				<div id = "writer" >
					<span class = "label">작성자</span>
					<span class = "print-box"><%=bvo.getbWriter() %></span>
				</div>
				<div id = "regdate" >
					<span class = "label">작성일자</span>
					<span class = "print-box"><%=bvo.getbRegDate() %></span>
				</div>
				<div id = "content">
					<span class = "label">내용</span><br>
					<textarea id= "bContent" name = "bContent" class ="content-box"><%=bvo.getbContent() %></textarea>
				</div>
				<!-- 수정/삭제 버튼 -->
				<div>
					<a href="#" id = "board-save-btn">저장</a>
					<a href="#" id = "board-cancel-btn">취소</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>