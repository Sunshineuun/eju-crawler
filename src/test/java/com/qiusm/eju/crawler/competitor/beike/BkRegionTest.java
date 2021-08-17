package com.qiusm.eju.crawler.competitor.beike;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.City;
import com.qiusm.eju.crawler.entity.bk.BkRegion;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.beike.app.base.BkAppCityDictSearch;
import com.qiusm.eju.crawler.service.base.ICityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域板块数据
 *
 * @author qiushengming
 */
@Slf4j
@SpringBootTest
public class BkRegionTest {

    @Resource
    ICityService cityService;

    /**
     * 抓取BK区域板块列表并入库 <br>
     */
    @Test
    void region() {
        EntityWrapper<City> entityWrapper = new EntityWrapper<>();
        entityWrapper.isNotNull("bk_code");
        List<City> cityList = cityService.selectList(entityWrapper);
        cityList.forEach(city -> {
            String cityId = city.getBkCode();
            String cityCode = city.getBkPinyinCode();
            JSONArray jsonArray = cityHandler(cityId, cityCode);
            if (jsonArray == null) {
                log.warn("板块信息为空：{}", city);
                return;
            } else {
                log.info("板块信息不为空：{}", city);
            }
            for (Object o : jsonArray) {
                JSONObject var = (JSONObject) o;

                BkRegion region = new BkRegion();
                region.setCityName(var.getString("city_name"));
                region.setCityId(var.getString("city_id"));
                region.setRegion(var.getString("region"));
                region.setRegionCd(var.getString("district_id"));
                region.setPlate(var.getString("plate"));
                region.setPlateCd(var.getString("bizcircle_id"));
                region.insert();
            }
        });
    }

    @Resource
    BkAppCityDictSearch bkAppCityDictSearch;

    /**
     * 获取城市下板块和区域的信息
     *
     * @param cityId 城市id
     * @param city   城市拼音简写
     * @return JSONArray
     */
    JSONArray cityHandler(String cityId, String city) {
        Map<String, String> params = new HashMap<>(8);
        params.put("city_id", cityId);
        params.put("city", city);
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .requestMethod(RequestMethodEnum.GET)
                .build();

        ResponseDto responseDto = bkAppCityDictSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }
}
