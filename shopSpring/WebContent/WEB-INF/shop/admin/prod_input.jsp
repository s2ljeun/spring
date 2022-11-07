<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_input.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>
<script type="text/javascript">
	function check(){
		if (f.pname.value==""){
			alert("상품명을 입력해 주세요!!")
			f.pname.focus()
			return
		}
		if (f.pcode.value==""){
			alert("상품코드를 입력해 주세요!!")
			f.pcode.focus()
			return
		}
		if (f.pcompany.value==""){
			alert("제조회사를 입력해 주세요!!")
			f.pcompany.focus()
			return
		}
		if (f.pimage.value==""){
			alert("상품이미지를 입력해 주세요!!")
			f.pimage.focus()
			return
		}
		if (f.pqty.value==""){
			alert("상품수량을 입력해 주세요!!")
			f.pqty.focus()
			return
		}
		if (f.price.value==""){
			alert("상품가격을 입력해 주세요!!")
			f.price.focus()
			return
		}
		if (f.pcontents.value==""){
			alert("상품소개를 입력해 주세요!!")
			f.pcontents.focus()
			return
		}
		if (f.point.value==""){
			alert("상품포인트를 입력해 주세요!!")
			f.point.focus()
			return
		}
		document.f.submit()
	}
</script>
<div align="center">
	<form name="f" action="prod_input.do" method="post" enctype="multipart/form-data">
		<table class="outline" width="600">
			<caption><font size="4"><b>상품등록 카테고리</b></font></caption>
			<tr>
				<th class="m2">카테고리</th>
				<td>
					<select name="pcatecode">
				<c:forEach var="dto" items="${listCate}">
					<option value="${dto.code}">${dto.cname}[${dto.code}]</option>
				</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th class="m2">상품명</th>
				<td><input type="text" name="pname" class="box"></td>
			</tr>
			<tr>
				<th class="m2">상품코드</th>
				<td><input type="text" name="pcode" class="box"></td>
			</tr>
			<tr>
				<th class="m2">제조회사</th>
				<td><input type="text" name="pcompany" class="box"></td>
			</tr>
			<tr>
				<th class="m2">상품이미지</th>
				<td><input type="file" name="pimage" class="box" size="50"></td>
			</tr>
			<tr>
				<th class="m2">상품수량</th>
				<td><input type="text" name="pqty" class="box"></td>
			</tr>
			<tr>
				<th class="m2">상품가격</th>
				<td><input type="text" name="price" class="box"></td>
			</tr>
			<tr>
				<th class="m2">상품스팩</th>
				<td>
					<select name="pspec">
						<option>NORMAL</option>
						<option>HIT</option>
						<option>BEST</option>
						<option>NEW</option>
					</select>
				</td>
			</tr>
			<tr>
				<th class="m2">상품소개</th>
				<td><textarea name="pcontents" rows="5" cols="60"></textarea>
			</tr>
			<tr>
				<th class="m2">상품포인트</th>
				<td><input type="text" name="point" class="box"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="상품등록" onclick="javascript:check()">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>








