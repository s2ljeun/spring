<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="team.*"%>
<!-- member_insert_ok.jsp -->
<%
		request.setCharacterEncoding("EUC-KR");
		String name = request.getParameter("name");
		String team_id = request.getParameter("team_id");
		if (name == null || name.trim().equals("")){%>
			<script type="text/javascript">
				alert("�����̸��� �Է��� �ּ���!!")
				location.href="member_insert.jsp";
			</script>
<%		return;
		}%>

<jsp:useBean id="tdao" class="team.TeamDAO"/>
<%
		TeamDTO dto = tdao.getTeam(new Integer(team_id));
		//dto ��ü�� ��������� = ����� �����ϴ� team_id�� �Է��ߴ�
		if (dto == null) {%>
			<script type="text/javascript">
				alert("����ȣ�� ��Ȯ�� �Է��� �ּ���!!")
				location.href="member_insert.jsp";
			</script>
<%		return; 
		}%>
<jsp:useBean id="mdao" class="member.MemberDAO"/>		
<%
		int res = mdao.insertMember(name, dto);
		if (res>0){%>
			<script type="text/javascript">
				alert("<%=name%> ������ <%=dto.getName()%> ���� ����Ͽ����ϴ�.")
				location.href="index.jsp";
			</script>
<%	}else { %>
			<script type="text/javascript">
				alert("<%=name%> ������ <%=dto.getName()%> ���� ����ϴµ� �����Ͽ����ϴ�.")
				location.href="member_insert.jsp";
			</script>
<%	} %>				
		
		
		
		
		
		
		
		
		