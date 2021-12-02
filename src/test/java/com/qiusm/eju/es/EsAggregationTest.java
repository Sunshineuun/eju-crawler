package com.qiusm.eju.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

@Slf4j
public class EsAggregationTest extends EsAbstractApi {
    public static void main(String[] args) throws IOException {
        filterAggregationBuilder();
        close();
    }

    /**
     * 统计某个字段存在值的数量。这个场景会出现在，Doc之间的字段定义是不一样的，有些Doc会有A字段，有些没有。<br>
     * 然而ValueCount就是用来统计这个的吧。<br>
     */
    static void valueCountAggregationBuilder() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        ValueCountAggregationBuilder count = AggregationBuilders
                .count("cityCount").field("cityCd");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(count).size(0);
        request.source(sourceBuilder);
        SearchResponse response = getClient().search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
    }

    static void statsAggregationBuilder() {

    }

    /**
     * 相当于sql中的group by。
     */
    static void termsAggregationBuilder() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        TermsAggregationBuilder term = AggregationBuilders
                .terms("cityCount").field("cityCd");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(term).size(0);
        request.source(sourceBuilder);
        SearchResponse response = getClient().search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
    }

    static void filterAggregationBuilder() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);

        FilterAggregationBuilder filterAgg = AggregationBuilders
                .filter("interfaceType", QueryBuilders.matchQuery("interfaceType", "esf_community_deal"));
        TermsAggregationBuilder termAgg = AggregationBuilders
                .terms("pushStateCount").field("pushState");
        filterAgg.subAggregation(termAgg);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(filterAgg).size(0);
        request.source(sourceBuilder);
        SearchResponse response = getClient().search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
    }
}
