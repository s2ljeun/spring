package com.ezen.shop.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dto.CategoryDTO;

@Service
public class CategoryMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertCate(CategoryDTO dto) {
		return sqlSession.insert("insertCate", dto);
	}

	public List<CategoryDTO> listCate() {
		return sqlSession.selectList("listCate");
	}
	
	public int deleteCate(int cnum) {
		return sqlSession.delete("deleteCate", cnum);
	}
}













