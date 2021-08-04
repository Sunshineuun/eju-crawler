package com.qiusm.eju.crawler.poi.gaode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.poi.gaode.GaodePoiPageListSearch;
import com.qiusm.eju.crawler.service.poi.gaode.impl.GaodeCrawlerService;
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

    @Resource
    GaodePoiPageListSearch poiPageListSearch;

    @Test
    void poi() {
        Map<String, String> params = new HashMap<>(1);
        params.put("location", "121.4730050,31.2005970");
        params.put("types", "150500");
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = poiPageListSearch.execute(requestDto);
        System.out.printf("poi:%s\n", responseDto);
    }
}
