package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import student.dao.StudentDAO;
import student.dto.StudentDTO;

public class StudentInsertAbstractCommandController extends AbstractCommandController {
	private StudentDAO studentDAO;
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest arg0, 
			HttpServletResponse arg1, Object arg2, BindException arg3)
																					throws Exception {
		StudentDTO dto = (StudentDTO)arg2;
		int res = studentDAO.insertStudent(dto);
		String msg = null, url = null;
		if (res>0) {
			msg = "�л���ϼ���!! �л������������ �̵��մϴ�.";
			url = "student_list.do";
		}else {
			msg = "�л���Ͻ���!! �л������������� �̵��մϴ�.";
			url = "student_index.do";
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:message.jsp");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}		

}
