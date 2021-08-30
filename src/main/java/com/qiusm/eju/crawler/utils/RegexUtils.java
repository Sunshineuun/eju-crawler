package com.qiusm.eju.crawler.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author NJ
 * @create 2018-08-01 10:38
 **/
public class RegexUtils {

    private static final String REGEX_NUM = "[0-9]*";
    private static final String REGEX_D = ".*\\d+.*";
    private static final String REGEX_FH = "(?<=\\()[^\\)]+";
    private static final String REGEX = "(([0-9]+\\.[0-9]+)|([0-9]+))(:|：)(([0-9]+\\.[0-9]+)|([0-9]+))";
    private static final String REGEX_YEAR = "(?!0000)[0-9]{4}(年)";

    /**
     * 从 String 字符串里提取数字
     *
     * @param str
     * @return
     */
    public static String extractNumByString(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 从 String 字符串里提取数字
     *
     * @param str
     * @return
     */
    public static String extractNumByList(String str) {
        List<String> result = new ArrayList();
        String regEx = "\\s*(\\d[\\\\.\\d]*\\d)";  //数字 包括带点的
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        if (CollectionUtils.isNotEmpty(result)) {
            String strs = StringUtils.join(result, ";");
            return strs;
        }
        return null;
    }

    /**
     * 校验 当前数字是否是小区名
     *
     * @param str
     * @return
     */
    public static boolean checkIsCommunityName(String str, String text) {
        Pattern p = Pattern.compile(str + ".*(弄|号).*");
        Matcher m = p.matcher(text);
        boolean isExist = m.find();
        return isExist;
    }

    /**
     * 判断字符串 是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile(REGEX_NUM);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断一个字符串是否含有数字
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(REGEX_D);
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 使用正则表达式提取小括号中的内容
     *
     * @param msg
     * @return
     */
    public static String extractMessageByRegular(String msg) {

        Pattern p = Pattern.compile(REGEX_FH);
        Matcher m = p.matcher(msg);
        while (m.find()) {
            return m.group();
        }
        return null;
    }

    /**
     * 匹配
     *
     * @return
     */
    public static String regColon(String text) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher isNum = pattern.matcher(text);
        while (isNum.find()) {
            return isNum.group();
        }
        return null;
    }

    /**
     * 正则 截取字符串里面的 2018年 这种格式
     *
     * @param text
     * @return
     */
    public static String regYear(String text) {
        Pattern pattern = Pattern.compile(REGEX_YEAR);
        /*((?!0000)[0-9]{4})*/
        Matcher m = pattern.matcher(text);
        String year = null;
        while (m.find()) {
            /*获取的字符串的首位置和末位置*/
            year = m.group().substring(m.start(), m.end());
        }
        return year;
    }

    /**
     * 去掉字符串 中英文标点符号 和空格
     *
     * @param text
     * @return
     */
    public static String removeBlank(String text) {
        if(StringUtils.isNotBlank(text)){
            text = text.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
            if(StringUtils.isNotBlank(text)){
                text = text.trim();
            }
        }
        return text;
    }

    /**
     * 去除转义字符
     */
    public static String replaceAll(String source, String regex, String replacement) {
        if (replacement == null) {
            replacement = "";
        }
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(source);
        if (matcher.find()) {
            return matcher.replaceAll(replacement);
        }
        return source;
    }

    public static void main(String[] args) {
        String str = "小区总户约为336户，地上车位数目有53个，地下车位23个，车位配比约1:0.22，停车位配比较低，因而小区停车位较为紧张；<br/>小区未采取人车分流，居民行车或产生部分噪音和扫光干扰，行人和儿童玩乐时的安全也无法得到保障；<br/>小区地上车位无\n" +
                "行人和儿童玩乐时的安全也无法得到保障；<br/>小区地上车位无";
    }

}
