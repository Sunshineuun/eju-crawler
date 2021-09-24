package com.qiusm.eju.crawler.service.bk.impl;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.base.CommunitySkeletonTask;
import com.qiusm.eju.crawler.entity.bk.BkUser;
import com.qiusm.eju.crawler.enums.CommunitySkeletonTaskStateEnum;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.BuildingSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.HouseSearchV1;
import com.qiusm.eju.crawler.parser.competitor.beike.app.skeleton.UnitSearchV1;
import com.qiusm.eju.crawler.service.base.ICommunitySkeletonTaskService;
import com.qiusm.eju.crawler.service.bk.IBkSkeletonService;
import com.qiusm.eju.crawler.service.bk.IBkUserManagementService;
import com.qiusm.eju.crawler.utils.ThreadsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.qiusm.eju.crawler.constant.bk.BkBaseConstant.*;
import static com.qiusm.eju.crawler.constant.bk.BkHttpHeadConstant.LIANJIA_CITY_ID;

/**
 * @author qiushengming
 */
@Slf4j
@Service
public class BkSkeletonServiceImpl
        implements IBkSkeletonService {
    @Value("${eju.bk.skeleton.threadNum:8}")
    private Integer threadNum;

    @Resource
    private ICommunitySkeletonTaskService communitySkeletonTaskService;
    @Resource
    private IBkUserManagementService bkUserManagementService;
    @Resource
    private BuildingSearchV1 buildingSearchV1;
    @Resource
    private UnitSearchV1 unitSearchV1;
    @Resource
    private HouseSearchV1 houseSearchV1;
    @Resource
    private ValueOperations<String, String> valueOperations;

    final private ThreadsUtils urlThreadsUtils = new ThreadsUtils();
    final private ThreadsUtils communityThreadsUtils = new ThreadsUtils();
    private boolean isRunning = false;
    private static final Set<String> COMMUNITY_ID_SET = new ConcurrentHashSet<>();
    private final AtomicInteger submitTaskNum = new AtomicInteger(0);

    private String lockKey = "crawler:bk:skeleton";

    @Override
    public synchronized void scheduledTasks() {
        boolean success = Boolean.TRUE.equals(valueOperations.setIfAbsent(lockKey, "正在运行中", 2, TimeUnit.HOURS));
        if (!success) {
            return;
        }

        new Thread(() -> {
            List<CommunitySkeletonTask> list;
            int i = 0;
            while ((list = communitySkeletonTaskService.getByInState("0,1")).size() != 0) {

                log.info("定时任务开始处理, list:{}", list.size());

                for (CommunitySkeletonTask task : list) {
                    String msg = submitTask(task);
                    log.debug("{}", msg);
                }
                while (submitTaskNum.get() > 0) {
                    sleep();
                }
            }

            synchronized (BkSkeletonServiceImpl.class) {
                if (!isRunning) {
                    log.info("任务运行结束。");
                    isRunning = false;
                }
            }
        }).start();
    }

    public synchronized String submitTask(CommunitySkeletonTask task) {

        if (COMMUNITY_ID_SET.contains(task.getCommunityId())) {
            return "任务正在执行中。任务ID：" + task.getCommunityId();
        }

        // 到这里说明有空余线程可以使用，以下真正开始进入执行。

        submitTaskNum.incrementAndGet();
        // 将任务提交到线程池中进行执行
        communityThreadsUtils.getExecutorService(threadNum / 4).submit(() -> {
            COMMUNITY_ID_SET.add(task.getCommunityId());
            try {
                communitySkeleton(task);
            } catch (Throwable t) {
                log.error("{}", t);
            }
            task.updateById();
            COMMUNITY_ID_SET.remove(task.getCommunityId());
            submitTaskNum.decrementAndGet();
            valueOperations.getOperations().expire(lockKey, 2, TimeUnit.HOURS);
        });

        return "任务提交成功，任务id：" + task.getId();
    }

    /**
     * 处理逻辑：<br>
     * 1. desc，记录执行中的异常情况。 <br>
     *
     * @param task 小区骨架任务实体
     * @return 小区+楼栋+单元+房号列表
     */
    @Override
    public JSONArray communitySkeleton(CommunitySkeletonTask task) {
        if (task.getCityId() == null) {
            throw new BusinessException(999, "城市不能为空" + task);
        }
        // 初始化计数器
        int buildingCount = 0;
        int unitCount = 0;
        int buildingTargetCount = 0;
        int unitTargetCount = 0;

        // 返回结果
        JSONArray result = new JSONArray();

        // 骨架数据抓取开始 -----------------------------------------------------------------------------------------------

        // 用小区信息，请求楼栋列表
        JSONArray buildingList = buildingHandlerToArray(task);
        if (CollectionUtils.isEmpty(buildingList)) {
        } else {
            // 楼栋列表
            buildingTargetCount += buildingList.size();
            result.addAll(buildingList);
        }

        // 用楼栋列表信息，请求单元列表
        JSONArray unitResult = new JSONArray();
        if (CollectionUtils.isNotEmpty(buildingList)) {
            log.debug("开始抓取单元列表, 楼栋列表数量：{}", buildingList.size());
            // 多线程执行抓取逻辑
            List<JSON> unitList = urlThreadsUtils.executeFutures(buildingList,
                    (e) -> {
                        // 如果执行成功返回JSONArray；否则返回楼栋信息。
                        JSONObject building = (JSONObject) e;
                        JSONArray unitArray = unitHandlerToArray(task, building);
                        if (unitArray != null) {
                            building.put("unit", unitArray);
                            return unitArray;
                        } else {
                            return building;
                        }
                    }, threadNum);
            // 对抓取结果进行运算
            for (JSON unit : unitList) {
                // 小区单元信息抓取
                if (unit instanceof JSONObject) {
                } else if (unit instanceof JSONArray) {
                    // 楼栋获取单元成功
                    buildingCount++;
                    // 单元列表
                    unitResult.addAll(((JSONArray) unit));
                } else {
                    log.info("unit: {}", unit);
                }
            }
        }
        unitTargetCount += unitResult.size();
        result.addAll(unitResult);

        //用单元列表信息，请求房号列表
        JSONArray houseResult = new JSONArray();
        if (CollectionUtils.isNotEmpty(unitResult)) {
            log.debug("开始抓取房号列表, 单元列表数量：{}", unitResult.size());
            // 多线程执行抓取逻辑
            List<JSON> houseList = urlThreadsUtils.executeFutures(unitResult,
                    (e) -> {
                        // 如果执行成功返回JSONArray；否则返回楼栋信息。
                        JSONObject unit = (JSONObject) e;
                        JSONArray houseArray = houseHandlerToArray(task, unit);
                        if (houseArray != null) {
                            unit.put("house", houseArray);
                            return houseArray;
                        } else {
                            return unit;
                        }
                    }, threadNum);
            // 对抓取逻辑进行统计
            for (JSON house : houseList) {
                if (house instanceof JSONObject) {
                } else if (house instanceof JSONArray) {
                    // 单元获取房号成功
                    unitCount++;
                    houseResult.addAll(((JSONArray) house));
                } else {
                    log.info("house: {}", house);
                }
            }
        }
        result.addAll(houseResult);

        // 任务状态设置
        if (buildingCount + unitCount == buildingTargetCount + unitTargetCount) {
            task.setStateEnum(CommunitySkeletonTaskStateEnum.COMPLETE);
        } else {
            task.setStateEnum(CommunitySkeletonTaskStateEnum.EXCEPTION);
        }

        // 记录任务结束时间
        task.setEndTime(new Date());

        StringBuilder desc = new StringBuilder();
        // 因为异常信息会一直拼接，但是为了保障最新异常信息的存储，进行截取操作。
        desc.append(String.format("楼栋：%s/%s;单元：%s/%s。", buildingCount, buildingTargetCount,
                unitCount, unitTargetCount));
        task.setDesc(desc.toString());
        // 统计异常情况
        log.info("{}/{}/{}.{}", task.getId(), task.getCommunityName(), task.getCommunityId(), desc);

        // log.info("处理结果：{}", result.toJSONString());
        log.info("小区骨架数据：{}", buildingList);
        return result;
    }

    private JSONArray buildingHandlerToArray(CommunitySkeletonTask task) {
        ResponseDto responseDto = buildingHandlerToDto(task);
        JSONArray array = responseDto.getResult().getJSONArray(LIST);
        if (array != null) {
            array.forEach(o -> {
                JSONObject var2 = ((JSONObject) o);
                var2.put(CITY_ID, task.getCityId());
                var2.put(CREATE_TIME, new Date());
                var2.put(TITLE_ID, task.getCommunityId());
                var2.put(TITLE, task.getCommunityName());
            });
        }
        return array;
    }

    private ResponseDto buildingHandlerToDto(CommunitySkeletonTask task) {
        RequestDto.Builder builder = RequestDto.builder()
                .user(bkUserManagementService.getUser())
                .requestParam(COMMUNITY_ID, task.getCommunityId())
                .head(LIANJIA_CITY_ID, task.getCityId())
                .task(task)
                .isLoad(true);
        RequestDto requestDto = builder.build();
        ResponseDto responseDto = buildingSearchV1.execute(requestDto);
        updateBkUser(requestDto.getUser());
        return responseDto;
    }

    private JSONArray unitHandlerToArray(CommunitySkeletonTask task, JSONObject building) {
        ResponseDto responseDto = unitHandlerToDto(task, building);
        JSONArray array = responseDto.getResult().getJSONArray(LIST);
        if (array != null) {
            array.forEach(o -> {
                JSONObject var2 = ((JSONObject) o);
                JSONObject var3 = new JSONObject();
                var3.putAll(building);
                var3.putAll(var2);
                var2.putAll(var3);
            });
        }
        return array;
    }

    private ResponseDto unitHandlerToDto(CommunitySkeletonTask task, JSONObject building) {
        RequestDto.Builder builder = RequestDto.builder()
                .user(bkUserManagementService.getUser())
                .requestParam(BUILDING_ID, building.getString(BUILDING_ID))
                .head(LIANJIA_CITY_ID, building.getString(CITY_ID))
                .isLoad(true);
        if (task != null) {
            builder.task(task);
        }
        RequestDto requestDto = builder.build();
        ResponseDto responseDto = unitSearchV1.execute(requestDto);
        updateBkUser(requestDto.getUser());
        return responseDto;
    }

    private JSONArray houseHandlerToArray(CommunitySkeletonTask task, JSONObject unit) {
        ResponseDto responseDto = houseHandlerToDto(task, unit);
        JSONArray array = responseDto.getResult().getJSONArray(LIST);
        if (array != null) {
            array.forEach(o -> {
                JSONObject var2 = ((JSONObject) o);
                JSONObject var3 = new JSONObject();
                var3.putAll(unit);
                var3.putAll(var2);
                var2.putAll(var3);
            });
        }
        return array;
    }

    private ResponseDto houseHandlerToDto(CommunitySkeletonTask task, JSONObject unit) {
        RequestDto.Builder builder = RequestDto.builder()
                .user(bkUserManagementService.getUser())
                .requestParam(UNIT_ID, unit.getString(UNIT_ID))
                .head(LIANJIA_CITY_ID, unit.getString(CITY_ID))
                .isLoad(true);
        if (task != null) {
            builder.task(task);
        }
        RequestDto requestDto = builder.build();
        ResponseDto responseDto = houseSearchV1.execute(requestDto);
        updateBkUser(requestDto.getUser());
        return responseDto;
    }

    /**
     * 判断用户状态，如果用户状态不为1则将其更新到数据库中
     */
    private void updateBkUser(BkUser user) {
        if (user.getState() != 1) {
            bkUserManagementService.updateBkUser(user);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
