package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberUpdateProAbstractCommandController extends AbstractCommandController {
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {

		MemberDTO dto = (MemberDTO)arg2;
		int res = memberDAO.updateMember(dto);
		ModelAndView mav = new ModelAndView();
		
		if (res>0) {
			mav.addObject("msg", "멤버 수정 성공!! 멤버 목록페이지로 이동합니다.");
			mav.addObject("url", "list_member.do");
		}else {
			mav.addObject("msg", "멤버 수정 실패!! 멤버 목록페이지로 이동합니다.");
			mav.addObject("url", "list_member.do");
		}
		mav.setViewName("forward:message.jsp");
		return mav;	
	}

}
