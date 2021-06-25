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
</head>
<body>
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide"><b>WelCome!</b></h3>
  </div>
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
	<a href="myPage.mem" class="w3-bar-item w3-button">마이 페이지</a>
    <a href="goodsCartList.gd" class="w3-bar-item w3-button">장바구니</a>
    <a href="purchaseCon.gd" class="w3-bar-item w3-button">주문 확인</a>
  </div>
  <a href="logout.sm" class="w3-bar-item w3-button w3-padding">로그아웃</a> 
</nav>
<hr>
<div class="w3-main" style="margin-left:250px">
<form action="reviewWrite.gd" method="post" enctype="multipart/form-data">
<input type="hidden" name="prodNum" value="${prodNum }" />
<input type="hidden" name="purchaseNum" value="${purchaseNum }" />
<table border="1">
	<tr><td>리뷰 작성</td>
		<td><textarea rows="5" cols="30" name="reviewContent"></textarea></td></tr>
	<tr><td>파일</td>
		<td><input type="file" name="reviewImg" class="w3-bar-item w3-button w3-padding" /></td></tr>
	<tr><td colspan="2"><input type="submit" value="리뷰 등록" class="w3-button w3-black w3-padding"/></td></tr>
</table>
</form>
</div>
</body>
</html>