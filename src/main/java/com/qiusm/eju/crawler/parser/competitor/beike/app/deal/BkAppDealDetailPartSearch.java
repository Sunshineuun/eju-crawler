package com.qiusm.eju.crawler.parser.competitor.beike.app.deal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.lang.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 面向URL: /house/house/moreinfo?house_code=%s<br>
 * 必要参数: house_code(房屋编码) <br>
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class BkAppDealDetailPartSearch extends BkAppDealBaseSearch {
    /**
     * 贝壳房屋编码
     */
    private static final String HOUSE_CODE = "house_code";

    private static final String URL_TEMPLATE = "%s/house/house/moreinfo?house_code=%s";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        String houseCode = requestParam.get(HOUSE_CODE);

        if (StringUtils.isBlank(houseCode)) {
            throw new BusinessException(10000, "house_code is null");
        }
        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get(HOUSE_CODE));
        if (StringUtils.equals(url, "https://app.api.ke.com/house/house/moreinfo?house_code=null")) {
            log.error("{}", requestDto);
            throw new BusinessException(10000, "house_code is null");
        }
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        Map<String, Object> data = requestDto.getData();

        JSONUtils.getJsonArrayByKey(mainJson, "data.list.list").forEach(var -> {
            JSONObject jsonObject1 = (JSONObject) var;
            String key = jsonObject1.getString("name");
            String value1 = jsonObject1.getString("value");
            switch (key) {
                case "房源户型：":
                    data.put("layout", value1);
                    break;
                case "套内面积：":
                    data.put("dwelling_floor_space", value1);
                    break;
                case "户型结构：":
                    data.put("layout_str", value1);
                    break;
                case "梯户比例：":
                    data.put("ladder_ratio", value1);
                    break;
                case "供暖方式：":
                    data.put("heating", value1);
                    break;
                case "产权年限：":
                    data.put("property_year", value1);
                    break;
                //交易属性
                case "房屋年限：":
                    data.put("housing_years", value1);
                    break;
                case "产权所属：":
                    data.put("property_ownership", value1);
                    break;
                default:
                    break;
            }
        });

        responseDto.getResult().putAll(data);

    }
}
