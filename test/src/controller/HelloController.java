package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/helloSpring"); //이동할 페이지를 지정 - springapp-servlet에서 viewResolver지정완료
		mav.addObject("name", "홍길동"); //request에 담아갈 내용을 저장 -> 꺼낼 때 req에서 꺼내면 됨
		return mav;
	}

}
