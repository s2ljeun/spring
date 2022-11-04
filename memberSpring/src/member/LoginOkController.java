package member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class LoginOkController implements Controller {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		String passwd = req.getParameter("passwd");
		String saveId = req.getParameter("saveId");
		
		MemberDTO dto = memberDAO.getMember(id);
		ModelAndView mav = new ModelAndView();
		
		if (dto == null){
			mav.addObject("msg", "해당하는 아이디가 없습니다. 다시 확인하시고 입력해 주세요!!");
			mav.addObject("url", "login.do");
		}else {
			if (dto.getPasswd().equals(passwd)){
				mav.addObject("msg", dto.getName()+"님, 환영합니다.");
				mav.addObject("url", "index_member.do");
				//세션저장
				HttpSession session = req.getSession();
				session.setAttribute("mdto", dto);
				//쿠키전송
				Cookie ck = new Cookie("saveId", id);
				if (saveId == null) ck.setMaxAge(0);
				else ck.setMaxAge(24*60*60);
				resp.addCookie(ck);
			}else {
				req.setAttribute("msg", "비밀번호가 틀렸습니다. 다시 확인하시고 입력해 주세요!!");
				req.setAttribute("url", "login.do");
			}
		}
		mav.setViewName("forward:message.jsp");
		return mav;
	}

}
