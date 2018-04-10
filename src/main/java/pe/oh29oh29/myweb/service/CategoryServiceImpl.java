package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CategoryDao;
import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.CategoryExample;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired CategoryDao categoryDao;
	
	@Override
	public void addCategory(Category category) {
		category.setIdx(Utils.generateIdx());
		categoryDao.insertCategory(category);
	}

	@Override
	public List<Category> findCategoriesByParentIdx(String parentIdx) {
		CategoryExample example = new CategoryExample();
		if (parentIdx == null)
			example.createCriteria().andParentIdxIsNull();
		else
			example.createCriteria().andParentIdxEqualTo(parentIdx);
		return categoryDao.selectCategory(example);
	}

	@Override
	public void modifyCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public void removeCategoryByIdx(String idx) {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andIdxEqualTo(idx);
		categoryDao.deleteCategory(example);
	}
}