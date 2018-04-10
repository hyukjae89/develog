package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostExample;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired PostDao postDao;

	@Override
	public void writePost(Post post) {
		post.setIdx(Utils.generateIdx());
		post.setRegDate(Utils.generateNowGMTDate());
		postDao.insertPost(post);
	}

	@Override
	public Post readPost(String idx) {
		PostExample example = new PostExample();
		example.createCriteria().andIdxEqualTo(idx);
		List<Post> post = postDao.selectPost(example);
		return post.size() > 0 ? post.get(0) : null;
	}

	@Override
	public List<Post> readPosts(String categoryIdx) {
		PostExample example = new PostExample();
		example.createCriteria().andCategoryIdxEqualTo(categoryIdx);
		return postDao.selectPost(example);
	}
	
	@Override
	public void modifyPost(Post post) {
		postDao.updatePost(post);
	}

	@Override
	public void removePost(String idx) {
		PostExample example = new PostExample();
		example.createCriteria().andIdxEqualTo(idx);
		postDao.deletePost(example);
	}
}