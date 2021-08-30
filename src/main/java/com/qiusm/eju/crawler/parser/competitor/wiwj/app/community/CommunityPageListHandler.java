package com.qiusm.eju.crawler.parser.competitor.wiwj.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.wiwj.WiwjAppHandler;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.PAGE;
import static com.qiusm.eju.crawler.constant.wiwj.WiwjFieldConstant.CITY_ID;

/**
 * 城市ID
 * <p>
 * 杭州：2；
 *
 * @author qiushengming
 */
@Slf4j
@Service("wiwjCommunityPageListHandler")
public class CommunityPageListHandler extends WiwjAppHandler {

    protected static final String URL_TEMPLATE = DOMAIN + "appapi/community/%s/v1/list?pcount=15&page=%s";

    /**
     * page=1&pcount=15&qyid=&sqid=&price=&distance=&psort=&buildage=&communityid=&communitytype=&keywords=
     *
     * @param requestDto 请求dto
     */
    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        String cityId = requestParam.get(CITY_ID);
        if (StringUtils.isBlank(cityId)) {
            throw new BusinessException(10000, "city_id or area_id is not blank");
        }
        String page = requestParam.get(PAGE);
        if (StringUtils.isBlank(page)) {
            page = "1";
            requestParam.put(PAGE, page);
        }
        requestDto.setUrl(String.format(URL_TEMPLATE, cityId, page));
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONArray arrayResult = new JSONArray();

        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONObject dataJson = mainJson.getJSONObject("data");
        Integer totalCount = dataJson.getInteger("count");
        if (totalCount == null) {
            totalCount = 0;
        }

        if (totalCount > 0) {
            // 翻页
            int pageCount = 15;
            int pageNum = totalCount % pageCount == 0 ? totalCount / pageCount : totalCount / pageCount + 1;
            for (int m = 1; m <= pageNum; m++) {
                JSONObject resultJson = new JSONObject();
                resultJson.putAll(requestDto.getData());
                resultJson.put(PAGE, m);
                arrayResult.add(resultJson);
            }
        }

        responseDto.getResult().put("list", arrayResult);
        responseDto.getResult().put("totalCount", totalCount);
    }
}
