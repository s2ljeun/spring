<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="team.*, member.*"%>
<%
	request.setCharacterEncoding("EUC-KR");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String team_id = request.getParameter("team_id");
%>

<jsp:useBean id="tdao" class="team.TeamDAO"/>
<%
		TeamDTO dto = tdao.getTeam(new Integer(team_id));
		//dto 객체가 만들어졌다 = 제대로 존재하는 team_id를 입력했다
		if (dto == null) {%>
			<script type="text/javascript">
				alert("팀번호를 정확히 입력해 주세요!!")
				location.href="member_insert.jsp";
			</script>
<%		return; 
		}%>
		
<jsp:useBean id="mdao" class="member.MemberDAO"/>
<%
	int res = mdao.editMember(id, dto);
	if (res>0){%>
			<script type="text/javascript">
				alert("<%=name%> 선수를 <%=dto.getName()%> 팀으로 이적하였습니다.")
				location.href="index.jsp";
			</script>
<%	}else { %>
			<script type="text/javascript">
				alert("<%=name%> 선수를 <%=dto.getName()%> 팀으로 이적하는데 실패하였습니다.")
				location.href="member_edit.jsp";
			</script>
<%	} %>		