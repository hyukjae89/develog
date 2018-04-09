package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Categories;

public interface CategoriesService {

	public void addCategory(Categories category);
	
	public List<Categories> findCategoriesByParentIdx(String parentIdx);
	
	public void modifyCategory(Categories category);
	
	public void removeCategoryByIdx(String idx);
}
