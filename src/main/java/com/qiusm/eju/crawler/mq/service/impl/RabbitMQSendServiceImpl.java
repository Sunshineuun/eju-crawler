package com.qiusm.eju.crawler.mq.service.impl;

import com.qiusm.eju.crawler.mq.service.IRabbitMQSendService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RabbitMQSendServiceImpl
        implements IRabbitMQSendService {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * @param routingkey
     * @param vo
     */
    @Override
    public void sendData(String routingkey, Object vo) {
        rabbitTemplate.convertAndSend(routingkey, vo);
    }

}
