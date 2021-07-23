package com.qiusm.eju.crawler.competitor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.beike.app.base.BkAppCityDictSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailPartSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealPageListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstance;
import com.qiusm.eju.crawler.utils.DateUtils;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 成交
 *
 * @author qiushengming
 */
@Slf4j
@RestController
@RequestMapping("/bk/deal")
public class BeikeDealController extends BeiKeBaseController {

    @Resource(name = "bkAppDealPageListSearch")
    BkAppDealPageListSearch bkAppDealPageListSearch;
    @Resource
    BkAppDealListSearch bkAppDealListSearch;
    @Resource
    BkAppDealDetailSearch bkAppDealDetailSearch;
    @Resource
    BkAppDealDetailPartSearch bkAppDealDetailPartSearch;

    private final ThreadPoolExecutor bkDealExecutor = ThreadPoolUtils.newFixedThreadPool("bk-deal", 16, 20L);

    private CrawlerTaskInstance nowTask;

    @Override
    public void start(CrawlerTaskInstance crawlerTaskInstance) {
        this.nowTask = crawlerTaskInstance;
    }

    @GetMapping("/start/{city}/{cityId}")
    public void startA(
            @PathVariable String cityId,
            @PathVariable String city) {
        JSONArray bizArray = cityHandler(cityId, city);
        String filePath = "source\\beike\\deal\\" + DateUtils.formatDate(new Date(), "yyyy.MM.ddHHmmss");
        int count = 0;
        for (Object o1 : bizArray) {
            JSONArray pageListArray = pageListHandler((JSONObject) o1);
            if (pageListArray == null) {
                continue;
            }
            for (Object o2 : pageListArray) {
                JSONArray detailList = pageHandler((JSONObject) o2);
                if (detailList == null) {
                    continue;
                }
                for (Object o3 : detailList) {
                    if (count++ % 1000 == 0) {
                        log.info("成交数据，处理的数量：count={}。",
                                count);
                    }
                    bkDealExecutor.submit(() -> {
                        JSONObject detail = detailHandler((JSONObject) o3);
                        JSONObject detailPart = detail;
                        // detailPartHandler(detail);

                        if (detailPart != null) {
                            FileUtils.printFile(detailPart.toJSONString() + "\n", filePath,
                                    Thread.currentThread().getName() + ".txt", true);
                        }
                    });
                }
            }
        }
    }

    JSONArray pageListHandler(JSONObject biz) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", biz.getString("city_id"));
        params.put("city", biz.getString("city"));
        params.put("district_id", biz.getString("district_id"));
        params.put("bizcircle_id", biz.getString("bizcircle_id"));
        BkRequestDto requestDto = BkRequestDto.builder()
                .requestParam(params)
                .data(biz)
                .build();

        BkResponseDto responseDto = bkAppDealPageListSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray pageHandler(JSONObject page) {
        BkResponseDto responseDto = pageHandler1(page);
        JSONArray list = responseDto.getResult().getJSONArray("list");
        JSONArray requestList = responseDto.getResult().getJSONArray("request_list");
        if (requestList != null) {
            for (Object o : requestList) {
                BkResponseDto responseDto1 = pageHandler1((JSONObject) o);
                JSONArray list1 = responseDto1.getResult().getJSONArray("list");
                if (list1 != null) {
                    list.addAll(list1);
                }
            }
        }
        return list;
    }

    BkResponseDto pageHandler1(JSONObject page) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", page.getString("city_id"));
        params.put("city", page.getString("city"));
        params.put("district_id", page.getString("district_id"));
        params.put("bizcircle_id", page.getString("bizcircle_id"));
        if (page.containsKey("limit_offset")) {
            params.put("limit_offset", page.getString("limit_offset"));
        }
        if (page.containsKey("price_bp")) {
            params.put("price_bp", page.getString("price_bp"));
        }
        if (page.containsKey("price_ep")) {
            params.put("price_ep", page.getString("price_ep"));
        }

        BkRequestDto requestDto = BkRequestDto.builder()
                .requestParam(params)
                .data(page)
                .build();

        return bkAppDealListSearch.execute(requestDto);
    }

    JSONObject detailHandler(JSONObject detail) {
        Map<String, String> params = new HashMap<>(8);
        params.put("house_code", detail.getString("house_code"));
        params.put("strategy_info", detail.getString("strategy_info"));
        BkRequestDto requestDto = BkRequestDto.builder()
                .requestParam(params)
                .data(detail)
                .build();

        BkResponseDto responseDto = bkAppDealDetailSearch.execute(requestDto);

        return responseDto.getResult();
    }

    JSONObject detailPartHandler(JSONObject detail) {
        if (detail == null) {
            return null;
        }

        Map<String, String> params = new HashMap<>();
        params.put("house_code", detail.getString("house_code"));
        BkRequestDto requestDto = BkRequestDto.builder()
                .requestParam(params)
                .data(detail)
                .build();

        BkResponseDto responseDto = bkAppDealDetailPartSearch.execute(requestDto);
        return responseDto.getResult();
    }
}
