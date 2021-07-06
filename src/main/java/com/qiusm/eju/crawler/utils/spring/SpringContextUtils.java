package com.qiusm.eju.crawler.utils.spring;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import reactor.util.annotation.NonNullApi;

import javax.annotation.Nullable;
import java.util.Locale;

/**
 * @author qiushengming
 */
public class SpringContextUtils implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext = null;

    private static final Logger log = LoggerFactory.getLogger(SpringContextUtils.class);

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 获取实例
     *
     * @param name Bean名称
     * @param type Bean类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.notNull(name, "beanName is required");
        Assert.notNull(type, "beanType is required");
        return applicationContext.getBean(name, type);
    }

    /**
     * 获取国际化消息
     *
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
    public static String getMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return applicationContext.getMessage(code, args, "应答信息没有找到[" + code + "]", locale);
    }

    /**
     * 获取国际化消息
     *
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
    public static String getMessageNoCode(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return applicationContext.getMessage(code, args, null, locale);
    }

    /**
     * 清除SpringContextUtils中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        log.debug("清除SpringContextUtils中的ApplicationContext:{}", applicationContext);
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) {
        log.debug("注入ApplicationContext到SpringContextUtils:{}", applicationContext);

        if (SpringContextUtils.applicationContext != null) {
            log.warn("SpringContextUtils中的ApplicationContext被覆盖, 原有ApplicationContext为:"
                    + SpringContextUtils.applicationContext);
        }

        //NOSONAR
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() {
        SpringContextUtils.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null,
                "applicationContext属性未注入, 请在applicationContext.xml中定义SpringContextUtils.");
    }

}
