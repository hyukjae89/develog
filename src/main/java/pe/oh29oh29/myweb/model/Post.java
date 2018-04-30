package pe.oh29oh29.myweb.model;

public class Post extends PostKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POSTS.TITLE
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POSTS.DESCRIPTION
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POSTS.REG_DATE
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	private String regDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column POSTS.CONTENTS
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	private String contents;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POSTS.TITLE
	 * @return  the value of POSTS.TITLE
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POSTS.TITLE
	 * @param title  the value for POSTS.TITLE
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POSTS.DESCRIPTION
	 * @return  the value of POSTS.DESCRIPTION
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POSTS.DESCRIPTION
	 * @param description  the value for POSTS.DESCRIPTION
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POSTS.REG_DATE
	 * @return  the value of POSTS.REG_DATE
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POSTS.REG_DATE
	 * @param regDate  the value for POSTS.REG_DATE
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column POSTS.CONTENTS
	 * @return  the value of POSTS.CONTENTS
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column POSTS.CONTENTS
	 * @param contents  the value for POSTS.CONTENTS
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
}