<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- top.jsp -->
<html>
<head>
	<title>ȸ������</title>
	<script type="text/javascript">
		function checkMember(){
			window.open("check_member.do", "", "width=640, height=400")
		}
	</script>
	<link rel="stylesheet" type="text/css" href="resources/styles/style.css">
</head>
<c:if test="${not empty sessionScope.mdto}">
	<c:set var="isLogin" value="true" />
</c:if>
<body>
<div align="center">
	<table border="1" width="800" height="600">
		<tr height="10%">
		<c:if test="${isLogin}">
			<th width="20%"><a href="logout.do">�α׾ƿ�</a></th>	
		</c:if>
		<c:if test="${!isLogin}">
			<th width="20%"><a href="login.do">�α���</a></th>
		</c:if>
			<th width="20%"><a href="javascript:checkMember()">ȸ������</a></th>
			<th width="20%"><a href="list_member.do">ȸ������</a></th>
			<th width="20%"><a href="list_member.do?mode=find">ȸ��ã��</a></th>
			<th width="20%"><a href="index_member.do">��������</a></th>
		</tr>
		<tr height="80%">
			<td colspan="5">
			
			
			
			
			
			