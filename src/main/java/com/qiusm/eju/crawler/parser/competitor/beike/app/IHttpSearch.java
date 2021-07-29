package com.qiusm.eju.crawler.parser.competitor.beike.app;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;

/**
 * @author qiushengming
 */
public interface IHttpSearch {
    /**
     * 查询
     *
     * @param requestDto 请求DTO
     * @return BkResponseDto
     */
    ResponseDto execute(RequestDto requestDto);
}
