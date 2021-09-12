package com.qiusm.eju.crawler.utils.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BinaryFileUtils {

    /**
     * 将文件转换为二进制流的形式</br>
     * 2017年7月3日</br>
     * @author qiushengming
     * @param in 文件流
     * @return byte字节码
     * @throws IOException byte[]
     */
    public static byte[] fileToBinaryFile(InputStream in) 
            throws IOException{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        
        int ch;
        
        /*返回值byte字节数组*/
        byte[] data;
        
        while((ch = in.read()) != -1){
            byteOut.write(ch);
        }
        
        /*转换为byte*/
        data = byteOut.toByteArray();
        
        /*关闭流*/
        byteOut.close();
        
        return data;
    }
    
    /**
     * 二进制文件转换为文件默认存在D盘</br>
     * 2017年7月3日</br>
     * @author qiushengming
     * @param fileName 文件名称
     * @param binaryFile 二进制值数组
     * @throws IOException void
     */
    public static void binaryFileToFile(String fileName, byte[] binaryFile)
            throws IOException{
        File file = new File("D:\\" + fileName);
        
        if(!file.exists()){
            file.createNewFile();
        }
        
        FileOutputStream fileOut = new FileOutputStream(file);
        
        if(binaryFile.length > 0){
            fileOut.write(binaryFile, 0, binaryFile.length);
        }
        
        fileOut.close();
    }
}
