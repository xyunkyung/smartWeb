<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="noticeModify.nc" method="post" name="frm">
<input type="hidden" name="noticeNo" value="${dto.noticeNo }" />
<table border="1" align="center">
	<tr><td>게시글 번호</td>
		<td>${dto.noticeNo }</td></tr>
	<tr><td>말머리</td>
		<td>${dto.noticeKind }</td></tr>
	<tr><td>게시글 제목</td>
		<td><input type="text" name="noticeSub" value="${dto.noticeSub }" /></td></tr>
	<tr><td>게시글 내용</td>
		<td><textarea rows="6" cols="50" name="noticeCon">${dto.noticeCon }</textarea></td></tr>
	<tr><td>작성일</td>
		<td>${dto.noticeDate }</td></tr>
	<tr><td>파일 첨부</td>
		<td><input type="file" name="noticeFile" value="${dto.noticeFile }" /></td></tr>
	<tr><td>조회수</td>
		<td>${dto.noticeCount }</td></tr>
	<tr><td>작성자</td>
		<td>${dto.employeeId }</td></tr>
	<tr><th colspan="2">
			<input type="submit" value="공지사항 수정" />
			<input type="button" value="취소" onclick="javascript:history.back();" />
			<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'" />
			</th></tr>
</table>
</form>
</body>
</html>