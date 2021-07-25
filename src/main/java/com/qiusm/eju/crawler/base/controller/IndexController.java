package com.qiusm.eju.crawler.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author qiushengming
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("key", "welcome");
        mv.setViewName("index");
        return mv;
    }
}
