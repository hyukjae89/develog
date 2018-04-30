package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostView;

public interface PostService {

	public void writePost(Post post);
	
	public void writePost(Post post, List<String> relatedPostIdxList);
	
	public void modifyPost(Post post);
	
	public void removePost(String idx);
	
	public PostView readPost(String idx);
	
	public List<PostView> getPosts(String categoryName);
	
	public List<PostView> getPosts();
}
