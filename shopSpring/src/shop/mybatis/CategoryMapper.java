package shop.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import shop.dto.CategoryDTO;
import shop.dto.ProductDTO;


public class CategoryMapper {
	private static SqlSessionFactory sqlMapper;
	static {
		try {
			String resource = "configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new RuntimeException("DB연결 오류 발생" + e.getMessage());
		}
	}
	public static int insertCate(CategoryDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.insert("insertCate", dto);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
	public static List<CategoryDTO> listCate() {
		SqlSession session = sqlMapper.openSession();
		try {
			List<CategoryDTO> list = session.selectList("listCate");
			return list;
		}finally {
			session.close();
		}
	}
	public static int deleteCate(int cnum) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.delete("deleteCate", cnum);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
		

}





