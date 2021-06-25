<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#btn").click(function() {
		if($($("#memPw")).val() == "") {
			alert("현재 비밀번호를 입력하세요.");
			$("#memPw").focus();
			return false;
		}
		if($($("#newPw")).val() == "") {
			alert("새로운 비밀번호를 입력하세요.");
			$("#newPw").focus();
			return false;
		} 
		if($($("#newPwCon")).val() == "") {
			alert("새로운 비밀번호를 한번 더 입력하세요.");
			$("#newPwCon").focus();
			return false;
		} else {
			if($("#newPw").val() != $("#newPwCon").val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$("#newPw").val("");
				$("#newPwCon").val("");
				$("#newPw").focus();
				return false;
			}
		}
		$("#frm").submit();
	});
});
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
<form action="ChangePw.mem" name="frm" method="post" id="frm">
<div class="w3-row w3-grayscale">
    <div class="w3-col l3 s6">
      <div class="w3-container">
		<p>현재 비밀번호 : <input type="password" name="memPw" id="memPw" /></p>
		<p>새 비밀번호 : <input type="password" name="newPw" id="newPw" /></p>
		<p>새 비밀번호 확인 : <input type="password" name="newPwCon" id="newPwCon" /></p>
		<input type="button" value="비밀번호 변경" id="btn" class="w3-button w3-black w3-padding" />
	  </div>
	</div>
</div>
</form>
</div>
</body>
</html>