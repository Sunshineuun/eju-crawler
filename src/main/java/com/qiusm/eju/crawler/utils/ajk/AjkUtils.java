package com.qiusm.eju.crawler.utils.ajk;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okio.Buffer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AjkUtils implements InitializingBean {

    @Value("${eju.ajk.osPath:ajk}")
    private String osPath;

    private static AjkJniHookHandler AJK_JNI_HOOK_HANDLER;

    public static void createJniHookHandler(String osPath) {
        AJK_JNI_HOOK_HANDLER = new AjkJniHookHandler(osPath);
    }

    /**
     * 虚拟环境会抛出未知异常，注意捕获
     *
     * @param url1 akj url
     * @return 加密后的结果
     * @throws Exception 未知异常
     */
    public synchronized static String nsignOfGet(String url1) throws Exception {
        String url = URLDecoder.decode(url1, "UTF-8");
        String regex = "anjuke.com([\\s\\S]*?)\\?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        String path = "";
        if (m.find()) {
            path = m.group(1).trim();
        }
        //        String args1 = "/mobile/v5/houseprice/deal/list";
        String args1 = path;
        byte[] lAE = new byte[0];
        String args2 = t(lAE);
        Map<String, String> map = new HashMap<>();
        String param = url.split("\\?")[1];
        for (int i = 0; i < param.split("&").length; i++) {
            String key = "";
            String value = "";
            if (param.split("&")[i].split("=").length == 2) {
                key = param.split("&")[i].split("=")[0];
                value = param.split("&")[i].split("=")[1];
            } else {
                key = param.split("&")[i].split("=")[0];
            }
            map.put(key, value);
        }

        for (String next : map.keySet()) {
            if (map.get(next) == null) {
                map.put(next, "");
            }
        }
        HashMap hashMap = new HashMap();
        for (String next2 : map.keySet()) {
            hashMap.put(next2, map.get(next2).getBytes(StandardCharsets.UTF_8));
        }

        String args4 = UUID.randomUUID().toString();
        int args5 = 0;
        return AJK_JNI_HOOK_HANDLER.getSign0(args1, args2, hashMap, args4, args5);
    }

    public static String t(byte[] bArr) {
        try {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte[] digest = MessageDigest.getInstance("MD5").digest(bArr);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(cArr[(b & 240) >>> 4]);
                sb.append(cArr[b & ExprCommon.mPi]);
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    private static byte[] c(Map<String, String> resbody) {
        RequestBody body = new FormBody.Builder()
                .add("key", resbody.get("key"))
                .add("键", "值")
                .build();
        try (Buffer buffer = new Buffer()) {
            body.writeTo(buffer);
            return buffer.readByteArray();
        } catch (IOException unused) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "https://fang-sydc.anjuke.com/app/mainpage/recommend/business/biByTab?tabTitle=%E6%8E%A8%E8%8D%90&tabAlias=tuijian&localname=sh&os=android&signature=381fcfe3618cc614201757c7c32ef202&format=json&localName=sh&curVer=15.13&appId=1&action=getListInfo&imei=654967177695721&page=1&filterParams=&sidDict=&ts=1624602880941&origin_mac=&app=a-ajk&_pid=22774&_guid=e7f8ee30-f98f-485d-8d2f-875f7ee94f4a&macid=e101a87abcf15692&version_code=322064&i=654967177695721&m=Android-MI%206&uuid=5116f100-492e-4774-8930-2a632042786a&manufacturer=Xiaomi&o=sagit-user%209%20PKQ1.190118.001%20V11.0.5.0.PCACNXM%20release-keys&qtime=20210625143440&cv=15.13&origin_imei=654967177695721&v=9&ajk_city_id=11&from=mobile&pm=b135&androidid=e101a87abcf15692&_chat_id=&oaid=60dcdbf28fc62fbe&cid=11&ajk_sign=51667aad5f648e68773d730d80121d25";
        String deurl = URLDecoder.decode(url, "UTF-8");
        String sig = nsignOfGet(deurl);
        System.out.println(sig);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AJK_JNI_HOOK_HANDLER = new AjkJniHookHandler(osPath);
    }
}
