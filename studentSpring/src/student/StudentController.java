package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.dto.StudentDTO;
import student.mybatis.StudentMapper;

//java annotation / 메소드 단위로 접근
// 1. bean에 컨트롤러 등록 <bean class="student.StudentController">
// 1-2. bean에 dao 주입, 컨트롤러에 setter메소드 설정
// 2. bean에 등록했던 각 Controller를 @RequestMapping('')으로
// 메소드단위로 등록 => 주소값이 '' 이 이름으로 왔다면...

@Controller
public class StudentController {

	
	@RequestMapping(value="/student_index.do")
	public String student_index() { // 여기도 request response는 존재하는데 필요없어서 안 가져온 것
		return "/student";
	}
	
	@RequestMapping(value="/student_list.do")
	public String student_list(HttpServletRequest req) { //request(내부적으로 이미 존재하고있음)를 매개변수로 끄집어내 list 받아가게 하기
		//List<StudentDTO> list = studentDAO.listStudent();
		List<StudentDTO> list = StudentMapper.listStudent();//★클래스 이름으로 접근하는거니까 static으로 만든다(?)
		req.setAttribute("listStudent", list); //필요한 정보는 request에 담아두고
		return "/list"; //주소값은 스트링으로 리턴해주기
	}
	
	@RequestMapping(value="/student_find.do")
	public String student_find(HttpServletRequest req, @RequestParam String name) {
		//List<StudentDTO> find = studentDAO.findStudent(name);
		List<StudentDTO> find = StudentMapper.findStudent(name);
		req.setAttribute("listStudent", find); //필요한 정보는 request에 담아두고
		return "/list"; //주소값은 스트링으로 리턴해주기
	}
	
	@RequestMapping(value="/student_delete.do") //값이 1개면 value라고 인식해 생략가능
	public String student_delete(HttpServletRequest req, @RequestParam String id) {
		//int res = studentDAO.deleteStudent(id);
		int res = StudentMapper.deleteStudent(id);
		if (res>0) {
			req.setAttribute("msg", "학생삭제성공!! 학생목록페이지로 이동합니다.");
			req.setAttribute("url", "student_list.do");
		}else {
			req.setAttribute("msg", "학생삭제실패!! 학생목록페이지로 이동합니다.");
			req.setAttribute("url", "student_index.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/student_insert.do")
	public String student_insert(HttpServletRequest req, @ModelAttribute StudentDTO dto) {
		//AbstractCommandController 상속받아서 arg2에 DTO로 형변환해 넣어줬던 것 처럼...
		//parameter에 따른 값을 가져와 DTO객체를 만들어주는 역할을 다 한다
		//int res = studentDAO.insertStudent(dto);
		int res = StudentMapper.insertStudent(dto); // dao 대신 mapper
		if (res>0) {
			req.setAttribute("msg", "학생등록성공!! 학생목록페이지로 이동합니다.");
			req.setAttribute("url", "student_list.do");
		}else {
			req.setAttribute("msg", "학생등록실패!! 학생목록페이지로 이동합니다.");
			req.setAttribute("url", "student_index.do");
		}
		return "forward:message.jsp";
	}
	
	
}
//ModelAndView, View, String, void 등 다양하게 리턴가능
// @Controller @RequestMapping @RequestParam @ModelAttribute @Autowired






