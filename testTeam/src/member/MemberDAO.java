package member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import team.TeamDTO;

public class MemberDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static DataSource ds;
	static {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public MemberDAO() {}
	
	public int insertMember(String name, TeamDTO dto) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "insert into spring_member values(seq_member_id.nextval, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, dto.getId());
			int res = ps.executeUpdate();
			return res;
		}finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	protected List<MemberDTO> makeList(ResultSet rs) throws SQLException {
		List<MemberDTO> list = new ArrayList<>();
		while(rs.next()) {
			MemberDTO mdto = new MemberDTO();
			mdto.setId(rs.getInt("member_id"));
			mdto.setName(rs.getString("memberName"));
			TeamDTO tdto = new TeamDTO();
			tdto.setId(rs.getInt("team_id"));
			tdto.setName(rs.getString("teamName"));
			mdto.setTeam(tdto);
			list.add(mdto);
		}
		return list;
	}
	
	public List<MemberDTO> listMember(Integer team_id) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select member_id, spring_member.name as memberName, "
					+"spring_team.team_id, spring_team.name as teamName "
					+"from spring_member, spring_team "
					+"	where spring_member.team_id = spring_team.team_id and spring_team.team_id = ?";
			//join. table 2개를 합쳐서 값을 꺼낸다
			ps = con.prepareStatement(sql);
			ps.setInt(1, team_id);
			rs = ps.executeQuery();
			List<MemberDTO> list = makeList(rs);
			return list;
		}finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	public int deleteMember(String team_id) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "delete from spring_member where member_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, new Integer(team_id));
			int res = ps.executeUpdate();
			return res;
		}finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	public int editMember(String id, TeamDTO dto) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "update spring_member set team_id = ? where member_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getId());
			ps.setInt(2, new Integer(id));
			int res = ps.executeUpdate();
			return res;
		}finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	
}





