package com.qiusm.eju.crawler.utils.http;

import org.apache.http.HttpHost;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

class SingleOkHttpConfig {

    static final String S = ":";
    static final String GET = "get";
    static final String POST_FROM = "post-from";
    static final String POST_JSON = "post-json";
    static final String USER_AGENT = "User-Agent";
    static final String RESPONSE_HEADERS = "responseHeaders";
    static final String CURR_PROXY_IP = "ProxyIp";
    static final Random RANDOM = new Random();
    static final X509TrustManager TRUST_ALL_MANAGER = getTrustAllManager();
    static final HostnameVerifier HOSTNAME_VERIFIER = getHostnameVerifier();
    static final SSLSocketFactory SSL_SOCKET_FACTORY = createTrustAllSSLFactory(TRUST_ALL_MANAGER);

    static final int PROXY_RETRY_MAX = 100;
    static final int WAIL_PROXY_TIMEOUT = 200;
    static final ReentrantLock LOCK = new ReentrantLock();
    static final List<String> pcList = new ArrayList<>();
    static final List<String> mbList = new ArrayList<>();
    static final ConcurrentLinkedQueue<HttpHost> IP_LIST = new ConcurrentLinkedQueue<>();

    static final int INT999 = 999;
    static final int INT901 = 901;
    static final int INT902 = 902;
    static final int INT903 = 903;
    static final int INT904 = 904;
    static final int INT905 = 905;
    static final int INT906 = 906;
    static final String APPLICATION_JSON_CHARSET = "application/json; charset=";
    static final String EJU_RESPONSE_CODE_EXCEPTION_PROXY_CODE = "ResponseCode=%s proxy=%s msg=%s";
    static final String EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR = "ResponseError=%s proxy=%s error=%s";
    static final String NO_SUPPORT_REQUEST_OK_HTTP_UTILS_TYPE = "no support request OK_HTTP_UTILS type:";

    SingleOkHttpConfig() {
    }

    static {
        pcList.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.11 (KHTML, like Gecko) Ubuntu/11.10 Chromium/27.0.1453.93 Chrome/27.0.1453.93 Safari/537.36");
        pcList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
        pcList.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.94 Safari/537.36");
        pcList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:21.0) Gecko/20100101 Firefox/21.0");
        pcList.add("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:21.0) Gecko/20130331 Firefox/21.0");
        pcList.add("Mozilla/5.0 (Windows NT 6.2; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
        pcList.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)");
        pcList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
        pcList.add("Mozilla/5.0 (compatible; WOW64; MSIE 10.0; Windows NT 6.2)");
        pcList.add("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.9.168 Version/11.52");
        pcList.add("Opera/9.80 (Windows NT 6.1; WOW64; U; en) Presto/2.10.229 Version/11.62");
        pcList.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; en-US) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        pcList.add("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        pcList.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.5.3.18599");
        pcList.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9b4) Gecko/2008030317 Firefox/3.0b4");


        mbList.add("Mozilla/5.0 (Linux; U; Android 8.1.0; zh-CN; EML-AL00 Build/HUAWEIEML-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 UCBrowser/11.9.4.974 UWS/2.13.1.48 Mobile Safari/537.36 AliApp(DingTalk/4.5.11) com.alibaba.android.rimet/10487439 Channel/227200 language/zh-CN");
        mbList.add("Mozilla/5.0 (Linux; U; Android 8.0.0; zh-CN; MI 5 Build/OPR1.170623.032) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 UCBrowser/11.8.9.969 Mobile Safari/537.36");
        mbList.add("Mozilla/5.0 (Linux; U; Android 8.0.0; zh-cn; SM-N9500 Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.8 Mobile Safari/537.36");
        mbList.add("Mozilla/5.0 (Linux; U; Android 5.1.1; zh-CN; OPPO R9 Plusm A Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 UCBrowser/12.1.4.994 Mobile Safari/537.36");
        mbList.add("Mozilla/5.0 (Linux; Android 7.1.1; OPPO R11s Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044205 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x26070234) NetType/WIFI Language/zh_CN");
        mbList.add("Mozilla/5.0 (iPhone 6p; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 MQQBrowser/8.0.0 Mobile/16A5357b Safari/8536.25 MttCustomUA/2 QBWebViewType/1 WKType/1");
        mbList.add("Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 MicroMessenger/6.7.3(0x16070321) NetType/WIFI Language/zh_CN");
        mbList.add("Mozilla/5.0 (Linux; U; Android 7.1.2; zh-CN; vivo X9 Build/N2G47H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/40.0.2214.89 UCBrowser/11.4.1.939 UCBS/2.10.1.8 Mobile Safari/537.36 AliApp(TB/6.8.6) WindVane/8.0.0 1080X1920 GCanvas/1.4.2.21");
        mbList.add("Mozilla/5.0 (Linux; Android 7.1.2; vivo X9 Build/N2G47H; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044303 Mobile Safari/537.36 V1_AND_SQ_7.8.0_922_YYB_D QQ/7.8.0.3740 NetType/WIFI WebP/0.3.0 Pixel/1080 StatusBarHeight/72");
        mbList.add("Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; Redmi 6 Pro Build/OPM1.171019.019) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.128 Mobile Safari/537.36 XiaoMi/MiuiBrowser/9.8.7");
        mbList.add("Mozilla/5.0 (Linux; Android 7.0; Redmi Note 4X Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/WIFI Language/zh_CN Process/tools");
        mbList.add("Mozilla/5.0 (Linux; U; Android 7.0; zh-cn; FRD-AL10 Build/HUAWEIFRD-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.8 Mobile Safari/537.36");

    }

    static List<String> getPcList() {
        return pcList;
    }

    static String randomPC() {
        return pcList.get(RANDOM.nextInt(pcList.size()));
    }

    static String randomMOBILE() {
        return mbList.get(RANDOM.nextInt(mbList.size()));
    }

    /**
     * 获取这个SSLSocketFactory
     */
    static SSLSocketFactory createTrustAllSSLFactory(X509TrustManager trustAllManager) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trustAllManager}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return ssfFactory;
    }

    /**
     * 获取TrustManager
     */
    static X509TrustManager getTrustAllManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    /**
     * 获取HostnameVerifier
     */
    static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
    }


}