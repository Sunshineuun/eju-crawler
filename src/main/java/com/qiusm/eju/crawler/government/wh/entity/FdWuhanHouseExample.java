package com.qiusm.eju.crawler.government.wh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdWuhanHouseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FdWuhanHouseExample() {
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

        public Criteria andApprovalPresaleHouseNumIsNull() {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumIsNotNull() {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumEqualTo(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM =", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumNotEqualTo(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM <>", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumGreaterThan(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM >", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM >=", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumLessThan(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM <", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumLessThanOrEqualTo(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM <=", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumLike(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM like", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumNotLike(String value) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM not like", value, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumIn(List<String> values) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM in", values, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumNotIn(List<String> values) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM not in", values, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumBetween(String value1, String value2) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM between", value1, value2, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andApprovalPresaleHouseNumNotBetween(String value1, String value2) {
            addCriterion("APPROVAL_PRESALE_HOUSE_NUM not between", value1, value2, "approvalPresaleHouseNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumIsNull() {
            addCriterion("house_sold_num is null");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumIsNotNull() {
            addCriterion("house_sold_num is not null");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumEqualTo(String value) {
            addCriterion("house_sold_num =", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumNotEqualTo(String value) {
            addCriterion("house_sold_num <>", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumGreaterThan(String value) {
            addCriterion("house_sold_num >", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumGreaterThanOrEqualTo(String value) {
            addCriterion("house_sold_num >=", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumLessThan(String value) {
            addCriterion("house_sold_num <", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumLessThanOrEqualTo(String value) {
            addCriterion("house_sold_num <=", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumLike(String value) {
            addCriterion("house_sold_num like", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumNotLike(String value) {
            addCriterion("house_sold_num not like", value, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumIn(List<String> values) {
            addCriterion("house_sold_num in", values, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumNotIn(List<String> values) {
            addCriterion("house_sold_num not in", values, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumBetween(String value1, String value2) {
            addCriterion("house_sold_num between", value1, value2, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseSoldNumNotBetween(String value1, String value2) {
            addCriterion("house_sold_num not between", value1, value2, "houseSoldNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumIsNull() {
            addCriterion("house_unsale_num is null");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumIsNotNull() {
            addCriterion("house_unsale_num is not null");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumEqualTo(String value) {
            addCriterion("house_unsale_num =", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumNotEqualTo(String value) {
            addCriterion("house_unsale_num <>", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumGreaterThan(String value) {
            addCriterion("house_unsale_num >", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumGreaterThanOrEqualTo(String value) {
            addCriterion("house_unsale_num >=", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumLessThan(String value) {
            addCriterion("house_unsale_num <", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumLessThanOrEqualTo(String value) {
            addCriterion("house_unsale_num <=", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumLike(String value) {
            addCriterion("house_unsale_num like", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumNotLike(String value) {
            addCriterion("house_unsale_num not like", value, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumIn(List<String> values) {
            addCriterion("house_unsale_num in", values, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumNotIn(List<String> values) {
            addCriterion("house_unsale_num not in", values, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumBetween(String value1, String value2) {
            addCriterion("house_unsale_num between", value1, value2, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andHouseUnsaleNumNotBetween(String value1, String value2) {
            addCriterion("house_unsale_num not between", value1, value2, "houseUnsaleNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumIsNull() {
            addCriterion("non_house_sold_num is null");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumIsNotNull() {
            addCriterion("non_house_sold_num is not null");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumEqualTo(String value) {
            addCriterion("non_house_sold_num =", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumNotEqualTo(String value) {
            addCriterion("non_house_sold_num <>", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumGreaterThan(String value) {
            addCriterion("non_house_sold_num >", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumGreaterThanOrEqualTo(String value) {
            addCriterion("non_house_sold_num >=", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumLessThan(String value) {
            addCriterion("non_house_sold_num <", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumLessThanOrEqualTo(String value) {
            addCriterion("non_house_sold_num <=", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumLike(String value) {
            addCriterion("non_house_sold_num like", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumNotLike(String value) {
            addCriterion("non_house_sold_num not like", value, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumIn(List<String> values) {
            addCriterion("non_house_sold_num in", values, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumNotIn(List<String> values) {
            addCriterion("non_house_sold_num not in", values, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumBetween(String value1, String value2) {
            addCriterion("non_house_sold_num between", value1, value2, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseSoldNumNotBetween(String value1, String value2) {
            addCriterion("non_house_sold_num not between", value1, value2, "nonHouseSoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumIsNull() {
            addCriterion("non_house_unsold_num is null");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumIsNotNull() {
            addCriterion("non_house_unsold_num is not null");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumEqualTo(String value) {
            addCriterion("non_house_unsold_num =", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumNotEqualTo(String value) {
            addCriterion("non_house_unsold_num <>", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumGreaterThan(String value) {
            addCriterion("non_house_unsold_num >", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumGreaterThanOrEqualTo(String value) {
            addCriterion("non_house_unsold_num >=", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumLessThan(String value) {
            addCriterion("non_house_unsold_num <", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumLessThanOrEqualTo(String value) {
            addCriterion("non_house_unsold_num <=", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumLike(String value) {
            addCriterion("non_house_unsold_num like", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumNotLike(String value) {
            addCriterion("non_house_unsold_num not like", value, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumIn(List<String> values) {
            addCriterion("non_house_unsold_num in", values, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumNotIn(List<String> values) {
            addCriterion("non_house_unsold_num not in", values, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumBetween(String value1, String value2) {
            addCriterion("non_house_unsold_num between", value1, value2, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andNonHouseUnsoldNumNotBetween(String value1, String value2) {
            addCriterion("non_house_unsold_num not between", value1, value2, "nonHouseUnsoldNum");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentIsNull() {
            addCriterion("contract_record_handling_department is null");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentIsNotNull() {
            addCriterion("contract_record_handling_department is not null");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentEqualTo(String value) {
            addCriterion("contract_record_handling_department =", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentNotEqualTo(String value) {
            addCriterion("contract_record_handling_department <>", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentGreaterThan(String value) {
            addCriterion("contract_record_handling_department >", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("contract_record_handling_department >=", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentLessThan(String value) {
            addCriterion("contract_record_handling_department <", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentLessThanOrEqualTo(String value) {
            addCriterion("contract_record_handling_department <=", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentLike(String value) {
            addCriterion("contract_record_handling_department like", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentNotLike(String value) {
            addCriterion("contract_record_handling_department not like", value, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentIn(List<String> values) {
            addCriterion("contract_record_handling_department in", values, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentNotIn(List<String> values) {
            addCriterion("contract_record_handling_department not in", values, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentBetween(String value1, String value2) {
            addCriterion("contract_record_handling_department between", value1, value2, "contractRecordHandlingDepartment");
            return (Criteria) this;
        }

        public Criteria andContractRecordHandlingDepartmentNotBetween(String value1, String value2) {
            addCriterion("contract_record_handling_department not between", value1, value2, "contractRecordHandlingDepartment");
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