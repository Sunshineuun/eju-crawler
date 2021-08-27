package com.qiusm.eju.crawler.service.bk;

import com.baomidou.mybatisplus.service.IService;
import com.qiusm.eju.crawler.entity.bk.BkDealTask;

public interface IBkDealTaskService extends IService<BkDealTask> {
    // @Scheduled(cron = "0 */30 * * * ?")
    void scheduledTasks();
}
