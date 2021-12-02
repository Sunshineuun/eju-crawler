package com.qiusm.eju.es;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.es.House;
import com.qiusm.eju.crawler.entity.es.PushLog;
import com.qiusm.eju.crawler.mapper.es.HouseRepository;
import com.qiusm.eju.crawler.mapper.es.PushLogRepository;
import com.qiusm.eju.crawler.mapper.goodpush.TaskErrorMapper;
import com.qiusm.eju.crawler.utils.lang.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import javax.annotation.Resource;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchRestTemplate esRestTemplate;
    @Resource
    private HouseRepository houseRepository;
    @Resource
    private PushLogRepository pushLogRepository;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void test() throws IOException {
        String str;
        LineNumberReader lnr = new LineNumberReader(new FileReader("source/er_fang_list.txt"));
        long i = 1L;
        long start = 0L;
        long s = DateUtils.getCurrentTimeMillis();
        while ((str = lnr.readLine()) != null) {
            i++;
            if (i < start) {
                continue;
            }
            House house = JSONObject.parseObject(str, House.class);
            houseRepository.save(house);

            if (i % 100 == 0) {
                long e = DateUtils.getCurrentTimeMillis();
                log.info("索引位置：{}, 耗时：{}", i, e - s);
                s = e;
            }
        }

    }

    /**
     * Elastic快速插入的方案
     *
     * @throws IOException 文件读取失败的情况下
     */
    @Test
    public void test1() throws IOException {/*
        String str;
        LineNumberReader lnr = new LineNumberReader(new FileReader("source/er_fang_list.txt"));
        long i = 1L;
        long s = DateUtils.getCurrentTimeMillis();
        BulkProcessor.Builder builder = BulkProcessor.builder(((bulkRequest, bulkResponseActionListener) -> {
            //bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);//写入后立刻刷新，效率比较低
            restHighLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener);
        }), listener());
        //设置 request 的数量,每添加10000个request，执行一次bulk操作
        builder.setBulkActions(10000);
        // 每达到100M的请求size时，执行一次bulk操作
        builder.setBulkSize(new ByteSizeValue(100L, ByteSizeUnit.MB));
        // 每5s执行一次bulk操作
        builder.setFlushInterval(TimeValue.timeValueSeconds(10));
        // 设置并发请求数。默认是1，表示允许执行1个并发请求，积累bulk requests和发送bulk是异步的，其数值表示发送bulk的并发线程数（可以为2、3、...）；若设置为0表示二者同步
        builder.setConcurrentRequests(1);
        // 最大重试次数为3次，启动延迟为100ms。
        builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(100), 3));
        BulkProcessor build = builder.build();
        while ((str = lnr.readLine()) != null) {
            House house = JSONObject.parseObject(str, House.class);
            build.add(
                    new IndexRequest("house")
                            .source(JSONObject.toJSONString(house), XContentType.JSON)
            );
        }
        build.flush();
        build.close();
    */}

    private BulkProcessor.Listener listener() {
        return new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                log.info("1. 【beforeBulk】批次[{}] 携带 {} 请求数量", executionId, request.numberOfActions());
            }

            /***
             * // 在每次执行BulkRequest后调用，通过此方法可以获取BulkResponse是否包含错误
             * @param executionId
             * @param request
             * @param response
             */
            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                if (!response.hasFailures()) {
                    log.info("2. 【afterBulk-成功】批量 [{}] 完成在 {} ms", executionId, response.getTook().getMillis());
                } else {
                    BulkItemResponse[] items = response.getItems();
                    for (BulkItemResponse item : items) {
                        if (item.isFailed()) {
                            log.info("2. 【afterBulk-失败】批量 [{}] 出现异常的原因 : {}", executionId, item.getFailureMessage());
                            break;
                        }
                    }
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  Throwable failure) {

                List<DocWriteRequest<?>> requests = request.requests();
                List<String> esIds = requests.stream().map(DocWriteRequest::id).collect(Collectors.toList());
                log.error("3. 【afterBulk-failure失败】es执行bluk失败,失败的esId为：{}", esIds, failure);
            }
        };
    }

    @Test
    public void testGet() {
        House house = new House();
        house.setCommunityName("远洋");
        long s = System.currentTimeMillis();
        Iterable<House> aIterable = houseRepository.findByCommunityName("远洋");
        System.out.println(System.currentTimeMillis() - s);
        for (House h : aIterable) {
            System.out.println(h);
        }

    }

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * 聚合统计
     */
    @Test
    public void testC1() {
        //目标：搜索写博客写得最多的用户（一个博客对应一个用户），通过搜索博客中的用户名的频次来达到想要的结果
        //首先新建一个用于存储数据的集合
        List<String> ueserNameList = new ArrayList<>();
        //1.创建查询条件，也就是QueryBuild
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();//设置查询所有，相当于不设置查询条件
        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //2.0 设置QueryBuilder
        nativeSearchQueryBuilder.withQuery(matchAllQuery);
        //2.1设置搜索类型，默认值就是QUERY_THEN_FETCH，参考https://blog.csdn.net/wulex/article/details/71081042
        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);//指定索引的类型，只先从各分片中查询匹配的文档，再重新排序和排名，取前size个文档
        //2.2指定索引库和文档类型
        // nativeSearchQueryBuilder.with("myBlog").withTypes("blog");//指定要查询的索引库的名称和类型，其实就是我们文档@Document中设置的indedName和type
        //2.3重点来了！！！指定聚合函数,本例中以某个字段分组聚合为例（可根据你自己的聚合查询需求设置）
        //该聚合函数解释：计算该字段(假设为username)在所有文档中的出现频次，并按照降序排名（常用于某个字段的热度排名）
        TermsAggregationBuilder termsAggregation = AggregationBuilders.terms("给聚合查询取的名").field("communityName");
        nativeSearchQueryBuilder.addAggregation(termsAggregation);
        //2.4构建查询对象
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        //3.执行查询
        //3.1方法1,通过reporitory执行查询,获得有Page包装了的结果集
        SearchHits<House> search = elasticsearchOperations.search(nativeSearchQuery, House.class);
        SearchHit<House> content = search.getSearchHit(1);
        for (SearchHit<House> houseSearchHit : search) {
            System.out.println(houseSearchHit.getContent());
        }

    }

    @Resource
    private TaskErrorMapper taskErrorMapper;

    @Test
    void testPushLog() {
        List<Map<String, Object>> list;
        int minId = 0 * 100000;
        int step = 100000;
        while (CollectionUtils.isNotEmpty((list = taskErrorMapper.select(minId, step)))) {
            long s = System.currentTimeMillis();
            List<PushLog> pls = new ArrayList<>();
            list.forEach(map -> {
                PushLog pl = JSONObject.parseObject(JSONObject.toJSONString(map), PushLog.class);
                JSONObject j = JSONObject.parseObject(pl.getDataJson());
                if (j != null) {
                    pl.setBatchNo(j.getString("batchNo"));
                    pl.setInterfaceId(String.valueOf(j.getInteger("id")));
                }
                pl.setId(null);
                pl.setCreateTime(new Date());
                pl.setSuccess(0);
                pl.setDataJson("-");
                pls.add(pl);
            });
            log.info("类型转换耗时：{}", System.currentTimeMillis() - s);

            s = System.currentTimeMillis();
            pushLogRepository.saveAll(pls);
            log.info("存储ES耗时：{}", System.currentTimeMillis() - s);
            minId += step;
        }

    }

    @Test
    void testPushLogUpdate() {
        int minId = 0;
        int step = 100;
        List<Map<String, Object>> list = taskErrorMapper.select(minId, step);

        list.forEach(map -> {
            PushLog pl = JSONObject.parseObject(JSONObject.toJSONString(map), PushLog.class);
            JSONObject j = JSONObject.parseObject(pl.getDataJson());
            if (j != null) {
                pl.setBatchNo(j.getString("batchNo"));
                pl.setInterfaceId(String.valueOf(j.getInteger("id")));
            }
            pl.setCreateTime(new Date());
            pl.setSuccess(0);
            pl.setDataJson("-");
            pushLogRepository.save(pl);
        });
    }

    @Test
    void test20211105() {
        PushLog pl = new PushLog();
        pl.setId(1L);
        pl.setCityName("上海11");
        pushLogRepository.save(pl);
    }
}
