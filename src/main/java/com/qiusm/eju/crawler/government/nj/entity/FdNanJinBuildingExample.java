package com.qiusm.eju.crawler.government.nj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdNanJinBuildingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FdNanJinBuildingExample() {
        oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Long value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Long value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Long value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Long value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Long value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Long> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Long> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Long value1, Long value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Long value1, Long value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andBuildingNumIsNull() {
            addCriterion("building_num is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumIsNotNull() {
            addCriterion("building_num is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumEqualTo(String value) {
            addCriterion("building_num =", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumNotEqualTo(String value) {
            addCriterion("building_num <>", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumGreaterThan(String value) {
            addCriterion("building_num >", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumGreaterThanOrEqualTo(String value) {
            addCriterion("building_num >=", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumLessThan(String value) {
            addCriterion("building_num <", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumLessThanOrEqualTo(String value) {
            addCriterion("building_num <=", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumLike(String value) {
            addCriterion("building_num like", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumNotLike(String value) {
            addCriterion("building_num not like", value, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumIn(List<String> values) {
            addCriterion("building_num in", values, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumNotIn(List<String> values) {
            addCriterion("building_num not in", values, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumBetween(String value1, String value2) {
            addCriterion("building_num between", value1, value2, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andBuildingNumNotBetween(String value1, String value2) {
            addCriterion("building_num not between", value1, value2, "buildingNum");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNull() {
            addCriterion("open_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("open_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(String value) {
            addCriterion("open_time =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(String value) {
            addCriterion("open_time <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(String value) {
            addCriterion("open_time >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(String value) {
            addCriterion("open_time >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(String value) {
            addCriterion("open_time <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(String value) {
            addCriterion("open_time <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLike(String value) {
            addCriterion("open_time like", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotLike(String value) {
            addCriterion("open_time not like", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<String> values) {
            addCriterion("open_time in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<String> values) {
            addCriterion("open_time not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(String value1, String value2) {
            addCriterion("open_time between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(String value1, String value2) {
            addCriterion("open_time not between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNull() {
            addCriterion("total_number is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNotNull() {
            addCriterion("total_number is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberEqualTo(String value) {
            addCriterion("total_number =", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotEqualTo(String value) {
            addCriterion("total_number <>", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThan(String value) {
            addCriterion("total_number >", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThanOrEqualTo(String value) {
            addCriterion("total_number >=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThan(String value) {
            addCriterion("total_number <", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThanOrEqualTo(String value) {
            addCriterion("total_number <=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLike(String value) {
            addCriterion("total_number like", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotLike(String value) {
            addCriterion("total_number not like", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIn(List<String> values) {
            addCriterion("total_number in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotIn(List<String> values) {
            addCriterion("total_number not in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberBetween(String value1, String value2) {
            addCriterion("total_number between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotBetween(String value1, String value2) {
            addCriterion("total_number not between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumIsNull() {
            addCriterion("unsold_total_num is null");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumIsNotNull() {
            addCriterion("unsold_total_num is not null");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumEqualTo(String value) {
            addCriterion("unsold_total_num =", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumNotEqualTo(String value) {
            addCriterion("unsold_total_num <>", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumGreaterThan(String value) {
            addCriterion("unsold_total_num >", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumGreaterThanOrEqualTo(String value) {
            addCriterion("unsold_total_num >=", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumLessThan(String value) {
            addCriterion("unsold_total_num <", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumLessThanOrEqualTo(String value) {
            addCriterion("unsold_total_num <=", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumLike(String value) {
            addCriterion("unsold_total_num like", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumNotLike(String value) {
            addCriterion("unsold_total_num not like", value, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumIn(List<String> values) {
            addCriterion("unsold_total_num in", values, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumNotIn(List<String> values) {
            addCriterion("unsold_total_num not in", values, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumBetween(String value1, String value2) {
            addCriterion("unsold_total_num between", value1, value2, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalNumNotBetween(String value1, String value2) {
            addCriterion("unsold_total_num not between", value1, value2, "unsoldTotalNum");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeIsNull() {
            addCriterion("today_subscribe is null");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeIsNotNull() {
            addCriterion("today_subscribe is not null");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeEqualTo(String value) {
            addCriterion("today_subscribe =", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeNotEqualTo(String value) {
            addCriterion("today_subscribe <>", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeGreaterThan(String value) {
            addCriterion("today_subscribe >", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeGreaterThanOrEqualTo(String value) {
            addCriterion("today_subscribe >=", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeLessThan(String value) {
            addCriterion("today_subscribe <", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeLessThanOrEqualTo(String value) {
            addCriterion("today_subscribe <=", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeLike(String value) {
            addCriterion("today_subscribe like", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeNotLike(String value) {
            addCriterion("today_subscribe not like", value, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeIn(List<String> values) {
            addCriterion("today_subscribe in", values, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeNotIn(List<String> values) {
            addCriterion("today_subscribe not in", values, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeBetween(String value1, String value2) {
            addCriterion("today_subscribe between", value1, value2, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodaySubscribeNotBetween(String value1, String value2) {
            addCriterion("today_subscribe not between", value1, value2, "todaySubscribe");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionIsNull() {
            addCriterion("today_transaction is null");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionIsNotNull() {
            addCriterion("today_transaction is not null");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionEqualTo(String value) {
            addCriterion("today_transaction =", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionNotEqualTo(String value) {
            addCriterion("today_transaction <>", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionGreaterThan(String value) {
            addCriterion("today_transaction >", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionGreaterThanOrEqualTo(String value) {
            addCriterion("today_transaction >=", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionLessThan(String value) {
            addCriterion("today_transaction <", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionLessThanOrEqualTo(String value) {
            addCriterion("today_transaction <=", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionLike(String value) {
            addCriterion("today_transaction like", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionNotLike(String value) {
            addCriterion("today_transaction not like", value, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionIn(List<String> values) {
            addCriterion("today_transaction in", values, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionNotIn(List<String> values) {
            addCriterion("today_transaction not in", values, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionBetween(String value1, String value2) {
            addCriterion("today_transaction between", value1, value2, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andTodayTransactionNotBetween(String value1, String value2) {
            addCriterion("today_transaction not between", value1, value2, "todayTransaction");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumIsNull() {
            addCriterion("subscribe_total_num is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumIsNotNull() {
            addCriterion("subscribe_total_num is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumEqualTo(String value) {
            addCriterion("subscribe_total_num =", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumNotEqualTo(String value) {
            addCriterion("subscribe_total_num <>", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumGreaterThan(String value) {
            addCriterion("subscribe_total_num >", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumGreaterThanOrEqualTo(String value) {
            addCriterion("subscribe_total_num >=", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumLessThan(String value) {
            addCriterion("subscribe_total_num <", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumLessThanOrEqualTo(String value) {
            addCriterion("subscribe_total_num <=", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumLike(String value) {
            addCriterion("subscribe_total_num like", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumNotLike(String value) {
            addCriterion("subscribe_total_num not like", value, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumIn(List<String> values) {
            addCriterion("subscribe_total_num in", values, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumNotIn(List<String> values) {
            addCriterion("subscribe_total_num not in", values, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumBetween(String value1, String value2) {
            addCriterion("subscribe_total_num between", value1, value2, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalNumNotBetween(String value1, String value2) {
            addCriterion("subscribe_total_num not between", value1, value2, "subscribeTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumIsNull() {
            addCriterion("transaction_total_num is null");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumIsNotNull() {
            addCriterion("transaction_total_num is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumEqualTo(String value) {
            addCriterion("transaction_total_num =", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumNotEqualTo(String value) {
            addCriterion("transaction_total_num <>", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumGreaterThan(String value) {
            addCriterion("transaction_total_num >", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_total_num >=", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumLessThan(String value) {
            addCriterion("transaction_total_num <", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumLessThanOrEqualTo(String value) {
            addCriterion("transaction_total_num <=", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumLike(String value) {
            addCriterion("transaction_total_num like", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumNotLike(String value) {
            addCriterion("transaction_total_num not like", value, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumIn(List<String> values) {
            addCriterion("transaction_total_num in", values, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumNotIn(List<String> values) {
            addCriterion("transaction_total_num not in", values, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumBetween(String value1, String value2) {
            addCriterion("transaction_total_num between", value1, value2, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalNumNotBetween(String value1, String value2) {
            addCriterion("transaction_total_num not between", value1, value2, "transactionTotalNum");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaIsNull() {
            addCriterion("transaction_total_area is null");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaIsNotNull() {
            addCriterion("transaction_total_area is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaEqualTo(String value) {
            addCriterion("transaction_total_area =", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaNotEqualTo(String value) {
            addCriterion("transaction_total_area <>", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaGreaterThan(String value) {
            addCriterion("transaction_total_area >", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_total_area >=", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaLessThan(String value) {
            addCriterion("transaction_total_area <", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaLessThanOrEqualTo(String value) {
            addCriterion("transaction_total_area <=", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaLike(String value) {
            addCriterion("transaction_total_area like", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaNotLike(String value) {
            addCriterion("transaction_total_area not like", value, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaIn(List<String> values) {
            addCriterion("transaction_total_area in", values, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaNotIn(List<String> values) {
            addCriterion("transaction_total_area not in", values, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaBetween(String value1, String value2) {
            addCriterion("transaction_total_area between", value1, value2, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andTransactionTotalAreaNotBetween(String value1, String value2) {
            addCriterion("transaction_total_area not between", value1, value2, "transactionTotalArea");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNull() {
            addCriterion("average_price is null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNotNull() {
            addCriterion("average_price is not null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceEqualTo(String value) {
            addCriterion("average_price =", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotEqualTo(String value) {
            addCriterion("average_price <>", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThan(String value) {
            addCriterion("average_price >", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("average_price >=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThan(String value) {
            addCriterion("average_price <", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("average_price <=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLike(String value) {
            addCriterion("average_price like", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotLike(String value) {
            addCriterion("average_price not like", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIn(List<String> values) {
            addCriterion("average_price in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotIn(List<String> values) {
            addCriterion("average_price not in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceBetween(String value1, String value2) {
            addCriterion("average_price between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotBetween(String value1, String value2) {
            addCriterion("average_price not between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioIsNull() {
            addCriterion("turnover_ratio is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioIsNotNull() {
            addCriterion("turnover_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioEqualTo(String value) {
            addCriterion("turnover_ratio =", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioNotEqualTo(String value) {
            addCriterion("turnover_ratio <>", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioGreaterThan(String value) {
            addCriterion("turnover_ratio >", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioGreaterThanOrEqualTo(String value) {
            addCriterion("turnover_ratio >=", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioLessThan(String value) {
            addCriterion("turnover_ratio <", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioLessThanOrEqualTo(String value) {
            addCriterion("turnover_ratio <=", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioLike(String value) {
            addCriterion("turnover_ratio like", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioNotLike(String value) {
            addCriterion("turnover_ratio not like", value, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioIn(List<String> values) {
            addCriterion("turnover_ratio in", values, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioNotIn(List<String> values) {
            addCriterion("turnover_ratio not in", values, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioBetween(String value1, String value2) {
            addCriterion("turnover_ratio between", value1, value2, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andTurnoverRatioNotBetween(String value1, String value2) {
            addCriterion("turnover_ratio not between", value1, value2, "turnoverRatio");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlIsNull() {
            addCriterion("building_detail_url is null");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlIsNotNull() {
            addCriterion("building_detail_url is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlEqualTo(String value) {
            addCriterion("building_detail_url =", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlNotEqualTo(String value) {
            addCriterion("building_detail_url <>", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlGreaterThan(String value) {
            addCriterion("building_detail_url >", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("building_detail_url >=", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlLessThan(String value) {
            addCriterion("building_detail_url <", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("building_detail_url <=", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlLike(String value) {
            addCriterion("building_detail_url like", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlNotLike(String value) {
            addCriterion("building_detail_url not like", value, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlIn(List<String> values) {
            addCriterion("building_detail_url in", values, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlNotIn(List<String> values) {
            addCriterion("building_detail_url not in", values, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlBetween(String value1, String value2) {
            addCriterion("building_detail_url between", value1, value2, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andBuildingDetailUrlNotBetween(String value1, String value2) {
            addCriterion("building_detail_url not between", value1, value2, "buildingDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlIsNull() {
            addCriterion("price_list_img_url is null");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlIsNotNull() {
            addCriterion("price_list_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlEqualTo(String value) {
            addCriterion("price_list_img_url =", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlNotEqualTo(String value) {
            addCriterion("price_list_img_url <>", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlGreaterThan(String value) {
            addCriterion("price_list_img_url >", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("price_list_img_url >=", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlLessThan(String value) {
            addCriterion("price_list_img_url <", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlLessThanOrEqualTo(String value) {
            addCriterion("price_list_img_url <=", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlLike(String value) {
            addCriterion("price_list_img_url like", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlNotLike(String value) {
            addCriterion("price_list_img_url not like", value, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlIn(List<String> values) {
            addCriterion("price_list_img_url in", values, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlNotIn(List<String> values) {
            addCriterion("price_list_img_url not in", values, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlBetween(String value1, String value2) {
            addCriterion("price_list_img_url between", value1, value2, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andPriceListImgUrlNotBetween(String value1, String value2) {
            addCriterion("price_list_img_url not between", value1, value2, "priceListImgUrl");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
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