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
			msg = "학생삭제성공!! 학생목록페이지로 이동합니다.";
			url = "student_list.do";
		}else {
			msg = "학생삭제실패!! 학생관리페이지로 이동합니다.";
			url = "student_index.do";
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:message.jsp");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	//ViewResolver를 통하지 않고 직접 주소를 이동하는 방식
	//forward를 통한 방식 - request값 response값이 유지된 채 전송 = forward, ... 뿐
	//redirect를 통한 방식 - request값이 초기화됨

}
