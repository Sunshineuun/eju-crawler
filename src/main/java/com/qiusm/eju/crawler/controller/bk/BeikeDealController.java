package com.qiusm.eju.crawler.controller.bk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.bk.BkDeal;
import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailPartSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealPageListSearch;
import com.qiusm.eju.crawler.utils.DateUtils;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import static com.qiusm.eju.crawler.constant.CrawlerDataPathConstant.SOURCE_BK_DEAL;
import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.CITY_ID;

/**
 * 成交
 *
 * @author qiushengming
 */
@Api(tags = {"BeikeDeal", "bk成交数据"})
@Slf4j
@RestController
@RequestMapping("/bk/deal")
public class BeikeDealController extends BeiKeBaseController implements InitializingBean {

    @Value("${eju.bk.deal.threadNum:8}")
    private Integer threadNum;

    @Resource(name = "bkAppDealPageListSearch")
    BkAppDealPageListSearch bkAppDealPageListSearch;
    @Resource
    BkAppDealListSearch bkAppDealListSearch;
    @Resource
    BkAppDealDetailSearch bkAppDealDetailSearch;
    @Resource
    BkAppDealDetailPartSearch bkAppDealDetailPartSearch;

    private ThreadPoolExecutor bkDealExecutor;

    private CrawlerTaskInstance nowTask;

    @Override
    public void start(CrawlerTaskInstance crawlerTaskInstance) {
        this.nowTask = crawlerTaskInstance;
    }

    /**
     * @param cityId 城市ID
     * @param city   城市简拼
     * @param isDb   是否将结果持久化到数据库
     */
    @GetMapping("/start/{city}/{cityId}")
    public void startA(
            @PathVariable String cityId,
            @PathVariable String city,
            @RequestParam(required = false, defaultValue = "0") String isDb) {
        JSONArray bizArray = cityHandler(cityId, city);
        String filePath = SOURCE_BK_DEAL + DateUtils.formatDate(new Date(), "yyyy.MM.ddHHmmss");
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
                            if (StringUtils.equals("1", isDb)) {
                                BkDeal deal = JSONObject.parseObject(detailPart.toJSONString(), BkDeal.class);
                                deal.insert();
                            }
                        }
                    });
                }
            }
        }

        log.info("city:{},cityId:{},总计数量:{}", city, cityId, count);
    }

    JSONArray pageListHandler(JSONObject biz) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", biz.getString("city_id"));
        params.put("city", biz.getString("city"));
        params.put("district_id", biz.getString("district_id"));
        params.put("bizcircle_id", biz.getString("bizcircle_id"));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(biz)
                .build();

        ResponseDto responseDto = bkAppDealPageListSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray pageHandler(JSONObject page) {
        ResponseDto responseDto = pageHandler1(page);
        JSONArray list = responseDto.getResult().getJSONArray("list");
        JSONArray requestList = responseDto.getResult().getJSONArray("request_list");
        if (requestList != null) {
            for (Object o : requestList) {
                ResponseDto responseDto1 = pageHandler1((JSONObject) o);
                JSONArray list1 = responseDto1.getResult().getJSONArray("list");
                if (list1 != null) {
                    list.addAll(list1);
                }
            }
        }
        return list;
    }

    ResponseDto pageHandler1(JSONObject page) {
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

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(page)
                .build();

        return bkAppDealListSearch.execute(requestDto);
    }

    JSONObject detailHandler(JSONObject detail) {
        Map<String, String> params = new HashMap<>(8);
        params.put("house_code", detail.getString("house_code"));
        params.put("strategy_info", detail.getString("strategy_info"));
        params.put(CITY_ID, detail.getString(CITY_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(detail)
                .build();

        ResponseDto responseDto = bkAppDealDetailSearch.execute(requestDto);

        return responseDto.getResult();
    }

    JSONObject detailPartHandler(JSONObject detail) {
        if (detail == null) {
            return null;
        }

        Map<String, String> params = new HashMap<>();
        params.put("house_code", detail.getString("house_code"));
        params.put(CITY_ID, detail.getString(CITY_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(detail)
                .build();

        ResponseDto responseDto = bkAppDealDetailPartSearch.execute(requestDto);
        return responseDto.getResult();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        bkDealExecutor = ThreadPoolUtils.newFixedThreadPool("bk-deal", threadNum, 20L);
    }
}
