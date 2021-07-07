<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

*{
  color: black;
}
body {
  font-family: 'Noto Sans KR', sans-serif;
}
table{
  font-size: 15px/1;
  border: 1px solid;
  border-spacing: 8px;

</style>
<body>
<form:form action="changePw" name="frm" method="post" id = "frm" 
	modelAttribute="memberCommand">
현재 비밀번호 : <form:password path="oldPw" />
			<form:errors path="oldPw"/><br />
변경 비밀번호 : <input type="password" name="memPw" />
			<form:errors path="memPw"/><br />
변경 비밀번호 확인:<input type="password" name="memPwCon" />
			<form:errors path="memPwCon"/><br />
<input type="submit" value="비밀변호 변경" id="btn"/>
</form:form>
</body> 
</html>
