package com.ezen.student;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.student.dto.StudentDTO;
import com.ezen.student.service.StudentMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired //Service ������̼����� ����� ��
	StudentMapper studentMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) // ������ > �����ϸ�
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate ); // ������Ʈ�� ����
		
		return "index";
	}
	
	@RequestMapping("/student_index.do")
	public String student_index() {
		return "student/student";
	}
	
	@RequestMapping(value="/student_list.do")
	public String student_list(HttpServletRequest req) {
		List<StudentDTO> list = studentMapper.listStudent();
		req.setAttribute("listStudent", list);
		return "student/list";
	}
	
	@RequestMapping(value="/student_find.do")
	public String student_find(HttpServletRequest req, @RequestParam(required = false) String name) {
		List<StudentDTO> find = studentMapper.findStudent(name);
		req.setAttribute("listStudent", find);
		return "student/list";
	}
	
	@RequestMapping(value="/student_delete.do")
	public String student_delete(HttpServletRequest req, @RequestParam String id) {
		int res = studentMapper.deleteStudent(id);
		if (res>0) {
			req.setAttribute("msg", "�л���������!! �л������������ �̵��մϴ�.");
			req.setAttribute("url", "student_list.do");
		}else {
			req.setAttribute("msg", "�л���������!! �л������������� �̵��մϴ�.");
			req.setAttribute("url", "student_index.do");
		}
		return "message";
	}
	
	@RequestMapping(value="/student_insert.do")
	public String student_insert(HttpServletRequest req, 
			@ModelAttribute StudentDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setId("");
			dto.setName("");
			dto.setCname("");
		}
		int res = studentMapper.insertStudent(dto);
		if (res>0) {
			req.setAttribute("msg", "�л���ϼ���!! �л������������ �̵��մϴ�.");
			req.setAttribute("url", "student_list.do");
		}else {
			req.setAttribute("msg", "�л���Ͻ���!! �л������������� �̵��մϴ�.");
			req.setAttribute("url", "student_index.do");
		}
		return "message";
	}
	

	
	
}
