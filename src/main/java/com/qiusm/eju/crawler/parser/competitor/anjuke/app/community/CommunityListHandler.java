package com.qiusm.eju.crawler.parser.competitor.anjuke.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.JSONUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.COMMUNITY_ID;
import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.DETAIL_URL;

/**
 * /community/list?lng=121.45633665813051&select_type=0&page=1&area_id=13&lat=31.278022958545325&city_id=11&page_size=25&origin_mac=&app=a-ajk&_pid=5382&_guid=571b0a0f-beb6-4ea8-970d-1d0e0a1e9f3a&macid=e101a87abcf15692&version_code=322064&i=654967177695721&m=Android-MI%206&uuid=314eaef0-8124-4d9d-954d-ec8beb3b2d13&manufacturer=Xiaomi&o=sagit-user%209%20PKQ1.190118.001%20V11.0.5.0.PCACNXM%20release-keys&qtime=20210827180642&cv=15.13&origin_imei=654967177695721&v=9&ajk_city_id=11&from=mobile&pm=b135&androidid=e101a87abcf15692&_chat_id=&oaid=60dcdbf28fc62fbe&cid=11
 * <p>
 * /community/list?select_type=0&page=1&area_id=13&city_id=11&page_size=25&app=a-ajk&cv=15.13&v=9&ajk_city_id=11
 * ajk小区列表抓取
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class CommunityListHandler extends CommunityPageListHandler {

    private final Map<String, String> baseInfoKey = new HashMap<>();

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONArray listArray = JSONUtils.getJsonArrayByKey(mainJson, "data.communities");
        JSONArray resultArray = new JSONArray();
        listArray.forEach(o -> {
            JSONObject community = (JSONObject) o;
            JSONObject result = new JSONObject();

            result.put(DETAIL_URL, requestDto.getUrl());
            result.putAll(requestDto.getData());

            parserInfo(community, result, baseInfoKey);
            result.put("gd", String.format("%s,%s", result.get("gd_lng"), result.get("gd_lat")));
            result.put("bd", String.format("%s,%s", result.get("bd_lat"), result.get("bd_lat")));

            // 获取新的小区id用于获取骨架数据
            getNewCommunityId(community.getJSONObject("propInfo"), result);
            resultArray.add(result);
        });
        responseDto.getResult().put("list", resultArray);
    }

    private void getNewCommunityId(JSONObject propInfo, JSONObject result) {
        String startKey = "openanjuke://jump/house/list?params=";
        JSONObject hotZufang = propInfo.getJSONObject("hotZufang");
        String jumpAction = hotZufang.getString("jumpAction");
        if (!StringUtils.startsWith(jumpAction, startKey)) {
            return;
        }

        String str1 = jumpAction.substring(startKey.length());
        String str2;
        try {
            str2 = URLDecoder.decode(str1, "UTF-8");
            JSONObject json1 = JSONObject.parseObject(str2);
            String fromPage = JSONUtils.getStringByKey(json1, "xiaoquParams.fromPage");
            if (StringUtils.equals(fromPage, "community")) {
                result.put(COMMUNITY_ID, JSONUtils.getStringByKey(json1, "xiaoquParams.comm_id"));
            }
        } catch (UnsupportedEncodingException ignored) {

        }

    }

    private void parserInfo(JSONObject data, JSONObject result, Map<String, String> keyMapping) {
        keyMapping.forEach((k, v) -> result.put(k, JSONUtils.getStringByKey(data, v)));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        baseInfoKey.put("community", "base.name");
        baseInfoKey.put("community_id", "base.id");
        baseInfoKey.put("community_add", "base.address");
        baseInfoKey.put("lng", "lng");
        baseInfoKey.put("lat", "lat");

        baseInfoKey.put("city_id", "base.cityId");
        baseInfoKey.put("region", "base.areaName");
        baseInfoKey.put("region_id", "base.areaId");
        baseInfoKey.put("plate", "base.tradingAreaName");
        baseInfoKey.put("plate_id", "base.tradingAreaId");
        baseInfoKey.put("gd_lng", "base.lng");
        baseInfoKey.put("gd_lat", "base.lat");
        baseInfoKey.put("bd_lng", "base.blng");
        baseInfoKey.put("bd_lat", "base.blat");
        baseInfoKey.put("build_year", "base.completionTime");
        baseInfoKey.put("build_type", "base.buildTypeStr");

        baseInfoKey.put("greend_rate", "extend.landscapingRatio");
        baseInfoKey.put("property_company", "extend.propertyCompany");
        baseInfoKey.put("property_expenses", "extend.propertyMoney");
        baseInfoKey.put("property_type", "extend.type");
        baseInfoKey.put("trade_ownership", "base.shipTypeStr");
        baseInfoKey.put("area_total", "extend.totalArea");
        baseInfoKey.put("house_total", "extend.totalHouseHoldNum");
        baseInfoKey.put("sale", "propInfo.saleNum");
        baseInfoKey.put("rent", "propInfo.rentNum");
        baseInfoKey.put("avg_price", "priceInfo.price");
        baseInfoKey.put("avg_price_month", "priceInfo.month");
        baseInfoKey.put("avg_price_month_change", "priceInfo.monthChange");
    }
}
