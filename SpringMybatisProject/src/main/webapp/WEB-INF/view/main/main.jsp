<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${empty authInfo }">
<!-- 로그인이 안된 경우 -->
<form:form action="login" method="post" name="frm" modelAttribute="loginCommand">
<table>
	<tr><td colspan="3">아이디 저장 | 자동 로그인</td></tr>
	<tr><td>아이디</td>
		<td><form:input path="userId" />
			<form:errors path="userId" /></td>
		<td rowspan="2">
		<input type="image" src="images/img1.jpg" width="100" alt="login"/></td></tr>
	<tr><td>비밀번호</td>
		<td><form:password path="userPw" />
			<form:errors path="userPw" /></td></tr>
	<tr><td colspan="3">아이디/비밀번호 찾기 | <a href="register/agree">회원 가입</a></td></tr>
</table>
</form:form>
</c:if>
<!-- 로그인이 되었을 때 -->
<c:if test="${!empty authInfo }">
	<!-- 일반 사용자 -->
	<c:if test="${authInfo.grade == 1 }">
	<a href="edit/myPage">마이 페이지</a>
	</c:if>
	<!-- 관리자 -->
	<c:if test="${authInfo.grade != 1 }">
	<a href="member/memList" >회원리스트</a>
	<a href="emp/empList">직원리스트</a>
	</c:if>
	<a href="login/logOut">로그아웃</a>
</c:if>
</body>
</html>