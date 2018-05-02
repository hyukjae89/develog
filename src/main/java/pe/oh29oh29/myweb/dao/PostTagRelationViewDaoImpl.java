package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.PostTagRelationViewMapper;
import pe.oh29oh29.myweb.model.PostTagRelationView;
import pe.oh29oh29.myweb.model.PostTagRelationViewExample;

@Repository
public class PostTagRelationViewDaoImpl implements PostTagRelationViewDao {

	@Autowired PostTagRelationViewMapper postTagRelationViewMapper;
	
	@Override
	public List<PostTagRelationView> selectPostTagRelations() {
		return postTagRelationViewMapper.selectByExample(null);
	}

	@Override
	public List<PostTagRelationView> selectPostTagRelations(PostTagRelationViewExample example) {
		return postTagRelationViewMapper.selectByExample(example);
	}

}
