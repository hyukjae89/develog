package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Post;

public interface PostService {

	public void writePost(Post post);
	
	public Post readPost(String idx);
	
	public List<Post> readPosts(String categoryIdx);
	
	public void modifyPost(Post post);
	
	public void removePost(String idx);
}
