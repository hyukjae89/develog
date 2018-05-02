package pe.oh29oh29.myweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.dao.PostTagRelationDao;
import pe.oh29oh29.myweb.dao.PostTagRelationViewDao;
import pe.oh29oh29.myweb.dao.PostViewDao;
import pe.oh29oh29.myweb.dao.TagDao;
import pe.oh29oh29.myweb.model.CommentExample;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostTagRelation;
import pe.oh29oh29.myweb.model.PostTagRelationExample;
import pe.oh29oh29.myweb.model.PostTagRelationView;
import pe.oh29oh29.myweb.model.PostTagRelationViewExample;
import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.PostViewExample;
import pe.oh29oh29.myweb.model.Tag;
import pe.oh29oh29.myweb.model.TagExample;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired PostDao postDao;
	@Autowired PostViewDao postViewDao;
	@Autowired TagDao tagDao;
	@Autowired PostTagRelationDao postTagRelationDao;
	@Autowired PostTagRelationViewDao postTagRelationViewDao;
	@Autowired CommentDao commentDao;

	@Override
	public void writePost(Post post, String tags) {
		post.setIdx(Utils.generateIdx());
		post.setRegDate(Utils.generateNowGMTDate());
		postDao.insertPost(post);
		
		String[] tagArr = tags.split("\\s");
		TagExample tagExample = new TagExample();
		
		for (int i = 0; i < tagArr.length; i++) {
			String tagStr = tagArr[i];
			tagExample.createCriteria().andNameEqualTo(tagStr);
			Tag tag = new Tag();
			if (tagDao.countTag(tagExample) == 0) {
				tag.setIdx(Utils.generateIdx());
				tag.setName(tagStr);
				tagDao.insertTag(tag);
			} else {
				tag = tagDao.selectTag(tagExample).get(0);
			}
			tagExample.clear();
			
			PostTagRelation relation = new PostTagRelation();
			relation.setPostIdx(post.getIdx());
			relation.setTagIdx(tag.getIdx());
			relation.setOrd(i);
			postTagRelationDao.insertPostTagRelation(relation);
		}
	}
	
	@Override
	public void modifyPost(Post post, String tags) {
		// Post 수정
		postDao.updatePost(post);
		
		PostTagRelationViewExample postTagRelationViewExample = new PostTagRelationViewExample();
		postTagRelationViewExample.createCriteria().andPostIdxEqualTo(post.getIdx());
		postTagRelationViewExample.setOrderByClause("ORD ASC");
		List<PostTagRelationView> prevTags = postTagRelationViewDao.selectPostTagRelations(postTagRelationViewExample);
		
		String[] nextTags = tags.split("\\s");
		TagExample tagExample = new TagExample();
		
		Map<String, Integer> prevTagMap = new HashMap<String, Integer>();
		Map<String, Integer> nextTagMap = new HashMap<String, Integer>();
		
		for (PostTagRelationView prevTag : prevTags) {
			prevTagMap.put(prevTag.getTagName(), prevTag.getOrd());
		}
		
		for (int ord = 0; ord < nextTags.length; ord++) {
			nextTagMap.put(nextTags[ord], ord);
		}
		
		for (String nextTag : nextTags) {
			if (prevTagMap.containsKey(nextTag)) {
				if (nextTagMap.get(nextTag) != prevTagMap.get(nextTag)) {
					// PostTagRelation ord 수정
					PostTagRelation relation = new PostTagRelation();
					relation.setOrd(nextTagMap.get(nextTag));
					PostTagRelationExample postTagRelationExample = new PostTagRelationExample();
					postTagRelationExample.createCriteria().andPostIdxEqualTo(post.getIdx()).andOrdEqualTo(prevTagMap.get(nextTag));
					postTagRelationDao.updatePostTagRelation(relation, postTagRelationExample);
				}
			} else {
				// PostTagRelation 추가
				tagExample.createCriteria().andNameEqualTo(nextTag);
				Tag tag = new Tag();
				if (tagDao.countTag(tagExample) == 0) {
					// Tag 추가
					tag.setIdx(Utils.generateIdx());
					tag.setName(nextTag);
					tagDao.insertTag(tag);
				} else {
					tag = tagDao.selectTag(tagExample).get(0);
				}
				tagExample.clear();
				
				PostTagRelation relation = new PostTagRelation();
				relation.setPostIdx(post.getIdx());
				relation.setTagIdx(tag.getIdx());
				postTagRelationDao.insertPostTagRelation(relation);
			}
		}
		
		for (PostTagRelationView prevTag : prevTags) {
			if (!nextTagMap.containsKey(prevTag.getTagName())) {
				// PostTagRelation 삭제
				PostTagRelationExample postTagRelationExample = new PostTagRelationExample();
				postTagRelationExample.createCriteria().andPostIdxEqualTo(prevTag.getPostIdx()).andTagIdxEqualTo(prevTag.getTagIdx());
				postTagRelationDao.deletePostTagRelation(postTagRelationExample);
				
				postTagRelationExample.clear();
				postTagRelationExample.createCriteria().andTagIdxEqualTo(prevTag.getTagIdx());
				if (postTagRelationDao.countPostTagRelation(postTagRelationExample) == 0) {
					// Tag 삭제
					tagDao.deleteTag(prevTag.getTagIdx());
				}
			}
		}
	}

	@Override
	public void removePost(String idx, String memberIdx) {
		// Comment 삭제
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andPostIdxEqualTo(idx);
		commentDao.deleteComment(commentExample);
		
		PostTagRelationExample postTagRelationExample = new PostTagRelationExample();
		postTagRelationExample.createCriteria().andPostIdxEqualTo(idx);
		
		// PostTagRelation 조회
		List<PostTagRelation> postTagRelations = postTagRelationDao.selectPostTagRelation(postTagRelationExample);
		
		// PostTagRelation 삭제
		postTagRelationDao.deletePostTagRelation(postTagRelationExample);
		
		// Post 삭제
		postDao.deletePost(idx, memberIdx);
		
		for (PostTagRelation postTagRelation : postTagRelations) {
			postTagRelationExample.clear();
			postTagRelationExample.createCriteria().andTagIdxEqualTo(postTagRelation.getTagIdx());
			if (postTagRelationDao.countPostTagRelation(postTagRelationExample) == 0) {
				// Tag 삭제
				tagDao.deleteTag(postTagRelation.getTagIdx());
			}
		}
		
	}

	@Override
	public PostView getPost(String uriId) {
		PostViewExample example = new PostViewExample();
		example.createCriteria().andUriIdEqualTo(uriId);
		List<PostView> posts = postViewDao.selectPosts(example); 
		return posts.size() > 0 ? posts.get(0) : null;
	}

	@Override
	public List<PostView> getPosts(String tag) {
		if (tag == null)
			tag = "";
		tag = "%" + tag + "%";
		return postViewDao.selectPosts(tag);
	}

	@Override
	public List<PostView> getPosts() {
		return getPosts();
	}
	
	
}