package com.qiusm.eju.crawler.parser.competitor.anjuke.pc;


import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;


@Slf4j
public abstract class AnjukeParserBase {

    public Predicate<String> viewTry() {
        return (html) -> StringUtils.isBlank(html)
                || html.contains("<title>请输入验证码")
                || html.contains("访问验证-安居客")
                || html.contains("系统检测到您正在使用网页抓取工具访问安居客网站，请卸载删除后访问")
                || html.contains("对不起，您要浏览的网页可能被删除，重命名或者暂时不可用")
                || html.contains("Bad Gateway:")
                || html.contains("<title>登录</title>")
                || html.contains("<title>验证码</title>");
    }

    public abstract String getCode();

    public abstract String getParentCode();

    public abstract JSONObject parser(String htmlStr, OkHttpUtils okHttpUtils);
}
