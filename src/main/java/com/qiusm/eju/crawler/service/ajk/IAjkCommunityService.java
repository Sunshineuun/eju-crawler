package com.qiusm.eju.crawler.service.ajk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IAjkCommunityService {

    /**
     * 获取当前区域下的小区列表
     *
     * @param area 区域信息，包含城市id，区域id
     * @return 小区列表
     */
    JSONArray communityList(JSONObject area);
}
