package shop;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import shop.dao.CategoryDAO;
import shop.dao.ProductDAO;
import shop.dto.CategoryDTO;
import shop.dto.ProductDTO;

@Controller
public class ProductController {
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value="/prod_input.do", method=RequestMethod.GET)
	public String prod_input(HttpServletRequest req) {
		List<CategoryDTO> clist = categoryDAO.listCate();
		if (clist == null || clist.size() == 0) {
			req.setAttribute("msg", "카테고리를 먼저 등록해 주세요!!");
			req.setAttribute("url", "index_shop.do");
			return "forward:message.jsp";
		}
		req.setAttribute("listCate", clist);
		return "admin/prod_input";
	}
	
	@RequestMapping(value="/prod_input.do", method=RequestMethod.POST)
	public String prod_input(HttpServletRequest req, 
			@ModelAttribute ProductDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setPimage("");
		}
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("pimage");
		String filename = mf.getOriginalFilename();
		if (filename == null || filename.trim().equals("")) {
			req.setAttribute("msg", "이미지를 입력해 주세요!!");
			req.setAttribute("url", "prod_input.do");
			return "forward:message.jsp";
		}
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		File file = new File(upPath, filename);
		try {
			mf.transferTo(file);
		}catch(IOException e) {
			req.setAttribute("msg", "이미지를 업로드시 오류발생!! 다시 입력해 주세요");
			req.setAttribute("url", "prod_input.do");
			return "forward:message.jsp";
		}
		session.setAttribute("upPath", upPath);
		dto.setPimage(filename);
		dto.setPcategory_fk(req.getParameter("pcatecode") + req.getParameter("pcode"));
		int res = productDAO.insertProd(dto);
		if (res>0) {
			req.setAttribute("msg", "상품 등록 성공!! 상품 목록페이지로 이동합니다.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "상품 등록 실패!! 상품 등록페이지로 이동합니다.");
			req.setAttribute("url", "prod_input.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping("/prod_list.do")
	public String prod_list(HttpServletRequest req) {
		List<ProductDTO> plist = productDAO.listProd();
		req.setAttribute("listProd", plist);
		return "admin/prod_list";
	}
	
	@RequestMapping("/prod_view.do")
	public String prod_view(HttpServletRequest req, @RequestParam int pnum) {
		ProductDTO dto = productDAO.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		return "admin/prod_view";		
	}
	
	
	@RequestMapping("/prod_delete.do")
	public String prod_delete(HttpServletRequest req, @RequestParam Map<String, String> map){
		int res = productDAO.deleteProd(Integer.parseInt(map.get("pnum")));
		if (res>0) {
			HttpSession session = req.getSession();
			String upPath = (String)session.getAttribute("upPath");
			File file = new File(upPath, map.get("pimage"));
			if (file.exists()) {
				file.delete();
				req.setAttribute("msg", "상품 삭제(이미지도 삭제) 성공!! 상품 목록페이지로 이동합니다.");
			}else {
				req.setAttribute("msg", "상품 삭제(이미지 삭제 실패) 성공!! 상품 목록페이지로 이동합니다.");
			}
		}else {
			req.setAttribute("msg", "상품 삭제 실패!! 상품 목록페이지로 이동합니다.");
		}
		req.setAttribute("url", "prod_list.do");
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/prod_update.do", method=RequestMethod.GET)
	public String prod_update(HttpServletRequest req, @RequestParam int pnum) {
		ProductDTO dto = productDAO.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		return "admin/prod_update";
	}
	
	@RequestMapping(value="/prod_update.do", method=RequestMethod.POST)
	public String prod_update_ok(HttpServletRequest req, 
									@ModelAttribute ProductDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setPimage("");
		}
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("pimage");
		String filename = mf.getOriginalFilename();
		if (filename == null || filename.trim().equals("")) {
			dto.setPimage(req.getParameter("pimage2"));
		}else {
			HttpSession session = req.getSession();
			String upPath = session.getServletContext().getRealPath("/images");
			File file = new File(upPath, filename);
			try {
				mf.transferTo(file);
			}catch(IOException e) {
				req.setAttribute("msg", "이미지를 업로드시 오류발생!! 다시 입력해 주세요");
				req.setAttribute("url", "prod_input.do");
				return "forward:message.jsp";
			}
			dto.setPimage(filename);
		}
		int res = productDAO.updateProd(dto);
		if (res>0) {
			req.setAttribute("msg", "상품 수정 성공!! 상품 목록페이지로 이동합니다.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "상품 수정 실패!! 상품 수정페이지로 이동합니다.");
			req.setAttribute("url", "prod_update.do?pnum=" + dto.getPnum());
		}
		return "forward:message.jsp";
	}
}
	








