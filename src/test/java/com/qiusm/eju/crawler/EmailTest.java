package com.qiusm.eju.crawler;

import com.qiusm.eju.crawler.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class EmailTest {
    @Resource
    EmailUtil emailUtil;

    @Test
    public void test1() {
        String text = "邮件发送测试";
        emailUtil.sendSimpleMail("583853240@qq.com", text);
    }
}
