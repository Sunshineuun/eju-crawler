package com.qiusm.eju.crawler.parser.competitor.beike.app.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.utils.lang.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 小区详情：<br>
 * 面向url：/house/resblock/detailpart2?id=5011000013052 <br>
 * 必要参数：community_id(小区id) <br>
 *
 * @author qiushengming
 */
@Service
public class BkAppCommunityDetailSearch extends BkAppCommunityBaseSearch implements InitializingBean {

    /**
     * 小区id
     */
    private static final String COMMUNITY_ID = "community_id";

    private static final String URL_TEMPLATE = "%s/house/resblock/detailpart1?id=%s";

    private final Map<String, String> communityOverviewKeyMapping = new HashMap<>();
    private final Map<String, String> communityBaseInfoKeyMapping = new HashMap<>();
    private final Map<String, String> communityOtherInfoKeyMapping = new HashMap<>();

    @Override
    protected void buildingUrl(RequestDto requestDto) {

        Map<String, String> requestParam = requestDto.getRequestParam();

        if (!requestParam.containsKey(COMMUNITY_ID)) {
            throw new BusinessException(10000, "community_id null");
        }

        String url = String.format(URL_TEMPLATE, DOMAIN_NAME, requestParam.get(COMMUNITY_ID));
        requestDto.setUrl(url);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONObject mainJson = JSON.parseObject(requestDto.getResponseStr());
        JSONObject mainData = mainJson.getJSONObject("data");
        JSONObject result = new JSONObject();
        responseDto.setResult(result);

        result.put("detail_url", requestDto.getUrl());
        result.put("info_src", "BK");
        result.put("file_name", "community_base_info");

        //区划信息
        parserBasicInfo(mainData, result);
        // 图片
        parserImg(mainData, result);
        // 小区概况
        parserIntroduceInfo(mainData, result);
        // 小区其它信息
        parserOtherInfo(mainData, result);

    }

    /**
     * 小区概况
     *
     * @param mainData json
     * @param result   存储
     */
    private void parserIntroduceInfo(JSONObject mainData, JSONObject result) {
        JSONObject introduceJsonObj = mainData.getJSONObject("introduceInfo");
        JSONArray listArr = introduceJsonObj.getJSONArray("list");
        if (null != listArr && listArr.size() > 0) {
            for (Object listObj : listArr) {
                JSONObject var = (JSONObject) listObj;
                String name = var.getString("name");
                String value = var.getString("value");
                if (name.contains(":")) {
                    name = name.replace(":", "");
                }
                String key = communityOverviewKeyMapping.get(name);
                if (StringUtils.isNotBlank(key)) {
                    result.put(key, value);
                }

            }
        }
    }

    /**
     * 首页图，实景图
     *
     * @param mainData json
     * @param result   存储
     */
    private void parserImg(JSONObject mainData, JSONObject result) {/*
        //存放到图片的kafka中：1、首页图片 2、实景图
        String encrypt = null;
        String path = "fang/" + String.valueOf(data.get(CommunityBaseInfoConstant.CITY_CODE)) + "/community/";
        List<PictureKey> picKeylist = new ArrayList<>();
        String surfacePlot = String.valueOf(data.get(CommunityBaseInfoConstant.SURFACE_PLOT));
        if (StringUtils.isNotBlank(surfacePlot) && !"null".equals(surfacePlot)) {
            encrypt = tranformPic(surfacePlot, path, "3", "1", picKeylist);
            // http://ejufangdata.ess.ejucloud.cn/fang/110000/community/964a7d6294fe2de5f3e3347c800707c1.jpg
            String surfacePlotLocal = KE_APP_ESS_URL_PRE + path + encrypt + ParserConstans.KE_APP_ESS_PIC_FIX;
            data.put(CommunityBaseInfoConstant.SURFACE_PLOT_LOCAL, surfacePlotLocal);
        }

        String noMarkSurfacePlot = String.valueOf(data.get(CommunityBaseInfoConstant.NO_MARK_SURFACE_PLOT));
        if (StringUtils.isNotBlank(noMarkSurfacePlot) && !"null".equals(noMarkSurfacePlot)) {
            encrypt = tranformPic(noMarkSurfacePlot, path, "3", "0", picKeylist);
            // http://ejufangdata.ess.ejucloud.cn/fang/110000/community/964a7d6294fe2de5f3e3347c800707c1.jpg
            String noMarkLocal = KE_APP_ESS_URL_PRE + path + encrypt + ParserConstans.KE_APP_ESS_PIC_FIX;
            data.put(CommunityBaseInfoConstant.NO_MARK_SURFACE_PLOT_LOCAL, noMarkLocal);
        }
        stringParserResult.setPictureList(picKeylist);
    */
    }

    /**
     * 小区基础信息解析
     *
     * @param mainData json
     * @param result   存储
     */
    private void parserBasicInfo(JSONObject mainData, JSONObject result) {
        JSONObject basicInfoObj = mainData.getJSONObject("basicInfo");
        communityBaseInfoKeyMapping.forEach((k, v) -> {
            result.put(v, basicInfoObj.getString(k));
        });
    }

    private void parserOtherInfo(JSONObject mainData, JSONObject result) {
        communityOtherInfoKeyMapping.forEach((k, v) -> {
            result.put(v, JSONUtils.getStringByKey(mainData, k));
        });

        JSONArray buttonListArr = mainData.getJSONObject("quotation").getJSONArray("buttonList");
        buttonListArr.forEach(o -> {
            JSONObject var = (JSONObject) o;
            String title = var.getString("title");
            String key = communityOtherInfoKeyMapping.get(title);
            if (StringUtils.isNotBlank(key)) {
                result.put(key, var.getString("desc"));
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        // 小区基础信息
        communityBaseInfoKeyMapping.put("name", "title");
        communityBaseInfoKeyMapping.put("id", "title_id");
        communityBaseInfoKeyMapping.put("districtName", "region");
        communityBaseInfoKeyMapping.put("bizcircleName", "plate");

        // 小区概要信息
        communityOverviewKeyMapping.put("建成年代", "build_year");
        communityOverviewKeyMapping.put("建筑类型", "build_type");
        communityOverviewKeyMapping.put("产权年限", "property_year");
        communityOverviewKeyMapping.put("开发企业", "build_developers");
        communityOverviewKeyMapping.put("供暖类型", "heating");
        communityOverviewKeyMapping.put("交易权属", "trading_rights");
        communityOverviewKeyMapping.put("用水类型", "water");
        communityOverviewKeyMapping.put("用电类型", "supply_electricity");
        communityOverviewKeyMapping.put("固定车位", "park_num");
        communityOverviewKeyMapping.put("停车费用", "fixed_charge");
        communityOverviewKeyMapping.put("燃气费用", "gas");
        communityOverviewKeyMapping.put("容积率", "plot_rate");
        communityOverviewKeyMapping.put("绿化率", "green_rate");
        communityOverviewKeyMapping.put("物业公司", "property_company");
        communityOverviewKeyMapping.put("物业费用", "property_price");
        communityOverviewKeyMapping.put("物业电话", "property_phone");

        // 小区其它信息
        communityOtherInfoKeyMapping.put("surroundings.desc", "address");
        communityOtherInfoKeyMapping.put("surroundings.centerLng", "lng");
        communityOtherInfoKeyMapping.put("surroundings.centerLat", "lat");
        communityOtherInfoKeyMapping.put("quotation.consultant.title", "average_price");
        communityOtherInfoKeyMapping.put("在售房源", "num_for_sale");
        communityOtherInfoKeyMapping.put("在租房源", "rent_num");

    }
}
