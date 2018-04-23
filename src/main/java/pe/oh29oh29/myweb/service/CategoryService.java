package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Category;

public interface CategoryService {

	public enum AccessSpecifier {
		PUBLIC(0), PRIVATE(1), TOTAL(2);
		
		private final int value;
		
		AccessSpecifier(int value) {
			this.value = value;
		}
		
		public int intValue() {
			return value;
		}
	}
	
	public void saveCategories(List<Category> categories);
	
	public String addCategory(Category category);
	
	public void modifyCategory(Category category);
	
	public void removeCategoryByIdx(String idx);
	
	public Category findCategory(String idx);
	
	public List<Category> findCategories(AccessSpecifier accessSpecifier);
	
	public List<Category> findCategories(String parentIdx, AccessSpecifier accessSpecifier);
}
