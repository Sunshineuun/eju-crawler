package com.qiusm.eju.crawler.parser.competitor.beike.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 小区详情：<br>
 * 面向url：/house/resblock/detailpart2?id=5011000013052 <br>
 * 必要参数：community_id(小区id) <br>
 *
 * @author qiushengming
 */
@Service
public class BkAppCommunityDetailSearch extends BkAppCommunityBaseSearch {

    /**
     * 小区id
     */
    private static final String COMMUNITY_ID = "community_id";

    private static final String URL_TEMPLATE = "%s/house/resblock/detailpart1?id=%s";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {

        Map<String, String> requestParam = requestDto.getRequestParam();

        if (!requestParam.containsKey(COMMUNITY_ID)) {
            throw new BusinessException(10000, "community_id null");
        }

        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get(COMMUNITY_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        responseDto.setResult(mainJson.getJSONObject("data"));
    }
}
