package shop.dao;

import java.util.*;

import shop.dto.ProductDTO;
import shop.mybatis.MallMapper;

import java.sql.*;

public class ProductList {
	Hashtable<String, List<ProductDTO>> ht;

	public ProductList() {
		ht = new Hashtable<>();
	}
	
	public Hashtable<String, List<ProductDTO>> getHt(){
		return ht;
	}
		
	public void selectBySpec(String pspec){
		if (ht.containsKey(pspec)) {
			return;
		}
		List<ProductDTO> list = MallMapper.selectBySpec(pspec);
		ht.put(pspec, list);
	}
	
	public void selectByCode(String code){
		if (ht.containsKey(code)) {
			return;
		}
		List<ProductDTO> list = MallMapper.selectByCode(code);
		ht.put(code, list);
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











