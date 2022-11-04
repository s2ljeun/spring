package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;

public class BoardDeleteProController implements Controller {
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int num = ServletRequestUtils.getIntParameter(req, "num");
		String passwd = req.getParameter("passwd");
		int res = boardDAO.deleteBoard(num, passwd);
		ModelAndView mav = new ModelAndView();
		if (res>0) {
			mav.addObject("msg", "�Խñ� ���� ����!! �Խñ� ����������� �̵��մϴ�.");
			mav.addObject("url", "list_board.do");
		}else if (res==0){
			mav.addObject("msg", "�Խñ� ���� ����!! �Խñ� �󼼺����������� �̵��մϴ�.");
			mav.addObject("url", "content_board.do?num=" + num);
		}else {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ� �ٽ� �Է��� �ּ���");
			mav.addObject("url", "deleteForm_board.do?num=" + num);
		}
		mav.setViewName("forward:message.jsp");
		return mav;
	}

}
