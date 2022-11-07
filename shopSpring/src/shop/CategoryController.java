package shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.dao.CategoryDAO;
import shop.dto.CategoryDTO;

@Controller
public class CategoryController {
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value="/cate_input.do", method=RequestMethod.GET)
	public String cate_input() {
		return "admin/cate_input";
	}
	
	@RequestMapping(value="/cate_input.do", method=RequestMethod.POST)
	public String cate_input_ok(HttpServletRequest req, 
								@ModelAttribute CategoryDTO dto) {
		int res = categoryDAO.insertCate(dto);
		if (res>0) {
			req.setAttribute("msg", "카테고리 등록 성공!! 카테고리 목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "카테고리 등록 실패!! 카테고리 등록페이지로 이동합니다.");
			req.setAttribute("url", "cate_input.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping("/cate_list.do")
	public String cate_list(HttpServletRequest req) {
		List<CategoryDTO> list = categoryDAO.listCate();
		req.setAttribute("listCate", list);
		return "admin/cate_list";
	}
	
	@RequestMapping("/cate_delete.do")
	public String cate_delete(HttpServletRequest req, int cnum) {
		int res = categoryDAO.deleteCate(cnum);
		if (res>0) {
			req.setAttribute("msg", "카테고리 삭제 성공!! 카테고리 목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "카테고리 삭제 실패!! 카테고리 목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}
		return "forward:message.jsp";
	}
}





