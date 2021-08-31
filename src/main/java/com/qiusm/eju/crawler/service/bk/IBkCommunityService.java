package com.qiusm.eju.crawler.service.bk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IBkCommunityService {
    /**
     * 通过板块信息获取小区列表
     *
     * @param biz 板块信息
     * @return
     */
    JSONArray communityList(JSONObject biz);

    /**
     * 小区详情抓取
     *
     * @param community 小区简要信息
     * @return
     */
    boolean communityDetail(JSONObject community);
}
