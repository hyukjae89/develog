package pe.oh29oh29.myweb.mapper;

import java.util.List;

import pe.oh29oh29.myweb.model.PostView;
import pe.oh29oh29.myweb.model.PostViewExample;

public interface PostViewMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Thu Apr 26 16:57:40 KST 2018
	 */
	long countByExample(PostViewExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Thu Apr 26 16:57:40 KST 2018
	 */
	List<PostView> selectByExampleWithBLOBs(PostViewExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Thu Apr 26 16:57:40 KST 2018
	 */
	List<PostView> selectByExample(PostViewExample example);
}