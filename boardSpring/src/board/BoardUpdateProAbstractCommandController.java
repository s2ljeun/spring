package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardUpdateProAbstractCommandController extends AbstractCommandController {
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		BoardDTO dto = (BoardDTO)arg2;
		int res = boardDAO.updateBoard(dto);
		ModelAndView mav = new ModelAndView();
		if (res>0) {
			mav.addObject("msg", "�Խñ� ���� ����!! �Խñ� ����������� �̵��մϴ�.");
			mav.addObject("url", "list_board.do");
		}else if (res==0){
			mav.addObject("msg", "�Խñ� ���� ����!! �Խñ� �󼼺����������� �̵��մϴ�.");
			mav.addObject("url", "content_board.do?num=" + dto.getNum());
		}else {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ� �ٽ� �Է��� �ּ���");
			mav.addObject("url", "updateForm_board.do?num=" + dto.getNum());
		}
		mav.setViewName("forward:message.jsp");
		return mav;		
	}

}
