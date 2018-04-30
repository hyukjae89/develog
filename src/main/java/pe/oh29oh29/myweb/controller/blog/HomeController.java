package pe.oh29oh29.myweb.controller.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.oh29oh29.myweb.service.PostService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired PostService postService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, String categoryName) {
		
		/*List<Category> categories = categoryService.findCategories(AccessSpecifier.PUBLIC);
		List<PostView> posts = postService.readPosts(categoryName);
		
		model.addAttribute("categories", categories);
		model.addAttribute("posts", posts);*/
		
		return "blog/home";
	}
}
