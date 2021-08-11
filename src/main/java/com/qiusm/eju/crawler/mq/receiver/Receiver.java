package com.qiusm.eju.crawler.mq.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author qiushengming
 */
@Slf4j
@Component
public class Receiver {
    @RabbitHandler
    @RabbitListener(queues = "crawl_complete_notify_queue", ackMode = "MANUAL", concurrency = "1")
    public void process(Object vo, Channel channel, Message message) {
        log.info("{}", vo);
        log.info("{}", message);
    }
}
