package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.CategoryExample;

public interface CategoryDao {

	public int insertCategory(Category category);
	
	public List<Category> selectCategory(CategoryExample example);
	
	public int updateCategory(Category category);
	
	public int deleteCategory(CategoryExample example);
	
	public int deleteAllCategories();

}