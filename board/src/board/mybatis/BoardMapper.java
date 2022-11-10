package board.mybatis;

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

import board.dto.BoardDTO;

public class BoardMapper {
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
	
	public static List<BoardDTO> listBoard(int startRow, int endRow) {
		SqlSession session = sqlMapper.openSession();
		
		Map<String, Integer> map = new HashMap<>(); //Hashtable
		map.put("start", startRow);
		map.put("end", endRow);
		
		try {
			List<BoardDTO> list = session.selectList("listBoard", map);
			return list;
		}finally {
			session.close();
		}
	}
	


	public static BoardDTO getBoard(int num, String mode) {
		SqlSession session = sqlMapper.openSession();
		try {
			if (mode.equals("content")) {
				session.update("plusReadcount", num);
				session.commit();
			}
			BoardDTO dto = session.selectOne("getBoard", num);
			return dto;
		}finally {
			session.close();
		}
	}
	
	
	public static int insertBoard(BoardDTO dto) {
		SqlSession session = sqlMapper.openSession();
		
		try {
			String sql = null;
			
			int res = session.insert("insertBoard", dto);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
	
	
	
	public static int getCount() {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.selectOne("getCount");
			return res;
		}finally {
			session.close();
		}
	}

	
	

}





