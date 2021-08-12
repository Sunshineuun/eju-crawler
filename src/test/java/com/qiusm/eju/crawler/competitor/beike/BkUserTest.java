package com.qiusm.eju.crawler.competitor.beike;

import com.qiusm.eju.crawler.service.bk.IBkUserManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class BkUserTest {
    @Resource
    IBkUserManagementService userManagementService;

    @Test
    void getUserTest() {
        userManagementService.getUser();
    }

}
