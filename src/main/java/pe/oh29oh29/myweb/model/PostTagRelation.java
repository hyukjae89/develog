package pe.oh29oh29.myweb.model;

public class PostTagRelation {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_tag_relations.POST_IDX
     *
     * @mbg.generated Tue May 01 00:10:51 KST 2018
     */
    private String postIdx;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column post_tag_relations.TAG_IDX
     *
     * @mbg.generated Tue May 01 00:10:51 KST 2018
     */
    private String tagIdx;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_tag_relations.POST_IDX
     *
     * @return the value of post_tag_relations.POST_IDX
     *
     * @mbg.generated Tue May 01 00:10:51 KST 2018
     */
    public String getPostIdx() {
        return postIdx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_tag_relations.POST_IDX
     *
     * @param postIdx the value for post_tag_relations.POST_IDX
     *
     * @mbg.generated Tue May 01 00:10:51 KST 2018
     */
    public void setPostIdx(String postIdx) {
        this.postIdx = postIdx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column post_tag_relations.TAG_IDX
     *
     * @return the value of post_tag_relations.TAG_IDX
     *
     * @mbg.generated Tue May 01 00:10:51 KST 2018
     */
    public String getTagIdx() {
        return tagIdx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column post_tag_relations.TAG_IDX
     *
     * @param tagIdx the value for post_tag_relations.TAG_IDX
     *
     * @mbg.generated Tue May 01 00:10:51 KST 2018
     */
    public void setTagIdx(String tagIdx) {
        this.tagIdx = tagIdx;
    }
}