package com.qiusm.eju.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiusm.eju.crawler.entity.es.House;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
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
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;
import java.util.UUID;

/**
 * ES常规操作DEMO
 */
@Slf4j
public class EsOperDemoTest {

    static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    //        new HttpHost("localhost", 9201, "http"),
                    new HttpHost("172.29.28.235", 9200, "http")
            ));

    static final String QIUSM1_TEST = "qiusm1_test";

    public static void main(String[] args) {
        try {
            deleteIndex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建索引
     */
    static void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(QIUSM1_TEST);
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
        log.info("aliases===>{}", response.getAliases());
        log.info("mappings===>{}", response.getMappings());
        log.info("settings===>{}", response.getSettings());
    }

    static void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(QIUSM1_TEST);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        log.info("索引操作 >> {}", response.isAcknowledged());
    }


    static void addDoc() throws IOException {
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

    static void selectDoc() throws IOException {
        GetRequest request = new GetRequest();
        request.index(QIUSM1_TEST).id("1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        log.info("doc >> {}", response.getSourceAsMap());
    }

    static void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.index(QIUSM1_TEST).id("1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        log.info("删除：index:{},result:{},", response.getIndex(), response.getResult());
    }

    /**
     * 批量添加数据
     */
    static void bulkAddDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();

        House house1 = createHouse();
        House house2 = createHouse();
        House house3 = createHouse();

        bulkRequest.add(new IndexRequest().index(QIUSM1_TEST).id("2").source(mapper.writeValueAsString(house1), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index(QIUSM1_TEST).id("3").source(mapper.writeValueAsString(house2), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index(QIUSM1_TEST).id("4").source(mapper.writeValueAsString(house3), XContentType.JSON));

        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("took:{},items:{}", response.getTook(), response.getItems());
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
     * 查询全部
     * {
     * "query": {
     * "match_all": {}
     * }
     * }
     *
     * @throws IOException IO
     */
    static void searchDoc() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(QIUSM1_TEST)
                .source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        log.info("total hits:{}, took:{}", hits.getTotalHits(), response.getTook());

        hits.forEach(h -> {
            log.info("record: {}", h.getSourceAsString());
        });

    }

    /**
     * 条件查询
     * {
     * "query": {
     * "term": {
     * "age": {
     * "value": "18"
     * }
     * }
     * }
     * }
     *
     * @throws IOException IO
     */
    static void searchQueryDoc() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(QIUSM1_TEST)
                .source(new SearchSourceBuilder().query(QueryBuilders.termQuery("houseName", "test")));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        log.info("total hits:{}, took:{}", hits.getTotalHits(), response.getTook());

        hits.forEach(h -> {
            log.info("record: {}", h.getSourceAsString());
        });
    }

    /**
     * 分页查询、查询排序
     * {
     * "query": {"match_all": {}},
     * "from": 0,
     * "size": 2
     * "sort":[{"age":{"order":"asc"}}]
     * }
     *
     * @throws IOException IO
     */
    static void searchDocPage() throws IOException {
        // 构建查询条件，相当于构建注释中的json结构
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchAllQuery());
        // 增加分页条件
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);
        // 增加排序条件,排序字段要设定为特定的值
        // searchSourceBuilder.sort("houseName", SortOrder.ASC);

        // 查询指定字段, fetchSource重载了很多方法，可以单个字段，也可以多个字段，使用过程中多参见API，再百度。
        String[] excludes = {"id"};
        String[] includes = {"houseName", "communityName", "cityName"};
        searchSourceBuilder.fetchSource(includes, excludes);

        // 构建查询请求体
        SearchRequest request = new SearchRequest();
        request.indices(QIUSM1_TEST)
                .source(searchSourceBuilder);

        // 发送请求，获取响应结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        log.info("total hits:{}, took:{}", hits.getTotalHits(), response.getTook());

        hits.forEach(h -> {
            log.info("record: {}", h.getSourceAsString());
        });

    }

    /**
     * {
     * "query": {
     * "bool": {
     * "must": [
     * {
     * "match": {
     * "age": 18
     * }
     * }
     * ],
     * "must_not": [
     * {
     * "match": {
     * "sex": "女"
     * }
     * }
     * ]
     * }
     * }
     * }
     *
     * @throws IOException
     */
    static void searchBoolDoc() throws IOException {
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
        log.info("total hits:{}, took:{}", hits.getTotalHits(), response.getTook());

        hits.forEach(h -> {
            log.info("record: {}", h.getSourceAsString());
        });
    }

    /**
     * 模糊查询
     *
     * @throws IOException
     */
    static void searchFuzzDoc()
            throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(QIUSM1_TEST);
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
     * 高亮查询
     */
    static void searchHighlighterDoc() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(QIUSM1_TEST);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("houseName", "t");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");

        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(termsQueryBuilder);

        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    }

    /**
     * 聚合查询
     * {
     * "aggs": {
     * "ageMax": {
     * "max": {
     * "field": "age"
     * }
     * }
     * }
     * }
     *
     * @throws IOException
     */
    static void searchAggregationDoc() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(QIUSM1_TEST);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");

        searchSourceBuilder.aggregation(aggregationBuilder);


    }
}
