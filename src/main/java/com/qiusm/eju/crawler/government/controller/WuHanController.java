package com.qiusm.eju.crawler.government.controller;

import com.qiusm.eju.crawler.government.wh.WuHanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
@Slf4j
@RestController
@RequestMapping("/wuhan")
public class WuHanController {
    @Resource
    private WuHanService service;

    private static Boolean lock = false;

    @RequestMapping("/start")
    public void start() {
        synchronized (WuHanController.class) {
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

    @RequestMapping("/house/start")
    public void houseStart() {
        synchronized (WuHanController.class) {
            if (!lock) {
                lock = true;
                try {
                    service.houseStart();
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

    @RequestMapping("/building/start")
    public void buildingStart() {
        synchronized (WuHanController.class) {
            if (!lock) {
                lock = true;
                try {
                    service.buildingStart();
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

    @RequestMapping("/unit/start")
    public void unitStart() {
        synchronized (WuHanController.class) {
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

    /**
     * 目前是假的，后续优化
     */
    @RequestMapping("/stop")
    public void stop() {
        service.stop();
    }
}
