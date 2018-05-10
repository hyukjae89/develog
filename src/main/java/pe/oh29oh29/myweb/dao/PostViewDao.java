package pe.oh29oh29.myweb.dao;

import java.util.List;
import java.util.Map;

import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.PostViewExample;

public interface PostViewDao {

	public List<PostView> selectPosts();
	
	public List<PostView> selectPosts(PostViewExample example);
	
	public List<PostView> selectPosts(Map<String, Object> parameters);
	
	public long countPosts(String tag);
	
}
