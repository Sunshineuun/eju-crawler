package com.qiusm.eju.crawler.service.bk.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.bk.BkDeal;
import com.qiusm.eju.crawler.entity.bk.BkDealTask;
import com.qiusm.eju.crawler.enums.CommunitySkeletonTaskStateEnum;
import com.qiusm.eju.crawler.mapper.bk.BkDealTaskMapper;
import com.qiusm.eju.crawler.parser.competitor.beike.app.base.BkAppCityDictSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailPartSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealDetailSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.deal.BkAppDealPageListSearch;
import com.qiusm.eju.crawler.service.bk.IBkDealTaskService;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.CITY_ID;

/**
 * @author qiushengming
 * @date 2021.8.24
 */
@Slf4j
@Service
public class BkDealServiceImpl
        extends ServiceImpl<BkDealTaskMapper, BkDealTask>
        implements IBkDealTaskService, CommandLineRunner, InitializingBean {

    @Resource(name = "bkAppDealPageListSearch")
    private BkAppDealPageListSearch bkAppDealPageListSearch;
    @Resource
    private BkAppDealListSearch bkAppDealListSearch;
    @Resource
    private BkAppDealDetailSearch bkAppDealDetailSearch;
    @Resource
    private BkAppDealDetailPartSearch bkAppDealDetailPartSearch;
    @Resource
    private BkAppCityDictSearch bkAppCityDictSearch;
    @Resource
    private ListOperations<String, String> listOperations;

    @Value("${eju.bk.skeleton.threadNum:8}")
    private Integer threadNum;
    final private ThreadsUtils urlThreadsUtils = new ThreadsUtils();
    private ThreadPoolExecutor bkDealExecutor;


    // @Scheduled(cron = "0 */30 * * * ?")
    private synchronized void scheduledTasks() {
        EntityWrapper<BkDealTask> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id");
        List<BkDealTask> task = this.selectList(wrapper);
        task.forEach(this::dealHandler);
    }

    private void dealHandler(BkDealTask task) {
        int count = 0;
        int totalCount = 0;

        JSONArray bizArray = cityHandler(task.getCityId(), task.getCity());
        for (Object o1 : bizArray) {
            JSONArray pageListArray = pageListHandler((JSONObject) o1);
            if (pageListArray == null) {
                continue;
            }
            totalCount += ((JSONObject) o1).getInteger("total_count");
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

                        if (detailPart != null && StringUtils.equals("3", task.getState())) {
                            BkDeal deal = JSONObject.parseObject(detailPart.toJSONString(), BkDeal.class);
                            deal.insert();
                        }
                    });
                }
            }
        }
        String desc = String.format("总计数量:%s,采集数量:%s", totalCount, count);
        task.setStateEnum(CommunitySkeletonTaskStateEnum.COMPLETE);

        task.setDesc(desc);
        task.updateById();
    }

    private JSONArray cityHandler(String cityId, String city) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", cityId);
        params.put("city", city);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = bkAppCityDictSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    private JSONArray pageListHandler(JSONObject biz) {
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
        biz.put("total_count", responseDto.getResult().getInteger("total_count"));
        return responseDto.getResult().getJSONArray("list");
    }

    private JSONArray pageHandler(JSONObject page) {
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

    private ResponseDto pageHandler1(JSONObject page) {
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

    private JSONObject detailHandler(JSONObject detail) {
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

    private JSONObject detailPartHandler(JSONObject detail) {
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
    public void run(String... args) throws Exception {
        scheduledTasks();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        bkDealExecutor = ThreadPoolUtils.newFixedThreadPool("bk-deal", threadNum, 20L);
    }
}
