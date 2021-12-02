package com.qiusm.eju.es;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.entity.es.House;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsOperDemoTwoTest extends EsDocmentApi {
    public static void main(String[] args) {
        try {
            bulkProcessor();
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    static void bulkProcessor() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index(QIUSM1_TEST).id("1");
        House house = new House();
        house.setHouseId("12");
        house.setCityId("31000012");
        house.setHouseName("测试名称122");
        String houseJson = JSONObject.toJSONString(house);
        request.source(houseJson, XContentType.JSON);
        bulkProcessor.add(request);
        // bulkProcessor.flush();
    }
}
