<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_view.jsp -->
<%@ include file="top.jsp"%>
<div align="center">
	<form name="f" action="prod_list.do" method="post">
		<table class="outline" width="80%">
			<caption><font size="4"><b>��ǰ�󼼺���</b></font></caption>
			<tr>
				<th class="m2" width="15%">ī�װ�</th>
				<td width="35%">${getProduct.pcategory_fk}</td>
				<th class="m2" width="15%">��ǰ��ȣ</th>
				<td width="35%">${getProduct.pnum}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">��ǰ��</th>
				<td width="35%">${getProduct.pname}</td>
				<th class="m2" width="15%">����ȸ��</th>
				<td width="35%">${getProduct.pcompany}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">��ǰ�̹���</th>
				<td width="85%" colspan="3">
					<img src="${upPath}/${getProduct.pimage}" width="200" height="150">
				</td>
			</tr>
			<tr>
				<th class="m2" width="15%">��ǰ����</th>
				<td width="35%">${getProduct.pqty}</td>
				<th class="m2" width="15%">��ǰ����</th>
				<td width="35%">${getProduct.price}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">��ǰ����</th>
				<td width="35%">${getProduct.pspec}</td>
				<th class="m2" width="15%">��ǰ����Ʈ</th>
				<td width="35%">${getProduct.point}</td>
			</tr>
			<tr>
				<th class="m2" width="15%">��ǰ�Ұ�</th>
				<td width="85%" colspan="3">
					<textarea name="contents" rows="5" cols="50" disabled>${getProduct.pcontents}</textarea>
				</td>
			</tr>
			<tr>
				<td class="m1" align="center" colspan="4">
					<input type="submit" value="���ư���">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>














