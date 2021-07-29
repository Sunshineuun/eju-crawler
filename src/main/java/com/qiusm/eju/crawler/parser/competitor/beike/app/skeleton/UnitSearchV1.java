package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 贝壳app端：单元查询v1版本. 入参：楼栋id <br>
 * 面向URL:/yezhu/publish/getUnits?building_id=5012000243156{楼栋ID} <br>
 *
 * @author qiushengming
 */
@Service
public class UnitSearchV1 extends BkAppSkeletonBaseSearch {

    private static final String URL_TEMPLATE = "%s/yezhu/publish/getUnits?building_id=%s";
    private static final String BUILDING_ID = "building_id";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(BUILDING_ID)
                || StringUtils.isBlank(requestParam.get(BUILDING_ID))) {
            throw new BusinessException(10000, "楼栋ID为空");
        }

        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get(BUILDING_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject var0 = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray list = JSONUtils.getJsonArrayByKey(var0, "data.list");
        JSONArray array = new JSONArray();
        list.forEach(o -> {
            JSONObject var = (JSONObject) o;
            String id = var.getString("unit_id");
            String name = var.getString("unit_name");
            JSONObject jsonVar = new JSONObject();
            jsonVar.put("unit_name", name);
            jsonVar.put("unit_id", id);
            jsonVar.putAll(requestDto.getData());
            array.add(jsonVar);
        });
        responseDto.getResult().put("list", array);
    }
}
