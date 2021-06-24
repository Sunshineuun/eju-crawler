package com.qiusm.eju.crawler.government.nj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdNanJinPreSaleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FdNanJinPreSaleExample() {
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

        public Criteria andPreSalePermitIdIsNull() {
            addCriterion("pre_sale_permit_id is null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdIsNotNull() {
            addCriterion("pre_sale_permit_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdEqualTo(String value) {
            addCriterion("pre_sale_permit_id =", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdNotEqualTo(String value) {
            addCriterion("pre_sale_permit_id <>", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdGreaterThan(String value) {
            addCriterion("pre_sale_permit_id >", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_permit_id >=", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdLessThan(String value) {
            addCriterion("pre_sale_permit_id <", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_permit_id <=", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdLike(String value) {
            addCriterion("pre_sale_permit_id like", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdNotLike(String value) {
            addCriterion("pre_sale_permit_id not like", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdIn(List<String> values) {
            addCriterion("pre_sale_permit_id in", values, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdNotIn(List<String> values) {
            addCriterion("pre_sale_permit_id not in", values, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdBetween(String value1, String value2) {
            addCriterion("pre_sale_permit_id between", value1, value2, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdNotBetween(String value1, String value2) {
            addCriterion("pre_sale_permit_id not between", value1, value2, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeIsNull() {
            addCriterion("pre_sale_permit_code is null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeIsNotNull() {
            addCriterion("pre_sale_permit_code is not null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeEqualTo(String value) {
            addCriterion("pre_sale_permit_code =", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeNotEqualTo(String value) {
            addCriterion("pre_sale_permit_code <>", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeGreaterThan(String value) {
            addCriterion("pre_sale_permit_code >", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_permit_code >=", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeLessThan(String value) {
            addCriterion("pre_sale_permit_code <", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_permit_code <=", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeLike(String value) {
            addCriterion("pre_sale_permit_code like", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeNotLike(String value) {
            addCriterion("pre_sale_permit_code not like", value, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeIn(List<String> values) {
            addCriterion("pre_sale_permit_code in", values, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeNotIn(List<String> values) {
            addCriterion("pre_sale_permit_code not in", values, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeBetween(String value1, String value2) {
            addCriterion("pre_sale_permit_code between", value1, value2, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitCodeNotBetween(String value1, String value2) {
            addCriterion("pre_sale_permit_code not between", value1, value2, "preSalePermitCode");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
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

        public Criteria andProjectAddressIsNull() {
            addCriterion("project_address is null");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIsNotNull() {
            addCriterion("project_address is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAddressEqualTo(String value) {
            addCriterion("project_address =", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotEqualTo(String value) {
            addCriterion("project_address <>", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressGreaterThan(String value) {
            addCriterion("project_address >", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressGreaterThanOrEqualTo(String value) {
            addCriterion("project_address >=", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLessThan(String value) {
            addCriterion("project_address <", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLessThanOrEqualTo(String value) {
            addCriterion("project_address <=", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLike(String value) {
            addCriterion("project_address like", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotLike(String value) {
            addCriterion("project_address not like", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIn(List<String> values) {
            addCriterion("project_address in", values, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotIn(List<String> values) {
            addCriterion("project_address not in", values, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressBetween(String value1, String value2) {
            addCriterion("project_address between", value1, value2, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotBetween(String value1, String value2) {
            addCriterion("project_address not between", value1, value2, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitIsNull() {
            addCriterion("land_use_right_permit is null");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitIsNotNull() {
            addCriterion("land_use_right_permit is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitEqualTo(String value) {
            addCriterion("land_use_right_permit =", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitNotEqualTo(String value) {
            addCriterion("land_use_right_permit <>", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitGreaterThan(String value) {
            addCriterion("land_use_right_permit >", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitGreaterThanOrEqualTo(String value) {
            addCriterion("land_use_right_permit >=", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitLessThan(String value) {
            addCriterion("land_use_right_permit <", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitLessThanOrEqualTo(String value) {
            addCriterion("land_use_right_permit <=", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitLike(String value) {
            addCriterion("land_use_right_permit like", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitNotLike(String value) {
            addCriterion("land_use_right_permit not like", value, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitIn(List<String> values) {
            addCriterion("land_use_right_permit in", values, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitNotIn(List<String> values) {
            addCriterion("land_use_right_permit not in", values, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitBetween(String value1, String value2) {
            addCriterion("land_use_right_permit between", value1, value2, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUseRightPermitNotBetween(String value1, String value2) {
            addCriterion("land_use_right_permit not between", value1, value2, "landUseRightPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodIsNull() {
            addCriterion("land_use_period is null");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodIsNotNull() {
            addCriterion("land_use_period is not null");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodEqualTo(String value) {
            addCriterion("land_use_period =", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodNotEqualTo(String value) {
            addCriterion("land_use_period <>", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodGreaterThan(String value) {
            addCriterion("land_use_period >", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodGreaterThanOrEqualTo(String value) {
            addCriterion("land_use_period >=", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodLessThan(String value) {
            addCriterion("land_use_period <", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodLessThanOrEqualTo(String value) {
            addCriterion("land_use_period <=", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodLike(String value) {
            addCriterion("land_use_period like", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodNotLike(String value) {
            addCriterion("land_use_period not like", value, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodIn(List<String> values) {
            addCriterion("land_use_period in", values, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodNotIn(List<String> values) {
            addCriterion("land_use_period not in", values, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodBetween(String value1, String value2) {
            addCriterion("land_use_period between", value1, value2, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andLandUsePeriodNotBetween(String value1, String value2) {
            addCriterion("land_use_period not between", value1, value2, "landUsePeriod");
            return (Criteria) this;
        }

        public Criteria andHouseUseIsNull() {
            addCriterion("house_use is null");
            return (Criteria) this;
        }

        public Criteria andHouseUseIsNotNull() {
            addCriterion("house_use is not null");
            return (Criteria) this;
        }

        public Criteria andHouseUseEqualTo(String value) {
            addCriterion("house_use =", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseNotEqualTo(String value) {
            addCriterion("house_use <>", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseGreaterThan(String value) {
            addCriterion("house_use >", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseGreaterThanOrEqualTo(String value) {
            addCriterion("house_use >=", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseLessThan(String value) {
            addCriterion("house_use <", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseLessThanOrEqualTo(String value) {
            addCriterion("house_use <=", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseLike(String value) {
            addCriterion("house_use like", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseNotLike(String value) {
            addCriterion("house_use not like", value, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseIn(List<String> values) {
            addCriterion("house_use in", values, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseNotIn(List<String> values) {
            addCriterion("house_use not in", values, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseBetween(String value1, String value2) {
            addCriterion("house_use between", value1, value2, "houseUse");
            return (Criteria) this;
        }

        public Criteria andHouseUseNotBetween(String value1, String value2) {
            addCriterion("house_use not between", value1, value2, "houseUse");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryIsNull() {
            addCriterion("sales_category is null");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryIsNotNull() {
            addCriterion("sales_category is not null");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryEqualTo(String value) {
            addCriterion("sales_category =", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryNotEqualTo(String value) {
            addCriterion("sales_category <>", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryGreaterThan(String value) {
            addCriterion("sales_category >", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("sales_category >=", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryLessThan(String value) {
            addCriterion("sales_category <", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryLessThanOrEqualTo(String value) {
            addCriterion("sales_category <=", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryLike(String value) {
            addCriterion("sales_category like", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryNotLike(String value) {
            addCriterion("sales_category not like", value, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryIn(List<String> values) {
            addCriterion("sales_category in", values, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryNotIn(List<String> values) {
            addCriterion("sales_category not in", values, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryBetween(String value1, String value2) {
            addCriterion("sales_category between", value1, value2, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesCategoryNotBetween(String value1, String value2) {
            addCriterion("sales_category not between", value1, value2, "salesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesUnitIsNull() {
            addCriterion("sales_unit is null");
            return (Criteria) this;
        }

        public Criteria andSalesUnitIsNotNull() {
            addCriterion("sales_unit is not null");
            return (Criteria) this;
        }

        public Criteria andSalesUnitEqualTo(String value) {
            addCriterion("sales_unit =", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotEqualTo(String value) {
            addCriterion("sales_unit <>", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitGreaterThan(String value) {
            addCriterion("sales_unit >", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitGreaterThanOrEqualTo(String value) {
            addCriterion("sales_unit >=", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitLessThan(String value) {
            addCriterion("sales_unit <", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitLessThanOrEqualTo(String value) {
            addCriterion("sales_unit <=", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitLike(String value) {
            addCriterion("sales_unit like", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotLike(String value) {
            addCriterion("sales_unit not like", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitIn(List<String> values) {
            addCriterion("sales_unit in", values, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotIn(List<String> values) {
            addCriterion("sales_unit not in", values, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitBetween(String value1, String value2) {
            addCriterion("sales_unit between", value1, value2, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotBetween(String value1, String value2) {
            addCriterion("sales_unit not between", value1, value2, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesMethodIsNull() {
            addCriterion("sales_method is null");
            return (Criteria) this;
        }

        public Criteria andSalesMethodIsNotNull() {
            addCriterion("sales_method is not null");
            return (Criteria) this;
        }

        public Criteria andSalesMethodEqualTo(String value) {
            addCriterion("sales_method =", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodNotEqualTo(String value) {
            addCriterion("sales_method <>", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodGreaterThan(String value) {
            addCriterion("sales_method >", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodGreaterThanOrEqualTo(String value) {
            addCriterion("sales_method >=", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodLessThan(String value) {
            addCriterion("sales_method <", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodLessThanOrEqualTo(String value) {
            addCriterion("sales_method <=", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodLike(String value) {
            addCriterion("sales_method like", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodNotLike(String value) {
            addCriterion("sales_method not like", value, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodIn(List<String> values) {
            addCriterion("sales_method in", values, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodNotIn(List<String> values) {
            addCriterion("sales_method not in", values, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodBetween(String value1, String value2) {
            addCriterion("sales_method between", value1, value2, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andSalesMethodNotBetween(String value1, String value2) {
            addCriterion("sales_method not between", value1, value2, "salesMethod");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlIsNull() {
            addCriterion("pre_sale_license_img_url is null");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlIsNotNull() {
            addCriterion("pre_sale_license_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlEqualTo(String value) {
            addCriterion("pre_sale_license_img_url =", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlNotEqualTo(String value) {
            addCriterion("pre_sale_license_img_url <>", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlGreaterThan(String value) {
            addCriterion("pre_sale_license_img_url >", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_license_img_url >=", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlLessThan(String value) {
            addCriterion("pre_sale_license_img_url <", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_license_img_url <=", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlLike(String value) {
            addCriterion("pre_sale_license_img_url like", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlNotLike(String value) {
            addCriterion("pre_sale_license_img_url not like", value, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlIn(List<String> values) {
            addCriterion("pre_sale_license_img_url in", values, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlNotIn(List<String> values) {
            addCriterion("pre_sale_license_img_url not in", values, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlBetween(String value1, String value2) {
            addCriterion("pre_sale_license_img_url between", value1, value2, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleLicenseImgUrlNotBetween(String value1, String value2) {
            addCriterion("pre_sale_license_img_url not between", value1, value2, "preSaleLicenseImgUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlIsNull() {
            addCriterion("pre_sale_reg_detail_url is null");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlIsNotNull() {
            addCriterion("pre_sale_reg_detail_url is not null");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlEqualTo(String value) {
            addCriterion("pre_sale_reg_detail_url =", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlNotEqualTo(String value) {
            addCriterion("pre_sale_reg_detail_url <>", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlGreaterThan(String value) {
            addCriterion("pre_sale_reg_detail_url >", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_reg_detail_url >=", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlLessThan(String value) {
            addCriterion("pre_sale_reg_detail_url <", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_reg_detail_url <=", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlLike(String value) {
            addCriterion("pre_sale_reg_detail_url like", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlNotLike(String value) {
            addCriterion("pre_sale_reg_detail_url not like", value, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlIn(List<String> values) {
            addCriterion("pre_sale_reg_detail_url in", values, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlNotIn(List<String> values) {
            addCriterion("pre_sale_reg_detail_url not in", values, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlBetween(String value1, String value2) {
            addCriterion("pre_sale_reg_detail_url between", value1, value2, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSaleRegDetailUrlNotBetween(String value1, String value2) {
            addCriterion("pre_sale_reg_detail_url not between", value1, value2, "preSaleRegDetailUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlIsNull() {
            addCriterion("pre_sale_plan_url is null");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlIsNotNull() {
            addCriterion("pre_sale_plan_url is not null");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlEqualTo(String value) {
            addCriterion("pre_sale_plan_url =", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlNotEqualTo(String value) {
            addCriterion("pre_sale_plan_url <>", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlGreaterThan(String value) {
            addCriterion("pre_sale_plan_url >", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_plan_url >=", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlLessThan(String value) {
            addCriterion("pre_sale_plan_url <", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_plan_url <=", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlLike(String value) {
            addCriterion("pre_sale_plan_url like", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlNotLike(String value) {
            addCriterion("pre_sale_plan_url not like", value, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlIn(List<String> values) {
            addCriterion("pre_sale_plan_url in", values, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlNotIn(List<String> values) {
            addCriterion("pre_sale_plan_url not in", values, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlBetween(String value1, String value2) {
            addCriterion("pre_sale_plan_url between", value1, value2, "preSalePlanUrl");
            return (Criteria) this;
        }

        public Criteria andPreSalePlanUrlNotBetween(String value1, String value2) {
            addCriterion("pre_sale_plan_url not between", value1, value2, "preSalePlanUrl");
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