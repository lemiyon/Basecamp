<%@ page import= "servlets.guestbook.model.GuestBook" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 상세 보기</title>
<script  src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

</head>
<body>

<h1>게시글 상세 보기</h1>
<form name="formUpdateGuestbook" action = 'update' method='post'>
<%
	GuestBook g = (GuestBook)request.getAttribute("guestbook");
	if(g != null) {
%>

<!-- 자바스크립트로  맞는 암호 입력 시 통과, 아닐 시 -->
<script type="text/javascript">


	function checkPassword() {
		var msg = "비밀번호를 입력해 주세요!";
		var pwd = "<%= g.getG_pwd() %>";	
		//게시글 작성 때, 비밀번호가 입력되었을 때만 체크한다.
		if (pwd != null && pwd != ""){
			
			//사용자가 프롬프트(팝업창)에 입력한 입력값을 result에 갖고 온다.
			var result = prompt(msg);
			
			//비밀번호가 틀릴 경우
			if(result != pwd) {
				alert("비밀번호가 올바르지 않습니다.");
				return;
			}
			//비밀 번호가 맞을 경우
			else {
				
				//1. 수정버튼을 가리고, 저장 버튼을 보인다.
				document.getElementById("btn_update").style.display = "none";
				document.getElementById("btn_save").style.display = "";
				//2. 내용 입력용 textarea를 활성화 시킨다.
				document.getElementById("contents").readOnly = false;
				document.getElementById("contents").focus();
			}	
		}
		//비밀 번호가 없을 때도, 글을 수정할 수 있게 해 준다.
		else {
			
			//1. 수정버튼을 가리고, 저장 버튼을 보인다.
			document.getElementById("btn_update").style.display = "none";
			document.getElementById("btn_save").style.display = "";
			//2. 내용 입력용 textarea를 활성화 시킨다.
			document.getElementById("contents").readOnly = false;
			document.getElementById("contents").focus();
		}	
	}
	
</script>


<br>
<input type='text' name='id' value='<%= g.getG_id() %>' readonly><br>
<input type='text' name='email' value='<%= g.getG_mail() %>' readonly><br>
<textarea rows='20' cols='50' name='contents' id = 'contents' readonly><%= g.getG_contents()%></textarea><br>
<input type='text' value = '<%=g.getCre_date()%>' readonly><br>
<br>
<input type="button" id="btn_update" value="수정" onclick="checkPassword();" >
<input type='submit' id="btn_save" value='저장' style="display:none">
<input type='button' value='삭제' onclick="location:href='delete?gid=<%=g.getG_id()%>';">
<input type='button' value='취소' onclick="location:href='list';">
<%} %>
</form>

</body>
</html>