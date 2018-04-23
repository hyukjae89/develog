package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.mapper.CategoryMapper;
import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.CategoryExample;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired CategoryMapper categoryMapper;

	@Override
	public int insertCategory(Category category) {
		return categoryMapper.insertSelective(category);
	}

	@Override
	public int updateCategory(Category category) {
		return categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public int deleteCategory(String idx) {
		return categoryMapper.deleteByPrimaryKey(idx);
	}
	
	@Override
	public int deleteCategory(CategoryExample example) {
		return categoryMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllCategories() {
		Category category = new Category();
		category.setIdx(Utils.generateIdx());
		category.setParentIdx(null);
		category.setName("deleted DATA");
		category.setOrd(0);
		CategoryExample example = new CategoryExample();
		example.createCriteria().andParentIdxIsNotNull();
		categoryMapper.updateByExample(category, example);
		return categoryMapper.deleteByExample(null);
	}

	@Override
	public Category selectCategory(String idx) {
		return categoryMapper.selectByPrimaryKey(idx);
	}
	
	@Override
	public List<Category> selectCategories() {
		return categoryMapper.selectByExample(null);
	}
	
	@Override
	public List<Category> selectCategories(CategoryExample example) {
		return categoryMapper.selectByExample(example);
	}

}
