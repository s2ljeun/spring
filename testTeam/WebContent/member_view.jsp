<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, team.*, member.*"%>
<!-- member_view.jsp -->
<html>
<head>
	<title>��������</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>�� �� �� ��</h2>
		<jsp:useBean id="tdao" class="team.TeamDAO" />
<%	List<TeamDTO> list = tdao.listTeam();%>
		<form name="f" method="post">
			<select name="team">	
<%	for(TeamDTO dto : list){%>
			<option value="<%=dto.getId()%>"><%=dto.getName()%></option>
<%	} %>
		</select>
		<input type="submit" value="��ȸ">
		</form>		
		<hr color="green" width="300">
		<table border="1" width="500">
			<tr>
				<th>������ȣ</th>
				<th>�����̸�</th>
				<th>����|����</th>
			</tr>
<jsp:useBean id="mdao" class="member.MemberDAO"/>
<%
		String team_id = request.getParameter("team");
		if (team_id == null){%>
			<tr>
				<td colspan="3">���� ������ �ּ���</td>
			</tr>
<%	}else {
		List<MemberDTO> mlist = mdao.listMember(new Integer(team_id));
		if (mlist == null || mlist.size() == 0){%>
			<tr>
				<td colspan="3">��ϵ� ������ �����ϴ�.</td>
			</tr>
<%	}else { 
			for(MemberDTO mdto : mlist){%>
			<tr>
				<td><%=mdto.getId()%></td>
				<td><%=mdto.getName()%></td>
				<td>
					<a href="member_edit.jsp?id=<%=mdto.getId()%>&name=<%=mdto.getName()%>">����</a>
					|
					<a href="member_delete.jsp?id=<%=mdto.getId()%>">����</a>
				</td>
			</tr>
<%		}
		}
		}%>					
		</table>
	</div>
</body>
</html>






