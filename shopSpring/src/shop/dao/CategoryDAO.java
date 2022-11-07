package shop.dao;

import java.util.List;

import shop.dto.CategoryDTO;

public interface CategoryDAO {
	public int insertCate(CategoryDTO dto);
	public int deleteCate(int cnum);
	public List<CategoryDTO> listCate();
	
}
