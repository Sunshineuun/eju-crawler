package com.qiusm.eju.crawler.parser.competitor.beike.app.deal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BkAppDealListSearch
        extends BkAppDealPageListSearch {

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONArray arrayResult = new JSONArray();
        if (!checkJsonError(mainJson)) {
            log.warn("{}", mainJson);
            return;
        }

        JSONArray listArray = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        listArray.forEach(listObj -> {
            JSONObject jsonObject = (JSONObject) listObj;
            String code = jsonObject.getString("house_code");
            String title = jsonObject.getString("title");
            String desc = jsonObject.getString("desc");
            String resblockId = jsonObject.getString("resblock_id");
            String strategyInfo = jsonObject.getString("strategy_info");

            JSONObject jsonResult = new JSONObject();
            jsonResult.putAll(requestDto.getData());
            jsonResult.put("house_code", jsonObject.getString("house_code"));
            jsonResult.put("title", title);
            jsonResult.put("desc", desc);
            jsonResult.put("resblock_id", resblockId);
            jsonResult.put("strategy_info", strategyInfo);
            jsonResult.put("unit_price_str", jsonObject.getString("unit_price_str"));
            jsonResult.put("ele_id", jsonObject.getString("eleid"));
            jsonResult.put("price_str", jsonObject.getString("price_str"));
            jsonResult.put("sign_date", jsonObject.getString("sign_date"));

            if (StringUtils.isNotBlank(desc)) {
                String[] split = desc.split("/");
                if (split.length == 4) {
                    String orientation = split[0];
                    jsonResult.put("orientation", orientation);

                    String floorHeight = split[1];
                    jsonResult.put("floor_height", floorHeight);

                    String totalFloor = split[2];
                    jsonResult.put("total_floor", totalFloor);

                    String area = split[3];
                    jsonResult.put("area", area);
                }
            }
            arrayResult.add(jsonResult);
        });

        responseDto.getResult().put("list", arrayResult);
    }
}
