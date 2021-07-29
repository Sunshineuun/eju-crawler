package com.qiusm.eju.crawler.competitor.beike;

import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import com.qiusm.eju.crawler.parser.competitor.base.IHttpSearch;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByPasswordV2;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.LoginByVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PicVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.login.PhoneVerifyCode;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.*;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.xiaoleilu.hutool.util.RandomUtil;

import java.util.*;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL0;
import static com.qiusm.eju.crawler.constant.head.BkHttpHeadConstant.LIANJIA_CITY_ID;

/**
 * 贝壳骨架数据抓取 test
 *
 * @author qiushengming
 */
public class CommunityTest extends BkTest {


    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL0).charset(UTF8)
            .connectTimeout(60000).readTimeout(60000)
            .builderHttp();

    private static final BkUser BK_USER = BkUser.builder()
            .phoneNo("15958624595")
            .password("qweasdzxc456")
            .deviceId("bfaa183c70b47690")
            .build();


    public static void main(String[] args) {
        /*loadCommunity().getResult().forEach((k, v) -> {
            buildingNumber(k).getResult().forEach((k1, v1) -> {
                unitNumber(k1).getResult().forEach((k2, v2) -> {
                    houseNumber(k2);
                });
            });
        });*/
        // buildingNumber();
        // unitNumber();
        // houseNumber();
//        imgVerification();
//        phoneVerification();
//        loginByVerifyCode();
        erShouFangSearch();
//        System.out.println(RandomUtil.simpleUUID());
    }

    static String input() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入：");
        // 等待输入值
        return input.next();
    }

    static void loginByPasswordV2() {
        IHttpSearch httpSearch = new LoginByPasswordV2();
        Map<String, String> params = new HashMap<>(1);
        params.put("pic_verify_code", "1082");
        RequestDto requestDto = RequestDto.builder()
                .user(BK_USER)
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        System.out.printf("loginByVerifyCode:%s\n", responseDto);
    }

    static void loginByVerifyCode() {
        IHttpSearch httpSearch = new LoginByVerifyCode();
        Map<String, String> params = new HashMap<>(1);
        params.put("pic_verify_code", "3023");
        params.put("verify_code", "739734");
        RequestDto requestDto = RequestDto.builder()
                .user(BK_USER)
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        System.out.printf("loginByVerifyCode:%s\n", responseDto);
    }

    static void imgVerification() {
        IHttpSearch httpSearch = new PicVerifyCode();
        Map<String, String> params = new HashMap<>(1);
        RequestDto requestDto = RequestDto.builder()
                .user(BK_USER)
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);
        FileUtils.printFile(responseDto.getResultByte(), "source", "1.png", false);
        System.out.printf("imgVerification:%s\n", responseDto);
    }

    /**
     * 发送验证码;
     * {"request_id":"64083d97-e980-4648-96cf-fe8eecfe79ce","uniqid":"010ACA160D1DDD9B9E7A0194597357AB","errno":0,"error":"操作成功","data":{},"cost":192}
     */
    static void phoneVerification() {
        IHttpSearch httpSearch = new PhoneVerifyCode();
        Map<String, String> params = new HashMap<>();
        params.put("pic_verify_code", "2070");

        RequestDto requestDto = RequestDto.builder()
                .user(BK_USER)
                .requestParam(params)
                .head(LIANJIA_CITY_ID, "310000")
                .build();

        ResponseDto responseDto = httpSearch.execute(requestDto);

        System.out.printf("phoneVerification:%s\n", requestDto);
        System.out.printf("phoneVerification:%s\n", responseDto);
    }

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
     * 小区详情url:https://app.api.ke.com/house/resblock/detailpart1?id=5011000019793 <br>
     */
    static void communityDetails() {
        String communityId = "5011000010212";
        String url = String.format("%s/house/resblock/detailpart1?id=%s", APP_DOMAIN_NAME, communityId);
        String htmlStr = httpUtils.proxyGet(url, "UTF-8", heads(url));
        System.out.println(htmlStr);
    }

    public static void test1() {
        String titleId = "5011000001662";
        //String url = String.format("https://app.api.ke.com/yezhu/publish/getBuildings?community_id=%s", titleId);
        String uuid = UUID.randomUUID().toString();
        String ssid = UUID.randomUUID().toString();
        String udid = RandomUtil.randomNumbers(15);
        //String cookie = String.format("lianjia_udid=%s;lianjia_ssid=%s;lianjia_uuid=%s", udid, ssid, uuid);
        String cookie = "lianjia_udid=bfaa183c70b47681;lianjia_token=2.0013dc2f3c74d767870271060dc0bb8ca1;lianjia_ssid=dcc78ae9-3457-4a9a-b28a-a82d758edb73;lianjia_uuid=359ab4e2-6e30-4cbe-a300-5c676b76c3ee";
        String url = "https://app.api.ke.com/yezhu/publish/getBuildings?community_id=5011000016384";
        Map<String, String> heads = new HashMap<>(4);

        heads.put("Cookie", cookie);
        heads.put("User-Agent", "Beike2.20.1;google Pixel; Android 8.1.0");
        heads.put("Authorization", BeikeUtils.authorization(url));
        heads.put("Lianjia-Version", "2.20.1");
        heads.put("Host", "app.api.ke.com");
        heads.put("Lianjia-City-Id", "110000");
        // 'Host': 'app.api.ke.com','Lianjia-City-Id': '110000','User-Agent': 'Beike2.20.1;google Pixel; Android 8.1.0',

        String htmlStr = httpUtils.get(url, "utf-8", heads);
        System.out.println(htmlStr);

    }
}
