package com.qiusm.eju.crawler.entity.government.nj;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author qiushengming
 */
@Data
@TableName("fd_nanjin_building")
@EqualsAndHashCode(callSuper = true)
public class FdNanJinBuilding
        extends SuperEntity<FdNanJinBuilding> {

    @TableField("city_name") private String cityName;

    @TableField("house_id")
    private Long houseId;

    @TableField("project_id")
    private String projectId;

    @TableField("project_name")
    private String projectName;

    @TableField("")
    private String buildingNum;

    @TableField("open_time")
    private String openTime;

    @TableField("total_number")
    private String totalNumber;

    @TableField("unsold_total_num")
    private String unsoldTotalNum;

    @TableField("today_subscribe")
    private String todaySubscribe;

    @TableField("today_transaction")
    private String todayTransaction;

    @TableField("subscribe_total_num")
    private String subscribeTotalNum;

    @TableField("transaction_total_num")
    private String transactionTotalNum;

    @TableField("transaction_total_area")
    private String transactionTotalArea;

    @TableField("average_price")
    private String averagePrice;

    @TableField("turnover_ratio")
    private String turnoverRatio;

    @TableField("building_detail_url")
    private String buildingDetailUrl;

    @TableField("price_list_img_url")
    private String priceListImgUrl;

    private String status;

    private String version;

    @TableField("create_time")
    private Date createTime;
}