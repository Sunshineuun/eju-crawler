package com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton;

import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;

/**
 * @author qiushengming
 */
public interface HttpSearch {
    /**
     * 查询
     *
     * @param requestDto 关键字
     * @return
     */
    BkResponseDto search(BkRequestDto requestDto);
}
