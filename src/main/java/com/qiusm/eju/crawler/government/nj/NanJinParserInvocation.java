package com.qiusm.eju.crawler.government.nj;

import com.qiusm.eju.crawler.government.base.utils.CommonUtils;
import com.qiusm.eju.crawler.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static com.qiusm.eju.crawler.constant.government.NanJinConstant.ERROR_HTML_PATH;
import static com.qiusm.eju.crawler.constant.government.NanJinConstant.FORMAT_FOURTEEN;

/**
 * @author qiushengming
 */
@Slf4j
public class NanJinParserInvocation implements InvocationHandler {
    Object target;

    public NanJinParserInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!StringUtils.endsWith(method.getName(), "Parser")) {
            return method.invoke(target, args);
        }

        if (!verifyHtmlStr((String) args[1])) {
            return null;
        }
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            printException((String) args[0], (String) args[1], e);
        }
        return null;
    }

    private boolean verifyHtmlStr(String htmlStr) {
        return StringUtils.isNotBlank(htmlStr);
    }

    /**
     * 打印日志；将错误信息输出的文件中，后期考虑输出的数据库中，但是介于考虑HTML文件比较大，再研究<br>
     *
     * @param requestUrl 请求的URL
     * @param htmlStr    响应的HTML结果
     * @param ex         解析期间抛出的异常信息
     */
    private void printException(String requestUrl, String htmlStr, Exception ex) {
        String fileName = String.format("%s.html", FORMAT_FOURTEEN.format(LocalDateTime.now()));
        String fileContent = CommonUtils.getStackTrace(ex) + "\n\n" + htmlStr;
        log.error("requestUrl:{},fileName:{}\nerror:{}", requestUrl, fileName, ex.getMessage());
        FileUtils.printFile(fileContent, ERROR_HTML_PATH, fileName, true);
    }
}
