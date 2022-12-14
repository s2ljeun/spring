package member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import member.dto.MemberDTO;
import member.mybatis.MemberMapper;

@Controller
public class MemberController {

	
	@RequestMapping(value="/index_member.do")
	public String indexMember() {
		return "/member_index";
	}
	
	@RequestMapping(value="/list_member.do")
	public String memberList(HttpServletRequest req,
			@RequestParam Map<String, String> map){
				List<MemberDTO> list = null;
				if (!map.containsKey("mode")) {
					list = MemberMapper.listMember();
				}else {
					list = MemberMapper.findMember(map);
				}
		
		req.setAttribute("listMember", list);
		return "/member_list";
	}
	
	@RequestMapping(value="/check_member.do", method=RequestMethod.GET) //top에서 회원가입
	public String memberSsn() {
		return "/memberSsn";
	}
	
	@RequestMapping(value="/check_member.do", method=RequestMethod.POST) //Ssn에서 제출
	public String memberCheck(HttpServletRequest req,
			@RequestParam Map<String, String> map) {
		
		try {
			
			boolean isMember = MemberMapper.checkMember(map);
			
			if (isMember) {
				req.setAttribute("msg", "저희 회원이십니다. 로그인을 해 주세요!!");			
				return "forward:closeWindow.jsp";
			}else {
				req.setAttribute("msg", "회원가입페이지로 이동합니다.");
				req.setAttribute("url", "input_member.do");
				
				HttpSession session = req.getSession();
				session.setAttribute("name", map.get("name"));
				session.setAttribute("ssn1", map.get("ssn1"));
				session.setAttribute("ssn2", map.get("ssn2"));
				
				return "forward:message.jsp";
			}
			
		}catch(IncorrectResultSizeDataAccessException e) { //결과가 없거나 2개 이상일 때 발생
			return null;
		}
		
		
	}
	
	@RequestMapping(value="/input_member.do", method=RequestMethod.GET) //바로 위에서 forward로 이동
	public String memberInput() {
		return "/member_input";
	}
	
	@RequestMapping(value="/input_member.do", method=RequestMethod.POST) //member_input.jsp에서 post로 제출
	public String memberInputOk(HttpServletRequest req, @ModelAttribute MemberDTO dto) {

		int res = MemberMapper.insertMember(dto);
		if (res>0) {
			req.setAttribute("msg", "회원등록성공!! 로그인을 해주세요.");			
			return "forward:closeWindow.jsp";

		}else {
			req.setAttribute("msg", "회원등록실패!! 회원등록페이지로 이동합니다.");
			req.setAttribute("url", "member_ssn.do");
			return "forward:message.jsp";
			
		}
	}
	
	@RequestMapping(value="/update_member.do", method=RequestMethod.GET) // list에서 <a> => GET방식으로 이동해옴
	public String memberUpdate(HttpServletRequest req, @RequestParam int no) {
		//MemberDTO dto = memberDAO.getMember(no);
		MemberDTO dto = MemberMapper.getMember(no);
		req.setAttribute("getMember", dto);
		
		return "/member_update";
	}
	
	@RequestMapping(value="/update_member.do", method=RequestMethod.POST) // member_update.jsp에서 POST로 폼 제출해서 이동해옴
	public String memberUpdatePro(HttpServletRequest req,
			@ModelAttribute MemberDTO dto) {
		
		//int res = memberDAO.updateMember(dto);
		int res = MemberMapper.updateMember(dto);
		if (res>0) {
			req.setAttribute("msg", "멤버 수정 성공!! 멤버 목록페이지로 이동합니다.");
			req.setAttribute("url", "list_member.do");
		}else {
			req.setAttribute("msg", "멤버 수정 실패!! 멤버 목록페이지로 이동합니다.");
			req.setAttribute("url", "list_member.do");
		}
		
		return "forward:message.jsp";
	}
	
	
	@RequestMapping(value="/delete_member.do")
	public String memberDelete(HttpServletRequest req, @RequestParam int no) {
		//int res = memberDAO.deleteMember(no);
		int res = MemberMapper.deleteMember(no);
		if (res>0) {
			req.setAttribute("msg", "멤버 삭제 성공!! 멤버 목록페이지로 이동합니다.");
			req.setAttribute("url", "list_member.do");
		}else {
			req.setAttribute("msg", "멤버 삭제 실패!! 멤버 목록페이지로 이동합니다.");
			req.setAttribute("url", "list_member.do");
		}
		
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET) // top에서 로그인버튼
	public String memberLogin() {
		return "/login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST) // 로그인폼 제출
	public String memberLoginOk(HttpServletRequest req,
			HttpServletResponse resp,
			@RequestParam String id,
			@RequestParam String passwd,
			@RequestParam(required=false) String saveId
			) {
		
		//MemberDTO dto = memberDAO.getMember(id);
		MemberDTO dto = MemberMapper.getMember(id);
		if (dto == null){
			req.setAttribute("msg", "해당하는 아이디가 없습니다. 다시 확인하시고 입력해 주세요!!");
			req.setAttribute("url", "login.do");
		}else {
			if (dto.getPasswd().equals(passwd)){
				req.setAttribute("msg", dto.getName()+"님, 환영합니다.");
				req.setAttribute("url", "index_member.do");
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
		return "forward:message.jsp";
		
	}
	
	@RequestMapping(value="/logout.do")
	public String memberLogout(HttpServletRequest req, HttpSession session) {
		session.invalidate();
		
		req.setAttribute("msg", "로그아웃 되었습니다.");
		req.setAttribute("url", "login.do");
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/member_search.do", method=RequestMethod.GET)
	public String memberSearch() {
		return "/member_search";
	}
	
	@RequestMapping(value="/member_search.do", method=RequestMethod.POST)
	public String memberSearchOk(
			HttpServletRequest req,
			@RequestParam String name,
			@RequestParam String ssn1,
			@RequestParam String ssn2,
			@RequestParam(required=false  ) String id) {
		
		//String msg = memberDAO.searchMember(name, ssn1, ssn2, id);
		String msg = MemberMapper.searchMember(name, ssn1, ssn2, id);
		req.setAttribute("msg", msg);
		
		return "forward:closeWindow.jsp";
	}
	
	
	
}
