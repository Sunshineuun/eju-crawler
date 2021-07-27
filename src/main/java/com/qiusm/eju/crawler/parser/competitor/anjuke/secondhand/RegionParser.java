/*
package com.qiusm.eju.crawler.parser.competitor.anjuke.secondhand;


import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeParserBase;
import com.qiusm.eju.crawler.utils.StringUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.*;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.PLATE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.PLATE_URL;

*/
/**
 * 安居客挂牌案例-首页-区域信息
 *
 * @author 赵乐
 * @date 2019/7/19 11:53
 *//*

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
    public ParserResult parser(String htmlStr, TaskInstanceRequest taskInstanceRequest, OkHttpUtils okHttpUtils) {
        ParserResult parserResult = new ParserResult();

        Map<String, Object> data = taskInstanceRequest.getData();
        Document document = Jsoup.parse(htmlStr);

        Elements aTags = document.select("section.filter-content>ul.region-line3 a");
        List<TaskInstanceRequest> taskInstanceRequests = new ArrayList<>();
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
            map.putAll(data);
            map.put(PLATE, plate);
            map.put(PLATE_URL, plateUrl);

            TaskInstanceRequest plateRequest = new TaskInstanceRequest();
            plateRequest.setUrl(plateUrl);
            plateRequest.setData(map);
            plateRequest.setCode(AJK_PC_SECONDHAND_LISTING_PLATE_CODE);
            plateRequest.setMethod(RequestMethodEnum.CHROME_DRIVE);
            taskInstanceRequests.add(plateRequest);
        });

        parserResult.setRequests(taskInstanceRequests);
        return parserResult;
    }


}
*/
