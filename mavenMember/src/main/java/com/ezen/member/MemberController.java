package com.ezen.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.member.dto.MemberDTO;
import com.ezen.member.service.MemberMapper;


@Controller
public class MemberController {
	@Autowired
	private MemberMapper memberMapper;
	
	@RequestMapping("index_member.do")
	public String index_member() {
		return "member/member_index";
	}
	
	@RequestMapping(value="/list_member.do")
	public String memberList(HttpServletRequest req,
			@RequestParam Map<String, String> map){
				List<MemberDTO> list = null;
				if (!map.containsKey("mode")) {
					list = memberMapper.listMember();
				}else {
					if (map.containsKey("search")) {
						list = memberMapper.findMember(map);
					}
				}
		
		req.setAttribute("listMember", list);
		return "member/member_list";
	}
	
	@RequestMapping(value="/check_member.do", method=RequestMethod.GET)
	public String memberSsn() {
		return "member/memberSsn";
	}
	
	@RequestMapping(value="/check_member.do", method=RequestMethod.POST)
	public String memberCheck(HttpServletRequest req,
			@RequestParam Map<String, String> map) {
		
		boolean isMember = memberMapper.checkMember(map);
		
		if (isMember) {
			req.setAttribute("msg", "���� ȸ���̽ʴϴ�. �α����� �� �ּ���!!");			
			return "closeWindow";
		}else {
			req.setAttribute("msg", "ȸ�������������� �̵��մϴ�.");
			req.setAttribute("url", "input_member.do");
			
			HttpSession session = req.getSession();
			session.setAttribute("name", map.get("name"));
			session.setAttribute("ssn1", map.get("ssn1"));
			session.setAttribute("ssn2", map.get("ssn2"));
			
			return "message";
		}
		
	}
	
	@RequestMapping(value="/input_member.do", method=RequestMethod.GET)
	public String memberInput() {
		return "member/member_input";
	}
	
	@RequestMapping(value="/input_member.do", method=RequestMethod.POST)
	public String memberInputOk(HttpServletRequest req,
			@ModelAttribute MemberDTO dto) {

		int res = memberMapper.insertMember(dto);
		if (res>0) {
			req.setAttribute("msg", "ȸ����ϼ���!! �α����� ���ּ���.");			
			return "closeWindow";

		}else {
			req.setAttribute("msg", "ȸ����Ͻ���!! ȸ������������� �̵��մϴ�.");
			req.setAttribute("url", "member_ssn.do");
			return "message";
			
		}
	}
	
	@RequestMapping(value="/update_member.do", method=RequestMethod.GET)
	public String memberUpdate(HttpServletRequest req, @RequestParam int no) {
		
		MemberDTO dto = memberMapper.getMember(no);
		req.setAttribute("getMember", dto);
		
		return "member/member_update";
	}
	
	@RequestMapping(value="/update_member.do", method=RequestMethod.POST)
	public String memberUpdatePro(HttpServletRequest req,
			@ModelAttribute MemberDTO dto) {
		int res = memberMapper.updateMember(dto);
		if (res>0) {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}else {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}
		
		return "message";
	}
	
	
	@RequestMapping(value="/delete_member.do")
	public String memberDelete(HttpServletRequest req, @RequestParam int no) {

		int res = memberMapper.deleteMember(no);
		if (res>0) {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}else {
			req.setAttribute("msg", "��� ���� ����!! ��� ����������� �̵��մϴ�.");
			req.setAttribute("url", "list_member.do");
		}
		
		return "message";
	}
	
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String memberLogin() {
		return "member/login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String memberLoginOk(HttpServletRequest req,
			HttpServletResponse resp,
			@RequestParam String id,
			@RequestParam String passwd,
			@RequestParam(required=false) String saveId
			) {
		
		MemberDTO dto = memberMapper.getMember(id);
		if (dto == null){
			req.setAttribute("msg", "�ش��ϴ� ���̵� �����ϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���!!");
			req.setAttribute("url", "login.do");
		}else {
			if (dto.getPasswd().equals(passwd)){
				req.setAttribute("msg", dto.getName()+"��, ȯ���մϴ�.");
				req.setAttribute("url", "index_member.do");
				
				HttpSession session = req.getSession();
				session.setAttribute("mdto", dto);
				
				Cookie ck = new Cookie("saveId", id);
				if (saveId == null) ck.setMaxAge(0);
				else ck.setMaxAge(24*60*60);
				resp.addCookie(ck);
			}else {
				req.setAttribute("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���!!");
				req.setAttribute("url", "login.do");
			}
	}
		return "message";
		
	}
	
	@RequestMapping(value="/logout.do")
	public String memberLogout(HttpServletRequest req, HttpSession session) {
		session.invalidate();
		
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		req.setAttribute("url", "login.do");
		return "message";
	}
	
	@RequestMapping(value="/member_search.do", method=RequestMethod.GET)
	public String memberSearch() {
		return "member/member_search";
	}
	
	@RequestMapping(value="/member_search.do", method=RequestMethod.POST)
	public String memberSearchOk(
			HttpServletRequest req,
			@RequestParam String name,
			@RequestParam String ssn1,
			@RequestParam String ssn2,
			@RequestParam(required=false  ) String id) {
		
		String msg = memberMapper.searchMember(name, ssn1, ssn2, id);
		req.setAttribute("msg", msg);
		
		return "closeWindow";
	}
	

}
