package com.ezen.fileupload;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 집합 안에 넣어서 value값을 여러 개 지정할 수 있다.
	@RequestMapping(value = {"/","/index.do"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "fileUpload";
	}
	
	@RequestMapping("/fileUpload.do")
	public String fileUpload_ok(HttpServletRequest req) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile file = mr.getFile("filename"); //"filename" = input의 value값
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/resources/img");
		System.out.println(upPath);
		File target = new File(upPath, file.getOriginalFilename());
		try {
			file.transferTo(target);
		}catch(IOException e) {
			req.setAttribute("msg", "파일업로드 끝!!");
			req.setAttribute("url", "index.do");
		}
		return "message";
	}
	
}
