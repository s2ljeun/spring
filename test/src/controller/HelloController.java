package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/helloSpring"); //�̵��� �������� ���� - springapp-servlet���� viewResolver�����Ϸ�
		mav.addObject("name", "ȫ�浿"); //request�� ��ư� ������ ���� -> ���� �� req���� ������ ��
		return mav;
	}

}