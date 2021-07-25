package com.qiusm.eju.crawler.competitor;

import com.qiusm.eju.crawler.competitor.beike.BeikeDistrictService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qiushengming
 */
@Api(tags = {"BeikeDistrict", "bk城市&区域&板块，围栏数据"})
@Slf4j
@RestController
@RequestMapping("/bk/district")
public class BeikeDistrictController {

    @Resource
    private BeikeDistrictService districtService;

    /**
     * 遍历贝壳所有城市，获取区域板块围栏信息 <br>
     */
    @GetMapping("/cityAll")
    public void cityAll() {
        List<String> cityCode = districtService.loadCityList();
        cityCode.forEach(o -> {
            districtService.city(o);
        });
        log.info("所有城市跑完！");
    }

    @GetMapping("/city/1/{cityCode}")
    public void city(@PathVariable String cityCode) {
        districtService.city(cityCode);
    }

    @GetMapping("/city/2/{cityCode}")
    public void city(@PathVariable String cityCode, String maxLatitude, String minLatitude, String maxLongitude, String minLongitude) {
        districtService.district(cityCode, maxLatitude, minLatitude, maxLongitude, minLongitude);
    }

    @GetMapping("/testCity/{cityCode}")
    public void testCity(@PathVariable String cityCode) {
        districtService.jsonParser(cityCode);
    }
}
