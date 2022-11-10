package board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDTO;
import board.mybatis.BoardMapper;

@Controller
public class BoardController {
	@RequestMapping(value="/list_board.do")
	public String listBoard(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int countRow = BoardMapper.getCount();
		if (endRow > countRow) endRow = countRow;
		
		List<BoardDTO> list = BoardMapper.listBoard(startRow, endRow);
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
		return "/list";
	}

	
	@RequestMapping(value="/write_board.do", method=RequestMethod.GET)
	public String writeForm_board() {
		return "/writeForm";
	}
	
	
	@RequestMapping(value="/write_board.do", method=RequestMethod.POST)
	public String writePro_board(HttpServletRequest req, 
			@ModelAttribute BoardDTO dto, BindingResult result) {
		/*if (result.hasErrors()) {
			//dto.setNum(0);
			dto.setFilename("");
		}*/
		dto.setIp(req.getRemoteAddr());
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("filename");
		String filename = mf.getOriginalFilename();
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		File file = new File(upPath, filename);
		try {
			mf.transferTo(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
		session.setAttribute("upPath", upPath);
		dto.setFilename(filename);
		
		int res = BoardMapper.insertBoard(dto);
		if (res>0) {
			req.setAttribute("msg", "게시글 등록 성공!! 게시글 목록 페이지로 이동합니다.");
			req.setAttribute("url", "list_board.do");
		}else {
			req.setAttribute("msg", "게시글 등록 실패!! 게시글 등록 페이지로 이동합니다.");
			req.setAttribute("url", "write_board.do");
		}
		return "forward:message.jsp";
	}
	
	
	@RequestMapping(value="/content_board.do")
	public String content_board(HttpServletRequest req, @RequestParam int num){
		BoardDTO dto = BoardMapper.getBoard(num, "content");
		req.setAttribute("getBoard", dto);
		return "/content";
	}
	
}
