<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
 <%
 	String userId = (String)session.getAttribute("userId");
 	if(userId ==null){
 		response.sendRedirect("login.jsp");
 	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
<link rel="stylesheet" href="../css/board-write-style.css">
<script type="text/javascript" src="../js/board-write-script.js"></script>
</head>
<body>
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
	<form action="board-write-process.jsp" method="post" name ="boardWriteForm">
	<div id="writearea">
		<div id = "write-box">
			<div id = "title">
				<span class="label">제목</span>
				<input class="print-box" type="text" id ="bTitle" name="bTitle" required>
			</div>
			<div id = "writer" >
				<span class="label">작성자</span>
				<input class="print-box" type="text" readonly value="<%=userId %>">
			</div>
			<div id = "content">
				<span class="label">내용</span><br>
				<textarea class ="content-box" id="bContent" name="bContent" required></textarea>
			</div>
			<div id ="buttons">
				<input type="button" id="board-save-btn" value ="저장">
				<input type="button" id="board-cancel-btn" value ="취소">
			</div>
		</div>
	</div>
	</form>
</body>
</html>