package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;

import java.util.Map;

/**
 * 贝壳app端: 楼栋查询v1版本。入参，小区id；<br>
 * 面向URL:/yezhu/publish/getBuildings?community_id=5011000002700{小区id} <br>
 *
 * @author qiushengming
 */
public class BuildingSearchV1 extends BkBaseSearch {

    private static final String URL_TEMPLATE = "%s/yezhu/publish/getBuildings?community_id=%s";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get("community_id"));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject var0 = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray list = JSONUtils.getJsonArrayByKey(var0, "data.list");
        JSONObject result = new JSONObject();
        list.forEach(o -> {
            JSONObject var = (JSONObject) o;
            String buildingId = var.getString("building_id");
            String buildingName = var.getString("building_name");
            result.put(buildingId, buildingName);
        });
        responseDto.setResult(result);
    }
}
