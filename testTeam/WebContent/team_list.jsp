<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, team.*"%>
<!-- team_list.jsp -->
<html>
<head>
	<title>������Ʈ</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>�� �� �� Ʈ</h2>
		<hr color="green" width="300">
		<table border="1" width="500">
			<tr>
				<th>����ȣ</th>
				<th>���̸�</th>
			</tr>
<jsp:useBean id="team" class="team.TeamDAO" />			
<%
		List<TeamDTO> list = team.listTeam();
		if (list == null || list.size() == 0){%>
			<tr>
				<td colspan="2">��ϵ� ���� �����ϴ�.</td>
			</tr>		
<%	}else {
			for(TeamDTO dto : list){%>
			<tr>
				<td><%=dto.getId()%></td>
				<td><%=dto.getName()%></td>
			</tr>
<% 		}
		}%>
		</table>
		<a href="index.jsp">���ư���</a>
	</div>
</body>
</html>








