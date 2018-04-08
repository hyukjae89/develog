package pe.oh29oh29.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.dao.PostsDao;

@Service
public class PostsServiceImpl implements PostsService{
	
	@Autowired PostsDao postDao;

	@Override
	public void makePost() {
		
	}

	@Override
	public void readPost() {
		
	}

	@Override
	public void modifyPost() {
		
	}

	@Override
	public void removePost() {
		
	}
}
