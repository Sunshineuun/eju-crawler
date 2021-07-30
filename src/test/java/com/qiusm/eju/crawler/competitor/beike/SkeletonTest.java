package com.qiusm.eju.crawler.competitor.beike;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class SkeletonTest {
    public static void main(String[] args) {
//        count();
        String sepa = System.getProperty("file.separator");
        System.out.println(sepa);
    }

    static void count() {
        String filePath = "source/beike/skeleton/2021.07.28";
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
