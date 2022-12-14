package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import member.dto.MemberDTO;
import member.mybatis.MemberMapper;

public class MemberDAOImpl implements MemberDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private MyRowMapper mapper = new MyRowMapper();
	
	class MyRowMapper implements RowMapper<MemberDTO>{
		@Override
		public MemberDTO mapRow(ResultSet rs, int count) throws SQLException {
			MemberDTO dto = new MemberDTO();
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setId(rs.getString("id"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setSsn1(rs.getString("ssn1"));
			dto.setSsn2(rs.getString("ssn2"));
			dto.setEmail(rs.getString("email"));
			dto.setHp1(rs.getString("hp1"));
			dto.setHp2(rs.getString("hp2"));
			dto.setHp3(rs.getString("hp3"));
			dto.setJoindate(rs.getString("joindate"));
			return dto;
		}
	}

	@Override
	public List<MemberDTO> listMember() {
		String sql = "select * from jsp_member";
		List<MemberDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public int insertMember(MemberDTO dto) {
		String sql = "insert into jsp_member values(member_seq.nextval, ?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] values = new Object[] {dto.getName(), dto.getId(), dto.getPasswd(), dto.getSsn1(),
				dto.getSsn2(), dto.getEmail(), dto.getHp1(), dto.getHp2(), dto.getHp3()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public boolean checkMember(String name, String ssn1, String ssn2) {
		String sql = "select * from jsp_member where ssn1=? and ssn2=?";
		try {
			Object[] values = new Object[] {ssn1, ssn2};
			MemberDTO dto = jdbcTemplate.queryForObject(sql, values, mapper);
			return true;
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
	}

	@Override
	public int deleteMember(int no) {
		String sql = "delete from jsp_member where no = ?";
		
		return jdbcTemplate.update(sql, no);
	}

	@Override
	public MemberDTO getMember(int no) {
		String sql = "select * from jsp_member where no = ?";
		MemberDTO dto = jdbcTemplate.queryForObject(sql, mapper, no);
		return dto;
	}

	@Override
	public MemberDTO getMember(String id) {
		String sql = "select * from jsp_member where id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, mapper, id);
		}catch(IncorrectResultSizeDataAccessException e){
			return null;
		}
	}

	@Override
	public int updateMember(MemberDTO dto) {
		String sql = "update jsp_member set passwd=?, email=?, hp1=?, hp2=?, hp3=? where no = ?";
		Object[] values = new Object[] {dto.getPasswd(), dto.getEmail(), dto.getHp1(), dto.getHp2(), dto.getHp3(), dto.getNo()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public List<MemberDTO> findMember(String search, String searchString) {
		String sql = "select * from jsp_member where "+search+" = ?";
		List<MemberDTO> list = jdbcTemplate.query(sql, mapper, searchString);
		return list;
	}

	@Override
	public String searchMember(String name, String ssn1, String ssn2, String id) {
		String sql = null;
		Object[] values;
		if (id == null) {
			sql = "select * from jsp_member where name=? and ssn1=? and ssn2=?";
			values = new Object[] {name, ssn1, ssn2};
		}else {	
			sql = "select * from jsp_member where name=? and ssn1=? and ssn2=? and id=?";
			values = new Object[] {name, ssn1, ssn2, id};
		}
		try {
			MemberDTO dto = jdbcTemplate.queryForObject(sql, values, mapper);
			String msg = null;
			if (id == null) msg = "?????? : " + dto.getId() + " ??????.";
			else msg = "???????? : " + dto.getPasswd() + " ??????.";
			return msg;
		}catch(EmptyResultDataAccessException e) {
			return "?????? ???????? ???????? ?????? ????????.";
		}
	}

}
