package com.qiusm.eju.crawler.service.base.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qiusm.eju.crawler.entity.base.CommunitySkeletonTask;
import com.qiusm.eju.crawler.enums.CommunitySkeletonTaskStateEnum;
import com.qiusm.eju.crawler.mapper.base.CommunitySkeletonTaskMapper;
import com.qiusm.eju.crawler.service.base.ICommunitySkeletonTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommunitySkeletonTaskServiceImpl
        extends ServiceImpl<CommunitySkeletonTaskMapper, CommunitySkeletonTask>
        implements ICommunitySkeletonTaskService {

    @Override
    public void checkAndAdd(CommunitySkeletonTask task) {
        // 按照小区ID+城市ID进行判重
        EntityWrapper<CommunitySkeletonTask> wrapper = new EntityWrapper<>();
        wrapper.eq("community_id", task.getCommunityId())
                .eq("city_id", task.getCityId());
        List<CommunitySkeletonTask> list = this.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            task.setStateEnum(CommunitySkeletonTaskStateEnum.INIT);
            this.insert(task);
        } else {
            log.debug("发现重复小区：{}", task);
        }
    }

    @Override
    public List<CommunitySkeletonTask> getByInState(String state) {
        Page<CommunitySkeletonTask> page = new Page<>(1, 1000);
        EntityWrapper<CommunitySkeletonTask> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("state", state)
                .orderBy("level,id");

        this.selectPage(page, entityWrapper);
        return page.getRecords();
    }
}
