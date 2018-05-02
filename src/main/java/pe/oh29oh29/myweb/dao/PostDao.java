package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostExample;

public interface PostDao {

	public int insertPost(Post post);
	
	public List<Post> selectPost(PostExample example);
	
	public int updatePost(Post post);
	
	public int deletePost(PostExample example);
	
	public int deletePost(String idx, String memberIdx);

	public int deleteAllPosts();
	
}
