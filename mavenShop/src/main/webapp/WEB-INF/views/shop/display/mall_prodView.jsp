<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_prodView.jsp -->
<%@ include file="mall_top.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function goCart(){
		document.go.action="mall_cartAdd.do"
		document.go.submit()
	}
	function goOrder(){
		document.go.action="mall_order.do"
		document.go.submit()
	}
</script>
<div align="center">
	<table class="outline" width="100%">
		<tr class="m1">
			<td colspan="2" align="center">
				<b>[${pdto.pname}] 상품 정보</b>
			</td>
		</tr>
		<tr>
			<td width="50%" align="center" class="m3">
				<img src="${upPath}/${pdto.pimage}" width="200" height="180">
			</td>
			<td width="50%" class="m3">
				상품번호 : ${pdto.pnum}<br>
				상품이름 : ${pdto.pname}<br>
				상품가격 : <font color="red"><fmt:formatNumber value="${pdto.price}"
				 pattern="000,000"/></font>원<br>
				상품포인트 : <font color="green"><fmt:formatNumber value="${pdto.point}"
				 pattern="000,000"/></font>point<br>
				<form name="go" method="post">
				상품갯수 : <input type="text" name="pqty" value="1" size="2">개<br><br>
					<input type="hidden" name="pnum" value="${param.pnum}"/>
					<input type="hidden" name="select" value="${param.select}"/>
				</form>
				<a href="javascript:goCart()">장바구니담기</a> | 
				<a href="javascript:goOrder()">즉시 구매하기</a>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<b>상품 상세 설명</b><br>
				${pdto.pcontents}
			</td>
		</tr>
	</table>
</div>
<%@ include file="mall_bottom.jsp"%>












