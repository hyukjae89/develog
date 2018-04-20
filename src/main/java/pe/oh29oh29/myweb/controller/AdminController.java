package pe.oh29oh29.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.oh29oh29.myweb.service.CategoryService;

@Controller
public class AdminController {
	
	@Autowired CategoryService categoryService;
	
	@RequestMapping(value = "adminView", method = RequestMethod.GET)
	public String adminView() {
		return "./admin/main";
	}
	
	@RequestMapping(value = "categoryManager", method = RequestMethod.GET)
	public String categoryManager() {
		return "./admin/contents/category";
	}
}
