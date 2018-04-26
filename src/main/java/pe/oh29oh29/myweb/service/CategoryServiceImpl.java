package pe.oh29oh29.myweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CategoryDao;
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.CategoryExample;
import pe.oh29oh29.myweb.model.CategoryExample.Criteria;
import pe.oh29oh29.myweb.model.CommentExample;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostExample;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired CategoryDao categoryDao;
	@Autowired PostDao postDao;
	@Autowired CommentDao commentDao;
	
	@Override
	public String addCategory(Category category) {
		category.setIdx(Utils.generateIdx());
		categoryDao.insertCategory(category);
		return category.getIdx();
	}

	@Override
	public void modifyCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public void removeCategoryByIdx(String idx) {
		List<Category> subCategories = findCategories(idx, AccessSpecifier.TOTAL);
		for (Category category : subCategories) {
			removeCategoryByIdx(category.getIdx());
		}
		
		PostExample postExample = new PostExample();
		postExample.createCriteria().andCategoryIdxEqualTo(idx);
		List<Post> posts = postDao.selectPost(postExample);
		if (!posts.isEmpty()) {
			List<String> postIdxList = new ArrayList<String>();
			for (Post post : posts) {
				postIdxList.add(post.getIdx());
			}
			CommentExample commentExample = new CommentExample();
			commentExample.createCriteria().andPostIdxIn(postIdxList);
			commentDao.deleteComment(commentExample);
		}
		postDao.deletePost(postExample);
		
		CategoryExample example = new CategoryExample();
		example.createCriteria().andIdxEqualTo(idx);
		categoryDao.deleteCategory(example);
	}
	
	@Override
	public void saveCategories(List<Category> categories) {
		List<Category> originalCategories = categoryDao.selectCategories();
		Map<String, Category> originalCategoryPack = new HashMap<String, Category>();
		for (Category category : originalCategories) {
			originalCategoryPack.put(category.getIdx(), category);
		}
		
		for (Category category : categories) {
			if (category.getIdx() == null) {
				category.setIdx(Utils.generateIdx());
				categoryDao.insertCategory(category);
			} else if (originalCategoryPack.containsKey(category.getIdx())){
				categoryDao.updateCategory(category);
				originalCategoryPack.remove(category.getIdx());
			}
		}
		
		for (Category category : originalCategoryPack.values()) {
			categoryDao.deleteCategory(category.getIdx());
		}
	}

	@Override
	public Category findCategory(String name) {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andNameEqualTo(name);
		List<Category> categories = categoryDao.selectCategories(example);
		if (categories.isEmpty())
			return null;
		else
			return categories.get(0);
	}
	
	@Override
	public List<Category> findCategories(AccessSpecifier accessSpecifier) {
		return findCategories(null, accessSpecifier);
	}
	
	@Override
	public List<Category> findCategories(String parentIdx, AccessSpecifier accessSpecifier) {
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		
		if (parentIdx == null)
			criteria.andParentIdxIsNull();
		else
			criteria.andParentIdxEqualTo(parentIdx);

		if (accessSpecifier == AccessSpecifier.PUBLIC)
			criteria.andIsPrivateEqualTo(0);
		
		example.setOrderByClause("DEPTH ASC, ORD ASC");
		
		List<Category> categories = categoryDao.selectCategories(example);
		
		for (int i = 0; i < categories.size(); ) {
			List<Category> subCategories = findCategories(categories.get(i).getIdx(), accessSpecifier);
			categories.addAll(i + 1, subCategories);
			i += subCategories.size() + 1;
		}
		
		return categories;
	}

}