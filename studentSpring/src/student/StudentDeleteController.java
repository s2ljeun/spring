package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import student.dao.StudentDAO;

public class StudentDeleteController implements Controller {
	private StudentDAO studentDAO;

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		int res = studentDAO.deleteStudent(id);
		String msg = null, url = null;
		if (res>0) {
			msg = "�л���������!! �л������������ �̵��մϴ�.";
			url = "student_list.do";
		}else {
			msg = "�л���������!! �л������������� �̵��մϴ�.";
			url = "student_index.do";
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:message.jsp");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	//ViewResolver�� ������ �ʰ� ���� �ּҸ� �̵��ϴ� ���
	//forward�� ���� ��� - request�� response���� ������ ä ���� = forward, ... ��
	//redirect�� ���� ��� - request���� �ʱ�ȭ��

}
