package com.qiusm.eju.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.City;
import com.qiusm.eju.crawler.parser.competitor.beike.pc.base.BkPcCityPinyinSearch;
import com.qiusm.eju.crawler.service.base.ICityService;
import com.qiusm.eju.crawler.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class CityTest {

    @Resource
    private ICityService cityService;

    @Resource
    private BkPcCityPinyinSearch cityPinyinSearch;

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
}
