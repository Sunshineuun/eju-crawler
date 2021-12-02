package com.qiusm.eju.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.time.LocalDate;

public abstract class EsAbstractApi {
    protected static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("10.122.143.11", 9200, "http")
//                    new HttpHost("172.29.28.235", 9200, "http")
            ));
    private static BulkProcessor.Listener listener = new BulkProcessor.Listener() {
        @Override
        public void beforeBulk(long executionId, BulkRequest request) {

        }

        @Override
        public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {

        }

        @Override
        public void afterBulk(long executionId, BulkRequest request, Throwable failure) {

        }
    };
    protected static BulkProcessor bulkProcessor = BulkProcessor.builder(
            (bulkRequest, bulkResponseActionListener) -> client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener),
            listener, "qsm-test").build();

    protected static final String QIUSM1_TEST = "qiusm_test1";
    protected static final String PUSH_LOG = "pushlog-2021-10-29";
    protected static final String HOUST_TEST = "houst_test";
    protected static final String ESF_PUSH_DATA = "esfpushdata";
    protected static final String ESF_PUSH_DATA_LOG = "esfpushdata-log-" + LocalDate.now();

    public static RestHighLevelClient getClient() {
        return client;
    }

    public static BulkProcessor getBulkProcessor() {
        return bulkProcessor;
    }

    public static void close() {
        try {
            getClient().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
