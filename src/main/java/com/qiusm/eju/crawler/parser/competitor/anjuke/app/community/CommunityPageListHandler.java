package com.qiusm.eju.crawler.parser.competitor.anjuke.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.AjkAppBase;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.ajk.AjkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

/**
 * 小区分页列表
 * shangquan_id=6351；<br>
 * 必要参数，商圈、区域、城市id；<br>
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class CommunityPageListHandler extends AjkAppBase {
    protected static final String URL_TEMPLATE = "https://api.anjuke.com/community/list?page=%s&area_id=%s&city_id=%s&page_size=100&app=a-ajk&ajk_city_id=%s";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        String cityId = requestParam.get(CITY_ID);
        String areaId = requestParam.get(AREA_ID);
        if (StringUtils.isBlank(cityId)
                || StringUtils.isBlank(areaId)) {
            throw new BusinessException(10000, "city_id or area_id is not blank");
        }
        String page = requestParam.get(PAGE);
        if (StringUtils.isBlank(page)) {
            page = "1";
            requestParam.put(PAGE, page);
        }
        requestDto.setUrl(String.format(URL_TEMPLATE, page, areaId, cityId, cityId));
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONArray arrayResult = new JSONArray();

        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONObject dataJson = mainJson.getJSONObject("data");
        Integer totalCount = dataJson.getInteger("total");
        if (totalCount == null) {
            totalCount = 0;
        }

        // 总数小于2100
        if (totalCount > 0) {
            // 翻页
            int pageNum = totalCount % 100 == 0 ? totalCount / 100 : totalCount / 100 + 1;
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

    @Override
    protected void buildingHeader(RequestDto dto) {
        try {
            dto.getHead().put("memberid", "0");
            dto.getHead().put("clouduid", "0");
            dto.getHead().put("user-agent", "a-ajk/15.13/Android-MI 6/android/9");
            dto.getHead().put("nsign", AjkUtils.nsignOfGet(dto.getUrl()));
        } catch (Exception e) {
            log.error("AJK nsingn 无法执行：{}", e.getMessage());
            log.debug("{}", StringUtils.stackTraceInfoToStr(e));
        }
    }
}
