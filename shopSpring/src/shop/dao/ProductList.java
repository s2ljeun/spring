package shop.dao;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.dao.ProductDAOImpl.MyRowMapper;
import shop.dto.ProductDTO;

import java.sql.*;

public class ProductList {
	Hashtable<String, List<ProductDTO>> ht;
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private PreparedStatement ps;
	private ResultSet rs;
	
	public ProductList() {
		ht = new Hashtable<>();
	}

	private MyRowMapper mapper = new MyRowMapper();
	class MyRowMapper implements RowMapper<ProductDTO>{

		@Override
		public ProductDTO mapRow(ResultSet rs, int co) throws SQLException {
			ProductDTO dto = new ProductDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setPname(rs.getString("pname"));
			dto.setPcategory_fk(rs.getString("pcategory_fk"));
			dto.setPcompany(rs.getString("pcompany"));
			dto.setPimage(rs.getString("pimage"));
			dto.setPqty(rs.getInt("pqty"));
			dto.setPrice(rs.getInt("price"));
			dto.setPspec(rs.getString("pspec"));
			dto.setPcontents(rs.getString("pcontents"));
			dto.setPoint(rs.getInt("point"));
			dto.setPinputdate(rs.getString("pinputdate"));
			return dto;
		}
		
	}
	
	public List<ProductDTO> selectBySpec(String pspec) throws SQLException {
		if (ht.containsKey(pspec)) {
			return ht.get(pspec);
		}
		
		try {
			String sql = "select * from product where pspec = ?";			
			List<ProductDTO> list = jdbcTemplate.query(sql, mapper, pspec);			
			ht.put(pspec, list);
			
			return list;

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public List<ProductDTO> selectByCode(String code) throws SQLException {
		if (ht.containsKey(code)) {
			return ht.get(code);
		}
		
		try {
			String sql = "select * from product where pcategory_fk like ?";
			List<ProductDTO> list = jdbcTemplate.query(sql, mapper, code+"%");

			ht.put(code, list);
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ProductDTO getProduct(int pnum, String select) {
		List<ProductDTO> list = ht.get(select);
		for(ProductDTO dto : list) {
			if (pnum == dto.getPnum()) {
				return dto;
			}
		}
		return null;
	}
	
}











