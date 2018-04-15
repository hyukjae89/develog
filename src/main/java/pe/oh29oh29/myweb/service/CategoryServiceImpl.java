package pe.oh29oh29.myweb.service;

import java.util.ArrayList;
import java.util.List;

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
	public void addCategory(Category category) {
		category.setIdx(Utils.generateIdx());
		categoryDao.insertCategory(category);
	}

	@Override
	public List<Category> findCategoriesByParentIdx(String parentIdx) {
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		
		if (parentIdx == null)
			criteria.andParentIdxIsNull();
		else
			criteria.andParentIdxEqualTo(parentIdx);
		
		return categoryDao.selectCategory(example);
	}

	@Override
	public void modifyCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public void removeCategoryByIdx(String idx) {
		List<Category> subCategories = findCategoriesByParentIdx(idx);
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
}