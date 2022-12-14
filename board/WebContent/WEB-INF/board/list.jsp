<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- list.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html> 
<head>
	<title>게시글보기</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>	
<body>
<div align="center"> 
	<b>글 목 록</b>
	<table border="0" width="50%">
		<tr bgcolor="yellow">
			<td align="right">
				<a href="write_board.do">글쓰기</a>
			</td>
		</tr>
	</table>
	<table border="1" width="50%">
		<tr bgcolor="green">
			<th>번호</th>
			<th width="30%">제 목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
			<th>IP</th>
			<th>파일</th>
		</tr>
	<c:if test="${empty listBoard}">
		<tr>
			<td colspan="7">등록된 게시글이 없습니다.</td>
		</tr>
	</c:if>	
	<c:set var="num" value="${requestScope.num}"/>
	<c:forEach var="dto" items="${listBoard}">
		<tr>
			<td>
				<c:out value="${num}"/>
				<c:set var="num" value="${num-1}"/>
			</td>
			<td>
				<a href="content_board.do?num=${dto.num}">
					${dto.subject}
				</a>
				<c:if test="${dto.readcount>10}">
					<img src="images/hot.gif">
				</c:if>	
			</td>
			<td>${dto.writer}</td>
			<td>${dto.reg_date}</td>
			<td>${dto.readcount}</td>
			<td>${dto.ip}</td>
			<td>
				<c:if test="${not empty dto.filename}">
					<img src="images/file.png" width="20">
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</table>
	<c:if test="${not empty listBoard}">	
		<c:if test="${startPage > pageBlock}">
			[<a href="list_board.do?pageNum=${startPage-1}">이전</a>]
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			[<a href="list_board.do?pageNum=${i}">${i}</a>]
		</c:forEach>	
		<c:if test="${pageCount > endPage}">
			[<a href="list_board.do?pageNum=${endPage+1}">다음</a>]
		</c:if>
	</c:if>
</div>
</body>
</html>










