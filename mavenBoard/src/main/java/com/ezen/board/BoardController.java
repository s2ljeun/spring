package com.ezen.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.board.dto.BoardDTO;
import com.ezen.board.service.BoardMapper;

@Controller
public class BoardController {
	@Autowired
	private BoardMapper boardMapper;
	
	@RequestMapping("list_board.do")
	public String list_board(HttpServletRequest req, 
			@RequestParam(required = false) String pageNum) {
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int countRow = boardMapper.getCount();
		if (endRow > countRow) endRow = countRow;

		List<BoardDTO> list = boardMapper.listBoard(startRow, endRow);
		int num = countRow - (startRow - 1);
		req.setAttribute("listBoard", list);
		req.setAttribute("num", num);
		int pageCount = countRow / pageSize + (countRow%pageSize==0 ? 0 : 1);
		int pageBlock = 3;
		int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;		
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageBlock", pageBlock);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		return "board/list";
	}
	
	@RequestMapping(value="/write_board.do", method=RequestMethod.GET)
	public String writeForm_board() {
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write_board.do", method=RequestMethod.POST)
	public String writePro_board(HttpServletRequest req, 
			@ModelAttribute BoardDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setNum(0);
			dto.setRe_step(0);
			dto.setRe_level(0);
		}
		dto.setIp(req.getRemoteAddr());
		int res = boardMapper.insertBoard(dto);
		if (res>0) {
			req.setAttribute("msg", "�Խñ� ��� ����!! �Խñ� ��� �������� �̵��մϴ�.");
			req.setAttribute("url", "list_board.do");
		}else {
			req.setAttribute("msg", "�Խñ� ��� ����!! �Խñ� ��� �������� �̵��մϴ�.");
			req.setAttribute("url", "write_board.do");
		}
		return "message";
	}
	
	@RequestMapping(value="/content_board.do")
	public String content_board(HttpServletRequest req, 
			@RequestParam int num){
		BoardDTO dto = boardMapper.getBoard(num, "content");
		req.setAttribute("getBoard", dto);
		return "board/content";
	}
	
	@RequestMapping(value="/delete_board.do", method=RequestMethod.GET)
	public String deleteForm_board() {
		return "board/deleteForm";
	}
	
	@RequestMapping(value="/delete_board.do", method=RequestMethod.POST)
	public String deletePro_board(HttpServletRequest req, 
										@RequestParam Map<String, String> params) {
		int res = boardMapper.deleteBoard(params);
		if (res>0) {
			req.setAttribute("msg", "�Խñ� ���� ����!! �Խñ� ��� �������� �̵��մϴ�.");
			req.setAttribute("url", "list_board.do");
		}else if (res==0){
			req.setAttribute("msg", "�Խñ� ���� ����!! �Խñ� �󼼺��� �������� �̵��մϴ�.");
			req.setAttribute("url", "content_board.do?num="+params.get("num"));
		}else{
			req.setAttribute("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���!!");
			req.setAttribute("url", "delete_board.do?num="+params.get("num"));
		}
		
		return "message";
	}
	
	@RequestMapping(value="/update_board.do", method=RequestMethod.GET)
	public String updateForm_board(HttpServletRequest req, @RequestParam int num) {

		BoardDTO dto = boardMapper.getBoard(num, "update");
		req.setAttribute("getBoard", dto);
		return "board/updateForm";
	}
	
	@RequestMapping(value="/update_board.do", method=RequestMethod.POST)
	public String updatePro_board(HttpServletRequest req, 
							@ModelAttribute BoardDTO dto, BindingResult result) {

		int res = boardMapper.updateBoard(dto);
		if (res>0) {
			req.setAttribute("msg", "�Խñ� ���� ����!! �Խñ� ��� �������� �̵��մϴ�.");
			req.setAttribute("url", "list_board.do");
		}else if (res==0){
			req.setAttribute("msg", "�Խñ� ���� ����!! �Խñ� �󼼺��� �������� �̵��մϴ�.");
			req.setAttribute("url", "content_board.do?num="+dto.getNum());
		}else{
			req.setAttribute("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���!!");
			req.setAttribute("url", "update_board.do?num="+dto.getNum());
		}
		return "message";
	}
	
	
}
