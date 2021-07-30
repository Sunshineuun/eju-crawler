package com.qiusm.eju.crawler.service.ajk.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.entity.ajk.AjkUrlHistory;
import com.qiusm.eju.crawler.mapper.ajk.AjkUrlHistoryMapper;
import com.qiusm.eju.crawler.service.ajk.IAjkUrlHistoryService;
import com.qiusm.eju.crawler.utils.bk.BeikeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class AjkUrlHistoryServiceImpl
        extends ServiceImpl<AjkUrlHistoryMapper, AjkUrlHistory>
        implements IAjkUrlHistoryService {

    @Override
    public AjkUrlHistory getAjkHistoryByUrl(RequestDto requestDto) {
        EntityWrapper<AjkUrlHistory> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("url_base64", BeikeUtils.toBase64(requestDto.getUrl(), requestDto.getRequestParam()));
        return this.selectOne(entityWrapper);
    }

    @Override
    public void upHis(AjkUrlHistory his) {
        his.setCreateTime(new Date());
        if (his.getId() == null) {
            his.setUrlBase64(BeikeUtils.toBase64(his.getUrl(), JSONObject.parseObject(his.getParams(), new TypeReference<Map<String, String>>() {})));
            this.insert(his);
        } else {
            this.updateById(his);
        }
    }

    @Override
    public void urlToBase64() {}
}
