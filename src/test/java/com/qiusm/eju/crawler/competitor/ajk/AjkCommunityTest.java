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
import com.qiusm.eju.crawler.service.ajk.IAjkCommunityService;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.ajk.AjkUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

@Slf4j
@SpringBootTest
public class AjkCommunityTest {

    @Resource
    private AjkAppCommunitySearch communitySearch;

    @Resource
    private AjkAppUnit unitService;

    @Resource
    private AjkAppFloor floorService;

    @Resource
    private AjkAppHouse houseService;

    @Test
    public void skeTest() {
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
        System.out.printf("communitySearch:%s\n", responseDto.getResult());
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
        System.out.printf("buildingSearch:%s\n", responseDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray floorSearch(JSONObject unit) {
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

        ResponseDto responseDto = floorService.execute(requestDto);
        System.out.printf("unitSearch:%s\n", responseDto);
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
        System.out.printf("houseSearch:%s\n", responseDto);
        return responseDto.getResult().getJSONArray("list");
    }

    @Resource
    private IAjkCommunityService ajkCommunityService;

    @Test
    void communityList() {
        String areasStr = "1806,滨江#1804,西湖#1801,拱墅#1803,上城#1807,萧山#1808,余杭#21429,临平#20069,钱塘#1810,富阳#1809,临安#1813,淳安#1811,建德#1812,桐庐";
        for (String area : areasStr.split("#")) {
            String[] var1 = area.split(",");
            JSONObject areaJson = new JSONObject();
            areaJson.put(AREA_ID, var1[0]);
            areaJson.put(AREA_NAME, var1[1]);
            areaJson.put(CITY_ID, "18");
            areaJson.put(CITY_NAME, "杭州");
            JSONArray result = ajkCommunityService.communityList(areaJson);
            result.forEach(o -> {
                FileUtils.printFile(((JSONObject) o).toJSONString() + "\n", "source/ajk", "hangzhou_community_list.txt", true);
            });
        }
    }

    public static void main(String[] args) {
        communityListA();
    }

    static void communityListA() {
        OkHttpUtils httpClient = OkHttpUtils.Builder()
                .builderHttp();

        //String url = "https://api.anjuke.com/mobile/v5/sale/property/list?page=1&area_id=1806&city_id=18&page_size=100&app=a-ajk&macid=e101a87abcf15692&cv=15.13";
        // String url = "https://api.anjuke.com/mobile/v5/sale/property/list?page=36&city_id=18&page_size=100&app=a-ajk&macid=e101a87abcf15692&cv=15.13&area_id=1809";
        String url = "https://api.anjuke.com/community/list?page=1&area_id=19&city_id=11&page_size=100&app=a-ajk";
        String jsonStr;
        Map<String, String> map = new HashMap<>();
        try {
            map.put("memberid", "0");
            map.put("clouduid", "0");
            map.put("user-agent", "a-ajk/15.13/Android-MI 6/android/9");
            map.put("nsign", AjkUtils.nsignOfGet(url));
            jsonStr = httpClient.proxyGet(url, null, map);
            System.out.println(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
