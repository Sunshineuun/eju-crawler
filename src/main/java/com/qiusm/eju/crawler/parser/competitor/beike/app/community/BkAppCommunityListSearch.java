package com.qiusm.eju.crawler.parser.competitor.beike.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import org.springframework.stereotype.Service;

/**
 * 小区列表
 *
 * @author qiushengming
 */
@Service
public class BkAppCommunityListSearch extends BkAppCommunityPageListSearch {

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONArray listArray = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        responseDto.getResult().put("list", listArray);
    }
}
