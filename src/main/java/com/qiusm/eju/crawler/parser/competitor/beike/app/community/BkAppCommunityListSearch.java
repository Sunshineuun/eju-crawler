package com.qiusm.eju.crawler.parser.competitor.beike.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
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
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONArray listArray = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        responseDto.getResult().put("list", listArray);
    }
}
