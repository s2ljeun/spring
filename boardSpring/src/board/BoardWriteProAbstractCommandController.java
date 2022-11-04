package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardWriteProAbstractCommandController extends AbstractCommandController {
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object arg2, BindException arg3)
			throws Exception {
		BoardDTO dto = (BoardDTO)arg2;
		dto.setIp(req.getRemoteAddr());
		int res = boardDAO.insertBoard(dto);
		ModelAndView mav = new ModelAndView();
		if (res>0) {
			mav.addObject("msg", "게시글 등록 성공!! 게시글 목록페이지로 이동합니다.");
			mav.addObject("url", "list_board.do");
		}else {
			mav.addObject("msg", "게시글 등록 실패!! 게시글 등록페이지로 이동합니다.");
			mav.addObject("url", "writeForm_board.do");
		}
		mav.setViewName("forward:message.jsp");
		return mav;
	}

}







