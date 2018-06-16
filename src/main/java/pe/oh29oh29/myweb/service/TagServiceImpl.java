package pe.oh29oh29.myweb.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public Map<Character, List<String>> getTags() {
		TagExample example = new TagExample();
		example.setOrderByClause("name asc");
		List<Tag> tags = tagDao.selectTag(example);
		Map<Character, List<String>> sortedTags = new LinkedHashMap<Character, List<String>>();
		
		char[] chs = { 
	        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 
	        'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 
	        'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 
	        'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' 
		};
		
		for (Tag tag : tags) {
			System.out.println(tag.getName());
		}
		
		for (Tag tag : tags) {
			char firstChar = tag.getName().toUpperCase().charAt(0);
			if ('A' <= firstChar && firstChar <= 'Z') {
				List<String> tagList = sortedTags.get(firstChar);
				if (tagList == null) {
					tagList = new ArrayList<String>();
					sortedTags.put(firstChar, tagList);
				}
				tagList.add(tag.getName());
			} else {
	            int uniVal = firstChar - 0xAC00;
	            int cho = ((uniVal - (uniVal % 28))/28)/21;
	 
	            List<String> tagList = sortedTags.get(chs[cho]);
	            if (tagList == null) {
	            	tagList = new ArrayList<String>();
					sortedTags.put(chs[cho], tagList);
	            }
				tagList.add(tag.getName());
	        }
		}
		
		return sortedTags;
	}

}
