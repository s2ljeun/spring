<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- cate_input.jsp -->  
<%@ include file="top.jsp"%>
<div align="center">
	<form name="f" action="cate_input.do" method="post">
		<table border="1" width="400">
			<caption><font size="4"><b>ī�װ� ���</b></font></caption>
			<tr>
				<th bgcolor="yellow" width="30%">ī�װ� �ڵ�</th>
				<td width="70%"><input type="text" name="code" class="box"></td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="30%">ī�װ� �̸�</th>
				<td width="70%"><input type="text" name="cname" class="box"></td>
			</tr>
			<tr>
				<td align="center" colspan="2" bgcolor="yellow">
					<input type="submit" value="���">
					<input type="reset" value="���">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>