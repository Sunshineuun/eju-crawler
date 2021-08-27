package com.qiusm.eju.crawler.service.bk.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityPageListSearch;
import com.qiusm.eju.crawler.service.bk.IBkCommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.*;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BkCommunityServiceImpl implements IBkCommunityService {

    @Resource(name = "bkAppCommunityPageListSearch")
    private BkAppCommunityPageListSearch communityPageListSearch;
    @Resource
    private BkAppCommunityListSearch communityListSearch;

    /**
     * 贝壳小区列表抓取，抓取板块下的列表信息。后续可以转换为城市的。那么需要将结果存储起来，不能放在内存里面。<br>
     *
     * @return 小区列表
     */
    @Override
    public JSONArray communityList(JSONObject biz) {
        JSONArray result = new JSONArray();

        JSONArray pageListArray = pageListHandler(biz);
        if (pageListArray == null) {
            return new JSONArray();
        }
        for (Object o2 : pageListArray) {
            // 小区列表
            JSONArray communityList = pageHandler((JSONObject) o2);
            if (communityList != null) {
                result.addAll(communityList);
            }
        }
        return result;
    }

    JSONArray pageListHandler(JSONObject biz) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, biz.getString(CITY_ID));
        params.put(CITY, biz.getString(CITY));
        params.put(DISTRICT_ID, biz.getString(DISTRICT_ID));
        params.put(BIZCIRCLE_ID, biz.getString(BIZCIRCLE_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(biz)
                .build();

        ResponseDto responseDto = communityPageListSearch.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray pageHandler(JSONObject page) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, page.getString(CITY_ID));
        params.put(CITY, page.getString(CITY));
        params.put(DISTRICT_ID, page.getString(DISTRICT_ID));
        params.put(BIZCIRCLE_ID, page.getString(BIZCIRCLE_ID));
        if (page.containsKey(LIMIT_OFFSET)) {
            params.put(LIMIT_OFFSET, page.getString(LIMIT_OFFSET));
        }
        if (page.containsKey("price_bp")) {
            params.put("price_bp", page.getString("price_bp"));
        }
        if (page.containsKey("price_eq")) {
            params.put("price_eq", page.getString("price_eq"));
        }

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(page)
                .build();

        ResponseDto responseDto = communityListSearch.execute(requestDto);

        return responseDto.getResult().getJSONArray("list");
    }
}
