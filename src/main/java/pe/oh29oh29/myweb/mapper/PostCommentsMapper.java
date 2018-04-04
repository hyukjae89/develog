package pe.oh29oh29.myweb.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pe.oh29oh29.myweb.model.PostCommentsExample;
import pe.oh29oh29.myweb.model.PostCommentsKey;

public interface PostCommentsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	long countByExample(PostCommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	int deleteByExample(PostCommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	int deleteByPrimaryKey(PostCommentsKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	int insert(PostCommentsKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	int insertSelective(PostCommentsKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	List<PostCommentsKey> selectByExample(PostCommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	int updateByExampleSelective(@Param("record") PostCommentsKey record,
			@Param("example") PostCommentsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_COMMENTS
	 * @mbg.generated  Wed Apr 04 16:57:34 KST 2018
	 */
	int updateByExample(@Param("record") PostCommentsKey record, @Param("example") PostCommentsExample example);
}