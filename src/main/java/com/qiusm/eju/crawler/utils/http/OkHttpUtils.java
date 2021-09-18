package com.qiusm.eju.crawler.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.qiusm.eju.crawler.utils.http.SingleOkHttpConfig.*;
import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;


/**
 * @author qiushengming
 * @date 2021年6月23日
 */
@Slf4j
public class OkHttpUtils {

    static Logger LOG = LoggerFactory.getLogger(OkHttpUtils.class);

    public final okhttp3.OkHttpClient OK_HTT;
    private Builder builder;

    public OkHttpUtils() {
        OK_HTT = new OkHttpClient.Builder()
                .hostnameVerifier(HOSTNAME_VERIFIER)
                .sslSocketFactory(SSL_SOCKET_FACTORY, TRUST_ALL_MANAGER)
                .followRedirects(this.builder.retryEnable)
                .followSslRedirects(this.builder.retryEnable)
                .connectTimeout(this.builder.connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(this.builder.readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(this.builder.readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    public String get(String url) {
        return send(url, GET, null, null, null, null, null, null);
    }

    public String get(String url, String charter, Map<String, String> headers) {
        return send(url, GET, charter, null, null, headers, null, null);
    }

    public String get(String url, String charset, Integer connTimeout, Integer readTimeout, Map<String, String> headers) {
        return send(url, GET, charset, connTimeout, readTimeout, headers, null, null);
    }

    public String postFrom(String url, String charter, Map<String, String> params) {
        return send(url, POST_FROM, charter, null, null, null, params, null);
    }

    public String postFrom(String url, String charter, Map<String, String> headers, Map<String, String> params) {
        return send(url, POST_FROM, charter, null, null, headers, params, null);
    }

    public String postFrom(String url, String charset, Integer connTimeout, Integer readTimeout, Map<String, String> headers, Map<String, String> params) {
        return send(url, POST_FROM, charset, connTimeout, readTimeout, headers, params, null);
    }

    public String postJson(String url, String json) {
        return send(url, POST_JSON, null, null, null, null, json, null);
    }

    public String postJson(String url, String charter, String json) {
        return send(url, POST_JSON, charter, null, null, null, json, null);
    }

    public String postJson(String url, String charter, Map<String, String> headers, String json) {
        return send(url, POST_JSON, charter, null, null, headers, json, null);
    }

    public String postJson(String url, String charset, Integer connTimeout, Integer readTimeout, Map<String, String> headers, String json) {
        return send(url, POST_JSON, charset, connTimeout, readTimeout, headers, json, null);
    }

    public String proxyGet(String url) {
        return proxyTagRetry(null, null, (httpHost) -> send(url, GET, null, null, null, null, null, httpHost));
    }

    public String proxyGet(String url, Map<String, String> headers, Predicate<String> testRetry) {

        return getBodyByFnRetry(url, GET, headers, null, testRetry);
    }

    public String proxyPostFrom(String url, Map<String, String> headers, Object params, Predicate<String> testRetry) {
        return getBodyByFnRetry(url, POST_FROM, headers, params, testRetry);
    }

    public String proxyPostJson(String url, Map<String, String> headers, Object params, Predicate<String> testRetry) {

        return getBodyByFnRetry(url, POST_JSON, headers, params, testRetry);
    }

    private String getBodyByFnRetry(String url, String type, Map<String, String> headers, Object params, Predicate<String> fnRetry) {
        String body = proxyTagRetry(null, headers, (httpHost) -> send(url, type, null, null, null, headers, params, httpHost));
        if (fnRetry != null) {
            int y = 0;
            boolean flag = fnRetry.test(body);
            while (y++ < 20 && flag) {
                body = proxyTagRetry(null, headers, (httpHost) -> send(url, type, null, null, null, headers, params, httpHost));
                flag = fnRetry.test(body);
            }
            if (y >= 20 && flag) {
                body = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_CODE, "Retryup", JSON.toJSONString(headers), "Retry up to 20 !!");
                LOG.error(body);
            }
        }

        return body;
    }

    public String proxyGet(String url, String charset, Map<String, String> headers) {
        return proxyTagRetry(null, headers, (httpHost) -> send(url, GET, charset, null, null, headers, null, httpHost));
    }

    public String proxyGet(String url, String charset, HttpHost hHost, Map<String, String> headers) {
        return proxyTagRetry(null, hHost, headers, (httpHost) -> send(url, GET, charset, null, null, headers, null, httpHost));
    }

    public String proxyGet(String url, String charset, Integer connTimeout, Integer readTimeout, Integer retry, Map<String, String> headers) {
        return proxyTagRetry(retry, headers, (httpHost) -> send(url, GET, charset, connTimeout, readTimeout, headers, null, httpHost));
    }

    public String proxyPostFrom(String url, Map<String, String> params) {
        return proxyTagRetry(null, null, (httpHost) -> send(url, POST_FROM, null, null, null, null, params, httpHost));
    }

    public String proxyPostFrom(String url, String charset, Map<String, String> headers, Map<String, String> params) {
        return proxyTagRetry(null, headers, (httpHost) -> send(url, POST_FROM, charset, null, null, headers, params, httpHost));
    }

    public String proxyPostFrom(String url, String charset, Integer connTimeout, Integer readTimeout, Integer retry, Map<String, String> headers, Map<String, String> params) {
        return proxyTagRetry(retry, headers, (httpHost) -> send(url, POST_FROM, charset, connTimeout, readTimeout, headers, params, httpHost));
    }

    public String proxyPostJson(String url, String json) {
        return proxyTagRetry(null, null, (httpHost) -> send(url, POST_JSON, null, null, null, null, json, httpHost));
    }

    public String proxyPostJson(String url, String charset, Map<String, String> headers, String json) {
        return proxyTagRetry(null, headers, (httpHost) -> send(url, POST_JSON, charset, null, null, headers, json, httpHost));
    }

    public String proxyPostJson(String url, String charset, Integer connTimeout, Integer readTimeout, Integer retry, Map<String, String> headers, String json) {
        return proxyTagRetry(retry, headers, (httpHost) -> send(url, POST_JSON, charset, connTimeout, readTimeout, headers, json, httpHost));

    }

    private String proxyTagRetry(Integer retry, Map<String, String> headers, Function<HttpHost, String> fn) {
        return proxyTagRetry(retry, null, headers, fn);
    }

    private String proxyTagRetry(Integer retry, HttpHost hHost, Map<String, String> headers, Function<HttpHost, String> fn) {
        int cnt = retry == null ? this.builder.retryMax : retry;
        String body = null;
        boolean flag = true;
        for (int i = 0; i <= cnt && flag; i++) {
            flag = false;
            HttpHost httpHost;
            if (hHost == null) {
                httpHost = getHttpHost(headers);
            } else {
                httpHost = hHost;
            }
            if (headers != null && !headers.containsKey(USER_AGENT)) {
                headers.put(USER_AGENT, SingleOkHttpConfig.randomPC());
            }
            body = fn.apply(httpHost);
            if (this.builder.proxyRetryTag != null) {
                if (!body.contains("ResponseCode=404")) {
                    if ("null".equalsIgnoreCase(body)) {
                        flag = true;
                    } else {
                        for (int y = 0; y < this.builder.proxyRetryTag.size(); y++) {
                            if (!body.contains(this.builder.proxyRetryTag.get(y))) {
                                continue;
                            }
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        return body;
    }

    /**
     * Get HttpHost
     */
    private HttpHost getHttpHost(Map<String, String> header) {
        int x = 0;
        HttpHost httpHost;
        do {
            httpHost = SingleOkHttpConfig.IP_LIST.poll();
            //请求代理池获取ip
            if (StringUtils.isNotBlank(this.builder.proxyUrl) && SingleOkHttpConfig.IP_LIST.size() <= this.builder.proxyLessThan) {
                try {
                    SingleOkHttpConfig.LOCK.lock();
                    if (SingleOkHttpConfig.IP_LIST.size() <= this.builder.proxyLessThan) {
                        String body = get(this.builder.proxyUrl);
                        log.debug("proxyUrl:{},getBody:{}", this.builder.proxyUrl, body);
                        if (StringUtils.isNotBlank(body) && body.contains(SingleOkHttpConfig.S) && !body.contains("html")) {
                            String[] ips = body.split(this.builder.proxySeparator);
                            String[] ipAndPort = ips[0].split(SingleOkHttpConfig.S);
                            int y = 0;
                            if (httpHost == null) {
                                httpHost = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                                y = 1;
                            }
                            while (y < ips.length) {
                                ipAndPort = ips[y].split(SingleOkHttpConfig.S);
                                SingleOkHttpConfig.IP_LIST.offer(new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
                                y++;
                            }
                        } else {
                            try {
                                TimeUnit.MICROSECONDS.sleep(6 * SingleOkHttpConfig.WAIL_PROXY_TIMEOUT);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        httpHost = SingleOkHttpConfig.IP_LIST.poll();
                    }
                } finally {
                    SingleOkHttpConfig.LOCK.unlock();
                }
            }
            if (httpHost == null) {
                httpHost = SingleOkHttpConfig.IP_LIST.poll();
                if (httpHost == null && JSON.parseObject(this.builder.proxyJsonDefault) != null) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(SingleOkHttpConfig.WAIL_PROXY_TIMEOUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    JSONObject jsonObject = JSON.parseObject(this.builder.proxyJsonDefault);
                    if (header != null) {
                        jsonObject.getJSONObject("header").forEach((key, value) -> header.put(key, value + ""));
                    }
                    httpHost = new HttpHost(jsonObject.getString("hostname"), jsonObject.getInteger("port"));
                } else {
                    x++;
                }
            }

        } while (httpHost == null && x < SingleOkHttpConfig.PROXY_RETRY_MAX);

        log.debug("HttpHost:{}", httpHost);
        return httpHost;
    }

    /**
     * 发送请求
     */
    private String send(String url, String type, String charset, Integer conTimeout, Integer readTimeout, Map<String, String> headers, Object argus, HttpHost httpHost) {
        String resultBody;
        Response response = null;
        try {
            charset = charset == null ? this.builder.charset : charset;
            Request.Builder requestBuilder = new Request.Builder().url(url);
            // 请求头处理
            if (headers != null) {
                headers.remove(RESPONSE_HEADERS);
                headers.forEach(requestBuilder::addHeader);
            }

            // 针对请求类型进行参数组装
            if (GET.equals(type)) {
            } else if (POST_FROM.equals(type)) {
                // 表单提交方式
                FormBody.Builder builder = new FormBody.Builder(Charset.forName(charset));
                if (argus instanceof Map) {
                    Map<String, String> map = (Map) argus;
                    map.forEach(builder::add);
                }
                requestBuilder.post(builder.build());
            } else if (POST_JSON.equals(type)) {
                // json提交方式
                RequestBody body = FormBody.create(MediaType.parse(SingleOkHttpConfig.APPLICATION_JSON_CHARSET + charset), argus + "");
                requestBuilder.post(body);
            } else {
                throw new UnsupportedOperationException(SingleOkHttpConfig.NO_SUPPORT_REQUEST_OK_HTTP_UTILS_TYPE + type);
            }

            okhttp3.OkHttpClient okHttp;
            if (httpHost != null) {
                okHttp = new okhttp3.OkHttpClient.Builder()
                        .hostnameVerifier(HOSTNAME_VERIFIER)
                        .sslSocketFactory(SSL_SOCKET_FACTORY, TRUST_ALL_MANAGER)
                        .followRedirects(this.builder.retryEnable)
                        .followSslRedirects(this.builder.retryEnable)
                        .connectTimeout(conTimeout == null ? this.builder.connectTimeout : conTimeout, TimeUnit.MILLISECONDS)
                        .readTimeout(readTimeout == null ? this.builder.readTimeout : readTimeout, TimeUnit.MILLISECONDS)
                        .writeTimeout(readTimeout == null ? this.builder.readTimeout : readTimeout, TimeUnit.MILLISECONDS)
                        .connectionPool(new ConnectionPool(1, readTimeout == null ? this.builder.readTimeout : readTimeout, TimeUnit.MILLISECONDS))
                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpHost.getHostName(), httpHost.getPort())))
                        .build();
            } else {
                okHttp = OK_HTT;
            }
            response = okHttp.newCall(requestBuilder.build()).execute();
            if (headers != null) {
                Headers reHeaders = response.headers();
                Map<String, String> responseHeaders = new HashMap<>();
                reHeaders.names().forEach(name -> responseHeaders.put(name, reHeaders.get(name)));

                if (httpHost != null) {
                    responseHeaders.put(CURR_PROXY_IP, JSON.toJSONString(httpHost));
                }
                headers.put(RESPONSE_HEADERS, JSON.toJSONString(responseHeaders));
            }
            if (response.isSuccessful() && response.body() != null) {
                try {
                    resultBody = IOUtils.toString(response.body().byteStream(), charset);
                } catch (Exception e) {
                    if (!"gzip finished without exhausting source".equals(e.getMessage())) {
                        throw e;
                    }
                    InputStream reader = okHttp.newCall(requestBuilder.build()).execute().body().byteStream();
                    StringBuilder sb = new StringBuilder();
                    byte[] buffer = new byte[1024];
                    try {
                        while (reader.read(buffer) != -1) {
                            sb.append(new String(buffer, charset).trim());
                            buffer = new byte[1024];
                        }
                    } catch (IOException e1) {
                        // gzip finished without exhausting source
                        if (!"gzip finished without exhausting source".equals(e.getMessage())) {
                            throw e1;
                        }
                    }
                    resultBody = sb.toString();
                }
            } else {
                resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_CODE, response.code(), JSON.toJSONString(httpHost), response.message());
            }
        } catch (ConnectTimeoutException cte) {
            resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR, "ConnectTimeoutException", JSON.toJSONString(httpHost), cte.getMessage());
            //LOG.error("connect timeout:" + url);
        } catch (SocketTimeoutException se) {
            resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR, "SocketTimeoutException", JSON.toJSONString(httpHost), se.getMessage());
            //LOG.error("connect socket timeout:" + url);
        } catch (HttpHostConnectException he) {
            resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR, "HttpHostConnectException", JSON.toJSONString(httpHost), he.getMessage());
            //LOG.error("connect httpHost:" + url);
        } catch (ConnectException ce) {
            resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR, "ConnectException", JSON.toJSONString(httpHost), ce.getMessage());
            //LOG.error("connect httpHost:" + url);
        } catch (SocketException se) {
            resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR, "SocketException", JSON.toJSONString(httpHost), se.getMessage());
            //LOG.error("connect socket timeout:" + url);
        } catch (IOException ie) {
            resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR, "IOException", JSON.toJSONString(httpHost), ie.getMessage());
//            ie.printStackTrace();
        } catch (Exception e) {
            resultBody = String.format(SingleOkHttpConfig.EJU_RESPONSE_CODE_EXCEPTION_PROXY_ERROR, "Exception", JSON.toJSONString(httpHost), e.getMessage());
//            e.printStackTrace();
        } finally {
//            LOG.info("body:{},url:{},proxy:{}",
//                resultBody.contains("ResponseCode") || resultBody.contains("ResponseError") || resultBody.length() <= 200 ? resultBody : resultBody.substring(0, 200)
//                , url,
//                httpHost
//            );
            resClose(response);
        }
        return resultBody;
    }

    /**
     * 获取当前url的响应状态 <br>
     * 响应状态：{@link SingleOkHttpConfig}
     *
     * @param url      url
     * @param headers  请求头
     * @param httpHost 代理
     * @return 响应状态
     */
    public Integer getStatusCode(String url, Integer conTimeout, Integer readTimeout, Map<String, String> headers, HttpHost httpHost) {
        int resultBody = SingleOkHttpConfig.INT999;
        Response response = null;
        try {
            Request.Builder requestBuilder = new Request.Builder().url(url);
            if (headers != null) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    if (!RESPONSE_HEADERS.equals(e.getKey())) {
                        requestBuilder.addHeader(e.getKey(), e.getValue());
                    }
                }
            }
            if (httpHost != null) {
                okhttp3.OkHttpClient okHttp = new okhttp3.OkHttpClient.Builder()
                        .hostnameVerifier(HOSTNAME_VERIFIER)
                        .sslSocketFactory(SSL_SOCKET_FACTORY, TRUST_ALL_MANAGER)
                        .followRedirects(this.builder.retryEnable)
                        .followSslRedirects(this.builder.retryEnable)
                        .connectTimeout(conTimeout == null ? this.builder.connectTimeout : conTimeout, TimeUnit.MILLISECONDS)
                        .readTimeout(readTimeout == null ? this.builder.readTimeout : readTimeout, TimeUnit.MILLISECONDS)
                        .writeTimeout(readTimeout == null ? this.builder.readTimeout : readTimeout, TimeUnit.MILLISECONDS)
                        .connectionPool(new ConnectionPool(1, readTimeout == null ? this.builder.readTimeout : readTimeout, TimeUnit.MILLISECONDS))
                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpHost.getHostName(), httpHost.getPort())))
                        .build();
                response = okHttp.newCall(requestBuilder.build()).execute();
            } else {
                response = OK_HTT.newCall(requestBuilder.build()).execute();
            }
            resultBody = response.code();
        } catch (ConnectTimeoutException cte) {
            resultBody = SingleOkHttpConfig.INT901;
        } catch (SocketTimeoutException se) {
            resultBody = SingleOkHttpConfig.INT902;
        } catch (HttpHostConnectException he) {
            resultBody = SingleOkHttpConfig.INT903;
        } catch (ConnectException ce) {
            resultBody = SingleOkHttpConfig.INT904;
        } catch (SocketException se) {
            resultBody = SingleOkHttpConfig.INT905;
        } catch (IOException ie) {
            resultBody = SingleOkHttpConfig.INT906;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resClose(response);
        }
        return resultBody;
    }

    private static void resClose(Response response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

    public Builder restBuilder() {
        return this.builder;
    }

    /**
     * Builder
     */
    public static class Builder {
        private static final String PROXY_JSON_DEFAULT = "{'hostname':'transfer.mogumiao.com','port':9001,'scheme':'https','header':{'Proxy-Authorization':'Basic QzNjSEpJMVpXOUVxOGpRQTpGZzZvQmczd05tTjJwc1JO'}}";
        int proxyLessThan;
        int connectTimeout;
        int readTimeout;
        /**
         * 重试最大次数：默认3次
         */
        int retryMax;
        /**
         * 是否进行重试：默认是
         */
        boolean retryEnable;
        /**
         * 字符集：默认utf-8
         */
        String charset;
        String proxyJsonDefault;
        String proxyUrl;
        /**
         * 代理分隔符
         */
        String proxySeparator;
        /**
         * 重试检测标签；只要响应的结果体重包含该列表中的字符，则进行重试操作。
         */
        List<String> proxyRetryTag;

        private Builder() {
            this.proxyLessThan = 3;
            this.connectTimeout = 3000;
            this.readTimeout = 3000;
            this.retryMax = 6;
            this.retryEnable = true;
            this.charset = "utf-8";
            this.proxyJsonDefault = PROXY_JSON_DEFAULT;
            this.proxyUrl = null;
            this.proxySeparator = "\n";
            this.proxyRetryTag = new ArrayList<>();
            this.proxyRetryTag.add("ResponseError=");
            this.proxyRetryTag.add("ResponseCode=");
        }

        public Builder connectTimeout(int connectTimeout) {
            if (connectTimeout < 0L) {
                throw new IllegalArgumentException(connectTimeout + " < 0");
            }
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            if (connectTimeout < 0L) {
                throw new IllegalArgumentException(connectTimeout + " < 0");
            }
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder retryDisable() {
            this.retryEnable = false;
            return this;
        }

        public Builder retryMax(int retryMax) {
            if (this.retryMax < 0) {
                throw new NullPointerException("retryMax < " + retryMax);
            }
            this.retryMax = retryMax;
            return this;
        }

        public Builder addUserAgent(String userAgent) {
            if (userAgent == null) {
                throw new NullPointerException("userAgent == null");
            }
            SingleOkHttpConfig.getPcList().add(userAgent);
            return this;
        }

        public Builder charset(String Charset) {
            if (Charset == null || "".equals(Charset.trim())) {
                throw new NullPointerException("charset == null");
            }
            this.charset = Charset;
            return this;
        }

        public Builder proxyUrl(String proxyUrl) {
            if (proxyUrl == null || "".equals(proxyUrl.trim())) {
                throw new NullPointerException("proxyUrl == null");
            }
            // log.info("当前使用代理：{}", proxyUrl);
            this.proxyUrl = proxyUrl;
            return this;
        }

        public Builder proxySeparator(String proxySeparator) {
            if (proxySeparator == null) {
                throw new NullPointerException("proxySeparator == null");
            }
            this.proxySeparator = proxySeparator;
            return this;
        }

        public Builder proxyRetryTag(List<String> proxyRetryTag) {
            if (proxyRetryTag == null) {
                throw new NullPointerException("proxyRetryTag == null");
            }
            this.proxyRetryTag.addAll(proxyRetryTag);
            return this;
        }

        public Builder addProxyRetryTag(String... proxyRetryTag) {
            if (this.proxyRetryTag == null) {
                throw new NullPointerException("proxyRetryTag == null");
            }
            this.proxyRetryTag.addAll(Arrays.asList(proxyRetryTag));
            return this;
        }

        public Builder proxyJsonDefault(String proxyJsonDefault) {
            if (proxyJsonDefault == null
                    || (proxyJsonDefault.contains("hostname")
                    && proxyJsonDefault.contains("port")
                    && proxyJsonDefault.contains("header"))) {
                this.proxyJsonDefault = proxyJsonDefault;
                return this;
            } else {
                throw new IllegalArgumentException("proxyJsonDefault is json , required attribute:hostname、port、header【json】== null");
            }
        }

        public OkHttpUtils builderHttp() {
            OkHttpUtils okHttpUtils = new OkHttpUtils();
            okHttpUtils.builder = this;
            return okHttpUtils;
        }
    }
}
