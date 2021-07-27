/*
package com.qiusm.eju.crawler.parser.competitor.anjuke;


import com.qiusm.eju.crawler.controller.base.parser.ParserBaseAbstract;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

*/
/**
 * @author 赵乐
 * @date 2019/1/3 10:11
 *//*

@Slf4j
public abstract class AnjukeParserBase extends ParserBaseAbstract {

    @Override
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
}
*/
