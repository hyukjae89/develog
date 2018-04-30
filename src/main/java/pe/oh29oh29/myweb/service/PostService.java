package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.PostViewWithBLOBs;

public interface PostService {

	public void writePost(Post post);
	
	public void modifyPost(Post post);
	
	public void removePost(String idx);
	
	public PostViewWithBLOBs readPost(String idx);
	
	public List<PostViewWithBLOBs> getPosts(String tag);
	
}
