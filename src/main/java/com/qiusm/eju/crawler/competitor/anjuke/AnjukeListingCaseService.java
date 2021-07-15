package com.qiusm.eju.crawler.competitor.anjuke;

import com.qiusm.eju.crawler.base.entity.TaskInstance;
import com.qiusm.eju.crawler.base.vo.TaskInstanceRequest;
import com.qiusm.eju.crawler.constant.EjuConstant;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.utils.http.DownloadThread;
import org.springframework.stereotype.Service;

import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_INDEX_CODE;

/**
 * 安居客挂牌案例
 *
 * @author qiushengming
 */
@Service
public class AnjukeListingCaseService {

    /**
     * 二手房案例抓取启动
     */
    public void secondhandStart() {

        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setBatchNo("202107071100_0001");
        taskInstance.setTaskId(1L);
        taskInstance.setType("AJK");
        taskInstance.setThreadNum(8);
        taskInstance.setTaskId(1L);
        TaskInstanceRequest seedRequest = new TaskInstanceRequest();
        seedRequest.setUrl("https://shanghai.anjuke.com/sale/");
        seedRequest.setCode(AJK_PC_SECONDHAND_LISTING_INDEX_CODE);
        seedRequest.setTaskInstance(taskInstance);
        seedRequest.setMethod(RequestMethodEnum.CHROME_DRIVE);
        DownloadThread downloadThread = new DownloadThread(seedRequest, EjuConstant.PROXY_URL0);
        downloadThread.start();
    }
}
