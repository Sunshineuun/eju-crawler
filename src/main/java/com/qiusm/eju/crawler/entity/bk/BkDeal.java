package com.qiusm.eju.crawler.entity.bk;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("bk_deal")
@EqualsAndHashCode(callSuper = true)
public class BkDeal extends SuperEntity<BkDeal> {
    private String adjustments;
    private String propertyOwnership;
    private String goodesId;
    private String plate;
    private String dealTime;
    private String taskId;
    private String totalFloor;
    private String floorHeight;
    private String title;
    private String elevator;
    private String cityname;
    private String buildYear;
    private String propertyYear;
    private String propertyType;
    private String housingYears;
    private String historyDealPrice;
    private String viewcount;
    private String heating;
    private String ladderRatio;
    private String strategyInfo;
    private String buildType;
    private String decoration;
    private String resblockId;
    private String area;
    private String goodesName;
    private String orientation;
    private String batchNo;
    private String concernCount;
    private String fileName;
    private String dealAveragePrice;
    private String titleId;
    private String goodsId;
    private String dealPrice;
    private String listPrice;
    private String pvCount;
    private String layout;
    private String infoSrc;
    private String dwellingFloorSpace;
    private String historyDealDesc;
    private String dealCycle;
    private String tradingRights;
    private String createUser;
    private String region;
    private String layoutStr;
    private String desc;
    private String frameImage;
    private String citycode;
    private String frameUrl;
    private String frameImageLocal;
    private String detailUrl;
    private String frameImageMarkRemove;
    private Date createTime = new Date();
}
