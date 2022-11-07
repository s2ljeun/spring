<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall.jsp -->
<%@ include file="mall_top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="center"  style="overflow:scroll; width:100%; height:500">
	<h3>Welcome to My Mall</h3>
	<c:if test="${empty hitList}">
		<b>HIT 상품이 없습니다.</b>
	</c:if>
	<c:if test="${not empty hitList}">
		<hr color="green" width="80%">
		<font size="5">HIT</font>	
		<hr color="green" width="80%">
		<table border="0" width="80%">
			<tr>
				<c:set var="count" value="0" />
				<c:forEach var="pdto" items="${hitList}">
					<td align="center">
						<a href="mall_prodView.do?pnum=${pdto.pnum}&select=HIT">
							<img src="${upPath}/${pdto.pimage}" width="80" height="60">
						</a>
						<br>
						${pdto.pname}<br>
						<font color="red"><fmt:formatNumber value="${pdto.price}" pattern="000,000"/></font>원<br>
						<font color="green">[${pdto.point}]</font>point
					</td>
					<c:set var="count" value="${count+1}"/>
					<c:if test="${count%3==0}">
						</tr><tr>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty bestList}">
		<b>BEST 상품이 없습니다.</b>
	</c:if>
	<c:if test="${not empty bestList}">
		<hr color="green" width="80%">
		<font size="5">BEST</font>	
		<hr color="green" width="80%">
		<table border="0" width="80%">
			<tr>
				<c:set var="count" value="0" />
				<c:forEach var="pdto" items="${bestList}">
					<td align="center">
						<a href="mall_prodView.do?pnum=${pdto.pnum}&select=BEST">
							<img src="${upPath}/${pdto.pimage}" width="80" height="60">
						</a>
						<br>
						${pdto.pname}<br>
						<font color="red"><fmt:formatNumber value="${pdto.price}" pattern="000,000"/></font>원<br>
						<font color="green">[${pdto.point}]</font>point
					</td>
					<c:set var="count" value="${count+1}"/>
					<c:if test="${count%3==0}">
						</tr><tr>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty newList}">
		<b>NEW 상품이 없습니다.</b>
	</c:if>
	<c:if test="${not empty newList}">
		<hr color="green" width="80%">
		<font size="5">NEW</font>	
		<hr color="green" width="80%">
		<table border="0" width="80%">
			<tr>
				<c:set var="count" value="0" />
				<c:forEach var="pdto" items="${newList}">
					<td align="center">
						<a href="mall_prodView.do?pnum=${pdto.pnum}&select=NEW">
							<img src="${upPath}/${pdto.pimage}" width="80" height="60">
						</a>
						<br>
						${pdto.pname}<br>
						<font color="red"><fmt:formatNumber value="${pdto.price}" pattern="000,000"/></font>원<br>
						<font color="green">[${pdto.point}]</font>point
					</td>
					<c:set var="count" value="${count+1}"/>
					<c:if test="${count%3==0}">
						</tr><tr>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</div>
<%@ include file="mall_bottom.jsp"%>			











	