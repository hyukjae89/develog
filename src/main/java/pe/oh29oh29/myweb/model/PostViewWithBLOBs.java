package pe.oh29oh29.myweb.model;

public class PostViewWithBLOBs extends PostView {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column posts_view.CONTENTS
     *
     * @mbg.generated Mon Apr 30 22:29:24 KST 2018
     */
    private String contents;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column posts_view.TAGS
     *
     * @mbg.generated Mon Apr 30 22:29:24 KST 2018
     */
    private String tags;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column posts_view.CONTENTS
     *
     * @return the value of posts_view.CONTENTS
     *
     * @mbg.generated Mon Apr 30 22:29:24 KST 2018
     */
    public String getContents() {
        return contents;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column posts_view.CONTENTS
     *
     * @param contents the value for posts_view.CONTENTS
     *
     * @mbg.generated Mon Apr 30 22:29:24 KST 2018
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column posts_view.TAGS
     *
     * @return the value of posts_view.TAGS
     *
     * @mbg.generated Mon Apr 30 22:29:24 KST 2018
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column posts_view.TAGS
     *
     * @param tags the value for posts_view.TAGS
     *
     * @mbg.generated Mon Apr 30 22:29:24 KST 2018
     */
    public void setTags(String tags) {
        this.tags = tags;
    }
}