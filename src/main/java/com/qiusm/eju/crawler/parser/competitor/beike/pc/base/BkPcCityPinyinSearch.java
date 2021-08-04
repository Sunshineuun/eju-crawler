package com.qiusm.eju.crawler.parser.competitor.beike.pc.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.parser.competitor.beike.pc.BkPcBaseSearch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * bk城市列表解析
 *
 * @author qiushengming
 */
@Service
public class BkPcCityPinyinSearch
        extends BkPcBaseSearch {
    private static final String URL_TEMPLATE = "https://www.ke.com/city/";

    @Override
    protected void buildingUrl(RequestDto requestDto) {
        requestDto.setUrl(URL_TEMPLATE);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        Document doc = Jsoup.parse(requestDto.getResponseStr());
        Elements aEles = doc.select("div.city_list_section li.CLICKDATA a");
        JSONArray cityArr = new JSONArray();
        aEles.forEach(ele -> {
            JSONObject city = new JSONObject();
            city.put("city", ele.text());
            city.put("city_pinyin", ele.attr("href").replace(".ke.com", "").replace("//", ""));
            cityArr.add(city);
        });
        responseDto.getResult().put("list", cityArr);
    }
}
