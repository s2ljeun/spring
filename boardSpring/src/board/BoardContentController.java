package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.RequestUtil;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardContentController implements Controller {
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//int num = Integer.parseInt(req.getParameter("num"));
		int num = ServletRequestUtils.getIntParameter(req, "num");
		//parameter���� int���̶�� int�� �޾Ƶ帮�ڴٴ� �޼ҵ�
		BoardDTO dto = boardDAO.getBoard(num, "content");
		return new ModelAndView("/content", "getBoard", dto);
	}
}













