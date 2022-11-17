<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, team.*, member.*"%>
<!-- member_view.jsp -->
<html>
<head>
	<title>선수관리</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>선 수 관 리</h2>
		<jsp:useBean id="tdao" class="team.TeamDAO" />
<%	List<TeamDTO> list = tdao.listTeam();%>
		<form name="f" method="post">
			<select name="team">	
<%	for(TeamDTO dto : list){%>
			<option value="<%=dto.getId()%>"><%=dto.getName()%></option>
<%	} %>
		</select>
		<input type="submit" value="조회">
		</form>		
		<hr color="green" width="300">
		<table border="1" width="500">
			<tr>
				<th>선수번호</th>
				<th>선수이름</th>
				<th>수정|삭제</th>
			</tr>
<jsp:useBean id="mdao" class="member.MemberDAO"/>
<%
		String team_id = request.getParameter("team");
		if (team_id == null){%>
			<tr>
				<td colspan="3">팀을 선택해 주세요</td>
			</tr>
<%	}else {
		List<MemberDTO> mlist = mdao.listMember(new Integer(team_id));
		if (mlist == null || mlist.size() == 0){%>
			<tr>
				<td colspan="3">등록된 선수가 없습니다.</td>
			</tr>
<%	}else { 
			for(MemberDTO mdto : mlist){%>
			<tr>
				<td><%=mdto.getId()%></td>
				<td><%=mdto.getName()%></td>
				<td>
					<a href="member_edit.jsp?id=<%=mdto.getId()%>&name=<%=mdto.getName()%>">수정</a>
					|
					<a href="member_delete.jsp?id=<%=mdto.getId()%>">삭제</a>
				</td>
			</tr>
<%		}
		}
		}%>					
		</table>
	</div>
</body>
</html>






