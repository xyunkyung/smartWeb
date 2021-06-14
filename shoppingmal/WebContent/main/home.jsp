<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 로그인 안되어있을 때 -->
	<table>
		<tr><td colspan="3">아이디 저장 | 자동 로그인</td></tr>
		<tr><td>아이디</td>
			<td><input type="text" name="userId" /></td>
			<td rowspan="2">
				<input type="image" src="" alt="login" />
			</td></tr>
		<tr><td>비밀번호</td>
			<td><input type="password" name="userPw" /></td></tr>
		<tr><td colspan="3">
				<a href="#">아이디</a>/<a href="#">비밀번호 찾기</a> | 
				<a href="memAgree.mem">회원가입</a>
			</td></tr>
		<tr><td></td><td></td><td></td></tr>
	</table>
	<!--  로그인 되어있을 때 -->
	<!-- 일반 회원 -->
	
	<!-- 직원 -->
	
	<!-- 관리자 -->
	<a href="empList.em">직원 리스트</a>
</body>
</html>