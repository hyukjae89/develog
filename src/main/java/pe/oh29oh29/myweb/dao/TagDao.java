package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Tag;
import pe.oh29oh29.myweb.model.TagExample;

public interface TagDao {

	public int insertTag(Tag tag);
	
	public int updateTag(Tag tag);
	
	public int deleteTag(TagExample example);

	public int deleteAllTags();
	
	public List<Tag> selectTag(TagExample example);
	
}
