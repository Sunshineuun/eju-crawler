package com.qiusm.eju.crawler.parser.competitor.beike.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 小区翻页列表 <br>
 * 面向url: /house/community/searchv2?limit_offset=0&condition=&city_id=310000&containerType=0&limit_count=20 <br>
 * 必要入参：district_id(区域id) & bizcircle_id(商圈id) & city_id(城市id)<br>
 *
 * @author qiushengming
 */
@Service("bkAppCommunityPageListSearch")
public class BkAppCommunityPageListSearch extends BkAppCommunityBaseSearch {

    /**
     * 区域id
     */
    private static final String DISTRICT_ID = "district_id";
    /**
     * 板块id
     */
    private static final String BIZCIRCLE_ID = "bizcircle_id";
    /**
     * 翻页偏移位
     */
    private static final String LIMIT_OFFSET = "limit_offset";

    private static final String URL_TEMPLATE = "%s/house/community/searchv2?limit_offset=%s&condition=%s&city_id=%s&containerType=0&limit_count=100";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();

        if (!requestParam.containsKey(DISTRICT_ID)
                || !requestParam.containsKey(BIZCIRCLE_ID)
                || !requestParam.containsKey(CITY_ID)) {
            throw new BusinessException(10000, "city_id or district_id or bizcircle_id is null");
        }
        String limitOffset = requestParam.get(LIMIT_OFFSET);
        if (StringUtils.isBlank(limitOffset)) {
            limitOffset = "0";
        }

        // d%sb%s
        String condition = String.format("d%sb%s", requestParam.get(DISTRICT_ID), requestParam.get(BIZCIRCLE_ID));

        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, limitOffset, condition, requestParam.get(CITY_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {

        Map<String, Object> data = requestDto.getData();
        JSONArray arrayResult = new JSONArray();

        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());

        JSONObject dataJson = mainJson.getJSONObject("data");

        Integer totalCount = dataJson.getInteger("total_count");

        // 总数小于2100
        if (totalCount > 0) {
            // 翻页
            int pageNum = totalCount % 100 == 0 ? totalCount / 100 : totalCount / 100 + 1;
            for (int m = 0; m < pageNum; m++) {
                JSONObject resultJson = new JSONObject();
                resultJson.putAll(data);
                resultJson.put(LIMIT_OFFSET, m * 100);
                arrayResult.add(resultJson);
            }
        }

        responseDto.getResult().put("list", arrayResult);

    }
}
