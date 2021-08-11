package com.qiusm.eju.crawler.mq.controller;

import com.qiusm.eju.crawler.mq.service.IRabbitMQSendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mq")
public class MqController {

    @Resource
    IRabbitMQSendService rabbitMQSendService;

    @GetMapping("sendMsg")
    public void sendMsg() {
        rabbitMQSendService.sendData("crawl_complete_notify_queue", "test");
    }
}
