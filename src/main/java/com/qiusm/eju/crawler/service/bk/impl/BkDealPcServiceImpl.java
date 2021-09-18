package com.qiusm.eju.crawler.service.bk.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.bk.BkDealPc;
import com.qiusm.eju.crawler.mapper.bk.BkDealPcMapper;
import com.qiusm.eju.crawler.service.bk.IBkDealPcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BkDealPcServiceImpl
        extends ServiceImpl<BkDealPcMapper, BkDealPc>
        implements IBkDealPcService {

    @Override
    public void checkInsert(BkDealPc var) {
        EntityWrapper<BkDealPc> wrapper = new EntityWrapper<>();
        wrapper.eq("detail_url", var.getDetailUrl());
        int count = this.selectCount(wrapper);
        if (count == 0) {
            this.insert(var);
        }
    }
}
