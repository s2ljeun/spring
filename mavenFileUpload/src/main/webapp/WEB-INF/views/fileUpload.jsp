<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- fileUpload.jsp -->
<html>
<head>
	<title>���Ͼ��ε�</title>
</head>
<body>
	<h1 align="center">���̺� ���� ���ε�</h1>
	<form name="f" action="fileUpload.do" method="post" 
				enctype="multipart/form-data">
		<table border="1" align="center" width="500">
			<tr>
				<th>�ø���</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>���ϸ�</th>
				<td><input type="file" name="filename"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="��������">
					<input type="reset" value="���">
				</td>
			</tr>
		</table>				
	</form>
</body>
</html>