package com.qiusm.eju.crawler.controller.bk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.CommunitySkeletonTask;
import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityPageListSearch;
import com.qiusm.eju.crawler.service.base.ICommunitySkeletonTaskService;
import com.qiusm.eju.crawler.service.bk.IBkSkeletonService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.*;


/**
 * 骨架数据抓取
 *
 * @author qiushengming
 */
@Api(tags = {"BeikeSkeleton", "bk骨架数据抓取"})
@Slf4j
@RestController
@RequestMapping("/bk/skeleton")
public class BeikeSkeletonController extends BeiKeBaseController {

    @Value("${eju.bk.skeleton.threadNum:8}")
    private Integer threadNum;

    @Resource(name = "bkAppCommunityPageListSearch")
    private BkAppCommunityPageListSearch communityPageListSearch;
    @Resource
    private BkAppCommunityListSearch communityListSearch;
    @Resource
    private ICommunitySkeletonTaskService communitySkeletonTaskService;
    @Resource
    private IBkSkeletonService bkSkeletonService;

    /**
     * 用于存储cityId，如果运行中的任务存在列表中，则表示已经在执行了。
     */
    private final Set<String> cityIdSet = new HashSet<>();

    @Override
    public void start(CrawlerTaskInstance crawlerTaskInstance) {
    }

    /**
     * 获取板块信息
     */
    @GetMapping("/start")
    public void start() {
        bkSkeletonService.scheduledTasks();
    }

    @GetMapping("/communityList/{city}/{cityId}")
    public void communityList(
            @PathVariable String cityId,
            @PathVariable String city,
            @RequestParam(required = false, defaultValue = "0") Integer isPrintCommunitDetail) {

        synchronized (cityIdSet) {
            if (!cityIdSet.contains(cityId)) {
                cityIdSet.add(cityId);
            } else {
                log.info("{},{},已经在抓取了。", city, cityId);
                return;
            }
        }

        JSONArray bizArray = cityHandler(cityId, city);
        int count = 0;

        for (Object o1 : bizArray) {
            // 小区页面列表
            JSONArray pageListArray = pageListHandler((JSONObject) o1);
            if (pageListArray == null) {
                continue;
            }
            for (Object o2 : pageListArray) {
                // 小区列表
                JSONArray communityList = pageHandler((JSONObject) o2);
                if (communityList == null) {
                    continue;
                }
                for (Object o3 : communityList) {
                    if (count++ % 100 == 0) {
                        log.info("{}:{},骨架数据，处理的数量：count={}。",
                                city, cityId, count);
                    }
                    JSONObject var1 = (JSONObject) o3;
                    // 将小区信息存储到数据库中去
                    CommunitySkeletonTask task = new CommunitySkeletonTask();
                    task.setCommunityId(var1.getString(COMMUNITY_ID));
                    task.setCommunityName(var1.getString(COMMUNITY_NAME));
                    task.setCityId(cityId);
                    task.setCreateTime(new Date());
                    communitySkeletonTaskService.checkAndAdd(task);
                }
            }
        }

        synchronized (cityIdSet) {
            cityIdSet.remove(cityId);
        }

        log.info("小区数据抓取：city:{},cityId:{},总计小区数量:{}", city, cityId, count);
        // emailUtil.sendSimpleMail("583853240@qq.com", String.format("%s,运行完毕", cityId));
    }


    JSONArray pageListHandler(JSONObject biz) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, biz.getString(CITY_ID));
        params.put(CITY, biz.getString(CITY));
        params.put(DISTRICT_ID, biz.getString(DISTRICT_ID));
        params.put(BIZCIRCLE_ID, biz.getString(BIZCIRCLE_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(biz)
                .build();

        ResponseDto responseDto = communityPageListSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray pageHandler(JSONObject page) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, page.getString(CITY_ID));
        params.put(CITY, page.getString(CITY));
        params.put(DISTRICT_ID, page.getString(DISTRICT_ID));
        params.put(BIZCIRCLE_ID, page.getString(BIZCIRCLE_ID));
        if (page.containsKey(LIMIT_OFFSET)) {
            params.put(LIMIT_OFFSET, page.getString(LIMIT_OFFSET));
        }
        if (page.containsKey("price_bp")) {
            params.put("price_bp", page.getString("price_bp"));
        }
        if (page.containsKey("price_eq")) {
            params.put("price_eq", page.getString("price_eq"));
        }

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(page)
                .build();

        ResponseDto responseDto = communityListSearch.execute(requestDto);

        return responseDto.getResult().getJSONArray("list");
    }

}
