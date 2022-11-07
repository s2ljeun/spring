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
				<h4>��ٱ��� ����</h4>
			</td>
		</tr>
		<tr class="m1">
			<th width="10%">��ȣ</th>
			<th width="25%">��ǰ��</th>
			<th width="15%">����</th>
			<th width="20%">�ܰ�</th>
			<th width="20%">�ݾ�</th>
			<th width="10%">����</th>
		</tr>
		<c:if test="${empty cart}">
		<tr>
			<td colspan="6">��ٱ��Ͽ� ��ϵ� ��ǰ�� �����ϴ�.</td>
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
							<input type="text" name="pqty" value="${pdto.pqty}" size="2">��
							<input type="hidden" name="pnum" value="${pdto.pnum}">
							<input type="submit" value="����">
						</form>	
					</td>	
					<td align="right">
						<fmt:formatNumber value="${pdto.price}" pattern="000,000"/>��<br>
						${pdto.point}point
					</td>
					<td align="right">
						<fmt:formatNumber value="${pdto.price*pdto.pqty}" pattern="000,000"/>��<br>
						${pdto.point*pdto.pqty}point
					</td>
					<td align="center"><a href="mall_cartDel.mall?pnum=${pdto.pnum}">����</a></td>
				</tr>
				<c:set var="totalCartPrice" value="${totalCartPrice + pdto.price*pdto.pqty}"/>
				<c:set var="totalCartPoint" value="${totalCartPoint + pdto.point*pdto.pqty}"/>
			</c:forEach>
			<tr class="m1">
				<td colspan="4">
					<b>��ٱ����Ѿ� : </b>
					<font color="red"><fmt:formatNumber value="${totalCartPrice}" pattern="000,000"/></font>��<br>
					�� ���� ����Ʈ :
				<font color="green">${totalCartPoint}</font>point
			</td>
			<td colspan="2">
				[<a href="mall_order.mall?mode=cart">�ֹ��ϱ�</a>]
				[<a href="javascript:history.go(-2)">��Ӽ���</a>]
			</td>
		</tr>
	</c:if>		
	</table>
</div>
<%@ include file="mall_bottom.jsp"%>













