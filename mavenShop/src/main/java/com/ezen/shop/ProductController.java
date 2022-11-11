package com.ezen.shop;

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

import com.ezen.shop.dto.CategoryDTO;
import com.ezen.shop.dto.ProductDTO;
import com.ezen.shop.service.CategoryMapper;
import com.ezen.shop.service.ProductMapper;

@Controller
public class ProductController {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@RequestMapping(value="/prod_input.do", method=RequestMethod.GET)
	public String prod_input(HttpServletRequest req) {
		List<CategoryDTO> clist = categoryMapper.listCate();
		if (clist == null || clist.size() == 0) {
			req.setAttribute("msg", "ī�װ����� ���� ����� �ּ���!!");
			req.setAttribute("url", "index_shop.do");
			return "message";
		}
		req.setAttribute("listCate", clist);
		return "shop/admin/prod_input";
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
			req.setAttribute("msg", "�̹����� �Է��� �ּ���!!");
			req.setAttribute("url", "prod_input.do");
			return "message";
		}
		HttpSession session = req.getSession();
		//��upPath
		String upPath = session.getServletContext().getRealPath("/images");
		File file = new File(upPath, filename);
		try {
			mf.transferTo(file);
		}catch(IOException e) {
			req.setAttribute("msg", "�̹����� ���ε�� �����߻�!! �ٽ� �Է��� �ּ���");
			req.setAttribute("url", "prod_input.do");
			return "message";
		}
		//��upPath
		session.setAttribute("upPath", upPath);
		dto.setPimage(filename);
		dto.setPcategory_fk(req.getParameter("pcatecode") + req.getParameter("pcode"));
		
		int res = productMapper.insertProd(dto);
		if (res>0) {
			req.setAttribute("msg", "��ǰ ��� ����!! ��ǰ ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "��ǰ ��� ����!! ��ǰ ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_input.do");
		}
		return "message";
	}
	/*
	@RequestMapping("/prod_list.do")
	public String prod_list(HttpServletRequest req) {
		//List<ProductDTO> plist = productDAO.listProd();
		List<ProductDTO> plist = ProductMapper.listProd();
		String upPath = req.getServletContext().getRealPath("/images");
		req.setAttribute("upPath", upPath);
		req.setAttribute("listProd", plist);
		return "admin/prod_list";
	}
	
	@RequestMapping("/prod_view.do")
	public String prod_view(HttpServletRequest req, @RequestParam int pnum) {
		//ProductDTO dto = productDAO.getProduct(pnum);
		ProductDTO dto = ProductMapper.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		String upPath = req.getServletContext().getRealPath("/images");
		req.setAttribute("upPath", upPath);
		return "admin/prod_view";		
	}
	
	
	@RequestMapping("/prod_delete.do")
	public String prod_delete(HttpServletRequest req, @RequestParam Map<String, String> map){
		//int res = productDAO.deleteProd(Integer.parseInt(map.get("pnum")));
		int res = ProductMapper.deleteProd(Integer.parseInt(map.get("pnum")));
		if (res>0) {
			HttpSession session = req.getSession();
			String upPath = (String)session.getAttribute("upPath");
			File file = new File(upPath, map.get("pimage"));
			if (file.exists()) {
				file.delete();
				req.setAttribute("msg", "��ǰ ����(�̹����� ����) ����!! ��ǰ ����������� �̵��մϴ�.");
			}else {
				req.setAttribute("msg", "��ǰ ����(�̹��� ���� ����) ����!! ��ǰ ����������� �̵��մϴ�.");
			}
		}else {
			req.setAttribute("msg", "��ǰ ���� ����!! ��ǰ ����������� �̵��մϴ�.");
		}
		req.setAttribute("url", "prod_list.do");
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/prod_update.do", method=RequestMethod.GET)
	public String prod_update(HttpServletRequest req, @RequestParam int pnum) {
		//ProductDTO dto = productDAO.getProduct(pnum);
		ProductDTO dto = ProductMapper.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		String upPath = req.getServletContext().getRealPath("/images");
		req.setAttribute("upPath", upPath);
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
				req.setAttribute("msg", "�̹����� ���ε�� �����߻�!! �ٽ� �Է��� �ּ���");
				req.setAttribute("url", "prod_input.do");
				return "forward:message.jsp";
			}
			dto.setPimage(filename);
		}
		//int res = productDAO.updateProd(dto);
		int res = ProductMapper.updateProd(dto);
		if (res>0) {
			req.setAttribute("msg", "��ǰ ���� ����!! ��ǰ ����������� �̵��մϴ�.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "��ǰ ���� ����!! ��ǰ ������������ �̵��մϴ�.");
			req.setAttribute("url", "prod_update.do?pnum=" + dto.getPnum());
		}
		return "forward:message.jsp";
	}
	*/
}
	







