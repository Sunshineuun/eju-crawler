package com.qiusm.eju.crawler.utils;


import com.qiusm.eju.crawler.enums.ResponseStatusCode;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qiushengming
 */
public class ImageReaderUtils {

    private static final int TIMEOUT_6S = 6000;

    public static Map<String, String> getImageInfoByUrl(String urlStr) {
        Map<String, String> imageInfo = new HashMap<>(8);
        try {
            URL url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            int length = urlConnection.getContentLength();
            imageInfo.put("size", length + "");
            if (length <= 0) {
                System.out.println("image not exist");
            } else {
                try (ImageInputStream in = ImageIO.createImageInputStream(urlConnection.getInputStream())) {
                    final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
                    if (readers.hasNext()) {
                        ImageReader reader = readers.next();
                        try {
                            reader.setInput(in);
                            imageInfo.put("width", reader.getWidth(0) + "");
                            imageInfo.put("height", reader.getHeight(0) + "");
                        } finally {
                            reader.dispose();
                        }
                    }
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("url not right " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
        return imageInfo;
    }

    public static Map<String, String> getImageInfoByImageReader(String filePath) {
        Map<String, String> imageInfo = new HashMap<>();
        ImageInputStream in = null;
        try {
            File file = new File(filePath);
            in = ImageIO.createImageInputStream(file);
            imageInfo.put("size", in.length() + "");
            if (in.length() <= 0) {
                System.out.println("image not exist");
            } else {
                Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
                ImageReader reader = readers.next();
                reader.setInput(in);
                imageInfo.put("width", reader.getWidth(0) + "");
                imageInfo.put("height", reader.getHeight(0) + "");
            }
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageInfo;
    }

    /**
     * 通过图片的url获取图片的base64字符串
     *
     * @param path 图片url
     * @return 返回图片base64的字符串
     */
    public static byte[] imageToByte(String path) {

        try {
            return imageToByteV2(path, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] imageToByteV2(String path) {
        return imageToByteV2(path, null);
    }

    /**
     * 通过图片的url获取图片的base64字符串
     *
     * @param path 图片url
     * @return 返回图片base64的字符串
     */
    public static byte[] imageToByteV2(String path, Map<String, String> headers) {
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        Response response = null;
        try {
            HttpHost proxy = new HttpHost("transfer.mogumiao.com", 9001);
            if (headers == null) {
                headers = new HashMap<>(8);
            }
            headers.put("Authorization", "Basic QzNjSEpJMVpXOUVxOGpRQTpGZzZvQmczd05tTjJwc1JO");

            Request.Builder requestBuilder = new Request.Builder();
            headers.forEach((key, value) -> {
                if (!"responseHeaders" .equals(key)) {
                    requestBuilder.addHeader(key, value);
                }
            });

            Call call = OkHttpUtils.SingleOkHttpConfig.OK_HTT.newBuilder()
                    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.getHostName(), proxy.getPort())))
                    .connectTimeout(6000L, TimeUnit.MILLISECONDS).readTimeout(6000L, TimeUnit.MILLISECONDS)
                    .writeTimeout(6000L, TimeUnit.MILLISECONDS)
                    .build()
                    .newCall(requestBuilder.url(path).build());
            response = call.execute();
            if (response.code() != ResponseStatusCode.R200.getCode()) {
                throw new BusinessException(response.code(), "http code exception " + response.code());
            }
            assert response.body() != null;
            is = response.body().byteStream();
            outStream = new ByteArrayOutputStream();

            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = is.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            outStream.flush();
            return outStream.toByteArray();

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String imgUrlUpdate(String regex, String replaceStr, String url) {
        String reStr = null;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if (m.find()) {
            reStr = m.group();
        }
        if (StringUtils.isNotBlank(reStr)) {
            return url.replace(reStr, replaceStr);
        } else {
            return url;
        }
    }

}
