package com.qiusm.eju.crawler.controller.ajk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.qiusm.eju.crawler.entity.ajk.AjkArea;
import com.qiusm.eju.crawler.entity.base.Community;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.mapper.ajk.AjkAreaMapper;
import com.qiusm.eju.crawler.service.ajk.IAjkCommunityService;
import com.qiusm.eju.crawler.service.base.ICommunityService;
import com.qiusm.eju.crawler.utils.ThreadUtils;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import com.qiusm.eju.crawler.utils.ajk.AjkUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.qiusm.eju.crawler.utils.lang.DateUtils;
import com.qiusm.eju.crawler.utils.lang.FileUtils;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL1;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;
import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

@Slf4j
@RestController
@RequestMapping("/ajk/community")
public class AjkCommunityController {
    @Resource
    private IAjkCommunityService ajkCommunityService;
    @Resource
    private AjkAreaMapper ajkAreaDao;
    @Resource
    private ICommunityService communityService;
    @Resource
    private HashOperations<String, String, String> hashOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    private final ThreadsUtils threadsUtils = new ThreadsUtils();

    @GetMapping("/all")
    public void communityAll() {
        List<AjkArea> areas = ajkAreaDao.selectList(null);
        final String key = "crawler:community:ajk";
        List<Integer> future = threadsUtils.executeFutures(areas,
                (var) -> {
                    String value = hashOperations.get(key, String.valueOf(var.getId()));
                    if (!StringUtils.equals("0", value) && value != null) {
                        return 0;
                    }

                    JSONObject areaJson = new JSONObject();
                    areaJson.put(AREA_ID, var.getAreaId());
                    areaJson.put(AREA_NAME, var.getAreaName());
                    areaJson.put(CITY_ID, var.getCityId());
                    areaJson.put("city", var.getCity());
                    JSONArray result = ajkCommunityService.communityList(areaJson);
                    hashOperations.put(key,
                            String.valueOf(var.getId()), String.valueOf(result.size()));
                    return 1;
                }, 8);
    }

    @GetMapping("/detail/all")
    public void communityDetail() {
        String rk = "crawler:community:detail:ajk";
        AtomicInteger lock = new AtomicInteger(0);
        // 0. 判断key是否存在，如果存在则不推送
        threadsUtils.getExecutorService(16).submit(() -> {
            if (listOperations.size(rk) == null
                    || listOperations.size(rk) == 0) {
                // 1. 将69w小区推送到redis中
                int page = 1;
                do {
                    Page<Community> communityPage = communityService.getCommunityAjkNotDetail(page);
                    communityPage.getRecords().forEach(c -> listOperations.leftPush(rk, JSONObject.toJSONString(c)));
                    if (communityPage.getSize() <= 0) {
                        break;
                    } else {
                        page++;
                    }
                    lock.getAndIncrement();
                } while (true);
            }
        });

        while (listOperations.size(rk) <= 0) {
            ThreadUtils.sleep(10);
        }

        // 2. 从redis中消费数据
        // 完成 数量统计
        AtomicInteger communityFinish = new AtomicInteger();
        List<String> list = new ArrayList<>(Arrays.asList("1234567890ABCDEFG".split("")));
        List<String> futures = threadsUtils.executeFutures(list, (e) -> {
            String communityStr;
            do {
                communityStr = (String) listOperations.leftPop(rk);
                Community community = JSONObject.parseObject(communityStr, Community.class);
                if (community != null) {
                    ajkCommunityService.communityDetail(community);
                    community.updateById();
                }
                if (communityFinish.getAndIncrement() % 1000 == 0) {
                    log.info("目前所处位置：{}", communityFinish.get());
                }
            } while (StringUtils.isNotBlank(communityStr));

            return e;
        });
        futures.forEach(System.out::println);
    }

    @GetMapping("/price")
    public void communityPriceHistory(String citys) {
        for (String c : citys.split(",")) {
            List<Community> communityList = communityService.getCommunityByCity(c, SourceTypeEnum.AJK.getCode());
            ThreadsUtils threadUtils = new ThreadsUtils();
            final String key = "crawler:community:price:ajk_" + c;
            log.info("{},共计小区数量：{}", c, communityList.size());
            threadUtils.executeFutures(communityList, (community) -> {
                // 判断当前key的hash中是否有对应的key，有则return
                String value = hashOperations.get(key, community.getCommunityId());
                if (StringUtils.isNotBlank(value)) {
                    log.info("已执行：{},{}", community.getCommunity(), community.getCommunityId());
                    return null;
                } else {
                    long s = DateUtils.getCurrentTimeMillis();
                    communityAvgPrice(community);
                    long e = DateUtils.getCurrentTimeMillis();
                    log.info("执行结束:{},{},耗时:{}", community.getCommunity(), community.getCommunityId(), e - s);
                    hashOperations.put(key, community.getCommunityId(), community.getCommunity());
                }
                return null;
            }, 16);

        }
    }

    private final OkHttpUtils httpUtils = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL1).charset(UTF8)
            .connectTimeout(60000).readTimeout(60000)
            .retryMax(10)
            .proxyRetryTag(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=,枌~怣秙".split(COMMA)))
            .builderHttp();

    public void communityAvgPrice(Community community) {
        String url = String.format("https://api.anjuke.com/mobile/v5/sale/price/trend/report?id=%s&type=4&app=a-ajk&cv=15.13",
                community.getCommunityId());
        Map<String, String> map = new HashMap<>();
        String jsonStr;
        try {
            map.put("memberid", "0");
            map.put("clouduid", "0");
            map.put("user-agent", "a-ajk/15.13/Android-MI 6/android/9");
            map.put("nsign", AjkUtils.nsignOfGet(url));
            jsonStr = httpUtils.proxyGet(url, null, map);
            FileUtils.printFile(jsonStr, "source/ajk", String.format("community_price_history_%s.txt", community.getCity()), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
