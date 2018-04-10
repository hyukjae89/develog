package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List<Category> selectCategory(CategoryExample example) {
		return categoryMapper.selectByExample(example);
	}

	@Override
	public int updateCategory(Category category) {
		return categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public int deleteCategory(CategoryExample example) {
		return categoryMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllCategories() {
		return categoryMapper.deleteByExample(null);
	}

}
