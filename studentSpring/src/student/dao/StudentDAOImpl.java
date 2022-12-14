package student.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import student.dto.StudentDTO;

public class StudentDAOImpl implements StudentDAO{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertStudent(StudentDTO dto) {
		String sql = "insert into student values (?,?,?)";
		Object[] values = new Object[] {dto.getId(), dto.getName(), dto.getCname()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int deleteStudent(String id) {
		String sql = "delete from student where id = ?";
		int res = jdbcTemplate.update(sql, id); // ?가 한 개일 때는 값을 직접 넣어도 된다
		return res;
	}
	
	class MyRowMapper implements RowMapper<StudentDTO>{ //일반중첩클래스 만들기
		@Override
		public StudentDTO mapRow(ResultSet rs, int count) throws SQLException {
			StudentDTO dto = new StudentDTO();
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setCname(rs.getString("cname"));
			return dto;
		}
	}

	@Override
	public List<StudentDTO> findStudent(String name) {
		String sql = "select * from student where name = ?";
		MyRowMapper mapper = new MyRowMapper();		
		List<StudentDTO> list = jdbcTemplate.query(sql, mapper, name);
		return list;
		
		/*
		RowMapper mapper = new RowMapper<StudentDTO>() { // interface 객체 만들기 -> 익명중첩클래스 or 자식의 클래스를 상속받아 만든다 / 익명중첩클래스, 제너릭 활용 <StudentDTO>붙이기

			@Override
			public StudentDTO mapRow(ResultSet rs, int count) throws SQLException { //makeList의 역할 / rs에 row 하나씩 저장, count에 몇 번째 row인지 저장
				StudentDTO dto = new StudentDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setCname(rs.getString("cname"));
				return dto;
			}
		*/
		};

	@Override
	public List<StudentDTO> listStudent() {
		String sql = "select * from student";
		MyRowMapper mapper = new MyRowMapper();
		List<StudentDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

}
