package pe.oh29oh29.myweb.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.oh29oh29.myweb.common.config.Dir;
import pe.oh29oh29.myweb.service.PostService;

@Controller
public class HomeController {
	
	@Autowired PostService postService;
	@Autowired Dir dir;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("jsDir", dir.getJs());
		model.addAttribute("cssDir", dir.getCss());
		System.out.println(dir.getJs());
		System.out.println(dir.getCss());
		return "blog/home";
	}
}
