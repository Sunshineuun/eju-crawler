package com.qiusm.eju.crawler.competitor.beike.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BkDealUrlHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BkDealUrlHistoryExample() {
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

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andClassHandlerIsNull() {
            addCriterion("CLASS_HANDLER is null");
            return (Criteria) this;
        }

        public Criteria andClassHandlerIsNotNull() {
            addCriterion("CLASS_HANDLER is not null");
            return (Criteria) this;
        }

        public Criteria andClassHandlerEqualTo(String value) {
            addCriterion("CLASS_HANDLER =", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerNotEqualTo(String value) {
            addCriterion("CLASS_HANDLER <>", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerGreaterThan(String value) {
            addCriterion("CLASS_HANDLER >", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_HANDLER >=", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerLessThan(String value) {
            addCriterion("CLASS_HANDLER <", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerLessThanOrEqualTo(String value) {
            addCriterion("CLASS_HANDLER <=", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerLike(String value) {
            addCriterion("CLASS_HANDLER like", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerNotLike(String value) {
            addCriterion("CLASS_HANDLER not like", value, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerIn(List<String> values) {
            addCriterion("CLASS_HANDLER in", values, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerNotIn(List<String> values) {
            addCriterion("CLASS_HANDLER not in", values, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerBetween(String value1, String value2) {
            addCriterion("CLASS_HANDLER between", value1, value2, "classHandler");
            return (Criteria) this;
        }

        public Criteria andClassHandlerNotBetween(String value1, String value2) {
            addCriterion("CLASS_HANDLER not between", value1, value2, "classHandler");
            return (Criteria) this;
        }

        public Criteria andUrlBase64IsNull() {
            addCriterion("URL_BASE64 is null");
            return (Criteria) this;
        }

        public Criteria andUrlBase64IsNotNull() {
            addCriterion("URL_BASE64 is not null");
            return (Criteria) this;
        }

        public Criteria andUrlBase64EqualTo(String value) {
            addCriterion("URL_BASE64 =", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64NotEqualTo(String value) {
            addCriterion("URL_BASE64 <>", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64GreaterThan(String value) {
            addCriterion("URL_BASE64 >", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64GreaterThanOrEqualTo(String value) {
            addCriterion("URL_BASE64 >=", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64LessThan(String value) {
            addCriterion("URL_BASE64 <", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64LessThanOrEqualTo(String value) {
            addCriterion("URL_BASE64 <=", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64Like(String value) {
            addCriterion("URL_BASE64 like", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64NotLike(String value) {
            addCriterion("URL_BASE64 not like", value, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64In(List<String> values) {
            addCriterion("URL_BASE64 in", values, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64NotIn(List<String> values) {
            addCriterion("URL_BASE64 not in", values, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64Between(String value1, String value2) {
            addCriterion("URL_BASE64 between", value1, value2, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andUrlBase64NotBetween(String value1, String value2) {
            addCriterion("URL_BASE64 not between", value1, value2, "urlBase64");
            return (Criteria) this;
        }

        public Criteria andIsSuccessIsNull() {
            addCriterion("IS_SUCCESS is null");
            return (Criteria) this;
        }

        public Criteria andIsSuccessIsNotNull() {
            addCriterion("IS_SUCCESS is not null");
            return (Criteria) this;
        }

        public Criteria andIsSuccessEqualTo(Integer value) {
            addCriterion("IS_SUCCESS =", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessNotEqualTo(Integer value) {
            addCriterion("IS_SUCCESS <>", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessGreaterThan(Integer value) {
            addCriterion("IS_SUCCESS >", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_SUCCESS >=", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessLessThan(Integer value) {
            addCriterion("IS_SUCCESS <", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessLessThanOrEqualTo(Integer value) {
            addCriterion("IS_SUCCESS <=", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessIn(List<Integer> values) {
            addCriterion("IS_SUCCESS in", values, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessNotIn(List<Integer> values) {
            addCriterion("IS_SUCCESS not in", values, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessBetween(Integer value1, Integer value2) {
            addCriterion("IS_SUCCESS between", value1, value2, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_SUCCESS not between", value1, value2, "isSuccess");
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