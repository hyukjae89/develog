package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.PostViewCustomMapper;
import pe.oh29oh29.myweb.mapper.PostViewMapper;
import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.PostViewExample;

@Repository
public class PostViewDaoImpl implements PostViewDao {

	@Autowired PostViewMapper postViewMapper;
	@Autowired PostViewCustomMapper postViewCustomMapper;
	
	@Override
	public List<PostView> selectPosts() {
		return postViewMapper.selectByExampleWithBLOBs(null);
	}
	
	@Override
	public List<PostView> selectPosts(PostViewExample example) {
		return postViewMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<PostView> selectPosts(String tag) {
		return postViewCustomMapper.selectByTag(tag);
	}

}
