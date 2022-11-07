package shop.dao;

import java.util.List;

import shop.dto.ProductDTO;

public interface ProductDAO {
	public int insertProd(ProductDTO dto);
	public int deleteProd(int pnum);
	public int updateProd(ProductDTO dto);
	public ProductDTO getProduct(int pnum);
	public List<ProductDTO> listProd();
}
