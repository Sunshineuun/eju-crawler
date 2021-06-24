package com.qiusm.eju.crawler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * 文件操作
 *
 * @author qiushengming
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

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
            LOGGER.info("文件写入" + filePath + File.separator + fileName + "结束");
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
        File file = new File(filePath + File.separator + fileName); //1、建立连接
        OutputStream os = null;
        try {
            //2、选择输出流,以追加形式(在原有内容上追加) 写出文件 必须为true 否则为覆盖
            os = new FileOutputStream(file, append);
            os.write(data, 0, data.length);    //3、写入文件
            os.flush();    //将存储在管道中的数据强制刷新出去
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
}
