package pe.oh29oh29.myweb.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.service.CategoryService;
import pe.oh29oh29.myweb.service.CategoryService.AccessSpecifier;

@Controller
public class CategoryManagerController {
	
	@Autowired CategoryService categoryService;
	
	@RequestMapping(value = "categoryManager", method = RequestMethod.GET)
	public String categoryManagerView(Model model) {
		
		List<Category> categories = categoryService.findCategories(AccessSpecifier.TOTAL);
		
		model.addAttribute("categories", categories);
		
		return "./admin/contents/category";
	}
	
	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	@ResponseBody
	public Category addCategory(@RequestBody Category category) {
		String categoryIdx = categoryService.addCategory(category);
		return categoryService.findCategory(categoryIdx);
	}
}
