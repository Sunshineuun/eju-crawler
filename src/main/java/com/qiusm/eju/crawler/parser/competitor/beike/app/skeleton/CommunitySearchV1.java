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
 * 贝壳app端：小区查询v1版本. <br>
 * 面向URL:%s/house/suggestion/index?city_id={城市编码}&query={关键字}&channel_id=xiaoqu <br>
 *
 * @author qiushengming
 */
public class CommunitySearchV1 extends BkAppSkeletonBaseSearch {

    private static final String URL_TEMPLATE = "%s/house/suggestion/index?city_id=%s&query=%s&channel_id=xiaoqu";
    private static final String KEY = "key";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (requestParam.containsKey(KEY)) {
            String key = requestParam.get(KEY).trim();
            if (StringUtils.isNotBlank(key)) {
                String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get("city_id"), key);
                requestDto.setUrl(url);
            } else {
                throw new BusinessException("检索关键字为空，请重新输入.");
            }
        }
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
