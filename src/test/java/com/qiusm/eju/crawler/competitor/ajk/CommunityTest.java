package com.qiusm.eju.crawler.competitor.ajk;

import com.qiusm.eju.crawler.HttpTest;
import com.qiusm.eju.crawler.entity.base.Community;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.service.base.ICommunityService;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import com.qiusm.eju.crawler.utils.ajk.AjkUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.qiusm.eju.crawler.utils.lang.DateUtils;
import com.qiusm.eju.crawler.utils.lang.FileUtils;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL1;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;

@Slf4j
@SpringBootTest
public class CommunityTest extends HttpTest {

    public static void main(String[] args) {
        // communityAvgPrice();
        nsignOfGet();
    }

    /**
     * 加密耗时测试
     */
    private static void nsignOfGet() {
        String url = String.format("https://api.anjuke.com/mobile/v5/sale/price/trend/report?id=%s&type=4&app=a-ajk&cv=15.13",
                "1234567");
        for (int i = 0; i < 100; i++) {
            try {
                Long s = DateUtils.getCurrentTimeMillis();
                AjkUtils.nsignOfGet(url);
                Long e = DateUtils.getCurrentTimeMillis();
                log.info("耗时：{}", e - s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void communityAvgPrice(String communityId) {
        OkHttpUtils httpUtils = OkHttpUtils.Builder()
                .proxyUrl(PROXY_URL1).charset(UTF8)
                .connectTimeout(60000).readTimeout(60000)
                .retryMax(10)
                .proxyRetryTag(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=,枌~怣秙".split(COMMA)))
                .builderHttp();

        String url = String.format("https://api.anjuke.com/mobile/v5/sale/price/trend/report?id=%s&type=4&app=a-ajk&cv=15.13",
                communityId);
        Map<String, String> map = new HashMap<>();
        String jsonStr;
        try {
            map.put("memberid", "0");
            map.put("clouduid", "0");
            map.put("user-agent", "a-ajk/15.13/Android-MI 6/android/9");
            map.put("nsign", AjkUtils.nsignOfGet(url));
            jsonStr = httpUtils.proxyGet(url, null, map);
            FileUtils.printFile(jsonStr, "source/ajk", "community_price_history_hz.txt", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Resource
    private ICommunityService communityService;
    @Resource
    private HashOperations<String, String, String> hashOperations;

    /**
     * 注入小区列表的服务，获取小区。
     */
    @Test
    public void communityAvgPrice2() {
        List<Community> communityList = communityService.getCommunityByCity("杭州", SourceTypeEnum.AJK.getCode());
        ThreadsUtils threadUtils = new ThreadsUtils();
        final String key = "crawler:community:price:ajk_hz";
        threadUtils.executeFutures(communityList, (community) -> {
            // 判断当前key的hash中是否有对应的key，有则return
            String value = hashOperations.get(key, community.getCommunityId());
            if (StringUtils.isNotBlank(value)) {
                return null;
            } else {
                communityAvgPrice(community.getCommunityId());
                hashOperations.put(key, community.getCommunityId(), community.getCommunity());
            }
            return null;
        }, 16);

    }
}
