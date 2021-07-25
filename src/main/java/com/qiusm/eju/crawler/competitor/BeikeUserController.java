package com.qiusm.eju.crawler.competitor;

import com.qiusm.eju.crawler.competitor.beike.service.IBeikeLoginService;
import com.qiusm.eju.crawler.competitor.beike.service.IBkRedisService;
import com.qiusm.eju.crawler.parser.competitor.beike.dto.BkUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author qiushengming
 */
@Api(tags = {"BeikeUser", "bk用户管理列表"})
@Slf4j
@Controller
@RequestMapping("/bk/user")
public class BeikeUserController {

    @Resource
    private IBkRedisService bkRedisService;

    @Resource
    private IBeikeLoginService beikeLoginService;

    @GetMapping("/{startIndex}")
    public ModelAndView userList(@PathVariable Integer startIndex) {
        ModelAndView model = new ModelAndView("bk/userList");
        model.addObject("users", bkRedisService.getUserList(startIndex));
        return model;
    }

    @GetMapping("picVerifyCode")
    public ModelAndView getPicVerifyCodeByPhone(String phoneNo) {
        byte[] picByte = beikeLoginService.getPicVerifyCodeByPhone(phoneNo, "310000");
        ModelAndView model = new ModelAndView("bk/userList");
        // model.addObject("users", bkRedisService.getUserList(startIndex));
        log.info("login");
        return model;
    }

    @GetMapping("/loginByPasswordV2")
    @ResponseBody
    public BkUser loginByPasswordV2(
            String phoneNo, String password, String picVerifyCode,
            @RequestParam(required = false, defaultValue = "310000") String cityId) {
        return beikeLoginService.loginByPasswordV2(phoneNo, password, picVerifyCode, cityId);
    }
}
