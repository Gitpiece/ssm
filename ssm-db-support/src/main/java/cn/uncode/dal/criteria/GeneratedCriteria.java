package cn.uncode.dal.criteria;


import cn.uncode.dal.criteria.Criterion.Condition;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        protected void addCriterion(String sql) {
            if (StringUtils.isEmpty(sql)) {
                throw new RuntimeException("Sql cannot be null");
            }
            criteria.add(new Criterion(sql));
        }

        protected void addCriterion(Integer condition, String column) {
            if (condition == null || StringUtils.isEmpty(column)) {
                throw new RuntimeException("Column for condition cannot be null");
            }
            criteria.add(new Criterion(condition, column));
        }

        protected void addCriterion(Integer condition, Object value, String typeHandler, String column) {
            if (value == null || condition == null || StringUtils.isEmpty(column)) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition, value, typeHandler, column));
        }

        protected void addCriterion(Integer condition, Object value1, Object value2, String typeHandler, String column) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + column + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2, typeHandler, column));
        }

        public Criteria andColumnSql(String sql) {
            addCriterion(sql);
            return (Criteria) this;
        }

        public Criteria andColumnIsNull(String column) {
            addCriterion(Condition.IS_NULL, column);
            return (Criteria) this;
        }

        public Criteria andColumnIsNotNull(String column) {
            addCriterion(Condition.IS_NOT_NULL, column);
            return (Criteria) this;
        }

        public Criteria andColumnEqualTo(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.EQUAL, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnNotEqualTo(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.NOT_EQUAL, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnGreaterThan(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.GREATER_THAN, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnGreaterThanOrEqualTo(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.GREATER_THAN_OR_EQUAL, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnLessThan(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.LESS_THAN, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnLessThanOrEqualTo(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.LESS_THAN_OR_EQUAL, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnIn(String column, List<Object> values) {
            if (null != values && values.size() > 0) {
                addCriterion(Condition.IN, values, values.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnNotIn(String column, List<Object> values) {
            if (null != values && values.size() > 0) {
                addCriterion(Condition.NOT_IN, values, values.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnLike(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.LIKE, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnNotLike(String column, Object value) {
            if (null != value) {
                addCriterion(Condition.NOT_LIKE, value, value.getClass().getName(), column);
            }
            return (Criteria) this;
        }

        public Criteria andColumnBetween(String column, Object value1, Object value2) {
            addCriterion(Condition.BETWEEN, value1, value2, value1.getClass().getName(), column);
            return (Criteria) this;
        }

        public Criteria andColumnNotBetween(String column, Object value1, Object value2) {
            addCriterion(Condition.NOT_BETWEEN, value1, value2, value1.getClass().getName(), column);
            return (Criteria) this;
        }

    }