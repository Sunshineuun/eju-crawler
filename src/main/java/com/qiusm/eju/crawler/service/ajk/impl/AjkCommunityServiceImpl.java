package com.qiusm.eju.crawler.service.ajk.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.CommunityInfo;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.community.CommunityListHandler;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.community.CommunityPageListHandler;
import com.qiusm.eju.crawler.service.ajk.IAjkCommunityService;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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

    private ThreadsUtils threadUtils = new ThreadsUtils();
    private Integer threadNum = 8;

    @Override
    public JSONArray communityList(JSONObject area) {
        JSONArray result = new JSONArray();

        JSONArray pageListArray = pageListHandler(area);
        if (pageListArray == null) {
            return new JSONArray();
        }
        List<JSON> future = threadUtils.executeFutures(pageListArray,
                (e) -> {
                    // 如果执行成功返回JSONArray；否则返回楼栋信息。
                    return pageHandler((JSONObject) e);
                }, threadNum);

        for (Object o2 : future) {
            // 小区列表
            JSONArray communityList = pageHandler((JSONObject) o2);
            if (communityList != null) {
                result.addAll(communityList);
            }
        }

        result.forEach(r -> {
            JSONObject var = (JSONObject) r;
            var.put("source", "AJK");
            CommunityInfo communityInfo = JSONObject.parseObject(var.toJSONString(), CommunityInfo.class);
            communityInfo.insert();
        });

        log.info("area info: {}, 预计获取小区数量：{}, 实际获取小区数量：{}",
                area, area.getString("totalCount"), result.size());
        return result;
    }

    /**
     * 获取当前区域下的翻页列表
     *
     * @param area 区域信息
     * @return 翻页列表
     */
    JSONArray pageListHandler(JSONObject area) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, area.getString(CITY_ID));
        params.put(AREA_ID, area.getString(AREA_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(area)
                .requestMethod(RequestMethodEnum.GET)
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
    JSONArray pageHandler(JSONObject page) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, page.getString(CITY_ID));
        params.put(AREA_ID, page.getString(AREA_ID));
        if (page.containsKey(PAGE)) {
            params.put(PAGE, page.getString(PAGE));
        }

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .requestMethod(RequestMethodEnum.GET)
                .data(page)
                .build();

        ResponseDto responseDto = communityListHandler.execute(requestDto);

        return responseDto.getResult().getJSONArray("list");
    }
}
