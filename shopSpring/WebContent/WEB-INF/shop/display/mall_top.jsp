<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_top.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>碱俏隔权</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
		function cateList(code, cname){
			document.f.code.value = code
			document.f.cname.value = cname
			document.f.submit()
		}
	</script>
</head>
<form name="f" action="mall_cgProdList.do" method="post">
	<input type="hidden" name="code"/>
	<input type="hidden" name="cname"/>
</form>
<body>
	<div align="center">
		<table border="1" width="800" height="600">
			<tr height="50">
				<td colspan="2" align="center">
					<a href="">HOME</a> | 
					<a href="index_shop.do">包府磊权</a> | 
					<a href="mall_shop.do">碱俏隔权</a> | 
					<a href="cart.do">厘官备聪</a> | 
				</td>
			</tr>
			<tr>
				<td align="center" width="20%" valign="top">
					tree/view
					<table border="1">
				<c:forEach var="cdto" items="${clist}">
				<tr>
						<td>
							<a href="javascript:cateList('${cdto.code}','${cdto.cname}')">
								${cdto.cname}[${cdto.code}]
							</a>
						</td>	
					</tr>
				</c:forEach>	
					</table>
				</td>
				<td width="80%">
				
				
				
				
				
				
				
				
				
				
				
				