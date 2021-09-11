package com.qiusm.eju.crawler.controller.bk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.base.CommunitySkeletonTask;
import com.qiusm.eju.crawler.service.base.ICommunitySkeletonTaskService;
import com.qiusm.eju.crawler.service.bk.IBkCityInoService;
import com.qiusm.eju.crawler.service.bk.IBkCommunityService;
import com.qiusm.eju.crawler.service.bk.IBkSkeletonService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.COMMUNITY_ID;
import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.COMMUNITY_NAME;


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
    @Resource
    private ICommunitySkeletonTaskService communitySkeletonTaskService;
    @Resource
    private IBkSkeletonService bkSkeletonService;
    @Resource
    private IBkCommunityService bkCommunityService;
    @Resource
    private IBkCityInoService bkCityInoService;

    /**
     * 用于存储cityId，如果运行中的任务存在列表中，则表示已经在执行了。
     */
    private final Set<String> cityIdSet = new HashSet<>();

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

        JSONArray bizArray = bkCityInoService.getBizByCity(cityId, city);
        int count = 0;

        for (Object o1 : bizArray) {
            JSONArray communityList = bkCommunityService.communityList((JSONObject) o1);
            for (Object o2 : communityList) {
                JSONObject var1 = (JSONObject) o2;
                // 将小区信息存储到数据库中去
                CommunitySkeletonTask task = new CommunitySkeletonTask();
                task.setCommunityId(var1.getString(COMMUNITY_ID));
                task.setCommunityName(var1.getString(COMMUNITY_NAME));
                task.setCityId(cityId);
                task.setCreateTime(new Date());
                communitySkeletonTaskService.checkAndAdd(task);
            }
        }

        synchronized (cityIdSet) {
            cityIdSet.remove(cityId);
        }

        log.info("小区数据抓取：city:{},cityId:{},总计小区数量:{}", city, cityId, count);
        // emailUtil.sendSimpleMail("583853240@qq.com", String.format("%s,运行完毕", cityId));
    }
}
