package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.dao.PostViewDao;
import pe.oh29oh29.myweb.model.CommentExample;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostExample;
import pe.oh29oh29.myweb.model.PostViewExample;
import pe.oh29oh29.myweb.model.PostViewWithBLOBs;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired PostDao postDao;
	@Autowired PostViewDao postViewDao;
	@Autowired CommentDao commentDao;

	@Override
	public void writePost(Post post) {
		post.setIdx(Utils.generateIdx());
		post.setRegDate(Utils.generateNowGMTDate());
		postDao.insertPost(post);
		
	}
	
	@Override
	public void modifyPost(Post post) {
		postDao.updatePost(post);
	}

	@Override
	public void removePost(String idx) {
		// 코멘트 삭제
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andPostIdxEqualTo(idx);
		commentDao.deleteComment(commentExample);
		
		PostExample example = new PostExample();
		example.createCriteria().andIdxEqualTo(idx);
		postDao.deletePost(example);
	}

	@Override
	public PostViewWithBLOBs readPost(String idx) {
		PostViewExample example = new PostViewExample();
		example.createCriteria().andIdxEqualTo(idx);
		List<PostViewWithBLOBs> posts = postViewDao.selectPosts(example); 
		return posts.size() > 0 ? posts.get(0) : null;
	}

	@Override
	public List<PostViewWithBLOBs> getPosts(String tag) {
		PostViewExample example = new PostViewExample();
		example.createCriteria().andconte
		return null;
	}
	
	
}