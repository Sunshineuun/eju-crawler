package com.qiusm.eju.crawler.government.wh;

import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.ImageReaderUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qiushengming
 */
public class WuHanTest {
    private static WuHanProperties properties = new WuHanProperties();

    public static void main(String[] args) {
        loadImg();
    }

    public static void loadImg() {
        String url = "http://119.97.201.22:8080/GetHouseInfo.ashx?price=KL7ThIHERYEk5qtim6%2Bmwg==";
        String fileName = "KL7ThIHERYEk5qtim6%2Bmwg==.png";
        byte[] data = ImageReaderUtils.imageToByte(url);
        String filePath = properties.getPicturePath() + "test/";
        FileUtils.printFile(data, filePath, fileName, false);
        System.out.println(1);
    }
}
