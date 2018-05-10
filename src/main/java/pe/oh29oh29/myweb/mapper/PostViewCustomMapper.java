package pe.oh29oh29.myweb.mapper;

import java.util.List;
import java.util.Map;

import pe.oh29oh29.myweb.model.PostView;

public interface PostViewCustomMapper {

	List<PostView> selectByTag(Map<String, Object> parameters);
	
	long countByTag(String tag);
	
}
