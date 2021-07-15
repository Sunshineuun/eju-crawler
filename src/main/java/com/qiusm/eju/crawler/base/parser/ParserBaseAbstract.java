package com.qiusm.eju.crawler.base.parser;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.annotation.ParserProcessor;
import com.qiusm.eju.crawler.base.entity.ParserResult;
import com.qiusm.eju.crawler.base.entity.TaskInstance;
import com.qiusm.eju.crawler.base.vo.TaskInstanceRequest;
import com.qiusm.eju.crawler.utils.DateUtils;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static com.qiusm.eju.crawler.constant.RedisKeyConstant.TOTAL_KEY;

/**
 * @author qiushengming
 */
@ParserProcessor
@Slf4j
public abstract class ParserBaseAbstract implements ParserInterface {

    private final static String RULE_NAME_DEFAULT = "currParser";
    private TaskInstanceRequest request;

    /**
     * 解析请求结果
     *
     * @param htmlStr             被解析的文本内容
     * @param taskInstanceRequest 被解析对象源自的请求
     * @param okHttpUtils         http客户端
     * @return 解析结果
     */
    public abstract ParserResult parser(String htmlStr, TaskInstanceRequest taskInstanceRequest, OkHttpUtils okHttpUtils);

    public void archiveData(List<JSONObject> list, String nid) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(e -> archiveData(null, e, nid));
        }
    }

    public void archiveData(JSONObject obj, String nid) {
        archiveData(null, obj, nid);
    }

    public void archiveData(String topic, List<JSONObject> list, String nid) {
        if (!list.isEmpty()) {
            list.forEach(e -> archiveData(topic, e, nid));
        }
    }

    @Override
    public final void execute(String htmlStr, TaskInstanceRequest request, OkHttpUtils okHttpUtils) {
        this.request = request;
        ParserResult parserResult = parser(htmlStr, request, okHttpUtils);
        complete(parserResult);
    }

    private void complete(ParserResult o) {
        if (o == null) {
            return;
        }
        TaskInstance taskInstance = this.request.getTaskInstance();
        String taskInstanceTotalKey = TOTAL_KEY + taskInstance.getId() + ":";

        String dataTableName = this.getCode();
        String key = taskInstanceTotalKey + dataTableName;
        if (o.getResultJson() != null && !o.getResultJson().isEmpty()) {
            archiveData(o.getResultJson(), o.getNid());
            ParserUtils.getValueOperations().increment(key, 1);
        }
        if (CollectionUtils.isNotEmpty(o.getResultList())) {
            archiveData(o.getResultList(), o.getNid());
            ParserUtils.getValueOperations().increment(key, o.getResultList().size());
        }
        if (MapUtils.isNotEmpty(o.getTopicResultList())) {
            o.getTopicResultList().forEach((topic, list) -> {
                archiveData(topic, list, o.getNid());
                ParserUtils.getValueOperations().increment(taskInstanceTotalKey + topic, list.size());
            });
        }
        if (CollectionUtils.isNotEmpty(o.getPictureList())) {
            //图片结果集入库
            // archivePicture(o.getPictureList(), o.getNid());
        }
        if (CollectionUtils.isNotEmpty(o.getRequests())) {
            List<TaskInstanceRequest> list = o.getRequests().stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            request.setNewRequest(list);
        }
    }

    /**
     * 数据存储
     *
     * @param topic      主题
     * @param jsonObject 数据
     * @param nid        ？
     */
    private void archiveData(String topic, JSONObject jsonObject, String nid) {
        if (StringUtils.isBlank(topic)) {
            topic = this.getCode();
        }
        TaskInstance taskInstance = this.request.getTaskInstance();
        //关联主键id
        jsonObject.put("nid", nid);
        //解析器code
        jsonObject.put("parser_code", getCode());
        //批次号
        jsonObject.put("batch_no", taskInstance.getBatchNo());
        //版本号（多个实例version属于同一个批次号）
        jsonObject.put("version_no", taskInstance.getVersionNo());
        //分发hdfs数据的目录（Hadoop中数据存放的目录file_name，按照解析器的data_table_name分类）
        jsonObject.put("file_name", topic);
        //任务创建人
        jsonObject.put("create_user", taskInstance.getCreateUser());
        //任务创建时间
        jsonObject.put("create_time", DateUtils.formatDateTime(new Date()));
        //任务来源source
        jsonObject.put("info_src", taskInstance.getType());
        //任务id
        jsonObject.put("task_id", taskInstance.getTaskId());

        String filePath = String.join(File.separator, "source", taskInstance.getType(), taskInstance.getBatchNo());
        FileUtils.printFile(jsonObject.toJSONString(), filePath, topic + ".txt", true);
    }
}
