package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.PostTagRelationMapper;
import pe.oh29oh29.myweb.model.PostTagRelation;
import pe.oh29oh29.myweb.model.PostTagRelationExample;

@Repository
public class PostTagRelationDaoImpl implements PostTagRelationDao {

	@Autowired PostTagRelationMapper postTagRelationMapper;
	
	@Override
	public int insertPostTagRelation(PostTagRelation postTagRelation) {
		return postTagRelationMapper.insertSelective(postTagRelation);
	}

	@Override
	public int updatePostTagRelation(PostTagRelation postTagRelation, PostTagRelationExample example) {
		return postTagRelationMapper.updateByExampleSelective(postTagRelation, example);
	}

	@Override
	public int deletePostTagRelation(PostTagRelationExample example) {
		return postTagRelationMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllPostTagRelations() {
		return postTagRelationMapper.deleteByExample(null);
	}

	@Override
	public List<PostTagRelation> selectPostTagRelation(PostTagRelationExample example) {
		return postTagRelationMapper.selectByExample(example);
	}

	@Override
	public long countPostTagRelation(PostTagRelationExample example) {
		return postTagRelationMapper.countByExample(example);
	}

}
