package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.dao.PostsDao;
import pe.oh29oh29.myweb.model.Posts;

@Service
public class PostsServiceImpl implements PostsService{
	
	@Autowired PostsDao postDao;

	@Override
	public void createPost() {
		
	}

	@Override
	public Posts readPost() {
		return null;
	}

	@Override
	public List<Posts> readPosts() {
		return null;
	}
	
	@Override
	public void modifyPost() {
		
	}

	@Override
	public void removePost() {
		
	}
}