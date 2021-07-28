package com.qiusm.eju.crawler.government.nj;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.qiusm.eju.crawler.constant.government.NanJinFieldConstant.*;


/**
 * 南京政府源头html解析具体实现
 *
 * @author qiushengming
 */
@Slf4j
@Component
public class NanJinParserImpl implements NanJinParser {

    @Override
    public Integer totalPageNumFromHomepageParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        // 尾页
        Element tail = doc.select("div.navs_block>a").last();
        String totalPageNum = tail.attr("data-ci-pagination-page");
        return Integer.valueOf(totalPageNum);
    }

    @Override
    public List<Map<String, Object>> xmListNowPageParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Elements tables = doc.select("div.spl_table>table");
        List<Map<String, Object>> results = new ArrayList<>();
        tables.forEach((table) -> {
            Map<String, Object> result = new LinkedHashMap<>(8);
            // 图片URL 目前这个不需要
            String imgUrl = table.select("img").first().attr("data-original");
            result.put("imgUrl", imgUrl);

            Elements tds = table.select("td");
            tds.forEach((td) -> {
                String key = td.text();
                if (FIELD_MAPPING.containsKey(key)) {
                    if (StringUtils.equals(key, "项目名称")) {
                        Element nextElement = td.nextElementSibling();
                        result.put(PROJECT_NAME, nextElement.text());
                        Element a = nextElement.select("a").first();
                        // 结果示例：https://www.njhouse.com.cn/spf/detail?prjid=1463150
                        String href = a.attr("abs:href");
                        result.put(DETAIL_URL, href);
                        result.put(PROJECT_ID, href.split("=")[1]);
                    } else {// 可能包含的字段：最新许可证号、区属、最新拟开盘时间、项目类别、销售热线、项目地址
                        result.put(FIELD_MAPPING.get(key), td.nextElementSibling().text());
                    }
                }
            });

            results.add(result);
        });

        return results;
    }

    @Override
    public Map<String, Object> xmDetailParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> result = new LinkedHashMap<>(32);
        // 楼盘首页右侧表格
        doc.select("table.spf_table td.thead").forEach((td) -> {
            String key = td.text().replaceAll(" ", "");
            if (FIELD_MAPPING.containsKey(key)) {
                if (StringUtils.equals(key, "预售许可证/现售备案证")) {// TODO
                    List<Map<String, String>> preSalePermits = new ArrayList<>();
                    Elements aTags = td.nextElementSibling().select("a");
                    aTags.forEach((a) -> {
                        if (StringUtils.endsWith(a.nextElementSibling().tagName(), "br")) {
                            return;
                        }

                        Map<String, String> map = new HashMap<>(8);
                        String href = a.attr("abs:href");
                        map.put(PRE_SALE_PERMIT_CODE, a.text());
                        map.put(PRE_SALE_PERMIT_ID, href.split("=")[1]);
                        // 明细内容
                        map.put(PRE_SALE_REG_DETAIL_URL, href);
                        // 方案名称
                        map.put(XS_OR_YS, a.nextElementSibling().text());
                        // 现售or预售方案是个PDF文件
                        map.put(PRE_SALE_PLAN_URL, href.replace("persalereg_detail?permitid", "permit?id"));
                        preSalePermits.add(map);
                    });
                    result.put(FIELD_MAPPING.get(key), preSalePermits);
                } else if (StringUtils.equals(key, "开发企业")) {
                    Element aTag = td.nextElementSibling().select("a").first();
                    result.put(FIELD_MAPPING.get(key), aTag.text());
                    result.put(DEVELOPMENT_ENTERPRISE_DETAIL_URL, aTag.attr("abs:href"));
                } else {
                    // 可能包含的字段：项目地址、用途、开发企业、代理销售企业、最新拟开盘时间、
                    // 土地使用权证/不动产权证书、用地规划许可证、工程规划许可证、施工许可证
                    result.put(FIELD_MAPPING.get(key), td.nextElementSibling().text());
                }
            }
        });

        Elements tables = doc.select("div.spf_block_list>table");
        // 楼盘首页.项目总体销售情况 tables.get(0)
        Element salesTable = tables.get(0);
        salesTable.select("td").forEach((td) -> {
            String key = td.text();
            if (FIELD_MAPPING.containsKey(key)) {
                result.put(FIELD_MAPPING.get(key), td.nextElementSibling().text());
            }
        });

        // 楼盘首页.概述 tables.get(1)
        Element overviewTable = tables.get(1);
        overviewTable.select("td").forEach((td) -> {
            String key = td.text();
            if (FIELD_MAPPING.containsKey(key)) {
                result.put(FIELD_MAPPING.get(key), td.nextElementSibling().text());
            }
        });

        return result;
    }

    @Override
    public Map<String, Object> xmKfsDetailsParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> result = new LinkedHashMap<>(32);
        Elements tables = doc.select("table.kf_tb1");
        // 开发企业资料
        tables.get(0).select("td").forEach((td) -> {
            String key = td.text();
            if (FIELD_MAPPING.containsKey(key)) {
                result.put(FIELD_MAPPING.get(key), td.nextElementSibling().text());
            }
        });
        // 诚信记录
        StringBuilder sb = new StringBuilder();
        tables.get(1).select("td").forEach((td) -> sb.append(td.text()).append("##"));
        result.put(INTEGRITY_RECORD, sb.toString());

        // 曝光记录
        sb.delete(0, sb.length());
        tables.get(1).select("td").forEach((td) -> sb.append(td.text()).append("##"));
        result.put(EXPOSURE_RECORD, sb.toString());

        return result;
    }

    @Override
    public Map<String, Object> xmPreSaleDetailsParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> result = new LinkedHashMap<>(32);
        doc.select("table.kf_tb1 td").forEach((td) -> {
            String key = td.text();
            if (FIELD_MAPPING.containsKey(key)) {
                result.put(FIELD_MAPPING.get(key), td.nextElementSibling().text());
            }
        });
        // 获取长图
        String imgUrl = doc.select("div.business_centers>img").first().attr("src");
        result.put(PRE_SALE_LICENSE_IMG_URL, imgUrl);
        return result;
    }

    @Override
    public Map<String, Object> xmSalesDetailsParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> result = new LinkedHashMap<>(32);

        // 项目总体销售情况
        doc.select("div.all_dong>table td").forEach((td) -> {
            String key = td.text();
            if (FIELD_MAPPING.containsKey(key)) {
                result.put(FIELD_MAPPING.get(key), td.nextElementSibling().text());
            }
        });

        // 楼栋列表
        List<Map<String, Object>> buildingList = new ArrayList<>();
        // 分幢销售情况
        doc.select("div.fdxs li").forEach((li) -> {
            Map<String, Object> map = new LinkedHashMap<>(32);
            // [5幢]+详情URL地址
            Element aTag = li.select("div>a").get(0);
            // 楼栋号
            map.put(BUILDING_NUM, aTag.text());
            // 楼栋明细URL
            map.put(BUILDING_DETAIL_URL, aTag.attr("abs:href"));
            // 物价申报价格表 是个图片 有些楼栋是没有物价申报价格表的
            Elements thFdxsTs = li.select("th.fdxs-ts2>a");
            if (thFdxsTs.size() > 0) {
                map.put(PRICE_LIST_IMG_URL, thFdxsTs.get(0).attr("abs:href"));
            }
            buildingList.add(map);
        });

        result.put(BUILDING_LIST, buildingList);
        return result;
    }

    @Override
    public Map<String, Object> xmBuildingDetailsParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> buildingDetails = new LinkedHashMap<>(32);
        List<Map<String, Object>> houseList = new ArrayList<>();

        Element buildingTitle = doc.select("div.detail_dong").get(0).child(0);
        Element buildingTable = doc.select("div.detail_dong").get(0).child(1);

        String buildingNum = buildingTitle.text().replaceAll("[  ].*", "");
        buildingDetails.put(BUILDING_NUM, buildingNum);

        Elements buildingDetailTds = buildingTable.select("td");
        // 开盘时间,入网,未售,今日认购,今日成交,累计认购,累计成交,销售面积,均价,成交比例
        buildingDetails.put(OPEN_TIME, buildingDetailTds.get(0).text());
        buildingDetails.put(TOTAL_NUMBER, buildingDetailTds.get(1).text());
        buildingDetails.put(UNSOLD_TOTAL_NUM, buildingDetailTds.get(2).text());
        buildingDetails.put(TODAY_SUBSCRIBE, buildingDetailTds.get(3).text());
        buildingDetails.put(TODAY_TRANSACTION, buildingDetailTds.get(4).text());
        buildingDetails.put(SUBSCRIBE_TOTAL_NUM, buildingDetailTds.get(5).text());
        buildingDetails.put(TRANSACTION_TOTAL_NUM, buildingDetailTds.get(6).text());
        buildingDetails.put(TRANSACTION_TOTAL_AREA, buildingDetailTds.get(7).text());
        buildingDetails.put(AVERAGE_PRICE, buildingDetailTds.get(8).text());
        buildingDetails.put(TURNOVER_RATIO, buildingDetailTds.get(9).text());

        // 房屋列表
        doc.select("table.ck_table td.ks").forEach(td -> {
            Map<String, Object> result = new LinkedHashMap<>(32);

            // ks:未售;rg:已认购;qy:已签约;cq:已办产权;xz:被限制;az:安置;
            String saleStatus = "未知";
            switch (td.attr("class")) {
                case "ks":
                    saleStatus = "未售";
                    break;
                case "rg":
                    saleStatus = "已认购";
                    break;
                case "qy":
                    saleStatus = "已签约";
                    break;
                case "cq":
                    saleStatus = "已办产权";
                    break;
                case "xz":
                    saleStatus = "被限制";
                    break;
                case "az":
                    saleStatus = "安置";
                    break;
                default:
            }

            String houseType = td.attr("title");

            Elements aTag = td.select("a");
            String houseDetailUrl = aTag.get(0).attr("abs:href");
            String houseNum = aTag.get(0).text();

            String area = "";
            String price = "";
            List<Node> aChildNodes = aTag.get(1).childNodes();
            if (aChildNodes.size() == 5) {
                area = aChildNodes.get(1).attr("src");
                price = aChildNodes.get(4).attr("src");
            }

            if (aChildNodes.size() == 3) {
                area = aChildNodes.get(0).toString();
                price = aChildNodes.get(2).toString();
            }

            result.put(HOUSE_TYPE, houseType);
            result.put(HOUSE_DETAIL_URL, houseDetailUrl);
            result.put(HOUSE_NUM, houseNum);
            result.put(HOUSE_AREA, area);
            result.put(HOUSE_PRICE, price);
            result.put(SALES_STATUS, saleStatus);

            houseList.add(result);
        });
        buildingDetails.put(HOUSE_LIST, houseList);
        return buildingDetails;
    }

    @Override
    public Map<String, Object> xmUnitDetailsParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> unitDetails = new LinkedHashMap<>(32);
        Element table = doc.select("table").first();
        table.select("td").forEach(td -> {
            String key = td.text();
            if (FIELD_MAPPING.containsKey(key)) {
                Element tdNext = td.nextElementSibling();
                // 如果标签内包含img，说明该单元格存储的是图片
                if (tdNext.select("img").size() > 0) {
                    unitDetails.put(FIELD_MAPPING.get(key), tdNext.select("img").first().attr("src"));
                } else {
                    unitDetails.put(FIELD_MAPPING.get(key), tdNext.text());
                }
            }
        });
        return unitDetails;
    }

    @Override
    public Map<String, Object> xmNiaokPictureParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> result = new LinkedHashMap<>();
        Element img = doc.getElementById("image1");
        result.put(NIAOK_PICTURE_URL, img.attr("src"));
        return result;
    }

    @Override
    public Map<String, Object> xmQwPictureParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        Map<String, Object> result = new LinkedHashMap<>();
        Element img = doc.select("img").first();
        result.put(QW_PICTURE_URL, img.attr("src"));
        return result;
    }

    @Override
    public List<String> xmXgPictureParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        List<String> result = new ArrayList<>();
        Elements imgs = doc.select("div.sdd-photo-top img");
        imgs.forEach(img -> {
            result.add(img.attr("src"));
        });
        return result;
    }

    @Override
    public List<String> xmLayoutPictureParser(String requestUrl, String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        List<String> result = new ArrayList<>();
        Elements imgs = doc.select("ul.clearfix img");
        imgs.forEach(img -> {
            result.add(img.attr("src"));
        });
        return result;
    }
}