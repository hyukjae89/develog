package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Posts;

public interface PostsService {

	public void createPost();
	
	public Posts readPost();
	
	public List<Posts> readPosts();
	
	public void modifyPost();
	
	public void removePost();
}
