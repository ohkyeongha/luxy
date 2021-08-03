<%@page import="com.kitri.miniboard.board.BoardDAO"%>
<%@page import="com.kitri.miniboard.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
//글 등록(insert)하기 위해 값 받아옴
String bTitle = request.getParameter("bTitle");
String bContent = request.getParameter("bContent");
String bWriter= (String)session.getAttribute("userId");//세션으로 bwriter값 끌고오기

BoardVO bvo = new BoardVO();

bvo.setbTitle(bTitle);
bvo.setbContent(bContent);
bvo.setbWriter(bWriter);

BoardDAO bdao = new BoardDAO();

//insert
int result = bdao.insertBoard(bvo);
String msg="";

if(result != 0){
   //정상적으로 insert
   
   %>
   
   <script>
   
   alert("글이 정상적으로 등록되었습니다.");
   location.href="board.jsp";
   </script>
   
   <%
   
   //board.jsp
   
   
}else{
   //insert 안됨
   
   %>
   <script>
   alert("글 등록이 실패하였습니다. 다시 작성해 주세요");
   location.href="board-write.jsp";
   </script>
   <%
   
   //board-write.jsp
}

%>
</body>
</html>