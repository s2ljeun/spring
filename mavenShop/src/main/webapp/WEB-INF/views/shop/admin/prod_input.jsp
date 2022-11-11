<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_input.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>
<script type="text/javascript">
	function check(){
		if (f.pname.value==""){
			alert("��ǰ���� �Է��� �ּ���!!")
			f.pname.focus()
			return
		}
		if (f.pcode.value==""){
			alert("��ǰ�ڵ带 �Է��� �ּ���!!")
			f.pcode.focus()
			return
		}
		if (f.pcompany.value==""){
			alert("����ȸ�縦 �Է��� �ּ���!!")
			f.pcompany.focus()
			return
		}
		if (f.pimage.value==""){
			alert("��ǰ�̹����� �Է��� �ּ���!!")
			f.pimage.focus()
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
	<form name="f" action="prod_input.do" method="post" enctype="multipart/form-data">
		<table class="outline" width="600">
			<caption><font size="4"><b>��ǰ��� ī�װ�</b></font></caption>
			<tr>
				<th class="m2">ī�װ�</th>
				<td>
					<select name="pcatecode">
				<c:forEach var="dto" items="${listCate}">
					<option value="${dto.code}">${dto.cname}[${dto.code}]</option>
				</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th class="m2">��ǰ��</th>
				<td><input type="text" name="pname" class="box"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ�ڵ�</th>
				<td><input type="text" name="pcode" class="box"></td>
			</tr>
			<tr>
				<th class="m2">����ȸ��</th>
				<td><input type="text" name="pcompany" class="box"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ�̹���</th>
				<td><input type="file" name="pimage" class="box" size="50"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ����</th>
				<td><input type="text" name="pqty" class="box"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ����</th>
				<td><input type="text" name="price" class="box"></td>
			</tr>
			<tr>
				<th class="m2">��ǰ����</th>
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
				<th class="m2">��ǰ�Ұ�</th>
				<td><textarea name="pcontents" rows="5" cols="60"></textarea>
			</tr>
			<tr>
				<th class="m2">��ǰ����Ʈ</th>
				<td><input type="text" name="point" class="box"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="��ǰ���" onclick="javascript:check()">
					<input type="reset" value="���">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>








