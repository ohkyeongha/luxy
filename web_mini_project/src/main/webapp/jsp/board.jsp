<%@page import="com.kitri.miniboard.board.Pagination"%>
<%@page import="com.kitri.miniboard.board.BoardVO"%>
<%@page import="com.kitri.miniboard.board.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	BoardDAO bdao = new BoardDAO();
// 	List<BoardVO> blist = bdao.selectBoardList();
	
	String curPage = request.getParameter("curpage");
	if(curPage == null){
		curPage="1";
	}
	int curPageInt = Integer.parseInt(curPage);
	int totalContent = bdao.selectBoardCnt();
	int contentCnt = 5;
	Pagination pagination = new Pagination(curPageInt, totalContent, contentCnt);
	
	//한 페이지 내에 보여줘야 하는 게시물의 첫번째 rownum
	int start = (curPageInt*contentCnt) - (contentCnt -1);
	//한 페이지 내에 보여줘야 하는 게시물의 마지막 rownum
	int end = curPageInt*contentCnt;

	List<BoardVO> blist = bdao.selectBoardPage(start, end);
	
%>
<!DOCTYPE html>

<c:set var="page" value="<%=pagination %>"/>
<c:set var="blist" value="<%=blist %>"/>

<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link rel="stylesheet" href="../css/board-style.css">
</head>
<body>
	<header>
<%--		<% 
		String userId = (String)session.getAttribute("userId");
		String se ="";
		String notice="";
		if(userId != null){
			se="로그아웃";
			notice = "<span>" + userId + "</span> 님 반갑습니다.";
		}else {
			se="로그인";
			notice = "로그인 해주세요.";
		}
		%>
			<div id="hBtn"><a href="logout-process.jsp"><%=se %></a></div><label><%=notice %></label>
		<h1>게시판</h1>
		
		<% 
		if(userId != null){
		%>
			<div id="hBtn"><a href = "board-write.jsp"> 글 등록 </a></div>
		<%
		}
		%> --%>
		
		
		<c:choose>
			<c:when test="${!empty sessionScope.userId }">
				<div id="hBtn"><a href="logout-process.jsp">로그아웃</a></div><label><span>${userId }</span> 님 반갑습니다.</label>
			</c:when>
			<c:otherwise>
				<div id="hBtn"><a href="login.jsp">로그인</a></div><label>로그인 해주세요</label>
			</c:otherwise>
		</c:choose>
			
		<h1>게시판</h1>
		
		<c:choose>
			<c:when test="${!empty sessionScope.userId }">
				<div id="hBtn"><a href = "board-write.jsp"> 글 등록 </a></div>
			</c:when>
		</c:choose>
		
	</header>
	<table>
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<%--
			for(BoardVO bvo: blist){
				out.print("<tr>");
				out.print("	<td>" + bvo.getBno() + "</td>");
				out.print("	<td><a href='board-detail.jsp?bno="+ bvo.getBno()+ "'>" + bvo.getbTitle() + "</a></td>");
				out.print("	<td>" + bvo.getbWriter() + "</td>");
				out.print("	<td>" + bvo.getbRegDate() + "</td>");
				out.print("</tr>");
			}
			--%> 
		
		<c:forEach var="bvo" items="${blist }">
			<tr>
				<td>${bvo.bno }</td>
				<td><a href="board-detail.jsp?bno=${bvo.bno }">${bvo.bTitle }</a></td>
				<td>${bvo.bWriter }</td>
				<td>${bvo.bRegDate }</td>
			</tr>	
		
		</c:forEach>
		</tbody>
		
	</table>
	<!-- 페이지 개수 -->
	<div id="page-box">
		<ul>
			<c:if test="${page.prevBtn }">
				<li id="idBtn"><a href = "board.jsp?curpage=${page.startPage-1 }">이전</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
				<c:choose>
					<c:when test="${i eq param.curpage }"><li id='here'>${i }</li></c:when>
					<c:otherwise><li><a href = "board.jsp?curpage=${i }">${i }</a></li></c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${page.nextBtn }">
				<li id="idBtn"><a href = "board.jsp?curpage=${page.endPage+1 }">다음</a></li>
			</c:if>
			<%--
				if(pagination.isPrevBtn()){
					int prevPage = pagination.getStartPage()-1;
					out.print("<li id='idBtn'><a href = 'board.jsp?curpage=" + prevPage +"'>이전<a/></li>");
				}
			
				for(int i=pagination.getStartPage() ; i <= pagination.getEndPage(); i++){
					if(i==curPageInt){
						out.print("<li id='here'>" + i + "</li>");
					} else {
						out.print("<li><a href = 'board.jsp?curpage=" + + i + "'>" 
								+ i + "</a></li>");
					}
				}
				
				if(pagination.isNextBtn()){
					int nextPage = pagination.getEndPage()+1;
					out.print("<li id='idBtn'><a href ='board.jsp?curpage=" + nextPage +"'>다음<a/></li>");
				}
			--%>
			
		</ul>
	</div>
</body>
</html>