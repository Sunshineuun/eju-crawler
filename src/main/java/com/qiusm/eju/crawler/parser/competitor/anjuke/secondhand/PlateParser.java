package com.qiusm.eju.crawler.parser.competitor.anjuke.secondhand;

import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeParserBase;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_PLATE_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_REGION_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.*;


@Slf4j
public class PlateParser extends AnjukeParserBase {
    @Override
    public String getCode() {
        return AJK_PC_SECONDHAND_LISTING_PLATE_CODE;
    }

    @Override
    public String getParentCode() {
        return AJK_PC_SECONDHAND_LISTING_REGION_CODE;
    }

    @Override
    public JSONObject parser(String htmlStr, OkHttpUtils okHttpUtils) {
        /*ParserResult parserResult = new ParserResult();

        Map<String, Object> data = taskInstanceRequest.getData();*/
        /*final String city = (String) data.get("city");*/

        Document document = Jsoup.parse(htmlStr);

        /*List<TaskInstanceRequest> taskInstanceRequests = new ArrayList<>();*/
        List<JSONObject> resultList = new ArrayList<>();

        //存放到图片的kafka中
        /*List<PictureKey> picKeyList = new ArrayList<>();*/

        //获取当前页的列表信息
        Elements houseTagList = document.select("section.list>div>a");
        houseTagList.forEach(a -> {
            /*TaskInstanceRequest request = new TaskInstanceRequest();*/

            Map<String, Object> map = new HashMap<>(16);

            //缩略图
            Elements imgs = a.select("a>div.property-image>img.lazy-img");
            imgs.forEach(img -> {
                // 获取图片原始地址
                String surfacePlotSrc = img.attr("data-src");
                // 图片上传转换
                map.put(SURFACE_PLOT, surfacePlotSrc);
            });

            // 房屋挂牌标题
            Elements titleH3Tag = a.select("h3.property-content-title-name");
            String goodsName = titleH3Tag.attr("title");
            // 房屋详情URL
            String currentUrl = a.attr("href");
            if (StringUtils.isNotBlank(currentUrl)) {
                String[] split = currentUrl.split("\\?");
                if (split.length == 2) {
                    currentUrl = split[0];
                }
                map.put(CURRENT_RACK_RATE_URL, currentUrl);
                map.put(GOODS_NAME, goodsName);
            }

            // 获取p标签列表
            Elements houseTagInfo = a.select("div.property-content-info>p");

            // 户型、面积、朝向、楼层、建造年代
            map.put(RACK_RATE_LAYOUT, houseTagInfo.get(0).text());
            map.put(RACK_RATE_AREA, houseTagInfo.get(1).text());
            map.put(ORIENTATION, houseTagInfo.get(2).text());
            map.put(FLOOR_HEIGHT, houseTagInfo.get(3).text());
            map.put(RACK_RATE_BUILD_YEAR, houseTagInfo.get(4).text());

            // 小区名称
            map.put(TITLE, a.select("p.property-content-info-comm-name").text());
            // 小区地址
            map.put(ADDRESS, a.select("p.property-content-info-comm-address").text());
            // 小区标签
            map.put(TAG, a.select("span.property-content-info-tag").text());
            // 总价
            map.put(TOTAL_PRICE, a.select("p.property-price-total").text());
            // 均价
            map.put(AVG_PRICE, a.select("p.property-price-average").text());

            /*request.setData(map);
            request.setUrl(currentUrl);
            request.setCode(AJK_PC_SECONDHAND_LISTING_DETAIL_CODE);

            taskInstanceRequests.add(request);*/
            resultList.add(new JSONObject(map));

        });
        // 获取下一页链接
        /*taskInstanceRequests.add(nextActiveParser(document, data));

        parserResult.setRequests(taskInstanceRequests);
        parserResult.setResultList(resultList);
        parserResult.setPictureList(picKeyList);*/


        return null;
    }

    /*private TaskInstanceRequest nextActiveParser(Document document, Map<String, Object> data) {
        Elements nextActive = document.select("section.pagination-wrap a.next-active");
        if (nextActive.size() > 0) {
            String nextHref = nextActive.attr("href");
            if (StringUtils.isNotBlank(nextHref)) {
                TaskInstanceRequest nextPageRequest = new TaskInstanceRequest();
                nextPageRequest.setUrl(nextHref);
                nextPageRequest.setCode(AJK_PC_SECONDHAND_LISTING_PLATE_CODE);
                nextPageRequest.setData(data);
                nextPageRequest.setMethod(RequestMethodEnum.CHROME_DRIVE);
                return nextPageRequest;
            }
        }
        return null;
    }*/


}
