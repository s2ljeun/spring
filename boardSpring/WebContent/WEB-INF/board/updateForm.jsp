<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- updateForm.jsp -->
<html>
<head>
	<title>�ۼ���</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
		function checkBoard(){
			if (!f.subject.value){
				alert("������ �Է��� �ּ���!!")
				f.subject.focus()
				return false
			}
			if (f.content.value==""){
				alert("������ �Է��� �ּ���!!")
				f.content.focus()
				return false
			}
			if (f.passwd.value==""){
				alert("��й�ȣ�� �Է��� �ּ���!!")
				f.passwd.focus()
				return false
			}
			return true		
		}
	</script>
</head>
<body>
<div align="center">
	<b>�ۼ���</b>
	<form name="f" action="update_board.do" method="post" onsubmit="return checkBoard()">
		<input type="hidden" name="num" value="${getBoard.num}" />
		<table border="1" width="500">
			<tr>
				<th bgcolor="yellow">�� ��</th>
				<td><input type="text" name="writer" value="${getBoard.writer}" readOnly></td>
			</tr>
			 <tr>
				<th bgcolor="yellow">�� ��</th>
				<td><input type="text" name="subject" size="50" value="${getBoard.subject}"></td>
			</tr>
			<tr>
				<th bgcolor="yellow">Email</th>
				<td><input type="text" name="email" size="50" value="${getBoard.email}"></td>
			</tr>
			<tr>
				<th bgcolor="yellow">�� ��</th>
				<td><textarea name="content" rows="11" cols="50">${getBoard.content}</textarea></td>
			</tr>
			<tr>
				<th bgcolor="yellow">��й�ȣ</th>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr bgcolor="yellow">
				<td colspan="2" align="center">
					<input type="submit" value="�ۼ���">
					<input type="reset" value="�ٽ��ۼ�">
					<input type="button" value="��Ϻ���" onclick="window.location='list_board.do'">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>