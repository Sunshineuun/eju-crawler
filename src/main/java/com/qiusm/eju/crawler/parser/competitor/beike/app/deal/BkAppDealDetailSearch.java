package com.qiusm.eju.crawler.parser.competitor.beike.app.deal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.BkAppBaseSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkRequestDto;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 成交详情抓取 <br>
 * 面向URL:%s/house/chengjiao/detailpart1?house_code=%s&strategy_info=%s<br>
 * 必要入参：house_code(房屋编码) & strategy_info(bk查询) <br>
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class BkAppDealDetailSearch extends BkAppDealBaseSearch {

    /**
     * 贝壳房屋编码
     */
    private static final String HOUSE_CODE = "house_code";

    /**
     * 查询条件
     */
    private static final String STRATEGY_INFO = "strategy_info";

    private static final String URL_TEMPLATE = "%s/house/chengjiao/detailpart1?house_code=%s&strategy_info=%s";

    @Override
    protected void buildingUrl(BkRequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        if (!requestParam.containsKey(HOUSE_CODE)
                || !requestParam.containsKey(STRATEGY_INFO)) {
            throw new BusinessException("house_code or strategy_info is null");
        }
        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get(HOUSE_CODE), requestParam.get(STRATEGY_INFO));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(BkRequestDto requestDto, BkResponseDto responseDto) {

        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());

        if (!checkJsonError(mainJson)) {
            log.warn("{}", mainJson);
            return;
        }

        Map<String, Object> result = new HashMap<>(requestDto.getData());
        JSONObject data = mainJson.getJSONObject("data");
        // 基本信息
        JSONObject basicInfo = data.getJSONObject("basic_info");
        if (basicInfo != null) {
            result.put("house_name", basicInfo.getString("title"));
            result.put("house_code", basicInfo.getString("house_code"));
            result.put("title_id", basicInfo.getString("community_id"));
            result.put("title", basicInfo.getString("community_name"));
            result.put("area", basicInfo.getString("area"));
        }

        // basic_list
        JSONArray basicList = data.getJSONArray("basic_list");
        basicList.forEach(var -> {
            JSONObject jsonObject = (JSONObject) var;
            String name = jsonObject.getString("name");
            String value = jsonObject.getString("value");
            switch (name) {
                case "成交价格":
                    result.put("deal_price", value);
                    break;
                case "单价":
                    result.put("deal_average_price", value);
                    break;
                default:
                    break;
            }

        });

        //info_list
        JSONArray infoList = data.getJSONArray("info_list");
        infoList.forEach(var -> {
            JSONObject jsonObject = (JSONObject) var;
            String name = jsonObject.getString("name");
            String value = jsonObject.getString("value");
            switch (name) {
                case "成交：":
                    result.put("deal_time", value);
                    break;
                case "朝向：":
                    result.put("orientation", value);
                    break;
                case "楼层：":
                    String[] split = value.split("/");
                    if (split.length == 2) {
                        String s = split[0];
                        String s1 = split[1];
                        result.put("floor_height", s);
                        result.put("total_floor", s1);
                    }
                    break;
                case "楼型：":
                    result.put("build_type", value);
                    break;
                case "电梯：":
                    result.put("elevator", value);
                    break;
                case "装修：":
                    result.put("decoration", value);
                    break;
                case "年代：":
                    result.put("build_year", value);
                    break;
                case "用途：":
                    result.put("property_type", value);
                    break;
                case "权属：":
                    result.put("trading_rights", value);
                    break;
                default:
                    break;
            }
        });

        //location
        JSONObject locationObj = data.getJSONObject("location");
        String regionPlateStr = locationObj.getString("title");
        if (StringUtils.isNotBlank(regionPlateStr) && regionPlateStr.contains("，")) {
            String[] regionArr = regionPlateStr.split("，");
            if (regionArr.length == 2) {
                String region = regionArr[0];
                String plate = regionArr[1];
                result.put("region", region);
                result.put("plate", plate);
            }
        }

        //deal_info
        JSONObject dealInfo = data.getJSONObject("deal_info");
        if (dealInfo != null) {
            JSONObject review = dealInfo.getJSONObject("review");
            if (review != null) {
                JSONArray list = review.getJSONArray("list");
                list.forEach(var -> {
                    JSONObject jsonObject = (JSONObject) var;
                    String name = jsonObject.getString("name");
                    String value = jsonObject.getString("value");
                    switch (name) {
                        case "挂牌价格(万)":
                            result.put("list_price", value);
                            break;
                        case "成交周期(天)":
                            result.put("deal_cycle", value);
                            break;
                        case "调价(次)":
                            result.put("adjustments", value);
                            break;
                        case "带看(次)":
                            result.put("viewCount", value);
                            break;
                        case "关注(人)":
                            result.put("concern_count", value);
                            break;
                        case "浏览(次)":
                            result.put("pv_count", value);
                            break;
                        default:
                            break;
                    }
                });
            }
            //历史成交记录
            JSONObject history = dealInfo.getJSONObject("history");
            if (history != null) {
                JSONArray list = history.getJSONArray("list");
                if (list != null && list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        JSONObject jsonObject = list.getJSONObject(i);
                        String price = jsonObject.getString("price");
                        String desc = jsonObject.getString("desc");
                        result.put("history_deal_price", price);
                        result.put("history_deal_desc", desc);
                    }
                }
            }

        }

        //picture_list
        JSONArray pictureList = data.getJSONArray("picture_list");
        if (CollectionUtils.isNotEmpty(pictureList)) {
            for (int i = 0; i < pictureList.size(); i++) {
                JSONObject pictureJson = pictureList.getJSONObject(i);
                // 类型
                String groupName = pictureJson.getString("group_name");
                if ("户型图".equals(groupName)) {
                    JSONArray imgUrlList = pictureJson.getJSONArray("img_url_list");
                    if (CollectionUtils.isNotEmpty(imgUrlList)) {
                        //原图
                        String imgUrl = imgUrlList.getString(0);
                        if (StringUtils.isNotBlank(imgUrl)) {
                            result.put("frame_image", imgUrl);
                            if (imgUrl.contains("!m_fill")) {
                                String preStr = imgUrl.substring(0, imgUrl.lastIndexOf("/") + 1);
                                String keyStr = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                                keyStr = keyStr.substring(0, keyStr.indexOf(".") + 4);
                                String layoutPic = preStr + keyStr + "!m_fill,w_1000";

                                result.put("frame_image_mark_remove", layoutPic);
                            } else if (imgUrl.contains("x-se")) {
                                String replace = imgUrl.replace("/x-se", "");
                                String preStr = replace.substring(0, replace.lastIndexOf("/") + 1);
                                String keyStr = replace.substring(replace.lastIndexOf("/") + 1);
                                keyStr = keyStr.substring(0, keyStr.indexOf(".") + 4);
                                String layoutPic = preStr + keyStr + "!m_fill,w_1000";

                                result.put("frame_image_mark_remove", layoutPic);
                            }
                        }
                    }
                }
            }
        }

        responseDto.getResult().putAll(result);
    }

}
