package com.qiusm.eju.crawler.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author qiushengming
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 判断当前字符是不是汉字
     *
     * @param c 字符
     * @return 是&否
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;

    }

    public static String encode(String url, String enc) {
        StringBuilder sb = new StringBuilder();
        char[] urlChar = url.toCharArray();
        for (char c : urlChar) {
            try {
                if (isChinese(c)) {
                    sb.append(URLEncoder.encode(String.valueOf(c), enc));
                } else {
                    sb.append(c);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
