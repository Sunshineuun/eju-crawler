package com.qiusm.eju.crawler.parser.competitor.beike.app;

import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;

/**
 * @author qiushengming
 */
public interface HttpSearch {
    /**
     * 查询
     *
     * @param requestDto 请求DTO
     * @return BkResponseDto
     */
    BkResponseDto execute(BkRequestDto requestDto);
}
