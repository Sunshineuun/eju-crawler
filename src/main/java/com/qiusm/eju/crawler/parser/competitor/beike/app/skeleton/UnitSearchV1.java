package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;

import java.util.Map;

/**
 * 贝壳app端：单元查询v1版本. 入参：楼栋id <br>
 * 面向URL:/yezhu/publish/getUnits?building_id=5012000243156{楼栋ID} <br>
 *
 * @author qiushengming
 */
public class UnitSearchV1 extends BkBaseSearch {

    private static final String URL_TEMPLATE = "%s/yezhu/publish/getUnits?building_id=%s";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get("building_id"));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject var0 = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray list = JSONUtils.getJsonArrayByKey(var0, "data.list");
        JSONObject result = new JSONObject();
        list.forEach(o -> {
            JSONObject var = (JSONObject) o;
            String id = var.getString("unit_id");
            String name = var.getString("unit_name");
            result.put(id, name);
        });
        responseDto.setResult(result);
    }
}
