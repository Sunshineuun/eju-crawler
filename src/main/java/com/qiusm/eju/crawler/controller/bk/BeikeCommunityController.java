package com.qiusm.eju.crawler.controller.bk;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.base.City;
import com.qiusm.eju.crawler.service.base.ICityService;
import com.qiusm.eju.crawler.service.bk.IBkCityInoService;
import com.qiusm.eju.crawler.service.bk.IBkCommunityService;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.COMMUNITY_ID;

@Slf4j
@RestController
@RequestMapping("/bk/community/")
public class BeikeCommunityController {

    @Resource
    private IBkCommunityService bkCommunityService;
    @Resource
    private IBkCityInoService bkCityInoService;
    @Resource
    private ICityService cityService;
    @Resource
    private ListOperations<String, String> listOperations;
    private final String communityListKey = "eju-crawler:bk:community:list";
    private final ThreadsUtils threadsUtils = new ThreadsUtils();

    @GetMapping("/list/{cityCode}")
    public void communityList(@PathVariable String cityCode) {
        City city = cityService.selectByBkCityCode(cityCode);
        if (city == null) {
            log.error("贝壳中无该编码城市：{}", cityCode);
            return;
        }
        JSONArray bizArray = bkCityInoService.getBizByCity(city.getBkCode(), city.getBkPinyinCode());
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
            String bizcircleId = "";
            if (StringUtils.equals(city.getBkCode(), "330100")) {
                bizcircleId = bizJson.getString("bizcircle_id");
                bizJson.remove("bizcircle_id");
            }

            JSONArray communityList = bkCommunityService.communityList(bizJson);
            for (Object o : communityList) {
                JSONObject community = (JSONObject) o;
                community.put("bizcircle_id", bizcircleId);
                community.put("city", city.getCity());
                community.put("city_code", city.getBkCode());
                listOperations.leftPush(communityListKey, JSONObject.toJSONString(community));
            }
            return null;
        }, 8);
    }

    @GetMapping("detail")
    public void communityDetail() {
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
