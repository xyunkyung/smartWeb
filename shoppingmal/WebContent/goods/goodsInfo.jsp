<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	pageContext.setAttribute("br", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function onQty() {
		var qty = document.frm.purchaseQty.value;
		document.getElementById("tot").innerHTML = qty * ${dto.prodPrice}
	}
	
	function goodsCartAdd(prodNum) {
		var qty = document.frm.purchaseQty.value;
		location.href="goodsCartAdd.gd?prodNum="+prodNum+"&qty="+qty+"&prodPrice=${dto.prodPrice}";
	}
</script>
</head>
<body>
<form action="#" name="frm" method="post">
<input type="hidden" name="prodNum" value="${dto.prodNum }" />
${dto.ctgr }의 ${dto.prodName } 상품 상세 페이지 입니다.
<table width="800" align="center" >
	<tr><td rowspan="6">
		<img height="300" src="goods/upload/${dto.prodImage.split(',')[0] }" />
		</td><td>${dto.prodName }</td></tr>
	<tr><td>${dto.prodPrice }</td></tr>
	<tr><td><c:if test="${dto.prodDelFee == 0 }">무료 배송</c:if>
			<c:if test="${dto.prodDelFee != 0 }">${dto.prodDelFee }원</c:if>
		</td></tr>
	<tr><td>
		<input type="number" size="3" min="1" name="purchaseQty" onchange="onQty();" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		${dto.prodPrice }
		</td></tr>
	<tr><td>총 상품금액&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span id="tot">${dto.prodPrice }</span></td></tr>
	<tr><td><input type="button" value="장바구니" onclick="goodsCartAdd('${dto.prodNum}');" />
		<input type="submit" value="바로 구매" /></td></tr>
	<tr><td colspan="2">
		사이즈(용량) : ${dto.prodCapacity }<br />
		공급 업체 : ${dto.prodSupplyer }<br />
		${dto.prodDetail }<br />
		<c:forTokens items="${dto.prodImage }" delims="," var="file">
			<c:if test="${file != null }">
			<img width="800" src="goods/upload/${file }" />
			</c:if>
		</c:forTokens>
		</td></tr>
</table>
</form>
리뷰
<hr />
<c:forEach items="${list }" var="dto">
	<p>
	<c:if test="${dto.memId == null }">일반 사용자</c:if>
	<c:if test="${dto.memId != null }">${dto.memId }</c:if>
	/ ${dto.reviewDate }<br />
	${fn:replace(dto.reviewContent, br, "<br />") }<br />
	<c:if test="${dto.reviewImg != null }">
		<img src="goods/review/${dto.reviewImg }" />
		
	</c:if>
	<hr />
</c:forEach>
</body>
</html>