package com.qiusm.eju.crawler.competitor.beike;

import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.community.BkAppCommunityDetailSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.BuildingSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.CommunitySearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.HouseSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.UnitSearchV1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.ajk.AjkFieldConstant.COMMUNITY_ID;


/**
 * 贝壳骨架数据抓取 test
 *
 * @author qiushengming
 */
@Slf4j
@SpringBootTest
public class CommunityTest extends BkTest {

    @Autowired
    private BkAppCommunityDetailSearch communityDetailSearch;

    /**
     * 搜索接口 <br>
     * 1. 注意该接口参数不能转码，转码后通过url获取授权码就不行了。 <br>
     * 2. 注意编码，请求头【Accept-Encoding】一定要设置为utf8 <br>
     * <p>
     * 问题：这个是搜小区的还是搜索什么的？ <br>
     */
    public static void erShouFangSearch() {
        // String url = APP_DOMAIN_NAME + "/house/ershoufang/searchv5?condition=rs安墁西郊&refer=ershoulistsearch&containerType=1&limitCount=20&limitOffset=0&from=hot_click&fullFilters=1&cityId=310000";
        String url = "https://app.api.ke.com/house/house/moreinfo?house_code=107103747153";
        String htmlStr = httpUtils.proxyGet(url, "UTF-8", heads(url));
        System.out.println(htmlStr);
    }

    /**
     * 找小区：/house/community/searchv2?limit_offset=0&condition=&city_id=110000&containerType=0&limit_count=20 <br>
     * <p>
     * 小区名称模糊搜索：/house/suggestion/index?city_id=310000&query=%s&channel_id=ershoufang <br>
     * /house/suggestion/index?city_id=310000&query=%s&channel_id=xiaoqu <br>
     * <p>
     * channel_id=xiaoqu，可能的值xiaoqu\ershoufang。
     */
    static ResponseDto loadCommunity() {
        IHttpSearch httpSearch = new CommunitySearchV1();
        Map<String, String> params = new HashMap<>();
        params.put("city_id", "310000");
        params.put("key", "远洋香奈");
        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("loadCommunity:%s\n", responseDto);
        return responseDto;
    }

    /**
     * 楼栋号接口 <br>
     * 卖房：小区查询接口：/yezhu/publish/getCommunities?city_id=310000&query=%E8%BF%9C%E6%B4%8B&hide_yj=0; 返回小区简要信息 <br>
     * 卖房：楼栋信息接口：/yezhu/publish/getBuildings?community_id=5011000002700；取building_name和building_id字段 <br>
     * 卖房：单元信息接口：/yezhu/publish/getUnits?building_id=5012000243156，取unit_name和unit_id <br>
     * 卖房：房号信息接口：/yezhu/publish/getHouses?unit_id=5013000243156，取
     */
    static ResponseDto buildingNumber(String communityId) {
        IHttpSearch httpSearch = new BuildingSearchV1();
        Map<String, String> params = new HashMap<>(4);
        params.put("city_id", "310000");
        params.put("key", "远洋");
        params.put("community_id", communityId);

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("buildingNumber:%s\n", responseDto);
        return responseDto;
    }

    static ResponseDto unitNumber(String buildingId) {
        IHttpSearch httpSearch = new UnitSearchV1();
        Map<String, String> params = new HashMap<>(4);
        params.put("city_id", "310000");
        params.put("key", "远洋");
        params.put("community_id", "5011000016012");
        params.put("building_id", buildingId);

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("unitNumber:%s\n", responseDto);
        return responseDto;
    }

    static void houseNumber(String unitId) {
        IHttpSearch httpSearch = new HouseSearchV1();
        Map<String, String> params = new HashMap<>(4);
        params.put("city_id", "310000");
        params.put("key", "远洋");
        params.put("community_id", "5011000016012");
        params.put("building_id", "5012000046380");
        params.put("unit_id", unitId);

        RequestDto requestDto = RequestDto.builder()
                .requestParam(params)
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("houseNumber:%s\n", responseDto);
    }

    /**
     * 小区详情抓取测试 <br>
     * 面向url:https://app.api.ke.com/house/resblock/detailpart1?id=5011000019793 <br>
     */
    @Test
    void communityDetails() {
        String communityId = "5011000010212";
        RequestDto requestDto = RequestDto.builder()
                .requestParam(COMMUNITY_ID, communityId)
                .build();

        ResponseDto responseDto = communityDetailSearch.execute(requestDto);
        log.info("{}", responseDto);
    }
}
