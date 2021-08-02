package com.qiusm.eju.crawler.service.poi.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.poi.LocationKey;
import com.qiusm.eju.crawler.enums.poi.LocationKeyEnum;
import com.qiusm.eju.crawler.mapper.poi.LocationKeyMapper;
import com.qiusm.eju.crawler.service.poi.ILocationKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LocationKeyServiceImpl
        extends ServiceImpl<LocationKeyMapper, LocationKey>
        implements ILocationKeyService, CommandLineRunner {

    private static final Map<String, List<String>> KEY_MAP = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        try {
            List<LocationKey> locationKeys = this.selectList(null);
            if (null != locationKeys && locationKeys.size() > 0) {
                List<String> gaodeList = new ArrayList<>();
                List<String> baiduList = new ArrayList<>();
                for (LocationKey locationKey : locationKeys) {
                    if (LocationKeyEnum.GAO_DE.getCode().equals(locationKey.getSource())) {
                        gaodeList.add(locationKey.getApplyKey());
                    } else if (LocationKeyEnum.BAI_DU.getCode().equals(locationKey.getSource())) {
                        baiduList.add(locationKey.getApplyKey());
                    }
                }
                KEY_MAP.put(LocationKeyEnum.GAO_DE.getCode(), gaodeList);
                KEY_MAP.put(LocationKeyEnum.BAI_DU.getCode(), baiduList);
            }
            log.info("初始化百度高德key成功!");
        } catch (Exception e) {
            log.info("初始化百度高德key失败!{}", e.getMessage());
        }
    }


    /**
     * @return 获取高德key
     */
    @Override
    public List<String> getGaodeList() {
        try {
            List<String> gaodeList = KEY_MAP.get(LocationKeyEnum.GAO_DE.getCode());
            return new ArrayList<>(gaodeList);
        } catch (Exception e) {
            log.info("初始化高德key失败!");
        }
        return null;
    }

    /**
     * @return 获取百度key
     */
    @Override
    public List<String> getBaiduList() {
        try {
            List<String> baiduList = KEY_MAP.get(LocationKeyEnum.BAI_DU.getCode());
            return new ArrayList<>(baiduList);
        } catch (Exception e) {
            log.info("初始化百度key失败!");
        }
        return null;
    }
}
