package com.qiusm.eju.crawler.utils.lang;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


/**
 * 文件操作
 *
 * @author qiushengming
 */
@Slf4j
public class FileUtils {

    private final static String encoding =
            System.getProperties().getProperty("file.encoding");

    public static void printFile(String fileContent, String filePath, String fileName, boolean append) {
        printFile((fileContent + "\n").getBytes(StandardCharsets.UTF_8), filePath, fileName, append);
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

    public static String readFile(String fileName) {
        return readFile(new File(fileName));
    }

    public static String readFile(File file) {
        try {
            return org.apache.commons.io.FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            log.error(exception.toString());
        }
        return null;
    }

    private static int deleteFileNumber = 0; // 文件删除的数量，包含目录文件

    /**
     * <p>获取当前路径下的所有文件，不包含文件夹（目录）；2017年3月19日 下午5:34:30</p>
     *
     * @return 文件队列
     * @author qiushengming
     */
    public static List<File> getFile(String path) {
        File file = new File(path);
        return getFile(file);
    }

    public static List<File> getFile(File file) {
        List<File> enableFile = new ArrayList<>();
        /* 判断文件是否存在 */
        if (file.exists()) {
            /* 判断是否是文件 */
            if (file.isFile()) {
                enableFile.add(file);
            } else if (file.isDirectory()) {
                File[] listFile = file.listFiles();
                if (listFile != null) {
                    /* 否则如果它是一个目录*/
                    for (File f : listFile) {
                        enableFile.addAll(getFile(f));
                    }
                    log.info(file.getName() + ">>" + listFile.length);
                }
            }
        }
        return enableFile;
    }

    /**
     * 删除文件的对外接口；删除当前路劲下的所有文件，包含文件夹及其目录；
     * 2017年6月14日
     * qiushengming
     *
     * @param currentPath 删除文件的路径
     * @return 无
     */
    public static void deleteFile(String currentPath) {
        File file = new File(currentPath);
        deleteFile(file);
    }

    /**
     * 递归删除文件
     * 2017年6月14日
     * qiushengming
     *
     * @param file 要删除的文件对象
     * @return 无
     */
    private static void deleteFile(File file) {
        /* 判断文件是否存在 */
        if (file.exists()) {
            /* 判断是否是文件 */
            if (file.isFile()) {
                /* 删除文件 */
                file.delete();
                deleteFileNumber++;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                /* 声明目录下所有的文件 files[] */
                File[] files = file.listFiles();
                if (files != null) {
                    /* 遍历目录下所有的文件 */
                    for (File file1 : files) {
                        /* 把每个文件用这个方法进行迭代 */
                        deleteFile(file1);
                    }
                }
                /* 删除文件夹 */
                file.delete();
                deleteFileNumber++;
            }
        } else {
            log.info(file.getName() + "文件夹删除完毕！！");
        }
    }

    /**
     * 创建目录</br>
     * 2017年6月15日
     *
     * @param path 路径
     * @return Boolean true重新创建，false已存在
     * @author qiushengming
     */
    public static boolean createDirectory(String path) {
        File file = new File(path);
        boolean mkdir = false;
        /*如果文件夹不存在则创建*/
        if (!file.exists() && !file.isDirectory()) {
            mkdir = file.mkdir();
            log.info(path + "创建成功！！！");
        } else {
            log.info(path + "已存在！！！");
        }
        return mkdir;
    }

    public static void createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            boolean b = file.createNewFile();
            if (b) {
                log.info(path + "创建成功！！！");
            }
        }
    }

    /**
     * 判断path是否存在
     * 2017年6月20日</br>
     *
     * @param filePath 文件路径
     * @return Boolean 文件存在ture，不存在false
     * @author qiushengming
     */
    public static Boolean isEnable(String filePath) {
        File f = new File(filePath);

        return !(!f.exists() && !f.isDirectory());
    }

    /**
     * copy文件</br>
     * 2017年7月4日</br>
     *
     * @param oldPath copy的源文件
     * @param newPath 目标路劲
     * @return 无
     * @author qiushengming
     */
    public static void copy(String oldPath, String newPath) {
        File oldfile = new File(oldPath);
        copy(oldfile, newPath);
    }

    /**
     * copy文件</br>
     * 2017年7月4日，拷贝临床路径特殊化需求，需要拿上一级目录的年份</br>
     *
     * @param oldfile copy的源文件
     * @param newPath 目标路径
     * @author qiushengming
     * @reutnr 无
     */
    public static void copy(File oldfile, String newPath) {
        try {
            int byteread;
            String fileName = oldfile.getName();
            /*删除文件名称开头非中文字符*/
            fileName = fileName.substring(StringUtils.findHanZiFirstIndexOf(fileName));

            /*特殊化拼接拷贝路径+名称*/
            newPath += fileName.replace(".do", getSpecial(oldfile));

            if (oldfile.exists()) {
                InputStream in = new FileInputStream(oldfile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = in.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                fs.close();
                in.close();
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    private static String getSpecial(File f) {
        File parentFile = f.getParentFile();

        if (parentFile == null) {
            log.warn("未找到文件名称包含#的文件！！！");
            return "#";
        }

        if (!parentFile.getName().contains("#")) {
            return getSpecial(parentFile);
        } else {
            return "_" + parentFile.getName().substring(1, 5) + "_"
                    + System.currentTimeMillis() + ".do";
        }
    }

    /**
     * 按行遍历文件
     *
     * @param filePath example source/er_fang_list.txt
     * @param consumer 对行的处理方式
     */
    public static void traverseLine(String filePath, Consumer<String> consumer) {
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(filePath));
            String str;
            while ((str = lnr.readLine()) != null) {
                consumer.accept(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
