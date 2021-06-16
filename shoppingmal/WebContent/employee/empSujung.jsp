<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="empSujungOk.em" method="get" name="frm">
<table border = 1 align="center">
	<tr><td>사원번호</td>
		<td>                               				
			${dto.employeeId }
		</td></tr>
	<tr><td>사원아이디</td>
		<td>
			${dto.empUserid }
		</td></tr>
	<tr><td>비밀번호</td>
		<td><input type="password" name="empPw" />
				<span>${pwFail }</span>
		</td></tr>
	<tr><td>이름</td>
		<td>
			${dto.empName }
		</td></tr>
	<tr><td>입사일</td>
		<td>
			${dto.hireDate }
		</td></tr>
	<tr><td>직무</td>
		<td>
			${dto.jobId }
		</td></tr>
	<tr><td>연락처</td>
		<td>
			<input type="text" name="phNumber" 
					placeholder="010-1234-1234" value="${dto.phNumber }" />
		</td></tr>
	<tr><td>사무실번호</td>
		<td>
			<input type="text" name="officeNumber" 
					placeholder="02-1234-1234" value="${dto.officeNumber }" />
		</td></tr>
	<tr><td>이메일</td>
		<td>
			<input type="text" name="email" value="${dto.email }" />
		</td></tr>
	<tr><td>사무실 주소</td>
		<td>
			<input type="text" name="empAddress" value="${dto.empAddress }" />
		</td></tr>
	<tr><td colspan="2" align="center">
			<input type="submit" value="수정" />
			<input type="button" value="취소" onclick="javascript:history.back();" />
		</td></tr>
</table>
</form>
</body>
</html>