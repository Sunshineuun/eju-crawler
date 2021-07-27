/*
package com.qiusm.eju.crawler.parser.competitor.anjuke.secondhand;

import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeParserBase;
import com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_INDEX_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.AnjukeConstant.AJK_PC_SECONDHAND_LISTING_REGION_CODE;
import static com.qiusm.eju.crawler.parser.competitor.anjuke.CommunityRackRateConstant.CITY;

*/
/**
 * 安居客挂牌案例-首页
 * @author 赵乐
 * @date 2019/7/19 11:20
 *//*

public class IndexParser extends AnjukeParserBase {

    @Override
    public String getCode() {
        return AJK_PC_SECONDHAND_LISTING_INDEX_CODE;
    }

    @Override
    public String getParentCode() {
        return null;
    }

    @Override
    public ParserResult parser(String htmlStr, TaskInstanceRequest taskInstanceRequest, OkHttpUtils okHttpUtils) {
        ParserResult parserResult = new ParserResult();
        Map<String, Object> data = taskInstanceRequest.getData();

        Document document = Jsoup.parse(htmlStr);
        if (document != null) {
            // 获取城市名称
            String city = null;
            Elements cityTag = document.select("span.city-name");
            if (cityTag.size() > 0) {
                city = cityTag.text();
            }
            data.put(CITY, city);

            // 获取筛选条件中的区域条件
            Elements aTags = document.select("section.filter-content>ul.region a");
            if (aTags.size() > 0) {
                List<TaskInstanceRequest> taskInstanceRequests = new ArrayList<>();
                aTags.forEach(a -> {
                    String regionHref = a.attr("href");
                    String[] temp = regionHref.split("\\?");
                    if (temp.length == 2) {
                        regionHref = temp[0];
                    }
                    String region = a.text();

                    if (StringUtils.equals(region, "不限")) {
                        return;
                    }

                    Map<String, Object> map = new HashMap<>(data);
                    map.put(CommunityRackRateConstant.REGION, region);
                    map.put(CommunityRackRateConstant.REGION_URL, regionHref);

                    // 创建新的请求
                    TaskInstanceRequest request = new TaskInstanceRequest();
                    request.setUrl(regionHref);
                    request.setCode(AJK_PC_SECONDHAND_LISTING_REGION_CODE);
                    request.setData(map);
                    request.setMethod(RequestMethodEnum.CHROME_DRIVE);
                    taskInstanceRequests.add(request);

                });
                parserResult.setRequests(taskInstanceRequests);
            }
        }


        return parserResult;
    }


}
*/
