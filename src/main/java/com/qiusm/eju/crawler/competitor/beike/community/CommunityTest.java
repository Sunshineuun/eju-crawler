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

    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder()
            .proxyUrl(PROXY_URL).charset(UTF8)
            .connectTimeout(60000).readTimeout(60000)
            .builderHttp();

    public static void main(String[] args) {
        test1();
    }

    /**
     *
     */
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
