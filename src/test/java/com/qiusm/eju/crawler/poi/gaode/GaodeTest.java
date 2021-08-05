package com.qiusm.eju.crawler.poi.gaode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.poi.gaode.GaodePoiPageListSearch;
import com.qiusm.eju.crawler.service.poi.gaode.impl.GaodeCrawlerService;
import com.qiusm.eju.crawler.utils.JSONUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.qiusm.eju.crawler.utils.poi.GaodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 高德POI测试
 *
 * @author qiushengming
 */
@Slf4j
@SpringBootTest
public class GaodeTest {
    static GaodeCrawlerService service = new GaodeCrawlerService();

    public static void main(String[] args) {
        updateTest();
    }

    /**
     * 1. 测试当key不存在的时候，会是什么情况。
     */
    public static void testJson() {
        String json = "{  }";
        JSONObject var = JSONObject.parseObject(json);
        JSONArray var1 = var.getJSONObject("suggestion").getJSONArray("keywords");
        var.getJSONObject("suggestion").getJSONArray("keywords").forEach(o -> {
            System.out.println(o);
        });
    }

    public static void poiUrl() {
        String url = "http://restapi.amap.com/v3/place/around?location=121.520873,31.477357&keywords=&types=&radius=1500&offset=50&extensions=all&page=1";
        System.out.println(GaodeUtils.packageUrl(url));
    }

    @Resource
    GaodePoiPageListSearch poiPageListSearch;

    @Test
    void pois() {
        for (int i = 1; i < 100000; i++) {
            if (!poi(i)) {
                return;
            }
        }
    }

    /**
     * 获取小区；
     * url：http://172.30.4.75:8907/communityMonReport/getCommunityMonReportFacilitate
     * 更新提交结果；
     * url：http://172.30.4.75:8907/communityMonReport/updateCommunityMonReportFacilitate
     */
    boolean poi(int pageNum) {
        JSONObject mainJson = getCommunityList(pageNum);
        // 要处理的小区列表
        JSONArray communityList = JSONUtils.getJsonArrayByKey(mainJson, "resultVo.list");
        if (communityList == null || communityList.size() == 0) {
            return false;
        }
        // 要处理的类型列表
        JSONArray typeList = JSONUtils.getJsonArrayByKey(mainJson, "resultVo.type");

        // 返回的结果
        JSONArray communityResultArr = new JSONArray();
        communityList.forEach(o -> {
            JSONObject community = (JSONObject) o;

            JSONObject communityResult = new JSONObject();
            communityResult.put("communityId", community.get("communityId"));
            communityResult.put("status", 0);

            JSONArray communityTypeArr = new JSONArray();

            typeList.forEach(typeObj -> {
                JSONObject typeJson = (JSONObject) typeObj;
                Map<String, String> params = new HashMap<>(1);
                params.put("location", community.getString("communityCoordinate"));
                params.put("types", typeJson.getString("types"));
                params.put("radius", typeJson.getString("radius"));
                RequestDto requestDto = RequestDto.builder()
                        .requestParam(params)
                        .build();

                ResponseDto responseDto = poiPageListSearch.execute(requestDto);

                JSONObject typeResult = new JSONObject();
                typeResult.putAll(typeJson);
                // 拿到总数
                typeResult.put("count", responseDto.getResult().get("totalNum"));

                // 只获取两个
                JSONArray list = responseDto.getResult().getJSONArray("list");
                if (list != null) {
                    JSONArray resultArr = new JSONArray();
                    int count = 0;
                    for (Object o1 : list) {
                        if (count >= 2) {
                            break;
                        }
                        count++;
                        JSONObject var1 = (JSONObject) o1;
                        JSONObject result = new JSONObject();
                        result.put("name", var1.get("name"));
                        result.put("address", var1.get("address"));
                        result.put("distance", var1.get("distance"));
                        result.put("location", var1.get("location"));
                        resultArr.add(result);
                    }
                    typeResult.put("result", resultArr);
                }
                communityTypeArr.add(typeResult);
            });
            communityResult.put("result", JSONObject.toJSONString(communityTypeArr));
            communityResultArr.add(communityResult);
            //updateCommunityList(communityResult);
        });

        updateCommunityList(communityResultArr);
        return true;
    }

    static OkHttpUtils httpUtils = OkHttpUtils.Builder().charset("utf-8").builderHttp();

    /**
     * 获取小区列表，类型列表
     *
     * @param pageNum 当前页面
     * @return
     */
    static JSONObject getCommunityList(int pageNum) {
        // 获取小区列表
        String url = "http://172.30.4.75:8907/inner/communityMonReport/getCommunityMonReportFacilitate";
        Map<String, Object> param = new HashMap<>();
        param.put("pageNum", pageNum);
        param.put("pageSize", 100);

        String responseStr = httpUtils.postJson(url, JSONObject.toJSONString(param));
        return JSONObject.parseObject(responseStr);
    }

    /**
     * 更新小区列表
     *
     * @param json
     */
    static void updateCommunityList(JSON json) {
        String url = "http://172.30.4.75:8907/inner/communityMonReport/updateCommunityMonReportFacilitate";
        String responseStr = httpUtils.postJson(url, "utf-8", JSONObject.toJSONString(json));
        log.info("{}", responseStr);
    }

    static void updateTest() {
        JSONObject json = new JSONObject();
        json.put("communityId", "1234");
        json.put("status", 0);
        json.put("result", "1");
        updateCommunityList(json);
    }
}
