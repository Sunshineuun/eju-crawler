package com.qiusm.eju.crawler.government.wh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdWuhanUnitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FdWuhanUnitExample() {
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

        public Criteria andBuildingNameIsNull() {
            addCriterion("building_name is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNotNull() {
            addCriterion("building_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameEqualTo(String value) {
            addCriterion("building_name =", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotEqualTo(String value) {
            addCriterion("building_name <>", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThan(String value) {
            addCriterion("building_name >", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThanOrEqualTo(String value) {
            addCriterion("building_name >=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThan(String value) {
            addCriterion("building_name <", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThanOrEqualTo(String value) {
            addCriterion("building_name <=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLike(String value) {
            addCriterion("building_name like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotLike(String value) {
            addCriterion("building_name not like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIn(List<String> values) {
            addCriterion("building_name in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotIn(List<String> values) {
            addCriterion("building_name not in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameBetween(String value1, String value2) {
            addCriterion("building_name between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotBetween(String value1, String value2) {
            addCriterion("building_name not between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNull() {
            addCriterion("unit_id is null");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNotNull() {
            addCriterion("unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIdEqualTo(String value) {
            addCriterion("unit_id =", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotEqualTo(String value) {
            addCriterion("unit_id <>", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThan(String value) {
            addCriterion("unit_id >", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThanOrEqualTo(String value) {
            addCriterion("unit_id >=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThan(String value) {
            addCriterion("unit_id <", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThanOrEqualTo(String value) {
            addCriterion("unit_id <=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLike(String value) {
            addCriterion("unit_id like", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotLike(String value) {
            addCriterion("unit_id not like", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdIn(List<String> values) {
            addCriterion("unit_id in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotIn(List<String> values) {
            addCriterion("unit_id not in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdBetween(String value1, String value2) {
            addCriterion("unit_id between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotBetween(String value1, String value2) {
            addCriterion("unit_id not between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andNominalFloorIsNull() {
            addCriterion("nominal_floor is null");
            return (Criteria) this;
        }

        public Criteria andNominalFloorIsNotNull() {
            addCriterion("nominal_floor is not null");
            return (Criteria) this;
        }

        public Criteria andNominalFloorEqualTo(String value) {
            addCriterion("nominal_floor =", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorNotEqualTo(String value) {
            addCriterion("nominal_floor <>", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorGreaterThan(String value) {
            addCriterion("nominal_floor >", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorGreaterThanOrEqualTo(String value) {
            addCriterion("nominal_floor >=", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorLessThan(String value) {
            addCriterion("nominal_floor <", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorLessThanOrEqualTo(String value) {
            addCriterion("nominal_floor <=", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorLike(String value) {
            addCriterion("nominal_floor like", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorNotLike(String value) {
            addCriterion("nominal_floor not like", value, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorIn(List<String> values) {
            addCriterion("nominal_floor in", values, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorNotIn(List<String> values) {
            addCriterion("nominal_floor not in", values, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorBetween(String value1, String value2) {
            addCriterion("nominal_floor between", value1, value2, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andNominalFloorNotBetween(String value1, String value2) {
            addCriterion("nominal_floor not between", value1, value2, "nominalFloor");
            return (Criteria) this;
        }

        public Criteria andRoomNoIsNull() {
            addCriterion("room_no is null");
            return (Criteria) this;
        }

        public Criteria andRoomNoIsNotNull() {
            addCriterion("room_no is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNoEqualTo(String value) {
            addCriterion("room_no =", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotEqualTo(String value) {
            addCriterion("room_no <>", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoGreaterThan(String value) {
            addCriterion("room_no >", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoGreaterThanOrEqualTo(String value) {
            addCriterion("room_no >=", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoLessThan(String value) {
            addCriterion("room_no <", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoLessThanOrEqualTo(String value) {
            addCriterion("room_no <=", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoLike(String value) {
            addCriterion("room_no like", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotLike(String value) {
            addCriterion("room_no not like", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoIn(List<String> values) {
            addCriterion("room_no in", values, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotIn(List<String> values) {
            addCriterion("room_no not in", values, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoBetween(String value1, String value2) {
            addCriterion("room_no between", value1, value2, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotBetween(String value1, String value2) {
            addCriterion("room_no not between", value1, value2, "roomNo");
            return (Criteria) this;
        }

        public Criteria andHouseAddressIsNull() {
            addCriterion("house_address is null");
            return (Criteria) this;
        }

        public Criteria andHouseAddressIsNotNull() {
            addCriterion("house_address is not null");
            return (Criteria) this;
        }

        public Criteria andHouseAddressEqualTo(String value) {
            addCriterion("house_address =", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressNotEqualTo(String value) {
            addCriterion("house_address <>", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressGreaterThan(String value) {
            addCriterion("house_address >", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressGreaterThanOrEqualTo(String value) {
            addCriterion("house_address >=", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressLessThan(String value) {
            addCriterion("house_address <", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressLessThanOrEqualTo(String value) {
            addCriterion("house_address <=", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressLike(String value) {
            addCriterion("house_address like", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressNotLike(String value) {
            addCriterion("house_address not like", value, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressIn(List<String> values) {
            addCriterion("house_address in", values, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressNotIn(List<String> values) {
            addCriterion("house_address not in", values, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressBetween(String value1, String value2) {
            addCriterion("house_address between", value1, value2, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andHouseAddressNotBetween(String value1, String value2) {
            addCriterion("house_address not between", value1, value2, "houseAddress");
            return (Criteria) this;
        }

        public Criteria andPresellNoIsNull() {
            addCriterion("presell_no is null");
            return (Criteria) this;
        }

        public Criteria andPresellNoIsNotNull() {
            addCriterion("presell_no is not null");
            return (Criteria) this;
        }

        public Criteria andPresellNoEqualTo(String value) {
            addCriterion("presell_no =", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoNotEqualTo(String value) {
            addCriterion("presell_no <>", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoGreaterThan(String value) {
            addCriterion("presell_no >", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoGreaterThanOrEqualTo(String value) {
            addCriterion("presell_no >=", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoLessThan(String value) {
            addCriterion("presell_no <", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoLessThanOrEqualTo(String value) {
            addCriterion("presell_no <=", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoLike(String value) {
            addCriterion("presell_no like", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoNotLike(String value) {
            addCriterion("presell_no not like", value, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoIn(List<String> values) {
            addCriterion("presell_no in", values, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoNotIn(List<String> values) {
            addCriterion("presell_no not in", values, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoBetween(String value1, String value2) {
            addCriterion("presell_no between", value1, value2, "presellNo");
            return (Criteria) this;
        }

        public Criteria andPresellNoNotBetween(String value1, String value2) {
            addCriterion("presell_no not between", value1, value2, "presellNo");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaIsNull() {
            addCriterion("measured_total_area is null");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaIsNotNull() {
            addCriterion("measured_total_area is not null");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaEqualTo(String value) {
            addCriterion("measured_total_area =", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaNotEqualTo(String value) {
            addCriterion("measured_total_area <>", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaGreaterThan(String value) {
            addCriterion("measured_total_area >", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaGreaterThanOrEqualTo(String value) {
            addCriterion("measured_total_area >=", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaLessThan(String value) {
            addCriterion("measured_total_area <", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaLessThanOrEqualTo(String value) {
            addCriterion("measured_total_area <=", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaLike(String value) {
            addCriterion("measured_total_area like", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaNotLike(String value) {
            addCriterion("measured_total_area not like", value, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaIn(List<String> values) {
            addCriterion("measured_total_area in", values, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaNotIn(List<String> values) {
            addCriterion("measured_total_area not in", values, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaBetween(String value1, String value2) {
            addCriterion("measured_total_area between", value1, value2, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andMeasuredTotalAreaNotBetween(String value1, String value2) {
            addCriterion("measured_total_area not between", value1, value2, "measuredTotalArea");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceIsNull() {
            addCriterion("pre_building_avg_price is null");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceIsNotNull() {
            addCriterion("pre_building_avg_price is not null");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceEqualTo(String value) {
            addCriterion("pre_building_avg_price =", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceNotEqualTo(String value) {
            addCriterion("pre_building_avg_price <>", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceGreaterThan(String value) {
            addCriterion("pre_building_avg_price >", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceGreaterThanOrEqualTo(String value) {
            addCriterion("pre_building_avg_price >=", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceLessThan(String value) {
            addCriterion("pre_building_avg_price <", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceLessThanOrEqualTo(String value) {
            addCriterion("pre_building_avg_price <=", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceLike(String value) {
            addCriterion("pre_building_avg_price like", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceNotLike(String value) {
            addCriterion("pre_building_avg_price not like", value, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceIn(List<String> values) {
            addCriterion("pre_building_avg_price in", values, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceNotIn(List<String> values) {
            addCriterion("pre_building_avg_price not in", values, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceBetween(String value1, String value2) {
            addCriterion("pre_building_avg_price between", value1, value2, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andPreBuildingAvgPriceNotBetween(String value1, String value2) {
            addCriterion("pre_building_avg_price not between", value1, value2, "preBuildingAvgPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceIsNull() {
            addCriterion("house_total_price is null");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceIsNotNull() {
            addCriterion("house_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceEqualTo(String value) {
            addCriterion("house_total_price =", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceNotEqualTo(String value) {
            addCriterion("house_total_price <>", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceGreaterThan(String value) {
            addCriterion("house_total_price >", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceGreaterThanOrEqualTo(String value) {
            addCriterion("house_total_price >=", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceLessThan(String value) {
            addCriterion("house_total_price <", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceLessThanOrEqualTo(String value) {
            addCriterion("house_total_price <=", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceLike(String value) {
            addCriterion("house_total_price like", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceNotLike(String value) {
            addCriterion("house_total_price not like", value, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceIn(List<String> values) {
            addCriterion("house_total_price in", values, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceNotIn(List<String> values) {
            addCriterion("house_total_price not in", values, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceBetween(String value1, String value2) {
            addCriterion("house_total_price between", value1, value2, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalPriceNotBetween(String value1, String value2) {
            addCriterion("house_total_price not between", value1, value2, "houseTotalPrice");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusIsNull() {
            addCriterion("house_sales_status is null");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusIsNotNull() {
            addCriterion("house_sales_status is not null");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusEqualTo(String value) {
            addCriterion("house_sales_status =", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusNotEqualTo(String value) {
            addCriterion("house_sales_status <>", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusGreaterThan(String value) {
            addCriterion("house_sales_status >", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusGreaterThanOrEqualTo(String value) {
            addCriterion("house_sales_status >=", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusLessThan(String value) {
            addCriterion("house_sales_status <", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusLessThanOrEqualTo(String value) {
            addCriterion("house_sales_status <=", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusLike(String value) {
            addCriterion("house_sales_status like", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusNotLike(String value) {
            addCriterion("house_sales_status not like", value, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusIn(List<String> values) {
            addCriterion("house_sales_status in", values, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusNotIn(List<String> values) {
            addCriterion("house_sales_status not in", values, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusBetween(String value1, String value2) {
            addCriterion("house_sales_status between", value1, value2, "houseSalesStatus");
            return (Criteria) this;
        }

        public Criteria andHouseSalesStatusNotBetween(String value1, String value2) {
            addCriterion("house_sales_status not between", value1, value2, "houseSalesStatus");
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

        public Criteria andDetailsUrlIsNull() {
            addCriterion("details_url is null");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlIsNotNull() {
            addCriterion("details_url is not null");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlEqualTo(String value) {
            addCriterion("details_url =", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlNotEqualTo(String value) {
            addCriterion("details_url <>", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlGreaterThan(String value) {
            addCriterion("details_url >", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("details_url >=", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlLessThan(String value) {
            addCriterion("details_url <", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlLessThanOrEqualTo(String value) {
            addCriterion("details_url <=", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlLike(String value) {
            addCriterion("details_url like", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlNotLike(String value) {
            addCriterion("details_url not like", value, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlIn(List<String> values) {
            addCriterion("details_url in", values, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlNotIn(List<String> values) {
            addCriterion("details_url not in", values, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlBetween(String value1, String value2) {
            addCriterion("details_url between", value1, value2, "detailsUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsUrlNotBetween(String value1, String value2) {
            addCriterion("details_url not between", value1, value2, "detailsUrl");
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