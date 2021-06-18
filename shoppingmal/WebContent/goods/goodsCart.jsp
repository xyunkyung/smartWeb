<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
장바구니 페이지입니다.
<c:forEach items="${lists }" var="dto">
<table border=1 width=600 align="center">
	<tr><td colspan="4">${dto.goodsDTO.prodSupplyer }</td>
		<td>총 적용 금액</td><td>배송비</td></tr>
	<tr><td><img src="goods/upload/${dto.goodsDTO.prodImage.split(',')[0] }" width="50" /></td>
		<td>${dto.goodsDTO.prodName }</td>
		<td align="center">- &nbsp;&nbsp; ${dto.cartDTO.cartQty } &nbsp;&nbsp; +
		</td><td>${dto.cartDTO.cartPrice }</td>
		<td>${dto.goodsDTO.prodDelFee }</td>
		<td>${dto.cartDTO.cartPrice + dto.goodsDTO.prodDelFee }</td></tr>
</table>
</c:forEach>
</body>
</html>