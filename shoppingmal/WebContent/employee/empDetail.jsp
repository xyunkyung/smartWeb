<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
나의 상세 정보<br />
사원번호 : ${dto.employeeId }<br />
아이디 : ${dto.empUserid }<br />
이름 : ${dto.empName }<br />
입사일 : ${dto.hireDate }<br />
직무 : ${dto.jobId }<br />
연락처 : ${dto.phNumber }<br />
사무실 번호 : ${dto.officeNumber }<br />
이메일 : ${dto.email }<br />
주소 : ${dto.empAddress }<br />
<a href="empSujung.em">수정</a>
</body>
</html>