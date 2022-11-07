<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.text.*"%>    
<%@ include file="mall_top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div align="center" style="overflow:auto; width:100%; height:500">
	<table width="100%" border="1">
		<tr class="m2">
			<td colspan="6" align="center">
				<h4>장바구니 보기</h4>
			</td>
		</tr>
		<tr class="m1">
			<th width="10%">번호</th>
			<th width="25%">상품명</th>
			<th width="15%">수량</th>
			<th width="20%">단가</th>
			<th width="20%">금액</th>
			<th width="10%">삭제</th>
		</tr>
		<c:if test="${empty cart}">
		<tr>
			<td colspan="6">장바구니에 등록된 상품이 없습니다.</td>
		</tr>
		</c:if>
		<c:if test="${not empty cart}">
			<c:set var="totalCartPrice" value="0"/>
			<c:set var="totalCartPoint" value="0"/>
			<c:forEach var="pdto" items="${cart}">
				<tr>
					<td align="right">${pdto.pnum}</td>
					<td align="center">
						<img src="${upPath}/${pdto.pimage}" width="50" height="40"><br>
						${pdto.pname}
					</td>
					<td>
						<form action="mall_cartEdit.mall" method="post">
							<input type="text" name="pqty" value="${pdto.pqty}" size="2">개
							<input type="hidden" name="pnum" value="${pdto.pnum}">
							<input type="submit" value="수정">
						</form>	
					</td>	
					<td align="right">
						<fmt:formatNumber value="${pdto.price}" pattern="000,000"/>원<br>
						${pdto.point}point
					</td>
					<td align="right">
						<fmt:formatNumber value="${pdto.price*pdto.pqty}" pattern="000,000"/>원<br>
						${pdto.point*pdto.pqty}point
					</td>
					<td align="center"><a href="mall_cartDel.mall?pnum=${pdto.pnum}">삭제</a></td>
				</tr>
				<c:set var="totalCartPrice" value="${totalCartPrice + pdto.price*pdto.pqty}"/>
				<c:set var="totalCartPoint" value="${totalCartPoint + pdto.point*pdto.pqty}"/>
			</c:forEach>
			<tr class="m1">
				<td colspan="4">
					<b>장바구니총액 : </b>
					<font color="red"><fmt:formatNumber value="${totalCartPrice}" pattern="000,000"/></font>원<br>
					총 적립 포인트 :
				<font color="green">${totalCartPoint}</font>point
			</td>
			<td colspan="2">
				[<a href="mall_order.mall?mode=cart">주문하기</a>]
				[<a href="javascript:history.go(-2)">계속쇼핑</a>]
			</td>
		</tr>
	</c:if>		
	</table>
</div>
<%@ include file="mall_bottom.jsp"%>













