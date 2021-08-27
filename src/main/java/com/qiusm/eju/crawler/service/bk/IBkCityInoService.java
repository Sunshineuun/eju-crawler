package com.qiusm.eju.crawler.service.bk;

import com.alibaba.fastjson.JSONArray;

/**
 * 城市信息获取
 *
 * @author qiushengming
 */
public interface IBkCityInoService {
    /**
     * 获取城市下的板块列表
     *
     * @param cityId 城市code 6位编码
     * @param city   城市简拼
     * @return 板块列表
     */
    JSONArray getBizByCity(String cityId, String city);
}
