package pe.oh29oh29.myweb.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.service.PostService;

@Controller
public class PostController {

	@Autowired PostService postService;
	
	@RequestMapping(value = "postWrite", method = RequestMethod.GET)
	public String postWriteView() {
		return "./post/write";
	}
	
	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public void uploadImage(MultipartHttpServletRequest multipartRequest) {
		MultipartFile file = multipartRequest.getFile("Filedata");
		String fileRealName = file.getOriginalFilename();
		String fileExt = fileRealName.substring(fileRealName.lastIndexOf(".") + 1, fileRealName.length());
		String fileFakeName = Utils.generateIdx() + "." + fileExt;
		String fileDirPath = multipartRequest.getSession().getServletContext().getRealPath("");
		System.out.println(fileDirPath);
//		File fileDir = new File();
		System.out.println(multipartRequest.getServletPath());
		System.out.println(fileFakeName);
	}
}
