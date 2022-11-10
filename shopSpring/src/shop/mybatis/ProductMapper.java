package shop.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import shop.dto.ProductDTO;

public class ProductMapper {
	private static SqlSessionFactory sqlMapper;
	static {
		try {
			String resource = "configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new RuntimeException("DB 연결 오류 발생!!" + e.getMessage());
		}
	}
	
	public static int insertProd(ProductDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.insert("insertProd", dto);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
	
	public static List<ProductDTO> listProd() {
		SqlSession session = sqlMapper.openSession();
		try {
			List<ProductDTO> list = session.selectList("listProd");
			return list;
		}finally {
			session.close();
		}
	}
	
	public static ProductDTO getProduct(int pnum) {
		SqlSession session = sqlMapper.openSession();
		try {
			ProductDTO dto = session.selectOne("getProduct", pnum);
			return dto;
		}finally {
			session.close();
		}
	}
	
	public static int deleteProd(int pnum) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.delete("deleteProd", pnum);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
	
	public static int updateProd(ProductDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.update("updateProd", dto);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
}













