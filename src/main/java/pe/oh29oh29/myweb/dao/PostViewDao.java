package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.PostViewExample;
import pe.oh29oh29.myweb.model.PostViewWithBLOBs;

public interface PostViewDao {

	public List<PostViewWithBLOBs> selectPosts(PostViewExample example);
}
