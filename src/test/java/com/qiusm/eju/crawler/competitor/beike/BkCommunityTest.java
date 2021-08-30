package com.qiusm.eju.crawler.competitor.beike;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.EjuCrawlerApplicationTests;
import com.qiusm.eju.crawler.service.bk.IBkCityInoService;
import com.qiusm.eju.crawler.service.bk.IBkCommunityService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class BkCommunityTest extends EjuCrawlerApplicationTests {

    @Resource
    private IBkCommunityService bkCommunityService;
    @Resource
    private IBkCityInoService bkCityInoService;

    @Test
    public void communityList() {
        String cityId = "320100";
        String city = "hz";
        JSONArray bizArray = bkCityInoService.getBizByCity(cityId, city);

        for (Object o1 : bizArray) {
            JSONArray communityList = bkCommunityService.communityList((JSONObject) o1);
            for (Object o2 : communityList) {
                JSONObject var1 = (JSONObject) o2;
                // 将小区信息存储到数据库中去

            }
        }
    }

}
