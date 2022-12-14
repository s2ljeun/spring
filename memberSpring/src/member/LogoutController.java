package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "로그아웃 되었습니다.");
		mav.addObject("url", "login.do");
		mav.setViewName("forward:message.jsp");
		return mav;
	}

}
