package com.qiusm.eju.crawler.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
@Slf4j
@Component
public class EmailUtil {

    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.subject}")
    private String subject;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单文本邮件
     */
    public void sendSimpleMail(String to, String txt) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to.split(","));
            message.setSubject(subject);
            message.setText(txt);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送简单文本邮件异常！{}", e.getMessage());
        }
    }
}
