<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- member_insert.jsp -->
<html>
<head>
	<title>�����Է�</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>�� �� �� ��</h2>
		<hr color="green" width="300">
		<form name="f" action="member_insert_ok.jsp" method="post">
			<table border="1" width="500">
				<tr>
					<th>�����̸�</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<th>����ȣ</th>
					<td><input type="text" name="team_id"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="�Է�">
						<input type="reset" value="���">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>