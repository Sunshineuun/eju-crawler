package com.qiusm.eju.crawler.parser.competitor.wiwj.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.DETAIL_URL;

/**
 * @author qiushengming
 */
@Slf4j
@Service("wiwjCommunityListHandler")
public class CommunityListHandler extends CommunityPageListHandler {

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONArray listArray = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        JSONArray resultArray = new JSONArray();

        listArray.forEach(o -> {
            JSONObject community = ((JSONObject) o).getJSONObject("BaseDetail");
            JSONObject result = new JSONObject();

            result.put(DETAIL_URL, requestDto.getUrl());
            result.putAll(requestDto.getData());

            result.put("title", community.get("communityid"));
            result.put("title_id", community.get("community_id"));
            result.put("average_price", community.get("price"));
            // result.put("building_type", community.get("building_type"));
            result.put("lng", community.getString("x"));
            result.put("lat", community.getString("y"));
            result.put("area_name", result.get("qyname"));
            // result.put("area_id", result.get("district_id"));
            result.put("plate", result.get("sqname"));
            result.put("address", result.get("address"));
            result.put("sale", result.get("salecount"));
            result.put("rent", result.get("rentcount"));
            result.put("building_year", result.get("startData"));
            result.put("allhouse", result.get("allhouse"));
            resultArray.add(result);
        });
        responseDto.getResult().put("list", resultArray);
    }
}
