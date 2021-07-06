package com.qiusm.eju.crawler.exception;

import java.io.IOException;

/**
 * @author qiushengming
 */
public class SystemException extends RuntimeException {
    public SystemException() {
        super();
    }

    public SystemException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public SystemException(String arg0) {
        super(arg0);
    }

    public SystemException(Throwable arg0) {
        super(arg0);
    }
}
