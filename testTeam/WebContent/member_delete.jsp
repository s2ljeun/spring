<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!-- member_delete.jsp -->
<%
	request.setCharacterEncoding("EUC-KR");
	String team_id = request.getParameter("id");
%>

<jsp:useBean id="mdao" class="member.MemberDAO"/>

<%
	int res = mdao.deleteMember(team_id);
	if (res>0){%>
		<script type="text/javascript">
			alert("������ �����߽��ϴ�.")
			location.href="index.jsp";
		</script>
	<%	}else { %>
		<script type="text/javascript">
			alert("������ �����ϴµ� �����Ͽ����ϴ�.")
			location.href="member_delete.jsp";
		</script>
	<%	} %>