<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <p class="w3-left">Another Hyun</p>
    <p class="w3-right">
      <i class="fa fa-shopping-cart w3-margin-right"></i>
      <i class="fa fa-search"></i>
    </p>
  </header>
  </div>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 로그인 안되어있을 때 -->
	<c:if test="${empty authInfo }">
	<!-- Footer -->
  <footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
    <div class="w3-row-padding">
      <div class="w3-col s4">
        <h4>Contact</h4>
        <p>Questions? Go ahead.</p>
        <form action="/action_page.php" target="_blank">
          <p><input class="w3-input w3-border" type="text" placeholder="Name" name="Name" required></p>
          <p><input class="w3-input w3-border" type="text" placeholder="Email" name="Email" required></p>
          <p><input class="w3-input w3-border" type="text" placeholder="Subject" name="Subject" required></p>
          <p><input class="w3-input w3-border" type="text" placeholder="Message" name="Message" required></p>
          <button type="submit" class="w3-button w3-block w3-black">Send</button>
        </form>
      </div>
      </div>
      </footer>
	<form action="login.sm" method="post" name=frm>
	<table border = "1">
		<tr><td colspan="3">
			<input type="checkbox" name="idStore" value="store" 
				<c:if test="${isId != null }">checked</c:if>
			/>아이디 저장 | 
			<input type="checkbox" name="autoLogin" value="auto" />
				자동 로그인</td></tr>
		<tr><td>아이디</td>
			<td><input type="text" name="userId" value="${isId }" /><span>${userFail }</span></td>
			<td rowspan="2">
				<input type="image" src="images/pngegg.png" width="60" alt="login" />
			</td></tr>
		<tr><td>비밀번호</td>
			<td><input type="password" name="userPw" /><span>${pwFail }</span></td></tr>
		<tr><td colspan="3">
				<a href="idSearch.mem">아이디</a>/<a href="#">비밀번호 찾기</a> | 
				<a href="memAgree.mem">회원가입</a>
			</td></tr>
	</table>
	</form>
	</c:if>
	<!--  로그인 되어있을 때 -->
	<c:if test="${!empty authInfo }">
	<!-- 일반 회원 -->
	<c:if test="${authInfo.grade == 1 }">
	<!-- Sidebar/menu -->
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
	</c:if>
	<!-- 직원 -->
	<c:if test="${authInfo.grade != 1 }">
	<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide"><b>Employee</b></h3>
  </div>
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
	<a href="empMyPage.em" class="w3-bar-item w3-button">마이 페이지</a>
    <a href="goodsList.gd" class="w3-bar-item w3-button">상품 등록</a>
    <a href="noticeList.nc" class="w3-bar-item w3-button">공지 사항</a>
    <a href="empList.em" class="w3-bar-item w3-button">직원 관리</a>
    <a href="memList.mem" class="w3-bar-item w3-button">회원 관리</a>
    <a href="venta.vnt" class="w3-bar-item w3-button">판매 현황</a>
    
  </div>
  <a href="logout.sm" class="w3-bar-item w3-button w3-padding">로그아웃</a> 
</nav>

	</c:if>
	</c:if>
	<hr />
	<!-- 상품 리스트 -->
<script>
	function goodsBuy(prodNum) {
		if(${authInfo == null}) {
			alert("로그인이 되어있지 않습니다.");
			return false;
		} else {
			location.href="prodInfo.gd?prodNum="+prodNum;
		}
	}
</script>
<table align="center">
	<tr>
	<c:forEach items="${lists }" var="dto" varStatus="cnt">
		<td><a href="javascript:goodsBuy('${dto.prodNum}')" >
		<img width="300" height="300" src="goods/upload/${dto.prodImage.split(',')[0] }" ><br />
		${dto.prodName }<br />
		<fmt:formatNumber value="${dto.prodPrice }" type="currency" /></a></td>
		<c:if test="${cnt.count % 3 == 0 }">
		</tr><tr>
		</c:if>
	</c:forEach>
	</tr>
</table>
	<!-- 공지사항 -->
	
</body>
</html>