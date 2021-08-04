package com.qiusm.eju.crawler.controller.ajk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.community.AjkAppCommunitySearch;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton.AjkAppFloor;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton.AjkAppHouse;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton.AjkAppUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

/**
 * @author qiushengming
 */
@Slf4j
@RestController
@RequestMapping("/ajk/skeleton")
public class AjkSkeletonController {

    @Resource
    private AjkAppCommunitySearch communitySearch;

    @Resource
    private AjkAppUnit unitService;

    @Resource
    private AjkAppFloor floorService;

    @Resource
    private AjkAppHouse houseService;

    @GetMapping("/start/{cityId}/{key}")
    public void start(@PathVariable String cityId, @PathVariable String key) {
//        String key = "慧智湖花园";
//        String cityId = "11";
        // 查询小区
        JSONArray communityArray = communitySearch(key, cityId);
        if (communityArray == null) {
            log.info("{},{},小区列表抓取失败.", cityId, key);
            return;
        }
        for (Object o : communityArray) {
            JSONObject community = (JSONObject) o;
            // 查询单元列表
            JSONArray unitArray = unitSearch(community);
            community.put("unit", unitArray);
            if (unitArray == null) {
                log.info("{},{},单元抓取失败,{}", cityId, key, community);
                continue;
            }
            for (Object o1 : unitArray) {
                JSONObject unit = (JSONObject) o1;
                unit.put(COMMUNITY_TYPE, community.get(COMMUNITY_TYPE));
                // 查询楼层好
                JSONArray floorArray = floorSearch(unit);
                unit.put("floor", floorArray);
                if (floorArray == null) {
                    log.info("{},{},楼层列表抓取失败 or 为空.{}", cityId, key, unit);
                    continue;
                }
                for (Object o2 : floorArray) {
                    JSONObject floor = (JSONObject) o2;
                    floor.put(COMMUNITY_TYPE, unit.get(COMMUNITY_TYPE));
                    floor.put(UNIT_ID, unit.getString(UNIT_ID));
                    JSONArray houseArray = houseSearch(floor);
                    floor.put("house", houseArray);
                    if (houseArray == null) {
                        log.info("{},{},楼层抓取失败 or 为空.{}", cityId, key, floor);
                    }
                }
            }
        }
        log.info("{},{},小区列表抓取结束.", cityId, key);
    }

    /**
     * 慧智湖花园；100043064；
     *
     * @return 小区列表
     */
    JSONArray communitySearch(String key, String cityId) {
        Map<String, String> params = new HashMap<>(4);
        params.put(COMMUNITY_SEARCH_KEY, key);
        params.put(CITY_ID, cityId);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = communitySearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    /**
     * 单元
     * 100043064
     * {"community_id":100043064,"name_alias":"2","name":"2","id":173686,"type":2}
     *
     * @return
     */
    JSONArray unitSearch(JSONObject json) {
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

        ResponseDto responseDto = unitService.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray floorSearch(JSONObject unit) {
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

        ResponseDto responseDto = floorService.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray houseSearch(JSONObject floor) {
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

        ResponseDto responseDto = houseService.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }
}
