package com.qiusm.eju.crawler.controller.bk;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.CommunitySkeletonTask;
import com.qiusm.eju.crawler.entity.task.CrawlerTaskInstance;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityPageListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.BuildingSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.HouseSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.UnitSearchV1;
import com.qiusm.eju.crawler.service.base.ICommunitySkeletonTaskService;
import com.qiusm.eju.crawler.service.bk.IBkUserManagementService;
import com.qiusm.eju.crawler.utils.DateUtils;
import com.qiusm.eju.crawler.utils.EmailUtil;
import com.qiusm.eju.crawler.utils.ThreadPoolUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

import static com.qiusm.eju.crawler.constant.CrawlerDataPathConstant.SOURCE_BK_SKELETON;
import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.*;
import static com.qiusm.eju.crawler.constant.bk.BkHttpHeadConstant.LIANJIA_CITY_ID;


/**
 * 骨架数据抓取
 *
 * @author qiushengming
 */
@Api(tags = {"BeikeSkeleton", "bk骨架数据抓取"})
@Slf4j
@RestController
@RequestMapping("/bk/skeleton")
public class BeikeSkeletonController extends BeiKeBaseController implements InitializingBean {

    @Value("${eju.bk.skeleton.threadNum:8}")
    private Integer threadNum;

    @Resource(name = "bkAppCommunityPageListSearch")
    private BkAppCommunityPageListSearch communityPageListSearch;

    @Resource
    private BkAppCommunityListSearch communityListSearch;
    @Resource
    private BuildingSearchV1 buildingSearchV1;
    @Resource
    private UnitSearchV1 unitSearchV1;
    @Resource
    private HouseSearchV1 houseSearchV1;
    @Resource
    private IBkUserManagementService bkUserManagementService;
    @Resource
    private ICommunitySkeletonTaskService communitySkeletonTaskService;
    @Resource
    private EmailUtil emailUtil;

    private ThreadPoolExecutor bkSkeletonExecutor;

    /**
     * 用于存储cityId，如果运行中的任务存在列表中，则表示已经在执行了。
     */
    private final Set<String> cityIdSet = new HashSet<>();

    @Override
    public void start(CrawlerTaskInstance crawlerTaskInstance) {
    }

    private final Set<String> communityIdSet = new ConcurrentHashSet<>();

    /**
     * 获取板块信息
     *
     * @param cityId                城市id
     * @param city                  城市拼音
     * @param isPrintCommunitDetail 是否在日志中输出去小区详情信息，默认不输出;等于1时输出。
     */
    @GetMapping("/start/{city}/{cityId}")
    public void start(
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
        String filePath = SOURCE_BK_SKELETON + DateUtils.formatDate(new Date(), "yyyy.MM.ddHHmmss");
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
                    if (count++ % 10 == 0) {
                        log.info("{}:{},骨架数据，处理的数量：count={}。",
                                city, cityId, count);
                    }
                    JSONObject var1 = (JSONObject) o3;
                    if (!communityIdSet.contains(var1.getString(COMMUNITY_ID))) {
                        communityIdSet.add(var1.getString(COMMUNITY_ID));
                        communityTaskSubmit(var1, isPrintCommunitDetail);
                    } else {
                        log.warn("发现重复小区：{}", var1);
                    }

                }
            }
        }

        synchronized (cityIdSet) {
            cityIdSet.remove(cityId);
        }

        log.info("骨架数据抓取：city:{},cityId:{},总计数量:{}", city, cityId, count);
        // emailUtil.sendSimpleMail("583853240@qq.com", String.format("%s,运行完毕", cityId));
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

    private void communityTaskSubmit(JSONObject community, Integer isPrintCommunitDetail) {
        // 按照小区为单位抓取骨架数据
        bkSkeletonExecutor.submit(() -> {
            // 楼栋获取成功数量
            int bc = 0;
            // 楼栋目标数据 building total
            int bt = 0;
            // 单元获取成功数量
            int uc = 0;
            // 单元目标数量 unit total
            int ut = 0;
            // 小区楼栋信息抓取
            JSONArray buildingList = buildingHandler(community);
            if (buildingList == null) {
                return;
            }
            bt = buildingList.size();
            for (Object building : buildingList) {
                // 小区单元信息抓取
                JSONArray unitList = unitHandler((JSONObject) building);
                if (unitList == null) {
                    continue;
                }

                bc++;
                ut += unitList.size();
                for (Object unit : unitList) {
                    JSONArray houseList = houseHandler((JSONObject) unit);
                    if (houseList == null) {
                        continue;
                    }
                    uc++;
                    houseList.forEach(house -> {
                        /*FileUtils.printFile(((JSONObject) house).toJSONString() + "\n",
                                filePath, Thread.currentThread().getName() + ".txt", true);*/
                    });
                }
            }
            if (isPrintCommunitDetail == 1) {
                log.info("{}/{}/{}, building_count:{}/{}, unit_count:{}/{};",
                        community.get("district_name"),
                        community.get("community_name"),
                        community.get(COMMUNITY_ID),
                        bc, bt, uc, ut);
            }
            communityIdSet.remove(community.getString(COMMUNITY_ID));
        });
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

    JSONArray buildingHandler(JSONObject community) {
        RequestDto requestDto = RequestDto.builder()
                .user(bkUserManagementService.getUser())
                .requestParam(COMMUNITY_ID, community.getString(COMMUNITY_ID))
                .requestParam(CITY_ID, community.getString(CITY_ID))
                .head(LIANJIA_CITY_ID, community.getString(CITY_ID))
                .data(community)
                .isLoad(true)
                .build();
        ResponseDto responseDto = buildingSearchV1.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    private JSONArray unitHandler(JSONObject building) {
        RequestDto requestDto = RequestDto.builder()
                .user(bkUserManagementService.getUser())
                .requestParam(BUILDING_ID, building.getString(BUILDING_ID))
                .requestParam(CITY_ID, building.getString(CITY_ID))
                .head(LIANJIA_CITY_ID, building.getString(CITY_ID))
                .data(building)
                .isLoad(true)
                .build();
        ResponseDto responseDto = unitSearchV1.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    private JSONArray houseHandler(JSONObject unit) {
        RequestDto requestDto = RequestDto.builder()
                .user(bkUserManagementService.getUser())
                .requestParam(UNIT_ID, unit.getString(UNIT_ID))
                .requestParam(CITY_ID, unit.getString(CITY_ID))
                .head(LIANJIA_CITY_ID, unit.getString(CITY_ID))
                .data(unit)
                .isLoad(true)
                .build();
        ResponseDto responseDto = houseSearchV1.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    @Override
    public void afterPropertiesSet() {
        bkSkeletonExecutor = ThreadPoolUtils
                .newFixedThreadPool("bk-skeleton", threadNum, 20L);
    }
}
