<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
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
<form action="pwChangeOk" name="frm" method="post">
	비밀번호 : <input type="password" name="memPw" />
	<span>${pwFail1}</span><br />
	<input type="submit" value="확인" />
</form>
</body>
</html>
