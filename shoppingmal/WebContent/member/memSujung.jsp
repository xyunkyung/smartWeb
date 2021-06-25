<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:83px"></div>
  
  <!-- Top header -->
  <header class="w3-container w3-xlarge">
    <a href="main.sm"><p class="w3-left">Another Hyun</p></a>
    <p class="w3-right">
    <a href="goodsCartList.gd">
      <i class="fa fa-shopping-cart w3-margin-right">
      </i></a>
      <i class="fa fa-search"></i>
    </p>
  </header>
  </div>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide"><b>마이 페이지</b></h3>
  </div>
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
	<a href="memDetail.mem" class="w3-bar-item w3-button">회원 정보 수정</a>
    <a href="memPwChange.mem" class="w3-bar-item w3-button">비밀번호 변경</a>
    <a href="memOut.mem" class="w3-bar-item w3-button">회원 탈퇴</a>
  </div>
  <a href="logout.sm" class="w3-bar-item w3-button w3-padding">로그아웃</a> 
</nav>
<hr>
<div class="w3-main" style="margin-left:250px">

<form action="memSujungOk.mem" method="post" name="frm">
 	<table>
		<tr><td>아이디</td>
			<td>${dto.memId }</td></tr>
		<tr><td>비밀번호</td>
			<td><input type="password" name="memPw" size="50" />
				<span>${pwFail }</span></td></tr>
		<tr><td>이름</td>
			<td>${dto.memName }</td></tr>
		<tr><td>우편번호</td>
			<td><input type="text" name="postNumber" id="sample4_postcode" value="${dto.postNumber }" size="50" ></td></tr>
		<tr><td>주소</td>
			<td><input type="text" name="memAddress" id="sample4_roadAddress" value="${dto.memAddress }" size="50">
				<a href="javascript:sample4_execDaumPostcode();">주소 검색</a></td></tr>
		<tr><td>상세 주소</td>
			<td><input type="text" name="detailAdd" value="${dto.detailAdd }" size="50" ></td></tr>
		<tr><td>연락처</td>
			<td><input type="text" name="memPhone" value="${dto.memPhone }" size="50"></td></tr>
		<tr><td>이메일</td>
			<td><input type="text" name="memEmail" value="${dto.memEmail }" size="50" ></td></tr>
		<tr><td>생년월일</td>
			<td><input type="text" name="memBirth" value="${dto.memBirth }" size="50"></td></tr>
		<tr><td>성별</td>
			<td><c:if test="${dto.memGender == 'M'}">남자</c:if>
				<c:if test="${dto.memGender == 'F'}">여자</c:if></td></tr>
		<tr><td>계좌번호</td>
			<td><input type="text" name="memAccount" value="${dto.memAccount }" size="50" ></td></tr>
		<tr><td>이메일 수신 여부</td>
			<td><input type="radio" name="memEmailCk" value="Y" 
				<c:if test="${dto.memEmailCk == 'Y'}">checked</c:if>>예
				<input type="radio" name="memEmailCk" value="N" 
				<c:if test="${dto.memEmailCk == 'N'}">checked</c:if>>아니요</td></tr>
		<tr><td colspan="2">
		<p>
			<input type="submit" class="w3-button w3-black w3-padding" value="수정" />
			<input type="button" class="w3-button w3-black w3-padding" value="수정 취소" onclick="javjascript:history.back();" />
			</td></tr>
	</table>
</form>
</div>
</body>
</html>