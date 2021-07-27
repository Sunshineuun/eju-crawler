package com.qiusm.eju.crawler.controller.bk;

import com.qiusm.eju.crawler.service.bk.IBkUrlHistoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiushengming
 */
@Api(tags = {"BeikeUrlHistory", "URL请求记录"})
@Slf4j
@RestController
@RequestMapping("/bk/urlHistory")
public class BkDealUrlHistoryController {

    @Autowired
    protected IBkUrlHistoryService historyService;

    @GetMapping("toBase64")
    public void urlToBase64() {
        historyService.urlToBase64();
    }
}
