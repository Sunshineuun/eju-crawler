package com.qiusm.eju.crawler.controller.government;

import com.qiusm.eju.crawler.government.nj.NanJinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
@Slf4j
@RestController
@RequestMapping("/nanjin")
public class NanJinController {

    @Resource
    private NanJinService service;

    private static Boolean lock = false;

    @RequestMapping("/start")
    public void start() {
        synchronized (NanJinController.class) {
            if (!lock) {
                lock = true;
                try {
                    service.start();
                } catch (Exception e) {
                    log.error("{}", e.getMessage());
                } finally {
                    lock = false;
                }
            } else {
                log.warn("已经存在运行的实例！");
            }
        }
    }

    @RequestMapping("/unitStart")
    public void unitStart(){
        synchronized (NanJinController.class) {
            if (!lock) {
                lock = true;
                try {
                    service.unitStart();
                } catch (Exception e) {
                    log.error("{}", e.getMessage());
                } finally {
                    lock = false;
                }
            } else {
                log.warn("已经存在运行的实例！");
            }
        }
    }

}
