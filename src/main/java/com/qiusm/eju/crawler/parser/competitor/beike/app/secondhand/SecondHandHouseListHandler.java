package com.qiusm.eju.crawler.parser.competitor.beike.app.secondhand;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 二手房挂牌案例列表
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class SecondHandHouseListHandler extends SecondHandHousePageListHandler {
    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONArray listArray = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        responseDto.getResult().put("list", listArray);
    }
}
