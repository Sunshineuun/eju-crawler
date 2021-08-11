package com.qiusm.eju.crawler.mq.service;

public interface IRabbitMQSendService {
    void sendData(String routingkey, Object vo);
}
