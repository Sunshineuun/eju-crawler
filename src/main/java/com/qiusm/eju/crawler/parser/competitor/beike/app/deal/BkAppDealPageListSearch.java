package com.qiusm.eju.crawler.parser.competitor.beike.app.deal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.CITY_ID;

/**
 * deal page list <br>
 * 面向URL: /house/chengjiao/searchV2?limit_offset=%s&condition=%s&city_id=%s&containerType=0&limit_count=100 <br>
 * 成交页面列表，主要得到页面的请求列表 <br>
 * 必要入参：district_id(区域id) & bizcircle_id(商圈id) & city_id(城市id)<br>
 * 次要入参：limit_offset(偏移位，默认0)；price_bp(价格上限)&price_eq(价格下限)；<br>
 * 主要处理：根据区域板块分割之后，是否还存在大于2100条记录的，如果大于，则需要再按照价格进行分割 <br>
 *
 * @author qiushengming
 */
@Slf4j
@Service("bkAppDealPageListSearch")
public class BkAppDealPageListSearch extends BkAppDealBaseSearch {
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
    protected static final String LIMIT_OFFSET = "limit_offset";
    /**
     * 价格下限
     */
    private static final String PRICE_BP = "price_bp";
    /**
     * 价格上限
     */
    private static final String PRICE_EP = "price_ep";
    private static final String URL_TEMPLATE = "%s/house/chengjiao/searchV2?limit_offset=%s&condition=%s&city_id=%s&containerType=0&limit_count=100";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
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
        // 如果存在价格区间则加入价格区间
        if (requestParam.containsKey(PRICE_BP)
                && requestParam.containsKey(PRICE_EP)) {
            // d%sb%sbp%sep%s
            condition += String.format("bp%sep%s", requestParam.get(PRICE_BP), requestParam.get(PRICE_EP));
        }

        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, limitOffset, condition, requestParam.get(CITY_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        Map<String, Object> data = requestDto.getData();
        JSONArray arrayResult = new JSONArray();

        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());

        if (!checkJsonError(mainJson)) {
            log.warn("{}", mainJson);
            return;
        }

        JSONObject dataJson = mainJson.getJSONObject("data");

        Integer totalCount = dataJson.getInteger("total_count");

        if (totalCount != null) {
            // 总数小于2100
            if (totalCount < 2100) {
                log.info("{},{}:板块下总成交数量{}", data.get("region"), data.get("plate"), totalCount);

                JSONObject resultJson = new JSONObject();
                resultJson.putAll(data);
                resultJson.put(LIMIT_OFFSET, "0");
                arrayResult.add(resultJson);
            } else {
                //价格区间 分割
                // 总价格是2000w 间隔10w
                log.info("{},{}:板块下总成交数量{}，进行价格区间切割", data.get("region"), data.get("plate"), totalCount);
                int priceSize = 50;
                int priceCount = 21;
                for (int bp = 0; bp < priceCount; bp++) {
                    int ep = bp + 1;
                    if (bp == priceCount - 1) {
                        ep = bp * 20;
                    }

                    JSONObject resultJson = new JSONObject();
                    resultJson.putAll(data);
                    resultJson.put(LIMIT_OFFSET, "0");
                    resultJson.put(PRICE_BP, bp * priceSize);
                    resultJson.put(PRICE_EP, ep * priceSize);
                    arrayResult.add(resultJson);
                }
            }
        } else {
            log.info("{},{}:板块下无成交", data.get("region"), data.get("plate"));
            totalCount = 0;
        }

        responseDto.getResult().put("list", arrayResult);
        responseDto.getResult().put("total_count", totalCount);
    }
}
