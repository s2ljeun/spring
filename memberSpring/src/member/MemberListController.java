package member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberListController implements Controller {
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		List<MemberDTO> list = null;
		
		if (req.getParameter("mode") == null) {
			list = memberDAO.listMember();
		}else {
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			list = memberDAO.findMember(search, searchString);
		}

		return new ModelAndView("/member_list", "listMember", list);
	}

}
