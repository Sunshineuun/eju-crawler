package com.qiusm.eju.crawler.parser.competitor.anjuke.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.lang.JSONUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 安居客小区详情页面
 * https://api.anjuke.com/community/info?comm_id=363841&city_id=18&origin_mac=&app=a-ajk&_pid=14873&_guid=c079b21c-3a71-4704-b1bc-7b1fecd342e1&macid=e101a87abcf15692&version_code=322064&i=654967177695721&m=Android-MI%206&uuid=c3f381bd-1530-4bea-a483-b6dda974801d&manufacturer=Xiaomi&o=sagit-user%209%20PKQ1.190118.001%20V11.0.5.0.PCACNXM%20release-keys&qtime=20210918181747&cv=15.13&origin_imei=654967177695721&v=9&ajk_city_id=18&from=mobile&pm=b135&androidid=e101a87abcf15692&_chat_id=&oaid=60dcdbf28fc62fbe&cid=11
 */
@Service
public class CommunityDetailHandler extends CommunityPageListHandler {
    private final static String URL_TEMPLATE = "https://api.anjuke.com/community/info?comm_id=%s&city_id=%s&origin_mac=&app=a-ajk&cv=15.13";
    private final static Map<String, String> mappingKey = new HashMap<>();

    static {
        mappingKey.put("property_company", "data.community.base.extend.propertyCompany");
        mappingKey.put("property_money", "data.community.base.extend.propertyMoney");
        mappingKey.put("total_area", "data.community.base.extend.totalArea");
        mappingKey.put("total_house_hold_num", "data.community.base.extend.totalHouseHoldNum");
        mappingKey.put("developer", "data.community.base.extend.developer");
        mappingKey.put("plot_ratio", "data.community.base.extend.plotRatio");
        mappingKey.put("landscaping_ratio", "data.community.base.extend.landscapingRatio");
        mappingKey.put("parking", "data.community.base.extend.parking");
        mappingKey.put("completion_time", "data.community.base.extend.completionTime");
    }

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        requestDto.setUrl(String.format(URL_TEMPLATE, requestParam.get("comm_id"), requestParam.get("city_id")));
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONObject result = new JSONObject();
        mappingKey.forEach((k, v) -> {
            result.put(k, JSONUtils.getStringByKey(mainJson, v));
        });
        responseDto.setResult(result);
    }
}
