package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.RelationExample;
import pe.oh29oh29.myweb.model.RelationKey;

public interface RelationDao {

	public int insertRelation(RelationKey relation);
	
	public List<RelationKey> selectRelations(RelationExample example);
	
	public int updateRelation(RelationKey relation, RelationExample example);
	
	public int deleteRelation(RelationExample example);
	
	public int deleteAllRelations();

}