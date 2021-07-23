package com.qiusm.eju.crawler.utils.http;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.annotation.ParserProcessor;
import com.qiusm.eju.crawler.base.entity.TaskInstance;
import com.qiusm.eju.crawler.base.parser.ParserInterface;
import com.qiusm.eju.crawler.base.vo.TaskInstanceRequest;
import com.qiusm.eju.crawler.enums.SourceTypeEnum;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.utils.*;
import com.qiusm.eju.crawler.utils.spring.SpringContextUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.qiusm.eju.crawler.constant.RedisKeyConstant.TASK_POOL_KEY;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.UNDERSCORE;

/**
 * @author qiushengming
 */
@Slf4j
@Getter
public class DownloadThread extends Thread {
    private static final int EXECUTE_THREAD_NUM = Runtime.getRuntime().availableProcessors() * 15;

    private volatile boolean isStop = false;
    /**
     * 被执行的任务
     */
    public TaskInstance taskInstance;
    /**
     * 线程工具
     */
    public ThreadService threadService = new ThreadService();
    public ValueOperations<String, Object> valueOperations;
    public ListOperations<String, Object> listOperations;
    /**
     * 解析器DAO
     */
    private String ipProxyUrl;

    public CycleAtomicInteger caiIn;
    public CycleAtomicInteger caiOut;
    private final static int REDIS_CLUSTER_NODES = 8;

    private Download chromeDriveClient;

    public DownloadThread(TaskInstanceRequest seedRequest, String ipProxyUrl) {
        this.taskInstance = seedRequest.getTaskInstance();
        this.valueOperations = SpringContextUtils.getBean("valueOperations");
        this.listOperations = SpringContextUtils.getBean("listOperations");
        this.ipProxyUrl = ipProxyUrl;
        this.chromeDriveClient = new SeleniumDownload();
        buildCycleAtomicInteger(this.taskInstance);
        // 初始化种子
        initializeSeed(seedRequest);
    }

    @Override
    public void run() {
        log.info("暂停5秒后开始执行任务。任务id:{}", taskInstance.getId());
        ThreadUtils.sleep(5);
        log.info("开始执行任务：{}", JSONUtils.toJSONString(this.taskInstance));
        try {

            Map<String, ParserInterface> parserProcessorMap = loadParserInterFace();
            if (MapUtils.isEmpty(parserProcessorMap)) {
                throw new BusinessException("110", taskInstance.getId());
            }

            //任务运行线程数
            int threadNum = taskInstance.getThreadNum() <= 0 ? EXECUTE_THREAD_NUM : taskInstance.getThreadNum();
            threadService.executeFutures(IntStream.range(1, threadNum).boxed().collect(Collectors.toList()),
                    this.downloadLoopFunction(parserProcessorMap), false, EXECUTE_THREAD_NUM);
        } catch (Exception e) {
            if (!isStop) {
                log.error("任务执行异常:{},{}", JSONUtils.toJSONString(this.taskInstance), StringUtils.stackTraceInfoToStr(e));
                // warnDetailService.taskExSendEmail(taskInstance, e);
            }
        } finally {
            log.warn("任务关闭:{}", JSONUtils.toJSONString(this.getTaskInstance()));
            threadService.shutdown(5);
            super.interrupt();
            clean();
        }
    }

    /**
     * 下载器循环执行 <br>
     * 这里，我认为解析器只针对请求进行绑定 <br>
     *
     * @param parserInterfaceMap 解析器列表
     */
    public Function<Integer, String> downloadLoopFunction(Map<String, ParserInterface> parserInterfaceMap) {
        return (e) -> {
            while (!isStop) {
                String htmlStr = "";
                TaskInstanceRequest request = null;
                String taskInstanceTaskPoolKey = null;
                for (int i = 0; i < 10 * REDIS_CLUSTER_NODES; i++) {
                    if (request != null) {
                        break;
                    } else if (i % REDIS_CLUSTER_NODES == 0) {
                        ThreadUtils.sleep(15);
                    }
                    taskInstanceTaskPoolKey = caiOut.next();
                    request = popRequestTask(taskInstanceTaskPoolKey);
                }

                if (request == null) {
                    // 结束  任务结束 修改状态  验证状态 总数入库
                    /*int total = taskInstanceService.complete(this.taskInstance, this.taskInstance.getCreateTime());
                    if (total > 0) {
                        log.info("任务:{},执行总数:{}", JSONUtils.toJSONString(this.taskInstance), total);
                    }*/
                    return null;
                }

                if (request.getTryNum() < 0) {
                    continue;
                }

                try {
                    ParserInterface parserProcessor = parserInterfaceMap.get(request.getCode());
                    if (parserProcessor == null) {
                        throw new BusinessException(10000, "找不到解析器。{}" + JSONObject.toJSONString(request));
                    }

                    OkHttpUtils httpClient = null;
                    if (SourceTypeEnum.SOURCE_01.getCode().equals(request.getSourceType())) {

                    } else {
                        httpClient = getHttpClient(request);

                        // 睡眠时间
                        Integer sleepTime = request.getSleepTime();
                        if (sleepTime != 0) {
                            ThreadUtils.sleep(RandomUtils.nextInt(5 < sleepTime ? 5 : sleepTime, 5 < sleepTime ? sleepTime : 5));
                        }

                        //是否需要登录cookie
                        Boolean isLogin = request.getIsLogin();
                        if (null != isLogin && isLogin) {
                            continue;
                        }

                        switch (request.getMethod()) {
                            case GET:
                                htmlStr = httpClient.proxyGet(
                                        request.getUrl(), parserProcessor.buildingHeader(request), parserProcessor.viewTry());
                                break;
                            case POST_FORM:
                                htmlStr = httpClient.proxyPostFrom(
                                        request.getUrl(), parserProcessor.buildingHeader(request), request.getParams(), parserProcessor.viewTry());
                                break;
                            case CHROME_DRIVE:
                                htmlStr = chromeDriveClient.get(request.getUrl());
                                break;
                            default:
                                htmlStr = httpClient.proxyPostJson(
                                        request.getUrl(), parserProcessor.buildingHeader(request),
                                        JSONUtils.toJSONString(request.getParams()), parserProcessor.viewTry());
                        }
                    }

                    if (parserProcessor.viewTry().test(htmlStr)) {
                        tryAgain(request, htmlStr, taskInstanceTaskPoolKey);
                    } else {
                        // 是否重新返回队列 true 返回队列
                        parserProcessor.execute(htmlStr, request, httpClient);
                        // 拉取新的请求
                        pullNewRequest(request);
                    }
                } catch (Exception ex) {
                    log.error("任务执行异常{},\n当前种子:{},\n任务:{}", StringUtils.stackTraceInfoToStr(ex), request, JSONUtils.toJSONString(this.taskInstance));
                    // warnDetailService.parserExSendEmail(request, parser, taskInstance, ex);
                }

            }
            return null;
        };
    }

    public void shutdownShop() {
        this.isStop = true;
    }

    private void clean() {
        this.taskInstance = null;
        this.valueOperations = null;
        this.listOperations = null;
        this.ipProxyUrl = null;
        this.chromeDriveClient.quit();
        this.chromeDriveClient = null;

        this.caiIn = null;
        this.caiOut = null;
    }

    private void buildCycleAtomicInteger(TaskInstance taskInstance) {
        if (StringUtils.isBlank(this.taskInstance.getQueueNames())) {
            caiIn = new CycleAtomicInteger(REDIS_CLUSTER_NODES, TASK_POOL_KEY + taskInstance.getId().toString());
            this.taskInstance.setQueueNames(String.join(",", caiIn.getNames()));
        } else {
            String queueNames = taskInstance.getQueueNames();
            String[] names = queueNames.split(",");
            caiIn = new CycleAtomicInteger(names.length, names);
        }
        caiOut = new CycleAtomicInteger(REDIS_CLUSTER_NODES, caiIn.getNames());
    }

    /**
     * 从队列中获取请求实例
     *
     * @param requestTaskPoolKey
     * @return
     */
    private TaskInstanceRequest popRequestTask(String requestTaskPoolKey) {
        String requestStr = (String) listOperations.leftPop(requestTaskPoolKey);
        if (StringUtils.isBlank(requestStr)) {
            return null;
        }

        TaskInstanceRequest request = JSONObject.parseObject(requestStr, TaskInstanceRequest.class);
        request.setTaskInstance(this.taskInstance);
        return request;
    }

    /**
     * 拉取新的请求实例
     *
     * @param request 请求实例
     */
    private void pullNewRequest(TaskInstanceRequest request) {
        if (CollectionUtils.isNotEmpty(request.getNewRequest())) {
            request.getNewRequest().forEach(var -> {
                String taskInstanceTaskPoolKey = caiIn.next();
                listOperations.rightPush(taskInstanceTaskPoolKey, JSONUtils.toJSONString(var));
            });
        }
    }

    /**
     * 加载指定包中所有的ParserInterface接口的实现，并且将其实例化。
     *
     * @return
     */
    private Map<String, ParserInterface> loadParserInterFace() {
        String scanPackage = "com.qiusm.eju.crawler.parser";
        TypeFilter[] typeFilters = new TypeFilter[]{
                new AnnotationTypeFilter(ParserProcessor.class)
        };
        List<Class<?>> classes = ClassResourceUtils.scanPackages(typeFilters, scanPackage);
        return classes.stream()
                .map(clazz -> {
                    try {
                        Constructor<?> var = clazz.getDeclaredConstructor();
                        return (ParserInterface) var.newInstance();
                    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        log.error("实例化失败：{}", e.getMessage());
                    }
                    return null;
                })
                .filter(var -> !(var == null || StringUtils.isBlank(var.getCode())))
                .collect(Collectors.toMap(ParserInterface::getCode, (var) -> var));
    }

    /**
     * 获取http请求客户端
     *
     * @param request 请求实例
     * @return http请求客户端
     */
    private OkHttpUtils getHttpClient(TaskInstanceRequest request) {
        //获取当前请求的charset
        String charset = request.getCharset();
        if (StringUtils.isBlank(charset)) {
            charset = taskInstance.getCharset();
        }
        return OkHttpUtils.Builder()
                .proxyUrl(ipProxyUrl)
                .connectTimeout(30000).readTimeout(30000).charset(charset)
                .builderHttp();
    }

    /**
     * @param request                     请求实例
     * @param htmlStr                     错误
     * @param taskInstanceTaskPoolKeyNode redis节点
     */
    private void tryAgain(TaskInstanceRequest request, String htmlStr, String taskInstanceTaskPoolKeyNode) {
        if (request.getTryNum() != null && request.getTryNum() >= 1) {
            request.setTryNum(request.getTryNum() - 1);
            listOperations.rightPush(taskInstanceTaskPoolKeyNode, JSONUtils.toJSONString(request));
            log.warn("url:{},请求失败:{},重试剩余次数:{}", JSONUtils.toJSONString(request), htmlStr.substring(0, 50), request.getTryNum());
        } else {
            log.warn("url:{},请求失败:{},重试结束error-{}", request.getUrl(), JSONUtils.toJSONString(request), htmlStr.substring(0, 50));

            String key = taskInstanceTaskPoolKeyNode.split(UNDERSCORE)[0] + "_ERROR";
            listOperations.rightPush(key, JSONUtils.toJSONString(request));
        }

        JSONObject jsonObject = requestToJson(request, htmlStr);
        String tableName = jsonObject.getString("file_name");

        String filePath = String.join(File.separator, "source", this.taskInstance.getType(), getTaskInstance().getBatchNo(), tableName);

        FileUtils.printFile(jsonObject.toJSONString(), filePath, "error_json_info.txt", true);
        FileUtils.printFile(htmlStr, filePath, jsonObject.getString("id") + ".txt", false);
        // kafkaTemplate.send(tableName, UUID.randomUUID().toString(),jsonObject.toString());
        log.info("异常信息存入文件。id:{}", jsonObject.getString("id"));
    }

    /**
     * 将请求实例和请求结果持久化到磁盘，一般用于出现异常情况是，对齐进行持久化。
     *
     * @param request 请求实例
     * @param htmlStr 响应结果
     * @return JSON
     */
    private JSONObject requestToJson(TaskInstanceRequest request, String htmlStr) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", UUID.randomUUID());
        //解析器code
        jsonObject.put("parser_code", request.getCode());
        //批次号
        jsonObject.put("batch_no", getTaskInstance().getBatchNo());
        //版本号（多个实例version属于同一个批次号）
        jsonObject.put("version_no", taskInstance.getVersionNo());
        //分发hdfs数据的目录（Hadoop中数据存放的目录file_name，按照解析器的data_table_name分类）
        jsonObject.put("file_name", "request_error_url");
        //任务创建人
        jsonObject.put("create_user", taskInstance.getCreateUser());
        //任务创建时间
        jsonObject.put("create_time", DateUtils.formatDateTime(new Date()));
        //任务更新人
        //任务来源source
        jsonObject.put("info_src", taskInstance.getType());
        //任务id
        jsonObject.put("task_id", taskInstance.getTaskId());
        jsonObject.put("request", request);
        jsonObject.put("htmlStrError", htmlStr.substring(0, 50));
        return jsonObject;
    }

    /**
     * 初始化的种子只要告诉
     *
     * @param seedRequest 种子请求
     */
    private void initializeSeed(TaskInstanceRequest seedRequest) {
        String taskInstanceTaskPoolKey = caiIn.next();
        listOperations.rightPush(taskInstanceTaskPoolKey, JSONUtils.toJSONString(seedRequest));
    }
}
