package pe.oh29oh29.myweb.service;

import java.util.List;
import java.util.Map;

import pe.oh29oh29.myweb.model.Tag;

public interface TagService {

	public List<Tag> getTags(String tag);
	
	public Map<Character, List<String>> getTags();
	
}
