package com.qiusm.eju.crawler.poi.controller;

import com.qiusm.eju.crawler.poi.gaode.GaodeService;
import com.qiusm.eju.crawler.utils.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
@RestController
@RequestMapping("gaode")
public class GaodeController {

    @Resource
    private GaodeService service;

    @GetMapping("callAllCityPoiInfo")
    public void callAllCityPoiInfo() {
        service.allCityStart();
    }

    @GetMapping("/callAllCityPoiInfo/{cityName}")
    public void callCityFence(@PathVariable String cityName) {
        if (StringUtils.equals(cityName, "中国")) {
            service.allCityFenceStart();
        } else {
            service.cityFenceStart(cityName);
        }
    }

    @GetMapping("/cuttingAllCityElectronicFence/{cityName}")
    public void cuttingAllCityElectronicFence(@PathVariable String cityName) {
        service.cuttingAllCityElectronicFence(cityName);
    }
}
