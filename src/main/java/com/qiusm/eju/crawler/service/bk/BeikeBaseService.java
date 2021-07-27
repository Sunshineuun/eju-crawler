package com.qiusm.eju.crawler.service.bk;

import com.qiusm.eju.crawler.utils.http.OkHttpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL0;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;

/**
 * beike base service
 *
 * @author qiushengming
 */
public abstract class BeikeBaseService {
    protected static final String BEIKE_FILE_ROOT = "source\\beike\\";

    protected OkHttpUtils httpUtils = OkHttpUtils.Builder().proxyUrl(PROXY_URL0).connectTimeout(60000).readTimeout(60000).charset(GBK).builderHttp();
    protected final List<String> ERROR_MSG = new ArrayList<>();

    {
        ERROR_MSG.addAll(Arrays.asList("ejuResponseCode=500,ResponseCode=,ResponseError=,枌~怣秙".split(COMMA)));
    }
}
