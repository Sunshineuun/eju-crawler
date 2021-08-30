package com.qiusm.eju.crawler.service.wiwj;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IWiwjCommunityService {
    JSONArray communityList(JSONObject cityInfo);
}
