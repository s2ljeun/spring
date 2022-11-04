package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class StudentIndexController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/student");
		return mav;
	}
	//ModelandView�� ��ü�����
	//ModelAndView()�� ����� 
	// - setViewName()�޼ҵ带 ���� �̵��� ��θ� �˷��ش�.
	// - addObject(key, value)�� ���� �̵��� ���� ������ �����͸� �˷��ش�.
	//ModelAndView(String) �� ����� : setViewName�޼ҵ忡 ���� ���� �����ڿ� �־� �ָ� �ȴ�.
	//ModelAndView(String, key, value)�� ����� : setViewName���� 1��, �̵��� �����Ͱ��� key 2��, �� 3��
	// - �ַ� �ϳ��� �����͸� �ѱ涧 ���� ��� �ȴ�.
	
}
