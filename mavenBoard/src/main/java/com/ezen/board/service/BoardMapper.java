package com.ezen.board.service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.board.dto.BoardDTO;


@Service
public class BoardMapper {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDTO> listBoard(int startRow, int endRow) {
		Map<String, Integer> map = new HashMap<>();
		map.put("start", startRow);
		map.put("end", endRow);
		
		//List<BoardDTO> list = sqlSession.selectList("listBoard", map);
		return sqlSession.selectList("listBoard", map);
	}
	


	public BoardDTO getBoard(int num, String mode) {
		if (mode.equals("content")) {
			sqlSession.update("plusReadcount", num);
		}
		return sqlSession.selectOne("getBoard", num);
	}
	
	
	public int insertBoard(BoardDTO dto) {
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
		sqlSession.update("plusRe_step", map);
		
		return sqlSession.insert("insertBoard", dto);
	}


	public int deleteBoard(Map<String, String> map) {
		BoardDTO dto = getBoard(Integer.parseInt(map.get("num")), "password");
		
		if (dto.getPasswd().equals(map.get("passwd"))) {
			return sqlSession.delete("deleteBoard", Integer.parseInt(map.get("num")));
		}else return -1;
		
	}

	
	
	public int updateBoard(BoardDTO dto) {
		BoardDTO dto2 = getBoard(dto.getNum(), "password");
		if (dto2.getPasswd().equals(dto.getPasswd())) {
			return sqlSession.update("updateBoard", dto);
		}else return -1;
	}
	
	
	
	public int getCount() {
		return sqlSession.selectOne("getCount");
	}

	
	

}





