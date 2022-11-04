package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class StudentIndexController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/student");
		return mav;
	}
	//ModelandView의 객체만들기
	//ModelAndView()로 만들기 
	// - setViewName()메소드를 통해 이동할 경로를 알려준다.
	// - addObject(key, value)를 통해 이동할 곳에 가져갈 데이터를 알려준다.
	//ModelAndView(String) 로 만들기 : setViewName메소드에 넣을 값을 생성자에 넣어 주면 된다.
	//ModelAndView(String, key, value)로 만들기 : setViewName값이 1번, 이동할 데이터값의 key 2번, 값 3번
	// - 주로 하나의 데이터를 넘길때 자주 사용 된다.
	
}
