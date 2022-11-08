package student.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import student.dto.StudentDTO;

public class StudentMapper {
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
	
	
	
	public static List<StudentDTO> listStudent() {
		SqlSession session = sqlMapper.openSession();
		try {
			List<StudentDTO> list = session.selectList("listStudent");
			return list;
		}finally {
			session.close();
		}
	}
	
	
	
	public static List<StudentDTO> findStudent(String name) {
		SqlSession session = sqlMapper.openSession();
		try {
			List<StudentDTO> list = session.selectList("findStudent", name);
			return list;
		}finally {
			session.close();
		}
	}



	public static int deleteStudent(String id) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.delete("deleteStudent", id);
			session.commit(); // insert, delete, update는 반드시 commit을 수동으로 해야만 적용된다.
			return res;
		}finally {
			session.close();
		}
	}
	
	
	
	public static int insertStudent(StudentDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.insert("insertStudent", dto);
			session.commit(); // insert, delete, update는 반드시 commit을 수동으로 해야만 적용된다.
			return res;
		}finally {
			session.close();
		}
	}
	
	
	
}
