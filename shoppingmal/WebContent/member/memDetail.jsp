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

<div class="w3-row w3-grayscale">
    <div class="w3-col l3 s6">
      <div class="w3-container">
      	<b>${dto.memName }님의 상세 정보</b>
        <p>아이디 : ${dto.memId }<br></p>
        <p>이름 : ${dto.memName }<br></p>
        <p>생년월일 : ${dto.memBirth }<br></p>
        <p>성별 : ${dto.memGender }<br></p>
        <p>우편번호 : ${dto.postNumber }<br></p>
        <p>주소 : ${dto.memAddress }<br></p>
        <p>연락처 : ${dto.memPhone }<br></p>
        <p>이메일 : ${dto.memEmail }<br></p>
        <p>계좌번호 : ${dto.memAccount }<br></p>
        <p>이메일 수신 여부 : <c:if test="${dto.memEmailCk == 'Y' }">동의</c:if>
			  <c:if test="${dto.memEmailCk == 'N' }">비동의</c:if><br></p>
      </div>
    </div>
</div>

<p><a href="memSujung.mem" class="w3-button w3-black w3-padding">수정</a></p>
</div>
</body>
</html>