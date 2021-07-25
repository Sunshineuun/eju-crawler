package com.qiusm.eju.crawler.competitor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.competitor.beike.service.IBkRedisService;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityPageListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.BuildingSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.HouseSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.UnitSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkUser;
import com.qiusm.eju.crawler.task.entity.CrawlerTaskInstance;
import com.qiusm.eju.crawler.utils.*;
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

import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.LIANJIA_CITY_ID;


/**
 * 骨架数据抓取
 *
 * @author qiushengming
 */
@Slf4j
@RestController
@RequestMapping("/bk/skeleton")
public class BeikeSkeletonController extends BeiKeBaseController {

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
    private IBkRedisService bkRedisService;

    private final ThreadPoolExecutor bkSkeletonExecutor = ThreadPoolUtils
            .newFixedThreadPool("bk-skeleton", 4, 20L);

    private CrawlerTaskInstance nowTask;

    @Override
    public void start(CrawlerTaskInstance crawlerTaskInstance) {
        this.nowTask = crawlerTaskInstance;
    }

    /**
     * 获取板块信息
     *
     * @param cityId 城市id
     * @param city   城市拼音
     */
    @GetMapping("/start/{city}/{cityId}")
    public void start(
            @PathVariable String cityId,
            @PathVariable String city) {
        JSONArray bizArray = cityHandler(cityId, city);
        String filePath = "source\\beike\\skeleton\\" + DateUtils.formatDate(new Date(), "yyyy.MM.ddHHmmss");
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
                        log.info("骨架数据，处理的数量：count={}。",
                                count);
                    }

                    // 按照小区为单位抓取骨架数据
                    bkSkeletonExecutor.submit(() -> {
                        // 小区楼栋信息抓取
                        JSONArray buildingList = buildingHandler((JSONObject) o3);
                        if (buildingList == null) {
                            return;
                        }
                        for (Object building : buildingList) {
                            // 小区单元信息抓取
                            JSONArray unitList = unitHandler((JSONObject) building);
                            if (unitList == null) {
                                continue;
                            }
                            for (Object unit : unitList) {
                                JSONArray houseList = houseHandler((JSONObject) unit);
                                if (houseList == null) {
                                    continue;
                                }
                                houseList.forEach(house -> {
                                    FileUtils.printFile(((JSONObject) house).toJSONString() + "\n",
                                            filePath, Thread.currentThread().getName() + ".txt", true);
                                });
                            }
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

        BkResponseDto responseDto = communityPageListSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray pageHandler(JSONObject page) {
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
        if (page.containsKey("price_eq")) {
            params.put("price_eq", page.getString("price_eq"));
        }

        BkRequestDto requestDto = BkRequestDto.builder()
                .requestParam(params)
                .data(page)
                .build();

        BkResponseDto responseDto = communityListSearch.execute(requestDto);

        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray buildingHandler(JSONObject community) {
        BkRequestDto requestDto = BkRequestDto.builder()
                .user(bkRedisService.getUser())
                .requestParam("community_id", community.getString("community_id"))
                .head(LIANJIA_CITY_ID, community.getString("city_id"))
                .isLoad(true)
                .build();
        BkResponseDto responseDto = buildingSearchV1.execute(requestDto);
        JSONArray array = responseDto.getResult().getJSONArray("list");
        if (array != null) {
            array.forEach(o -> {
                ((JSONObject) o).put("city_id", community.getString("city_id"));
                ((JSONObject) o).put("community_id", community.getString("community_id"));
                ((JSONObject) o).put("community_name", community.getString("community_name"));
            });
        }
        return array;
    }

    private JSONArray unitHandler(JSONObject building) {
        BkRequestDto requestDto = BkRequestDto.builder()
                .user(bkRedisService.getUser())
                .requestParam("building_id", building.getString("building_id"))
                .head(LIANJIA_CITY_ID, building.getString("city_id"))
                .isLoad(true)
                .build();
        BkResponseDto responseDto = unitSearchV1.execute(requestDto);
        JSONArray array = responseDto.getResult().getJSONArray("list");
        if (array != null) {
            array.forEach(o -> {
                JSONObject var = ((JSONObject) o);
                var.putAll(building);
            });
        }
        return array;
    }

    private JSONArray houseHandler(JSONObject unit) {
        BkRequestDto requestDto = BkRequestDto.builder()
                .user(bkRedisService.getUser())
                .requestParam("unit_id", unit.getString("unit_id"))
                .head(LIANJIA_CITY_ID, unit.getString("city_id"))
                .isLoad(true)
                .build();
        BkResponseDto responseDto = houseSearchV1.execute(requestDto);
        JSONArray array = responseDto.getResult().getJSONArray("list");

        if (array != null) {
            array.forEach(o -> {
                JSONObject var = ((JSONObject) o);
                var.putAll(unit);
            });
        }
        return array;
    }
}
