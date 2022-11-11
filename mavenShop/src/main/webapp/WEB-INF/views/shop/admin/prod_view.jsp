<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_view.jsp -->
<%@ include file="top.jsp"%>
<div align="center">
	<form name="f" action="prod_list.do" method="post">
		<table class="outline" width="80%">
			<caption><font size="4"><b>상품상세보기</b></font></caption>
			<tr>
				<th class="m2" width="15%">카테고리</th>
				<td width="35%">${getProduct.pcategory_fk}</td>
				<th class="m2" width="15%">상품번호</th>
				<td width="35%">${getProduct.pnum}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">상품명</th>
				<td width="35%">${getProduct.pname}</td>
				<th class="m2" width="15%">제조회사</th>
				<td width="35%">${getProduct.pcompany}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">상품이미지</th>
				<td width="85%" colspan="3">
					<img src="${upPath}/${getProduct.pimage}" width="200" height="150">
				</td>
			</tr>
			<tr>
				<th class="m2" width="15%">상품수량</th>
				<td width="35%">${getProduct.pqty}</td>
				<th class="m2" width="15%">상품가격</th>
				<td width="35%">${getProduct.price}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">상품스팩</th>
				<td width="35%">${getProduct.pspec}</td>
				<th class="m2" width="15%">상품포인트</th>
				<td width="35%">${getProduct.point}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">상품소개</th>
				<td width="85%" colspan="3">
					<textarea name="contents" rows="5" cols="50" disabled>${getProduct.pcontents}</textarea>
				</td>
			</tr>
			<tr>
				<td class="m1" align="center" colspan="4">
					<input type="submit" value="돌아가기">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>














