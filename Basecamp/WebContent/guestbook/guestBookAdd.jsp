<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 게시글 쓰기</title>
<script  src="http://code.jquery.com/jquery-1.11.3.min.js"></script>


<!-- 클라이언트에서 email 형식을 체크한다. -->
<!-- 내 이 번주 안에 자바스크립트를 배우고 만다. -_- -->
<!-- 주의!!! 만약 form의 submit관련 함수를 작성하고 있다면 절대적으로 조심! 에러가 나면 그냥 submit 되어 버린다. -->
<script type="text/javascript">

	function check() {		
		var email = document.getElementById("email").value;

		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

		if(exptext.test(email) == false){

			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			

			alert("이 메일형식이 올바르지 않습니다.");
			return false;
		}

		return true;
	}
</script>
</head>
<body>


<h1>새 게시글 쓰기</h1>
<form action='add' onsubmit="return check();" method='post'>
<label for="contents">게시글 내용</label> <br> <textarea rows='20' cols='50' name='content'></textarea><br>
<label for='email'>이메일</label> <input type='text' id='email' name='email'><br>
<label for='password'>비밀번호</label> <input type='password' name='password'><br>
<input type='submit' value='제출' class='btn_submit'>
<input type='reset' value='취소'>
</form>


</body>
</html>