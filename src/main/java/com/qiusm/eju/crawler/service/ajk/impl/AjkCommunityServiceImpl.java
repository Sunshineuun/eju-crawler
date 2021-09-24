package com.qiusm.eju.crawler.service.ajk.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.Community;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.community.CommunityDetailHandler;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.community.CommunityListHandler;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.community.CommunityPageListHandler;
import com.qiusm.eju.crawler.service.ajk.IAjkCommunityService;
import com.qiusm.eju.crawler.service.base.ICommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class AjkCommunityServiceImpl
        implements IAjkCommunityService {
    @Resource
    private CommunityPageListHandler communityPageListHandler;
    @Resource
    private CommunityListHandler communityListHandler;
    @Resource
    private ICommunityService communityService;
    @Resource
    private CommunityDetailHandler communityDetailHandler;

    @Override
    public JSONArray communityList(JSONObject area) {
        JSONArray result = new JSONArray();

        JSONArray pageListArray = pageListHandler(area);
        if (pageListArray == null) {
            return new JSONArray();
        }
        for (Object o : pageListArray) {
            JSONArray communityList = pageHandler((JSONObject) o);
            if (communityList != null) {
                result.addAll(communityList);
                toCommunityDb(communityList);
            }
        }

        log.info("area info: {}, 预计获取小区数量：{}, 实际获取小区数量：{}",
                area, area.getString("totalCount"), result.size());
        return result;
    }

    @Override
    public void communityDetail(Community community) {
        communityDetailHandler(community);
    }

    /**
     * 获取当前区域下的翻页列表
     *
     * @param area 区域信息
     * @return 翻页列表
     */
    private JSONArray pageListHandler(JSONObject area) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, area.getString(CITY_ID));
        params.put(AREA_ID, area.getString(AREA_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(area)
                // .requestMethod(RequestMethodEnum.GET)
                .build();

        ResponseDto responseDto = communityPageListHandler.execute(requestDto);
        area.put("totalCount", responseDto.getResult().getInteger("totalCount"));
        return responseDto.getResult().getJSONArray("list");
    }

    /**
     * 当前页面的请求参数
     *
     * @param page 当前页面的请求参数
     * @return 小区列表
     */
    private JSONArray pageHandler(JSONObject page) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, page.getString(CITY_ID));
        params.put(AREA_ID, page.getString(AREA_ID));
        if (page.containsKey(PAGE)) {
            params.put(PAGE, page.getString(PAGE));
        }

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                // .requestMethod(RequestMethodEnum.GET)
                .data(page)
                .build();

        ResponseDto responseDto = communityListHandler.execute(requestDto);

        return responseDto.getResult().getJSONArray("list");
    }

    private void communityDetailHandler(Community community) {
        Map<String, String> params = new HashMap<>(8);
        params.put("comm_id", community.getCommunityId());
        params.put("city_id", community.getCityId());

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();
        ResponseDto responseDto = communityDetailHandler.execute(requestDto);
        JSONObject json = responseDto.getResult();
        if (json != null && json.size() != 0) {
            community.setPropertyCompany(json.getString("property_company"));
            community.setPropertyExpenses(json.getString("property_money"));
            community.setVolumeRate(json.getString("plot_ratio"));
            community.setGreeningRate(json.getString("landscaping_ratio"));
            community.setParkSpace(json.getString("parking"));
            community.setDevEnt(json.getString("developer"));
            community.setBuildYear(json.getString("completion_time"));
            community.setTotalArea(json.getString("total_area"));
            community.setTotalHouse(json.getString("total_house_hold_num"));
        } else {
        }
    }

    private void toCommunityDb(JSONArray communityList) {
        communityList.forEach(o -> {
            JSONObject var = (JSONObject) o;
            Community community = JSONObject.parseObject(var.toJSONString(), Community.class);
            community.setSource("AJK");
            communityService.checkInsert(community);
        });
    }
}
