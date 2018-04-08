package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Posts;
import pe.oh29oh29.myweb.model.PostsExample;

public interface PostsDao {

	public int insertPost(Posts post);
	
	public Posts selectPost(PostsExample example);
	
	public List<Posts> selectPosts(PostsExample example);
	
	public int updatePost(Posts post);
	
	public int deletePost(PostsExample example);
	
}
