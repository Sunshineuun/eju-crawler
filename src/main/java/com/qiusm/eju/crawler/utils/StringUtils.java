package com.qiusm.eju.crawler.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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

    /**
     * 异常堆栈信息转String
     *
     * @param e 异常
     * @return String
     */
    public static String stackTraceInfoToStr(Exception e) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append(e.toString())
                .append("      ")
                .append(e.getMessage());
        // 限制长度20；将其转换为String；并通过 \n 连接起来
        String stackTraceInfo = Arrays.stream(e.getStackTrace())
                .limit(20)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
        errorMsg.append(stackTraceInfo);
        return errorMsg.toString();
    }

    public static String gunzip(String str) {
        if (str == null) {
            return null;
        }

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ByteArrayInputStream byteIn = null;
        GZIPInputStream gzipIn = null;
        byte[] compressed = null;
        String deCompressed = null;

        try {
            compressed = new BASE64Decoder().decodeBuffer(str);
            byteIn = new ByteArrayInputStream(compressed);
            gzipIn = new GZIPInputStream(byteIn);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = gzipIn.read(buffer)) != -1) {
                byteOut.write(buffer, 0, offset);
            }
            deCompressed = byteOut.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteOut.close();
                if (byteIn != null) {
                    byteIn.close();
                }
                if (gzipIn != null) {
                    gzipIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return deCompressed;
    }

    public static String gzip(String str) {
        if (isBlank(str)) {
            return str;
        }

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = null;
        try {
            gzipOut = new GZIPOutputStream(byteOut);
            gzipOut.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzipOut != null) {
                try {
                    gzipOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new BASE64Encoder().encode(byteOut.toByteArray());
    }
}
