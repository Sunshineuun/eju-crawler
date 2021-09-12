
package com.qiusm.eju.crawler.utils.lang;

/**
 * @author Luun
 * @version 1.0
 * @emile 583853240@qq.com
 * @date 2016-8-25下午5:27:10
 */
public class UnicodeUtils {

    /**
     * <p>unicode编码转中文，单个字符[111]</p>
     * 2017年4月30日 <br>
     *
     * @param unicode unicode
     * @return String
     */
    public static String unicodeToString(Integer unicode) {
        StringBuilder sb = new StringBuilder();

        int data = Integer.parseInt(unicode.toString(), 16);
        sb.append((char) data);
        //            sb.append(unicode.substring(4));
        return sb.toString();
    }

    /**
     * <p>unicode编码转中文，格式[\u1111]</p>
     * 2017年4月30日 <br>
     *
     * @param unicode unicode
     * @return String
     */
    public static String unicodeToString(String unicode) {
        StringBuilder sb = new StringBuilder();

        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i].substring(0, 4), 16);
            sb.append((char) data);
            sb.append(hex[i].substring(4));
        }
        return sb.toString();
    }

    /**
     * 将文本转成unicode
     *
     * @param str 任意文本
     * @return unicode文本
     */
    public static String stringToUnicode(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); // 取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); // 取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }
}
