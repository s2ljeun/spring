package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberInputOkAbstractCommandController extends AbstractCommandController {
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		MemberDTO dto = (MemberDTO)arg2;
		int res = memberDAO.insertMember(dto);
		ModelAndView mav = new ModelAndView();
		if (res>0) {
			mav.addObject("msg", "회원 등록 성공!! 로그인을 해 주세요");
			mav.setViewName("forward:closeWindow.jsp");
		}else {
			mav.addObject("msg", "회원 등록 실패!! 회원등록페이지로 이동합니다.");
			mav.addObject("url", "member_ssn.do");
			mav.setViewName("forward:message.jsp");
		}		
		return mav;
	}

}
