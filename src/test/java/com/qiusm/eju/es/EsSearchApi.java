package com.qiusm.eju.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.fieldcaps.FieldCapabilities;
import org.elasticsearch.action.fieldcaps.FieldCapabilitiesRequest;
import org.elasticsearch.action.fieldcaps.FieldCapabilitiesResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ES Search API Demo
 *
 * @author qiushengming
 */
@Slf4j
public class EsSearchApi extends EsAbstractApi {
    public static void main(String[] args) throws IOException {
        countApi();
        client.close();
    }

    /**
     * 字段选择 <br>
     * 对 fetchSource 属性进行测试 <br>
     * 1.当设置为fetchSource=false时，不返回任何元数据定义的字段信息;
     * 当设置为fetchSource=true时，返回所有元数据中定义的字段信息；<br>
     * 2.当设置 includes、excludes，只返回includes中定义的字段，所以在使用中 includes、excludes二者定义其一就行了.<br>
     */
    static void fetchSource() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String[] includes = new String[]{"city"};
        String[] excludes = new String[]{"cityCd"};
        sourceBuilder.fetchSource(true);

        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
    }

    /**
     * 排序 <br>
     * 对 sort 属性进行测试 <br>
     * 1. FieldSortBuilder,可以指定按照某个字段进行排序。（如果多字段怎么操作？） <br>
     * 2. ScoreSortBuilder,根据查询得分进行排序。<br>
     */
    static void sort() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.ASC));

        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
        log.info("{}", response.status());
        log.info("{}", response.isTerminatedEarly());
    }

    /**
     * 高亮 <br>
     * 对 highlight 属性进行测试 <br>
     * 1. 高亮只在存在查询条件，且查询条件是当前设置的查询字段时才有效<br>
     */
    static void highlight() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field cityField = new HighlightBuilder.Field("city");
        highlightBuilder.field(cityField);

        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("city", "宁波市");

//        highlightBuilder.postTags("</p>");
//        highlightBuilder.preTags("<p>");

        sourceBuilder.highlighter(highlightBuilder)
                .query(queryBuilder);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
        SearchHits hits = response.getHits();
        hits.forEach(h -> {
            Map<String, HighlightField> hfMap = h.getHighlightFields();
            HighlightField hf = hfMap.get("city");
            log.info("{}", hf.fragments());
        });
    }

    /**
     * 简单聚合 <br>
     * 1. 这里重点是需要聚合操作几个方法的含义。 <br>
     * 1.1 terms, 统计某字段出现的次数。类似group by count <br>
     * 1.2 max,min,count,range,filter,sum等等具体含义是什么 <br>
     * 1.3 TODO
     */
    static void aggregations() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("cityGroup").field("city");

        sourceBuilder.aggregation(aggregationBuilder)
                .size(0);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
        Aggregations aggregations = response.getAggregations();
        Aggregation aggregation = aggregations.get("cityGroup");
        log.info("{}", aggregation);
        if (aggregation instanceof ParsedStringTerms) {
            ParsedStringTerms pst = (ParsedStringTerms) aggregation;
            List<? extends Terms.Bucket> buckets = pst.getBuckets();
            buckets.forEach(b -> {
                log.info("key:{},value:{}", b.getKeyAsString(), b.getDocCount());
            });
        }

        aggregations.forEach(agg -> {
            // 参考：AggregationBuilders.里面基本定义了所有类型。
            switch (agg.getType()) {
                case TermsAggregationBuilder.NAME:
                    log.info("{}", TermsAggregationBuilder.NAME);
                    break;
                case ValueCountAggregationBuilder.NAME:
                    log.info("{}", ValueCountAggregationBuilder.NAME);
                    break;
                case AvgAggregationBuilder.NAME:
                    log.info("{}", AvgAggregationBuilder.NAME);
                    break;
                default:
                    throw new NullPointerException("找不到对应类型，" + agg.getType());
            }
        });

    }

    /**
     * 检索建议（自动补全）<br>
     * Field [community] is not a completion suggest field. 对字段类型有一定的要求 <br>
     * CompletionSuggestionBuilder, 搜索自动补全； <br>
     * TermSuggestionBuilder, 错词纠正; <br>
     * PhraseSuggestionBuilder, 短语错别纠正; <br>
     */
    static void suggestions() throws IOException {
        SearchRequest request = new SearchRequest(ESF_PUSH_DATA);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // CompletionSuggestionBuilder completionSuggestionBuilder = SuggestBuilders.completionSuggestion("community").prefix("卢浮").size(10);
        TermSuggestionBuilder termSuggestionBuilder = SuggestBuilders.termSuggestion("community").text("");
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("communitySugg", termSuggestionBuilder);
        sourceBuilder.suggest(suggestBuilder);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("{}", response);

        Suggest suggest = response.getSuggest();
        TermSuggestion termSuggestion = suggest.getSuggestion("communitySugg");
        for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
            for (TermSuggestion.Entry.Option option : entry) {
                String suggestText = option.getText().string();
                log.info("{}", suggestText);
            }
        }
    }

    /**
     * 多条件查询 <br>
     */
    static void boolQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 创建多条件查询 bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("houseName", "test"));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("houseName", "test"));
        searchSourceBuilder.query(boolQueryBuilder);

        // 范围查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("housePrice");
        rangeQueryBuilder.gt(1);
        rangeQueryBuilder.lt(20);
        searchSourceBuilder.query(rangeQueryBuilder);

        SearchRequest request = new SearchRequest();
        request.indices(QIUSM1_TEST)
                .source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        log.info("total hits:{}, took:{}, maxScore(最高得分):{}", hits.getTotalHits(), response.getTook(), hits.getMaxScore());

        hits.forEach(h -> {
            log.info("record: {}", h.getSourceAsString());
        });
    }

    /**
     * 模糊查询
     */
    static void fuzz() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        SearchSourceBuilder builder = new SearchSourceBuilder();

        // 构建模糊查询
        FuzzyQueryBuilder queryBuilder = QueryBuilders
                .fuzzyQuery("houseName", "t")
                .fuzziness(Fuzziness.ONE);

        builder.query(queryBuilder);

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        hits.forEach(h -> {
            log.info("record:{}", h.getSourceAsString());
        });
    }

    /**
     * 分页查询
     */
    static void page() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(20);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
    }

    /**
     * 性能分析
     */
    static void profile() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(ESF_PUSH_DATA);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.profile(true);

        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("{}", response);
    }

    /**
     * 滚动查询； 在查询大量数据时会使用到该API。<br>
     */
    static void searchScrollApi() throws IOException {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.scroll(scroll);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        String scrollId = searchResponse.getScrollId();

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest();
            searchScrollRequest.scroll(scroll);
            searchResponse = client.scroll(searchScrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
    }

    /**
     * 通过脚本模板进行查询 <br>
     * 1. Simulate参数设置为true，表示只对脚本模板进行渲染，及对占位符进行值填充.不进行查询。一般用于测试<br>
     * 2. 多个查询请使用MultiSearchTemplateAPI
     */
    static void searchTemplateApi() throws IOException {
        SearchTemplateRequest searchTemplateRequest = new SearchTemplateRequest();
        searchTemplateRequest.setRequest(new SearchRequest(ESF_PUSH_DATA));
        searchTemplateRequest.setScriptType(ScriptType.INLINE);
        searchTemplateRequest.setScript("{" +
                "  \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
                "  \"size\" : \"{{size}}\"" +
                "}");
        Map<String, Object> scriptParams = new HashMap<>();
        scriptParams.put("field", "city");
        scriptParams.put("value", "北京市");
        scriptParams.put("size", 5);
        searchTemplateRequest.setScriptParams(scriptParams);

        searchTemplateRequest.setSimulate(true);

        SearchTemplateResponse response = client.searchTemplate(searchTemplateRequest, RequestOptions.DEFAULT);
        log.info("{}", response.getSource().utf8ToString());
        log.info("{}", response);
    }

    /**
     * 查询字段的一些配置信息 <br>
     * 可以知道，字段在指定索引范围内有哪些类型，及其其它相关信息。如果要查询字段信息就用这个接口 <br>
     * {keyword={"type":"keyword","metadata_field":false,"searchable":true,"aggregatable":true}} <br>
     * searchable: 是否为所有索引上的搜索对该字段建立索引。<br>
     * aggregatable: 是否可以将此字段聚合到所有索引上。 <br>
     * indices: 该字段具有相同类型的索引列表，如果所有索引具有相同的字段类型，则为null。 <br>
     * non_searchable_indices: 该字段不可搜索的索引列表，如果所有索引对该字段都有相同的定义，则为null。 <br>
     * non_aggregatable_indices: 该字段不可聚合的索引列表，如果所有索引对该字段都有相同的定义，则为null。 <br>
     */
    static void fieldCapabilitiesApi() throws IOException {
        String cityField = "city";
        FieldCapabilitiesRequest request = new FieldCapabilitiesRequest();
        request.fields(cityField).indices();
        FieldCapabilitiesResponse response = client.fieldCaps(request, RequestOptions.DEFAULT);

        Map<String, FieldCapabilities> cityResponse = response.getField(cityField);
        log.info("cityResponse:{}", cityResponse);
    }

    /**
     * 计数API <br>
     */
    static void countApi() throws IOException {
        CountRequest countRequest = new CountRequest();
        countRequest.indices(ESF_PUSH_DATA).query(QueryBuilders.matchAllQuery());

        CountResponse response = client.count(countRequest, RequestOptions.DEFAULT);
        log.info("{}", response);
    }
}
