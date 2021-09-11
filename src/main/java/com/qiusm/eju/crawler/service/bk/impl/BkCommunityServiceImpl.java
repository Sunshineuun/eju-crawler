package com.qiusm.eju.crawler.service.bk.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.Community;
import com.qiusm.eju.crawler.entity.base.CommunityDetail;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityDetailSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityListSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityPageListSearch;
import com.qiusm.eju.crawler.service.base.ICommunityDetailService;
import com.qiusm.eju.crawler.service.base.ICommunityService;
import com.qiusm.eju.crawler.service.bk.IBkCommunityService;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
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
    private BkAppCommunityPageListSearch pageListSearch;
    @Resource
    private BkAppCommunityListSearch listSearch;
    @Resource
    private BkAppCommunityDetailSearch detailSearch;
    @Resource
    private ICommunityDetailService communityDetailService;
    @Resource
    private ListOperations<String, String> listOperations;
    @Resource
    private ICommunityService communityService;

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
                toCommunityDb(communityList);
            }
        }
        return result;
    }

    @Override
    public boolean communityDetail(JSONObject community) {
        JSONObject communityDetail = detailHandler(community);
        communityDetail.putAll(community);
        communityDetail.put("region_code", community.getString("district_id"));
        communityDetail.put("plate_code", community.getString("bizcircle_id"));
        communityDetail.put("current_base_url", community.getString("detail_url"));
        communityDetail.put("sale", community.getString("num_for_sale"));
        communityDetail.put("rent", community.getString("rent_num"));

        CommunityDetail communityDetailObj = JSONObject.parseObject(communityDetail.toJSONString(), CommunityDetail.class);
        return communityDetailService.insertIfAbsent(communityDetailObj);
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

        ResponseDto responseDto = pageListSearch.execute(requestDto);
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

        ResponseDto responseDto = listSearch.execute(requestDto);

        return responseDto.getResult().getJSONArray("list");
    }

    JSONObject detailHandler(JSONObject community) {
        RequestDto requestDto = RequestDto.builder()
                .requestParam(COMMUNITY_ID, community.getString(COMMUNITY_ID))
                .build();

        ResponseDto responseDto = detailSearch.execute(requestDto);
        return responseDto.getResult();
    }

    /**
     * 存储到community表中
     *
     * @param communityList 小区列表
     */
    private void toCommunityDb(JSONArray communityList) {
        communityList.forEach(o -> {
            JSONObject var = (JSONObject) o;
            Community community = new Community();
            community.setSource("BK");
            community.setCommunity(var.getString("community_name"));
            community.setCommunityId(var.getString("community_id"));
            // ershoufang_avg_unit_price avg_unit_price 这两个字段取哪个比较好，等有实际数据再定。目前都是空值。
            community.setAvgPrice(var.getString("ershoufang_avg_unit_price"));
//community.setCommunityAdd();
            community.setCity(var.getString("city_name"));
            community.setCityId(var.getString("city_id"));
            community.setRegion(var.getString("district_name"));
            community.setRegionId(var.getString("district_id"));
            community.setPlate(var.getString("plate"));
            community.setPlateId(var.getString("bizcircle_id"));

            String bd = String.format("%s,%s", var.get("lng"), var.get("lat"));
            community.setBd(bd);

            String neoDesc = var.getString("neo_desc");
            if (StringUtils.contains(neoDesc, "/")) {
                String[] var1 = neoDesc.split("/");
                community.setSale(var1[0]);
                community.setRent(var1[1]);
            }

            community.setBuildType(var.getString("building_type"));
            communityService.checkInsert(community);
        });
    }
}
