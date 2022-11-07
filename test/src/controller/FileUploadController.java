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
		
		//���Ϲޱ�
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		//�� ����ȯ�� �����Ϸ��� XXX-servlet.xml�� multipartResolver�� ������ �ȴ�.
		
		MultipartFile mf = mr.getFile("filename");
		String filename = mf.getOriginalFilename();
		
		if(filename==null || filename.trim().equals("")) {
			System.out.println("�������� �ȵǾ����ϴ�.");
			return;
		}
		
		//���������ϱ�
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/files");
		System.out.println("upPath = " + upPath);
		System.out.println("filename = " + filename);
		
		File file = new File(upPath, filename); //��ü(Ʋ)�����
		try {
			mf.transferTo(file); //req�ȿ��ִ� ���ϳ������� ��ü�ϼ�
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
