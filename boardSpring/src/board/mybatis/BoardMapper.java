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
		
		//해시맵 만들어서 mapper.xml에 넘겨주기
		Map<String, Integer> map = new HashMap<>(); //Hashtable로도 만드시네
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
			if (dto.getNum() == 0) {
				sql = "update board set re_step = re_step + 1"; // ?
			}else {
				sql = "update board set re_step = re_step + 1 where re_step >" + dto.getRe_step();
				dto.setRe_step(dto.getRe_step()+1);
				dto.setRe_level(dto.getRe_level()+1);
			}
			
			Map<String, String> map = new Hashtable<>();
			map.put("sql", sql);
			session.update("plusRe_step", map);
			
			int res = session.insert("insertBoard", dto);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}


	public static int deleteBoard(Map<String, String> map) {
		SqlSession session = sqlMapper.openSession();
		BoardDTO dto = getBoard(Integer.parseInt(map.get("num")), "password");
		
		if (dto.getPasswd().equals(map.get("passwd"))) {
			try {
				int res = session.delete("deleteBoard", Integer.parseInt(map.get("num")));
				session.commit();
				return res;
			}finally {
				session.close();
			}
		}else return -1;
		
	}

	
	
	public static int updateBoard(BoardDTO dto) {
		SqlSession session = sqlMapper.openSession();
		BoardDTO dto2 = getBoard(dto.getNum(), "password");
		if (dto2.getPasswd().equals(dto.getPasswd())) {
			try {
				int res = session.update("updateBoard", dto);
				session.commit();
				return res;
			}finally {
				session.close();
			}
		}else return -1;
		
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





