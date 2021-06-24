package com.qiusm.eju.crawler.government.wh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdWuhanGhxkzExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FdWuhanGhxkzExample() {
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

        public Criteria andGhxkzConstructionUnitIsNull() {
            addCriterion("ghxkz_construction_unit is null");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitIsNotNull() {
            addCriterion("ghxkz_construction_unit is not null");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitEqualTo(String value) {
            addCriterion("ghxkz_construction_unit =", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitNotEqualTo(String value) {
            addCriterion("ghxkz_construction_unit <>", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitGreaterThan(String value) {
            addCriterion("ghxkz_construction_unit >", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitGreaterThanOrEqualTo(String value) {
            addCriterion("ghxkz_construction_unit >=", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitLessThan(String value) {
            addCriterion("ghxkz_construction_unit <", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitLessThanOrEqualTo(String value) {
            addCriterion("ghxkz_construction_unit <=", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitLike(String value) {
            addCriterion("ghxkz_construction_unit like", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitNotLike(String value) {
            addCriterion("ghxkz_construction_unit not like", value, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitIn(List<String> values) {
            addCriterion("ghxkz_construction_unit in", values, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitNotIn(List<String> values) {
            addCriterion("ghxkz_construction_unit not in", values, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitBetween(String value1, String value2) {
            addCriterion("ghxkz_construction_unit between", value1, value2, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzConstructionUnitNotBetween(String value1, String value2) {
            addCriterion("ghxkz_construction_unit not between", value1, value2, "ghxkzConstructionUnit");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoIsNull() {
            addCriterion("ghxkz_no is null");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoIsNotNull() {
            addCriterion("ghxkz_no is not null");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoEqualTo(String value) {
            addCriterion("ghxkz_no =", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoNotEqualTo(String value) {
            addCriterion("ghxkz_no <>", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoGreaterThan(String value) {
            addCriterion("ghxkz_no >", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoGreaterThanOrEqualTo(String value) {
            addCriterion("ghxkz_no >=", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoLessThan(String value) {
            addCriterion("ghxkz_no <", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoLessThanOrEqualTo(String value) {
            addCriterion("ghxkz_no <=", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoLike(String value) {
            addCriterion("ghxkz_no like", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoNotLike(String value) {
            addCriterion("ghxkz_no not like", value, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoIn(List<String> values) {
            addCriterion("ghxkz_no in", values, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoNotIn(List<String> values) {
            addCriterion("ghxkz_no not in", values, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoBetween(String value1, String value2) {
            addCriterion("ghxkz_no between", value1, value2, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andGhxkzNoNotBetween(String value1, String value2) {
            addCriterion("ghxkz_no not between", value1, value2, "ghxkzNo");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumIsNull() {
            addCriterion("builnding_num is null");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumIsNotNull() {
            addCriterion("builnding_num is not null");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumEqualTo(String value) {
            addCriterion("builnding_num =", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumNotEqualTo(String value) {
            addCriterion("builnding_num <>", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumGreaterThan(String value) {
            addCriterion("builnding_num >", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumGreaterThanOrEqualTo(String value) {
            addCriterion("builnding_num >=", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumLessThan(String value) {
            addCriterion("builnding_num <", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumLessThanOrEqualTo(String value) {
            addCriterion("builnding_num <=", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumLike(String value) {
            addCriterion("builnding_num like", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumNotLike(String value) {
            addCriterion("builnding_num not like", value, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumIn(List<String> values) {
            addCriterion("builnding_num in", values, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumNotIn(List<String> values) {
            addCriterion("builnding_num not in", values, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumBetween(String value1, String value2) {
            addCriterion("builnding_num between", value1, value2, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBuilndingNumNotBetween(String value1, String value2) {
            addCriterion("builnding_num not between", value1, value2, "builndingNum");
            return (Criteria) this;
        }

        public Criteria andBaseAreaIsNull() {
            addCriterion("base_area is null");
            return (Criteria) this;
        }

        public Criteria andBaseAreaIsNotNull() {
            addCriterion("base_area is not null");
            return (Criteria) this;
        }

        public Criteria andBaseAreaEqualTo(String value) {
            addCriterion("base_area =", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaNotEqualTo(String value) {
            addCriterion("base_area <>", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaGreaterThan(String value) {
            addCriterion("base_area >", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaGreaterThanOrEqualTo(String value) {
            addCriterion("base_area >=", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaLessThan(String value) {
            addCriterion("base_area <", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaLessThanOrEqualTo(String value) {
            addCriterion("base_area <=", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaLike(String value) {
            addCriterion("base_area like", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaNotLike(String value) {
            addCriterion("base_area not like", value, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaIn(List<String> values) {
            addCriterion("base_area in", values, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaNotIn(List<String> values) {
            addCriterion("base_area not in", values, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaBetween(String value1, String value2) {
            addCriterion("base_area between", value1, value2, "baseArea");
            return (Criteria) this;
        }

        public Criteria andBaseAreaNotBetween(String value1, String value2) {
            addCriterion("base_area not between", value1, value2, "baseArea");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumIsNull() {
            addCriterion("households_num is null");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumIsNotNull() {
            addCriterion("households_num is not null");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumEqualTo(String value) {
            addCriterion("households_num =", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumNotEqualTo(String value) {
            addCriterion("households_num <>", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumGreaterThan(String value) {
            addCriterion("households_num >", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumGreaterThanOrEqualTo(String value) {
            addCriterion("households_num >=", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumLessThan(String value) {
            addCriterion("households_num <", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumLessThanOrEqualTo(String value) {
            addCriterion("households_num <=", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumLike(String value) {
            addCriterion("households_num like", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumNotLike(String value) {
            addCriterion("households_num not like", value, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumIn(List<String> values) {
            addCriterion("households_num in", values, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumNotIn(List<String> values) {
            addCriterion("households_num not in", values, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumBetween(String value1, String value2) {
            addCriterion("households_num between", value1, value2, "householdsNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdsNumNotBetween(String value1, String value2) {
            addCriterion("households_num not between", value1, value2, "householdsNum");
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