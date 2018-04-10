package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Category;

public interface CategoryService {

	public void addCategory(Category category);
	
	public List<Category> findCategoriesByParentIdx(String parentIdx);
	
	public void modifyCategory(Category category);
	
	public void removeCategoryByIdx(String idx);
}
