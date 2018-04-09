package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Categories;
import pe.oh29oh29.myweb.model.CategoriesExample;

public interface CategoriesDao {

	public int insertCategory(Categories category);
	
	public List<Categories> selectCategories(CategoriesExample example);
	
	public int updateCategory(Categories category);
	
	public int deleteCategory(CategoriesExample example);
	
	public int deleteAllCategories();

}