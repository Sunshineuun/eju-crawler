package com.qiusm.eju.crawler.base.entity;

import lombok.Data;

/**
 * 图片入kafka的转换实体
 *
 * @author 赵乐
 * @date 2019/1/29 15:04
 */
@Data
public class PictureKey {
    /**
     * 图片入存储通的路径
     */
    private String path;
    /**
     * 图片转换之后的key
     */
    private String picKey;
    /**
     * 图片的源url
     */
    private String picSrc;
    /**
     * 图片后缀
     */
    private String picFix;
    /**
     * 图片的类型
     * 1，户型图 2，实景图 ,3缩略图
     */
    private String picType;
    /**
     * 有无水印
     * 0 无水印， 1 有水印
     */
    private String remarkStatus;
}
