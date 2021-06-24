package com.qiusm.eju.crawler.exception;

/**
 * 业务异常
 *
 * @author qiushengming
 */
public class BusinessException extends RuntimeException {

    /**
     * 业务信息编码
     */
    private String businessCode;

    /**
     * 业务信息参数
     */
    private final Object[] args;

    /**
     * 业务异常级别
     */
    private Level level = Level.WARN;

    /**
     * @param errorCode
     */
    public BusinessException(Integer errorCode, String msg) {
        super(msg);
        this.level = level;
        this.businessCode = String.valueOf(errorCode);
        this.args = null;
    }

    public BusinessException(String errorCode, Object... theArgs) {
        this(Level.WARN, errorCode, theArgs);
    }

    public BusinessException(Level level, String errorCode, Object... theArgs) {
        super();
        this.level = level;
        this.businessCode = errorCode;
        this.args = theArgs;
    }

    public BusinessException(String message, String businessCode, Object[] args) {
        super(message);
        this.businessCode = businessCode;
        this.args = args;
    }

    /**
     * 获取“args”(类型：Object[])
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * 获取“args”(类型：Object[])
     */
    public String[] getArgStrs() {
        if (args == null) {
            return null;
        }
        String[] argStrs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            argStrs[i] = args[i] != null ? args[i].toString() : null;
        }
        return argStrs;
    }


    public String[] getArgs4StringArr() {
        if (args == null) {
            return null;
        }

        String[] stringArr = new String[args.length];
        for (int i = 0; i < stringArr.length; i++) {
            stringArr[i] = String.valueOf(args[i]);
        }

        return stringArr;
    }

    @Override
    public String toString() {
        StringBuffer argsBuf = new StringBuffer();
        for (int i = 0; args != null && i < args.length; i++) {
            argsBuf.append(args[i]);
            if (i + 1 < args.length) {
                argsBuf.append(",");
            }
        }

        return "{" + getClass().getName() + "@" + hashCode() + "[" + businessCode + "(" + argsBuf + ")]:" + getMessage() + "}";
    }

    /**
     * @return the busiInfoCode
     */
    public String getBusinessCode() {
        return businessCode;
    }

    /**
     * @param busiInfoCode the busiInfoCode to set
     */
    public void setBusinessCode(String busiInfoCode) {
        this.businessCode = busiInfoCode;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object obj) {
        //判断空对象
        if (obj == null) {
            return false;
        }

        //判断类型不一致的情况
        if (!(obj.getClass() == this.getClass())) {
            return false;
        }

        //判断业务码不相同情况
        BusinessException otherBe = (BusinessException) obj;
        if (!otherBe.businessCode.equals(businessCode)) {
            return false;
        }

        // 判断业务异常级别
        if (!otherBe.getLevel().equals(this.level)) {
            return false;
        }

        //判断参数数量不同的情况
        if (otherBe.args.length != args.length) {
            return false;
        }

        //判断参数内容不相同情况
        for (int i = 0; i < args.length; i++) {
            if (!args[i].equals(otherBe.args[i])) {
                return false;
            }
        }

        return true;
    }

    public enum Level {
        /**
         * 异常级别定义
         */
        INFO, WARN, ERROR, FATAL
    }

    @Deprecated
    public String getMsg() {
        return getMessage();
    }
}
