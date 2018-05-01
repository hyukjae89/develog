package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.PostTagRelation;
import pe.oh29oh29.myweb.model.PostTagRelationExample;

public interface PostTagRelationDao {

	public int insertPostTagRelation(PostTagRelation postTagRelation);
	
	public int updatePostTagRelation(PostTagRelation postTagRelation, PostTagRelationExample example);
	
	public int deletePostTagRelation(PostTagRelationExample example);

	public int deleteAllPostTagRelations();
	
	public List<PostTagRelation> selectPostTagRelation(PostTagRelationExample example);
	
}
