package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
	
	@RequestMapping("/fileUpload_ok.do")
	public void fileUpload(HttpServletRequest req) {
		
		//파일받기
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		//위 형변환이 가능하려면 XXX-servlet.xml에 multipartResolver를 만들어야 된다.
		
		MultipartFile mf = mr.getFile("filename");
		String filename = mf.getOriginalFilename();
		
		if(filename==null || filename.trim().equals("")) {
			System.out.println("파일전송 안되었습니다.");
			return;
		}
		
		//파일저장하기
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/files");
		System.out.println("upPath = " + upPath);
		System.out.println("filename = " + filename);
		
		File file = new File(upPath, filename); //객체(틀)만들고
		try {
			mf.transferTo(file); //req안에있는 파일내용으로 객체완성
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
