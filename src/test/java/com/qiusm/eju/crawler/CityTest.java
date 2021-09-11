package com.qiusm.eju.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.City;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.beike.app.base.BkAppCityDictSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.pc.base.BkPcCityPinyinSearch;
import com.qiusm.eju.crawler.service.base.ICityService;
import com.qiusm.eju.crawler.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class CityTest {

    @Resource
    private ICityService cityService;

    @Resource
    private BkPcCityPinyinSearch cityPinyinSearch;

    @Resource
    private BkAppCityDictSearch cityDictSearch;

    @Test
    void bkCity() {
        try {
            File file = ResourceUtils.getFile("classpath:json/bk/city_list.json");

            if (!file.exists()) {
                log.error("城市列表json文件不存在！");
            }
            String jsonStr = FileUtils.readFile(file);
            List<String> cityCodes = new ArrayList<>();
            JSONObject jsonObj = JSONObject.parseObject(jsonStr);
            JSONArray cityArray = jsonObj.getJSONObject("data").getJSONArray("cityList");
            cityArray.forEach(o -> {
                JSONObject var = (JSONObject) o;
                for (Object o1 : var.getJSONArray("list")) {
                    JSONObject var1 = (JSONObject) o1;
                    for (Object o2 : var1.getJSONArray("list")) {
                        JSONObject var2 = (JSONObject) o2;
                        String name = var2.getString("name");
                        EntityWrapper<City> entityWrapper = new EntityWrapper<>();
                        entityWrapper.eq("city", name + "市");
                        List<City> cityList = cityService.selectList(entityWrapper);
                        if (cityList.size() == 1) {
                            City city = cityList.get(0);
                            city.setBkCode(var2.getString("id"));
                            city.updateById();
                        } else {
                            log.info("{}", name);
                        }

                    }
                }
            });
            log.info("{}", cityCodes);
        } catch (Exception ignored) {

        }
    }

    @Test
    void bkCityPinyin() {
        RequestDto requestDto = RequestDto.builder()
                .build();
        ResponseDto responseDto = cityPinyinSearch.execute(requestDto);
        log.info("{}", responseDto);

        responseDto.getResult().getJSONArray("list").forEach(o -> {
            JSONObject var = (JSONObject) o;
            String name = var.getString("city");
            EntityWrapper<City> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("city", name + "市");
            List<City> cityList = cityService.selectList(entityWrapper);
            if (cityList.size() == 1) {
                City city = cityList.get(0);
                city.setBkPinyinCode(var.getString("city_pinyin"));
                city.updateById();
            } else {
                log.info("{}", name);
            }
        });
    }

    @Test
    void ejCityCode() {
        try {
            File file = ResourceUtils.getFile("classpath:json/bk/eju_city_list.json");
            String result = FileUtils.readFile(file);
            JSONObject mainJson = JSONObject.parseObject(result);
            JSONArray arrayList = mainJson.getJSONObject("result").getJSONArray("arrayList");
            arrayList.forEach(o -> {
                JSONObject var = (JSONObject) o;
                EntityWrapper<City> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", var.getString("cityCd"));
                List<City> cityList = cityService.selectList(entityWrapper);
                if (cityList.size() == 1) {
                    City city = cityList.get(0);
                    // city.setCode(var.getString("cityCd"));
                    // city.setProvinceCd(var.getString("provinceCd"));
                    city.setCity(var.getString("cityName"));
                    city.updateById();
                } else {
                    log.info("{}", var.get("cityName"));
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void ejProvCode() {
        try {
            File file = ResourceUtils.getFile("classpath:json/bk/eju_province_list.json");
            String result = FileUtils.readFile(file);
            JSONObject mainJson = JSONObject.parseObject(result);
            JSONArray arrayList = mainJson.getJSONObject("result").getJSONArray("arrayList");
            arrayList.forEach(o -> {
                JSONObject var = (JSONObject) o;
                EntityWrapper<City> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("province_cd", var.getString("provinceCd"));
                List<City> cityList = cityService.selectList(entityWrapper);
                cityList.forEach(city -> {
                    // city.setCode(var.getString("cityCd"));
                    city.setProvinceName(var.getString("provinceName"));
                    city.updateById();
                });
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void areaInfo() {
        // 北京,成都,广州,杭州,合肥,济南,昆明,南京,宁波,厦门,上海,沈阳,苏州,天津,无锡,
        String cityNames = "武汉,西安,郑州,重庆,佛山";
        Map<String, String> condition = new HashMap<>();
        for (String cityName : cityNames.split(",")) {
            condition.put("city", cityName);
            City city = cityService.selectCityByEqMap(condition);
            Map<String, String> params = new HashMap<>();
            params.put("city_id", city.getBkCode());
            params.put("city", city.getBkPinyinCode());
            RequestDto requestDto = RequestDto.builder()
                    .requestParam(params)
                    .requestMethod(RequestMethodEnum.GET)
                    .build();

            ResponseDto responseDto = cityDictSearch.execute(requestDto);
            JSONArray array = responseDto.getResult().getJSONArray("list");
            array.forEach(o -> FileUtils.printFile(JSONObject.toJSONString(o) + "\n", "source/bk", "dict.txt", true));
        }

    }
}
