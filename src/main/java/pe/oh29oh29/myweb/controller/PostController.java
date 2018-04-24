package pe.oh29oh29.myweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pe.oh29oh29.myweb.common.Files;
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
		int i = 0;
		while(multipartRequest.getParameterNames().hasMoreElements()) {
			System.out.println(multipartRequest.getParameterNames().nextElement().toString());
			i++;
			if (i == 10) {
				break;
			}
		}
	}
}
