package com.qiusm.eju.crawler.entity.bk;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("bk_deal_pc")
@EqualsAndHashCode(callSuper = true)
public class BkDealPc extends SuperEntity<BkDealPc> {
    private String city;
    private String bkName;
    private String qyName;
    private String title;
    private String houseInfo;
    private String dealDate;
    private String totalPrice;
    private String positionInfo;
    private String unitPrice;
    private String listedPrice;
    private String dealHouseTxt;
    private String detailUrl;
    private String layoutImg;
}
