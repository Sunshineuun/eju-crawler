package com.qiusm.eju.crawler.government.nj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdNanJinUnitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FdNanJinUnitExample() {
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

        public Criteria andBuildingIdIsNull() {
            addCriterion("building_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIsNotNull() {
            addCriterion("building_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdEqualTo(Long value) {
            addCriterion("building_id =", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotEqualTo(Long value) {
            addCriterion("building_id <>", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThan(Long value) {
            addCriterion("building_id >", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThanOrEqualTo(Long value) {
            addCriterion("building_id >=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThan(Long value) {
            addCriterion("building_id <", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThanOrEqualTo(Long value) {
            addCriterion("building_id <=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIn(List<Long> values) {
            addCriterion("building_id in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotIn(List<Long> values) {
            addCriterion("building_id not in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdBetween(Long value1, Long value2) {
            addCriterion("building_id between", value1, value2, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotBetween(Long value1, Long value2) {
            addCriterion("building_id not between", value1, value2, "buildingId");
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

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(String value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(String value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(String value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(String value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(String value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(String value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLike(String value) {
            addCriterion("floor like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotLike(String value) {
            addCriterion("floor not like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<String> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<String> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(String value1, String value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(String value1, String value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andHouseNumIsNull() {
            addCriterion("house_num is null");
            return (Criteria) this;
        }

        public Criteria andHouseNumIsNotNull() {
            addCriterion("house_num is not null");
            return (Criteria) this;
        }

        public Criteria andHouseNumEqualTo(String value) {
            addCriterion("house_num =", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumNotEqualTo(String value) {
            addCriterion("house_num <>", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumGreaterThan(String value) {
            addCriterion("house_num >", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumGreaterThanOrEqualTo(String value) {
            addCriterion("house_num >=", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumLessThan(String value) {
            addCriterion("house_num <", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumLessThanOrEqualTo(String value) {
            addCriterion("house_num <=", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumLike(String value) {
            addCriterion("house_num like", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumNotLike(String value) {
            addCriterion("house_num not like", value, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumIn(List<String> values) {
            addCriterion("house_num in", values, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumNotIn(List<String> values) {
            addCriterion("house_num not in", values, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumBetween(String value1, String value2) {
            addCriterion("house_num between", value1, value2, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseNumNotBetween(String value1, String value2) {
            addCriterion("house_num not between", value1, value2, "houseNum");
            return (Criteria) this;
        }

        public Criteria andHouseAreaIsNull() {
            addCriterion("house_area is null");
            return (Criteria) this;
        }

        public Criteria andHouseAreaIsNotNull() {
            addCriterion("house_area is not null");
            return (Criteria) this;
        }

        public Criteria andHouseAreaEqualTo(String value) {
            addCriterion("house_area =", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaNotEqualTo(String value) {
            addCriterion("house_area <>", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaGreaterThan(String value) {
            addCriterion("house_area >", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaGreaterThanOrEqualTo(String value) {
            addCriterion("house_area >=", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaLessThan(String value) {
            addCriterion("house_area <", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaLessThanOrEqualTo(String value) {
            addCriterion("house_area <=", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaLike(String value) {
            addCriterion("house_area like", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaNotLike(String value) {
            addCriterion("house_area not like", value, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaIn(List<String> values) {
            addCriterion("house_area in", values, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaNotIn(List<String> values) {
            addCriterion("house_area not in", values, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaBetween(String value1, String value2) {
            addCriterion("house_area between", value1, value2, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseAreaNotBetween(String value1, String value2) {
            addCriterion("house_area not between", value1, value2, "houseArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaIsNull() {
            addCriterion("house_set_area is null");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaIsNotNull() {
            addCriterion("house_set_area is not null");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaEqualTo(String value) {
            addCriterion("house_set_area =", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaNotEqualTo(String value) {
            addCriterion("house_set_area <>", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaGreaterThan(String value) {
            addCriterion("house_set_area >", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaGreaterThanOrEqualTo(String value) {
            addCriterion("house_set_area >=", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaLessThan(String value) {
            addCriterion("house_set_area <", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaLessThanOrEqualTo(String value) {
            addCriterion("house_set_area <=", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaLike(String value) {
            addCriterion("house_set_area like", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaNotLike(String value) {
            addCriterion("house_set_area not like", value, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaIn(List<String> values) {
            addCriterion("house_set_area in", values, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaNotIn(List<String> values) {
            addCriterion("house_set_area not in", values, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaBetween(String value1, String value2) {
            addCriterion("house_set_area between", value1, value2, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseSetAreaNotBetween(String value1, String value2) {
            addCriterion("house_set_area not between", value1, value2, "houseSetArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaIsNull() {
            addCriterion("house_apportioned_area is null");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaIsNotNull() {
            addCriterion("house_apportioned_area is not null");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaEqualTo(String value) {
            addCriterion("house_apportioned_area =", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaNotEqualTo(String value) {
            addCriterion("house_apportioned_area <>", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaGreaterThan(String value) {
            addCriterion("house_apportioned_area >", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaGreaterThanOrEqualTo(String value) {
            addCriterion("house_apportioned_area >=", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaLessThan(String value) {
            addCriterion("house_apportioned_area <", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaLessThanOrEqualTo(String value) {
            addCriterion("house_apportioned_area <=", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaLike(String value) {
            addCriterion("house_apportioned_area like", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaNotLike(String value) {
            addCriterion("house_apportioned_area not like", value, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaIn(List<String> values) {
            addCriterion("house_apportioned_area in", values, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaNotIn(List<String> values) {
            addCriterion("house_apportioned_area not in", values, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaBetween(String value1, String value2) {
            addCriterion("house_apportioned_area between", value1, value2, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHouseApportionedAreaNotBetween(String value1, String value2) {
            addCriterion("house_apportioned_area not between", value1, value2, "houseApportionedArea");
            return (Criteria) this;
        }

        public Criteria andHousePriceIsNull() {
            addCriterion("house_price is null");
            return (Criteria) this;
        }

        public Criteria andHousePriceIsNotNull() {
            addCriterion("house_price is not null");
            return (Criteria) this;
        }

        public Criteria andHousePriceEqualTo(String value) {
            addCriterion("house_price =", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceNotEqualTo(String value) {
            addCriterion("house_price <>", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceGreaterThan(String value) {
            addCriterion("house_price >", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceGreaterThanOrEqualTo(String value) {
            addCriterion("house_price >=", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceLessThan(String value) {
            addCriterion("house_price <", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceLessThanOrEqualTo(String value) {
            addCriterion("house_price <=", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceLike(String value) {
            addCriterion("house_price like", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceNotLike(String value) {
            addCriterion("house_price not like", value, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceIn(List<String> values) {
            addCriterion("house_price in", values, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceNotIn(List<String> values) {
            addCriterion("house_price not in", values, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceBetween(String value1, String value2) {
            addCriterion("house_price between", value1, value2, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHousePriceNotBetween(String value1, String value2) {
            addCriterion("house_price not between", value1, value2, "housePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTypeIsNull() {
            addCriterion("house_type is null");
            return (Criteria) this;
        }

        public Criteria andHouseTypeIsNotNull() {
            addCriterion("house_type is not null");
            return (Criteria) this;
        }

        public Criteria andHouseTypeEqualTo(String value) {
            addCriterion("house_type =", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotEqualTo(String value) {
            addCriterion("house_type <>", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeGreaterThan(String value) {
            addCriterion("house_type >", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("house_type >=", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeLessThan(String value) {
            addCriterion("house_type <", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeLessThanOrEqualTo(String value) {
            addCriterion("house_type <=", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeLike(String value) {
            addCriterion("house_type like", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotLike(String value) {
            addCriterion("house_type not like", value, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeIn(List<String> values) {
            addCriterion("house_type in", values, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotIn(List<String> values) {
            addCriterion("house_type not in", values, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeBetween(String value1, String value2) {
            addCriterion("house_type between", value1, value2, "houseType");
            return (Criteria) this;
        }

        public Criteria andHouseTypeNotBetween(String value1, String value2) {
            addCriterion("house_type not between", value1, value2, "houseType");
            return (Criteria) this;
        }

        public Criteria andSalesStatusIsNull() {
            addCriterion("sales_status is null");
            return (Criteria) this;
        }

        public Criteria andSalesStatusIsNotNull() {
            addCriterion("sales_status is not null");
            return (Criteria) this;
        }

        public Criteria andSalesStatusEqualTo(String value) {
            addCriterion("sales_status =", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusNotEqualTo(String value) {
            addCriterion("sales_status <>", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusGreaterThan(String value) {
            addCriterion("sales_status >", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusGreaterThanOrEqualTo(String value) {
            addCriterion("sales_status >=", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusLessThan(String value) {
            addCriterion("sales_status <", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusLessThanOrEqualTo(String value) {
            addCriterion("sales_status <=", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusLike(String value) {
            addCriterion("sales_status like", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusNotLike(String value) {
            addCriterion("sales_status not like", value, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusIn(List<String> values) {
            addCriterion("sales_status in", values, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusNotIn(List<String> values) {
            addCriterion("sales_status not in", values, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusBetween(String value1, String value2) {
            addCriterion("sales_status between", value1, value2, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andSalesStatusNotBetween(String value1, String value2) {
            addCriterion("sales_status not between", value1, value2, "salesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlIsNull() {
            addCriterion("house_detail_url is null");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlIsNotNull() {
            addCriterion("house_detail_url is not null");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlEqualTo(String value) {
            addCriterion("house_detail_url =", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlNotEqualTo(String value) {
            addCriterion("house_detail_url <>", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlGreaterThan(String value) {
            addCriterion("house_detail_url >", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("house_detail_url >=", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlLessThan(String value) {
            addCriterion("house_detail_url <", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("house_detail_url <=", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlLike(String value) {
            addCriterion("house_detail_url like", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlNotLike(String value) {
            addCriterion("house_detail_url not like", value, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlIn(List<String> values) {
            addCriterion("house_detail_url in", values, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlNotIn(List<String> values) {
            addCriterion("house_detail_url not in", values, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlBetween(String value1, String value2) {
            addCriterion("house_detail_url between", value1, value2, "houseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHouseDetailUrlNotBetween(String value1, String value2) {
            addCriterion("house_detail_url not between", value1, value2, "houseDetailUrl");
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