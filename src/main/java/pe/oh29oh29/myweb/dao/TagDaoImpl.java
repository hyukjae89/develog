package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.TagMapper;
import pe.oh29oh29.myweb.model.Tag;
import pe.oh29oh29.myweb.model.TagExample;

@Repository
public class TagDaoImpl implements TagDao {

	@Autowired TagMapper tagMapper;
	
	@Override
	public int insertTag(Tag tag) {
		return tagMapper.insertSelective(tag);
	}

	@Override
	public int updateTag(Tag tag) {
		return tagMapper.updateByPrimaryKeySelective(tag);
	}

	@Override
	public int deleteTag(String idx) {
		return tagMapper.deleteByPrimaryKey(idx);
	}

	@Override
	public int deleteAllTags() {
		return tagMapper.deleteByExample(null);
	}

	@Override
	public List<Tag> selectTag(TagExample example) {
		return tagMapper.selectByExample(example);
	}

	@Override
	public long countTag(TagExample example) {
		return tagMapper.countByExample(example);
	}
}
