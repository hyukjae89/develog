package pe.oh29oh29.myweb.controller.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
		
//		model.addAttribute("categories", categories);
		
		return "./admin/contents/category";
	}
	
	@RequestMapping(value = "findCategories", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> findCategories() {
		List<Category> categories = categoryService.findCategories(AccessSpecifier.TOTAL);
		return categories;
	}
	
	@RequestMapping(value = "saveCategory", method = RequestMethod.POST)
	@ResponseBody
	public void saveCategory(@RequestBody String categories) {
		List<Category> updatedCategories = new ArrayList<Category>();
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject categoryPack = (JSONObject) jsonParser.parse(categories);
			Iterator<String> keys = categoryPack.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				JSONObject category = (JSONObject) categoryPack.get(key);
				Category updatedCategory = new Category();
				updatedCategory.setIdx(category.get("idx").toString());
				updatedCategory.setName(category.get("name").toString());
				updatedCategory.setIsPrivate(Integer.parseInt(category.get("isPrivate").toString()));
				updatedCategory.setOrd(Integer.parseInt(category.get("ord").toString()));
				updatedCategories.add(updatedCategory);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		categoryService.saveCategories(updatedCategories);
	}
	
	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	@ResponseBody
	public Category addCategory(@RequestBody Category category) {
		String categoryIdx = categoryService.addCategory(category);
		return categoryService.findCategory(categoryIdx);
	}
}
