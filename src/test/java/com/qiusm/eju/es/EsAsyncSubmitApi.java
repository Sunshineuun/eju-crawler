package com.qiusm.eju.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.asyncsearch.AsyncSearchResponse;
import org.elasticsearch.client.asyncsearch.SubmitAsyncSearchRequest;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

@Slf4j
public class EsAsyncSubmitApi extends EsAbstractApi {
    public static void main(String[] args) throws IOException {
        submitAsyncSearchApi();
        client.close();
    }

    static void submitAsyncSearchApi() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        String[] indices = new String[]{ESF_PUSH_DATA};
        SubmitAsyncSearchRequest searchRequest = new SubmitAsyncSearchRequest(searchSourceBuilder, indices);

        searchRequest.setKeepAlive(TimeValue.timeValueHours(5));
        searchRequest.setKeepOnCompletion(false);
        searchRequest.setWaitForCompletionTimeout(TimeValue.timeValueSeconds(20));
        AsyncSearchResponse response = client.asyncSearch().submit(searchRequest, RequestOptions.DEFAULT);
        log.info("ID：{},partial:{},running:{},startTime:{},expirationTime:{}",
                response.getId(), response.isPartial(), response.isRunning(), response.getStartTime(), response.getExpirationTime());
    }

    static void getAsyncSearchApi() throws IOException {

    }

    static void deleteAsyncSearchApi() {

    }
}
