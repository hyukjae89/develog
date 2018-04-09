package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CategoriesDao;
import pe.oh29oh29.myweb.model.Categories;
import pe.oh29oh29.myweb.model.CategoriesExample;

@Service
public class CategoriesServiceImpl implements CategoriesService{

	@Autowired CategoriesDao categoriesDao;
	
	@Override
	public void addCategory(Categories category) {
		category.setIdx(Utils.generateIdx());
		categoriesDao.insertCategory(category);
	}

	@Override
	public List<Categories> findCategoriesByParentIdx(String parentIdx) {
		CategoriesExample example = new CategoriesExample();
		if (parentIdx == null)
			example.createCriteria().andParentIdxIsNull();
		else
			example.createCriteria().andParentIdxEqualTo(parentIdx);
		return categoriesDao.selectCategories(example);
	}

	@Override
	public void modifyCategory(Categories category) {
		categoriesDao.updateCategory(category);
	}

	@Override
	public void removeCategoryByIdx(String idx) {
		CategoriesExample example = new CategoriesExample();
		example.createCriteria().andIdxEqualTo(idx);
		categoriesDao.deleteCategory(example);
	}
}