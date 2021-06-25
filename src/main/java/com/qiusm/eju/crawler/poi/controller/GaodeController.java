package com.qiusm.eju.crawler.poi.controller;

import com.qiusm.eju.crawler.poi.gaode.GaodeService;
import com.qiusm.eju.crawler.utils.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 获取城市内所有坐标点的 poi 信息
     *
     * @param cityName 城市名称
     */
    @GetMapping("/callCityPoiInfo/{cityName}")
    public void callCityPoiInfo(@PathVariable String cityName) {
        Map<String, String> tagMap = new HashMap<>(1);
        service.callCityPoiInfo(cityName, tagMap);
    }

    /**
     * 获取满足 longitude & laitude 经纬度的坐标点
     * TODO
     * 需要进行测试。
     * 测试的坐标121.4,31.2;上海黄浦区
     *
     * @param longitude 经度
     * @param latitude  纬度
     */
    @GetMapping("/callCityPoiInfo")
    public void callCityPoiInfo2(String longitude, String latitude) {
        Map<String, String> tagMap = new HashMap<>(1);
        service.callCityPoiInfoByLngAndLat(longitude, latitude, tagMap);
    }
}
