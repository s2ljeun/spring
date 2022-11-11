<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.text.*"%>
<!-- mall_order.jsp -->
<%@ include file="mall_top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div align="center" style="overflow:auto; width:100%; height:500">
	<table class="outline" width="90%">
		<tr class="m2">
			<td colspan="3" align="center"><h4>결재 내역서 보기</h4></td>
		</tr>
		<tr class="m1">
			<th width="60%">상품명</th>
			<th width="15%">수량</th>
			<th width="25%">금액</th>
		</tr>
		<c:forEach var="dto" items="${cartList}">
		<tr>
			<td align="center">${dto.pname}</td>
			<td align="right">${dto.pqty}</td>
			<td align="right">
				<fmt:formatNumber value="${dto.pqty * dto.price}" pattern="000,000"/>원
			</td>
		</tr>
		<c:set var="totalPrice" value="${totalPrice + dto.price*dto.pqty}"/>
		</c:forEach>
		<tr class="m1">
			<td colspan="3" align="center">
				<b>결재하실 총액은 : </b>
				<font color="red"><fmt:formatNumber value="${totalPrice}" pattern="000,000"/></font>원
			</td>	
		</tr>
	</table>
</div>

<%@ include file="mall_bottom.jsp"%>













