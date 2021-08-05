package com.qiusm.eju.crawler.competitor.beike;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.bk.BkDeal;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.base.BkAppCityDictSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailPartSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealPageListSearch;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 成交测试
 *
 * @author qiushengming
 */
@Slf4j
@SpringBootTest
public class DealTest {


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
        String filePath = "source/beike/deal/2021.08.05112249";
        File file = new File(filePath);
        File[] files = file.listFiles();
        int count = 0;
        for (File file1 : files) {
            try {
                LineNumberReader lnr = new LineNumberReader(new FileReader(file1));
                // lnr.skip(Long.MAX_VALUE);
                String str = null;
                while ((str = lnr.readLine()) != null) {
                    count++;
                }
                lnr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count);
    }


    private final ThreadPoolExecutor bkDealDb = ThreadPoolUtils
            .newFixedThreadPool("bk-deal-db", 8, 20L);

    /**
     * 将数据存储到数据库中
     */
    @Test
    void toDb() {
        String filePath = "source/beike/deal/2021.08.05112249";
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            bkDealDb.submit(() -> {
                try {
                    LineNumberReader lnr = new LineNumberReader(new FileReader(file1));
                    String str;
                    int count = 0;
                    while ((str = lnr.readLine()) != null) {
                        count++;
                        try {
                            if (StringUtils.equals(str, "{}")) {
                                continue;
                            }

                            BkDeal deal = JSONObject.parseObject(str, BkDeal.class);
                            if (deal != null && deal.insert()) {

                            } else {
                                System.out.println(str);
                            }
                        } catch (Exception e) {
                            log.error("{},{}", count, str);
                            System.out.println(e.getMessage());
                        }
                    }
                    lnr.close();
                } catch (IOException e) {

                }
            });
        }

        bkDealDb.shutdown();

        while(true) {
            if(bkDealDb.isTerminated()) {
                break;
            }else {
                ThreadUtil.sleep(5000);
            }
        }
    }
}
