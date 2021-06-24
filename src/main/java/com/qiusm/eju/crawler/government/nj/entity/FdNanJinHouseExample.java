package com.qiusm.eju.crawler.government.nj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdNanJinHouseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FdNanJinHouseExample() {
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

        public Criteria andDevelopmentEnterpriseIsNull() {
            addCriterion("development_enterprise is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseIsNotNull() {
            addCriterion("development_enterprise is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseEqualTo(String value) {
            addCriterion("development_enterprise =", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseNotEqualTo(String value) {
            addCriterion("development_enterprise <>", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseGreaterThan(String value) {
            addCriterion("development_enterprise >", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseGreaterThanOrEqualTo(String value) {
            addCriterion("development_enterprise >=", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseLessThan(String value) {
            addCriterion("development_enterprise <", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseLessThanOrEqualTo(String value) {
            addCriterion("development_enterprise <=", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseLike(String value) {
            addCriterion("development_enterprise like", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseNotLike(String value) {
            addCriterion("development_enterprise not like", value, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseIn(List<String> values) {
            addCriterion("development_enterprise in", values, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseNotIn(List<String> values) {
            addCriterion("development_enterprise not in", values, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseBetween(String value1, String value2) {
            addCriterion("development_enterprise between", value1, value2, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseNotBetween(String value1, String value2) {
            addCriterion("development_enterprise not between", value1, value2, "developmentEnterprise");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlIsNull() {
            addCriterion("development_enterprise_detail_url is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlIsNotNull() {
            addCriterion("development_enterprise_detail_url is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlEqualTo(String value) {
            addCriterion("development_enterprise_detail_url =", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlNotEqualTo(String value) {
            addCriterion("development_enterprise_detail_url <>", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlGreaterThan(String value) {
            addCriterion("development_enterprise_detail_url >", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("development_enterprise_detail_url >=", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlLessThan(String value) {
            addCriterion("development_enterprise_detail_url <", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("development_enterprise_detail_url <=", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlLike(String value) {
            addCriterion("development_enterprise_detail_url like", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlNotLike(String value) {
            addCriterion("development_enterprise_detail_url not like", value, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlIn(List<String> values) {
            addCriterion("development_enterprise_detail_url in", values, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlNotIn(List<String> values) {
            addCriterion("development_enterprise_detail_url not in", values, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlBetween(String value1, String value2) {
            addCriterion("development_enterprise_detail_url between", value1, value2, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andDevelopmentEnterpriseDetailUrlNotBetween(String value1, String value2) {
            addCriterion("development_enterprise_detail_url not between", value1, value2, "developmentEnterpriseDetailUrl");
            return (Criteria) this;
        }

        public Criteria andSalesAgentIsNull() {
            addCriterion("sales_agent is null");
            return (Criteria) this;
        }

        public Criteria andSalesAgentIsNotNull() {
            addCriterion("sales_agent is not null");
            return (Criteria) this;
        }

        public Criteria andSalesAgentEqualTo(String value) {
            addCriterion("sales_agent =", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentNotEqualTo(String value) {
            addCriterion("sales_agent <>", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentGreaterThan(String value) {
            addCriterion("sales_agent >", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentGreaterThanOrEqualTo(String value) {
            addCriterion("sales_agent >=", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentLessThan(String value) {
            addCriterion("sales_agent <", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentLessThanOrEqualTo(String value) {
            addCriterion("sales_agent <=", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentLike(String value) {
            addCriterion("sales_agent like", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentNotLike(String value) {
            addCriterion("sales_agent not like", value, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentIn(List<String> values) {
            addCriterion("sales_agent in", values, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentNotIn(List<String> values) {
            addCriterion("sales_agent not in", values, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentBetween(String value1, String value2) {
            addCriterion("sales_agent between", value1, value2, "salesAgent");
            return (Criteria) this;
        }

        public Criteria andSalesAgentNotBetween(String value1, String value2) {
            addCriterion("sales_agent not between", value1, value2, "salesAgent");
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

        public Criteria andLandUsePlanningPermitIsNull() {
            addCriterion("land_use_planning_permit is null");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitIsNotNull() {
            addCriterion("land_use_planning_permit is not null");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitEqualTo(String value) {
            addCriterion("land_use_planning_permit =", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitNotEqualTo(String value) {
            addCriterion("land_use_planning_permit <>", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitGreaterThan(String value) {
            addCriterion("land_use_planning_permit >", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitGreaterThanOrEqualTo(String value) {
            addCriterion("land_use_planning_permit >=", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitLessThan(String value) {
            addCriterion("land_use_planning_permit <", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitLessThanOrEqualTo(String value) {
            addCriterion("land_use_planning_permit <=", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitLike(String value) {
            addCriterion("land_use_planning_permit like", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitNotLike(String value) {
            addCriterion("land_use_planning_permit not like", value, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitIn(List<String> values) {
            addCriterion("land_use_planning_permit in", values, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitNotIn(List<String> values) {
            addCriterion("land_use_planning_permit not in", values, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitBetween(String value1, String value2) {
            addCriterion("land_use_planning_permit between", value1, value2, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andLandUsePlanningPermitNotBetween(String value1, String value2) {
            addCriterion("land_use_planning_permit not between", value1, value2, "landUsePlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitIsNull() {
            addCriterion("engineering_planning_permit is null");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitIsNotNull() {
            addCriterion("engineering_planning_permit is not null");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitEqualTo(String value) {
            addCriterion("engineering_planning_permit =", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitNotEqualTo(String value) {
            addCriterion("engineering_planning_permit <>", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitGreaterThan(String value) {
            addCriterion("engineering_planning_permit >", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitGreaterThanOrEqualTo(String value) {
            addCriterion("engineering_planning_permit >=", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitLessThan(String value) {
            addCriterion("engineering_planning_permit <", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitLessThanOrEqualTo(String value) {
            addCriterion("engineering_planning_permit <=", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitLike(String value) {
            addCriterion("engineering_planning_permit like", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitNotLike(String value) {
            addCriterion("engineering_planning_permit not like", value, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitIn(List<String> values) {
            addCriterion("engineering_planning_permit in", values, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitNotIn(List<String> values) {
            addCriterion("engineering_planning_permit not in", values, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitBetween(String value1, String value2) {
            addCriterion("engineering_planning_permit between", value1, value2, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andEngineeringPlanningPermitNotBetween(String value1, String value2) {
            addCriterion("engineering_planning_permit not between", value1, value2, "engineeringPlanningPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitIsNull() {
            addCriterion("construction_permit is null");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitIsNotNull() {
            addCriterion("construction_permit is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitEqualTo(String value) {
            addCriterion("construction_permit =", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitNotEqualTo(String value) {
            addCriterion("construction_permit <>", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitGreaterThan(String value) {
            addCriterion("construction_permit >", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitGreaterThanOrEqualTo(String value) {
            addCriterion("construction_permit >=", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitLessThan(String value) {
            addCriterion("construction_permit <", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitLessThanOrEqualTo(String value) {
            addCriterion("construction_permit <=", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitLike(String value) {
            addCriterion("construction_permit like", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitNotLike(String value) {
            addCriterion("construction_permit not like", value, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitIn(List<String> values) {
            addCriterion("construction_permit in", values, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitNotIn(List<String> values) {
            addCriterion("construction_permit not in", values, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitBetween(String value1, String value2) {
            addCriterion("construction_permit between", value1, value2, "constructionPermit");
            return (Criteria) this;
        }

        public Criteria andConstructionPermitNotBetween(String value1, String value2) {
            addCriterion("construction_permit not between", value1, value2, "constructionPermit");
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

        public Criteria andCommercialMonthAveragePriceIsNull() {
            addCriterion("commercial_month_average_price is null");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceIsNotNull() {
            addCriterion("commercial_month_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceEqualTo(String value) {
            addCriterion("commercial_month_average_price =", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceNotEqualTo(String value) {
            addCriterion("commercial_month_average_price <>", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceGreaterThan(String value) {
            addCriterion("commercial_month_average_price >", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("commercial_month_average_price >=", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceLessThan(String value) {
            addCriterion("commercial_month_average_price <", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("commercial_month_average_price <=", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceLike(String value) {
            addCriterion("commercial_month_average_price like", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceNotLike(String value) {
            addCriterion("commercial_month_average_price not like", value, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceIn(List<String> values) {
            addCriterion("commercial_month_average_price in", values, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceNotIn(List<String> values) {
            addCriterion("commercial_month_average_price not in", values, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceBetween(String value1, String value2) {
            addCriterion("commercial_month_average_price between", value1, value2, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialMonthAveragePriceNotBetween(String value1, String value2) {
            addCriterion("commercial_month_average_price not between", value1, value2, "commercialMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andTotalAreaIsNull() {
            addCriterion("total_area is null");
            return (Criteria) this;
        }

        public Criteria andTotalAreaIsNotNull() {
            addCriterion("total_area is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAreaEqualTo(String value) {
            addCriterion("total_area =", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaNotEqualTo(String value) {
            addCriterion("total_area <>", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaGreaterThan(String value) {
            addCriterion("total_area >", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaGreaterThanOrEqualTo(String value) {
            addCriterion("total_area >=", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaLessThan(String value) {
            addCriterion("total_area <", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaLessThanOrEqualTo(String value) {
            addCriterion("total_area <=", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaLike(String value) {
            addCriterion("total_area like", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaNotLike(String value) {
            addCriterion("total_area not like", value, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaIn(List<String> values) {
            addCriterion("total_area in", values, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaNotIn(List<String> values) {
            addCriterion("total_area not in", values, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaBetween(String value1, String value2) {
            addCriterion("total_area between", value1, value2, "totalArea");
            return (Criteria) this;
        }

        public Criteria andTotalAreaNotBetween(String value1, String value2) {
            addCriterion("total_area not between", value1, value2, "totalArea");
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

        public Criteria andHouseTotalAveragePriceIsNull() {
            addCriterion("house_total_average_price is null");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceIsNotNull() {
            addCriterion("house_total_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceEqualTo(String value) {
            addCriterion("house_total_average_price =", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceNotEqualTo(String value) {
            addCriterion("house_total_average_price <>", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceGreaterThan(String value) {
            addCriterion("house_total_average_price >", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("house_total_average_price >=", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceLessThan(String value) {
            addCriterion("house_total_average_price <", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("house_total_average_price <=", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceLike(String value) {
            addCriterion("house_total_average_price like", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceNotLike(String value) {
            addCriterion("house_total_average_price not like", value, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceIn(List<String> values) {
            addCriterion("house_total_average_price in", values, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceNotIn(List<String> values) {
            addCriterion("house_total_average_price not in", values, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceBetween(String value1, String value2) {
            addCriterion("house_total_average_price between", value1, value2, "houseTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseTotalAveragePriceNotBetween(String value1, String value2) {
            addCriterion("house_total_average_price not between", value1, value2, "houseTotalAveragePrice");
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

        public Criteria andUnsoldTotalAreaIsNull() {
            addCriterion("unsold_total_area is null");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaIsNotNull() {
            addCriterion("unsold_total_area is not null");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaEqualTo(String value) {
            addCriterion("unsold_total_area =", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaNotEqualTo(String value) {
            addCriterion("unsold_total_area <>", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaGreaterThan(String value) {
            addCriterion("unsold_total_area >", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaGreaterThanOrEqualTo(String value) {
            addCriterion("unsold_total_area >=", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaLessThan(String value) {
            addCriterion("unsold_total_area <", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaLessThanOrEqualTo(String value) {
            addCriterion("unsold_total_area <=", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaLike(String value) {
            addCriterion("unsold_total_area like", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaNotLike(String value) {
            addCriterion("unsold_total_area not like", value, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaIn(List<String> values) {
            addCriterion("unsold_total_area in", values, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaNotIn(List<String> values) {
            addCriterion("unsold_total_area not in", values, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaBetween(String value1, String value2) {
            addCriterion("unsold_total_area between", value1, value2, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andUnsoldTotalAreaNotBetween(String value1, String value2) {
            addCriterion("unsold_total_area not between", value1, value2, "unsoldTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaIsNull() {
            addCriterion("subscribe_total_area is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaIsNotNull() {
            addCriterion("subscribe_total_area is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaEqualTo(String value) {
            addCriterion("subscribe_total_area =", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaNotEqualTo(String value) {
            addCriterion("subscribe_total_area <>", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaGreaterThan(String value) {
            addCriterion("subscribe_total_area >", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaGreaterThanOrEqualTo(String value) {
            addCriterion("subscribe_total_area >=", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaLessThan(String value) {
            addCriterion("subscribe_total_area <", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaLessThanOrEqualTo(String value) {
            addCriterion("subscribe_total_area <=", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaLike(String value) {
            addCriterion("subscribe_total_area like", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaNotLike(String value) {
            addCriterion("subscribe_total_area not like", value, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaIn(List<String> values) {
            addCriterion("subscribe_total_area in", values, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaNotIn(List<String> values) {
            addCriterion("subscribe_total_area not in", values, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaBetween(String value1, String value2) {
            addCriterion("subscribe_total_area between", value1, value2, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andSubscribeTotalAreaNotBetween(String value1, String value2) {
            addCriterion("subscribe_total_area not between", value1, value2, "subscribeTotalArea");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceIsNull() {
            addCriterion("commercial_total_average_price is null");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceIsNotNull() {
            addCriterion("commercial_total_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceEqualTo(String value) {
            addCriterion("commercial_total_average_price =", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceNotEqualTo(String value) {
            addCriterion("commercial_total_average_price <>", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceGreaterThan(String value) {
            addCriterion("commercial_total_average_price >", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("commercial_total_average_price >=", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceLessThan(String value) {
            addCriterion("commercial_total_average_price <", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("commercial_total_average_price <=", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceLike(String value) {
            addCriterion("commercial_total_average_price like", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceNotLike(String value) {
            addCriterion("commercial_total_average_price not like", value, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceIn(List<String> values) {
            addCriterion("commercial_total_average_price in", values, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceNotIn(List<String> values) {
            addCriterion("commercial_total_average_price not in", values, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceBetween(String value1, String value2) {
            addCriterion("commercial_total_average_price between", value1, value2, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andCommercialTotalAveragePriceNotBetween(String value1, String value2) {
            addCriterion("commercial_total_average_price not between", value1, value2, "commercialTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumIsNull() {
            addCriterion("unsold_garage_num is null");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumIsNotNull() {
            addCriterion("unsold_garage_num is not null");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumEqualTo(String value) {
            addCriterion("unsold_garage_num =", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumNotEqualTo(String value) {
            addCriterion("unsold_garage_num <>", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumGreaterThan(String value) {
            addCriterion("unsold_garage_num >", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumGreaterThanOrEqualTo(String value) {
            addCriterion("unsold_garage_num >=", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumLessThan(String value) {
            addCriterion("unsold_garage_num <", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumLessThanOrEqualTo(String value) {
            addCriterion("unsold_garage_num <=", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumLike(String value) {
            addCriterion("unsold_garage_num like", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumNotLike(String value) {
            addCriterion("unsold_garage_num not like", value, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumIn(List<String> values) {
            addCriterion("unsold_garage_num in", values, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumNotIn(List<String> values) {
            addCriterion("unsold_garage_num not in", values, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumBetween(String value1, String value2) {
            addCriterion("unsold_garage_num between", value1, value2, "unsoldGarageNum");
            return (Criteria) this;
        }

        public Criteria andUnsoldGarageNumNotBetween(String value1, String value2) {
            addCriterion("unsold_garage_num not between", value1, value2, "unsoldGarageNum");
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

        public Criteria andHouseMonthAveragePriceIsNull() {
            addCriterion("house_month_average_price is null");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceIsNotNull() {
            addCriterion("house_month_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceEqualTo(String value) {
            addCriterion("house_month_average_price =", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceNotEqualTo(String value) {
            addCriterion("house_month_average_price <>", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceGreaterThan(String value) {
            addCriterion("house_month_average_price >", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("house_month_average_price >=", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceLessThan(String value) {
            addCriterion("house_month_average_price <", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("house_month_average_price <=", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceLike(String value) {
            addCriterion("house_month_average_price like", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceNotLike(String value) {
            addCriterion("house_month_average_price not like", value, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceIn(List<String> values) {
            addCriterion("house_month_average_price in", values, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceNotIn(List<String> values) {
            addCriterion("house_month_average_price not in", values, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceBetween(String value1, String value2) {
            addCriterion("house_month_average_price between", value1, value2, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andHouseMonthAveragePriceNotBetween(String value1, String value2) {
            addCriterion("house_month_average_price not between", value1, value2, "houseMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceIsNull() {
            addCriterion("project_total_average_price is null");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceIsNotNull() {
            addCriterion("project_total_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceEqualTo(String value) {
            addCriterion("project_total_average_price =", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceNotEqualTo(String value) {
            addCriterion("project_total_average_price <>", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceGreaterThan(String value) {
            addCriterion("project_total_average_price >", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("project_total_average_price >=", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceLessThan(String value) {
            addCriterion("project_total_average_price <", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("project_total_average_price <=", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceLike(String value) {
            addCriterion("project_total_average_price like", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceNotLike(String value) {
            addCriterion("project_total_average_price not like", value, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceIn(List<String> values) {
            addCriterion("project_total_average_price in", values, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceNotIn(List<String> values) {
            addCriterion("project_total_average_price not in", values, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceBetween(String value1, String value2) {
            addCriterion("project_total_average_price between", value1, value2, "projectTotalAveragePrice");
            return (Criteria) this;
        }

        public Criteria andProjectTotalAveragePriceNotBetween(String value1, String value2) {
            addCriterion("project_total_average_price not between", value1, value2, "projectTotalAveragePrice");
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

        public Criteria andOfficeBuildingMonthAveragePriceIsNull() {
            addCriterion("office_building_month_average_price is null");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceIsNotNull() {
            addCriterion("office_building_month_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceEqualTo(String value) {
            addCriterion("office_building_month_average_price =", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceNotEqualTo(String value) {
            addCriterion("office_building_month_average_price <>", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceGreaterThan(String value) {
            addCriterion("office_building_month_average_price >", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("office_building_month_average_price >=", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceLessThan(String value) {
            addCriterion("office_building_month_average_price <", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("office_building_month_average_price <=", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceLike(String value) {
            addCriterion("office_building_month_average_price like", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceNotLike(String value) {
            addCriterion("office_building_month_average_price not like", value, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceIn(List<String> values) {
            addCriterion("office_building_month_average_price in", values, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceNotIn(List<String> values) {
            addCriterion("office_building_month_average_price not in", values, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceBetween(String value1, String value2) {
            addCriterion("office_building_month_average_price between", value1, value2, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andOfficeBuildingMonthAveragePriceNotBetween(String value1, String value2) {
            addCriterion("office_building_month_average_price not between", value1, value2, "officeBuildingMonthAveragePrice");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyIsNull() {
            addCriterion("property_management_company is null");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyIsNotNull() {
            addCriterion("property_management_company is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyEqualTo(String value) {
            addCriterion("property_management_company =", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyNotEqualTo(String value) {
            addCriterion("property_management_company <>", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyGreaterThan(String value) {
            addCriterion("property_management_company >", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("property_management_company >=", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyLessThan(String value) {
            addCriterion("property_management_company <", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyLessThanOrEqualTo(String value) {
            addCriterion("property_management_company <=", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyLike(String value) {
            addCriterion("property_management_company like", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyNotLike(String value) {
            addCriterion("property_management_company not like", value, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyIn(List<String> values) {
            addCriterion("property_management_company in", values, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyNotIn(List<String> values) {
            addCriterion("property_management_company not in", values, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyBetween(String value1, String value2) {
            addCriterion("property_management_company between", value1, value2, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyManagementCompanyNotBetween(String value1, String value2) {
            addCriterion("property_management_company not between", value1, value2, "propertyManagementCompany");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitIsNull() {
            addCriterion("construction_unit is null");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitIsNotNull() {
            addCriterion("construction_unit is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitEqualTo(String value) {
            addCriterion("construction_unit =", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotEqualTo(String value) {
            addCriterion("construction_unit <>", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitGreaterThan(String value) {
            addCriterion("construction_unit >", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitGreaterThanOrEqualTo(String value) {
            addCriterion("construction_unit >=", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitLessThan(String value) {
            addCriterion("construction_unit <", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitLessThanOrEqualTo(String value) {
            addCriterion("construction_unit <=", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitLike(String value) {
            addCriterion("construction_unit like", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotLike(String value) {
            addCriterion("construction_unit not like", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitIn(List<String> values) {
            addCriterion("construction_unit in", values, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotIn(List<String> values) {
            addCriterion("construction_unit not in", values, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitBetween(String value1, String value2) {
            addCriterion("construction_unit between", value1, value2, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotBetween(String value1, String value2) {
            addCriterion("construction_unit not between", value1, value2, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitIsNull() {
            addCriterion("architectural_design_unit is null");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitIsNotNull() {
            addCriterion("architectural_design_unit is not null");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitEqualTo(String value) {
            addCriterion("architectural_design_unit =", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitNotEqualTo(String value) {
            addCriterion("architectural_design_unit <>", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitGreaterThan(String value) {
            addCriterion("architectural_design_unit >", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitGreaterThanOrEqualTo(String value) {
            addCriterion("architectural_design_unit >=", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitLessThan(String value) {
            addCriterion("architectural_design_unit <", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitLessThanOrEqualTo(String value) {
            addCriterion("architectural_design_unit <=", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitLike(String value) {
            addCriterion("architectural_design_unit like", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitNotLike(String value) {
            addCriterion("architectural_design_unit not like", value, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitIn(List<String> values) {
            addCriterion("architectural_design_unit in", values, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitNotIn(List<String> values) {
            addCriterion("architectural_design_unit not in", values, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitBetween(String value1, String value2) {
            addCriterion("architectural_design_unit between", value1, value2, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andArchitecturalDesignUnitNotBetween(String value1, String value2) {
            addCriterion("architectural_design_unit not between", value1, value2, "architecturalDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitIsNull() {
            addCriterion("environmental_art_design_unit is null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitIsNotNull() {
            addCriterion("environmental_art_design_unit is not null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitEqualTo(String value) {
            addCriterion("environmental_art_design_unit =", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitNotEqualTo(String value) {
            addCriterion("environmental_art_design_unit <>", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitGreaterThan(String value) {
            addCriterion("environmental_art_design_unit >", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitGreaterThanOrEqualTo(String value) {
            addCriterion("environmental_art_design_unit >=", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitLessThan(String value) {
            addCriterion("environmental_art_design_unit <", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitLessThanOrEqualTo(String value) {
            addCriterion("environmental_art_design_unit <=", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitLike(String value) {
            addCriterion("environmental_art_design_unit like", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitNotLike(String value) {
            addCriterion("environmental_art_design_unit not like", value, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitIn(List<String> values) {
            addCriterion("environmental_art_design_unit in", values, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitNotIn(List<String> values) {
            addCriterion("environmental_art_design_unit not in", values, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitBetween(String value1, String value2) {
            addCriterion("environmental_art_design_unit between", value1, value2, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalArtDesignUnitNotBetween(String value1, String value2) {
            addCriterion("environmental_art_design_unit not between", value1, value2, "environmentalArtDesignUnit");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionIsNull() {
            addCriterion("project_description is null");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionIsNotNull() {
            addCriterion("project_description is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionEqualTo(String value) {
            addCriterion("project_description =", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionNotEqualTo(String value) {
            addCriterion("project_description <>", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionGreaterThan(String value) {
            addCriterion("project_description >", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("project_description >=", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionLessThan(String value) {
            addCriterion("project_description <", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionLessThanOrEqualTo(String value) {
            addCriterion("project_description <=", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionLike(String value) {
            addCriterion("project_description like", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionNotLike(String value) {
            addCriterion("project_description not like", value, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionIn(List<String> values) {
            addCriterion("project_description in", values, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionNotIn(List<String> values) {
            addCriterion("project_description not in", values, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionBetween(String value1, String value2) {
            addCriterion("project_description between", value1, value2, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andProjectDescriptionNotBetween(String value1, String value2) {
            addCriterion("project_description not between", value1, value2, "projectDescription");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIsNull() {
            addCriterion("corporate_name is null");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIsNotNull() {
            addCriterion("corporate_name is not null");
            return (Criteria) this;
        }

        public Criteria andCorporateNameEqualTo(String value) {
            addCriterion("corporate_name =", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotEqualTo(String value) {
            addCriterion("corporate_name <>", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameGreaterThan(String value) {
            addCriterion("corporate_name >", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_name >=", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLessThan(String value) {
            addCriterion("corporate_name <", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLessThanOrEqualTo(String value) {
            addCriterion("corporate_name <=", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLike(String value) {
            addCriterion("corporate_name like", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotLike(String value) {
            addCriterion("corporate_name not like", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIn(List<String> values) {
            addCriterion("corporate_name in", values, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotIn(List<String> values) {
            addCriterion("corporate_name not in", values, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameBetween(String value1, String value2) {
            addCriterion("corporate_name between", value1, value2, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotBetween(String value1, String value2) {
            addCriterion("corporate_name not between", value1, value2, "corporateName");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNull() {
            addCriterion("legal_representative is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNotNull() {
            addCriterion("legal_representative is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeEqualTo(String value) {
            addCriterion("legal_representative =", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotEqualTo(String value) {
            addCriterion("legal_representative <>", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThan(String value) {
            addCriterion("legal_representative >", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("legal_representative >=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThan(String value) {
            addCriterion("legal_representative <", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("legal_representative <=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLike(String value) {
            addCriterion("legal_representative like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotLike(String value) {
            addCriterion("legal_representative not like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIn(List<String> values) {
            addCriterion("legal_representative in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotIn(List<String> values) {
            addCriterion("legal_representative not in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeBetween(String value1, String value2) {
            addCriterion("legal_representative between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("legal_representative not between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeIsNull() {
            addCriterion("legal_person_code is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeIsNotNull() {
            addCriterion("legal_person_code is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeEqualTo(String value) {
            addCriterion("legal_person_code =", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeNotEqualTo(String value) {
            addCriterion("legal_person_code <>", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeGreaterThan(String value) {
            addCriterion("legal_person_code >", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_code >=", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeLessThan(String value) {
            addCriterion("legal_person_code <", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeLessThanOrEqualTo(String value) {
            addCriterion("legal_person_code <=", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeLike(String value) {
            addCriterion("legal_person_code like", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeNotLike(String value) {
            addCriterion("legal_person_code not like", value, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeIn(List<String> values) {
            addCriterion("legal_person_code in", values, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeNotIn(List<String> values) {
            addCriterion("legal_person_code not in", values, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeBetween(String value1, String value2) {
            addCriterion("legal_person_code between", value1, value2, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andLegalPersonCodeNotBetween(String value1, String value2) {
            addCriterion("legal_person_code not between", value1, value2, "legalPersonCode");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerIsNull() {
            addCriterion("general_manager is null");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerIsNotNull() {
            addCriterion("general_manager is not null");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerEqualTo(String value) {
            addCriterion("general_manager =", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerNotEqualTo(String value) {
            addCriterion("general_manager <>", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerGreaterThan(String value) {
            addCriterion("general_manager >", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerGreaterThanOrEqualTo(String value) {
            addCriterion("general_manager >=", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerLessThan(String value) {
            addCriterion("general_manager <", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerLessThanOrEqualTo(String value) {
            addCriterion("general_manager <=", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerLike(String value) {
            addCriterion("general_manager like", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerNotLike(String value) {
            addCriterion("general_manager not like", value, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerIn(List<String> values) {
            addCriterion("general_manager in", values, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerNotIn(List<String> values) {
            addCriterion("general_manager not in", values, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerBetween(String value1, String value2) {
            addCriterion("general_manager between", value1, value2, "generalManager");
            return (Criteria) this;
        }

        public Criteria andGeneralManagerNotBetween(String value1, String value2) {
            addCriterion("general_manager not between", value1, value2, "generalManager");
            return (Criteria) this;
        }

        public Criteria andContactNumberIsNull() {
            addCriterion("contact_number is null");
            return (Criteria) this;
        }

        public Criteria andContactNumberIsNotNull() {
            addCriterion("contact_number is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumberEqualTo(String value) {
            addCriterion("contact_number =", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotEqualTo(String value) {
            addCriterion("contact_number <>", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThan(String value) {
            addCriterion("contact_number >", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThanOrEqualTo(String value) {
            addCriterion("contact_number >=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThan(String value) {
            addCriterion("contact_number <", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThanOrEqualTo(String value) {
            addCriterion("contact_number <=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLike(String value) {
            addCriterion("contact_number like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotLike(String value) {
            addCriterion("contact_number not like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberIn(List<String> values) {
            addCriterion("contact_number in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotIn(List<String> values) {
            addCriterion("contact_number not in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberBetween(String value1, String value2) {
            addCriterion("contact_number between", value1, value2, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotBetween(String value1, String value2) {
            addCriterion("contact_number not between", value1, value2, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumIsNull() {
            addCriterion("business_license_num is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumIsNotNull() {
            addCriterion("business_license_num is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumEqualTo(String value) {
            addCriterion("business_license_num =", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumNotEqualTo(String value) {
            addCriterion("business_license_num <>", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumGreaterThan(String value) {
            addCriterion("business_license_num >", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumGreaterThanOrEqualTo(String value) {
            addCriterion("business_license_num >=", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumLessThan(String value) {
            addCriterion("business_license_num <", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumLessThanOrEqualTo(String value) {
            addCriterion("business_license_num <=", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumLike(String value) {
            addCriterion("business_license_num like", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumNotLike(String value) {
            addCriterion("business_license_num not like", value, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumIn(List<String> values) {
            addCriterion("business_license_num in", values, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumNotIn(List<String> values) {
            addCriterion("business_license_num not in", values, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumBetween(String value1, String value2) {
            addCriterion("business_license_num between", value1, value2, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNumNotBetween(String value1, String value2) {
            addCriterion("business_license_num not between", value1, value2, "businessLicenseNum");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayIsNull() {
            addCriterion("business_registration_day is null");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayIsNotNull() {
            addCriterion("business_registration_day is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayEqualTo(String value) {
            addCriterion("business_registration_day =", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayNotEqualTo(String value) {
            addCriterion("business_registration_day <>", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayGreaterThan(String value) {
            addCriterion("business_registration_day >", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayGreaterThanOrEqualTo(String value) {
            addCriterion("business_registration_day >=", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayLessThan(String value) {
            addCriterion("business_registration_day <", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayLessThanOrEqualTo(String value) {
            addCriterion("business_registration_day <=", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayLike(String value) {
            addCriterion("business_registration_day like", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayNotLike(String value) {
            addCriterion("business_registration_day not like", value, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayIn(List<String> values) {
            addCriterion("business_registration_day in", values, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayNotIn(List<String> values) {
            addCriterion("business_registration_day not in", values, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayBetween(String value1, String value2) {
            addCriterion("business_registration_day between", value1, value2, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationDayNotBetween(String value1, String value2) {
            addCriterion("business_registration_day not between", value1, value2, "businessRegistrationDay");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateIsNull() {
            addCriterion("license_expiry_date is null");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateIsNotNull() {
            addCriterion("license_expiry_date is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateEqualTo(String value) {
            addCriterion("license_expiry_date =", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateNotEqualTo(String value) {
            addCriterion("license_expiry_date <>", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateGreaterThan(String value) {
            addCriterion("license_expiry_date >", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateGreaterThanOrEqualTo(String value) {
            addCriterion("license_expiry_date >=", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateLessThan(String value) {
            addCriterion("license_expiry_date <", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateLessThanOrEqualTo(String value) {
            addCriterion("license_expiry_date <=", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateLike(String value) {
            addCriterion("license_expiry_date like", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateNotLike(String value) {
            addCriterion("license_expiry_date not like", value, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateIn(List<String> values) {
            addCriterion("license_expiry_date in", values, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateNotIn(List<String> values) {
            addCriterion("license_expiry_date not in", values, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateBetween(String value1, String value2) {
            addCriterion("license_expiry_date between", value1, value2, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andLicenseExpiryDateNotBetween(String value1, String value2) {
            addCriterion("license_expiry_date not between", value1, value2, "licenseExpiryDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNull() {
            addCriterion("registered_capital is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNotNull() {
            addCriterion("registered_capital is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalEqualTo(String value) {
            addCriterion("registered_capital =", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotEqualTo(String value) {
            addCriterion("registered_capital <>", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThan(String value) {
            addCriterion("registered_capital >", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThanOrEqualTo(String value) {
            addCriterion("registered_capital >=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThan(String value) {
            addCriterion("registered_capital <", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThanOrEqualTo(String value) {
            addCriterion("registered_capital <=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLike(String value) {
            addCriterion("registered_capital like", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotLike(String value) {
            addCriterion("registered_capital not like", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIn(List<String> values) {
            addCriterion("registered_capital in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotIn(List<String> values) {
            addCriterion("registered_capital not in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalBetween(String value1, String value2) {
            addCriterion("registered_capital between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotBetween(String value1, String value2) {
            addCriterion("registered_capital not between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeIsNull() {
            addCriterion("business_registration_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeIsNotNull() {
            addCriterion("business_registration_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeEqualTo(String value) {
            addCriterion("business_registration_type =", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeNotEqualTo(String value) {
            addCriterion("business_registration_type <>", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeGreaterThan(String value) {
            addCriterion("business_registration_type >", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_registration_type >=", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeLessThan(String value) {
            addCriterion("business_registration_type <", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeLessThanOrEqualTo(String value) {
            addCriterion("business_registration_type <=", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeLike(String value) {
            addCriterion("business_registration_type like", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeNotLike(String value) {
            addCriterion("business_registration_type not like", value, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeIn(List<String> values) {
            addCriterion("business_registration_type in", values, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeNotIn(List<String> values) {
            addCriterion("business_registration_type not in", values, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeBetween(String value1, String value2) {
            addCriterion("business_registration_type between", value1, value2, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andBusinessRegistrationTypeNotBetween(String value1, String value2) {
            addCriterion("business_registration_type not between", value1, value2, "businessRegistrationType");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressIsNull() {
            addCriterion("registered_address is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressIsNotNull() {
            addCriterion("registered_address is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressEqualTo(String value) {
            addCriterion("registered_address =", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressNotEqualTo(String value) {
            addCriterion("registered_address <>", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressGreaterThan(String value) {
            addCriterion("registered_address >", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressGreaterThanOrEqualTo(String value) {
            addCriterion("registered_address >=", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressLessThan(String value) {
            addCriterion("registered_address <", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressLessThanOrEqualTo(String value) {
            addCriterion("registered_address <=", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressLike(String value) {
            addCriterion("registered_address like", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressNotLike(String value) {
            addCriterion("registered_address not like", value, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressIn(List<String> values) {
            addCriterion("registered_address in", values, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressNotIn(List<String> values) {
            addCriterion("registered_address not in", values, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressBetween(String value1, String value2) {
            addCriterion("registered_address between", value1, value2, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressNotBetween(String value1, String value2) {
            addCriterion("registered_address not between", value1, value2, "registeredAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressIsNull() {
            addCriterion("business_address is null");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressIsNotNull() {
            addCriterion("business_address is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressEqualTo(String value) {
            addCriterion("business_address =", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotEqualTo(String value) {
            addCriterion("business_address <>", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressGreaterThan(String value) {
            addCriterion("business_address >", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressGreaterThanOrEqualTo(String value) {
            addCriterion("business_address >=", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressLessThan(String value) {
            addCriterion("business_address <", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressLessThanOrEqualTo(String value) {
            addCriterion("business_address <=", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressLike(String value) {
            addCriterion("business_address like", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotLike(String value) {
            addCriterion("business_address not like", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressIn(List<String> values) {
            addCriterion("business_address in", values, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotIn(List<String> values) {
            addCriterion("business_address not in", values, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressBetween(String value1, String value2) {
            addCriterion("business_address between", value1, value2, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotBetween(String value1, String value2) {
            addCriterion("business_address not between", value1, value2, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeIsNull() {
            addCriterion("business_scope is null");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeIsNotNull() {
            addCriterion("business_scope is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeEqualTo(String value) {
            addCriterion("business_scope =", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotEqualTo(String value) {
            addCriterion("business_scope <>", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeGreaterThan(String value) {
            addCriterion("business_scope >", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeGreaterThanOrEqualTo(String value) {
            addCriterion("business_scope >=", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeLessThan(String value) {
            addCriterion("business_scope <", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeLessThanOrEqualTo(String value) {
            addCriterion("business_scope <=", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeLike(String value) {
            addCriterion("business_scope like", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotLike(String value) {
            addCriterion("business_scope not like", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeIn(List<String> values) {
            addCriterion("business_scope in", values, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotIn(List<String> values) {
            addCriterion("business_scope not in", values, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeBetween(String value1, String value2) {
            addCriterion("business_scope between", value1, value2, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotBetween(String value1, String value2) {
            addCriterion("business_scope not between", value1, value2, "businessScope");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberIsNull() {
            addCriterion("qualification_certificate_number is null");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberIsNotNull() {
            addCriterion("qualification_certificate_number is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberEqualTo(String value) {
            addCriterion("qualification_certificate_number =", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberNotEqualTo(String value) {
            addCriterion("qualification_certificate_number <>", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberGreaterThan(String value) {
            addCriterion("qualification_certificate_number >", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("qualification_certificate_number >=", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberLessThan(String value) {
            addCriterion("qualification_certificate_number <", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberLessThanOrEqualTo(String value) {
            addCriterion("qualification_certificate_number <=", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberLike(String value) {
            addCriterion("qualification_certificate_number like", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberNotLike(String value) {
            addCriterion("qualification_certificate_number not like", value, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberIn(List<String> values) {
            addCriterion("qualification_certificate_number in", values, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberNotIn(List<String> values) {
            addCriterion("qualification_certificate_number not in", values, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberBetween(String value1, String value2) {
            addCriterion("qualification_certificate_number between", value1, value2, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationCertificateNumberNotBetween(String value1, String value2) {
            addCriterion("qualification_certificate_number not between", value1, value2, "qualificationCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelIsNull() {
            addCriterion("qualification_level is null");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelIsNotNull() {
            addCriterion("qualification_level is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelEqualTo(String value) {
            addCriterion("qualification_level =", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelNotEqualTo(String value) {
            addCriterion("qualification_level <>", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelGreaterThan(String value) {
            addCriterion("qualification_level >", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelGreaterThanOrEqualTo(String value) {
            addCriterion("qualification_level >=", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelLessThan(String value) {
            addCriterion("qualification_level <", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelLessThanOrEqualTo(String value) {
            addCriterion("qualification_level <=", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelLike(String value) {
            addCriterion("qualification_level like", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelNotLike(String value) {
            addCriterion("qualification_level not like", value, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelIn(List<String> values) {
            addCriterion("qualification_level in", values, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelNotIn(List<String> values) {
            addCriterion("qualification_level not in", values, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelBetween(String value1, String value2) {
            addCriterion("qualification_level between", value1, value2, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationLevelNotBetween(String value1, String value2) {
            addCriterion("qualification_level not between", value1, value2, "qualificationLevel");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateIsNull() {
            addCriterion("qualification_issuance_date is null");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateIsNotNull() {
            addCriterion("qualification_issuance_date is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateEqualTo(String value) {
            addCriterion("qualification_issuance_date =", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateNotEqualTo(String value) {
            addCriterion("qualification_issuance_date <>", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateGreaterThan(String value) {
            addCriterion("qualification_issuance_date >", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateGreaterThanOrEqualTo(String value) {
            addCriterion("qualification_issuance_date >=", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateLessThan(String value) {
            addCriterion("qualification_issuance_date <", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateLessThanOrEqualTo(String value) {
            addCriterion("qualification_issuance_date <=", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateLike(String value) {
            addCriterion("qualification_issuance_date like", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateNotLike(String value) {
            addCriterion("qualification_issuance_date not like", value, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateIn(List<String> values) {
            addCriterion("qualification_issuance_date in", values, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateNotIn(List<String> values) {
            addCriterion("qualification_issuance_date not in", values, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateBetween(String value1, String value2) {
            addCriterion("qualification_issuance_date between", value1, value2, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andQualificationIssuanceDateNotBetween(String value1, String value2) {
            addCriterion("qualification_issuance_date not between", value1, value2, "qualificationIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDayIsNull() {
            addCriterion("approval_day is null");
            return (Criteria) this;
        }

        public Criteria andApprovalDayIsNotNull() {
            addCriterion("approval_day is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalDayEqualTo(String value) {
            addCriterion("approval_day =", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayNotEqualTo(String value) {
            addCriterion("approval_day <>", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayGreaterThan(String value) {
            addCriterion("approval_day >", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayGreaterThanOrEqualTo(String value) {
            addCriterion("approval_day >=", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayLessThan(String value) {
            addCriterion("approval_day <", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayLessThanOrEqualTo(String value) {
            addCriterion("approval_day <=", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayLike(String value) {
            addCriterion("approval_day like", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayNotLike(String value) {
            addCriterion("approval_day not like", value, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayIn(List<String> values) {
            addCriterion("approval_day in", values, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayNotIn(List<String> values) {
            addCriterion("approval_day not in", values, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayBetween(String value1, String value2) {
            addCriterion("approval_day between", value1, value2, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andApprovalDayNotBetween(String value1, String value2) {
            addCriterion("approval_day not between", value1, value2, "approvalDay");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionIsNull() {
            addCriterion("annual_inspection is null");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionIsNotNull() {
            addCriterion("annual_inspection is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionEqualTo(String value) {
            addCriterion("annual_inspection =", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionNotEqualTo(String value) {
            addCriterion("annual_inspection <>", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionGreaterThan(String value) {
            addCriterion("annual_inspection >", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionGreaterThanOrEqualTo(String value) {
            addCriterion("annual_inspection >=", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionLessThan(String value) {
            addCriterion("annual_inspection <", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionLessThanOrEqualTo(String value) {
            addCriterion("annual_inspection <=", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionLike(String value) {
            addCriterion("annual_inspection like", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionNotLike(String value) {
            addCriterion("annual_inspection not like", value, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionIn(List<String> values) {
            addCriterion("annual_inspection in", values, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionNotIn(List<String> values) {
            addCriterion("annual_inspection not in", values, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionBetween(String value1, String value2) {
            addCriterion("annual_inspection between", value1, value2, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andAnnualInspectionNotBetween(String value1, String value2) {
            addCriterion("annual_inspection not between", value1, value2, "annualInspection");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordIsNull() {
            addCriterion("integrity_record is null");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordIsNotNull() {
            addCriterion("integrity_record is not null");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordEqualTo(String value) {
            addCriterion("integrity_record =", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordNotEqualTo(String value) {
            addCriterion("integrity_record <>", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordGreaterThan(String value) {
            addCriterion("integrity_record >", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordGreaterThanOrEqualTo(String value) {
            addCriterion("integrity_record >=", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordLessThan(String value) {
            addCriterion("integrity_record <", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordLessThanOrEqualTo(String value) {
            addCriterion("integrity_record <=", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordLike(String value) {
            addCriterion("integrity_record like", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordNotLike(String value) {
            addCriterion("integrity_record not like", value, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordIn(List<String> values) {
            addCriterion("integrity_record in", values, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordNotIn(List<String> values) {
            addCriterion("integrity_record not in", values, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordBetween(String value1, String value2) {
            addCriterion("integrity_record between", value1, value2, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andIntegrityRecordNotBetween(String value1, String value2) {
            addCriterion("integrity_record not between", value1, value2, "integrityRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordIsNull() {
            addCriterion("exposure_record is null");
            return (Criteria) this;
        }

        public Criteria andExposureRecordIsNotNull() {
            addCriterion("exposure_record is not null");
            return (Criteria) this;
        }

        public Criteria andExposureRecordEqualTo(String value) {
            addCriterion("exposure_record =", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordNotEqualTo(String value) {
            addCriterion("exposure_record <>", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordGreaterThan(String value) {
            addCriterion("exposure_record >", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordGreaterThanOrEqualTo(String value) {
            addCriterion("exposure_record >=", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordLessThan(String value) {
            addCriterion("exposure_record <", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordLessThanOrEqualTo(String value) {
            addCriterion("exposure_record <=", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordLike(String value) {
            addCriterion("exposure_record like", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordNotLike(String value) {
            addCriterion("exposure_record not like", value, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordIn(List<String> values) {
            addCriterion("exposure_record in", values, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordNotIn(List<String> values) {
            addCriterion("exposure_record not in", values, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordBetween(String value1, String value2) {
            addCriterion("exposure_record between", value1, value2, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andExposureRecordNotBetween(String value1, String value2) {
            addCriterion("exposure_record not between", value1, value2, "exposureRecord");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlIsNull() {
            addCriterion("niaok_picture_url is null");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlIsNotNull() {
            addCriterion("niaok_picture_url is not null");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlEqualTo(String value) {
            addCriterion("niaok_picture_url =", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlNotEqualTo(String value) {
            addCriterion("niaok_picture_url <>", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlGreaterThan(String value) {
            addCriterion("niaok_picture_url >", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlGreaterThanOrEqualTo(String value) {
            addCriterion("niaok_picture_url >=", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlLessThan(String value) {
            addCriterion("niaok_picture_url <", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlLessThanOrEqualTo(String value) {
            addCriterion("niaok_picture_url <=", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlLike(String value) {
            addCriterion("niaok_picture_url like", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlNotLike(String value) {
            addCriterion("niaok_picture_url not like", value, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlIn(List<String> values) {
            addCriterion("niaok_picture_url in", values, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlNotIn(List<String> values) {
            addCriterion("niaok_picture_url not in", values, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlBetween(String value1, String value2) {
            addCriterion("niaok_picture_url between", value1, value2, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andNiaokPictureUrlNotBetween(String value1, String value2) {
            addCriterion("niaok_picture_url not between", value1, value2, "niaokPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlIsNull() {
            addCriterion("qw_picture_url is null");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlIsNotNull() {
            addCriterion("qw_picture_url is not null");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlEqualTo(String value) {
            addCriterion("qw_picture_url =", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlNotEqualTo(String value) {
            addCriterion("qw_picture_url <>", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlGreaterThan(String value) {
            addCriterion("qw_picture_url >", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlGreaterThanOrEqualTo(String value) {
            addCriterion("qw_picture_url >=", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlLessThan(String value) {
            addCriterion("qw_picture_url <", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlLessThanOrEqualTo(String value) {
            addCriterion("qw_picture_url <=", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlLike(String value) {
            addCriterion("qw_picture_url like", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlNotLike(String value) {
            addCriterion("qw_picture_url not like", value, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlIn(List<String> values) {
            addCriterion("qw_picture_url in", values, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlNotIn(List<String> values) {
            addCriterion("qw_picture_url not in", values, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlBetween(String value1, String value2) {
            addCriterion("qw_picture_url between", value1, value2, "qwPictureUrl");
            return (Criteria) this;
        }

        public Criteria andQwPictureUrlNotBetween(String value1, String value2) {
            addCriterion("qw_picture_url not between", value1, value2, "qwPictureUrl");
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