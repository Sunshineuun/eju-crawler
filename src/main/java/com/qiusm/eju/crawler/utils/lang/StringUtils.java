package com.qiusm.eju.crawler.utils.lang;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author qiushengming
 */
@Slf4j
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

    /**
     * 暂不处理多音字，只返回结果的第一个拼音码。
     *
     * @param args 汉字
     * @return 返回拼音码
     */
    public static String[] hanYuToPinYin(String args) {
        //初始化汉语拼音输出格式
        HanyuPinyinOutputFormat py = new HanyuPinyinOutputFormat();
        py.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        py.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        py.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] src1 = args.toCharArray();
        String[] result = new String[src1.length];
        String regex = "[\\u4E00-\\u9FA5]";
        try {
            for (int i = 0; i < src1.length; i++) {
                if (Character.toString(src1[i]).matches(regex)) {
                    String[] temp =
                            PinyinHelper.toHanyuPinyinStringArray(src1[i], py);
                    result[i] = temp[0];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return ArrayUtils.removeNull(result);
    }

    /**
     * 获取汉语拼音首字母
     *
     * @param args 被处理
     * @return 拼音码
     */
    public static String getHanYuPinYinFist(String args)
            throws UnsupportedEncodingException {
        String[] pinyin = hanYuToPinYin(args);
        StringBuilder sb = new StringBuilder();
        for (String s : pinyin) {
            sb.append(s.charAt(0));
        }
        return sb.toString();
    }

    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     * @author qiushengming
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 查找第一次出现汉字的位置,包含汉字和中文符号</br>
     * 逻辑：通过正则去分割(split)字符串，取得第0个字符数据的长度</br>
     * 2017年7月5日</br>
     *
     * @param str 被操作的字符串
     * @return int 成功返回索引位置；失败返回-1；
     * @author qiushengming
     */
    public static int findHanZiFirstIndexOf(String str) {
        String reg = "[\u4e00-\u9fa5]";
        String regex = "[。，！？（）《》……、：——【】；’”‘“]";
        int index = -1;

        if (str.matches(".*" + reg + ".*")) {
            /*汉字*/
            index = str.split(reg)[0].length();
        } else if (str.matches(".*" + regex + ".*")) {
            /*中文符号*/
            index = str.split(regex)[0].length();
        }
        return index;
    }

    /**
     * 按照len长度分割s
     *
     * @param s   被分割的字符
     * @param len 长度
     * @return 分割后的数据
     */
    public static String[] stringSpilt(String s, int len) {
        if (isEmpty(s)) {
            return null;
        }


        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int start = i * len;
            int end = (i + 1) * len;
            try {
                sb.append(s.substring(start, end)).append("#");
            } catch (StringIndexOutOfBoundsException e) {
                log.error(
                        "start >> " + start + "; end >> " + end + "; string >> "
                                + s);
            }
            if (end >= s.length()) {
                break;
            }
            i++;
        }
        return sb.toString().split("#");
    }

    /**
     * 将clob对象转换为string类型；clob == null，返回""；
     *
     * @param clob java.sql.Clob
     * @return clob == null ? return "";
     */
    public static String clobToString(Clob clob) {
        if (clob == null) {
            return "";
        }

        BufferedReader br; // 字符流
        StringBuilder sb = new StringBuilder(); // 转换的存储

        try {
            br = new BufferedReader(clob.getCharacterStream());
            String s = br.readLine();
            // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            while (s != null) {
                sb.append(s);
                s = br.readLine();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            log.error("{}", e);
        }

        return sb.toString();
    }
}
