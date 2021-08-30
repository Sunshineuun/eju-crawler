package com.qiusm.eju.crawler.service.bk.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.bk.BkDealUrlHistory;
import com.qiusm.eju.crawler.mapper.bk.BkDealUrlHistoryMapper;
import com.qiusm.eju.crawler.service.bk.IBkDealUrlHistoryService;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BkDealUrlHistoryServiceImpl
        extends ServiceImpl<BkDealUrlHistoryMapper, BkDealUrlHistory>
        implements IBkDealUrlHistoryService {

    @Override
    public BkDealUrlHistory getBkHistoryByUrl(String url) {
        EntityWrapper<BkDealUrlHistory> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("url_base64", BeikeUtils.toBase64(url));
        BkDealUrlHistory his = this.selectOne(entityWrapper);

        // 大文本解压
        if (his != null && his.getIsSuccess() == 1 && !StringUtils.startsWith(his.getResult(), "{")) {
            his.setResult(StringUtils.gunzip(his.getResult()));
        }
        return his;
    }

    @Override
    public void upHis(BkDealUrlHistory his) {
        his.setCreateTime(new Date());
        // 大文本压缩 已{开头的都认为是json，需要进行压缩
        if (his.getIsSuccess() == 1) {
            his.setResult(StringUtils.gzip(his.getResult()));
        }
        if (his.getId() == null) {
            his.setUrlBase64(BeikeUtils.toBase64(his.getUrl()));
            this.insert(his);
        } else {
            this.updateById(his);
        }
    }
}
