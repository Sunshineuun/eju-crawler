package com.qiusm.eju.crawler.competitor.beike.utils;

import android.util.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.*;

import static com.qiusm.eju.crawler.constant.NumberConstant.NUM1;
import static com.qiusm.eju.crawler.constant.NumberConstant.NUM2;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.AMPERSAND;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.EQUAL_SIGN;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author qiushengming
 */
@Slf4j
public class BeikeUtils {

    public static void main(String[] args) {

        // MjAxODAxMTFfYW5kcm9pZDpmYWI1ZmEzNTMwNzYyNWM1ZmY0NDYwNGQ2OGQzMTcyZWQ5ODI3NTE2
        String url = "https://app.api.ke.com/user/account/sendverifycodeforbindmobilev2?mobile_phone_no=13341702682&pic_verify_code=5066";
        System.out.println(authorization(url));;
    }

    /**
     * 将URL中携带的参数转换为map； <br>
     * 从
     *
     * @param url get请求的url
     * @return 请求参数
     */
    public static Map<String, String> urlToParams(String url) {
        Map<String, String> resMap = new HashMap<>(8);
        String[] split = url.split("\\?");
        if (split.length == NUM2) {
            for (String s : split[NUM1].split(AMPERSAND)) {
                String[] param = s.split(EQUAL_SIGN);
                String key = param[0];
                String value = String.join(EQUAL_SIGN, Arrays.copyOfRange(param, 1, param.length));
                resMap.put(key, value);
            }
        }
        return resMap;
    }

    /**
     * 对 url 进行授权
     *
     * @param url url
     * @return 授权信息
     */
    public static String authorization(String url) {

        Map<String, String> hashMap = urlToParams(url);

        // 根据key进行排序
        List<Map.Entry<String, String>> arrayList = new ArrayList<>(hashMap.entrySet());
        arrayList.sort(Map.Entry.comparingByKey());

        //JniClient.GetAppSecret(APPConfigHelper.c());
        String appSecret = "d5e343d453aecca8b14b2dc687c381ca";
        //JniClient.GetAppId(APPConfigHelper.c());
        String appId = "20180111_android";
        StringBuilder paramsSb = new StringBuilder(appSecret);
        for (Map.Entry<String, String> entry : arrayList) {
            paramsSb.append(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
        StringBuilder signOriginSb = new StringBuilder("sign origin=");
        signOriginSb.append(paramsSb);

        url = digestSha1(paramsSb.toString());
        signOriginSb = new StringBuilder();
        signOriginSb.append(appId);
        signOriginSb.append(":");
        signOriginSb.append(url);

        url = Base64.encodeToString(signOriginSb.toString().getBytes(), 2);
        return url;
    }

    /**
     * 将字符串已SHA-1，进行加密
     *
     * @param str 被加密的字符串
     * @return 加密后的字符串
     */
    public static String digestSha1(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes(UTF_8));
            byte[] digest = instance.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 0xff);
                if (toHexString.length() < 2) {
                    sb.append(0);
                }
                sb.append(toHexString);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String sign(String var1, String var2) {
        Exception e;
        log.info("stringToSign: {},{}", var1, var2);

        String var3 = "";
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(new SecretKeySpec(var2.getBytes(), "HmacSHA256"));
            var1 = Base64.encodeToString(instance.doFinal(var1.getBytes()), 0);
            try {
                return var1.replaceAll("\n", "");
            } catch (Exception e2) {
                e = e2;
                var3 = var1;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            log.error("HmacSHA256 signature error: {}", e.getMessage());
            return var3;
        }
        return null;
    }
}
