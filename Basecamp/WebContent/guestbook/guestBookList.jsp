<%@ page import = "servlets.guestbook.model.GuestBook" %>
<%@ page import = "java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Bola's guestBook!</title>
</head>
<body>

<!-- Header.jsp에 헤더 그리는 데 필요하는 정보가 있다. -->
<jsp:include page="Header.jsp"/>
<p><a href='add'>새 글 쓰기</a></p>
<%
	ArrayList<GuestBook> guestbooks = (ArrayList<GuestBook>) request.getAttribute("guestBooks");
for(GuestBook g : guestbooks) {
%>
<%=g.getG_id()%>,
<a href ='update?gid=<%=g.getG_id()%>'><%=g.getG_contents()%></a>,
<%=g.getG_mail()%>,
<%=g.getCre_date()%>
<a href = 'delete?gid=<%=g.getG_id()%>'>[삭제]</a><br>
<% } %>
</body>
</html>