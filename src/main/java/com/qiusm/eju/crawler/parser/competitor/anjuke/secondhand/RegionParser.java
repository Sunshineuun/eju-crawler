package com.qiusm.eju.crawler.parser.competitor.anjuke.secondhand;


import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeParserBase;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_INDEX_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_REGION_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.PLATE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.PLATE_URL;

public class RegionParser extends AnjukeParserBase {

    @Override
    public String getCode() {
        return AJK_PC_SECONDHAND_LISTING_REGION_CODE;
    }

    @Override
    public String getParentCode() {
        return AJK_PC_SECONDHAND_LISTING_INDEX_CODE;
    }

    @Override
    public JSONObject parser(String htmlStr, OkHttpUtils okHttpUtils) {

        Document document = Jsoup.parse(htmlStr);

        Elements aTags = document.select("section.filter-content>ul.region-line3 a");
        aTags.forEach(a -> {
            String plateUrl = a.attr("href");
            String[] temp = plateUrl.split("\\?");
            if (temp.length == 2) {
                plateUrl = temp[0];
            }
            String plate = a.text();

            if (StringUtils.equals("不限", plate)) {
                return;
            }

            Map<String, Object> map = new HashMap<>(8);
            map.put(PLATE, plate);
            map.put(PLATE_URL, plateUrl);

           /* TaskInstanceRequest plateRequest = new TaskInstanceRequest();
            plateRequest.setUrl(plateUrl);
            plateRequest.setData(map);
            plateRequest.setCode(AJK_PC_SECONDHAND_LISTING_PLATE_CODE);
            plateRequest.setMethod(RequestMethodEnum.CHROME_DRIVE);
            taskInstanceRequests.add(plateRequest);*/
        });

        /*parserResult.setRequests(taskInstanceRequests);
        return parserResult;*/

        return null;
    }


}
