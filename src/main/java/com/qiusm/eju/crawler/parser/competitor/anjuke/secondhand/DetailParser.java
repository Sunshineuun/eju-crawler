/*
package com.qiusm.eju.crawler.parser.competitor.anjuke.secondhand;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeParserBase;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_DETAIL_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_PLATE_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.*;

*/
/**
 * 安居客挂牌案例-首页-挂牌详情
 *
 * @author 赵乐
 * @date 2019/7/19 15:20
 *//*

@Slf4j
public class DetailParser extends AnjukeParserBase {

    @Override
    public String getCode() {
        return AJK_PC_SECONDHAND_LISTING_DETAIL_CODE;
    }

    @Override
    public String getParentCode() {
        return AJK_PC_SECONDHAND_LISTING_PLATE_CODE;
    }

    @Override
    public ParserResult parser(String htmlStr, TaskInstanceRequest taskInstanceRequest, OkHttpUtils okHttpUtils) {
        ParserResult parserResult = new ParserResult();
        Map<String, Object> data = taskInstanceRequest.getData();
        String city = (String) data.get("city");

        String detailUrl = taskInstanceRequest.getUrl();
        Document document = Jsoup.parse(htmlStr);
        if (document != null) {
            if (StringUtils.isBlank(city)) {
                city = document.select("div#switch_apf_id_6>span.city").text();
            }

            Map<String, Object> map = new HashMap<>(32);
            map.putAll(data);
            map.put(RACK_RATE_DETAIL_URL, detailUrl);

            // 解析房源信息
            Elements houseInfo = document.getElementById("houseInfo").select("td");
            houseInfo.forEach(td -> {
                Elements span = td.select("span");
                if (span.size() != 2) {
                    return;
                }
                String key = span.get(0).text();
                String value = span.get(1).text();
                switch (key) {
                    case "产权性质":
                        map.put(RACK_RATE_TRADING_RIGHTS, value);
                        break;
                    case "物业类型":
                        map.put(RACK_RATE_PROPERTY_TYPE, value);
                        break;
                    case "产权年限":
                        map.put(RACK_RATE_PROPERTY_YEAR, value);
                        break;
                    case "房本年限":
                        map.put(HOUSING_YEARS, value);
                        break;
                    case "唯一住房":
                        map.put(SOLE_HOUSING, value);
                        break;
                    case "参考预算":
                        map.put(DOWN_PAYMENT, value);
                        break;
                    case "发布公司":
                        map.put(AGENT_COMPANY, value);
                        break;
                    case "发布时间":
                        map.put(GOODS_PUBTIME, value);
                        break;
                    case "营业执照":
                        break;
                    case "房源核验编码":
                        break;
                    default:
                        System.out.println(span.text());
                        break;
                }
            });

            // 房屋编码
            String houseCode = document.select("div.props-main>div.banner>div.banner-title>div.banner-title-code").text();
            map.put(GOODS_CODE, houseCode.replace("房屋编码：", ""));
            log.info("房屋编码: {}， url: {}", map.get(GOODS_CODE), detailUrl);
            // 户型图右侧信息解析
            Elements div = document.select("div.maininfo");
            // 价格信息 单价、总价
            Elements price = div.select("div.maininfo-price");
            map.put(AVG_PRICE, price.select("div.maininfo-avgprice-price").text());
            map.put(TOTAL_PRICE, price.select("div.maininfo-price-wrap").text());
            // 户型信息 户型、楼层、面积、朝向
            Elements model = div.select("div.maininfo-model");
            // 户型
            map.put(RACK_RATE_LAYOUT, model.select("div.maininfo-model-item-1>div.maininfo-model-strong").text());
            // 所在楼层
            map.put(FLOOR_HEIGHT, model.select("div.maininfo-model-item-1>div.maininfo-model-weak").text());
            // 面积
            map.put(RACK_RATE_AREA, model.select("div.maininfo-model-item-2>div.maininfo-model-strong").text());
            // 装修
            map.put(DECORATION, model.select("div.maininfo-model-item-2>div.maininfo-model-weak").text());
            // 朝向
            map.put(ORIENTATION, model.select("div.maininfo-model-item-3>div.maininfo-model-strong").text());

            // 元信息 标签、小区信息、所属小区、所属区域\板块、小区URL
            Elements meta = div.select("div.maininfo-meta");
            // a标签
            Elements anchorWeak = meta.select("div.maininfo-community-item a.anchor-weak");
            // 小区信息
            Element community = anchorWeak.get(0);
            String communityUrl = community.attr("href");
            map.put(RACK_RATE_TITLE, community.text());
            map.put(RACK_RATE_TITLE_ID, communityUrl.substring(communityUrl.lastIndexOf("/") + 1));
            map.put(TITLE_URL, communityUrl);
            // 区域 板块
            map.put(RACK_RATE_REGION, anchorWeak.get(1).text());
            map.put(RACK_RATE_PLATE, anchorWeak.get(2).text());

            // 经纪人信息
            Elements broker = div.select("div.maininfo-broker");

            // 描述
            Elements divs = document.select("div.houseIntro-content-wrap div.houseIntro-content-item");
            divs.forEach(var -> {
                String title = var.select("div.houseIntro-content-title").text();
                String desc = var.select("div.houseIntro-content-p").text();
                switch (title) {
                    case "核心卖点":
                        map.put(SELLING_POINT, desc);
                        break;
                    case "业主心态":
                        map.put(PROPRIETOR_MENTALITY, desc);
                        break;
                    case "服务介绍":
                        map.put(SERVICE_TAG, title);
                        map.put(SERVICE_INTRODUCTION, desc);
                        break;
                    default:
                        break;
                }
            });

            // 房屋核验编码、发布时间\所属小区
            document.select("span.houseInfo-main-item-name");

            // 小区详情
            Elements communityDetailInfo = document.getElementById("community").select("div.community-info-td");
            communityDetailInfo.forEach(var -> {
                Elements pTitle = var.getElementsByClass("community-info-td-title");
                Elements pValue = var.getElementsByClass("community-info-td-value");
                String key = pTitle.text();
                String value = pValue.text();
                switch (key) {
                    case "小区均价":
                        map.put(COMMUNITY_AVERAGE_PRICE, var.select("span.monthchange-money").text());
                        // 环比
                        map.put(COMMUNITY_CHAIN_RATIO, var.select("span.monthchange-value").text());
                        break;
                    case "小区户数":
                        map.put(COMMUNITY_HOME_TOTAL, value);
                        break;
                    case "容积率":
                        map.put(COMMUNITY_PLOT_RATE, value);
                        break;
                    case "绿化率":
                        map.put(COMMUNITY_GREEN_RATE, value);
                        break;
                    case "物业费用":
                        map.put(COMMUNITY_PROPERTY_PRICE, value);
                        break;
                    default:
                        break;
                }
            });

            //存放到图片的kafka中
            List<PictureKey> picKeyList = new ArrayList<>();
            //图片链接
            List<Map<String, Object>> housePictureList = new ArrayList<>();
            houseImageParser(document, picKeyList, housePictureList, city);
            map.put("housing_pictures", housePictureList);

            parserResult.setResultJson(new JSONObject(map));
            parserResult.setPictureList(picKeyList);
        }
        return parserResult;
    }

    private void houseImageParser(Document document, List<PictureKey> picKeyList, List<Map<String, Object>> housePictureList, String city) {
        //存放到图片的kafka中
        // List<PictureKey> picKeylist = new ArrayList<>();
        // 第0\1是VR和视频图片，第2是户型图，后面都是室内图
        Elements imgs = document.select("div.props-left>section.gallery div.gallery-indicator-image img");
        if (imgs.size() <= 1) {
            return;
        }

        // 户型图
        Element layoutImg = imgs.get(2);
        housePictureList.add(imgUpload(layoutImg.attr("src"), picKeyList, "户型图", city));


        //室内图
        for (int i = 3; i < imgs.size(); i++) {
            Element img = imgs.get(i);
            housePictureList.add(imgUpload(img.attr("src"), picKeyList, "室内图", city));
        }
    }

    private Map<String, Object> imgUpload(String imgSrc, List<PictureKey> picKeyList, String type, String city) {
        */
/*String path = "fang/" + CityTagLoading.getCityCode(PlateformTypeEnum.PLATEFORM_TYPE_BEIKE, city, PlateformTypeEnum.COMMUNITYKETAG_TYPE_CITYID) + "/community/";
        String picKey = tranformPic(imgSrc, path, picKeyList);
        String picUrl = ParserConstans.KE_APP_ESS_URL_PRE + path + picKey + ParserConstans.KE_APP_ESS_PIC_FIX;*//*


        Map<String, Object> map = new HashMap<>(4);
        //上传图片
        map.put("pic_src", imgSrc);
        */
/*map.put("pic_src_local", picUrl);
        map.put("pic_key", picKey);*//*

        map.put("pic_type", type);
        return map;
    }


}
*/
