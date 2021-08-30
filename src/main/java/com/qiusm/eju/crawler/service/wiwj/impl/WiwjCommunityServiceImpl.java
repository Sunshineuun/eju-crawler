package com.qiusm.eju.crawler.service.wiwj.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.competitor.wiwj.app.community.CommunityListHandler;
import com.qiusm.eju.crawler.parser.competitor.wiwj.app.community.CommunityPageListHandler;
import com.qiusm.eju.crawler.service.wiwj.IWiwjCommunityService;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.CITY_ID;
import static com.qiusm.eju.crawler.constant.wiwj.WiwjFieldConstant.PAGE;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class WiwjCommunityServiceImpl implements IWiwjCommunityService {

    @Resource(name = "wiwjCommunityPageListHandler")
    private CommunityPageListHandler pageListHandler;
    @Resource(name = "wiwjCommunityListHandler")
    private CommunityListHandler listHandler;

    private ThreadsUtils threadUtils = new ThreadsUtils();
    private Integer threadNum = 8;

    @Override
    public JSONArray communityList(JSONObject cityInfo) {
        JSONArray result = new JSONArray();

        JSONArray pageListArray = pageListHandler(cityInfo);
        if (pageListArray == null) {
            return new JSONArray();
        }

        List<JSON> future = threadUtils.executeFutures(pageListArray,
                (e) -> {
                    // 如果执行成功返回JSONArray；否则返回楼栋信息。
                    return pageHandler((JSONObject) e);
                }, threadNum);
        for (Object o2 : future) {
            // 小区列表
            JSONArray communityList = (JSONArray) o2;
            if (communityList != null) {
                result.addAll(communityList);
            }
        }
        return result;
    }

    JSONArray pageListHandler(JSONObject cityInfo) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, cityInfo.getString(CITY_ID));
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(cityInfo)
                .build();

        ResponseDto responseDto = pageListHandler.execute(requestDto);
        return responseDto.getResult().getJSONArray("list");
    }

    JSONArray pageHandler(JSONObject page) {
        Map<String, String> params = new HashMap<>(8);
        params.put(CITY_ID, page.getString(CITY_ID));
        if (page.containsKey(PAGE)) {
            params.put(PAGE, page.getString(PAGE));
        }

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .data(page)
                .build();

        ResponseDto responseDto = listHandler.execute(requestDto);

        return responseDto.getResult().getJSONArray("list");
    }
}
