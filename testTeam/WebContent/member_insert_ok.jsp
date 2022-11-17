<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="team.*"%>
<!-- member_insert_ok.jsp -->
<%
		request.setCharacterEncoding("EUC-KR");
		String name = request.getParameter("name");
		String team_id = request.getParameter("team_id");
		if (name == null || name.trim().equals("")){%>
			<script type="text/javascript">
				alert("선수이름을 입력해 주세요!!")
				location.href="member_insert.jsp";
			</script>
<%		return;
		}%>

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
		int res = mdao.insertMember(name, dto);
		if (res>0){%>
			<script type="text/javascript">
				alert("<%=name%> 선수를 <%=dto.getName()%> 팀에 등록하였습니다.")
				location.href="index.jsp";
			</script>
<%	}else { %>
			<script type="text/javascript">
				alert("<%=name%> 선수를 <%=dto.getName()%> 팀에 등록하는데 실패하였습니다.")
				location.href="member_insert.jsp";
			</script>
<%	} %>				
		
		
		
		
		
		
		
		
		