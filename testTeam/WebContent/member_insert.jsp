<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- member_insert.jsp -->
<html>
<head>
	<title>팀원입력</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>팀 원 입 력</h2>
		<hr color="green" width="300">
		<form name="f" action="member_insert_ok.jsp" method="post">
			<table border="1" width="500">
				<tr>
					<th>팀원이름</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<th>팀번호</th>
					<td><input type="text" name="team_id"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="입력">
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>