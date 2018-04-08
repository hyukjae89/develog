package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.PostsMapper;
import pe.oh29oh29.myweb.model.Posts;
import pe.oh29oh29.myweb.model.PostsExample;

@Repository
public class PostsDaoImpl implements PostsDao{
	
	@Autowired PostsMapper postsMapper;

	@Override
	public int insertPost(Posts post) {
		return postsMapper.insert(post);
	}

	@Override
	public Posts selectPost(PostsExample example) {
		return null;
	}

	@Override
	public List<Posts> selectPosts(PostsExample example) {
		return postsMapper.selectByExample(example);
	}

	@Override
	public int updatePost(Posts post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePost(PostsExample example) {
		// TODO Auto-generated method stub
		return 0;
	}




	


}
