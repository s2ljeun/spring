package shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.dto.CategoryDTO;
import shop.mybatis.CategoryMapper;

@Controller
public class CategoryController {
	//@Autowired
	//private CategoryDAO categoryDAO;
	
	@RequestMapping(value="/cate_input.do", method=RequestMethod.GET)
	public String cate_input() {
		return "admin/cate_input";
	}
	
	@RequestMapping(value="/cate_input.do", method=RequestMethod.POST)
	public String cate_input_ok(HttpServletRequest req, 
								@ModelAttribute CategoryDTO dto) {
		//int res = categoryDAO.insertCate(dto);
		int res = CategoryMapper.insertCate(dto);
		if (res>0) {
			req.setAttribute("msg", "ī�װ� ��� ����!! ī�װ� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "ī�װ� ��� ����!! ī�װ� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_input.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping("/cate_list.do")
	public String cate_list(HttpServletRequest req) {
		//List<CategoryDTO> list = categoryDAO.listCate();
		List<CategoryDTO> list = CategoryMapper.listCate();
		req.setAttribute("listCate", list);
		return "admin/cate_list";
	}
	
	@RequestMapping("/cate_delete.do")
	public String cate_delete(HttpServletRequest req, int cnum) {
		//int res = categoryDAO.deleteCate(cnum);
		int res = CategoryMapper.deleteCate(cnum);
		if (res>0) {
			req.setAttribute("msg", "ī�װ� ���� ����!! ī�װ� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "ī�װ� ���� ����!! ī�װ� ����������� �̵��մϴ�.");
			req.setAttribute("url", "cate_list.do");
		}
		return "forward:message.jsp";
	}
}





