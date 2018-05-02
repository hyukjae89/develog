package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.PostMapper;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostExample;

@Repository
public class PostDaoImpl implements PostDao{
	
	@Autowired PostMapper postMapper;

	@Override
	public int insertPost(Post post) {
		return postMapper.insertSelective(post);
	}

	@Override
	public List<Post> selectPost(PostExample example) {
		return postMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int updatePost(Post post) {
		return postMapper.updateByPrimaryKeySelective(post);
	}

	@Override
	public int deletePost(PostExample example) {
		return postMapper.deleteByExample(example);
	}
	
	@Override
	public int deletePost(String idx, String memberIdx) {
		return postMapper.deleteByPrimaryKey(idx, memberIdx);
	}

	@Override
	public int deleteAllPosts() {
		return postMapper.deleteByExample(null);
	}
}