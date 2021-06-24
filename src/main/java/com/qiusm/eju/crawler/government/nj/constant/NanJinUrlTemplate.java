package com.qiusm.eju.crawler.government.nj.constant;

/**
 * url模板
 *
 * @author qiushengming
 */
public class NanJinUrlTemplate {
    /**
     * 初始种子URL
     */
    public static final String INITIAL_SEED_URL = "https://www.njhouse.com.cn/spf/lists?use=0&dist=&saledate=0&per_name=";
    /**
     * 项目翻页种子URL模板
     */
    public static final String XM_LIST_PAGE_SEED_TEMPLATE = "https://www.njhouse.com.cn/spf/lists?use=0&dist=&saledate=0per_name=&page=%s";
    /**
     * 楼盘首页
     */
    public static final String XM_DETAIL_TEMPLATE = "https://www.njhouse.com.cn/spf/detail?prjid=%s";
    /**
     * 许可/备案证公示
     */
    public static final String XM_PERSALEREG_LIST_TEMPLATE = "https://www.njhouse.com.cn/spf/persalereg_list?prjid=%s";
    /**
     * 销售公示
     */
    public static final String XM_SALES_TEMPLATE = "https://www.njhouse.com.cn/spf/sales?prjid=%s";
    /**
     * 鸟瞰图
     */
    public static final String XM_NIAOK_TEMPLATE = "https://www.njhouse.com.cn/spf/niaok?prjid=%s";
    /**
     * 区位图
     */
    public static final String XM_QWT_TEMPLATE = "https://www.njhouse.com.cn/spf/qwt?prjid=%s";
    /**
     * 效果图
     */
    public static final String XM_SHOW_PIC_TEMPLATE = "https://www.njhouse.com.cn/spf/showpic?prjid=%s";
    /**
     * 套型图/户型图
     */
    public static final String XM_LAYOUT_TEMPLATE = "https://www.njhouse.com.cn/spf/tx?prjid=%s";
}
