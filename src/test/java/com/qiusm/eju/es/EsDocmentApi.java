package com.qiusm.eju.es;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiusm.eju.crawler.entity.es.House;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ES常规操作DEMO
 */
@Slf4j
public class EsDocmentApi extends EsAbstractApi {

    public static void main(String[] args) throws IOException {
    }

    static void indexApi() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index(QIUSM1_TEST).id("1");
        House house = new House();
        house.setHouseId("1");
        house.setCityId("3100001");
        house.setHouseName("测试名称12");
        ObjectMapper mapper = new ObjectMapper();
        String houseJson = mapper.writeValueAsString(house);
        request.source(houseJson, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        log.info("Result >> {}", response.getResult());
    }

    static void getApi() throws IOException {
        GetRequest request = new GetRequest();
        request.index(QIUSM1_TEST).id("1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        log.info("doc >> {}", response.getSourceAsMap());
    }

    /**
     * 根据ID+索引获取文档数据
     */
    static void getApiAsync() {
        GetRequest getRequest = new GetRequest(ESF_PUSH_DATA, "system_esf_community_price-92165");
        ActionListener<GetResponse> actionListener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse o) {
                Map<String, Object> source = o.getSource();
                System.out.println(source);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        client.getAsync(getRequest, RequestOptions.DEFAULT, actionListener);
    }

    static void getSourceApiAsync() {
        GetSourceRequest getSourceRequest = new GetSourceRequest(ESF_PUSH_DATA, "system_esf_community_price-92165");
        ActionListener<GetSourceResponse> actionListener = new ActionListener<GetSourceResponse>() {
            @Override
            public void onResponse(GetSourceResponse o) {
                Map<String, Object> source = o.getSource();
                System.out.println(source);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        client.getSourceAsync(getSourceRequest, RequestOptions.DEFAULT, actionListener);
    }

    /**
     * 判断索引是否存在
     */
    static void existsApi() throws IOException {
        GetRequest getRequest = new GetRequest(ESF_PUSH_DATA, "system_esf_community_price-92165");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean response = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.printf("索引是否存在：%s \n", response);
    }

    /**
     * 删除Doc
     */
    static void deleteApi() throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.index(QIUSM1_TEST).id("1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        log.info("删除：index:{},result:{},", response.getIndex(), response.getResult());
    }

    /**
     * 这个接口暂时不知道什么作用后续再研究
     *
     * @throws IOException IO
     */
    static void termVectorsApi() throws IOException {
        TermVectorsRequest termVectorsRequest = new TermVectorsRequest(ESF_PUSH_DATA, "system_esf_community_price-92165");
        TermVectorsResponse response = client.termvectors(termVectorsRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    /**
     * 批量添加数据
     */
    static void bulkApi() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();

        House house1 = createHouse();
        House house2 = createHouse();
        House house3 = createHouse();

        bulkRequest.add(new IndexRequest().index(QIUSM1_TEST).id("2").source(mapper.writeValueAsString(house1), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index(QIUSM1_TEST).id("3").source(mapper.writeValueAsString(house2), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index(QIUSM1_TEST).id("4").source(mapper.writeValueAsString(house3), XContentType.JSON));

        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        log.info("took:{},items:{}", response.getTook(), response.getItems());
    }

    static void bulkProcessorApi() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index(QIUSM1_TEST).id("1");
        House house = new House();
        house.setHouseId("1");
        house.setCityId("3100001");
        house.setHouseName("测试名称12");
        String houseJson = JSONObject.toJSONString(house);
        request.source(houseJson, XContentType.JSON);
        bulkProcessor.add(request);
        // bulkProcessor.flush();
    }

    /**
     * 创建索引
     */
    static void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(HOUST_TEST);
        // 配置字段映射关系，也可理解为配置字段类型。ES字段类型定义见：
        // index: 设置为false的时候，表示不进行索引，不能被查询；
        request.mapping("  {\"properties\": {\"houseName\": {\"type\": \"text\"}, \"houseId\": {\"type\": \"keyword\"}, \"communityName\": {\"type\": \"text\"}, \"communityId\": {\"type\": \"keyword\"}, \"createTime\": {\"type\": \"date\", \"format\": \"date_hour_minute_second\"}}}", XContentType.JSON);
        // String jsonString = "{\"user\":\"kimchy\",\"postDate\":\"2013-01-30\",\"message\":\"trying out Elasticsearch\"}";
        // request.source(jsonString, XContentType.JSON);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        log.info("索引操作 >> {}", response.isAcknowledged());
    }

    /**
     * 获取索引信息
     */
    static void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(QIUSM1_TEST);
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
//        client.indices().getIndexTemplate();
        log.info("aliases===>{}", response.getAliases());
        log.info("mappings===>{}", response.getMappings());
        log.info("settings===>{}", response.getSettings());
    }

    static void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(QIUSM1_TEST);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        log.info("索引操作 >> {}", response.isAcknowledged());
    }

    private static House createHouse() {
        House house = new House();
        house.setHouseId(UUID.randomUUID().toString());
        house.setHouseName("test-update");
        house.setHousePrice("2000");
        house.setHousePriceUnit("万");
        house.setHouseAvgPrice("100");
        house.setCommunityId("communityIdTest");
        house.setCommunityName("远洋香奈");
        house.setCityId("310000");
        house.setCityName("上海");
        house.setRegion("浦东");
        house.setRegionId("pd01");
        house.setPlate("陆家嘴");
        house.setPlateId("lujiazui");
        return house;
    }

    /**
     * 1. 统计城市下有多少个挂牌；
     * 2. 统计城市下有多少小区；
     * ES 桶的类型有。
     * terms: 针对某个字段进行分组，并统计数量；
     * filter: 过滤桶，根据条件进行过滤；
     * top_hist: 在某个桶中找出前几位；
     *
     * @throws IOException
     */
    static void searchAggCityCommunityCount() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(PUSH_LOG);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 聚合查询语法组织
        FilterAggregationBuilder aggFilter = AggregationBuilders
                .filter("cityName1", QueryBuilders.matchQuery("cityName", "上海市"));
        TermsAggregationBuilder aggTerm = AggregationBuilders.terms("community_count").field("cityName");
        aggFilter.subAggregation(aggTerm);

        searchSourceBuilder.aggregation(aggFilter).size(0);

        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Aggregations aggregations = response.getAggregations();
        log.info("aggregations: {}", aggregations);
        log.info(": {}", aggregations.get("cityName1"));
        aggregations.asList().forEach(aggregation -> {
            log.info("agg record: {}", aggregation.getName());
            if (aggregation instanceof ParsedStringTerms) {
                List<? extends Terms.Bucket> buckets = ((ParsedStringTerms) aggregation).getBuckets();
                buckets.forEach(bucket -> {
                    log.info("key:{},value:{}", bucket.getKeyAsString(), bucket.getDocCount());
                });
            }

            if (aggregation instanceof ParsedFilter) {
                ParsedFilter parsedFilter = (ParsedFilter) aggregation;
                log.info("docCount: {}", parsedFilter.getDocCount());
            }
        });

    }

    /**
     * 1. 统计城市下的全量数据。
     * 2. 统计城市下的各个接口的数量。
     * 3. 统计城市下的各个接口各状态的数据推送状态。
     */
    static void cityGroup() throws IOException {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        request.indices(ESF_PUSH_DATA);
        // 多条件 must的性能要比filter低一点，must需要进行打分处理
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("createTime").gt("2021-11-22 12:00:00").lt("2021-11-22 16:00:00"))
                .filter(QueryBuilders.termQuery("city", "上海"))
        ;

        FilterAggregationBuilder updateTimeAggFilter = AggregationBuilders
                .filter("updateTimeFilter", boolQueryBuilder);

        TermsAggregationBuilder pushStateGroupTerm = AggregationBuilders
                .terms("pushStateGroup")
                .field("pushState");

        TermsAggregationBuilder interfaceTypeGroupTerm = AggregationBuilders
                .terms("interfaceTypeGroup")
                .field("interfaceType");
        interfaceTypeGroupTerm.subAggregation(pushStateGroupTerm);

        TermsAggregationBuilder cityGroupTerms = AggregationBuilders
                .terms("cityGroup")
                .field("city");
        cityGroupTerms.subAggregation(interfaceTypeGroupTerm);
        // updateTimeAggFilter.subAggregation(cityGroupTerms);


        searchSourceBuilder.query(QueryBuilders
                        .rangeQuery("createTime").gt("2021-11-22 14:00:00").lt("2021-11-22 15:00:00"))
                .aggregation(cityGroupTerms);

        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Aggregation cityGroupAgg = response.getAggregations().get("cityGroup");
        if (cityGroupAgg instanceof ParsedStringTerms) {
            ParsedStringTerms cityGroupAggPst = (ParsedStringTerms) cityGroupAgg;
            cityGroupAggPst.getBuckets().forEach(co -> {
                log.info("{},{}", co.getKeyAsString(), co.getDocCount());
                Aggregation interfaceTypeGroupAgg = co.getAggregations().get("interfaceTypeGroup");
                if (interfaceTypeGroupAgg instanceof ParsedStringTerms) {
                    ParsedStringTerms interfaceTypeGroupPst = (ParsedStringTerms) interfaceTypeGroupAgg;
                    interfaceTypeGroupPst.getBuckets().forEach(ito -> {
                        StringBuilder sb = new StringBuilder();

                        sb.append(ito.getKeyAsString())
                                .append(",")
                                .append(ito.getDocCount())
                                .append(";");

                        Aggregation pushStateGroupAgg = ito.getAggregations().get("pushStateGroup");
                        if (pushStateGroupAgg instanceof ParsedStringTerms) {
                            ParsedStringTerms pushStateGroupPst = (ParsedStringTerms) pushStateGroupAgg;
                            pushStateGroupPst.getBuckets().forEach(pso -> {
                                String k = pso.getKeyAsString();
                                String kc = "未推送";
                                switch (k) {
                                    case "0":
                                        kc = "等待推送";
                                        break;
                                    case "1":
                                        kc = "推送成功";
                                        break;
                                    case "2":
                                        kc = "推送失败";
                                        break;
                                    default:
                                        kc = "未定义状态:" + k;
                                }
                                sb.append(kc).append(",").append(pso.getDocCount()).append(";");
                            });
                            log.info("{}", sb);
                        }
                    });
                }
            });
        }
        log.info("{}", response);
    }


}
