package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.PostTagRelationView;
import pe.oh29oh29.myweb.model.PostTagRelationViewExample;

public interface PostTagRelationViewDao {

	public List<PostTagRelationView> selectPostTagRelations();
	
	public List<PostTagRelationView> selectPostTagRelations(PostTagRelationViewExample example);
	
}
