package com.qiusm.eju.crawler.utils.lang;

public class ArrayUtils {
    public static String[] removeNull(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            if (StringUtils.isNotEmpty(s)) {
                sb.append(s).append("#");
            }
        }

        return sb.toString().split("#");
    }
}
