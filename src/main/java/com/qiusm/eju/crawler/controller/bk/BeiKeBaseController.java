package com.qiusm.eju.crawler.controller.bk;

import com.alibaba.fastjson.JSONArray;
import com.qiusm.eju.crawler.controller.base.CrawlerTaskSchedulingBaseController;
import com.qiusm.eju.crawler.parser.competitor.beike.app.base.BkAppCityDictSearch;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiushengming
 */
public abstract class BeiKeBaseController extends CrawlerTaskSchedulingBaseController {

    @Resource
    BkAppCityDictSearch bkAppCityDictSearch;

    /**
     * 获取城市下板块和区域的信息
     *
     * @param cityId 城市id
     * @param city   城市拼音简写
     * @return JSONArray
     */
    JSONArray cityHandler(String cityId, String city) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", cityId);
        params.put("city", city);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = bkAppCityDictSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }
}
