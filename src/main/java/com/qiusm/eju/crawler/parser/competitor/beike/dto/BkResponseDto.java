package com.qiusm.eju.crawler.parser.competitor.beike.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author qiushengming
 */
@Data
public class BkResponseDto {
    private Boolean success;
    private JSONObject result = new JSONObject();
    private byte[] resultByte;
    /**
     * 系统内部出现的异常错误
     */
    private String sysErrorMsg;
    /**
     * bk提示的异常
     */
    private String bkErrorMsg;

    public Boolean isResultEmpty() {
        return (result == null || result.isEmpty()) && (resultByte == null || resultByte.length == 0);
    }
}
