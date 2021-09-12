package com.qiusm.eju.crawler.service.bk.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.competitor.beike.app.secondhand.SecondHandHouseListHandler;
import com.qiusm.eju.crawler.parser.competitor.beike.app.secondhand.SecondHandHousePageListHandler;
import com.qiusm.eju.crawler.service.bk.ISecondHandHouseService;
import com.qiusm.eju.crawler.utils.lang.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class SecondHandHouseServiceImpl
        implements ISecondHandHouseService {

    @Resource
    private BkCityInoServiceImpl bkCityInoService;
    @Resource(name = "secondHandHousePageListHandler")
    private SecondHandHousePageListHandler pageListHandler;
    @Resource
    private SecondHandHouseListHandler listHandler;

    @Override
    public void handler(String cityId, String cityPy) {
        int count = 0;
        int totalCount = 0;

        JSONArray bizArray = bkCityInoService.getBizByCity(cityId, cityPy);
        if (bizArray == null) {
            log.warn("获取不到板块信息：{}, {}", cityId, cityPy);
            return;
        }
        for (Object o1 : bizArray) {
            JSONArray pageListArray = pageListHandler((JSONObject) o1);
            if (pageListArray == null) {
                continue;
            }
            totalCount += ((JSONObject) o1).getInteger("total_count");
            for (Object o2 : pageListArray) {
                JSONArray detailList = pageHandler((JSONObject) o2);
                if (detailList != null) {
                    // 需要将结果存储起来，暂时写到文件里面吧
                    for (Object o : detailList) {
                        JSONObject var = (JSONObject) o;
                        FileUtils.printFile(var.toJSONString() + "\n",
                                "source/bk/", this.getClass().getSimpleName() + ".txt", true);
                    }
                }
            }
        }
        String desc = String.format("总计数量:%s,采集数量:%s", totalCount, count);
        log.info("{},{} :{}", cityId, cityPy, desc);
    }

    private JSONArray pageListHandler(JSONObject biz) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", biz.getString("city_id"));
        params.put("city", biz.getString("city"));
        params.put("district_id", biz.getString("district_id"));
        params.put("bizcircle_id", biz.getString("bizcircle_id"));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(biz)
                .build();

        ResponseDto responseDto = pageListHandler.execute(requestDto);
        biz.put("total_count", responseDto.getResult().getInteger("total_count"));
        return responseDto.getResult().getJSONArray("list");
    }

    private JSONArray pageHandler(JSONObject page) {
        ResponseDto responseDto = pageHandler1(page);
        JSONArray list = responseDto.getResult().getJSONArray("list");
        JSONArray requestList = responseDto.getResult().getJSONArray("request_list");
        if (requestList != null) {
            for (Object o : requestList) {
                ResponseDto responseDto1 = pageHandler1((JSONObject) o);
                JSONArray list1 = responseDto1.getResult().getJSONArray("list");
                if (list1 != null) {
                    list.addAll(list1);
                }
            }
        }
        return list;
    }

    private ResponseDto pageHandler1(JSONObject page) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", page.getString("city_id"));
        params.put("city", page.getString("city"));
        params.put("district_id", page.getString("district_id"));
        params.put("bizcircle_id", page.getString("bizcircle_id"));
        if (page.containsKey("limit_offset")) {
            params.put("limit_offset", page.getString("limit_offset"));
        }
        if (page.containsKey("price_bp")) {
            params.put("price_bp", page.getString("price_bp"));
        }
        if (page.containsKey("price_ep")) {
            params.put("price_ep", page.getString("price_ep"));
        }

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(page)
                .build();

        return listHandler.execute(requestDto);
    }
}
