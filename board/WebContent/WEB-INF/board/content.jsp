<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- content.jsp -->
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>글상세보기</title>
</head>
<body>
<div align="center">
	<b>글내용 보기</b><br>
	<table border="1" width="500">
		<tr>
			<th bgcolor="yellow" width="20%">글번호</th>
			<td align="center" width="30%">${getBoard.num}</td>
			<th bgcolor="yellow" width="20%">조회수</th>
			<td align="center" width="30%">${getBoard.readcount}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">작성자</th>
			<td align="center" width="30%">${getBoard.writer}</td>
			<th bgcolor="yellow" width="20%">작성일</th>
			<td align="center" width="30%">${getBoard.reg_date}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">글제목</th>
			<td width="80%" colspan="3">${getBoard.subject}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">글내용</th>
			<td width="80%" colspan="3">${getBoard.content}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">파일</th>
			<td width="80%" colspan="3">
				${getBoard.filename}
			</td>
		</tr>
		<tr bgcolor="yellow">
			<td colspan="4" align="right">
				<input type="button" value="글목록" onclick="window.location='list_board.do'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>













