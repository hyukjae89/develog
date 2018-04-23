package pe.oh29oh29.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.oh29oh29.myweb.service.PostService;

@Controller
public class PostController {

	@Autowired PostService postService;
	
	@RequestMapping(value = "/postWrite", method = RequestMethod.GET)
	public String postWriteView() {
		return "./post/write";
	}
}
