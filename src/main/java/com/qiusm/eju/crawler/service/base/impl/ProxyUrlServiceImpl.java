package com.qiusm.eju.crawler.service.base.impl;

import com.qiusm.eju.crawler.service.base.IProxyUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProxyUrlServiceImpl
        implements IProxyUrlService {

    @Value("${eju.proxy.httpbase}")
    private String httpBaseProxyUrl;

    @Value("${eju.proxy.bklogin}")
    private String bkLoginProxyUrl;

    @Override
    public String getHttpBaseProxyUrl() {
        return httpBaseProxyUrl;
    }

    public void setHttpBaseProxyUrl(String httpBaseProxyUrl) {
        this.httpBaseProxyUrl = httpBaseProxyUrl;
    }

    @Override
    public String getBkLoginProxyUrl() {
        return bkLoginProxyUrl;
    }

    public void setBkLoginProxyUrl(String bkLoginProxyUrl) {
        this.bkLoginProxyUrl = bkLoginProxyUrl;
    }
}
