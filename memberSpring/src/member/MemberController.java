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

import member.dao.MemberDAO;
import member.dto.MemberDTO;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping(value="/index_member.do")
	public String indexMember() {
		return "/member_index";
	}
	
	@RequestMapping(value="/list_member.do")
	public String memberList(HttpServletRequest req,
			@RequestParam(required=false) String mode,
			@RequestParam(required=false) String search,
			@RequestParam(required=false) String searchString
			) {
		// RequestParam Map<String, String> map ���� �޾Ƽ� (map�� ������ �� �޴´�)
		// if (!map.containsKey("mode"))
		// map.get("search") �̷������� 
		
		List<MemberDTO> list = null;
		if (mode == null)	list = memberDAO.listMember();
		else	list = memberDAO.findMember(search, searchString);
		
		req.setAttribute("listMember", list);
		return "/member_list";
	}
	
	@RequestMapping(value="/check_member.do", method=RequestMethod.GET) //top���� ȸ������
	public String memberSsn() {
		return "/memberSsn";
	}
	
	@RequestMapping(value="/check_member.do", method=RequestMethod.POST) //Ssn���� ����
	public String memberCheck(HttpServletRequest req,
			@RequestParam Map<String, String> map) {
		
		try {
			
			boolean isMember = memberDAO.checkMember(
					map.get("name"), map.get("ssn1"), map.get("ssn2")
					);
			
			if (isMember) {
				req.setAttribute("msg", "���� ȸ���̽ʴϴ�. �α����� �� �ּ���!!");			
				return "forward:closeWindow.jsp";
			}else {
				req.setAttribute("msg", "ȸ�������������� �̵��մϴ�.");
				req.setAttribute("url", "input_member.do");
				
				HttpSession session = req.getSession();
				session.setAttribute("name", map.get("name"));
				session.setAttribute("ssn1", map.get("ssn1"));
				session.setAttribute("ssn2", map.get("ssn2"));
				
				return "forward:message.jsp";
			}
			
		}catch(IncorrectResultSizeDataAccessException e) { //����� ���ų� 2�� �̻��� �� �߻�
			return null;
		}
		
		
	}
	
	@RequestMapping(value="/input_member.do", method=RequestMethod.GET) //�ٷ� ������ forward�� �̵�
	public String memberInput() {
		return "/member_input";
	}
	
	@RequestMapping(value="/input_member.do", method=RequestMethod.POST) //member_input.jsp���� post�� ����
	public String memberInputOk(HttpServletRequest req, @ModelAttribute MemberDTO dto) {
		
		int res = memberDAO.insertMember(dto);
		if (res>0) {
			req.setAttribute("msg", "ȸ����ϼ���!! �α����� ���ּ���.");			
			return "forward:closeWindow.jsp";

		}else {
			req.setAttribute("msg", "ȸ����Ͻ���!! ȸ������������� �̵��մϴ�.");
			req.setAttribute("url", "member_ssn.do");
			return "forward:message.jsp";
			
		}
	}
	
	@RequestMapping(value="/update_member.do", method=RequestMethod.GET) // list���� <a> => GET������� �̵��ؿ�
	public String memberUpdate(HttpServletRequest req, @RequestParam int no) {
		MemberDTO dto = memberDAO.getMember(no);
		req.setAttribute("getMember", dto);
		
		return "/member_update";
	}
	
	@RequestMapping(value="/update_member.do", method=RequestMethod.POST) // member_update.jsp���� POST�� �� �����ؼ� �̵��ؿ�
	public String memberUpdatePro(HttpServletRequest req,
			@ModelAttribute MemberDTO dto) {
		
		int res = memberDAO.updateMember(dto);
		if (res>0) {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}else {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}
		
		return "forward:message.jsp";
	}
	
	
	@RequestMapping(value="/delete_member.do")
	public String memberDelete(HttpServletRequest req, @RequestParam int no) {
		int res = memberDAO.deleteMember(no);
		if (res>0) {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}else {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}
		
		return "forward:message.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET) // top���� �α��ι�ư
	public String memberLogin() {
		return "/login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST) // �α����� ����
	public String memberLoginOk(HttpServletRequest req,
			HttpServletResponse resp,
			@RequestParam String id,
			@RequestParam String passwd,
			@RequestParam(required=false) String saveId
			) {
		
		MemberDTO dto = memberDAO.getMember(id);
		if (dto == null){
			req.setAttribute("msg", "�ش��ϴ� ���̵� �����ϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���!!");
			req.setAttribute("url", "login.do");
		}else {
			if (dto.getPasswd().equals(passwd)){
				req.setAttribute("msg", dto.getName()+"��, ȯ���մϴ�.");
				req.setAttribute("url", "index_member.do");
				//��������
				HttpSession session = req.getSession();
				session.setAttribute("mdto", dto);
				//��Ű����
				Cookie ck = new Cookie("saveId", id);
				if (saveId == null) ck.setMaxAge(0);
				else ck.setMaxAge(24*60*60);
				resp.addCookie(ck);
			}else {
				req.setAttribute("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���!!");
				req.setAttribute("url", "login.do");
			}
	}
		return "forward:message.jsp";
		
	}
	
	@RequestMapping(value="/logout.do")
	public String memberLogout(HttpServletRequest req, HttpSession session) {
		session.invalidate();
		
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
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
		
		String msg = memberDAO.searchMember(name, ssn1, ssn2, id);
		req.setAttribute("msg", msg);
		
		return "forward:closeWindow.jsp";
	}
	
	
	
}