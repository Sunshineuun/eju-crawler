package com.qiusm.eju.crawler.parser.competitor.anjuke.app.skeleton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.anjuke.app.AjkAppBase;
import com.qiusm.eju.crawler.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.*;

/**
 * 骨架数据顶层内容 <br>
 * ajk获取房号的逻辑：获取单元列表 >> 获取楼层列表 >> 获取房号 <br>
 * 小区id获取单元列表；单元id获取楼层列表；楼层id获取房号；
 * type:2,楼栋；3：单元；4：房号；<br>
 * community_type:3.<br>
 * @author qiushengming
 */
@Slf4j
public abstract class AjkAppSkeletonBase extends AjkAppBase {
    protected static final String URL_TEMPLATE = DOMAIN + "landlord/cross/community/houseNoList";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        requestDto.setUrl(URL_TEMPLATE);
        Map<String, String> requestParam = requestDto.getRequestParam();
        String[] keys = new String[]{BUILDING_ID, UNIT_ID, COMMUNITY_ID, COMMUNITY_TYPE, TYPE, PAGE,};
        for (String key : keys) {
            if (!requestParam.containsKey(key)) {
                throw new BusinessException(10000, key + " 必填");
            }
        }
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSONObject.parseObject(requestDto.getResponseStr());
        JSONArray list = JSONUtils.getJsonArrayByKey(mainJson, "data.list");
        responseDto.getResult().put("list", list);
    }
}
