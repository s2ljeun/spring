package shop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.dao.CategoryDAO;
import shop.dao.ProductList;
import shop.dto.CategoryDTO;
import shop.dto.ProductDTO;

@Controller
public class MallController {
	
	@Autowired
	private ProductList productList;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping("/mall_shop.do")
	public String index_shop(HttpServletRequest req) {
		HttpSession session = req.getSession();
		ProductList plist = (ProductList)session.getAttribute("prodList");
		if (plist == null) plist = productList; //위에서 이미 만들어놨으니까 불러왔음...
		
		try {
		List<CategoryDTO> clist = categoryDAO.listCate();
		if (clist == null || clist.size()==0) {
			req.setAttribute("msg", "쇼핑몰 준비중!! 다음에 이용해 주세요");
			req.setAttribute("url", "/index_shop.do");
			return "message.jsp";
		}
		
		session.setAttribute("clist", clist);
		String upPath = session.getServletContext().getRealPath("/images");
		session.setAttribute("upPath", upPath);
		List<ProductDTO> hitList = plist.selectBySpec("HIT");
		List<ProductDTO> newList =  plist.selectBySpec("NEW");
		List<ProductDTO> bestList = plist.selectBySpec("BEST");
		req.setAttribute("hitList", hitList);
		req.setAttribute("newList", newList);
		req.setAttribute("bestList", bestList);
		session.setAttribute("prodList", plist); //plist 저장
		
		return "display/mall";
		
		}catch(SQLException e) {
			req.setAttribute("msg", "DB 오류 발생!! 관리자에게 문의하세요.");
			req.setAttribute("url", "index.do");
			return "message.jsp";
		}
	}
	
	@RequestMapping("/mall_cgProdList.do")
	public String cg_prodList(HttpServletRequest req,
			@RequestParam String code) {
		
		HttpSession session = req.getSession();
		ProductList plist = (ProductList)session.getAttribute("prodList");
		try {
			List<ProductDTO> pcode = plist.selectByCode(code);
			req.setAttribute("pcode", pcode);
			session.setAttribute("prodList", plist);
			return "display/mall_cgProdList";
			
		}catch(SQLException e) {
			req.setAttribute("msg", "DB 오류 발생!! 관리자에게 문의하세요.");
			req.setAttribute("url", "index.do");
			return "message.jsp";
		}
	}
	
	@RequestMapping("/mall_prodView.do")
	public String prod_view(HttpServletRequest req,
			@RequestParam Map<String, String> map) {
		HttpSession session = req.getSession();
		ProductList plist = (ProductList)session.getAttribute("prodList");
		ProductDTO pdto = plist.getProduct(Integer.parseInt(map.get("pnum")),
				map.get("select"));
		
		req.setAttribute("pdto", pdto);
		
		return "display/mall_prodView";
	}
	
	@RequestMapping("mall_cartAdd.do")
	public String cart_add(HttpServletRequest req,
			@RequestParam Map<String, String> map) {
		//String pnum = req.getParameter("pnum");
		//String select = req.getParameter("select");
		//String pqty = req.getParameter("pqty");
		
		HttpSession session = req.getSession();
		ProductList plist = (ProductList)session.getAttribute("prodList");
		List<ProductDTO> cart = (List)session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}
		ProductDTO dto = plist.getProduct(Integer.parseInt(map.get("pnum")),
				map.get("select"));
		for (ProductDTO pdto : cart) {
			if (pdto.getPnum() == dto.getPnum()) { //카트 안에 있는
				pdto.setPqty(pdto.getPqty() + Integer.parseInt(pqty));
				session.setAttribute("cart", cart);
				return "mall_cartList.mall";
			}
		}
		dto.setPqty(Integer.parseInt(pqty));
		cart.add(dto);
		session.setAttribute("cart", cart);
		return "mall_cartList.mall";
	} 
	}
	
}
