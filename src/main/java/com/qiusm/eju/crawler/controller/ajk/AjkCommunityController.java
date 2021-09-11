package com.qiusm.eju.crawler.controller.ajk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.ajk.AjkArea;
import com.qiusm.eju.crawler.mapper.ajk.AjkAreaMapper;
import com.qiusm.eju.crawler.service.ajk.IAjkCommunityService;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

@RestController
@RequestMapping("/ajk/community")
public class AjkCommunityController {
    @Resource
    private IAjkCommunityService ajkCommunityService;
    @Resource
    private AjkAreaMapper ajkAreaDao;
    @Resource
    private HashOperations<String, String, String> hashOperations;
    private final ThreadsUtils threadsUtils = new ThreadsUtils();

    @GetMapping("/all")
    public void communityAll() {
        List<AjkArea> areas = ajkAreaDao.selectList(null);
        final String key = "crawler:community:ajk";
        List<Integer> future = threadsUtils.executeFutures(areas,
                (var) -> {
                    String value = hashOperations.get(key, String.valueOf(var.getId()));
                    if (!StringUtils.equals("0", value) || value != null) {
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
}
