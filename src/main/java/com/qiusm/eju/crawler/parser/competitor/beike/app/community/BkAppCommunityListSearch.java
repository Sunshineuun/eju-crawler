package com.qiusm.eju.crawler.parser.competitor.beike.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import org.springframework.stereotype.Service;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.DETAIL_URL;

/**
 * 小区列表
 *
 * @author qiushengming
 */
@Service
public class BkAppCommunityListSearch extends BkAppCommunityPageListSearch {

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONArray listArray = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        JSONArray resultArray = new JSONArray();

        listArray.forEach(o -> {
            JSONObject community = (JSONObject) o;
            JSONObject result = new JSONObject();

            result.put(DETAIL_URL, requestDto.getUrl());
            result.putAll(requestDto.getData());

            result.put("title", community.get("community_name"));
            result.put("title_id", community.get("community_id"));
            result.put("average_price", community.get("avg_unit_price"));
            result.put("building_type", community.get("building_type"));
            result.put("lng", community.get("point_lng"));
            result.put("lat", community.get("point_lat"));
            result.put("area_name", result.get("region"));
            result.put("area_id", result.get("district_id"));
            result.put("plate", result.get("plate"));
            resultArray.add(result);
        });
        responseDto.getResult().put("list", resultArray);

    }
}
