package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dao.CategoryDAO;
import shop.dao.ProductList;
import shop.dto.CategoryDTO;
import shop.dto.ProductDTO;

@Controller
public class MallController {

	private ProductList productList;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/mall_shop.do")
	public String mall_shop(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("upPath") == null) {
			session.setAttribute("upPath", session.getServletContext().getRealPath("/images"));
		}
		if (session.getAttribute("clist") == null) {
			List<CategoryDTO> clist = categoryDAO.listCate();
			session.setAttribute("clist", clist);
		}
		ProductList plist = (ProductList)session.getAttribute("prodList");
		if (plist == null) {
			plist = new ProductList(jdbcTemplate);
		}

		List<ProductDTO> hitList, bestList, newList;
		hitList = plist.selectBySpec("HIT");
		bestList = plist.selectBySpec("BEST");
		newList = plist.selectBySpec("NEW");
		session.setAttribute("prodList", plist);
		req.setAttribute("hitList", hitList);
		req.setAttribute("bestList", bestList);
		req.setAttribute("newList", newList);
		return "display/mall";
	}
	
	@RequestMapping("/mall_cgProdList.do")
	public String mall_cgProdList(HttpServletRequest req, @RequestParam String code) {
		HttpSession session = req.getSession();
		ProductList plist = (ProductList)session.getAttribute("prodList");
		if (plist == null) {
			plist = new ProductList(jdbcTemplate);
		}
		List<ProductDTO> prolist = plist.selectByCode(code);
		session.setAttribute("prodList", plist);
		req.setAttribute("pcode", prolist);
		return "display/mall_cgProdList";
	}
	
	@RequestMapping("/mall_prodView.do")
	public String mall_prodView(HttpServletRequest req, @RequestParam Map<String, String> map) {
		HttpSession session = req.getSession();
		ProductList plist = (ProductList)session.getAttribute("prodList");
		if (plist == null) {
			plist = new ProductList(jdbcTemplate);
		}
		ProductDTO pdto = plist.getProduct(Integer.parseInt(map.get("pnum")), map.get("select"));
		req.setAttribute("pdto", pdto);
		return "display/mall_prodView";
	}
	
	@RequestMapping("/mall_cartAdd.do")
	public String mall_cartAdd(HttpServletRequest req, @RequestParam Map<String, String> map) {
		HttpSession session = req.getSession();
		List<ProductDTO> cartList = (List)session.getAttribute("cart");
		if (cartList == null) {
			cartList = new ArrayList<>();
		}
		ProductList plist = (ProductList)session.getAttribute("prodList");
		
		ProductDTO pdto = plist.getProduct(Integer.parseInt(map.get("pnum")), map.get("select"));
		pdto.setPqty(Integer.parseInt(map.get("pqty")));
		for(ProductDTO dto : cartList) {
			if (dto.getPnum() == pdto.getPnum()) {
				dto.setPqty(dto.getPqty() + pdto.getPqty());
				session.setAttribute("cart", cartList);
				return "forward:mall_cartList.do";
			}
		}
		cartList.add(pdto);
		session.setAttribute("cart", cartList);
		return "forward:mall_cartList.do";
	}
	
	@RequestMapping("/mall_cartList.do")
	public String mall_cartList(HttpServletRequest req) {
		return "display/mall_cartList";
	}
	
	@RequestMapping("/mall_cartDel.do")
	public String mall_cartDel(HttpServletRequest req, @RequestParam int pnum) {
		HttpSession session = req.getSession();
		List<ProductDTO> cartList = (List)session.getAttribute("cart");
		for(ProductDTO dto : cartList) {
			if (pnum == dto.getPnum()) {
				cartList.remove(dto);
				break;
			}
		}
		return "forward:mall_cartList.do";		
	}
	
	@RequestMapping("/mall_cartEdit.do")
	public String mall_cartEdit(HttpServletRequest req, @RequestParam Map<String, String> map) {
		HttpSession session = req.getSession();
		List<ProductDTO> cartList = (List)session.getAttribute("cart");
		for(ProductDTO dto : cartList) {
			if (Integer.parseInt(map.get("pnum")) == dto.getPnum()) {
				if (Integer.parseInt(map.get("pqty")) == 0) {
					cartList.remove(dto);
					break;
				}else {
					dto.setPqty(Integer.parseInt(map.get("pqty")));
					break;
				}
			}
		}
		
		return "forward:mall_cartList.do";
	}
}














