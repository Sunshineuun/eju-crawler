package com.qiusm.eju.crawler.service.bk;

import com.qiusm.eju.crawler.service.base.IProxyUrlService;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;

/**
 * beike base service
 *
 * @author qiushengming
 */
public abstract class BeikeBaseService implements InitializingBean {

    @Resource
    private IProxyUrlService proxyUrlService;
    protected static final String BEIKE_FILE_ROOT = "source/beike/";

    protected OkHttpUtils httpUtils;
    protected final List<String> ERROR_MSG = new ArrayList<>();

    {
        ERROR_MSG.addAll(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=,枌~怣秙".split(COMMA)));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        httpUtils = OkHttpUtils.Builder()
                .proxyUrl(proxyUrlService.getHttpBaseProxyUrl())
                .connectTimeout(60000)
                .readTimeout(60000)
                .charset(GBK)
                .builderHttp();
    }
}
