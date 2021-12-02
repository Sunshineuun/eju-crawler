package com.qiusm.eju.crawler.utils;

import java.io.UnsupportedEncodingException;

public class UrlUtil {
    public static void main(String[] args) {
        String u = "%7B%0A%20%20%22duid%22%20%3A%20%22D2U0G4IbEYx4X7VXfbjtygP9oU6AFRpk%5C%2FcXn79RBiTsHYX59%22%2C%0A%20%20%22appVersion%22%20%3A%20%222.65.1%22%0A%7D";
        System.out.println(getURLDecoderString(u));
        // %C1%F5%C3%CE%D5%E4 153154
        String u1 = "刘梦珍";
        System.out.println(getURLEncoderString(u1));

    }

    private final static String ENCODE = "GBK";

    /**
     * URL 解码
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL 转码
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
