package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	MyRowMapper mapper = new MyRowMapper();

	class MyRowMapper implements RowMapper<BoardDTO>{
		@Override
		public BoardDTO mapRow(ResultSet rs, int count) throws SQLException {
			BoardDTO dto = new BoardDTO();
			dto.setNum(rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			dto.setRe_step(rs.getInt("re_step"));
			dto.setRe_level(rs.getInt("re_level"));
			return dto;
		}
	}
	
	@Override
	public List<BoardDTO> listBoard(int start, int end) {
		String sql  = "select * from (select rownum rn, A.* from "
				+ "(select * from board order by re_step asc)A) "
				+ "where rn between ? and ?";
		Object[] values = new Object[] {start, end};
		List<BoardDTO> list = jdbcTemplate.query(sql, values, mapper);
		return list;
	}

	protected void plusReadcount(int num) {
		String sql = "update board set readcount=readcount+1 where num = ?";
		jdbcTemplate.update(sql, num);
	}
	
	@Override
	public BoardDTO getBoard(int num, String mode) {
		if (mode.equals("content")) {
			plusReadcount(num);
		}
		String sql = "select * from board where num = ?";
		BoardDTO dto = jdbcTemplate.queryForObject(sql, mapper, num);
		return dto;
	}

	@Override
	public int insertBoard(BoardDTO dto) {
		String sql = null;
		if (dto.getNum() == 0) {
			sql = "update board set re_step = re_step + 1";
			jdbcTemplate.update(sql);
		}else {
			sql = "update board set re_step = re_step + 1 where re_step > ?";
			jdbcTemplate.update(sql, dto.getRe_step());
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
		}
		sql = "insert into board values(board_seq.nextval, ?,?,?,?,sysdate,0,?,?,?,?)";
		Object[] values = new Object[] {dto.getWriter(), dto.getEmail(), dto.getSubject(),
				dto.getPasswd(), dto.getContent(), dto.getIp(), dto.getRe_step(), dto.getRe_level()	};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int deleteBoard(int num, String passwd) {
		BoardDTO dto = getBoard(num, "password");
		if (dto.getPasswd().equals(passwd)) {
			int res = jdbcTemplate.update("delete from board where num = ?", num);
			return res;
		}else {
			return -1;
		}
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		BoardDTO dto2 = getBoard(dto.getNum(), "password");
		if (dto2.getPasswd().equals(dto.getPasswd())) {
			String sql = "update board set subject=?, email=?, content=? where num = ?";
			Object[] values = new Object[] {dto.getSubject(), dto.getEmail(), dto.getContent(), dto.getNum()};
			int res = jdbcTemplate.update(sql, values);
			return res;
		}else {
			return -1;
		}
	}

	@Override
	public int getCount() {
		String sql = "select count(*) from board";
		int count = jdbcTemplate.queryForInt(sql);
		return count;
	}

}






