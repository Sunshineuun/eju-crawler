package com.qiusm.eju.crawler.task.entity;

import java.util.ArrayList;
import java.util.List;

public class CrawlerTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CrawlerTaskExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonIsNull() {
            addCriterion("task_param_json is null");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonIsNotNull() {
            addCriterion("task_param_json is not null");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonEqualTo(String value) {
            addCriterion("task_param_json =", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonNotEqualTo(String value) {
            addCriterion("task_param_json <>", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonGreaterThan(String value) {
            addCriterion("task_param_json >", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonGreaterThanOrEqualTo(String value) {
            addCriterion("task_param_json >=", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonLessThan(String value) {
            addCriterion("task_param_json <", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonLessThanOrEqualTo(String value) {
            addCriterion("task_param_json <=", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonLike(String value) {
            addCriterion("task_param_json like", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonNotLike(String value) {
            addCriterion("task_param_json not like", value, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonIn(List<String> values) {
            addCriterion("task_param_json in", values, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonNotIn(List<String> values) {
            addCriterion("task_param_json not in", values, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonBetween(String value1, String value2) {
            addCriterion("task_param_json between", value1, value2, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andTaskParamJsonNotBetween(String value1, String value2) {
            addCriterion("task_param_json not between", value1, value2, "taskParamJson");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeIsNull() {
            addCriterion("handler_type is null");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeIsNotNull() {
            addCriterion("handler_type is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeEqualTo(String value) {
            addCriterion("handler_type =", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeNotEqualTo(String value) {
            addCriterion("handler_type <>", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeGreaterThan(String value) {
            addCriterion("handler_type >", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("handler_type >=", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeLessThan(String value) {
            addCriterion("handler_type <", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeLessThanOrEqualTo(String value) {
            addCriterion("handler_type <=", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeLike(String value) {
            addCriterion("handler_type like", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeNotLike(String value) {
            addCriterion("handler_type not like", value, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeIn(List<String> values) {
            addCriterion("handler_type in", values, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeNotIn(List<String> values) {
            addCriterion("handler_type not in", values, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeBetween(String value1, String value2) {
            addCriterion("handler_type between", value1, value2, "handlerType");
            return (Criteria) this;
        }

        public Criteria andHandlerTypeNotBetween(String value1, String value2) {
            addCriterion("handler_type not between", value1, value2, "handlerType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

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
}