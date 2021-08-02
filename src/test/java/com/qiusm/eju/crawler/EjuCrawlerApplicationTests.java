package com.qiusm.eju.crawler;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiusm.eju.crawler.entity.bk.BkFence;
import com.qiusm.eju.crawler.service.bk.IBkFenceService;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import com.qiusm.eju.crawler.utils.poi.LocationConvertUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class EjuCrawlerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private IBkFenceService bkFenceService;

    @Test
    public void locationConvert() {
        int id = 2056;
        int step = 1000;
        OkHttpUtils httpUtils = OkHttpUtils.Builder().builderHttp();
        while (true) {
            EntityWrapper<BkFence> entityWrapper = new EntityWrapper<>();
            entityWrapper.between("id", id, id + step).eq("source", "baidu");
            List<BkFence> bdFence = bkFenceService.selectList(entityWrapper);
            id += 1000;
            if (CollectionUtils.isNotEmpty(bdFence)) {
                bdFence.forEach(o -> {
                    String gdLocation1 = LocationConvertUtils.gdInternalConvertOfLength(
                            o.getLongitude() + "," + o.getLatitude(), "baidu", 8, httpUtils);
                    String gdLocation2 = LocationConvertUtils.gdInternalConvertOfLength(
                            o.getFence(), "baidu", 8, httpUtils);
                    o.setSource("gaode");
                    o.setLongitude(gdLocation1.split(",")[0]);
                    o.setLatitude(gdLocation1.split(",")[1]);
                    o.setFence(gdLocation2);
                    o.setId(null);
                    o.insert();
                });
            }else{
                return;
            }
        }
    }

}
