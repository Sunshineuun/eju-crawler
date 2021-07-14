package com.qiusm.eju.crawler.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.base.dao.CrawlerUrlMapper;
import com.qiusm.eju.crawler.base.entity.CrawlerUrl;
import com.qiusm.eju.crawler.constant.EjuConstant;
import com.qiusm.eju.crawler.government.base.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Resource;
import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.utils.http.SeleniumDownload.SingleOkHttpConfig.BUILDER;

/**
 * @author qiushengming
 */
@Slf4j
public class SeleniumDownload implements Download {

    /**
     * 驱动器的路径
     */
    private static final String CHROME_DRIVER_EXE_PROPERTY = "C:/chrome/chromedriver.exe";
    /**
     * Chrome运行日志存储的路径
     */
    private static final String CHROME_DRIVER_LOG_PROPERTY = "C:/chrome/chromedriver.log";

    private static final OkHttpUtils HTTP_CLIENT = CommonUtils.createHttpClient();

    private final ChromeDriverService driverService;

    @Resource
    private CrawlerUrlMapper urlMapper;

    /**
     * 驱动 driver存在重复创建的可能性
     */
    private ChromeDriver driver;


    public SeleniumDownload() {
        this(Boolean.FALSE);
    }

    public SeleniumDownload(Boolean isProxy) {
        // 设置环境变量，chrome驱动的文件路径
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_EXE_PROPERTY);
        // 设置环境变量，chrome驱动运行的日志文件存储地址
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, CHROME_DRIVER_LOG_PROPERTY);
        this.driverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(CHROME_DRIVER_EXE_PROPERTY)) // chrome驱动的文件路径
                .withLogFile(new File(CHROME_DRIVER_LOG_PROPERTY)) // chrome驱动运行日志存储位置
                .usingPort(8000) // 端口
                .build();
        try {
            driverService.start();
        } catch (IOException e) {
            log.error("DriverService{}", e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public String get(String url) {
        if (this.driver == null) {
            this.driver = createDriver(false);
        }
        this.driver.get(url);
        String html = driver.getPageSource();
        // urlMapper.insert(new CrawlerUrl(url, "chromeDrive", "1"));
        return html;
    }

    @Override
    public String proxyGet(String url, Map<String, String> head) {
        this.driver = createDriver(true);
        driver.get(url);
        String html = driver.getPageSource();
        return html;
    }


    @Override
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * @param isProxy 是否启用代理
     * @return ChromeDriver
     */
    private ChromeDriver createDriver(Boolean isProxy) {
        ChromeOptions options = createOptions(isProxy);

        /*ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        DriverService driverService = new ChromeDriverService(driverPath, 8000, );*/

        ChromeDriver driver = new ChromeDriver(driverService, options);

        // 进行隐式等待，等待1分钟
        driver.manage().timeouts()
                .implicitlyWait(1, TimeUnit.MINUTES)
                .pageLoadTimeout(1, TimeUnit.MINUTES)
                .setScriptTimeout(1, TimeUnit.MINUTES);

        return driver;
    }

    private ChromeOptions createOptions(Boolean isProxy) {
        ChromeOptions options = new ChromeOptions();
        // 代理设置,需要有一个代理池提供IP和端口
        if (isProxy) {
            Map<String, String> head = new HashMap<>(8);
            String proxyServer = "--proxy-server=" + getHttpHost(head).toString();
            options.addArguments(proxyServer);
            head.forEach((k, v) -> {
                options.addArguments(k + "=\"" + v + "\"");
            });
            log.info(proxyServer);
        }

        Map<String, Object> prefs = new LinkedHashMap<>();
        // 禁用图片
        // prefs.put("profile.managed_default_content_settings.images", 2);
        // 设置文件下载存储的路径
        prefs.put("download.default_directory", "D:\\Medlive");
        // options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("prefs", prefs);
        // 使用headless无界面浏览器模式.这样可以避免浏览器资源的消耗
        // options.addArguments("--headless", "--disable-gpu");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("disable-infobars");
        options.addArguments("auto-open-devtools-for-tabs");

        return options;
    }

    private HttpHost getHttpHost(Map<String, String> header) {
        int x = 0;
        HttpHost httpHost;
        do {
            httpHost = SeleniumDownload.SingleOkHttpConfig.IP_LIST.poll();
            //请求代理池获取ip
            if (StringUtils.isNotBlank(BUILDER.proxyUrl) && SeleniumDownload.SingleOkHttpConfig.IP_LIST.size() <= BUILDER.proxyLessThan) {
                try {
                    SeleniumDownload.SingleOkHttpConfig.LOCK.lock();
                    if (SeleniumDownload.SingleOkHttpConfig.IP_LIST.size() <= BUILDER.proxyLessThan) {
                        String body = HTTP_CLIENT.get(BUILDER.proxyUrl, UTF8, null);
                        if (StringUtils.isNotBlank(body) && body.contains(SeleniumDownload.SingleOkHttpConfig.S) && !body.contains("html")) {
                            String[] ips = body.split(BUILDER.proxySeparator);
                            String[] ipAndPort = ips[0].split(SeleniumDownload.SingleOkHttpConfig.S);
                            int y = 0;
                            if (httpHost == null) {
                                httpHost = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                                y = 1;
                            }
                            while (y < ips.length) {
                                ipAndPort = ips[y].split(SeleniumDownload.SingleOkHttpConfig.S);
                                SeleniumDownload.SingleOkHttpConfig.IP_LIST.offer(new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
                                y++;
                            }
                        } else {
                            try {
                                TimeUnit.MICROSECONDS.sleep(6 * SeleniumDownload.SingleOkHttpConfig.WAIL_PROXY_TIMEOUT);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        httpHost = SeleniumDownload.SingleOkHttpConfig.IP_LIST.poll();
                    }
                } finally {
                    SeleniumDownload.SingleOkHttpConfig.LOCK.unlock();
                }
            }
            if (httpHost == null) {
                httpHost = SeleniumDownload.SingleOkHttpConfig.IP_LIST.poll();
                if (httpHost == null && JSON.parseObject(BUILDER.proxyJsonDefault) != null) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(SeleniumDownload.SingleOkHttpConfig.WAIL_PROXY_TIMEOUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    JSONObject jsonObject = JSON.parseObject(BUILDER.proxyJsonDefault);
                    if (header != null) {
                        jsonObject.getJSONObject("header").entrySet().forEach(e -> {
                            header.put(e.getKey(), e.getValue() + "");
                        });
                    }
                    httpHost = new HttpHost(jsonObject.getString("hostname"), jsonObject.getInteger("port"));
                } else {
                    x++;
                }
            }

        } while (httpHost == null && x < SeleniumDownload.SingleOkHttpConfig.PROXY_RETRY_MAX);
        return httpHost;
    }

    public static SeleniumDownload.Builder Builder() {
        return SeleniumDownload.SingleOkHttpConfig.BUILDER;
    }

    /**
     * Builder
     */
    public static final class Builder {
        private static final String PROXY_JSON_DEFAULT = "{'hostname':'transfer.mogumiao.com','port':9001,'scheme':'https','header':{'Proxy-Authorization':'Basic QzNjSEpJMVpXOUVxOGpRQTpGZzZvQmczd05tTjJwc1JO'}}";
        int proxyLessThan;
        int connectTimeout;
        int readTimeout;
        int retryMax;
        boolean retryEnable;
        String charset;
        String proxyJsonDefault;
        String proxyUrl;
        String proxySeparator;
        List<String> proxyRetryTag;

        private Builder() {
            this.proxyLessThan = 3;
            this.connectTimeout = 3000;
            this.readTimeout = 3000;
            this.retryMax = 3;
            this.retryEnable = true;
            this.charset = "utf-8";
            this.proxyJsonDefault = PROXY_JSON_DEFAULT;
            this.proxyUrl = null;
            this.proxySeparator = "\n";
            this.proxyRetryTag = new ArrayList<>();
            this.proxyRetryTag.add("ResponseError=");
            this.proxyRetryTag.add("ResponseCode=");
        }

        public SeleniumDownload.Builder connectTimeout(int connectTimeout) {
            if (connectTimeout < 0L) {
                throw new IllegalArgumentException(connectTimeout + " < 0");
            }
            this.connectTimeout = connectTimeout;
            return this;
        }

        public SeleniumDownload.Builder readTimeout(int readTimeout) {
            if (connectTimeout < 0L) {
                throw new IllegalArgumentException(connectTimeout + " < 0");
            }
            this.readTimeout = readTimeout;
            return this;
        }

        public SeleniumDownload.Builder retryDisable() {
            this.retryEnable = false;
            return this;
        }

        public SeleniumDownload.Builder retryMax(int retryMax) {
            if (this.retryMax < 0) {
                throw new NullPointerException("retryMax < " + retryMax);
            }
            this.retryMax = retryMax;
            return this;
        }

        public SeleniumDownload.Builder addUserAgent(String userAgent) {
            if (userAgent == null) {
                throw new NullPointerException("userAgent == null");
            }
            SeleniumDownload.SingleOkHttpConfig.pcList.add(userAgent);
            return this;
        }

        public SeleniumDownload.Builder charset(String Charset) {
            if (Charset == null || "".equals(Charset.trim())) {
                throw new NullPointerException("charset == null");
            }
            this.charset = Charset;
            return this;
        }

        public SeleniumDownload.Builder proxyUrl(String proxyUrl) {
            if (proxyUrl == null || "".equals(proxyUrl.trim())) {
                throw new NullPointerException("proxyUrl == null");
            }
            this.proxyUrl = proxyUrl;
            return this;
        }

        public SeleniumDownload.Builder proxySeparator(String proxySeparator) {
            if (proxySeparator == null) {
                throw new NullPointerException("proxySeparator == null");
            }
            this.proxySeparator = proxySeparator;
            return this;
        }

        public SeleniumDownload.Builder proxyRetryTag(List<String> proxyRetryTag) {
            if (proxyRetryTag == null) {
                throw new NullPointerException("proxyRetryTag == null");
            }
            this.proxyRetryTag.addAll(proxyRetryTag);
            return this;
        }

        public SeleniumDownload.Builder addProxyRetryTag(String proxyRetryTag) {
            if (this.proxyRetryTag == null) {
                throw new NullPointerException("proxyRetryTag == null");
            }
            this.proxyRetryTag.add(proxyRetryTag);
            return this;
        }

        public SeleniumDownload.Builder proxyJsonDefault(String proxyJsonDefault) {
            if (proxyJsonDefault != null
                    && (proxyJsonDefault.indexOf("hostname") == -1
                    || proxyJsonDefault.indexOf("port") == -1
                    || proxyJsonDefault.indexOf("header") == -1)) {
                throw new IllegalArgumentException("proxyJsonDefault is json , required attribute:hostname、port、header【json】== null");
            }
            this.proxyJsonDefault = proxyJsonDefault;
            return this;
        }

        public SeleniumDownload builder() {
            return SeleniumDownload.SingleOkHttpConfig.OK_HTTP_UTILS;
        }

    }

    /**
     * OkHttpConfig
     */
    public static final class SingleOkHttpConfig {

        public static final String S = ":";
        public static final String GET = "get";
        public static final String USER_AGENT = "User-Agent";
        public static final String RESPONSE_HEADERS = "responseHeaders";
        public static final String CURR_PROXY_IP = "ProxyIp";
        public static final Random RANDOM = new Random();
        public static final SeleniumDownload.Builder BUILDER = new SeleniumDownload.Builder();
        public static final X509TrustManager TRUST_ALL_MANAGER = getTrustAllManager();
        public static final HostnameVerifier HOSTNAME_VERIFIER = getHostnameVerifier();
        public static final SSLSocketFactory SSL_SOCKET_FACTORY = createTrustAllSSLFactory(TRUST_ALL_MANAGER);
        public static final okhttp3.OkHttpClient OK_HTT = new OkHttpClient.Builder()
                .hostnameVerifier(HOSTNAME_VERIFIER)
                .sslSocketFactory(SSL_SOCKET_FACTORY, TRUST_ALL_MANAGER)
                .followRedirects(BUILDER.retryEnable)
                .followSslRedirects(BUILDER.retryEnable)
                .connectTimeout(BUILDER.connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(BUILDER.readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(BUILDER.readTimeout, TimeUnit.MILLISECONDS)
                .build();

        private static final int PROXY_RETRY_MAX = 100;
        private static final int WAIL_PROXY_TIMEOUT = 200;
        private static final ReentrantLock LOCK = new ReentrantLock();
        private static final List<String> pcList = new ArrayList<>();
        /*private static final List<String> mbList = new ArrayList<>();*/
        private static final SeleniumDownload OK_HTTP_UTILS = new SeleniumDownload();
        private static final ConcurrentLinkedQueue<HttpHost> IP_LIST = new ConcurrentLinkedQueue<>();

        private static final int INT999 = 999;
        private static final int INT901 = 901;
        private static final int INT902 = 902;
        private static final int INT903 = 903;
        private static final int INT904 = 904;
        private static final int INT905 = 905;
        private static final int INT906 = 906;
        private static final String APPLICATION_JSON_CHARSET = "application/json; charset=";
        private static final String EJU_RESPONSE_CODE_EXCEPTION_PROXY_CODE = "ResponseCode=%s proxy=%s msg=%s";
        private static final String EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR = "ResponseError=%s proxy=%s error=%s";
        private static final String NO_SUPPORT_REQUEST_OK_HTTP_UTILS_TYPE = "no support request OK_HTTP_UTILS type:";

        private SingleOkHttpConfig() {
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

            /*
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
            */
        }

        public static String randomPC() {
            return pcList.get(RANDOM.nextInt(pcList.size()));
        }

        /*public static String randomMOBILE(){
            return mbList.get(RANDOM.nextInt(mbList.size()));
        }*/

        /**
         * 获取这个SSLSocketFactory
         */
        private static SSLSocketFactory createTrustAllSSLFactory(X509TrustManager trustAllManager) {
            SSLSocketFactory ssfFactory = null;
            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{trustAllManager}, new SecureRandom());
                ssfFactory = sc.getSocketFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ssfFactory;
        }

        /**
         * 获取TrustManager
         */
        private static X509TrustManager getTrustAllManager() {
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
        private static HostnameVerifier getHostnameVerifier() {
            return (s, sslSession) -> true;
        }


    }
}