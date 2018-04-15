package pe.oh29oh29.myweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.dao.RelationDao;
import pe.oh29oh29.myweb.model.CommentExample;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostExample;
import pe.oh29oh29.myweb.model.RelationExample;
import pe.oh29oh29.myweb.model.RelationKey;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired PostDao postDao;
	@Autowired CommentDao commentDao;
	@Autowired RelationDao relationDao;

	@Override
	public void writePost(Post post) {
		writePost(post, new ArrayList<String>());
	}
	
	@Override
	public void writePost(Post post, List<String> relatedPostIdxList) {
		post.setIdx(Utils.generateIdx());
		post.setRegDate(Utils.generateNowGMTDate());
		postDao.insertPost(post);
		
		for (String relatedPostIdx : relatedPostIdxList) {
			RelationKey relation = new RelationKey();
			relation.setIdx(Utils.generateIdx());
			relation.setPostIdx(post.getIdx());
			relation.setRelatedPostIdx(relatedPostIdx);
			relationDao.insertRelation(relation);
			
			RelationKey reverseRelation = new RelationKey();
			reverseRelation.setIdx(Utils.generateIdx());
			reverseRelation.setPostIdx(relatedPostIdx);
			reverseRelation.setRelatedPostIdx(post.getIdx());
			relationDao.insertRelation(reverseRelation);
		}
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
		example.setOrderByClause("REG_DATE DESC");
		return postDao.selectPost(example);
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
		
		// 연관 포스트 정보 삭제
		RelationExample relationExample = new RelationExample();
		relationExample.createCriteria().andPostIdxEqualTo(idx);
		relationExample.or().andRelatedPostIdxEqualTo(idx);
		relationDao.deleteRelation(relationExample);
		
		PostExample example = new PostExample();
		example.createCriteria().andIdxEqualTo(idx);
		postDao.deletePost(example);
	}
}