package team;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;
import javax.naming.*;
public class TeamDAO {
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
	
	public TeamDAO() {}
	
	public List<TeamDTO> listTeam() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from spring_team";
			//sql = "select team_id id, name name from spring_team";
			// team_id를 셀렉트할 건데 id라는 별칭으로 셀렉트
			// 이 경우 makeList메소드에서 getInt("id") 만으로 적을 수 있다
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<TeamDTO> list = makeList(rs);
			return list;
		}finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	protected List<TeamDTO> makeList(ResultSet rs) throws SQLException {
		List<TeamDTO> list = new ArrayList<>();
		while(rs.next()) {
			TeamDTO dto = new TeamDTO();
			dto.setId(rs.getInt("team_id"));
			dto.setName(rs.getString("name"));
			list.add(dto);
		}
		return list;
	}
	
	public TeamDTO getTeam(Integer id) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from spring_team where team_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			List<TeamDTO> list = makeList(rs);
			if (list.size() == 0) return null;
			return list.get(0);
		}finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
}






