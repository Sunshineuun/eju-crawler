package com.qiusm.eju.crawler.competitor.beike;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.EjuCrawlerApplicationTests;
import com.qiusm.eju.crawler.entity.base.CommunityInfo;
import com.qiusm.eju.crawler.service.bk.IBkCityInoService;
import com.qiusm.eju.crawler.service.bk.IBkCommunityService;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.ListOperations;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.COMMUNITY_ID;

public class BkCommunityTest extends EjuCrawlerApplicationTests {

    @Resource
    private IBkCommunityService bkCommunityService;
    @Resource
    private IBkCityInoService bkCityInoService;

    @Test
    public void communityList() {
        String cityId = "330100";
        String city = "hz";
        JSONArray bizArray = bkCityInoService.getBizByCity(cityId, city);

        for (Object biz : bizArray) {
            JSONArray communityList = bkCommunityService.communityList((JSONObject) biz);
            for (Object o2 : communityList) {
                JSONObject var = (JSONObject) o2;
                // 将小区信息存储到数据库中去
                var.put("source", "BK");
                CommunityInfo communityInfo = JSONObject.parseObject(var.toJSONString(), CommunityInfo.class);
                communityInfo.insert();
            }
        }
    }

    @Resource
    private ListOperations<String, String> listOperations;
    private String communityListKey = "eju-crawler:bk:community:list";
    private ThreadsUtils threadsUtils = new ThreadsUtils();

    @Test
    public void communityListHz() {
        String cityId = "330100";
        String city = "hz";
        JSONArray bizArray = bkCityInoService.getBizByCity(cityId, city);
        Set<String> disSet = new ConcurrentHashSet<>();
        listOperations.getOperations().delete(communityListKey);
        threadsUtils.executeFutures(bizArray, (e) -> {
            JSONObject bizJson = (JSONObject) e;
            String districtId = bizJson.getString("district_id");
            if (disSet.contains(districtId)) {
                return null;
            } else {
                disSet.add(districtId);
            }
            String bizcircleId = bizJson.getString("bizcircle_id");
            bizJson.remove("bizcircle_id");

            JSONArray communityList = bkCommunityService.communityList(bizJson);
            communityList.forEach(o -> {
                JSONObject community = (JSONObject) o;
                community.put("bizcircle_id", bizcircleId);
                community.put("city", "杭州");
                community.put("city_code", cityId);
                listOperations.leftPush(communityListKey, JSONObject.toJSONString(community));
            });
            return null;
        }, 8);
    }

    @Test
    public void communityDetail() {
        communityListHz();
        Set<String> communityIdSet = new ConcurrentHashSet<>();

        int threadNum = 8;
        threadsUtils.executeFutures(IntStream.range(1, threadNum).boxed().collect(Collectors.toList()),
                (e) -> {
                    Long size;
                    while ((size = listOperations.size(communityListKey)) != null
                            && size != 0) {

                        String jsonStr = listOperations.leftPop(communityListKey);
                        JSONObject community = JSONObject.parseObject(jsonStr);

                        String communityId = community.getString(COMMUNITY_ID);
                        if (!communityIdSet.contains(communityId)) {
                            communityIdSet.add(communityId);
                        } else {
                            continue;
                        }
                        boolean success = bkCommunityService.communityDetail(community);
                        if (!success && jsonStr != null) {
                            listOperations.rightPush(communityListKey, jsonStr);
                        }
                    }
                    return null;
                }, 8);
    }

}
