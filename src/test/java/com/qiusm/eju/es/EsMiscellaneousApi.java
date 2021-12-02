package com.qiusm.eju.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.core.MainResponse;
import org.elasticsearch.client.xpack.XPackInfoRequest;
import org.elasticsearch.client.xpack.XPackInfoResponse;
import org.elasticsearch.client.xpack.XPackUsageRequest;
import org.elasticsearch.client.xpack.XPackUsageResponse;

import java.io.IOException;
import java.util.EnumSet;

@Slf4j
public class EsMiscellaneousApi extends EsAbstractApi {
    public static void main(String[] args) throws IOException {
        xpackInfoApi();
        client.close();
    }

    /**
     * 获取ES集群的信息
     */
    static void infoApi() throws IOException {
        MainResponse response = client.info(RequestOptions.DEFAULT);
        String clusterName = response.getClusterName();
        log.info("clusterName:{}", clusterName);
        String clusterUuid = response.getClusterUuid();
        log.info("clusterUuid:{}", clusterUuid);
        String nodeName = response.getNodeName();
        log.info("nodeName:{}", nodeName);
        MainResponse.Version version = response.getVersion();
        String buildDate = version.getBuildDate();
        log.info("buildDate:{}", buildDate);
        String buildFlavor = version.getBuildFlavor();
        log.info("buildFlavor:{}", buildFlavor);
        String buildHash = version.getBuildHash();
        log.info("buildHash:{}", buildHash);
        String buildType = version.getBuildType();
        log.info("buildType:{}", buildType);
        String luceneVersion = version.getLuceneVersion();
        log.info("luceneVersion:{}", luceneVersion);
        String minimumIndexCompatibilityVersion = version.getMinimumIndexCompatibilityVersion();
        log.info("minimumIndexCompatibilityVersion:{}", minimumIndexCompatibilityVersion);
        String minimumWireCompatibilityVersion = version.getMinimumWireCompatibilityVersion();
        log.info("minimumWireCompatibilityVersion:{}", minimumWireCompatibilityVersion);
        String number = version.getNumber();
        log.info("number:{}", number);
    }

    /**
     * ping ES集群
     */
    static void pingApi() throws IOException {
        boolean response = client.ping(RequestOptions.DEFAULT);
        log.info("ping: {}", response);
    }

    static void xpackInfoApi() throws IOException {
        XPackInfoRequest request = new XPackInfoRequest();

        // Enable verbose mode. The default is false but true will return more information.
        // 启用详细模式。默认为false，但true将返回更多信息。
        request.setVerbose(true);

        request.setCategories(EnumSet.of(XPackInfoRequest.Category.BUILD,
                XPackInfoRequest.Category.LICENSE,
                XPackInfoRequest.Category.FEATURES));
        XPackInfoResponse response = client.xpack().info(request, RequestOptions.DEFAULT);

        XPackInfoResponse.BuildInfo build = response.getBuildInfo();
        XPackInfoResponse.LicenseInfo license = response.getLicenseInfo();
        XPackInfoResponse.FeatureSetsInfo features = response.getFeatureSetsInfo();
    }

    static void xpageUsageApi() throws IOException {
        XPackUsageRequest request = new XPackUsageRequest();
        XPackUsageResponse response = client.xpack().usage(request, RequestOptions.DEFAULT);

        /*Map<String, Map<String, Object>> usages = response.getUsages();
        Map<String, Object> monitoringUsage = usages.get("monitoring");
        assertThat(monitoringUsage.get("available"), is(true));
        assertThat(monitoringUsage.get("enabled"), is(true));
        assertThat(monitoringUsage.get("collection_enabled"), is(false));*/
    }
}
