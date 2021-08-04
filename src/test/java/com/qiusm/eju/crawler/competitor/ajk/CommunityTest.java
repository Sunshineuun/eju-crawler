package com.qiusm.eju.crawler.competitor.ajk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.community.AjkAppCommunitySearch;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton.AjkAppFloor;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton.AjkAppHouse;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton.AjkAppUnit;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

public class CommunityTest {
    public static void main(String[] args) {
        String key = "慧智湖花园";
        String cityId = "11";
        // 查询小区
        JSONArray communityArray = communitySearch(key, cityId);
        for (Object o : communityArray) {
            JSONObject community = (JSONObject) o;
            // 查询单元列表
            JSONArray unitArray = unitSearch(community);
            community.put("unit", unitArray);
            for (Object o1 : unitArray) {
                JSONObject unit = (JSONObject) o1;
                unit.put(COMMUNITY_TYPE, community.get(COMMUNITY_TYPE));
                // 查询楼层好
                JSONArray floorArray = floorSearch(unit);
                unit.put("floor", floorArray);
                for (Object o2 : floorArray) {
                    JSONObject floor = (JSONObject) o2;
                    floor.put(COMMUNITY_TYPE, unit.get(COMMUNITY_TYPE));
                    floor.put(UNIT_ID, unit.getString(UNIT_ID));
                    JSONArray houseArray = houseSearch(floor);
                    floor.put("house", houseArray);
                }
            }
        }
    }

    /**
     * 慧智湖花园；100043064；
     * @return 小区列表
     */
    static JSONArray communitySearch(String key, String cityId) {
        IHttpSearch httpSearch = new AjkAppCommunitySearch();
        Map<String, String> params = new HashMap<>(4);
        params.put(COMMUNITY_SEARCH_KEY, key);
        params.put(CITY_ID, cityId);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        System.out.printf("communitySearch:%s\n", responseDto.getResult());
        return responseDto.getResult().getJSONArray("list");
    }

    /**
     * 单元
     * 100043064
     * {"community_id":100043064,"name_alias":"2","name":"2","id":173686,"type":2}
     * @return
     */
    static JSONArray unitSearch(JSONObject json) {
        IHttpSearch httpSearch = new AjkAppUnit();
        Map<String, String> params = new HashMap<>(8);
        params.put(COMMUNITY_ID, json.getString(COMMUNITY_ID));
        params.put(COMMUNITY_TYPE, json.getString(COMMUNITY_NUMBER_TYPE));
        params.put(TYPE, "2");
        params.put(BUILDING_ID, "");
        params.put(UNIT_ID, "");
        params.put(PAGE, "1");
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .requestMethod(RequestMethodEnum.PROXY_POST_JSON)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        System.out.printf("buildingSearch:%s\n", responseDto);
        return responseDto.getResult().getJSONArray("list");
    }

    static JSONArray floorSearch(JSONObject unit) {
        IHttpSearch httpSearch = new AjkAppFloor();
        Map<String, String> params = new HashMap<>(8);
        params.put(COMMUNITY_ID, unit.getString(COMMUNITY_ID));
        params.put(COMMUNITY_TYPE, unit.getString(COMMUNITY_TYPE));
        params.put(TYPE, "3");
        params.put(BUILDING_ID, "");
        params.put(UNIT_ID, unit.getString(UNIT_ID));
        params.put(PAGE, "1");
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .requestMethod(RequestMethodEnum.PROXY_POST_JSON)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        System.out.printf("unitSearch:%s\n", responseDto);
        return responseDto.getResult().getJSONArray("list");
    }

    static JSONArray houseSearch(JSONObject floor) {
        IHttpSearch httpSearch = new AjkAppHouse();
        Map<String, String> params = new HashMap<>(8);
        params.put(COMMUNITY_ID, floor.getString(COMMUNITY_ID));
        params.put(COMMUNITY_TYPE, floor.getString(COMMUNITY_TYPE));
        params.put(BUILDING_ID, "");
        params.put(UNIT_ID, floor.getString(UNIT_ID));
        params.put(PAGE, "1");
        params.put(TYPE, "4");
        params.put(FLOOR_ID, floor.getString(FLOOR_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .requestMethod(RequestMethodEnum.PROXY_POST_JSON)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        System.out.printf("houseSearch:%s\n", responseDto);
        return responseDto.getResult().getJSONArray("list");
    }
}
