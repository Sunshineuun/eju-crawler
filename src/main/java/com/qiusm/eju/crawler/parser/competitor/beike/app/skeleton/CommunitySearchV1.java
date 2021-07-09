package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;

import java.util.Map;

/**
 * 贝壳app端：小区查询v1版本. <br>
 * 面向URL:%s/house/suggestion/index?city_id={城市编码}&query={关键字}&channel_id=xiaoqu <br>
 *
 * @author qiushengming
 */
public class CommunitySearchV1 extends BkBaseSearch {

    private static final String URL_TEMPLATE = "%s/house/suggestion/index?city_id=%s&query=%s&channel_id=xiaoqu";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get("city_id"), requestParam.get("key"));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject var0 = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray items = JSONUtils.getJsonArrayByKey(var0, "data.groups.items");

        JSONObject result = new JSONObject();
        items.forEach(o -> {
            if (o instanceof JSONObject) {
                JSONObject var = (JSONObject) o;
                String communityId = JSONUtils.getStringByKey(var, "value.communityId");
                String communityName = JSONUtils.getStringByKey(var, "text");
                result.put(communityId, communityName);
            }
        });
        responseDto.setResult(result);
    }
}
