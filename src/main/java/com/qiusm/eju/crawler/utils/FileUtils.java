package com.qiusm.eju.crawler.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * 文件操作
 *
 * @author qiushengming
 */
@Slf4j
public class FileUtils {

    public static void printFile(String fileContent, String filePath, String fileName, boolean append) {
        OutputStream os = null;
        try {
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(filePath + File.separator + fileName);
            os = new FileOutputStream(file, append);
            byte[] data = fileContent.getBytes();
            os.write(data, 0, data.length);
            os.flush();
            log.info("文件写入" + filePath + File.separator + fileName + "结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void printFile(byte[] data, String filePath, String fileName, boolean append) {
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //1、建立连接
        File file = new File(filePath + File.separator + fileName);
        OutputStream os = null;
        try {
            //2、选择输出流,以追加形式(在原有内容上追加) 写出文件 必须为true 否则为覆盖
            os = new FileOutputStream(file, append);
            //3、写入文件
            os.write(data, 0, data.length);
            //将存储在管道中的数据强制刷新出去
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件没有找到！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入文件失败！");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("关闭输出流失败！");
                }
            }
        }
    }

    public static String readFile(File file) {
        try {
            return org.apache.commons.io.FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            log.error(exception.toString());
        }
        return null;
    }
}
