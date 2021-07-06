<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;                
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }
                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
</head>
<body>
<form:form action="memJoin" method="post" name="frm" modelAttribute="memberCommand"> 
	<table border = 1 align="center">
		<tr><td>아이디</td>
			<td><form:input path="memId" />
				<form:errors path="memId"/>
			</td></tr>
		<tr><td>비밀번호</td>
			<td><form:password path="memPw" />
			<form:errors path="memPw"/></td></tr>
		<tr><td>비밀번호 확인</td>
			<td><form:password path="memPwCon" />
			<form:errors path="memPwCon"/></td></tr>
		<tr><td>이름</td>
			<td><form:input path="memName" />
			<form:errors path="memName"/></td></tr>
		<tr><td>우편번호</td>
			<td><form:input path="postNumber" id="sample4_postcode" readonly="readonly" />
			<form:errors path="postNumber"/>
			</td></tr>
		<tr><td>주소</td>
			<td><form:input path="memAddress" id="sample4_roadAddress" 
			    size="30" readonly="readonly" />
				<a href="javascript:sample4_execDaumPostcode();">주소 검색</a>
			<form:errors path="memAddress"/>
			</td></tr>
		<tr><td>상세주소</td>
			<td><form:input path="detailAdd" />
			<form:errors path="detailAdd"/></td></tr>
		<tr><td>연락처</td>
			<td><form:input path="memPhone" />
			<form:errors path="memPhone"/></td></tr>
			
		<tr><td>이메일</td>
			<td><form:input path="memEmail" />
			<form:errors path="memEmail"/></td></tr>
		<tr><td>생년월일</td>
			<td><input type="date" name="memBirth" />
			<form:errors path="memBirth"/>
			</td></tr>
		<tr><td>성별</td>
			<td><form:radiobutton path="memGender"  value="M" checked="checked" />남자
				<form:radiobutton path="memGender" value="F" />여자
				<form:errors path="memGender"/></td></tr>
		<tr><td>계좌번호</td>
			<td><form:input path="memAccount" /></td></tr>
		<tr><td>이메일 수신여부</td>
			<td><input type="radio" name="memEmailCk" value="Y" checked />예
			<input type="radio" name="memEmailCk" value="N" />아니오
			<form:errors path="memEmailCk"/></td></tr>
		<tr><td colspan="2" align="center">
				<input type="submit" value="가입 완료" />
				<input type="reset" value="취소" />
				<input type="button"  value="가입 안함" 
					onclick="javascript:location.href='main'" />
			</td></tr>
	</table>
</form:form>
</body>
</html>