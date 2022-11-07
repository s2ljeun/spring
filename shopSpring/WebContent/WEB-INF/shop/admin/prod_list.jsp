<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_list.jsp -->
<%@ include file="top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function checkDel(pnum, pimage){
		var isDel = window.confirm("정말로 삭제하시겠습니까?")
		if (isDel){
			document.f.pnum.value = pnum
			document.f.pimage.value = pimage
			document.f.submit()
		}
	}
</script>
<div align="center" style="overflow:scroll; width:100%; height:500">
	<table class="outline" width="100%">
		<caption><font size="4"><b>상품목록 카테고리</b></font></caption>
		<tr bgcolor="yellow">
			<th class="m2">번호</th>
			<th class="m2">상품코드</th>
			<th class="m2">상품명</th>
			<th class="m2">이미지</th>
			<th class="m2">가격</th>
			<th class="m2">수량</th>
			<th class="m2">수정|삭제</th>
		</tr>
	<c:if test="${empty listProd}">
		<tr>
			<td colspan="7">등록된 상품이 없습니다.</td>
		</tr>	
	</c:if>	
	<c:forEach var="dto" items="${listProd}">
		<tr>
			<td>${dto.pnum}</td>
			<td>${dto.pcategory_fk}</td>
			<td>${dto.pname}</td>
			<td>
				<a href="prod_view.do?pnum=${dto.pnum}">
					<img src="${upPath}/${dto.pimage}" width="50" height="40">
				</a>
			</td>
			<td>${dto.price}</td>
			<td>${dto.pqty}</td>
			<td>
				<a href="prod_update.do?pnum=${dto.pnum}">수정</a> |
				<a href="javascript:checkDel('${dto.pnum}','${dto.pimage}')">삭제</a>
			</td>
		</tr>			 
	</c:forEach>			
	</table>		
</div>
<%@ include file="bottom.jsp"%>
<form name="f" action="prod_delete.do" method="post">
	<input type="hidden" name="pnum"/>
	<input type="hidden" name="pimage"/>
</form>













