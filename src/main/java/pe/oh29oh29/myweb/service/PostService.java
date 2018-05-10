package pe.oh29oh29.myweb.service;

import java.util.List;
import java.util.Map;

import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostView;

public interface PostService {

	public void writePost(Post post, String tags);
	
	public void modifyPost(Post post, String tags);
	
	public void removePost(String uriId, String memberIdx);
	
	public PostView getPost(String uriId);
	
	public List<PostView> getPosts(String tag, int nowPage);
	
	public Map<String, Object> getPostListPaginationInfo(String tag);
	
}