package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class MemberCheckController implements Controller {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String name = req.getParameter("name");
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		String msg = null, url = null;
		ModelAndView mav = new ModelAndView();
		
		boolean isMember = memberDAO.checkMember(name, ssn1, ssn2);
		if (isMember) {
			msg = "저희 회원이십니다. 로그인을 해 주세요!!";
			mav.setViewName("forward:closeWindow.jsp");
			mav.addObject("msg", msg);
		}else {
			msg = "회원가입페이지로 이동합니다.";
			url = "input_member.do";
			HttpSession session = req.getSession();
			session.setAttribute("name", name);
			session.setAttribute("ssn1", ssn1);
			session.setAttribute("ssn2", ssn2);
			
			mav.setViewName("forward:message.jsp");
			mav.addObject("msg", msg);
			mav.addObject("url", url);
		}
		
		return mav;
	}

}
