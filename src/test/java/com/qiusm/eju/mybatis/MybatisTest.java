package com.qiusm.eju.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.EjuCrawlerApplication;
import com.qiusm.eju.crawler.entity.demo.EsfCommunitySurroundingInfo;
import com.qiusm.eju.crawler.entity.demo.EsfPictureInfoMapIdentify2;
import com.qiusm.eju.crawler.entity.demo.GxdAssetData20211216;
import com.qiusm.eju.crawler.mapper.demo.OperDemoMapper;
import com.qiusm.eju.crawler.utils.SnowflakeUtil;
import com.qiusm.eju.crawler.utils.lang.DateUtils;
import com.qiusm.eju.crawler.utils.lang.FileUtils;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(classes = EjuCrawlerApplication.class)
public class MybatisTest {

    @Resource
    OperDemoMapper operDemoMapper;

    @Test
    void streamQuery() {
        long s = System.currentTimeMillis();
        String sql = "select id,outer_sid outersid,outer_id outerid,type,is_valid isvalid,support_type supporttype,point_code pointcode,support_name supportname,address,longitude,latitude,linear_distance lineardistance,walking_distance walkingdistance,car_distance cardistance,prov_id provid,city_id cityid,is_not_push isnotpush,create_time createtime,update_time updatetime from esf_community_surrounding_info";
        operDemoMapper.streamQuery(sql, resultContext -> {
            EsfCommunitySurroundingInfo orgData = resultContext.getResultObject();
            System.out.printf("%s,%s\n", orgData.getId(), orgData);
        });
        System.out.println((System.currentTimeMillis() - s) / 1000);
    }

    @Test
    void gxdPictureMd5StreamQuery() {
        Set<String> pictureId = new HashSet<>();
        CsvParser<GxdAssetData20211216> parser = new CsvParser<>(",");
        AtomicInteger index = new AtomicInteger();
        String sql = "select outer_picture_id pictureId,outer_id         communityId,pic_data_source  communityPicPath,category         type,source_type from gxd_asset_data_20211216_1_3";
        operDemoMapper.streamQueryGxdAssetData20211216(sql, resultContext -> {
            GxdAssetData20211216 orgData = resultContext.getResultObject();
            if (StringUtils.isBlank(orgData.getCommunityId())) {
                return;
            }
            // 类型设置
            String picPath = orgData.getCommunityPicPath();
            /*if (StringUtils.contains(picPath, "/10/")) {
                orgData.setType("ROADS");
            } else if (StringUtils.contains(picPath, "/11/")) {
                orgData.setType("FACILITIES");
            } else if (StringUtils.contains(picPath, "/14/")) {
                orgData.setType("ROADS");
            } else if (StringUtils.contains(picPath, "/41/")) {
                orgData.setType("GATEWAYS");
            }*/

            String md5 = DigestUtils.md5Hex(orgData.getCommunityId() + picPath);
            orgData.setComposeMd5(md5);
            if (pictureId.contains(orgData.getPictureId())) {
                orgData.setPictureId(String.valueOf(SnowflakeUtil.getInstance().nextId()));
            } else {
                pictureId.add(orgData.getPictureId());
            }
            // orgData.updateById();
            FileUtils.printFile(parser.parse(orgData), "./source/gxd", "result3.csv", true);
            if (index.getAndIncrement() % 1000 == 0) {
                System.out.printf("已处理：%s,%s", index, DateUtils.formatDate(System.currentTimeMillis()));
            }
        });
    }

    public static String getMd5(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] digest = md.digest(data.getBytes(StandardCharsets.UTF_8));
        return byte2hex(digest);
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 国信达pictureId重复进行处理
     */
    void a() {
        Set<String> pictureIdSet = new HashSet<>();
        // 为了清空下文件的
        FileUtils.printFile("",
                "./source/gxd",
                "select_outer_picture_id__id_from_esf_pic.csv",
                false);
        FileUtils.traverseLine(
                "/Users/qiushengming/Downloads/select_outer_picture_id__id_from_esf_pic.csv",
                s -> {
                    String[] strArr = s.split(",");
                    if (pictureIdSet.contains(strArr[0])) {
                        FileUtils.printFile(String.format("update esf_picture_info set outer_picture_id = '%s' where id = '%s';",
                                        SnowflakeUtil.getInstance().nextId(), strArr[1]),
                                "./source/gxd",
                                "select_outer_picture_id__id_from_esf_pic.csv",
                                true);
                    } else {
                        pictureIdSet.add(strArr[0]);
                    }
                });
    }

    /**
     * 计算马赛克的大小
     */
    @Test
    void esfPictureInfoMapIdentify2() {
        FileUtils.printFile("", "./source/picture_mosaic", "result.csv", false);
        DecimalFormat df = new DecimalFormat("######0.00");
        CsvParser<EsfPictureInfoMapIdentify2> parser = new CsvParser<>(",");
        String sql = "select  id, face_infor faceInfor, pic_data picData, url_mosaic urlMosaic from esf_picture_info_mosaic_result_20211217";
        operDemoMapper.streamQueryEsfPictureInfoMapIdentify2(sql, resultContext -> {
            EsfPictureInfoMapIdentify2 orgData = resultContext.getResultObject();
            JSONObject faceInfor = JSONObject.parseObject(orgData.getFaceInfor());
            StringBuilder areasb = new StringBuilder();
            faceInfor.getJSONObject("result")
                    .getJSONArray("face_list")
                    .forEach(o -> {
                        JSONObject o1 = (JSONObject) o;
                        JSONObject location = o1.getJSONObject("location");
                        double left = location.getDouble("left");
                        double top = location.getDouble("top");
                        double width = location.getDouble("width");
                        double height = location.getDouble("height");
                        areasb.append(df.format(width / 28.346 * height / 28.346)).append("#");
                    });
            orgData.setArea(areasb.toString());
            orgData.setFaceInfor("");
            FileUtils.printFile(parser.parse(orgData), "./source/picture_mosaic", "result.csv", true);
        });
    }

    public static void main(String[] args) {
        System.out.println(getMd5("周姿依"));


    }

}
