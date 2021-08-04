package com.qiusm.eju.crawler.competitor.beike;

import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.base.BkAppCityDictSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailPartSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealPageListSearch;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 成交测试
 *
 * @author qiushengming
 */
public class DealTest extends BkTest {


    public static void main(String[] args) {
        statisticalData();
    }

    static void city() {
        IHttpSearch httpSearch = new BkAppCityDictSearch();
        Map<String, String> params = new HashMap<>();
        params.put("city_id", "310000");
        params.put("city", "sh");
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("city:%s\n", responseDto);
    }

    static void pageList() {
        IHttpSearch httpSearch = new BkAppDealPageListSearch();
        Map<String, String> params = new HashMap<>();
        params.put("city_id", "310000");
        params.put("city", "sh");
        params.put("district_id", "310101");
        params.put("bizcircle_id", "611100441");
        Map<String, Object> data = new HashMap<>();
        data.put("plate", "打浦桥");
        data.put("region", "黄浦");
        data.putAll(params);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(data)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("pageList:%s\n", responseDto);
    }

    static void list() {
        IHttpSearch httpSearch = new BkAppDealListSearch();
        Map<String, String> params = new HashMap<>();
        params.put("city_id", "310000");
        params.put("city", "sh");
        params.put("district_id", "310101");
        params.put("bizcircle_id", "611100441");
        params.put("limit_offset", "100");
        Map<String, Object> data = new HashMap<>();
        data.put("plate", "打浦桥");
        data.put("region", "黄浦");
        data.putAll(params);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(data)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("list:%s\n", responseDto);
    }

    static void detail() {
        IHttpSearch httpSearch = new BkAppDealDetailSearch();
        Map<String, String> params = new HashMap<>();
        params.put("house_code", "107102501281");
        params.put("strategy_info", "{\"fb_query_id\":\"469879021158055936\",\"fb_expo_id\":\"469879021363576932\",\"fb_item_location\":\"99\",\"fb_service_id\":\"1011710018\",\"fb_ab_test_flag\":\"\",\"fb_item_id\":\"107102501281\"}");
        Map<String, Object> data = new HashMap<>();
        data.put("city_id", "310000");
        data.put("city", "sh");
        data.put("district_id", "310101");
        data.put("bizcircle_id", "611100441");
        data.put("limit_offset", "100");
        data.put("plate", "打浦桥");
        data.put("region", "黄浦");
        data.putAll(params);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(data)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("detail:%s\n", responseDto);
    }

    static void detailPart() {
        IHttpSearch httpSearch = new BkAppDealDetailPartSearch();
        Map<String, String> params = new HashMap<>();
        params.put("house_code", "107102501281");
        Map<String, Object> data = new HashMap<>();
        data.put("city_id", "310000");
        data.put("city", "sh");
        data.put("district_id", "310101");
        data.put("bizcircle_id", "611100441");
        data.put("limit_offset", "100");
        data.put("plate", "打浦桥");
        data.put("region", "黄浦");
        data.put("strategy_info", "{\"fb_query_id\":\"469879021158055936\",\"fb_expo_id\":\"469879021363576932\",\"fb_item_location\":\"99\",\"fb_service_id\":\"1011710018\",\"fb_ab_test_flag\":\"\",\"fb_item_id\":\"107102501281\"}");
        data.putAll(params);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(data)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("detailPart:%s\n", responseDto);
    }

    /**
     * 统计数据
     */
    static void statisticalData() {
        String filePath = "source/beike/deal/2021.07.29100240";
        File file = new File(filePath);
        File[] files = file.listFiles();
        int count = 0;
        for (File file1 : files) {
            String str = FileUtils.readFile(file1);
            try {
                LineNumberReader lnr = new LineNumberReader(new FileReader(file1));
                lnr.skip(Long.MAX_VALUE);
                count += lnr.getLineNumber() + 1;
                lnr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count);
    }
}
