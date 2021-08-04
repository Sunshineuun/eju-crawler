package com.qiusm.eju.crawler.service.task.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.task.CrawlerUrlHistory;
import com.qiusm.eju.crawler.mapper.task.CrawlerUrlHistoryMapper;
import com.qiusm.eju.crawler.service.task.ICrawlerUrlHistoryService;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author qiushengming
 */
@Service
public class CrawlerUrlHistoryServiceImpl
        extends ServiceImpl<CrawlerUrlHistoryMapper, CrawlerUrlHistory>
        implements ICrawlerUrlHistoryService {
    @Override
    public void upHis(CrawlerUrlHistory his) {
        his.setCreateTime(new Date());
        if (his.getId() == null) {
            his.setUrlBase64(BeikeUtils.toBase64(his.getUrl()));
            this.insert(his);
        } else {
            this.updateById(his);
        }
    }

    @Override
    public CrawlerUrlHistory getByUrl(String url) {
        EntityWrapper<CrawlerUrlHistory> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("url_base64", BeikeUtils.toBase64(url));
        return this.selectOne(entityWrapper);
    }
}
