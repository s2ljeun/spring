<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- content.jsp -->
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>�ۻ󼼺���</title>
</head>
<body>
<div align="center">
	<b>�۳��� ����</b><br>
	<table border="1" width="500">
		<tr>
			<th bgcolor="yellow" width="20%">�۹�ȣ</th>
			<td align="center" width="30%">${getBoard.num}</td>
			<th bgcolor="yellow" width="20%">��ȸ��</th>
			<td align="center" width="30%">${getBoard.readcount}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">�ۼ���</th>
			<td align="center" width="30%">${getBoard.writer}</td>
			<th bgcolor="yellow" width="20%">�ۼ���</th>
			<td align="center" width="30%">${getBoard.reg_date}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">������</th>
			<td width="80%" colspan="3">${getBoard.subject}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">�۳���</th>
			<td width="80%" colspan="3">${getBoard.content}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">����</th>
			<td width="80%" colspan="3">
				${getBoard.filename}
			</td>
		</tr>
		<tr bgcolor="yellow">
			<td colspan="4" align="right">
				<input type="button" value="�۸��" onclick="window.location='list_board.do'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>













