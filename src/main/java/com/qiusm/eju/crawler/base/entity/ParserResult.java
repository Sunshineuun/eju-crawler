package com.qiusm.eju.crawler.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.base.vo.TaskInstanceRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 解析器的返回对象
 * </p>
 *
 * @author lz
 * @since 2018-11-28
 */
@Data
@Slf4j
public class ParserResult {
    /**
     * 存储图片,发送至kafka，用于消费图片上传存储通
     */
    List<PictureKey> pictureList;
    /**
     * 对象类型的返回结果
     */
    JSONObject resultJson;
    /**
     * 数组类型的返回结果
     */
    List<JSONObject> resultList;
    /**
     * 关联的主键id
     */
    String nid;
    /**
     * 任务实例请求
     */
    List<TaskInstanceRequest> requests;
    /**
     * 数组类型的返回结果
     */
    Map<String,List<JSONObject>> topicResultList;
}
