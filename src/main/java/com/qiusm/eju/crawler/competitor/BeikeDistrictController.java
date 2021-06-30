package com.qiusm.eju.crawler.competitor;

import com.qiusm.eju.crawler.competitor.beike.BeikeDistrictService;
import com.qiusm.eju.crawler.poi.gaode.GaodeService;
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
@Slf4j
@RestController
@RequestMapping("/bk")
public class BeikeDistrictController {

    @Resource
    private BeikeDistrictService districtService;

    @GetMapping("/cityAll")
    public void cityAll() {
        List<String> cityCode = districtService.cityList();
        cityCode.forEach(o -> {
            districtService.city(o);
        });
        log.info("所有城市跑完！");
    }

    @GetMapping("/city/{cityCode}")
    public void city(@PathVariable String cityCode) {
        districtService.city(cityCode);
    }

    @GetMapping("/testCity/{cityCode}")
    public void testCity(@PathVariable String cityCode) {
        districtService.jsonParser(cityCode);
    }
}
