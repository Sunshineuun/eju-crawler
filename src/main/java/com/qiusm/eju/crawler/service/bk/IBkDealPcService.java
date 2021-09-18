package com.qiusm.eju.crawler.service.bk;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.bk.BkDealPc;

public interface IBkDealPcService extends IService<BkDealPc> {
    void checkInsert(BkDealPc var);
}
