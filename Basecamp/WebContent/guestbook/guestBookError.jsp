<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류 발생</title>
</head>
<body>
<h1>이런! 오류가 발생했군요</h1>
이하와 같은 오류가 발생했습니다. <br>
<%= request.getAttribute("error") %>
<br>
<br>
에러 내용과 함께 이 사실을 알려주신다면 더 빠른 조치해드리겠습니다. 감사합니다.
</body>
</html>