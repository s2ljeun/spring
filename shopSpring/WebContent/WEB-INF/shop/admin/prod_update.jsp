<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="shop.dto.*"%>
<!-- prod_update.jsp -->
<%@ include file="top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function check(){
		if (f.pname.value==""){
			alert("��ǰ���� �Է��� �ּ���!!")
			f.pname.focus()
			return
		}
		if (f.pcompany.value==""){
			alert("����ȸ�縦 �Է��� �ּ���!!")
			f.pcompany.focus()
			return
		}
		if (f.pqty.value==""){
			alert("��ǰ������ �Է��� �ּ���!!")
			f.pqty.focus()
			return
		}
		if (f.price.value==""){
			alert("��ǰ������ �Է��� �ּ���!!")
			f.price.focus()
			return
		}
		if (f.pcontents.value==""){
			alert("��ǰ�Ұ��� �Է��� �ּ���!!")
			f.pcontents.focus()
			return
		}
		if (f.point.value==""){
			alert("��ǰ����Ʈ�� �Է��� �ּ���!!")
			f.point.focus()
			return
		}
		document.f.submit()
	}
</script>
<div align="center">
	<form name="f" action="prod_update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pnum" value="${getProduct.pnum}"/>
		<table class="outline" width="600">
			<caption><font size="4"><b>��ǰ����</b></font></caption>
			<tr>
				<th class="m2" width="20%">ī�װ�</th>
				<td>
					<input type="text" name="pcategory_fk" value="${getProduct.pcategory_fk}" disabled>
				</td>
			</tr>
			<tr>
				<th class="m2">��ǰ��ȣ</th>
				<td>${getProduct.pnum}</td>
			</tr>
			<tr>
				<th class="m2">��ǰ��</th>
				<td><input type="text" name="pname" class="box" value="${getProduct.pname}"></td>
			</tr>
			<tr>
				<th class="m2">����ȸ��</th>
				<td><input type="text" name="pcompany" class="box" value="${getProduct.pcompany}"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ�̹���</th>
				<td>
					<img src="${upPath}/${getProduct.pimage}" width="200" height="150">					
					<input type="file" name="pimage" class="box" size="50">
					<input type="hidden" name="pimage2" value="${getProduct.pimage}"/>
				</td>
			</tr>
			<tr>
				<th class="m2">��ǰ����</th>
				<td><input type="text" name="pqty" class="box" value="${getProduct.pqty}"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ����</th>
				<td><input type="text" name="price" class="box" value="${getProduct.price}"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ����</th>
				<td>
				<select name="pspec">
					<c:forTokens var="spec" items="NORMAL,HIT,BEST,NEW" delims=",">
						<c:if test="${spec == getProduct.pspec}">
							<option value="${spec}" selected>${spec}</option>
						</c:if>
						<c:if test="${spec != getProduct.pspec}">
							<option value="${spec}">${spec}</option>
						</c:if>
					</c:forTokens>
				</select>					
				</td>
			</tr>
			<tr>
				<th class="m2">��ǰ�Ұ�</th>
				<td><textarea name="pcontents" rows="5" cols="60">${getProduct.pcontents}</textarea>
			</tr>
			<tr>
				<th class="m2">��ǰ����Ʈ</th>
				<td><input type="text" name="point" class="box" value="${getProduct.point}"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="��ǰ����" onclick="javascript:check()">
					<input type="reset" value="���">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>