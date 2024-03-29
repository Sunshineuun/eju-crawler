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
 * 贝壳app端: 楼栋查询v1版本。入参，小区id；<br>
 * 面向URL:/yezhu/publish/getBuildings?community_id=5011000002700{小区id} <br>
 *
 * @author qiushengming
 */
@Service
public class BuildingSearchV1 extends BkAppSkeletonBaseSearch {

    private static final String URL_TEMPLATE = "%s/yezhu/publish/getBuildings?community_id=%s";
    private static final String COMMUNITY_ID = "community_id";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(COMMUNITY_ID)
                || StringUtils.isBlank(requestParam.get(COMMUNITY_ID))) {
            throw new BusinessException(10000, "小区ID为空");
        }

        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get(COMMUNITY_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject var0 = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray list = JSONUtils.getJsonArrayByKey(var0, "data.list");
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        Map<String, Object> data = requestDto.getData();
        String keys = "city_id,community_id,community_name,district_name";
        list.forEach(o -> {
            JSONObject var = (JSONObject) o;
            String buildingId = var.getString("building_id");
            String buildingName = var.getString("building_name");
            JSONObject jsonVar = new JSONObject();
            jsonVar.put("building_name", buildingName);
            jsonVar.put("building_id", buildingId);
            for (String key : keys.split(",")) {
                if (data.containsKey(key)) {
                    jsonVar.put(key, data.get(key));
                }
            }
            array.add(jsonVar);
        });
        result.put("list", array);
        responseDto.setResult(result);
    }
}
