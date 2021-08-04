package com.qiusm.eju.crawler.parser.poi.gaode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.dto.RequestDto;
import com.qiusm.eju.crawler.dto.ResponseDto;
import com.qiusm.eju.crawler.entity.poi.gaode.GaodePoi;
import com.qiusm.eju.crawler.enums.RequestMethodEnum;
import com.qiusm.eju.crawler.exception.BusinessException;
import com.qiusm.eju.crawler.service.poi.ILocationKeyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.poi.GaodeField.*;

/**
 * 高德api周边搜索 <br>
 * key: 请求权限标识；必填 <br>
 * location:中心点坐标；规则： 经度和纬度用","分割，经度在前，纬度在后，经纬度小数点后不得超过6位;必填 <br>
 * keywords:查询关键字；规则： 多个关键字用｜分割；可选 <br>
 * types:查询poi类型；规则多个用｜分割；可选 <br>
 * city:查询城市；<br>
 * radius:查询半径 <br>
 * sortrule:排序规则；规则： 按距离排序：distance；综合排序：weight；默认：distance <br>
 * offset:每页记录；规则强烈建议不超过25；<br>
 * page: 当前页数；规则： 最大翻页100；<br>
 * extensions: 返回结果控制；此项默认返回基本地址信息；取值为all返回地址信息、附近poi、道路以及道路交叉口信息。<br>
 * sig: 数字签名 <br>
 * output: 返回数据格式类型；规则：可选json、xml；默认：json <br>
 *
 * @author qiushengming
 */
@Slf4j
@Service
public class GaodePoiPageListSearch extends GaodeBaseSearch {

    private static final String URL_TEMPLATE = "http://mapdata-api.ejudata.com/inner/map/get?url=https://restapi.amap.com/v3/place/around?";
    /**
     * 必填参数
     */
    private String[] required = new String[]{"location"};
    private String[] unRequired = new String[]{"keywords", "types", "city", "radius", "sortrule", "offset", "page", "extensions", "sig", "output"};
    @Resource
    private ILocationKeyService locationKeyService;


    @Override
    protected void buildingUrl(RequestDto requestDto) {
        Map<String, String> requestParam = requestDto.getRequestParam();
        StringBuilder url = new StringBuilder(URL_TEMPLATE);
        for (String k : unRequired) {
            if (requestParam.containsKey(k)) {
                url.append(k).append("=").append(requestParam.get(k)).append("&");
            }
        }

        for (String k : required) {
            if (requestParam.containsKey(k)) {
                url.append(k).append("=").append(requestParam.get(k));
            } else {
                throw new BusinessException(999, "关键参数缺失。key：" + k);
            }
        }
        requestDto.setUrl(url.toString());
        requestDto.setRequestMethod(RequestMethodEnum.GET);
    }

    @Override
    protected void parser(RequestDto requestDto, ResponseDto responseDto) {
        JSONArray result = new JSONArray();
        // 默认存在一页
        int pageSize = 25;
        int totalPages = 1;
        int totalNum = 0;
        String gdBody = requestDto.getResponseStr();
        if (gdBody.contains("strategy:priority_first use up limit")) {
            try {
                log.info("请求次数超限，等待5s，返回结果：" + gdBody);
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (gdBody.contains(RESULT_VO)) {
            //解析结果
            JSONObject mainJson = JSON.parseObject(gdBody);
            mainJson = mainJson.getJSONObject(RESULT_VO);
            if (null == mainJson) {
                return;
            }

            String infoCode = mainJson.getString(INFO_CODE);
            if (!(StringUtils.isNotBlank(infoCode)
                    && "10000".equals(infoCode))) {
                return;
            }

            // 翻页参数
            if (totalPages == 1) {
                // 总记录数
                totalNum = !mainJson.containsKey("count") ? 0 : mainJson.getInteger("count");
                // 总页数
                totalPages = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
            }

            if (mainJson.containsKey(POIS)) {
                JSONArray poisJsonArray = JSON.parseArray(JSON.toJSONString(mainJson.get(POIS)));
                poisJsonArray.forEach(var -> {
                    if (var instanceof JSONObject) {
                        result.add((JSONObject) var);
                    } else {
                        log.warn("对象不是[JSONObject]类型，{}", var);
                    }
                });
            } else {
                log.info("请求参数: {}, 请求放回结果为空. ", requestDto.getRequestParam());
            }
        }
        responseDto.getResult().put("list", result);
        responseDto.getResult().put("totalPages", totalPages);
        responseDto.getResult().put("totalNum", totalNum);
    }

    private GaodePoi jsonObjToGaodePoiObj(JSONObject var0) {
        GaodePoi var1 = new GaodePoi();

        var1.setUid(var0.getString("id"));
        var1.setParent(var0.getString("parent"));
        var1.setChildType(var0.getString("childtype"));
        var1.setName(var0.getString("name"));
        var1.setTag(var0.getString("tag"));

        var1.setType(var0.getString("type"));
        var1.setTypeCode(var0.getString("typecode"));
        var1.setBizType(var0.getString("biz_type"));
        var1.setAddress(var0.getString("address"));
        var1.setLocation(var0.getString("location"));
        var1.setTel(var0.getString("tel"));

        var1.setPostCode(var0.getString("postcode"));
        var1.setWebsite(var0.getString("website"));
        var1.setEmail(var0.getString("email"));
        var1.setPname(var0.getString("pname"));
        var1.setPcode(var0.getString("pcode"));

        var1.setCityName(var0.getString("cityname"));
        var1.setCityCode(var0.getString("citycode"));
        var1.setAdname(var0.getString("adname"));
        var1.setAdcode(var0.getString("adcode"));
        var1.setImportance(var0.getString("importance"));

        var1.setShopId(var0.getString("shopid"));
        var1.setShopInfo(var0.getString("shopinfo"));
        var1.setPoiWeight(var0.getString("poiweight"));
        var1.setGridCode(var0.getString("gridcode"));
        var1.setDistance(var0.getString("distance"));

        var1.setBusinessArea(var0.getString("business_area"));
        var1.setNaviPoiid(var0.getString("navi_poiid"));
        var1.setEntrLocation(var0.getString("entr_location"));
        var1.setAlias(var0.getString("alias"));

        if (var0.containsKey("photos")) {
            StringBuilder photoSb = new StringBuilder();
            var0.getJSONArray("photos").forEach(photo -> {
                if (photo instanceof JSONObject) {
                    photoSb.append(((JSONObject) photo).getString("url")).append(",");
                }
            });

            if (photoSb.length() > 0) {
                var1.setPhotos(photoSb.toString());
            }
        }

        return var1;

    }

}
