package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.lang.JSONUtils;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 贝壳app端：小区查询v1版本. <br>
 * 面向URL:%s/house/suggestion/index?city_id={城市编码}&query={关键字}&channel_id=xiaoqu <br>
 *
 * @author qiushengming
 */
@Service
public class CommunitySearchV1 extends BkAppSkeletonBaseSearch {

    private static final String URL_TEMPLATE = "%s/house/suggestion/index?city_id=%s&query=%s&channel_id=xiaoqu";
    private static final String KEY = "key";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(KEY)
                || StringUtils.isBlank(requestParam.get(KEY))) {
            throw new BusinessException(10000, "检索关键字为空，请重新输入.");
        }

        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get("city_id"), requestParam.get(KEY));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject var0 = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray items = JSONUtils.getJsonArrayByKey(var0, "data.groups.items");

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        items.forEach(o -> {
            if (o instanceof JSONObject) {
                JSONObject var = (JSONObject) o;
                String communityId = var.getString("community_id");
                String communityName = var.getString("community_name");
                String communityAlias = var.getString("community_alias");
                JSONObject jsonVar = new JSONObject();
                jsonVar.put("community_name", communityName);
                jsonVar.put("community_Id", communityId);
                jsonVar.put("community_alias", communityAlias);
                array.add(jsonVar);
            }
        });
        result.put("list", array);
        responseDto.setResult(result);
    }
}
