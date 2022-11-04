package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.dao.StudentDAO;
import student.dto.StudentDTO;

//java annotation / �޼ҵ� ������ ����
// 1. bean�� ��Ʈ�ѷ� ��� <bean class="student.StudentController">
// 1-2. bean�� dao ����, ��Ʈ�ѷ��� setter�޼ҵ� ����
// 2. bean�� ����ߴ� �� Controller�� @RequestMapping('')����
// �޼ҵ������ ��� => �ּҰ��� '' �� �̸����� �Դٸ�...

@Controller
public class StudentController {
	@Autowired //bean�� id=''�� ����� �ֵ� ���� ����. bean���Ͽ��� �������� �ʰ��� ��밡��
	private StudentDAO studentDAO;

	
	@RequestMapping(value="/student_index.do")
	public String student_index() { // ���⵵ request response�� �����ϴµ� �ʿ��� �� ������ ��
		return "/student";
	}
	
	@RequestMapping(value="/student_list.do")
	public String student_list(HttpServletRequest req) { //request(���������� �̹� �����ϰ�����)�� �Ű������� ����� list �޾ư��� �ϱ�
		List<StudentDTO> list = studentDAO.listStudent();
		req.setAttribute("listStudent", list); //�ʿ��� ������ request�� ��Ƶΰ�
		return "/list"; //�ּҰ��� ��Ʈ������ �������ֱ�
	}
	
	@RequestMapping(value="/student_find.do")
	public String student_find(HttpServletRequest req, @RequestParam String name) {
		// �Ķ���Ͱ��� �Ű������� �ҷ��� �� �ִ�. �Ķ���ʹ� string�ε��� int���̶�� ���� int�� ����!
		// @RequestParam�� �����Ǿ��ִ�.
		List<StudentDTO> find = studentDAO.findStudent(name);
		req.setAttribute("listStudent", find); //�ʿ��� ������ request�� ��Ƶΰ�
		return "/list"; //�ּҰ��� ��Ʈ������ �������ֱ�
	}
	
	@RequestMapping(value="/student_delete.do") //���� 1���� value��� �ν��� ��������
	public String student_delete(HttpServletRequest req, @RequestParam String id) {
		int res = studentDAO.deleteStudent(id);
		if (res>0) {
			req.setAttribute("msg", "�л���������!! �л������������ �̵��մϴ�.");
			req.setAttribute("url", "student_list.do");
		}else {
			req.setAttribute("msg", "�л���������!! �л������������ �̵��մϴ�.");
			req.setAttribute("url", "student_index.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/student_insert.do")
	public String student_insert(HttpServletRequest req, @ModelAttribute StudentDTO dto) {
		//AbstractCommandController ��ӹ޾Ƽ� arg2�� DTO�� ����ȯ�� �־���� �� ó��...
		//parameter�� ���� ���� ������ DTO��ü�� ������ִ� ������ �� �Ѵ�
		int res = studentDAO.insertStudent(dto);
		if (res>0) {
			req.setAttribute("msg", "�л���ϼ���!! �л������������ �̵��մϴ�.");
			req.setAttribute("url", "student_list.do");
		}else {
			req.setAttribute("msg", "�л���Ͻ���!! �л������������ �̵��մϴ�.");
			req.setAttribute("url", "student_index.do");
		}
		return "forward:message.jsp";
	}
	
	
}
//ModelAndView, View, String, void �� �پ��ϰ� ���ϰ���
// @Controller @RequestMapping @RequestParam @ModelAttribute @Autowired





