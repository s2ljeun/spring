<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, team.*"%>
<!-- team_list.jsp -->
<html>
<head>
	<title>팀리스트</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>팀 리 스 트</h2>
		<hr color="green" width="300">
		<table border="1" width="500">
			<tr>
				<th>팀번호</th>
				<th>팀이름</th>
			</tr>
<jsp:useBean id="team" class="team.TeamDAO" />			
<%
		List<TeamDTO> list = team.listTeam();
		if (list == null || list.size() == 0){%>
			<tr>
				<td colspan="2">등록된 팀이 없습니다.</td>
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
		<a href="index.jsp">돌아가기</a>
	</div>
</body>
</html>








