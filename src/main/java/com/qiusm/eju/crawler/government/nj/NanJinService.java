package com.qiusm.eju.crawler.government.nj;

import com.qiusm.eju.crawler.entity.government.nj.FdNanJinBuilding;
import com.qiusm.eju.crawler.entity.government.nj.FdNanJinHouse;
import com.qiusm.eju.crawler.entity.government.nj.FdNanJinUnit;
import com.qiusm.eju.crawler.government.GovernmentBaseService;
import com.qiusm.eju.crawler.government.base.utils.CommonUtils;
import com.qiusm.eju.crawler.government.nj.dao.NanJinDao;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.ImageReaderUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static com.qiusm.eju.crawler.constant.CharacterSet.UTF8;
import static com.qiusm.eju.crawler.government.nj.constant.NanJinConstant.NAN_JIN_IMG_PATH;
import static com.qiusm.eju.crawler.government.nj.constant.NanJinFieldConstant.*;
import static com.qiusm.eju.crawler.government.nj.constant.NanJinUrlTemplate.*;

/**
 * @author qiushengming
 */
@Slf4j
@Component
public class NanJinService extends GovernmentBaseService {

    private final NanJinParser parser = (NanJinParser) Proxy.newProxyInstance(
            NanJinParserTest.class.getClassLoader(),
            NanJinParserImpl.class.getInterfaces(),
            new NanJinParserInvocation(new NanJinParserImpl()));

    private static final ExecutorService UNIT_EXECUTOR = CommonUtils.newFixedThreadPool("nj_unit_pool", 4, 120L);

    private static final ExecutorService IMG_LOAD_EXECUTOR = CommonUtils.newFixedThreadPool("nj_img_pool", 4, 120L);

    private static final ExecutorService MAIN_EXECUTOR = CommonUtils.newFixedThreadPool("nj_main_pool", 4, 120L);

    @Resource
    private NanJinDao dao;

    public void start() {
        int totalPageNum = totalPageNumFromHomepage(INITIAL_SEED_URL);
        xmList(totalPageNum);
    }

    public void unitStart() {
        unitTask();
    }

    /**
     * 第一步：获取总页码 <br>
     *
     * @param url 请求URL
     * @return 总页码
     */
    private Integer totalPageNumFromHomepage(String url) {
        String htmlStr = httpGetBodyNoHead(url, "totalPageNumFromHomepage", UTF8);
        return parser.totalPageNumFromHomepageParser(url, htmlStr);
    }

    /**
     * 第二步：根据页码进行翻页，获取项目列表 <br>
     * url样例：https://www.njhouse.com.cn/spf/lists?use=0&dist=&saledate=0per_name=&page=1 <br>
     * {@link }
     *
     * @param totalPageNum 总页码
     */
    private void xmList(Integer totalPageNum) {
        // 开始遍历每一页
        for (int p = 1; p <= totalPageNum; p++) {
            xmListNowPage(p);
        }
    }

    /**
     * 第三步：对当项目列表的当前前页进行获取和解析，用来获取项目明细URL <br>
     * 所有流程将在这里交接扭转，其它流程中仅仅负责本流程中的内容处理，不干涉其它流程的处理。 <br>
     * 仅仅提供数据 <br>
     *
     * @param currPageNum 当前页码
     */
    private void xmListNowPage(int currPageNum) {
        String requestUrl = String.format(XM_LIST_PAGE_SEED_TEMPLATE, currPageNum);
        String htmlStr = httpGetBodyNoHead(requestUrl, "xmListNowPage", UTF8);
        List<Map<String, Object>> results = parser.xmListNowPageParser(requestUrl, htmlStr);
        int rowNum = 0;
        for (Map<String, Object> xmLpBriefInfo : results) {
            rowNum++;
            if (checkHouse(xmLpBriefInfo)) {
                continue;
            }
            log.info("正在处理：第{}页{}行,{},{}", currPageNum, rowNum, xmLpBriefInfo.get(PROJECT_NAME), xmLpBriefInfo.get(PROJECT_ID));
            // 进入楼盘首页
            Map<String, Object> xmLpDetails = xmLpDetail(xmLpBriefInfo);

            // 开发商详情数据采集 楼盘信息可以先存储到数据库中了
            xmKfsDetails(xmLpDetails);
            // 鸟瞰图片
            picture(xmLpDetails);
            // 存储楼盘项目信息
            FdNanJinHouse house = dao.saveXmLp(xmLpDetails);
            // 许可证公示 许可证页面不请求了。原因是：在楼盘首页中的【预售许可证/现售备案证】有对应的跳转链接
            // 许可证详情获取
            xmPreSaleDetails(xmLpDetails);
            // 存储预售证信息
            dao.saveXmPreSale(house, (List<Map<String, Object>>) xmLpDetails.get(PRE_SALE_PERMIT_CODE));
            // 销售公示
            Map<String, Object> salesMap = xmSalesAnnouncement(xmLpDetails);
            // 楼栋详情 由于楼栋情况特殊，在buildingDetails方法中处理
            xmBuildingDetails(salesMap, house);
        }
    }

    /**
     * 判断当前楼盘项目是否已经经过处理了 <br>
     *
     * @param xmLpBriefInfo 楼盘项目简要信息
     * @return
     */
    private boolean checkHouse(Map<String, Object> xmLpBriefInfo) {
        return dao.selectHouseByProjectIdAndProjectName(xmLpBriefInfo).size() > 0;
    }

    /**
     * 第四步：对楼盘首页（项目明细）进行获取和解析 <br>
     * URL样例：https://www.njhouse.com.cn/spf/detail?prjid=2389300 <br>
     *
     * @param lpBrief 楼盘简要信息，其中包含楼盘详情访问的种子信息
     * @return 解析出来的数据
     */
    private Map<String, Object> xmLpDetail(Map<String, Object> lpBrief) {
        String requestUrl = (String) lpBrief.get(DETAIL_URL);
        String htmlStr = httpGetBodyNoHead(requestUrl, "xmDetail", UTF8);
        Map<String, Object> xmLpDetailMap = parser.xmDetailParser(requestUrl, htmlStr);
        apply(lpBrief, xmLpDetailMap);
        return xmLpDetailMap;
    }

    /**
     * 第五步：开发商明细解析<br>
     * URL样例：https://www.njhouse.com.cn/qy/detail?compno=694363&prjid=1463150 <br>
     * 开发商信息，直接存储在xmLpDetail的Map中 <br>
     *
     * @param xmLpDetail 楼盘详细信息，其中包含了楼盘的种子地址。
     */
    private void xmKfsDetails(Map<String, Object> xmLpDetail) {
        String requestUrl = (String) xmLpDetail.get(DEVELOPMENT_ENTERPRISE_DETAIL_URL);
        String htmlStr = httpGetBodyNoHead(requestUrl, "xmKfsDetails", UTF8);
        Map<String, Object> kfsDetailMap = parser.xmKfsDetailsParser(requestUrl, htmlStr);
        xmLpDetail.putAll(kfsDetailMap);
    }

    /**
     * 第六步：预售许可证解析<br>
     *
     * @param lpDetails 楼盘详细信息
     */
    private void xmPreSaleDetails(Map<String, Object> lpDetails) {
        List<Map<String, Object>> preSalePermitList = (List<Map<String, Object>>) lpDetails.get(PRE_SALE_PERMIT_CODE);
        preSalePermitList.forEach((o) -> {
            String requestUrl = (String) o.get(PRE_SALE_REG_DETAIL_URL);
            String htmlStr = httpGetBodyNoHead(requestUrl, "xmPreSaleDetails", UTF8);
            Map<String, Object> persaleregDetailsMap = parser.xmPreSaleDetailsParser(requestUrl, htmlStr);
            apply(lpDetails, persaleregDetailsMap);
            o.putAll(persaleregDetailsMap);
            loadPreSaleLicenseListImg(persaleregDetailsMap);
        });
    }

    /**
     * 第七步：销售公示解析 <br>
     * 主要获取楼栋列表，然后进入楼栋详情进一步获取数据。 <br>
     *
     * @param lpDetails 楼盘详细信息，其中包含了楼盘的种子地址。
     * @return 楼栋列表
     */
    private Map<String, Object> xmSalesAnnouncement(Map<String, Object> lpDetails) {
        String projectId = (String) lpDetails.get(PROJECT_ID);
        String requestUrl = String.format(XM_SALES_TEMPLATE, projectId);
        String htmlStr = httpGetBodyNoHead(requestUrl, "xmPreSaleDetails", UTF8);
        Map<String, Object> salesMap = parser.xmSalesDetailsParser(requestUrl, htmlStr);
        apply(lpDetails, salesMap);
        return salesMap;
    }

    /**
     * 第八步：楼栋详情&房屋详情 <br>
     * 流程：<br>
     * 遍历楼栋，获取详细信息并存储；<br>
     * 将遍历楼栋，获取的房屋信息，进一步获取房屋详细信息，并存储；<br>
     *
     * @param salesMap 出售公示简要信息
     */
    private void xmBuildingDetails(Map<String, Object> salesMap, FdNanJinHouse house) {
        List<Map<String, Object>> buildingList = (List<Map<String, Object>>) salesMap.get(BUILDING_LIST);
        buildingList.forEach(building -> {
            apply(salesMap, building);
            loadBuildingPriceListImg(building);
            String requestUrl = (String) building.get(BUILDING_DETAIL_URL);
            String htmlStr = httpGetBodyNoHead(requestUrl, "buildingDetails", UTF8);
            Map<String, Object> buildingDetails = parser.xmBuildingDetailsParser(requestUrl, htmlStr);
            buildingDetails.putAll(building);
            FdNanJinBuilding buildingObj = dao.saveXmBuilding(house, buildingDetails);

            for (Map<String, Object> unitDetail : (List<Map<String, Object>>) buildingDetails.get(HOUSE_LIST)) {
                apply(salesMap, unitDetail);
                unitDetail.put(BUILDING_NUM, buildingDetails.get(BUILDING_NUM));
                loadHouseAreaAndPriceImg(unitDetail);
                dao.saveXmUnit(buildingObj, unitDetail);
            }
        });
    }

    /**
     * 第九步：房屋详情 <br>
     * 字段参考：NanJinFieldConstant#houseDetail中的定义。
     *
     * @param houseDetails 房屋简要
     */
    private void xmUnitDetails(Map<String, Object> houseDetails) {
        String requestUrl = (String) houseDetails.get(HOUSE_DETAIL_URL);
        String htmlStr = httpGetBodyNoHead(requestUrl, "unitDetails", UTF8);
        Map<String, Object> unitDetails = parser.xmUnitDetailsParser(requestUrl, htmlStr);
        houseDetails.putAll(unitDetails);
        loadHouseAreaAndPriceImg(houseDetails);
    }

    /**
     * 第十步：鸟瞰图、区位图、效果图、户型图
     *
     * @param xmLpDetails 楼盘项目详情
     */
    private void picture(Map<String, Object> xmLpDetails) {
        // 鸟瞰图
        Map<String, Object> niaokMap = niaokPicture(xmLpDetails);
        xmLpDetails.putAll(niaokMap);
        // 区位图
        Map<String, Object> qwMap = qwPicture(xmLpDetails);
        xmLpDetails.putAll(qwMap);
        // 效果图
        List<String> xgList = xgPicture(xmLpDetails);
        xmLpDetails.put(XG_PICTURE_URL, xgList);
        // 户型图
        List<String> layoutList = layoutPicture(xmLpDetails);
        xmLpDetails.put(LAYOUT_PICTURE_URL, layoutList);
    }

    /**
     * 鸟瞰图
     *
     * @param lpDetails 楼盘项目明细
     * @return 鸟瞰图URL
     */
    private Map<String, Object> niaokPicture(Map<String, Object> lpDetails) {
        // 鸟瞰图
        String requestUrl = String.format(XM_NIAOK_TEMPLATE, lpDetails.get(PROJECT_ID));
        String htmlStr = httpGetBodyNoHead(requestUrl, "niaokPicture", UTF8);
        Map<String, Object> niaokMap = parser.xmNiaokPictureParser(requestUrl, htmlStr);
        String fileName = String.format("%s.jpg", NIAOK_PICTURE_URL);
        loadLpXmImg(lpDetails, (String) niaokMap.get(NIAOK_PICTURE_URL), fileName);
        return niaokMap;
    }

    /**
     * 区位图
     *
     * @param lpDetails 楼盘项目明细
     * @return 区位图URL
     */
    private Map<String, Object> qwPicture(Map<String, Object> lpDetails) {
        // 区位图
        String requestUrl = String.format(XM_QWT_TEMPLATE, lpDetails.get(PROJECT_ID));
        String htmlStr = httpGetBodyNoHead(requestUrl, "qwPicture", UTF8);
        Map<String, Object> qwMap = parser.xmQwPictureParser(requestUrl, htmlStr);
        String fileName = String.format("%s.jpg", QW_PICTURE_URL);
        loadLpXmImg(lpDetails, (String) qwMap.get(QW_PICTURE_URL), fileName);
        return qwMap;
    }

    /**
     * 效果图
     *
     * @param lpDetails 楼盘项目明细
     * @return 区位图URL列表
     */
    private List<String> xgPicture(Map<String, Object> lpDetails) {
        String requestUrl = String.format(XM_SHOW_PIC_TEMPLATE, lpDetails.get(PROJECT_ID));
        String htmlStr = httpGetBodyNoHead(requestUrl, "xgPicture", UTF8);
        List<String> qwList = parser.xmXgPictureParser(requestUrl, htmlStr);
        qwList.forEach(qwUrl -> {
            String[] arr = qwUrl.split("/");
            String fileName = String.format("%s_%s", "xg", arr[arr.length - 1]);
            loadLpXmImg(lpDetails, qwUrl, fileName);
        });

        return qwList;
    }

    /**
     * 户型图
     *
     * @param lpDetails 楼盘项目明细
     * @return 户型图URL列表
     */
    private List<String> layoutPicture(Map<String, Object> lpDetails) {
        String requestUrl = String.format(XM_LAYOUT_TEMPLATE, lpDetails.get(PROJECT_ID));
        String htmlStr = httpGetBodyNoHead(requestUrl, "layoutPicture", UTF8);
        List<String> layoutList = parser.xmLayoutPictureParser(requestUrl, htmlStr);
        layoutList.forEach(url -> {
            String[] arr = url.split("/");
            String fileName = String.format("%s_%s", "layout", arr[arr.length - 1]);
            loadLpXmImg(lpDetails, url, fileName);
        });

        return layoutList;
    }

    /**
     * 下载预售许可证图片
     *
     * @param persaleregDetailsMap 预售证信息
     */
    private void loadPreSaleLicenseListImg(Map<String, Object> persaleregDetailsMap) {
        String url = (String) persaleregDetailsMap.get(PRE_SALE_LICENSE_IMG_URL);
        String[] arr = url.split("/");
        String fileName = String.format("%s_%s_%s", persaleregDetailsMap.get(PRE_SALE_PERMIT_CODE), "presale", arr[arr.length - 1]);
        loadLpXmImg(persaleregDetailsMap, url, fileName);
    }

    /**
     * 下载 物价申报价格表。
     *
     * @param building 楼栋信息
     */
    private void loadBuildingPriceListImg(Map<String, Object> building) {
        String url = (String) building.get(PRICE_LIST_IMG_URL);
        if (StringUtils.isEmpty(url)) {
            return;
        }
        String[] arr = url.split("/");
        String fileName = String.format("%s_%s_%s", building.get(BUILDING_NUM), "pricelist", arr[arr.length - 1]);
        loadLpXmImg(building, url, fileName);
    }

    private void unitTask() {
        MAIN_EXECUTOR.submit(() -> {
            while (true) {
                try {
                    // 获取待请求明细信息的房屋数据
                    List<FdNanJinUnit> houses = dao.selectHouseDetailByPendingRequest();
                    if (!houses.isEmpty()) {
                        houses.forEach(house -> {
                            UNIT_EXECUTOR.submit(() -> {
                                Map<String, Object> houseDetails = new LinkedHashMap<>();
                                houseDetails.put(HOUSE_DETAIL_URL, house.getHouseDetailUrl());
                                houseDetails.put(PROJECT_ID, house.getProjectId());
                                houseDetails.put(BUILDING_NUM, house.getBuildingNum());
                                xmUnitDetails(houseDetails);
                                dao.updateXmUnit(houseDetails);
                            });
                        });
                    } else {
                        Thread.sleep(10 * 1000L);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 因为房屋面积和房屋价格有部分是图片展示的，所以要去下载图片
     *
     * @param houseDetails 房屋明细信息
     */
    public static void loadHouseAreaAndPriceImg(Map<String, Object> houseDetails) {
        houseDetails.forEach((k, v) -> {
            String v1 = (String) v;
            if (StringUtils.startsWith(v1, "common")) {
                // 房号_属性名.png 1802_area.png
                String fileName = String.format("%s_%s.png", houseDetails.get(HOUSE_NUM), k);
                loadBuildingImg(houseDetails, "https://www.njhouse.com.cn/" + v1, fileName);
            }
        });
    }

    /**
     * 下载当前楼栋相关的图片；例如：当前楼栋的物价申报价格表；房屋的价格图片；房屋的面积图片。
     *
     * @param baseMap  必须包含项目ID和楼栋号
     * @param url      图片的绝对路径
     * @param fileName 文件的名称
     */
    private static void loadBuildingImg(Map<String, Object> baseMap, String url, String fileName) {
        IMG_LOAD_EXECUTOR.submit(() -> {
            try {
                if (baseMap.containsKey(PROJECT_ID) && baseMap.containsKey(BUILDING_NUM)) {
                    byte[] data = ImageReaderUtils.imageToByte(url);
                    // /projectId/楼栋号/
                    String filePath = String.format("%s/%s/%s/",
                            NAN_JIN_IMG_PATH, baseMap.get(PROJECT_ID),
                            baseMap.get(BUILDING_NUM));
                    FileUtils.printFile(data, filePath, fileName, false);
                } else {
                    log.error("缺少 PROJECT_ID & BUILDING_NUM. {}", baseMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 下载当前楼盘项目相关的图片；例如：鸟瞰图；区位图；效果图；户型图；
     *
     * @param baseMap  必须包含项目ID
     * @param url      图片的绝对路径
     * @param fileName 文件的名称
     */
    private static void loadLpXmImg(Map<String, Object> baseMap, String url, String fileName) {
        IMG_LOAD_EXECUTOR.submit(() -> {
            try {
                if (baseMap.containsKey(PROJECT_ID)) {
                    try {

                        byte[] data = ImageReaderUtils.imageToByte(url);
                        // /projectId/楼栋号/
                        String filePath = String.format("%s/%s/",
                                NAN_JIN_IMG_PATH, baseMap.get(PROJECT_ID));
                        FileUtils.printFile(data, filePath, fileName, false);
                    } catch (Exception e) {
                        log.error("url: {}, 图片下载失败:{}", url, e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    log.error("缺少 PROJECT_ID . {}", baseMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 将主信息中的项目ID和项目名称，复制到从信息中来 <br>
     *
     * @param master 主
     * @param slave  从
     */
    private void apply(Map<String, Object> master, Map<String, Object> slave) {
        slave.put(PROJECT_ID, master.get(PROJECT_ID));
        slave.put(PROJECT_NAME, master.get(PROJECT_NAME));
    }

}
