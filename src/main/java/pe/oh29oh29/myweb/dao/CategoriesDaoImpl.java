package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.CategoriesMapper;
import pe.oh29oh29.myweb.model.Categories;
import pe.oh29oh29.myweb.model.CategoriesExample;

@Repository
public class CategoriesDaoImpl implements CategoriesDao {
	
	@Autowired CategoriesMapper categoriesMapper;

	@Override
	public int insertCategory(Categories category) {
		return categoriesMapper.insertSelective(category);
	}

	@Override
	public List<Categories> selectCategories(CategoriesExample example) {
		return categoriesMapper.selectByExample(example);
	}

	@Override
	public int updateCategory(Categories category) {
		return categoriesMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public int deleteCategory(CategoriesExample example) {
		return categoriesMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllCategories() {
		return categoriesMapper.deleteByExample(null);
	}

}
