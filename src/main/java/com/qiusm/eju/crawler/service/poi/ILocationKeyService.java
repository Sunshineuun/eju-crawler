package com.qiusm.eju.crawler.service.poi;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.poi.LocationKey;

import java.util.List;

public interface ILocationKeyService extends IService<LocationKey> {
    List<String> getGaodeList();

    List<String> getBaiduList();
}
