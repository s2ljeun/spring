<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- member_edit.jsp -->
<%
	request.setCharacterEncoding("EUC-KR");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
%>

<html>
<head>
	<title>��������</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>�� �� �� ��</h2>
		<hr color="green" width="300">
		<form name="f" action="member_edit_ok.jsp" method="post">
			<table border="1" width="500">
				<tr>
					<th>������ȣ</th>
					<td><input type="text" name="id" value="<%=id%>" readonly></td>
				</tr>
				<tr>
					<th>�����̸�</th>
					<td><input type="text" name="name" value="<%=name%>" readonly></td>
				</tr>
				<tr>
					<th>�ٲ�����ȣ</th>
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