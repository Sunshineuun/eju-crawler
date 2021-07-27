package com.qiusm.eju.crawler.poi.gaode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 高德POI测试
 *
 * @author qiushengming
 */
public class GaodeTest {
    static GaodeCrawlerService service = new GaodeCrawlerService();

    public static void main(String[] args) {
        poiUrl();
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
}
