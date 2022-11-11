package com.ezen.shop.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dto.ProductDTO;

@Service
public class ProductMapper {
	@Autowired
	private SqlSession sqlSession;
	
	public int insertProd(ProductDTO dto) {
		return sqlSession.insert("insertProd", dto);
	}
	
	/*
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
	*/
}













