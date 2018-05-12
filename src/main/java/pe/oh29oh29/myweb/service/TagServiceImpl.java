package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.dao.TagDao;
import pe.oh29oh29.myweb.model.Tag;
import pe.oh29oh29.myweb.model.TagExample;

@Service
public class TagServiceImpl implements TagService {

	@Autowired TagDao tagDao;
	
	@Override
	public List<Tag> getTags(String tag) {
		if (tag == null || tag.equals(""))
			return null;
		
		tag = "%" + tag + "%";
		
		TagExample example = new TagExample();
		example.createCriteria().andNameLike(tag);
		
		return tagDao.selectTag(example);
	}

}
