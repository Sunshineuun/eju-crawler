package com.qiusm.eju.crawler.poi.gaode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.constant.EjuConstant.PROXY_URL;
import static com.qiusm.eju.crawler.poi.gaode.constant.GaodeUrl.All_CITY_POI_URL;

/**
 * @author qiushengming
 */
@Service
public class GaodeService {

    protected static OkHttpUtils httpUtils = OkHttpUtils.Builder().proxyUrl(PROXY_URL).connectTimeout(60000).readTimeout(60000).charset(GBK).builderHttp();

    /**
     * 中国区所有城市的坐标 <br>
     * 结果参考：test/resources/poi/gaode/allCity.json <br>
     */
    public String allCity() {
        String body = httpUtils.proxyGet(All_CITY_POI_URL, UTF8, 10000, 10000, 5, null);
        if (StringUtils.isNotBlank(body)) {
            JSONObject bodyObj = JSONObject.parseObject(body);
            //一级
            JSONArray oneArr = bodyObj.getJSONArray("districts");
            JSONObject districtObj = (JSONObject) oneArr.get(0);
            //二级
            JSONArray twoObj = districtObj.getJSONArray("districts");
            for (int i = 0; i < twoObj.size(); i++) {
                JSONObject disObj = twoObj.getJSONObject(i);
                String province = disObj.getString("name");
                String provinceAdCode = disObj.getString("adcode");
                //三级
                JSONArray threeArr = disObj.getJSONArray("districts");
                for (int j = 0; j < threeArr.size(); j++) {
                    JSONObject cityObj = threeArr.getJSONObject(j);
                }
            }
        }
        return body;
    }
}
