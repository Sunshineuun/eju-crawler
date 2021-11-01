package com.qiusm.eju.crawler.utils;

import java.security.MessageDigest;

/**
 * MD5工具类
 */
public class MD5Util {

    public static final String KEY_MD5 = "MD5";

    public static String encrypt(String msg, String key) {
        return doMd5(msg + key, "UTF-8");
    }

    public static boolean verify(String msg, String key, String sign) {
        String digest = encrypt(msg, key);
        return sign.equals(digest);
    }

    public static String calcSign(String msg) {
        return doMd5(msg, "UTF-8");
    }

    public static boolean verifySign(String msg, String sign) {
        String digest = calcSign(msg);
        return sign.equals(digest);
    }

    public static String doMd5(String str, String charset) {
        MessageDigest messageDigest = null;
        String strDes = null;

        try {
            byte[] e = str.getBytes(charset);
            messageDigest = MessageDigest.getInstance(KEY_MD5);
            messageDigest.update(e);
            strDes = bytes2Hex(messageDigest.digest());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}