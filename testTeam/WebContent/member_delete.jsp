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
			alert("선수를 삭제했습니다.")
			location.href="index.jsp";
		</script>
	<%	}else { %>
		<script type="text/javascript">
			alert("선수를 삭제하는데 실패하였습니다.")
			location.href="member_delete.jsp";
		</script>
	<%	} %>