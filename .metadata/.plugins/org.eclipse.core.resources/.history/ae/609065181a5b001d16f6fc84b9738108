<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- top.jsp -->
<html>
<head>
	<title>회원관리</title>
	<script type="text/javascript">
		function checkMember(){
			window.open("member_ssn.mem", "", "width=640, height=400")
		}
	</script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<c:if test="${not empty sessionScope.mdto}">
	<c:set var="isLogin" value="true" />
</c:if>
<body>
<div align="center">
	<table border="1" width="800" height="600">
		<tr height="10%">
		<c:if test="${isLogin}">
			<th width="20%"><a href="logout.mem">로그아웃</a></th>	
		</c:if>
		<c:if test="${!isLogin}">
			<th width="20%"><a href="login.mem">로그인</a></th>
		</c:if>
			<th width="20%"><a href="javascript:checkMember()">회원가입</a></th>
			<th width="20%"><a href="member_list.mem">회원보기</a></th>
			<th width="20%"><a href="member_list.mem?mode=find">회원찾기</a></th>
			<th width="20%"><a href="index.do">메인으로</a></th>
		</tr>
		<tr height="80%">
			<td colspan="5">
			
			
			
			
			
			