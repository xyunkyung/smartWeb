<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script>
function checkQty(prodNum, prodPrice, cartQty) {
	if(cartQty > 1) {
		location.href="goodsCartQtyDown.gd?prodNum="+prodNum+"&prodPrice="+prodPrice;
	} else {
		alert("1개 이상의 상품을 담아야 합니다.");
		return false;
	}
}

function prodChk() {
	var prodTot = 0;
	var chk = document.getElementsByName("prodCk"); // check를 배열로
	var hddchk = document.getElementsByName("cartPrice");
	var cnt = 0;
	for(var i = 0; i < chk.length; i++) {
		if(chk[i].checked == true) {
			prodTot += Number(hddchk[i].value);
			cnt++;
		}
	}
	document.getElementById("totalPrice").innerHTML=prodTot;
	document.getElementById("prodCnt").innerHTML=cnt;
}
</script>
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
<div class="w3-row w3-grayscale">
    <div class="w3-col l3 s6">
      <div class="w3-container">
		<b>장바구니 페이지입니다.</b><p>
			<form action="goodsBuy.gd" method="post">
			<c:set var="price" value="0" /> <!-- 자바 변수 생성 --> 
			<c:set var="cnt" value="0" />
			<c:forEach items="${lists }" var="dto">

				<table width=800 align="center">
					<tr><td colspan="4">
						<input type="checkbox" name="prodCk" 
							value="${dto.goodsDTO.prodNum }" onchange="prodChk();" 
							checked />
						<input type="hidden" name="cartPrice" 
						value="${dto.cartDTO.cartPrice + dto.goodsDTO.prodDelFee}" />
						${dto.goodsDTO.prodSupplyer }</td>
						<td>적용 금액</td><td>배송비</td><td>총 적용 금액</td>
						<td rowspan="2">
						<input type="button" value="삭제" class="w3-black"
						onclick="javascript:location.href='cartProdDel.gd?prodNum=${dto.goodsDTO.prodNum}'"/></td></tr>
					<tr><td><img src="goods/upload/${dto.goodsDTO.prodImage.split(',')[0] }" width="50" /></td>
						<td>${dto.goodsDTO.prodName }</td>
						<td align="center">
						<a href="javascript:checkQty('${dto.goodsDTO.prodNum }','${dto.goodsDTO.prodPrice }','${dto.cartDTO.cartQty }')">-
						</a> &nbsp;&nbsp; ${dto.cartDTO.cartQty } &nbsp;&nbsp; 
						<a href="goodsCartAdd.gd?prodNum=${dto.goodsDTO.prodNum }&qty=1&&prodPrice=${dto.goodsDTO.prodPrice }">+
						</a></td>
						<td align="right">
						<fmt:formatNumber value="${dto.goodsDTO.prodPrice }" type="currency" />원</td>
						<td align="right">
						<fmt:formatNumber value="${dto.cartDTO.cartPrice }" type="currency" />원</td>
						<td align="right">
						<fmt:formatNumber value="${dto.goodsDTO.prodDelFee }" type="currency" />원</td>
						<td align="right">
						<fmt:formatNumber value="${dto.cartDTO.cartPrice + dto.goodsDTO.prodDelFee }" type="currency" />원</td></tr>
					</table>
					
					<c:set var="cnt" value="${cnt = cnt + 1 }" />
					<c:set var="price" value="${dto.cartDTO.cartPrice + dto.goodsDTO.prodDelFee + price }" />
			</c:forEach>
			<p>
			<span id="prodCnt">${cnt }</span>개의 상품이 선택 되었습니다.
			<p>
			총 금액 : <span id="totalPrice">${price }
			<p>
			<input type="submit" value="구매하기" class="w3-button w3-black w3-padding" />
			<input type="button" value="이전" onclick="location:history.back();" class="w3-button w3-black w3-padding" />
		</div>
	</div>
</div>
</form>
</div>
</body>
</html>