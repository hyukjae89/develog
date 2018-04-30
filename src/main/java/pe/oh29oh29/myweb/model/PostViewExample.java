package pe.oh29oh29.myweb.model;

import java.util.ArrayList;
import java.util.List;

public class PostViewExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public PostViewExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdxIsNull() {
			addCriterion("IDX is null");
			return (Criteria) this;
		}

		public Criteria andIdxIsNotNull() {
			addCriterion("IDX is not null");
			return (Criteria) this;
		}

		public Criteria andIdxEqualTo(String value) {
			addCriterion("IDX =", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxNotEqualTo(String value) {
			addCriterion("IDX <>", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxGreaterThan(String value) {
			addCriterion("IDX >", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxGreaterThanOrEqualTo(String value) {
			addCriterion("IDX >=", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxLessThan(String value) {
			addCriterion("IDX <", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxLessThanOrEqualTo(String value) {
			addCriterion("IDX <=", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxLike(String value) {
			addCriterion("IDX like", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxNotLike(String value) {
			addCriterion("IDX not like", value, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxIn(List<String> values) {
			addCriterion("IDX in", values, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxNotIn(List<String> values) {
			addCriterion("IDX not in", values, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxBetween(String value1, String value2) {
			addCriterion("IDX between", value1, value2, "idx");
			return (Criteria) this;
		}

		public Criteria andIdxNotBetween(String value1, String value2) {
			addCriterion("IDX not between", value1, value2, "idx");
			return (Criteria) this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("TITLE is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("TITLE is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("TITLE =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("TITLE <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("TITLE >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("TITLE >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("TITLE <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("TITLE <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("TITLE like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("TITLE not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("TITLE in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("TITLE not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("TITLE between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("TITLE not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("DESCRIPTION is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("DESCRIPTION is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("DESCRIPTION =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("DESCRIPTION <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("DESCRIPTION >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("DESCRIPTION >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("DESCRIPTION <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("DESCRIPTION <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("DESCRIPTION like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("DESCRIPTION not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("DESCRIPTION in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("DESCRIPTION not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("DESCRIPTION between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("DESCRIPTION not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andRegDateIsNull() {
			addCriterion("REG_DATE is null");
			return (Criteria) this;
		}

		public Criteria andRegDateIsNotNull() {
			addCriterion("REG_DATE is not null");
			return (Criteria) this;
		}

		public Criteria andRegDateEqualTo(String value) {
			addCriterion("REG_DATE =", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateNotEqualTo(String value) {
			addCriterion("REG_DATE <>", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateGreaterThan(String value) {
			addCriterion("REG_DATE >", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateGreaterThanOrEqualTo(String value) {
			addCriterion("REG_DATE >=", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateLessThan(String value) {
			addCriterion("REG_DATE <", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateLessThanOrEqualTo(String value) {
			addCriterion("REG_DATE <=", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateLike(String value) {
			addCriterion("REG_DATE like", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateNotLike(String value) {
			addCriterion("REG_DATE not like", value, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateIn(List<String> values) {
			addCriterion("REG_DATE in", values, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateNotIn(List<String> values) {
			addCriterion("REG_DATE not in", values, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateBetween(String value1, String value2) {
			addCriterion("REG_DATE between", value1, value2, "regDate");
			return (Criteria) this;
		}

		public Criteria andRegDateNotBetween(String value1, String value2) {
			addCriterion("REG_DATE not between", value1, value2, "regDate");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table posts_view
	 * @mbg.generated  Mon Apr 30 22:29:24 KST 2018
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table posts_view
     *
     * @mbg.generated do_not_delete_during_merge Thu Apr 26 11:44:11 KST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}