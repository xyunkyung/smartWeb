<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="noticeRegestOk.nc" method="get" name="frm" enctype="multipart/form-data">
<table border="1" align="center">
	<tr><td>게시글 번호</td>
		<td><input type="text" name="noticeNo" value="${noticeNo }" /></td></tr>
	<tr><td>게시글 제목</td>
		<td><input type="text" name="noticeSub" /></td></tr>
	<tr><td>게시글 내용</td>
		<td><textarea rows="6" cols="50" name="noticeCon"></textarea></td></tr>
	<tr><td>작성일</td>
		<td><input type="date" name="noticeDate" /></td></tr>
	<tr><td>말머리</td>
		<td><select name="noticeKind">
					<option value="guidance">안내</option>
					<option value="check">점검</option>
				</select></td></tr>
	<tr><td>파일 첨부</td>
		<td><input type="file" name="noticeFile" /></td></tr>
	<tr><td>조회수</td>
		<td><input type="number" name="noticeCount" /></td></tr>
	<tr><td>작성자</td>
		<td><input type="text" name="employeeId" value="${empId }" /></td></tr>
	<tr><th colspan="2">
			<input type="submit" value="공지사항 등록" />
			<input type="button" value="취소" onclick="javascript:history.back();" />
			<input type="button" value="홈으로" onclick="javascript:location.href='main.sm'" />
			</th></tr>
</table>
</form>
</body>
</html>