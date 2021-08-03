package com.qiusm.eju.crawler.competitor.beike;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class SkeletonTest {
    public static void main(String[] args) {
        count();
    }

    static void count() {
        String filePath = "/Users/qiushengming/Documents/2021.08.02091310/";
        File file = new File(filePath);
        File[] files = file.listFiles();
        int count = 0;
        for (File file1 : files) {
            try {
                LineNumberReader lnr = new LineNumberReader(new FileReader(file1));
                lnr.skip(Long.MAX_VALUE);
                count += lnr.getLineNumber() + 1;
                lnr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count);
    }
}
