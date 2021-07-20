package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import com.qiusm.eju.crawler.utils.StringUtils;

import java.util.Map;

/**
 * 贝壳app端: 楼栋查询v1版本。入参，小区id；<br>
 * 面向URL:/yezhu/publish/getBuildings?community_id=5011000002700{小区id} <br>
 *
 * @author qiushengming
 */
public class BuildingSearchV1 extends BkAppBaseSearch {

    private static final String URL_TEMPLATE = "%s/yezhu/publish/getBuildings?community_id=%s";
    private static final String COMMUNITY_ID = "community_id";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (requestParam.containsKey(COMMUNITY_ID)) {
            String communityId = requestParam.get(COMMUNITY_ID).trim();
            if (StringUtils.isNotBlank(communityId)) {
                String url = String.format(URL_TEMPLATE, DOMAIN_NAME, communityId);
                requestDto.setUrl(url);
            } else {
                throw new BusinessException("小区ID为空");
            }
        }
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
