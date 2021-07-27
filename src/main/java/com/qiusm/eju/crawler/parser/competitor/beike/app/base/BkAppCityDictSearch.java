package com.qiusm.eju.crawler.parser.competitor.beike.app.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 贝壳城市区域板块地铁线路基本信息 <br>
 * 面向URL:https://m.ke.com/sh/dict/city?city_id=310000 <br>
 *
 * @author qiushengming
 */
@Service
public class BkAppCityDictSearch extends BkAppBaseSearch {


    private static final String CITY = "city";
    private static final String URL_TEMPLATE = "https://m.ke.com/%s/dict/city?city_id=%s";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(CITY)
                || !requestParam.containsKey(CITY_ID)) {
            throw new BusinessException(10000, "city or cityCode is null");
        }

        String url = String.format(URL_TEMPLATE, requestParam.get(CITY), requestParam.get(CITY_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject object = JSON.parseObject(requestDto.getResponseStr());
        JSONArray resultArray = new JSONArray();
        if (object != null) {
            String errno = object.getString("errno");
            String errmsg = object.getString("errmsg");
            if (StringUtils.equals(errno, "0")
                    && StringUtils.equals(errmsg, "success")) {
                JSONArray infoArray = object.getJSONObject("data").getJSONArray("info");
                infoArray.forEach(infoObj -> {
                    JSONObject info = (JSONObject) infoObj;
                    //获取城市code和城市名称
                    String cityId = info.getString("cityId");
                    String cityName = info.getString("cityName");

                    //获取区域
                    JSONArray districtArray = info.getJSONArray("district");

                    districtArray.forEach(districtObj -> {
                        JSONObject district = (JSONObject) districtObj;
                        String districtName = district.getString("districtName");
                        String districtId = district.getString("districtId");

                        if (!StringUtils.equals("不限", districtName)) {
                            JSONArray bizcircle = district.getJSONArray("bizcircle");
                            bizcircle.forEach(bizObj -> {
                                JSONObject biz = (JSONObject) bizObj;
                                String bizcircleName = biz.getString("bizcircleName");
                                if (!StringUtils.equals("不限", bizcircleName)) {

                                    String bizcircleId = biz.getString("bizcircleId");

                                    JSONObject data = new JSONObject();
                                    data.put("city_id", cityId);
                                    data.put("city_name", cityName);
                                    data.put("region", districtName);
                                    data.put("district_id", districtId);
                                    data.put("plate", bizcircleName);
                                    data.put("bizcircle_id", bizcircleId);
                                    data.put("limit_offset", "0");
                                    resultArray.add(data);
                                }
                            });
                        }
                    });
                });
            }
        }
        responseDto.getResult().put("list", resultArray);
    }

    @Override
    protected void buildingHeader(BkRequestDto dto) {
        Map<String, String> baseHead = new HashMap<>(16);
        baseHead.putAll(dto.getHead());

        dto.setHead(baseHead);

    }

}
