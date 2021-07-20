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
 * 贝壳app端：房号查询v1版本. 入参：单元id <br>
 * 面向URL:/yezhu/publish/getHouses?unit_id=5013000243156{单元id} <br>
 *
 * @author qiushengming
 */
public class HouseSearchV1App extends BkAppBaseSearch {

    private static final String URL_TEMPLATE = "%s/yezhu/publish/getHouses?unit_id=%s";
    private static final String UNIT_ID = "unit_id";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (requestParam.containsKey(UNIT_ID)) {
            String unitId = requestParam.get(UNIT_ID).trim();
            if (StringUtils.isNotBlank(unitId)) {
                String url = String.format(URL_TEMPLATE, DOMAIN_NAME, unitId);
                requestDto.setUrl(url);
            } else {
                throw new BusinessException("单元ID为空");
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
            String id = var.getString("house_id");
            String name = var.getString("house_name");
            result.put(id, name);
        });
        responseDto.setResult(result);
    }
}
