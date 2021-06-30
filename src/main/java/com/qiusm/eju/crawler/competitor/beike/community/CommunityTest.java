package com.qiusm.eju.crawler.competitor.beike.community;

import com.qiusm.eju.crawler.competitor.beike.utils.BeikeUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.xiaoleilu.hutool.util.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL;

/**
 * @author qiushengming
 */
public class CommunityTest {

    static final String DOMAIN_NAME = "https://app.api.ke.com";

    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL).charset(UTF8)
            .connectTimeout(60000).readTimeout(60000)
            .builderHttp();

    public static void main(String[] args) {
        buildingNumber();
    }

    /**
     * 搜索接口 <br>
     * 1. 注意该接口参数不能转码，转码后通过url获取授权码就不行了。 <br>
     * 2. 注意编码，请求头【Accept-Encoding】一定要设置为utf8 <br>
     * <p>
     * 问题：这个是搜小区的还是搜索什么的？ <br>
     */
    public static void erShouFangSearch() {
        String url = DOMAIN_NAME + "/house/ershoufang/searchv5?condition=rs安墁西郊&refer=ershoulistsearch&containerType=1&limitCount=20&limitOffset=0&from=hot_click&fullFilters=1&cityId=310000";
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
    static void loadCommunity() {
        String cityId = "310000";
        String communityId = "5011000010212";
        String communityName = "远洋";
        String url = String.format("%s/house/community/searchv2?limit_offset=0&condition=c%s&city_id=%s&containerType=0&limit_count=20", DOMAIN_NAME, communityId, cityId);
//        String url = String.format("%s/house/suggestion/index?city_id=%s&query=%s&channel_id=xiaoqu", DOMAIN_NAME, cityId, communityName);
        String htmlStr = httpUtils.proxyGet(url, "UTF-8", heads(url));
        System.out.println(htmlStr);
    }

    /**
     * 小区详情url:https://app.api.ke.com/house/resblock/detailpart1?id=5011000019793 <br>
     */
    static void communityDetails() {
        String communityId = "5011000010212";
        String url = String.format("%s/house/resblock/detailpart1?id=%s", DOMAIN_NAME, communityId);
        String htmlStr = httpUtils.proxyGet(url, "UTF-8", heads(url));
        System.out.println(htmlStr);
    }

    /**
     * 楼栋号接口 <br>
     * 卖房：小区查询接口：/yezhu/publish/getCommunities?city_id=310000&query=%E8%BF%9C%E6%B4%8B&hide_yj=0; 返回小区简要信息 <br>
     * 卖房：楼栋信息接口：/yezhu/publish/getBuildings?community_id=5011000002700；取building_name和building_id字段 <br>
     * 卖房：单元信息接口：/yezhu/publish/getUnits?building_id=5012000243156，取unit_name和unit_id <br>
     * 卖房：房号信息接口：/yezhu/publish/getHouses?unit_id=5013000243156，取
     */
    static void buildingNumber() {
        String communityId = "5011000010212";
        String url = "https://app.api.ke.com/yezhu/publish/getBuildings?community_id=" + communityId;
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

    static Map<String, String> heads(String url) {
        Map<String, String> heads = new HashMap<>(16);
        heads.put("Accept", "application/json");
        heads.put("Accept-Encoding", "utf-8");
        heads.put("Referer", "ershoulistsearch");
        heads.put("Cookie", "lianjia_udid=bfaa183c70b47681;lianjia_token=2.0014c2fed773f3c0cf056fd7e6568b1c66;lianjia_ssid=97b3ff8b-cfee-4633-8418-21c3802e2d83;lianjia_uuid=359ab4e2-6e30-4cbe-a300-5c676b76c3ee");
        heads.put("Lianjia-City-Id", "310000");
        heads.put("User-Agent", "Beike2.20.1;google Pixel; Android 8.1.0");
        heads.put("Lianjia-Channel", "Android_ke_wandoujia");
        heads.put("Lianjia-Version", "2.20.1");
        heads.put("Authorization", BeikeUtils.authorization(url));
        heads.put("Lianjia-Im-Version", "2.34.0");
        heads.put("Host", "app.api.ke.com");
        heads.put("Connection", "Keep-Alive");
        return heads;
    }
}
