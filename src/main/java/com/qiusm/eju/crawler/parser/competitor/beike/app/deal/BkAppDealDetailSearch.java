package com.qiusm.eju.crawler.parser.competitor.beike.app.deal;

import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 成交详情抓取
 *
 * @author qiushengming
 */
@Slf4j
public class BkAppDealDetailSearch extends BkAppBaseSearch {

    /**
     * 贝壳房屋编码
     */
    private static final String HOUSE_CODE = "house_code";

    /**
     * 查询条件
     */
    private static final String STRATEGY_INFO = "strategy_info";

    private static final String URL_TEMPLATE = "%s/house/chengjiao/detailpart1?house_code=%s&strategy_info=%s";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(HOUSE_CODE)
                || requestParam.containsKey(STRATEGY_INFO)) {
            throw new BusinessException("house_code or strategy_info is null");
        }
        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get(HOUSE_CODE), requestParam.get(STRATEGY_INFO));
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {

    }
}
