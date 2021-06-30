package com.qiusm.eju.crawler.competitor;

import com.qiusm.eju.crawler.competitor.beike.BeikeDistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
@Slf4j
@RestController
@RequestMapping("/bk")
public class BeikeDistrictController {

    @Resource
    BeikeDistrictService districtService;

    @GetMapping("/city/{cityName}")
    public void city(@PathVariable String cityName) {
        districtService.city(cityName);
        log.info("{}-区域商圈围栏数据处理完毕！", cityName);
    }

    @GetMapping("/testCity/{cityCode}")
    public void testCity(@PathVariable String cityCode) {
        districtService.jsonParser(cityCode);
    }
}
