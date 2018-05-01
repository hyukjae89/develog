package pe.oh29oh29.myweb.mapper;

import java.util.List;

import pe.oh29oh29.myweb.model.PostView;

public interface PostViewCustomMapper {

	List<PostView> selectByTag(String tag);
	
}
