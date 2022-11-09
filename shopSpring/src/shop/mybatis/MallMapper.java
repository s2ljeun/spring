package shop.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import shop.dto.ProductDTO;

public class MallMapper {
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
	
	public static List<ProductDTO> selectBySpec(String pspec){
		SqlSession session = sqlMapper.openSession();
		try {
			List<ProductDTO> list = session.selectList("selectBySpec", pspec);
			return list;
		}finally {
			session.close();
		}
	}
	
	public static List<ProductDTO> selectByCode(String code){
		SqlSession session = sqlMapper.openSession();
		try {
			List<ProductDTO> list = session.selectList("selectByCode", code+"%");
			return list;
		}finally {
			session.close();
		}
	}
}
