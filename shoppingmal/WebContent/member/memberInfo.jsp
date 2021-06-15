<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원정보 페이지 입니다.<br />
아이디 : ${dto.memId }<br />
이름 : ${dto.memName }<br />
생년월일 : ${dto.memBirth }<br />
성별 : ${dto.memGender }<br />
우편번호 : ${dto.postNumber }<br />
주소 : ${dto.memAddress }<br />
상세주소 : ${dto.detailAdd }<br />
연락처 : ${dto.memPhone }<br />
이메일 : ${dto.memEmail }<br />
계좌번호 : ${dto.memAccount }<br />
이메일 수신 여부 : <c:if test="${dto.memEmailCk == 'T' }">이메일 수신 동의</c:if>
			  <c:if test="${dto.memEmailCk == 'F' }">이메일 수신 비동의</c:if><br />
<a href="memMod.mem?memId=${dto.memId }">수정</a>
</body>
</html>