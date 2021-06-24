<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
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
장바구니 페이지입니다.<br />
<table border=1 width=800 align="center">
<form action="goodsBuy.gd" method="post">
<c:set var="price" value="0" /> <!-- 자바 변수 생성 --> 
<c:set var="cnt" value="0" />
<c:forEach items="${lists }" var="dto">

	<tr><td colspan="4">
		<input type="checkbox" name="prodCk" value="${dto.goodsDTO.prodNum }" onchange="prodChk();" checked />
		<input type="hidden" name="cartPrice" value="${dto.cartDTO.cartPrice + dto.goodsDTO.prodDelFee}" />
		${dto.goodsDTO.prodSupplyer }</td>
		<td>적용 금액</td><td>배송비</td><td>총 적용 금액</td>
		<td rowspan="2"><input type="button" value="삭제" onclick="javascript:location.href='cartProdDel.gd?prodNum=${dto.goodsDTO.prodNum}'"/></td></tr>
	<tr><td><img src="goods/upload/${dto.goodsDTO.prodImage.split(',')[0] }" width="50" /></td>
		<td>${dto.goodsDTO.prodName }</td>
		<td align="center"><a href="javascript:checkQty('${dto.goodsDTO.prodNum }','${dto.goodsDTO.prodPrice }','${dto.cartDTO.cartQty }')">-</a> &nbsp;&nbsp; ${dto.cartDTO.cartQty } &nbsp;&nbsp; <a href="goodsCartAdd.gd?prodNum=${dto.goodsDTO.prodNum }&qty=1&&prodPrice=${dto.goodsDTO.prodPrice }">+</a></td>
		<td align="right"><fmt:formatNumber value="${dto.goodsDTO.prodPrice }" type="currency" />원</td>
		<td align="right"><fmt:formatNumber value="${dto.cartDTO.cartPrice }" type="currency" />원</td>
		<td align="right"><fmt:formatNumber value="${dto.goodsDTO.prodDelFee }" type="currency" />원</td>
		<td align="right"><fmt:formatNumber value="${dto.cartDTO.cartPrice + dto.goodsDTO.prodDelFee }" type="currency" />원</td></tr>

<c:set var="cnt" value="${cnt = cnt + 1 }" />
<c:set var="price" value="${dto.cartDTO.cartPrice + dto.goodsDTO.prodDelFee + price }" />
</c:forEach>
<span id="prodCnt">${cnt }</span>개의 상품이 선택 되었습니다.&nbsp;
총 금액 : <span id="totalPrice">${price }</span>


</form>
</table>
<input type="submit" value="구매하기" />
<input type="button" value="이전" onclick="location:history.back();" />
</body>
</html>