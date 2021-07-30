package com.qiusm.eju.crawler.parser.competitor.anjuke.app.community;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.AjkAppBase;
import com.qiusm.eju.crawler.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.CITY_ID;
import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.COMMUNITY_SEARCH_KEY;

/**
 * 面向url:landlord/cross/community/searchCommunity?1=小区名&limit=15&city_id=11&page=1 <br>
 * 必要参数：city_id，使用安居客的城市id，从ajk上抓取；searchCommunity，小区关键字；<br>
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class AjkAppCommunitySearch extends AjkAppBase {

    private static final String URL_TEMPLATE = DOMAIN + "landlord/cross/community/searchCommunity?q=%s&limit=15&city_id=%s&page=1";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(CITY_ID)) {
            throw new BusinessException(10000, "city_id null");
        }

        String url = String.format(URL_TEMPLATE, requestParam.get(COMMUNITY_SEARCH_KEY), requestParam.get(CITY_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray list = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        responseDto.getResult().put("list", list);
        log.info("{}", requestDto.getResponseStr());
    }
}
