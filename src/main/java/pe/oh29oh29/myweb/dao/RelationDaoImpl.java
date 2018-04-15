package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.RelationMapper;
import pe.oh29oh29.myweb.model.RelationExample;
import pe.oh29oh29.myweb.model.RelationKey;

@Repository
public class RelationDaoImpl implements RelationDao {

	@Autowired RelationMapper relationMapper;
	
	@Override
	public int insertRelation(RelationKey relation) {
		return relationMapper.insertSelective(relation);
	}

	@Override
	public List<RelationKey> selectRelations(RelationExample example) {
		return relationMapper.selectByExample(example);
	}

	@Override
	public int updateRelation(RelationKey relation, RelationExample example) {
		return relationMapper.updateByExampleSelective(relation, example);
	}

	@Override
	public int deleteRelation(RelationExample example) {
		return relationMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllRelations() {
		return relationMapper.deleteByExample(null);
	}

}
