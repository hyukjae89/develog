package pe.oh29oh29.myweb.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pe.oh29oh29.myweb.model.PostRelationExample;
import pe.oh29oh29.myweb.model.PostRelationKey;

public interface PostRelationMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	long countByExample(PostRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	int deleteByExample(PostRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	int deleteByPrimaryKey(PostRelationKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	int insert(PostRelationKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	int insertSelective(PostRelationKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	List<PostRelationKey> selectByExample(PostRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	int updateByExampleSelective(@Param("record") PostRelationKey record,
			@Param("example") PostRelationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table post_relation
	 * @mbg.generated  Sun Apr 08 23:39:31 KST 2018
	 */
	int updateByExample(@Param("record") PostRelationKey record, @Param("example") PostRelationExample example);
}