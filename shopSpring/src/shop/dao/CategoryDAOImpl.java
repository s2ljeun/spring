package shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.dto.CategoryDTO;

public class CategoryDAOImpl implements CategoryDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertCate(CategoryDTO dto) {
		String sql  = "insert into category values(cate_seq.nextval, ?, ?)";
		Object[] values = new Object[] {dto.getCode(), dto.getCname()};
		return jdbcTemplate.update(sql, values);
	}

	@Override
	public int deleteCate(int cnum) {
		String sql = "delete from category where cnum = ?";
		return jdbcTemplate.update(sql, cnum);
	}

	@Override
	public List<CategoryDTO> listCate() {
		String sql = "select * from category";
		return jdbcTemplate.query(sql, mapper);
	}

	private MyRowMapper mapper = new MyRowMapper();
	class MyRowMapper implements RowMapper<CategoryDTO>{
		@Override
		public CategoryDTO mapRow(ResultSet rs, int count) throws SQLException {
			CategoryDTO dto = new CategoryDTO();
			dto.setCnum(rs.getInt("cnum"));
			dto.setCode(rs.getString("code"));
			dto.setCname(rs.getString("cname"));
			return dto;
		}
	}
}
