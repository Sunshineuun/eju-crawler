package com.qiusm.eju.crawler.government.nj;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiusm.eju.crawler.government.utils.CommonUtils;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.government.nj.NanJinService.loadHouseAreaAndPriceImg;
import static com.qiusm.eju.crawler.government.nj.constant.NanJinFieldConstant.*;
import static com.qiusm.eju.crawler.government.nj.constant.NanJinUrlTemplate.*;


/**
 * 页面解析调试用。
 *
 * @author qiushengming
 */
public class NanJinParserTest {
    private static final OkHttpUtils HTTP_CLIENT = CommonUtils.createHttpClient();

    private static final NanJinParser PARSER = (NanJinParser) Proxy.newProxyInstance(
            NanJinParserTest.class.getClassLoader(),
            NanJinParserImpl.class.getInterfaces(),
            new NanJinParserInvocation(new NanJinParserImpl()));

    /**
     * 总页码解析 字符集常量定义
     */
    public static void totalPageNumFromHomepage() {
        String url = INITIAL_SEED_URL;
        String htmlStr = httpGetBody(url, UTF8);
        Integer totalPageNum = PARSER.totalPageNumFromHomepageParser(url, htmlStr);
        System.out.println(totalPageNum);
    }

    /**
     * 当前项目列表页解析
     */
    public static void xmListNowPageParser() {
        String url = String.format(XM_LIST_PAGE_SEED_TEMPLATE, 1);
        String htmlStr = httpGetBody(url, UTF8);
        List<Map<String, Object>> results = PARSER.xmListNowPageParser(url, htmlStr);
        System.out.println(new JSONArray(Collections.singletonList(results)));
    }

    /**
     * 楼盘首页解析
     */
    public static void xmDetail() {
        String url = String.format(XM_DETAIL_TEMPLATE, "1463150");
        String htmlStr = httpGetBody(url, UTF8);
        Map<String, Object> result = PARSER.xmDetailParser(url, htmlStr);
        System.out.println(new JSONObject(result));

        // 许可证详情
        xmPreSaleDetails(result);
        System.out.println(new JSONObject(result));
    }

    /**
     * 开发商
     */
    public static void xmKfsDetails() {
        String requestUrl = "https://www.njhouse.com.cn/qy/detail?compno=694363&prjid=1463150";
        String htmlStr = httpGetBody(requestUrl, UTF8);
        Map<String, Object> result = PARSER.xmKfsDetailsParser(requestUrl, htmlStr);
        System.out.println(new JSONObject(result));
    }

    /**
     * 许可证
     *
     * @param lpDetails 楼盘详情
     */
    public static void xmPreSaleDetails(Map<String, Object> lpDetails) {
        List<Map<String, Object>> preSalePermitList = (List<Map<String, Object>>) lpDetails.get(PRE_SALE_PERMIT_CODE);
        preSalePermitList.forEach((o) -> {
            String requestUrl = (String) o.get(PRE_SALE_REG_DETAIL_URL);
            String htmlStr = httpGetBody(requestUrl, UTF8);
            Map<String, Object> persaleregDetailsMap = PARSER.xmPreSaleDetailsParser(requestUrl, htmlStr);
            o.putAll(persaleregDetailsMap);
        });
    }

    /**
     * 出售公示
     */
    public static void xmSalesDetails() {
        String requestUrl = String.format(XM_SALES_TEMPLATE, "2570150");
        String htmlStr = httpGetBody(requestUrl, UTF8);
        Map<String, Object> result = PARSER.xmSalesDetailsParser(requestUrl, htmlStr);
        System.out.println(new JSONObject(result));
    }

    public static void xmBuildingDetails() {
        String requestUrl = "https://www.njhouse.com.cn/spf/sales_detail?PRJ_ID=2570150&prjid=2570150&buildid=576177&dm=5%E5%B9%A2";
        String htmlStr = httpGetBody(requestUrl, UTF8);
        Map<String, Object> buildingDetails = PARSER.xmBuildingDetailsParser("", htmlStr);
        for (Map<String, Object> houseDetails : (List<Map<String, Object>>) buildingDetails.get(HOUSE_LIST)) {
            houseDetails.put(BUILDING_NUM, buildingDetails.get(BUILDING_NUM));
            houseDetails.put(PROJECT_ID, "2570150");
            loadHouseAreaAndPriceImg(houseDetails);
            try {
                xmUnitDetails(houseDetails);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(new JSONObject(buildingDetails));
    }

    private static void xmUnitDetails(Map<String, Object> houseDetails) {
        String requestUrl = (String) houseDetails.get(HOUSE_DETAIL_URL);
        String htmlStr = httpGetBody(requestUrl, UTF8);
        Map<String, Object> unitDetails = PARSER.xmUnitDetailsParser(requestUrl, htmlStr);
        houseDetails.putAll(unitDetails);
        loadHouseAreaAndPriceImg(houseDetails);
    }

    private static String httpGetBody(String requestUrl, String charset) {
        return HTTP_CLIENT.proxyGet(requestUrl, charset, new HashMap<>(1));
    }

    public static void main(String[] args) {
        xmBuildingDetails();
    }
}
