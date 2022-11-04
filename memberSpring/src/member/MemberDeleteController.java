package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class MemberDeleteController implements Controller {
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int no = ServletRequestUtils.getIntParameter(req, "no");
		int res = memberDAO.deleteMember(no);
		
		ModelAndView mav = new ModelAndView();
		
		if (res>0) {
			mav.addObject("msg", "멤버 삭제 성공!! 멤버 목록페이지로 이동합니다.");
			mav.addObject("url", "list_member.do");
		}else {
			mav.addObject("msg", "멤버 삭제 실패!! 멤버 목록페이지로 이동합니다.");
			mav.addObject("url", "list_member.do");
		}
		mav.setViewName("forward:message.jsp");
		return mav;	
	}

}
