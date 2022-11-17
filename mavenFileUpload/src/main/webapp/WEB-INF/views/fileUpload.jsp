<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- fileUpload.jsp -->
<html>
<head>
	<title>파일업로드</title>
</head>
<body>
	<h1 align="center">메이븐 파일 업로드</h1>
	<form name="f" action="fileUpload.do" method="post" 
				enctype="multipart/form-data">
		<table border="1" align="center" width="500">
			<tr>
				<th>올린이</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>파일명</th>
				<td><input type="file" name="filename"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="파일전송">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>				
	</form>
</body>
</html>