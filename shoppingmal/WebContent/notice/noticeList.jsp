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
공지사항 리스트 페이지입니다.
<table border=1>
	<tr><td>게시글 번호</td><td>말머리</td><td>제목</td><td>작성일</td><td>조회수</td></tr>
	<c:forEach items="${lists }" var="dto">
		<tr><td><a href="noticeDetail.nc?noticeNo=${dto.noticeNo }">${dto.noticeNo }</a></td>
			<td>${dto.noticeKind }</td>
			<td>${dto.noticeSub }</td>
			<td>${dto.noticeDate }</td>
			<td>${dto.noticeCount }</td></tr>
	</c:forEach>
</table>
<a href="noticeRegest.nc">공지사항 등록</a>
</body>
</html>