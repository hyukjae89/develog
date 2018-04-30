package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.PostViewMapper;
import pe.oh29oh29.myweb.model.PostViewExample;
import pe.oh29oh29.myweb.model.PostViewWithBLOBs;

@Repository
public class PostViewDaoImpl implements PostViewDao {

	@Autowired PostViewMapper postViewMapper;
	
	@Override
	public List<PostViewWithBLOBs> selectPosts(PostViewExample example) {
		return postViewMapper.selectByExampleWithBLOBs(example);
	}
}
